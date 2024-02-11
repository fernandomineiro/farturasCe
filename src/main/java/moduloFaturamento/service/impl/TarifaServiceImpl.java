package moduloFaturamento.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.TarifaGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.tarifa.ParamametrosPesquisaTarifaFilterDTO;
import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;
import moduloFaturamento.dto.tarifa.TarifaDeletarDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.Tarifa;
import moduloFaturamento.regras.tarifa.TarifaRegra;
import moduloFaturamento.repository.TarifaRepository;
import moduloFaturamento.service.TarifaService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.tarifa.TarifaRegraDeletarValidacao;
import moduloFaturamento.validacoes.tarifa.TarifaRegraParaValidacao;

@Service
@Transactional
public class TarifaServiceImpl implements TarifaService{

    @Autowired
    private TarifaRepository repository;    
    @Autowired
    private TarifaRegra tarifaRegra;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private TarifaRegraParaValidacao tarifaPesquisarValidacao;
    @Autowired
    private TarifaRegraDeletarValidacao tarifaRegraDeletarValidacao;

    @Override
    public GenericoWrapperDTO<List<PesquisaTarifaRespostaDTO>> pesquisarTarifas(
            ParamametrosPesquisaTarifaFilterDTO tarifaFilterDTO, Pageable pageable) {

        tarifaPesquisarValidacao.gerenciarValidacaoPesquisaExigida(tarifaFilterDTO.getDataTarifa());

        Integer converterDataEmInteger = verificarSeDataEstaNull(tarifaFilterDTO.getDataTarifa());

        List<PesquisaTarifaRespostaDTO> listaTarifas = verificarQualPesquisaRealizar(tarifaFilterDTO, converterDataEmInteger);

        return Paginacao.paginarCampoUnico(new TarifaGridComparator(), pageable, listaTarifas);
    }

    @Override
    public List<TarifaLocalidadeRespostaDTO> buscarListaDasRegioes() {
        return repository.buscarBairrodaLocalidade().stream()
                                                    .map(x -> new TarifaLocalidadeRespostaDTO(x.getId(), x.getNome()))
                                                    .collect(Collectors.toList());
    }

    @Override
    public String cadastrarNovasTarifas(MultipartFile file, String token) {

        Workbook workbook = tarifaRegra.criarWorkbookDoUpload(file);

        List<Tarifa> listNovaPlanilha = tarifaRegra.gerarRegistrosNovasTarifasUpload(workbook);

        rotinaAuditoriaParaSalvarTarifa(listNovaPlanilha, token, "salvar");

        return "Planilha salva";
    }

    @Override
    public void deletarTarifaPorData(@Valid TarifaDeletarDTO tarifaDeletarDTO, String token) {
        
        tarifaPesquisarValidacao.gerenciarValidacaoPesquisaExigida(tarifaDeletarDTO.getDataTarifa());

        Integer converterDataEmInteger = ConverterData.localDateEmIntegerDataFormatoDB(tarifaDeletarDTO.getDataTarifa());
        
        tarifaRegraDeletarValidacao.gerenciarValidacaoVerificarExclucao(converterDataEmInteger, tarifaDeletarDTO.getIdTarifa());

        List<Tarifa> listaParaDeletar = repository.findByIdIdTarifaAndIdDataTarifa(tarifaDeletarDTO.getIdTarifa(), converterDataEmInteger);

        rotinaAuditoriaParaSalvarTarifa(listaParaDeletar, token, "deletar");
    }
     
	public Resource downloadModeloXLS(String token) {

		Workbook criarArquivoModeloAmostrasMinimasExigidas = tarifaRegra.criarArquivoModeloAmostrasMinimasExigidas();
        
		return tarifaRegra.transformarWorkBookEmResource(criarArquivoModeloAmostrasMinimasExigidas);
	}


    // Métodos privados 
    private void rotinaAuditoriaParaSalvarTarifa(List<Tarifa> lista, String token, String acao) {

        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        for (Tarifa tarifa : lista) {            
            
            if (acao.equalsIgnoreCase("salvar")) {

                Tarifa tarifaSalva = repository.saveAndFlush(tarifa);
                Long idAuditoria = repository.buscarCampoIdAuditoria(tarifaSalva.getId().getIdTarifa(), tarifaSalva.getId().getDataTarifa());
                tarifaSalva.setIdAuditoria(idAuditoria);

                String jsonDiaNoCicloDepoisDoCadastrado = ConvertObjectToJsonCesan.execute(tarifaSalva);
                auditoriaService.gerarAuditoriaCadastramento(tarifaSalva.getIdAuditoria(), jsonDiaNoCicloDepoisDoCadastrado, 67L, "Tarifa", idUsuario);
                
            } else { // Deletar
                
                String jsonDiaNoCicloAntesDeletar = ConvertObjectToJsonCesan.execute(tarifa);
                Long idAuditoria = tarifa.getIdAuditoria();
                repository.delete(tarifa);
                auditoriaService.gerarAuditoria(idAuditoria,jsonDiaNoCicloAntesDeletar, Constants.EMPTY_STRING, 67L, "Tarifa", idUsuario);
            }       
        }  
    }

    private  List<PesquisaTarifaRespostaDTO> verificarQualPesquisaRealizar(ParamametrosPesquisaTarifaFilterDTO tarifaFilterDTO, Integer converterDataEmInteger) {

        List<PesquisaTarifaRespostaDTO> listaTarifas;

        if (Optional.ofNullable(tarifaFilterDTO.getIdLocalidade()).orElse((short) 0) != 0) {

            listaTarifas = repository.pesquisaTarifaComLocalidade(tarifaFilterDTO.getIdLocalidade(), tarifaFilterDTO.getIdTarifca(), converterDataEmInteger)
                                                        .stream()
                                                        .map(dados -> new PesquisaTarifaRespostaDTO(dados.getId(), ConverterData.integerEmLocalDateFormatoDB(dados.getDataT()), dados.getGrupo(), dados.getLimite(), 
                                                        dados.getAgua(), dados.getVrAguaPfixa(), 
                                                        dados.getVrEsgotoTratado(), dados.getVrEsgotoTratatoPFixo(),
                                                        dados.getVrEsgotoNaoTratado(), dados.getVrEsgotoNaoTratadoPfixa(), 
                                                        dados.getVrDispEsgoto(), dados.getVrDispEsgotoPfixo()))
                                                        .collect(Collectors.toList());
            tarifaPesquisarValidacao.gerenciarValidacaoPesquisaExistente(listaTarifas);
        } else {

            listaTarifas = repository.pesquisaTarifaSemLocalidade(tarifaFilterDTO.getIdTarifca(), converterDataEmInteger)
                                                        .stream()
                                                        .map(dados -> new PesquisaTarifaRespostaDTO(dados.getId(), ConverterData.integerEmLocalDateFormatoDB(dados.getDataT()), dados.getGrupo(), dados.getLimite(), 
                                                        dados.getAgua(), dados.getVrAguaPfixa(), 
                                                        dados.getVrEsgotoTratado(), dados.getVrEsgotoTratatoPFixo(),
                                                        dados.getVrEsgotoNaoTratado(), dados.getVrEsgotoNaoTratadoPfixa(), 
                                                        dados.getVrDispEsgoto(), dados.getVrDispEsgotoPfixo()))
                                                        .collect(Collectors.toList());     
        }
        return listaTarifas;
    }

    private Integer verificarSeDataEstaNull(LocalDate dataTarifa) {

        if (dataTarifa != null) {
            Integer converterDataEmInteger = ConverterData.localDateEmIntegerDataFormatoDB(dataTarifa);
            return converterDataEmInteger;
        }
        return null;
    }
    
}

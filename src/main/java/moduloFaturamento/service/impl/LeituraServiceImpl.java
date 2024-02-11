package moduloFaturamento.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelSalvarAnexoDTO;
import moduloFaturamento.service.LeituraAnexoService;
import moduloFaturamento.validacoes.leitura.LeituraConsumoImovelConsultaValidacao;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.anexos.model.LeituraAnexo;
import moduloFaturamento.anexos.repository.LeituraAnexoRepository;
import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.LeituraComparador;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.leitura.LeituraAgrupadorListAtualizarDTO;
import moduloFaturamento.dto.leitura.LeituraAnexoDTO;
import moduloFaturamento.dto.leitura.LeituraMostraDataDTO;
import moduloFaturamento.dto.leitura.LeituraParaAtualizarDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarListaDTO;
import moduloFaturamento.dto.leitura.projection.LeituraCicloProjectionDTO;
import moduloFaturamento.dto.leitura.projection.LeituraOcorrenciasProjectionDTO;
import moduloFaturamento.dto.leitura.projection.PesquisarNomeEIdLocalidadeProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IdLeitura;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.regras.leitura.LeituraRegraCampoDataDaLeitura;
import moduloFaturamento.regras.leitura.LeituraRegraPesquisaLeitura;
import moduloFaturamento.regras.leitura.LeituraRegraVerificarSeExisteFase01NaReferencia;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.service.LeituraService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.leitura.LeituraAnexoValidacao;
import moduloFaturamento.validacoes.leitura.LeituraAtualizarValidacao;
import moduloFaturamento.validacoes.leitura.LeituraPesquisarValidacao;

@Service
@Transactional
public class LeituraServiceImpl implements LeituraService{

    @Autowired
    private LeituraRepository repository;
    @Autowired
    private LeituraPesquisarValidacao validacaoPesquisa;
    @Autowired
    private LeituraRegraPesquisaLeitura leituraRegraPesquisaLeitura;
    @Autowired
    private LeituraRegraCampoDataDaLeitura leituraRegraCampoDataDaLeitura;
    @Autowired
    private LeituraAtualizarValidacao leituraAtualizarValidacao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private LeituraAnexoService leituraAnexoService;
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private LeituraRegraVerificarSeExisteFase01NaReferencia leituraRegraVerificarSeExisteFase01NaReferencia;
    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private LeituraAnexoRepository leituraAnexoRepository;
    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private LeituraAnexoValidacao leituraAnexoValidacao;
    @Autowired
    private LeituraConsumoImovelConsultaValidacao leituraConsumoImovelConsultaValidacao;

    @Override
    public List<PesquisarNomeEIdLocalidadeProjectionDTO> buscarNomeDaLocalidade() {
        return repository.buscarCiclosdaLocalidade();
    }

    @Override
    public List<LeituraCicloProjectionDTO> buscarNumerosCiclos() {
        return repository.listaCiclos();
    }

    @Override
    public LeituraMostraDataDTO buscarDataDaleitura(LeituraPesquisarListaDTO listaDTO) {
        
        validacaoPesquisa.gerenciarValidacaoCampoDataDaLeitura(listaDTO.getReferencia());

        return leituraRegraCampoDataDaLeitura.regraParaBuscarDataDaLeiturasPrevista(listaDTO.getIdCidade(), listaDTO.getCiclo(), listaDTO.getReferencia());
    }

    @Override
    public List<LeituraOcorrenciasProjectionDTO> buscarOcorrecnias() {
        return repository.listaOcorrencias();
    }


    @Override
    public GenericoWrapperDTO<List<LeituraPesquisarDTO>> listarLeituraLocalidadeCicloReferencia(LeituraPesquisarListaDTO listaDTO, Pageable pageable) {

        validacaoPesquisa.gerenciarValidacaoPesquisaLeitura(listaDTO.getIdCidade(), listaDTO.getCiclo(), listaDTO.getReferencia());
        
        List<Leitura> listaMatriculasComRefrenciaANterior = leituraRegraPesquisaLeitura.regraParaListarLeituras(listaDTO.getIdCidade(), listaDTO.getCiclo(), listaDTO.getReferencia());
        
        List<LeituraPesquisarDTO> listaDaLeituraAnteriores = listaMatriculasComRefrenciaANterior.stream()
                                        .map(dado -> new LeituraPesquisarDTO(
                                        repository.buscarARotaDaMatriculaERefFaturaDaPesquisa(dado.getIdLeituraFaturamento().getMatriculaImovel(), listaDTO.getReferencia()), 
                                        dado.getIdLeituraFaturamento().getMatriculaImovel(),
                                        repository.bucarDvDaMatricula(dado.getIdLeituraFaturamento().getMatriculaImovel()), 
                                        dado.getLeitura()) )
                                        .collect(Collectors.toList() );

        return Paginacao.paginarCampoUnico(new LeituraComparador(), pageable, listaDaLeituraAnteriores);
    }

    @Override
    public void atualizarLeituras(LeituraAgrupadorListAtualizarDTO listaDeLeituraDTO, String token) {

        leituraAtualizarValidacao.gerenciarValidacaoPesquisaLeitura(listaDeLeituraDTO.getListaAtualizarDTO());

        gerarAuditoriaValidado(listaDeLeituraDTO.getListaAtualizarDTO(), token);
    }

    @Override
    @Transactional(value = "chainedTransactionManager")
    public void executarFluxoSalvarAnexoConsumoImovel(LeituraConsumoImovelSalvarAnexoDTO dto, String token){
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(dto.getIdLeitura());

        Leitura leitura = this.leituraRepository.buscarLeituraExistentePorId(dto.getIdLeitura());

        //this.leituraAnexoService.salvarAnexos(leitura, dto.getAnexoDTO(), token);
    }



    /**
     * Métodos privados do ServiceImpl de Leitura
     */
    @Transactional(value = "chainedTransactionManager")
    public void gerarAuditoriaValidado(List<LeituraParaAtualizarDTO> listaLeituraDTO, String token) {

            String loginUsuario = jwtTokenProvider.buscarLogin(token);
            Long idUsuario = jwtTokenProvider.buscarIdUsuario(token); 
            short numeroDaOrigemFatura = 1;
            
            for (LeituraParaAtualizarDTO leituraDTO : listaLeituraDTO) {

                Leitura recuperandoLeituraNoBanco = repository.findById(new IdLeitura(leituraDTO.getMatricula(), leituraDTO.getRefFatura())).get();

                String jsonLeituraAntesDaAlteracao = ConvertObjectToJsonCesan.execute(recuperandoLeituraNoBanco);
                jsonLeituraAntesDaAlteracao = auditoriaService.forcarRegistroCamposEmAlteracao(jsonLeituraAntesDaAlteracao, "refFatura", "id");

                converterLeituraNova(recuperandoLeituraNoBanco, leituraDTO);
                
                recuperandoLeituraNoBanco.setLoginUsuario(loginUsuario);
                repository.save(recuperandoLeituraNoBanco);  

                faturaRepository.mudarFaturDeFase01aParafase02(leituraDTO.getMatricula(), numeroDaOrigemFatura, leituraDTO.getRefFatura());

                String jsonLeituraDepoisDaAlteracao = ConvertObjectToJsonCesan.execute(recuperandoLeituraNoBanco);

                auditoriaService.gerarAuditoria(recuperandoLeituraNoBanco.getIdLeituraFaturamento().getMatriculaImovel().longValue(), 
                    jsonLeituraAntesDaAlteracao, jsonLeituraDepoisDaAlteracao, 68L, "Leitura", idUsuario);

                if(leituraDTO.getAnexos() != null){

                    for (LeituraAnexoDTO anexoDTO : leituraDTO.getAnexos()) {
                        
                        this.leituraAnexoService.executarFluxoSalvarAnexoLeituraFatura(recuperandoLeituraNoBanco, anexoDTO, token);
                    }
                }               
           }

        if(leituraRegraVerificarSeExisteFase01NaReferencia.regraVerificarSePodeMudarFase01ParaFase02EmCronogramaFatura(
                                                listaLeituraDTO.get(0).getIdCidade(),
                                                listaLeituraDTO.get(0).getCiclo(), 
                                                listaLeituraDTO.get(0).getRefFatura()
                                                )
            ){
            cronogramaFaturaRepository.atualizarFaseReferenciaDe01Para02(ConverterData.localDateEmIntegerDataFormatoDB(listaLeituraDTO.get(0).getDataLeitura()),
                                                                    listaLeituraDTO.get(0).getIdCidade(), 
                                                                    listaLeituraDTO.get(0).getCiclo(), 
                                                                    listaLeituraDTO.get(0).getRefFatura()
                                                                    );  
        }      
    }

    private void converterLeituraNova(Leitura recuperandoLeituraNoBanco, LeituraParaAtualizarDTO leituraNova) {

        if (leituraNova.getAgente() !=  null) {
            recuperandoLeituraNoBanco.setAgente(leituraNova.getAgente());
        }
        if (leituraNova.getLeitura() !=  null) {
            recuperandoLeituraNoBanco.setLeitura(leituraNova.getLeitura());
        }
        if (leituraNova.getIdOcorrencia() !=  null) {
            recuperandoLeituraNoBanco.setOcorrencia(leituraNova.getIdOcorrencia());
        }
        if (leituraNova.getIdOcorrencia2() !=  null) {
            recuperandoLeituraNoBanco.setOcorrencia2(leituraNova.getIdOcorrencia2());
        }
        if (leituraNova.getIdOcorrencia3() !=  null) {
            recuperandoLeituraNoBanco.setOcorrencia3(leituraNova.getIdOcorrencia3());
        }
        recuperandoLeituraNoBanco.setDataDeleitura(ConverterData.localDateEmIntegerDataFormatoDB(leituraNova.getDataLeitura()));
    }

    private void salvarAnexos(Leitura leitura, LeituraAnexoDTO anexoDTO , String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
		String loginUsuario = jwtTokenProvider.buscarLogin(token);

        byte[] arquivo = Base64.decodeBase64(anexoDTO.getData());

        LeituraAnexo leituraAnexo =  new LeituraAnexo();

        leituraAnexo.setNomeArquivo(anexoDTO.getNomeArquivo());
        leituraAnexo.setTamanhoArquivo(arquivo.length);

        leituraAnexo.setDataHoraInclusao(LocalDateTime.now());
        leituraAnexo.setLeituraId(leitura.getId());
        leituraAnexo.setArquivo(arquivo);
            
        leituraAnexo.setUsuario(loginUsuario);
        leituraAnexo.setAcessoRestrito("N");

        leituraAnexoValidacao.gerenciarValidacaoAnexo(leituraAnexo);

        leituraAnexoRepository.save(leituraAnexo);
            
        String anexosSolicitacaoServicoJson = ConvertObjectToJsonCesan.execute(leituraAnexo);

        auditoriaService.gerarAuditoriaCadastramento(leituraAnexo.getId(), anexosSolicitacaoServicoJson, 68L, "Anexos Leitura", idUsuario);
	}

    @Override
    public List<Leitura> buscarLeituraExistentePorMatricula(Integer matricula) {
        List<Leitura> leitura = repository.buscarLeituraExistentePorMatricula(matricula);
         return leitura;
    }

}



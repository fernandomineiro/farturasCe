package moduloFaturamento.service.impl;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IdIncentivoClienteGrupoConsumo;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.IncentivoClienteGrupoConsumo;
import moduloFaturamento.model.IncentivoClienteHistoricoGrupoConsumo;
import moduloFaturamento.model.common.CadGrupoConsumo;
import moduloFaturamento.repository.IncentivoClienteGrupoConsumoRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoGrupoConsumoRepository;
import moduloFaturamento.repository.common.CadGrupoConsumoRepository;
import moduloFaturamento.service.IncentivoClienteParametroDetalheGrupoConsumoService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo.IncentivoClienteGrupoConsumoAtualizarValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo.IncentivoClienteGrupoConsumoCadastroValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalheGrupoConsumo.IncentivoClienteGrupoConsumoConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IncentivoClienteParametroDetalheGrupoConsumoServiceImpl implements IncentivoClienteParametroDetalheGrupoConsumoService {

    @Autowired
    private IncentivoClienteGrupoConsumoRepository incentivoClienteGrupoConsumoRepository;
    @Autowired
    private CadGrupoConsumoRepository cadGrupoConsumoRepository;
    @Autowired
    private IncentivoClienteGrupoConsumoConsultaValidacao incentivoClienteGrupoConsumoConsultaValidacao;
    @Autowired
    private IncentivoClienteGrupoConsumoCadastroValidacao incentivoClienteGrupoConsumoCadastroValidacao;
    @Autowired
    private IncentivoClienteGrupoConsumoAtualizarValidacao incentivoClienteGrupoConsumoAtualizarValidacao;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private IncentivoClienteHistoricoGrupoConsumoRepository incentivoClienteHistoricoGrupoConsumoRepository;

    @Override
    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> buscarGrupoConsumoDoParametroDeIncentivoCliente(Integer idParametroDetalhe) {
        this.incentivoClienteGrupoConsumoConsultaValidacao.validarCodigoParametroDetalhe(idParametroDetalhe);

        return this.incentivoClienteGrupoConsumoRepository.buscarGrupoConsumoDoParametroIncentivoCliente(idParametroDetalhe);
    }

    @Override
    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarTodosGrupoConsumo() {
        return this.cadGrupoConsumoRepository.listarGrupoDeConsumo();
    }

    @Override
    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarGrupoConsumoAssociadoAoIncentivo(Integer idParametroDetalhe) {
        if(idParametroDetalhe != null){
            this.incentivoClienteGrupoConsumoConsultaValidacao.validarCodigoParametroDetalhe(idParametroDetalhe);
        }

        return this.incentivoClienteGrupoConsumoRepository.buscarGrupoConsumoDoParametroIncentivoCliente(idParametroDetalhe);
    }


    @Override
    public List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> executarFluxoCadastrarGrupoConsumoDoDetalheDoParametroIncentivo(List<Integer> listaGrupoConsumo,
                                                                                                                                          IncentivoClienteDetalhe incentivoClienteDetalhe, String token){
        List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> dtoRespostaLista = new ArrayList<>();
        List<Integer> listaGrupoConsumoDistinct = listaGrupoConsumo.stream().distinct().collect(Collectors.toList());

        for(Integer idGrupoConsumo : listaGrupoConsumoDistinct){
            this.incentivoClienteGrupoConsumoCadastroValidacao.validar(idGrupoConsumo);

            CadGrupoConsumo gc = new CadGrupoConsumo(idGrupoConsumo);
            IncentivoClienteGrupoConsumo salvo = this.cadastrarGrupoConsumoValido(incentivoClienteDetalhe, gc, this.jwtTokenProvider.buscarIdUsuario(token));

            IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO dtoResposta = new IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO(incentivoClienteDetalhe.getId(), gc.getId());
            dtoRespostaLista.add(dtoResposta);
        }

        return dtoRespostaLista;
    }

    @Override
    public List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> executarFluxoEditarGrupoConsumoDoDetalheDoParametroIncentivo(List<Integer> listaGrupoConsumo,
                                                                                                                                        IncentivoClienteDetalhe incentivoClienteDetalhe, String token){
        List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> dtoRespostaLista = new ArrayList<>();
        List<Integer> listaGrupoConsumoDistinct = listaGrupoConsumo.stream().distinct().collect(Collectors.toList());
        List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> listaParaAuditoria = new ArrayList<>();

        this.removerGrupoConsumoCadastradoParaIncentivo(incentivoClienteDetalhe, this.jwtTokenProvider.buscarIdUsuario(token));

        for(Integer idGrupoConsumo : listaGrupoConsumoDistinct){
            this.incentivoClienteGrupoConsumoAtualizarValidacao.validar(idGrupoConsumo);

            CadGrupoConsumo gc = new CadGrupoConsumo(idGrupoConsumo);
            IncentivoClienteGrupoConsumo salvo = this.atualizarGrupoConsumoValido(incentivoClienteDetalhe, gc, this.jwtTokenProvider.buscarIdUsuario(token));

            IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO dtoResposta = new IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO(incentivoClienteDetalhe.getId(), gc.getId());
            dtoRespostaLista.add(dtoResposta);
        }

        return dtoRespostaLista;
    }

    private IncentivoClienteGrupoConsumo cadastrarGrupoConsumoValido(IncentivoClienteDetalhe incentivoClienteDetalhe, CadGrupoConsumo cadGrupoConsumo, Long idUsuario){
        IdIncentivoClienteGrupoConsumo id = new IdIncentivoClienteGrupoConsumo(incentivoClienteDetalhe, cadGrupoConsumo);
        IncentivoClienteGrupoConsumo grupoConsumo = new IncentivoClienteGrupoConsumo(id);

        this.incentivoClienteGrupoConsumoRepository.save(grupoConsumo);
        Long idAuditoria = this.incentivoClienteGrupoConsumoRepository.buscarIdIncentivoClienteGrupoConsumoPorChaveComposta(grupoConsumo.getId().getIncentivoClienteDetalhe().getId(), grupoConsumo.getId().getCadGrupoConsumo().getId());

        this.auditoriaService.gerarAuditoriaCadastramento(idAuditoria, ConvertObjectToJsonCesan.execute(grupoConsumo), 73L, "Parâmetro de Negociação", idUsuario);

        this.salvarHistoricoGrupoConsumo(grupoConsumo);

        return grupoConsumo;
    }

    private IncentivoClienteGrupoConsumo atualizarGrupoConsumoValido(IncentivoClienteDetalhe incentivoClienteDetalhe, CadGrupoConsumo cadGrupoConsumo, Long idUsuario){
       return this.cadastrarGrupoConsumoValido(incentivoClienteDetalhe, cadGrupoConsumo, idUsuario);
    }

    private void removerGrupoConsumoCadastradoParaIncentivo(IncentivoClienteDetalhe incentivoClienteDetalhe, Long idUsuario){
        Optional<List<IncentivoClienteGrupoConsumo>> grupoConsumosOptional = this.incentivoClienteGrupoConsumoRepository.findById_IncentivoClienteDetalhe_Id(incentivoClienteDetalhe.getId());

        if(grupoConsumosOptional.isPresent()){
            List<IncentivoClienteGrupoConsumo> grupoConsumos =  grupoConsumosOptional.get();
            for (IncentivoClienteGrupoConsumo gc : grupoConsumos){
                String dadosAntesDeAtualizar = ConvertObjectToJsonCesan.execute(gc);

                this.incentivoClienteGrupoConsumoRepository.delete(gc);

                this.auditoriaService.gerarAuditoriaRemocao(gc.getIdAuditoria(), Constants.EMPTY_STRING , 73L, "Parâmetro de Negociação", idUsuario);
            }
        }
    }

    private void salvarHistoricoGrupoConsumo(IncentivoClienteGrupoConsumo grupoConsumo) {
        IncentivoClienteHistoricoGrupoConsumo incentivoClienteHistoricoGrupoConsumo = new IncentivoClienteHistoricoGrupoConsumo(grupoConsumo);
        this.incentivoClienteHistoricoGrupoConsumoRepository.save(incentivoClienteHistoricoGrupoConsumo);
    }
}

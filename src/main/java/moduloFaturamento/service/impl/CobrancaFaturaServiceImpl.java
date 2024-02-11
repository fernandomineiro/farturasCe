package moduloFaturamento.service.impl;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.CobrancaFaturaGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cobrancaFatura.*;
import moduloFaturamento.dto.cobrancaFatura.mapper.CobrancaFaturaCadastroMapper;
import moduloFaturamento.dto.cobrancaFatura.mapper.CobrancaFaturaRespostaConsultaMapper;
import moduloFaturamento.dto.cobrancaFatura.mapper.CobrancaFaturaRespostaSalvarMapper;
import moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.dto.imovel.projection.CodigoClienteDoImovelDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.CobrancaServicoFatura;
import moduloFaturamento.model.IdCobrancaServicoFatura;
import moduloFaturamento.model.common.*;
import moduloFaturamento.regras.cobrancaFatura.CobrancaFaturaCadastroRegra;
import moduloFaturamento.regras.cobrancaFatura.CobrancaFaturaConsultaRegra;
import moduloFaturamento.regras.cobrancaFatura.CobrancaFaturaEdicaoRegra;
import moduloFaturamento.regras.cobrancaFatura.CobrancaFaturaRemocaoRegra;
import moduloFaturamento.regras.cobrancaFatura.spec.CobrancaFaturaTipoNumeroSolicitacaoEnum;
import moduloFaturamento.regras.dossie.DossieImovelCobrancaFaturaRegra;
import moduloFaturamento.repository.CobrancaServicoFaturaRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.SolicitacaoServicoRepository;
import moduloFaturamento.service.CobrancaFaturaService;
import moduloFaturamento.service.common.ServicoNaoTarifadoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.cobrancaFatura.CobrancaFaturaAtualizarValidacao;
import moduloFaturamento.validacoes.cobrancaFatura.CobrancaFaturaCadastroValidacao;
import moduloFaturamento.validacoes.cobrancaFatura.CobrancaFaturaConsultaValidacao;
import moduloFaturamento.validacoes.cobrancaFatura.CobrancaFaturaRemocaoValidacao;
import moduloFaturamento.validacoes.imovel.ImovelConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CobrancaFaturaServiceImpl implements CobrancaFaturaService {
    @Autowired
    private CobrancaServicoFaturaRepository cobrancaServicoFaturaRepository;
    @Autowired
    private SolicitacaoServicoRepository solicitacaoServicoRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private DossieImovelRepository dossieImovelRepository;
    @Autowired
    private CobrancaFaturaConsultaValidacao cobrancaFaturaConsultaValidacao;
    @Autowired
    private CobrancaFaturaRemocaoValidacao cobrancaFaturaRemocaoValidacao;
    @Autowired
    private CobrancaFaturaAtualizarValidacao cobrancaFaturaAtualizarValidacao;
    @Autowired
    private ImovelConsultaValidacao imovelConsultaValidacao;
    @Autowired
    private CobrancaFaturaRemocaoRegra cobrancaFaturaRemocaoRegra;
    @Autowired
    private CobrancaFaturaCadastroRegra cobrancaFaturaCadastroRegra;
    @Autowired
    private DossieImovelCobrancaFaturaRegra dossieImovelCobrancaFaturaRegra;
    @Autowired
    private CobrancaFaturaEdicaoRegra cobrancaFaturaEdicaoRegra;
    @Autowired
    private CobrancaFaturaRespostaConsultaMapper cobrancaFaturaRespostaConsultaMapper;
    @Autowired
    private CobrancaFaturaCadastroMapper cobrancaFaturaCadastroMapper;
    @Autowired
    private CobrancaFaturaRespostaSalvarMapper cobrancaFaturaRespostaSalvarMapper;
    @Autowired
    private ServicoNaoTarifadoService servicoNaoTarifadoService;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CobrancaFaturaCadastroValidacao cobrancaFaturaCadastroValidacao;
    @Autowired
    private CobrancaFaturaConsultaRegra cobrancaFaturaConsultaRegra;

    @Override
    public List<BigDecimal> buscarValoresCadastradosNoServicosNaoTarifados(Short cdServico, Integer matriculaImovel, Short dv) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matriculaImovel, dv);
        Short cdLocalidade = this.imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matriculaImovel, dv);
        return this.servicoNaoTarifadoService.buscarListaValorServicoNaoTarifadoPorCodigoServicoELocalidadade(cdServico, cdLocalidade);
    }

    @Override
    public GenericoWrapperDTO<List<CobrancaFaturaRespostaGridProjectionDTO>> buscarCobrancaPorFiltro(CobrancaFaturaFilterDTO filter, Pageable pageable) {
        Integer matriculaImovel = filter.getMatriculaImovel();
        Short dvMatricula = filter.getDvMatricula();
        Short codigoServico = filter.getCodigoServico();
        Integer referenciaFaturamento = (filter.getReferenciaFaturamento() != null ? ConverterData.localDateEmIntegerReferenciaFormatoDB(filter.getReferenciaFaturamento()) : null);
        List<CobrancaFaturaRespostaGridProjectionDTO> gridDTO = this.cobrancaServicoFaturaRepository.buscarListaFeriadosPorFiltro(matriculaImovel, dvMatricula, codigoServico, referenciaFaturamento);

        return Paginacao.paginarCampoUnico(new CobrancaFaturaGridComparator(), pageable, gridDTO);
    }

    @Override
    public CobrancaFaturaRespostaConsultaDTO buscarCobrancaPorId(Long id) {
        this.cobrancaFaturaConsultaValidacao.validar(id);
        CobrancaServicoFatura cobrancaServicoFatura = this.cobrancaServicoFaturaRepository.findById(id);

        CobrancaFaturaRespostaConsultaDTO dto = this.cobrancaFaturaRespostaConsultaMapper.toDto(cobrancaServicoFatura);
        Integer data = cobrancaServicoFatura.getDataExecucao();
        return dto;
    }

    @Override
    public CobrancaFaturaRespostaSalvarDTO executarFluxoCadastrarCobranca(CobrancaFaturaCadastrarDTO dto, String token) {
        this.cobrancaFaturaCadastroValidacao.gerenciarValidacaoParaCadastrar(dto);
        CobrancaServicoFatura cobrancaFatura = this.cobrancaFaturaCadastroMapper.toEntity(dto);
        CobrancaServicoFatura cobrancaSalva =  this.cadastrarCobrancaValida(cobrancaFatura, dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao(), dto.getTipoSolicitacao(), dto.getJustificativa(),
                this.jwtTokenProvider.buscarIdUsuario(token), this.jwtTokenProvider.buscarLogin(token));

        return new CobrancaFaturaRespostaSalvarDTO(cobrancaSalva.getId());
    }

    @Override
    public CobrancaFaturaRespostaSalvarDTO executarFluxoEditarCobranca(CobrancaFaturaAtualizarDTO dto, String token){
        this.cobrancaFaturaAtualizarValidacao.gerenciarValidacaoParaEditar(dto);
        CobrancaServicoFatura cobrancaServicoFatura = this.cobrancaServicoFaturaRepository.findById(dto.getId());

        String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(cobrancaServicoFatura);

        CobrancaServicoFatura cobrancaAtualizada = this.atualizarCobrancaValida(cobrancaServicoFatura, dto.getCodigoServico(), dto.getValorServico(), dto.getDataDeExecucao(), dto.getReferenciaFaturar(),
                dto.getTipoSolicitacao(), dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao(), dto.getJustificativa(),
                this.jwtTokenProvider.buscarLogin(token), this.jwtTokenProvider.buscarIdUsuario(token), dadosAntesAtualizar);

        return new CobrancaFaturaRespostaSalvarDTO(cobrancaAtualizada.getId());
    }


    @Override
    public void executarFluxoRemoverCobranca(Long id, String token) {
        this.cobrancaFaturaRemocaoValidacao.validar(id);
        CobrancaServicoFatura cobrancaValida = this.cobrancaServicoFaturaRepository.findById(id);
        this.removerCobrancaValida(cobrancaValida, this.jwtTokenProvider.buscarIdUsuario(token));
    }

    private synchronized CobrancaServicoFatura cadastrarCobrancaValida(CobrancaServicoFatura cobrancaFatura, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao, CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao,
                                                      String dcAnotacao,Long idUsuario, String loginUsuario) {
        cobrancaFatura.setSolicitacaoServico(this.retornarConstrucaoSolicitacaoServico(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));
        cobrancaFatura.setItemAtendimento(this.retornarConstrucaoItemAtendimento(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));

        Short proximoCodigoCdCobrancaParaSerUsado = this.cobrancaFaturaCadastroRegra.obterProximoCodigoCdCobrancaQuePodeSerUsadoParaCadastrarCobranca(cobrancaFatura.getIdCobrancaServicoFatura().getMatriculaImovel());

        cobrancaFatura.setIdCobrancaServicoFatura(new IdCobrancaServicoFatura(proximoCodigoCdCobrancaParaSerUsado, cobrancaFatura.getIdCobrancaServicoFatura().getMatriculaImovel()));
        cobrancaFatura.setLoginUsuario(loginUsuario);

        CobrancaServicoFatura cobrancaSalva = this.cobrancaServicoFaturaRepository.saveAndFlush(cobrancaFatura);
        cobrancaSalva.setId(this.cobrancaServicoFaturaRepository.buscarIdCobrancaPorMatriculaECdCobranca(cobrancaFatura.getIdCobrancaServicoFatura().getMatriculaImovel(),
                cobrancaFatura.getIdCobrancaServicoFatura().getCdCobranca()));

        this.gerarDossieImovel(dcAnotacao, cobrancaSalva.getIdCobrancaServicoFatura().getMatriculaImovel(), cobrancaSalva.getDv(), loginUsuario);

        this.auditoriaService.gerarAuditoriaCadastramento(cobrancaSalva.getIdCobrancaServicoFatura().getMatriculaImovel().longValue(), ConvertObjectToJsonCesan.execute(cobrancaSalva), 69L, "Cobrança em Fatura", idUsuario);

        return cobrancaSalva;
    }

    private CobrancaServicoFatura atualizarCobrancaValida(CobrancaServicoFatura cobrancaFatura, Short cdServico, BigDecimal valorServico, LocalDate dataHoraExecucao,LocalDate dataRerefenciaFaturar,
                                                          CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao,
                                                          String justificativa, String loginUsuario, Long idUsuario,  String dadosAntesAtualizar) {

        this.cobrancaFaturaEdicaoRegra.validarReferenciaFatura(cobrancaFatura.getRefFatura());

        cobrancaFatura.setServico(new Servico(cdServico));
        cobrancaFatura.setValorServico(valorServico);
        cobrancaFatura.setDataExecucao(ConverterData.localDateEmIntegerDataFormatoDB(dataHoraExecucao));
        cobrancaFatura.setRefFaturar(dataRerefenciaFaturar != null ? ConverterData.localDateEmIntegerDataFormatoDB(dataRerefenciaFaturar) : 0);

        cobrancaFatura.setSolicitacaoServico(this.retornarConstrucaoSolicitacaoServico(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));
        cobrancaFatura.setItemAtendimento(this.retornarConstrucaoItemAtendimento(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));

        this.gerarDossieImovel(justificativa, cobrancaFatura.getIdCobrancaServicoFatura().getMatriculaImovel(), cobrancaFatura.getDv(), loginUsuario);

        this.auditoriaService.gerarAuditoriaAlteracao(cobrancaFatura.getIdCobrancaServicoFatura().getMatriculaImovel().longValue(), dadosAntesAtualizar, ConvertObjectToJsonCesan.execute(cobrancaFatura), 69L, "Cobrança em Fatura", idUsuario);
        return cobrancaFatura;
    }

    private void gerarDossieImovel(String dcAnotacao, Integer matricula, Short dv, String loginUsuario){
        CodigoClienteDoImovelDTO dto = this.imovelRepository.buscarClientePelaMatriculaDoImovel(matricula, dv);
        DossieImovel dossieImovelValido = this.dossieImovelCobrancaFaturaRegra.construirDossieImovel(dcAnotacao, matricula, dv, dto.getCodigo(), dto.getDv(), loginUsuario);
        this.dossieImovelRepository.save(dossieImovelValido);
    }

    private void removerCobrancaValida(CobrancaServicoFatura cobrancaValida, Long idUsuario) {
        Short codigoServico = cobrancaValida.getServico().getCdServico();
        Integer referenciaFaturada = cobrancaValida.getRefFatura();

        this.cobrancaFaturaRemocaoRegra.validarDadosEntidadeExistente(codigoServico, referenciaFaturada);

        String feriadoAntesAtualizar = ConvertObjectToJsonCesan.execute(cobrancaValida);

        cobrancaValida.setDataHoraExclusao(LocalDateTime.now());

        this.auditoriaService.gerarAuditoriaAlteracao(cobrancaValida.getIdCobrancaServicoFatura().getMatriculaImovel().longValue(), feriadoAntesAtualizar, ConvertObjectToJsonCesan.execute(cobrancaValida), 69L, "Cobrança em Fatura", idUsuario);
    }

    private SolicitacaoServico retornarConstrucaoSolicitacaoServico(CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao){
        SolicitacaoServico ss =  null;
        if (tipoSolicitacao != null && tipoSolicitacao.getTipo().equals(CobrancaFaturaTipoNumeroSolicitacaoEnum.SOLICITACAO_SERVICO.getTipo())) {
            ss = new SolicitacaoServico(new IdSolicitacaoServico(codSolicitacao, refSolicitacao, seqSolicitacao));
        }
        return ss;
    }

    private ItemAtendimento retornarConstrucaoItemAtendimento(CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao){
        ItemAtendimento itemAtendimento =  null;
        if (tipoSolicitacao != null && tipoSolicitacao.getTipo().equals(CobrancaFaturaTipoNumeroSolicitacaoEnum.ITEM_ATENDIMENTO.getTipo())) {
            itemAtendimento = new ItemAtendimento(new IdItemAtendimento(codSolicitacao, refSolicitacao, seqSolicitacao));
        }
        return itemAtendimento;
    }
}

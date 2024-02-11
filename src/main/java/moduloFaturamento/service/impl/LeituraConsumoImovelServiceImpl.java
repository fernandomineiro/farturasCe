package moduloFaturamento.service.impl;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.LeituraConsumoImovelGridComparator;
import moduloFaturamento.comparator.LeituraConsumoImovelMatriculaMicroGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.classificacaoImobiliaria.LeituraConsumoImovelCabecalhoClassificacaoImovelDTO;
import moduloFaturamento.dto.imovel.projection.CodigoClienteDoImovelDTO;
import moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO;
import moduloFaturamento.dto.imovelArrecadacao.ArrecadacaoImovelDTO;
import moduloFaturamento.dto.leituraConsumoImovel.*;
import moduloFaturamento.dto.leituraConsumoImovel.mapper.LeituraConsumoImovelRespostaConsultaMapper;
import moduloFaturamento.dto.leituraConsumoImovel.mapper.LeituraConsumoImovelTipoMotivoEdicaoMapper;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelCabecalhoDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.TipoConsumoFaturado;
import moduloFaturamento.model.TipoMotivoEdicaoLeituraConsumo;
import moduloFaturamento.model.common.*;
import moduloFaturamento.regras.dossie.DossieImovelLeituraConsumoImovelRegra;
import moduloFaturamento.regras.leituraConsumoImovel.consumoFaturado.LeituraConsumoFaturadoConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.consumoFaturadoEsgoto.LeituraConsumoFaturadoEsgotoConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.consumoMedido.LeituraConsumoMedidoConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.consumoMedidoMacro.LeituraConsumoMedidoMacroConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.dias.diasConsumo.LeituraConsumoDiasConsumoConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.dias.diasVenda.LeituraConsumoDiasVendaConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise.LeituraFaixaConsumoMaximoParaAnaliseConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise.LeituraFaixaConsumoMinimoParaAnaliseConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.mediaDiaria.LeituraConsumoMediaDiariaConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.spec.LeituraConsumoImovelTipoNumeroSolicitacaoEnum;
import moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.LeituraConsumoTipoConsumoConsultaRegra;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.repository.TipoMotivoEdicaoLeituraConsumoRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.service.FaturaService;
import moduloFaturamento.service.LeituraConsumoImovelService;
import moduloFaturamento.service.LeituraHistoricoService;
import moduloFaturamento.service.common.ClassificacaoImobiliariaService;
import moduloFaturamento.service.common.HidrometroRetiradoService;
import moduloFaturamento.service.common.ImovelArrecadacaoService;
import moduloFaturamento.service.common.OcorrenciaLeituraService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.imovel.ImovelConsultaValidacao;
import moduloFaturamento.validacoes.leitura.LeituraConsumoImovelConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LeituraConsumoImovelServiceImpl implements LeituraConsumoImovelService {
    @Autowired
    private ImovelConsultaValidacao imovelConsultaValidacao;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private TipoMotivoEdicaoLeituraConsumoRepository tipoMotivoEdicaoLeituraConsumoRepository;
    @Autowired
    private DossieImovelRepository dossieImovelRepository;
    @Autowired
    private ClassificacaoImobiliariaService classificacaoImobiliariaService;
    @Autowired
    private HidrometroRetiradoService hidrometroRetiradoService;
    @Autowired
    private ImovelArrecadacaoService imovelArrecadacaoService;
    @Autowired
    private FaturaService faturaService;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private OcorrenciaLeituraService ocorrenciaLeituraService;
    @Autowired
    private LeituraHistoricoService leituraHistoricoService;
    @Autowired
    private LeituraConsumoImovelConsultaValidacao leituraConsumoImovelConsultaValidacao;
    @Autowired
    private LeituraConsumoImovelRespostaConsultaMapper leituraConsumoImovelRespostaConsultaMapper;
    @Autowired
    private LeituraConsumoImovelTipoMotivoEdicaoMapper leituraConsumoImovelTipoMotivoEdicaoMapper;
    @Autowired
    private LeituraFaixaConsumoMaximoParaAnaliseConsultaRegra faixaConsumoMaximoParaAnaliseConsultaRegra;
    @Autowired
    private LeituraFaixaConsumoMinimoParaAnaliseConsultaRegra faixaConsumoMinimoParaAnaliseConsultaRegra;
    @Autowired
    private LeituraConsumoMedidoConsultaRegra leituraConsumoMedidoConsultaRegra;
    @Autowired
    private LeituraConsumoMediaDiariaConsultaRegra leituraConsumoMediaDiariaConsultaRegra;
    @Autowired
    private LeituraConsumoTipoConsumoConsultaRegra leituraConsumoTipoConsumoConsultaRegra;
    @Autowired
    private LeituraConsumoFaturadoConsultaRegra leituraConsumoFaturadoConsultaRegra;
    @Autowired
    private LeituraConsumoDiasVendaConsultaRegra leituraConsumoDiasVendaConsultaRegra;
    @Autowired
    private LeituraConsumoDiasConsumoConsultaRegra leituraConsumoDiasConsumoConsultaRegra;
    @Autowired
    private DossieImovelLeituraConsumoImovelRegra dossieImovelLeituraConsumoImovelRegra;
    @Autowired
    private LeituraConsumoMedidoMacroConsultaRegra leituraConsumoMedidoMacroConsultaRegra;
    @Autowired
    private LeituraConsumoFaturadoEsgotoConsultaRegra leituraConsumoFaturadoEsgotoConsultaRegra;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public List<LeituraConsumoImovelTipoMotivoEdicaoDTO> buscarListaMotivoEdicaoLeitura() {
        List<TipoMotivoEdicaoLeituraConsumo> motivos = this.tipoMotivoEdicaoLeituraConsumoRepository.findAll();
        return this.leituraConsumoImovelTipoMotivoEdicaoMapper.toDto(motivos);
    }

    @Override
    public List<ImovelMatriculaComDvProjectionDTO> buscarListaMatriculasMicroDeUmaMatriculaMacro(Integer matricula){
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula);
        return this.imovelRepository.buscarMatriculaMicroComDvDeImovelMacro(matricula);
    }

    @Override
    public LeituraConsumoImovelCabecalhoDTO buscarCabecalhoDadosDoImovel(Integer matricula, Short dv) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula, dv);

        LeituraConsumoImovelCabecalhoDTO dto = imovelRepository.buscarImovelParaCabecalhoLeituraConsumoPelaMatricula(matricula);

        List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> listaClassificacaoDTO = this.classificacaoImobiliariaService.buscarListaClassificacaoImobiliariaParaCabecalhoLeituraConsumoImovel(matricula);
        ArrecadacaoImovelDTO arrecadacaoImovelDTO = this.imovelArrecadacaoService.buscarArrecadacaoPorMatriculaImovel(matricula);
        HidrometroRetirado hidrometroRetirado = this.hidrometroRetiradoService.retornarEntidadeUltimoHidrometroRetiradoDaMatriculaImovel(matricula);
        Fatura fatura = this.faturaService.retornarEntidadeUltimoFaturamentoDoImovel(matricula, null);
        Short dvImovel = this.imovelRepository.buscarDvDoImovelPelaMatricula(dto.getMatriculaMacroNumero());


        dto.setListaDeClassificacaoImobiliariaETotalEconomias(listaClassificacaoDTO);
        dto.setHidrometroRetirado(hidrometroRetirado);
        dto.setNumeroDebitoContaCorrente(arrecadacaoImovelDTO);
        dto.setUltimoFaturamento(fatura);
        dto.setMatriculaMacroDv(dvImovel);


        return dto;
    }


    @Override
    public LeituraConsumoImovelFaixaConsumoAnaliseDTO buscarValorMaximoEMinimoFaixaConsumoParaAnalise(BigDecimal mediaDiariaDaLeitura) {
        BigDecimal valorMaximoFaixaConsumoParaAnalise = this.faixaConsumoMaximoParaAnaliseConsultaRegra.obterValorFaixa(mediaDiariaDaLeitura);
        BigDecimal valorMinimoFaixaConsumoParaAnalise = this.faixaConsumoMinimoParaAnaliseConsultaRegra.obterValorFaixa(mediaDiariaDaLeitura);

        return new LeituraConsumoImovelFaixaConsumoAnaliseDTO(valorMaximoFaixaConsumoParaAnalise, valorMinimoFaixaConsumoParaAnalise);
    }

    @Override
    public LeituraConsumoImovelRespostaConsumoMedidoDTO buscarValorConsumoMedido(LeituraConsumoImovelConsumoMedidoDTO dto) {
        Integer matriculaImovel = dto.getMatriculaImovel();
        LocalDate refFatura = dto.getRefFatura();
        Integer leituraAtual = dto.getLeituraAtual();

        LeituraConsumoImovelFaixaConsumoAnaliseDTO valorFaixaConsumoDTO = this.buscarValorMaximoEMinimoFaixaConsumoParaAnalise(dto.getMediaDiariaLeitura());
        BigDecimal valorMaximoFaixaConsumoParaAnalise = valorFaixaConsumoDTO.getValorMaximo();
        BigDecimal valorMinimoFaixaConsumoParaAnalise = valorFaixaConsumoDTO.getValorMinimo();


        Integer leituraConsumo = this.leituraConsumoMedidoConsultaRegra.consultaConsumoMedido(matriculaImovel, refFatura, leituraAtual);
        boolean leituraConsumoForaDaFaixaConsumo = this.leituraConsumoMedidoConsultaRegra.consultaFlagAbrirPopupConsumoMedidoForaDaFaixa(leituraConsumo, valorMaximoFaixaConsumoParaAnalise, valorMinimoFaixaConsumoParaAnalise);
        //boolean abrirPopupConsumoMedidoModificadoParaMatriculaMicro = this.leituraConsumoMedidoConsultaRegra.retornarSeDeveSerAbertoUmPopupQuandoConsumoMedidoEhModificadoEmMatriculaMicro(matriculaImovel, refFatura, leituraConsumo);
        boolean abrirPopupConsumoMedidoModificadoParaMatriculaMicro = this.leituraConsumoMedidoMacroConsultaRegra.consultarSeHouveAlteracaoEmAlgumaMatriculaMicro(matriculaImovel, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura));

        return new LeituraConsumoImovelRespostaConsumoMedidoDTO(leituraConsumo, leituraConsumoForaDaFaixaConsumo, abrirPopupConsumoMedidoModificadoParaMatriculaMicro);

    }


    @Override
    public Integer buscarConsumoMedidoMatriculaMacro(Integer matricula, LocalDate refFatura){
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula);
        return this.leituraConsumoMedidoMacroConsultaRegra.consultarConsumoMedidoMacro(matricula, refFatura);
    }

    @Override
    public Short buscarNovoTipoConsumoFaturado(Integer matricula, LocalDate refFatura, Short idTipoConsumoFaturado, LeituraConsumoImovelTipoConsumoFaturadoFilterDTO dto) {
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(matricula, refFatura);

        return this.leituraConsumoTipoConsumoConsultaRegra.consultarTipoConsumo(matricula, idTipoConsumoFaturado, refFatura, dto.getConsumoMedido(), dto.getDiasDeVenda(), dto.getMediaDiaria());
    }

    @Override
    public BigDecimal buscarValorConsumoFaturado(Integer matricula, LocalDate refFatura, Short idTipoConsumoFaturado, LeituraConsumoImovelConsumoFaturadoFilterDTO dto) {
        return this.leituraConsumoFaturadoConsultaRegra.consultarValorConsumoFaturado(idTipoConsumoFaturado, matricula, refFatura, dto.getValorLeituraInformada(), dto.getDiasDeVenda(), dto.getMediaDiaria());
    }

    @Override
    public BigDecimal buscarConsumoFaturadoEsgotoParaMatriculaPrincipal(Integer matricula, LocalDate refFatura, BigDecimal consumoFaturado){
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(matricula, refFatura);

        return this.leituraConsumoFaturadoEsgotoConsultaRegra.consultarConsumoFaturadoEsgoto(matricula, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura), consumoFaturado);
    }

    @Override
    public Short buscarValorDiasDeVenda(Integer matricula, LocalDate dataLeitura, LocalDate refFatura) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula);
        return this.leituraConsumoDiasVendaConsultaRegra.obterValorDiasDeVenda(matricula, dataLeitura, refFatura);
    }

    @Override
    public Short buscarValorDiasDeConsumo(Integer matricula, LocalDate dataLeitura, LocalDate refFatura) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula);
        return this.leituraConsumoDiasConsumoConsultaRegra.obterValorDiasDeConsumo(matricula, dataLeitura, refFatura);
    }


    @Override
    public BigDecimal buscarMediaDiaria(Integer matricula, LocalDate dataReferencia) {
        return this.leituraConsumoMediaDiariaConsultaRegra.consultarMediaDiaria(matricula, dataReferencia);
    }

    @Override
    public LeituraConsumoImovelRespostaConsultaDTO buscarLeituraConsumoImovelPorId(Long id) {
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(id);

        Leitura leitura = this.leituraRepository.buscarLeituraExistentePorId(id);

        LeituraConsumoImovelRespostaConsultaDTO dto = this.leituraConsumoImovelRespostaConsultaMapper.toDto(leitura);

        String ocorrenciaLeituraCodigoUm = this.ocorrenciaLeituraService.buscarDescricaoPorCodigoOcorrencia(dto.getOcorrenciaCodigo());
        String ocorrenciaLeituraCodigoDois = this.ocorrenciaLeituraService.buscarDescricaoPorCodigoOcorrencia(dto.getOcorrencia2Codigo());
        String ocorrenciaLeituraCodigoTres = this.ocorrenciaLeituraService.buscarDescricaoPorCodigoOcorrencia(dto.getOcorrencia3Codigo());
        Integer matriculaMacro = this.imovelRepository.buscarSeMatriculaEhMacro(leitura.getIdLeituraFaturamento().getMatriculaImovel());
        List<Integer> matriculasSecundarias = this.imovelRepository.retornarMatriculasSecundariasMatriculaMae(leitura.getIdLeituraFaturamento().getMatriculaImovel());

        boolean houveAlteracaoEmAlgumaMatriculaMicro = this.leituraConsumoMedidoMacroConsultaRegra.consultarSeHouveAlteracaoEmAlgumaMatriculaMicro(leitura.getIdLeituraFaturamento().getMatriculaImovel(), leitura.getIdLeituraFaturamento().getRefFatura());

        boolean houveAlteracaoMatriculaSecundaria = this.leituraConsumoFaturadoEsgotoConsultaRegra.consultarSeHouveAlteracaoEmAlgumaMatriculaSecundaria(leitura.getIdLeituraFaturamento().getMatriculaImovel(), leitura.getIdLeituraFaturamento().getRefFatura());
        dto.setMatriculaMacro(matriculaMacro);
        dto.setAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaMicro(houveAlteracaoEmAlgumaMatriculaMicro);
        dto.setMatriculaPrincipal(matriculasSecundarias);
        dto.setAbrirPopupHouveAlgumaModificacaoEmAlgumaMatriculaSecundaria(houveAlteracaoMatriculaSecundaria);

        dto.setOcorrenciaDescricao(ocorrenciaLeituraCodigoUm);
        dto.setOcorrencia2Descricao(ocorrenciaLeituraCodigoDois);
        dto.setOcorrencia3Descricao(ocorrenciaLeituraCodigoTres);
        LeituraConsumoImovelFaixaConsumoAnaliseDTO valorFaixaConsumo = this.buscarValorMaximoEMinimoFaixaConsumoParaAnalise(dto.getMediaDiaria());
        dto.setFaixaConsumoParaAnaliseValorMaximo(valorFaixaConsumo.getValorMaximo());
        dto.setFaixaConsumoParaAnaliseValorMinimo(valorFaixaConsumo.getValorMinimo());

        return dto;
    }

    @Override
    public GenericoWrapperDTO<List<LeituraConsumoImovelMicroRespostaGridProjectionDTO>> buscarLeiturasSomenteComMatriculaMicroPorFiltro(Integer matriculaMacro, LocalDate refFatura, Pageable pageable) {
        List<Integer> matriculasMicroDeUmaMatriculaMacro = this.imovelRepository.buscarMatriculaMicroDeImovelMacro(matriculaMacro);
        List<LeituraConsumoImovelMicroRespostaGridProjectionDTO> listaLeituraDTO = new ArrayList<>();
        for (Integer matriculaMicro : matriculasMicroDeUmaMatriculaMacro) {
            LeituraConsumoImovelMicroRespostaGridProjectionDTO dto = this.leituraRepository.buscarLeituraDeMatriculaMicroPorFiltro(matriculaMicro, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura));
            if (dto != null) {
                listaLeituraDTO.add(dto);
            }
        }

        return Paginacao.paginarCampoUnico(new LeituraConsumoImovelMatriculaMicroGridComparator(), pageable, listaLeituraDTO);
    }

    @Override
    public GenericoWrapperDTO<List<LeituraConsumoImovelRespostaGridProjectionDTO>> buscarLeiturasPorFiltro(Integer matricula, LocalDate refFatura, Pageable pageable) {
        this.imovelConsultaValidacao.validarMatriculaImovel(matricula);
        Integer referencia = (refFatura != null ? ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura) : null);

        List<LeituraConsumoImovelRespostaGridProjectionDTO> dto = this.leituraRepository.buscarListaLeituraPorFiltro(matricula, referencia);

        return Paginacao.paginarCampoUnico(new LeituraConsumoImovelGridComparator(), pageable, dto);
    }

    @Override
    public LeituraConsumoImovelGravadoRespostaIdentidadeDTO executarFluxoEditarLeitura(LeituraConsumoImovelAtualizarDTO dto, String token) {
        this.leituraConsumoImovelConsultaValidacao.gerenciarValidacao(dto.getId());

        Leitura leitura = this.leituraRepository.buscarLeituraExistentePorId(dto.getId());

        String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(leitura);
        String loginUsuario = this.jwtTokenProvider.buscarLogin(token);
        Long idUsuario = this.jwtTokenProvider.buscarIdUsuario(token);


        Leitura leituraEditada = this.atualizarLeituraValida(leitura, dadosAntesAtualizar, dto.getRefSolicitacao(), dto.getCodSolicitacao(), dto.getSeqSolicitacao(), dto.getTipoSolicitacao(), dto.getAlteradoFlag(), dto.getMedido(),
                dto.getFaixaConsumoParaAnaliseValorMaximo(), dto.getFaixaConsumoParaAnaliseValorMinimo(), dto.getOcorrenciaCodigo(), dto.getOcorrencia2Codigo(), dto.getOcorrencia3Codigo(),
                dto.getConsumoFaturarAgua(), dto.getConsumoFaturarEsgoto(), dto.getDiasConsumo(), dto.getDiasVenda(), dto.getLeitura(), dto.getMediaDiaria(), dto.getIdTipoConsumoFaturado(),
                dto.getIdTipoMotivoEdicao(), dto.getJustificativa(), dto.getMatriculaMacro(), dto.getConsumoMedidoMacro(), dto.getMatriculaPrincipal(), dto.getLeituraCriada(),
                dto.getExcluirCalculoDaMedia(), dto.getDataDeLeitura(), loginUsuario, idUsuario);

        return new LeituraConsumoImovelGravadoRespostaIdentidadeDTO(leituraEditada.getId());

    }

    private Leitura atualizarLeituraValida(Leitura leitura, String dadosAntesAtualizar, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao, LeituraConsumoImovelTipoNumeroSolicitacaoEnum tipoSolicitacao,
                                           String alteradoFlag, Integer consumoMedido, BigDecimal faixaConsumoParaAnaliseValorMaximo, BigDecimal faixaConsumoParaAnaliseValorMinimo, Short ocorrencia,
                                           Short ocorrencia2, Short ocorrencia3, BigDecimal consumoFaturadoAgua, BigDecimal consumoFaturadoEsgoto, Short diasDeConsumo, Short diasDeVenda, Integer valorLeitura,
                                           BigDecimal mediaDiaria, Short idTipoConsumoFaturar, Short motivoEdicao, String justificativa, Boolean matriculaMacro, Integer consumoMedidoMacro,
                                           Boolean matriculaPrincipal, Boolean leituraCriada, Boolean excluirCalculoDaMedia, LocalDate dataDeLeitura, String loginUsuario, Long idUsuario) {

        this.leituraHistoricoService.executarFluxoCadastrarHistoricoLeituraValidaInformada(leitura);

        short codigoAnormalidade = this.leituraConsumoMedidoConsultaRegra.retornarCodigoAnormalidadeSeHouver(consumoMedido, faixaConsumoParaAnaliseValorMaximo, faixaConsumoParaAnaliseValorMinimo);

        if (codigoAnormalidade != -1) {
            leitura.setCdAnormalidade(codigoAnormalidade);
        }

        if (alteradoFlag.equals("S")) {
            leitura.setDataHoraEdicao(LocalDate.now());
        }

        if (matriculaMacro) {
            leitura.setConsumoMedidoMacro(consumoMedidoMacro);
        } else {
            boolean consumoMedidoRecebidoDiferenteConsumoMedidoAnterior = !consumoMedido.equals(leitura.getMedido());
            if (consumoMedidoRecebidoDiferenteConsumoMedidoAnterior) {
                leitura.setConsumoMedidoMicroModificado("S");
            }else{
                leitura.setConsumoMedidoMicroModificado("N");
            }
        }

        if (!matriculaPrincipal) {
            boolean consumoFaturadoEsgotoRecebidoDiferenteConsumoFaturadoAnterior = !consumoFaturadoEsgoto.equals(leitura.getConsumoFaturarEsgoto());
            if (consumoFaturadoEsgotoRecebidoDiferenteConsumoFaturadoAnterior) {
                leitura.setConsumoFaturadoEsgotoSecundarioModificado("S");
            }else{
                leitura.setConsumoFaturadoEsgotoSecundarioModificado("N");
            }
        }

        leitura.setOcorrencia(ocorrencia != null ? ocorrencia : 0);
        leitura.setOcorrencia2(ocorrencia2 != null ? ocorrencia2 : 0);
        leitura.setOcorrencia3(ocorrencia3 != null ? ocorrencia3 : 0);
        leitura.setConsumoFaturarAgua(consumoFaturadoAgua != null ? consumoFaturadoAgua : leitura.getConsumoFaturarAgua());
        leitura.setConsumoFaturarEsgoto(consumoFaturadoEsgoto != null ? consumoFaturadoEsgoto : leitura.getConsumoFaturarEsgoto());
        leitura.setDiasVenda(diasDeVenda);
        leitura.setDiasConsumo(diasDeConsumo);
        leitura.setLeitura(valorLeitura);
        leitura.setMediaDiaria(mediaDiaria);
        leitura.setMedido(consumoMedido);
        leitura.setTipoMotivoEdicaoLeituraConsumo(new TipoMotivoEdicaoLeituraConsumo(motivoEdicao));
        leitura.setLeituraCriada(leituraCriada ? "S" : "N");
        leitura.setExcluirCalculoMedia(excluirCalculoDaMedia ? "S" : "N");
        leitura.setDataDeleitura(ConverterData.localDateEmIntegerDataFormatoDB(dataDeLeitura));

        if(idTipoConsumoFaturar != null){
            leitura.setTipoConsumoFaturado(new TipoConsumoFaturado(idTipoConsumoFaturar));
        }

        leitura.setSolicitacaoServico(this.retornarConstrucaoSolicitacaoServico(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));
        leitura.setItemAtendimento(this.retornarConstrucaoItemAtendimento(tipoSolicitacao, refSolicitacao, codSolicitacao, seqSolicitacao));

        this.gerarDossieImovel(justificativa, leitura.getIdLeituraFaturamento().getMatriculaImovel(), leitura.getDv(), loginUsuario);

        this.auditoriaService.gerarAuditoria(leitura.getIdLeituraFaturamento().getMatriculaImovel().longValue(), dadosAntesAtualizar, ConvertObjectToJsonCesan.execute(leitura), 68L, "Leitura", idUsuario);

        return leitura;
    }

    private void gerarDossieImovel(String dcAnotacao, Integer matricula, Short dv, String loginUsuario) {
        CodigoClienteDoImovelDTO dto = this.imovelRepository.buscarClientePelaMatriculaDoImovel(matricula, dv);
        DossieImovel dossieImovelValido = this.dossieImovelLeituraConsumoImovelRegra.construirDossieImovel(dcAnotacao, matricula, dv, dto.getCodigo(), dto.getDv(), loginUsuario);
        this.dossieImovelRepository.save(dossieImovelValido);
    }

    private SolicitacaoServico retornarConstrucaoSolicitacaoServico(LeituraConsumoImovelTipoNumeroSolicitacaoEnum tipoSolicitacao, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao) {
        SolicitacaoServico ss = null;
        if (tipoSolicitacao != null && tipoSolicitacao.getTipo().equals(LeituraConsumoImovelTipoNumeroSolicitacaoEnum.SOLICITACAO_SERVICO.getTipo())) {
            ss = new SolicitacaoServico(new IdSolicitacaoServico(codSolicitacao, refSolicitacao, seqSolicitacao));
        }
        return ss;
    }

    private ItemAtendimento retornarConstrucaoItemAtendimento(LeituraConsumoImovelTipoNumeroSolicitacaoEnum tipoSolicitacao, Integer refSolicitacao, Integer codSolicitacao, Short seqSolicitacao) {
        ItemAtendimento itemAtendimento = null;
        if (tipoSolicitacao != null && tipoSolicitacao.getTipo().equals(LeituraConsumoImovelTipoNumeroSolicitacaoEnum.ITEM_ATENDIMENTO.getTipo())) {
            itemAtendimento = new ItemAtendimento(new IdItemAtendimento(codSolicitacao, refSolicitacao, seqSolicitacao));
        }
        return itemAtendimento;
    }


}

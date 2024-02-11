package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.ParcelamentoFaturaComparador;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaPorDeliberacaoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaAdutoriaDaFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaAuditoriaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaListaDasSituacoesDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaCadastroDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaContasEmAbertoDoClienteDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaEnvioDaSimulacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaEmailDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaFaturaEmAbertoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaValoresDaParcelaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoPagamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoRespostaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSituacaoDasParcelasDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFauraSelecioadoDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaCabecalhoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaListaFaturaEmAbertoParaSimulacaoParcelamentoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaPesquisaResponseProjectionDTO;
import moduloFaturamento.excecoes.ExcecaoRegraNegocio;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.ParcelamentoFaturaCalculoParcelamentoMapper;
import moduloFaturamento.mapper.ParcelamentoFaturaConverterDTOMapper;
import moduloFaturamento.mapper.ParcelamentoFaturaFaturasMapper;
import moduloFaturamento.model.Credito;
import moduloFaturamento.model.DetalheDaFaturaParcelada;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.FaturamentoParcelamento;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.model.LancamentosDaFatura;
import moduloFaturamento.model.common.DossieCliente;
import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.regras.FaturamentoParcelamentoParcela.FaturamentoParcelamentoParcelaCrudRegra;
import moduloFaturamento.regras.detalheDaFaturaParcelada.DetalheDaFaturaParceladaCrudRegra;
import moduloFaturamento.regras.diaUtilCalendario.DiaUtilCalendarioPesquisaPorCidadeRegra;
import moduloFaturamento.regras.dossie.DossieImovelParcelamentofatura;
import moduloFaturamento.regras.dossieCliente.DossieClienteFaturamentoRegra;
import moduloFaturamento.regras.faturamentoParcelamentoRegra.FaturamentoParcelamentoCrudRegra;
import moduloFaturamento.regras.lancamentoContabil.LancamentoContabilParaCrudCreditoRegra;
import moduloFaturamento.regras.lancamentoContabil.LancamentoContabilParaCrudFaturaRegra;
import moduloFaturamento.regras.lancamentoContabil.LancamentoContabilParaCrudParcelasDoParcelamentoRegra;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.DetalheDaFaturaParceladaRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoParcelaRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoRepository;
import moduloFaturamento.repository.IncentivoClienteDetalheRepository;
import moduloFaturamento.repository.IncentivoClienteRepository;
import moduloFaturamento.repository.LancamentoContabilRepository;
import moduloFaturamento.repository.LancamentosDaFaturaRepository;
import moduloFaturamento.repository.common.DossieClienteRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.ServicoRepository;
import moduloFaturamento.service.IncentivoClienteParametroDetalheService;
import moduloFaturamento.service.IncentivoClienteParametroService;
import moduloFaturamento.service.ParcelamentoFaturaService;
import moduloFaturamento.service.TipoIncentivoClienteService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.parcelamentoFatura.ParcelamentoFaturaClienteValidacao;
import moduloFaturamento.validacoes.parcelamentoFatura.ParcelamentoFaturaSimulacaoValidacao;

@Service
@Transactional
public class ParcelamentoFaturaServiceImpl implements ParcelamentoFaturaService {

    @Autowired
    private ParcelamentoFaturaClienteValidacao parcelamentoFaturaClienteValidacao;
    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ParcelamentoFaturaFaturasMapper parcelamentoFaturaFaturasMapper;
    @Autowired
    private ParcelamentoFaturaSimulacaoValidacao parcelamentoFaturaSimulacaoValidacao;
    @Autowired
    private CreditoRepository creditoRepository;
    @Autowired
    private ParcelamentoFaturaCalculoParcelamentoMapper parcelamentoFaturaCalculoParcelamentoMapper;
    @Autowired
    private DiaUtilCalendarioPesquisaPorCidadeRegra diaUtilCalendarioPesquisaPorCidadeRegra;
    @Autowired
    private DossieImovelParcelamentofatura dossieImovelParcelamentofatura;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private DossieImovelRepository dossieImovelRepository;
    @Autowired
    private DossieClienteFaturamentoRegra dossieClienteFaturamentoRegra;
    @Autowired
    private ParcelamentoFaturaConverterDTOMapper parcelamentoFaturaConverterDTOMapper;
    @Autowired
    private IncentivoClienteRepository incentivoClienteRepository;
    @Autowired
    private IncentivoClienteDetalheRepository incentivoClienteDetalheRepository;
    @Autowired
    private DossieClienteRepository dossieClienteRepository;
    @Autowired
    private FaturamentoParcelamentoCrudRegra faturamentoParcelamentoCrudRegra;
    @Autowired
    private FaturamentoParcelamentoRepository faturamentoParcelamentoRepository;
    @Autowired
    private FaturamentoParcelamentoParcelaCrudRegra faturamentoParcelamentoParcelaCrudRegra;
    @Autowired
    private LancamentoContabilParaCrudParcelasDoParcelamentoRegra lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra;
    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    @Autowired
    private LancamentosDaFaturaRepository lancamentosDaFaturaRepository;
    @Autowired
    private LancamentoContabilParaCrudFaturaRegra lancamentoContabilParaCrudFaturaRegra;
    @Autowired
    private DetalheDaFaturaParceladaCrudRegra detalheDaFaturaParceladaCrudRegra;
    @Autowired
    private DetalheDaFaturaParceladaRepository detalheDaFaturaParceladaRepository;
    @Autowired
    private FaturamentoParcelamentoParcelaRepository faturamentoParcelamentoParcelaRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private LancamentoContabilParaCrudCreditoRegra lancamentoContabilParaCrudCreditoRegra;
    @Autowired
    private AuditoriaService auditoriaService;
    
    @Autowired
    private TipoIncentivoClienteService tipoIncentivoClienteService;

    /** */
    @Autowired
    private IncentivoClienteParametroService incentivoClienteParametroService;
    @Autowired
    private IncentivoClienteParametroDetalheService incentivoClienteParametroDetalheService;

    @Override
    public GenericoWrapperDTO<List<ParcelamentoFaturaPesquisaResponseProjectionDTO>> buscarFaturasParaCliente( Integer cdCliente, Short dvCliente, String cpfOuCnpj, 
        Pageable pageable) {

        parcelamentoFaturaClienteValidacao.verificarClienteValidao(cdCliente, dvCliente, cpfOuCnpj);

        cdCliente = verificarCdCliente(cdCliente, cpfOuCnpj);

        List<ParcelamentoFaturaPesquisaResponseProjectionDTO> listaDividasPorCliente = faturaRepository.buscarDividasEmAbertaPorCliente(cdCliente);

        return Paginacao.paginarCampoUnico( new ParcelamentoFaturaComparador(), pageable, listaDividasPorCliente);
    }

    @Override
    public String buscarNome(Integer cdCliente, String cpfOuCnpj) {

        cdCliente = verificarCdCliente(cdCliente, cpfOuCnpj);

        return clienteRepository.buscarNomeCliente(cdCliente);
    }

    @Override
    public ParcelamentoFaturaCabecalhoProjectionDTO buscarCabecalhoDaFaturaEmAberto( String cdClienteString, Integer matriculaImovel) {

        Integer cdCliente = verificarCdCliente(cdClienteString);

        return faturaRepository.buscarPorClienteFaturaDadosDoProprietarioEClienteDaFatura(cdCliente, matriculaImovel);
    }

    // Primeira pesquisa da dívida sem parâmetros
    @Override
    public ParcelamentoFaturaContasEmAbertoDoClienteDTO listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovel(String cdClienteString, Integer matriculaImovel) {

        Integer cdCliente = verificarCdCliente(cdClienteString);
        
        List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaFaturaEmAberto = faturaRepository.
            listaDeFaturaEmAbertoDoClienteEMatricula(cdCliente, matriculaImovel);

        boolean incentivoJuridico = false;
        BigDecimal valorInicialDaEntrada = BigDecimal.ZERO;
        Short numeroParcelas = (short) 0;

        return converterListaDasFaturasEmUmaListaDetalhada(listaFaturaEmAberto, incentivoJuridico, valorInicialDaEntrada, matriculaImovel, numeroParcelas);
    }

    //Exixbir quadro selecionado
    @Override
    public ParcelamentoFaturaContasEmAbertoDoClienteDTO quadroRefrenciasFaturaSelecioanda(ParcelamentoFauraSelecioadoDTO parcelamentoFaturaSimulacaoDTO) {  

        List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaFaturaEmAberto = buscarListaDeReferenciasEmAberto(
            parcelamentoFaturaSimulacaoDTO.getRefFaturaSelecionadas(), parcelamentoFaturaSimulacaoDTO.getMatriculaImovel(), parcelamentoFaturaSimulacaoDTO.getCdCliente());

        return converterListaDasFaturasEmUmaListaDetalhada(listaFaturaEmAberto, parcelamentoFaturaSimulacaoDTO.isIncentivoJuridico(), 
            parcelamentoFaturaSimulacaoDTO.getValorEntrada(), parcelamentoFaturaSimulacaoDTO.getMatriculaImovel(), parcelamentoFaturaSimulacaoDTO.getNumeroDeParcelas());
    }

    // Quadro Contabil
    @Override
    public ParcelamentoFaturaSimulacaoRespostaDTO simularNegociacaoDivida(ParcelamentoFaturaSelecionarFaturaDTO faturasSelecionadasDTO) {

        ParcelamentoFaturaSimulacaoDTO simulacaoDTO = converterDadosParaRealizacaoCalculo(faturasSelecionadasDTO);

        parcelamentoFaturaSimulacaoValidacao.verificarSeDescontosEMaiorQueDivida(simulacaoDTO.getSomatorioValorFatura(), simulacaoDTO.getSomatorioValorJuros(), 
            simulacaoDTO.getSomatorioJurosMora(), simulacaoDTO.getSomarioDescontosAutomaticosFatura(), simulacaoDTO.getSomatorioDescontosAutomaticosJuros(), 
            simulacaoDTO.getSomatorioDescontosAutomaticosJurosMora(), simulacaoDTO.getDescontoDaNegociacao());

        return execultarCalculoDaNegociacao(simulacaoDTO, faturasSelecionadasDTO.getRefFaturaSelecionadas().size(), 
            buscarUltimaReferenciaDeFaturaLocalDate(faturasSelecionadasDTO.getRefFaturaSelecionadas()), faturasSelecionadasDTO.getRefFaturaSelecionadas());
    }

    // Parcelas da divida
    @Override
    public ParcelamentoFaturaSimulacaoPagamentoDTO simularParcelamentoFatura(ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO simulacaoParcelamentoDTO) {

        ParcelamentoFaturaSelecionarFaturaDTO faturasSelecionadasDTO = parcelamentoFaturaCalculoParcelamentoMapper.toDto(simulacaoParcelamentoDTO);

        ParcelamentoFaturaSimulacaoDTO simulacaoDTO = converterDadosParaRealizacaoCalculo(faturasSelecionadasDTO);

        parcelamentoFaturaSimulacaoValidacao.verificarSeDescontosEMaiorQueDivida(simulacaoDTO.getSomatorioValorFatura(), simulacaoDTO.getSomatorioValorJuros(), 
            simulacaoDTO.getSomatorioJurosMora(), simulacaoDTO.getSomarioDescontosAutomaticosFatura(), simulacaoDTO.getSomatorioDescontosAutomaticosJuros(), 
            simulacaoDTO.getSomatorioDescontosAutomaticosJurosMora(), simulacaoDTO.getDescontoDaNegociacao());

        ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao = execultarCalculoDaNegociacao(simulacaoDTO, faturasSelecionadasDTO.getRefFaturaSelecionadas().size(),
            buscarUltimaReferenciaDeFaturaLocalDate(faturasSelecionadasDTO.getRefFaturaSelecionadas()), simulacaoParcelamentoDTO.getRefFaturaSelecionadas());
        
        BigDecimal valorDaEntrada = buscarValorDaEntrada(simulacaoParcelamentoDTO.getValorEntrada(), simulacaoParcelamentoDTO.isPagamentoAVista(), 
            quadroDaSimulacao.getTotalANegociar());
        
        BigDecimal valorASerParceladoMenosAEntrada = quadroDaSimulacao.getTotalANegociar().subtract(valorDaEntrada);

        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValores = calcularValorParcelas(valorASerParceladoMenosAEntrada,
            simulacaoParcelamentoDTO.getNumeroDeParcelas()); 

        if(!listaDasParcelasComValores.isEmpty()) parcelamentoFaturaSimulacaoValidacao.verificarSeParcelasEstaoDeAcordoComINcentivoCasoEstejaSendoAplicado(
            simulacaoParcelamentoDTO.isIncentivoJuridico(), simulacaoParcelamentoDTO.getNumeroDeParcelas(), simulacaoParcelamentoDTO.getRefFaturaSelecionadas(), 
            simulacaoParcelamentoDTO.getMatriculaImovel(), simulacaoDTO.getSomatorioValorFatura(), listaDasParcelasComValores.get(0).getValorParcelas(), 
            simulacaoParcelamentoDTO.getValorEntrada());
        
        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValoresEDatas = colocarDataNasParcelas(listaDasParcelasComValores, 
            simulacaoParcelamentoDTO.getDiaPagamentoDasParcelas(), simulacaoParcelamentoDTO.getDataPagamentoDaEntrada());
            
        return instanciandoValoresParaQuadroParcelamento(simulacaoParcelamentoDTO.getDataPagamentoDaEntrada(), quadroDaSimulacao, listaDasParcelasComValoresEDatas, 
            valorDaEntrada );
    }


    @Override
    public ParcelamentoDaFaturaListaDasSituacoesDTO buscarSituacaoDoParcelamento(Integer matriculaImovel, Integer cdCliente) {

        FaturamentoParcelamento faturamentoParcelamento = faturamentoParcelamentoRepository.buscarParcelamentoDaFaturaAindaNaoPaga(matriculaImovel, cdCliente);

        if (faturamentoParcelamento != null) {

            ParcelamentoDaFaturaListaDasSituacoesDTO parcelaDoParcelamentoDaSituacao = new ParcelamentoDaFaturaListaDasSituacoesDTO();

            List<ParcelamentoFaturaSituacaoDasParcelasDTO> listaDasParcelas = new ArrayList<>();

            List<FaturamentoParcelamentoParcela> listaDaParcela = faturamentoParcelamentoParcelaRepository.
                findByIdFaturamentoParcelamentoParcelaCdParcelamento(faturamentoParcelamento.getCodigoParcelamento());

            executarDadosParaListaDeParcelas(listaDasParcelas, listaDaParcela);

            parcelaDoParcelamentoDaSituacao.setListaDasParcelas(listaDasParcelas);

            BigDecimal totalDasParcelasNaoPagas = calculoSomaDasParcelasAindaNaoPagas(listaDasParcelas);
            
            parcelaDoParcelamentoDaSituacao.setTotal(totalDasParcelasNaoPagas);

            return parcelaDoParcelamentoDaSituacao;
        }
       return null;
    }

    @Override
    public ParcelamentoDaFaturaPorDeliberacaoDTO informacaoDeliberacao(Integer idParametroIncentivoCliente,Integer cdCliente, Pageable pageable) {
 
    	ParcelamentoDaFaturaPorDeliberacaoDTO deliberacao = new ParcelamentoDaFaturaPorDeliberacaoDTO();
    	
    	Short situacaoAgua = imovelRepository.buscarSituacaoAguaDoImovelPorCdCliente(cdCliente);
    	
    	Short situacaoEsgoto = imovelRepository.buscarSituacaoEsgotoDoImovel(cdCliente);
    	
        //GenericoWrapperDTO<List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO>> detalheIncentivo = this.incentivoClienteParametroDetalheService.listarGrid(idParametroIncentivoCliente, pageable);
       
    	GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listIncentivo = this.incentivoClienteParametroService.listarGrid(pageable);
      
        ArrayList<IncentivoClienteParametroRespostaConsultaDTO> incentivoCliente = new ArrayList<IncentivoClienteParametroRespostaConsultaDTO>();
        
        List<IncentivoClienteParametroTipoDTO> tipoDeliberacao =  this.tipoIncentivoClienteService.listarTodosTipoIncentivoCliente();
        
        
        for ( IncentivoClienteParametroRespostaGridProjectionDTO incentivo : listIncentivo.getDados()) {
        	
        	int idIncentivo = incentivo.getId();
        	
        	IncentivoClienteParametroRespostaConsultaDTO incentivoClientes = this.incentivoClienteParametroService.buscarParametroIncentivoPorId(idIncentivo);
        	incentivoCliente.add(incentivoClientes);
        }
        
     	deliberacao.setSituacaoEsgoto(situacaoEsgoto);
    	deliberacao.setSituacaoAgua(situacaoAgua);
        deliberacao.setListIncentivo(listIncentivo);
        deliberacao.setIncentivoCliente(incentivoCliente);
        deliberacao.setTipoDeliberacao(tipoDeliberacao);
        
        return deliberacao;
    }

    @Override
    public LocalDate buscarProximoDiaUtil(Integer matriculaImovel) {

        Integer cdCidade = imovelRepository.buscarCdCidadeDoImovel(matriculaImovel);
        
        LocalDate amanha = LocalDate.now().plusDays(1);

        return diaUtilCalendarioPesquisaPorCidadeRegra.retornarOProximoDiaUtil(amanha, cdCidade);
    }

    @Override
    public ParcelamentoFaturaListaEmailDTO buscarEmailDoCliente(Integer cdCliente) {

        return new ParcelamentoFaturaListaEmailDTO(clienteRepository.buscarEmailDoCliente(cdCliente));
    }

    // INCOMPLETO
    @Override
    public Void enviarEmailComPropostaDoParcelamento(ParcelamentoFaturaEnvioDaSimulacaoDTO envioDaSimulacaoDTO, String token) {

        String loginUsuario = jwtTokenProvider.buscarLogin(token);

        LocalDate hoje = LocalDate.now();

        String dcAnotacao = construirJustificativaParaSimulacao(hoje, envioDaSimulacaoDTO.getValorEntrada(), 
            envioDaSimulacaoDTO.getDataPagamentoDaEntrada(), envioDaSimulacaoDTO.getQuantidadeDeParcelas(), envioDaSimulacaoDTO.getValorPrimeiraParcela(), 
            envioDaSimulacaoDTO.getValordasRestanteDasParcelas());

        executarDossieDoImovelNoParcelamento(dcAnotacao, envioDaSimulacaoDTO.getMatriculaImovel(), 
            imovelRepository.buscarDvDoImovelPelaMatricula(envioDaSimulacaoDTO.getMatriculaImovel()), envioDaSimulacaoDTO.getCdCliente(), 
            clienteRepository.buscarDVDoCliente(envioDaSimulacaoDTO.getCdCliente()), loginUsuario);

        executarDossieClienteNoParcelamento(envioDaSimulacaoDTO.getCdCliente(), dcAnotacao, "PARCELAMENTO", loginUsuario);

        return null;
    }   
    
    @Override
    public void cadastrarParcelamento(ParcelamentoFaturaCadastroDTO cadastroDTO, String token) {

        String loginUsuario = jwtTokenProvider.buscarLogin(token);
        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);
        
        Integer quantidadeDeParcelas = cadastroDTO.getListaParcelas().size();

        FaturamentoParcelamento parcelamentoFatura = executarRotinaCadastrarParcela(cadastroDTO.getCdCliente(), cadastroDTO.getMatriculaImovel(), loginUsuario, 
            cadastroDTO.getPorcentagemDaCorrecaoMonetaria(), quantidadeDeParcelas, cadastroDTO.getDescontoDaNegociacao(), cadastroDTO.getDiaPagamentoDasParcelas(), 
            cadastroDTO.getTotalAgua(), cadastroDTO.getValorDaEntrada(), cadastroDTO.getTotalEsgoto(), cadastroDTO.getTotalOutrosServicos(), cadastroDTO.getTotalJuros(), 
            cadastroDTO.getTotalMulta(), cadastroDTO.getDescontoAutomaticoPrincipal(), cadastroDTO.getDescontoAutomaticoEncargosFinanceiros(), 
            cadastroDTO.getDescontoCreditoCliente(), cadastroDTO.getCorrecaoMonetaria());

        executarCadastroDosLancamentosDasParcelasNoBancoDados(parcelamentoFatura.getCodigoParcelamento(), cadastroDTO.getValorDaEntrada(), 
            cadastroDTO.getDataPagamentoDaEntrada(), cadastroDTO.getListaParcelas());

        executarLancamentoDoParcelamentoNaContabildiade(cadastroDTO.getTotalAgua(), cadastroDTO.getTotalEsgoto(), cadastroDTO.getTotalOutrosServicos(), 
            cadastroDTO.getTotalJuros(), cadastroDTO.getTotalMulta(), cadastroDTO.getCorrecaoMonetaria(), cadastroDTO.getTotalANegociar(),
            cadastroDTO.getCdCliente(), cadastroDTO.getMatriculaImovel(), parcelamentoFatura.getCodigoParcelamento());

        executarBaixaDasReferenciasSelecionada(cadastroDTO.getListadasReferencias(), cadastroDTO.getMatriculaImovel(), cadastroDTO.getCdCliente());

        executarBaixaDosCreditosCasoHouver(cadastroDTO.getDescontoCreditoCliente(), cadastroDTO.getCdCliente(), cadastroDTO.getMatriculaImovel(), loginUsuario,
            parcelamentoFatura.getCodigoParcelamento());

        executarRotinaCobrancaFatura(parcelamentoFatura.getCodigoParcelamento(), cadastroDTO.getMatriculaImovel(), cadastroDTO.getListadasReferencias(), 
            cadastroDTO.getTotalJuros(), cadastroDTO.getTotalMulta(), cadastroDTO.getSomarioDescontosAutomaticosFatura(), 
            cadastroDTO.getSomatorioDescontosAutomaticosJuros(), cadastroDTO.getSomatorioDescontosAutomaticosJurosMora(), cadastroDTO.getCdCliente());

        BigDecimal porcentagemDaEntrada = cadastroDTO.getValorDaEntrada().compareTo(BigDecimal.ZERO) == 0 ? cadastroDTO.getValorDaEntrada() :
            cadastroDTO.getTotalANegociar().divide(cadastroDTO.getValorDaEntrada(), 2, RoundingMode.HALF_EVEN);
            
        BigDecimal valorSomatorioDaFatura = cadastroDTO.getTotalAgua().add(cadastroDTO.getTotalEsgoto().add(cadastroDTO.getTotalOutrosServicos()));
        Integer ultimaRefenciaDaFaturaEmAberto = buscarUltimaReferenciaDeFaturaLocalDatePorReferencia(cadastroDTO.getListadasReferencias());

        IncentivoClienteDetalhe incentivoClienteDetalhado = verificarSeExisteIncentivoDetalhadoCliente(cadastroDTO.isIncentivoJuridico(), porcentagemDaEntrada, 
            quantidadeDeParcelas.shortValue(), cadastroDTO.getListadasReferencias().size(), cadastroDTO.getMatriculaImovel(), valorSomatorioDaFatura, 
            ultimaRefenciaDaFaturaEmAberto);
        
        executarAuditoriaDoParcelamento(idUsuario, incentivoClienteDetalhado, parcelamentoFatura.getCodigoParcelamento(), cadastroDTO.getMatriculaImovel(), 
            cadastroDTO.getCdCliente(), cadastroDTO.getTotalANegociar(), cadastroDTO.getTotalJuros(), cadastroDTO.getTotalMulta(), cadastroDTO.getDescontoAutomaticoPrincipal(),
            cadastroDTO.getDescontoAutomaticoEncargosFinanceiros(), cadastroDTO.getDescontoCreditoCliente(), cadastroDTO.getCorrecaoMonetaria(), 
            cadastroDTO.getListadasReferencias(), cadastroDTO.getDescontoDaNegociacao());
    }

    public IncentivoClienteDetalhe verificarSeExisteIncentivoDetalhadoCliente(boolean incentivoJuridico, BigDecimal porcentagemDaEntrada, 
        Short numeroParcelas, Integer quantidadeDeFaturaEmAberto, Integer matriculaImovel, BigDecimal valorDasFaturas, Integer ultimaRefenciaDaFaturaEmAberto) {

        LocalDateTime diaEHoraDeHoje = LocalDateTime.now();

        Short grupoDeConsumoDoImovel = imovelRepository.buscarGrupoDeConsumoAtravesDaMatriculaIMovel(matriculaImovel);
        Short situacaoAguaDoImovel = imovelRepository.buscarSituacaoAguaDoImovel(matriculaImovel);
        Short situacaoEsgotoDoImovel = imovelRepository.buscarSituacaoEsgotoDoImovel(matriculaImovel);

        if (incentivoJuridico) {
            
            return buscarIncentivoDetalhadoClienteDoTipoJuridco(numeroParcelas, quantidadeDeFaturaEmAberto,valorDasFaturas, ultimaRefenciaDaFaturaEmAberto, 
                diaEHoraDeHoje, porcentagemDaEntrada, grupoDeConsumoDoImovel, situacaoAguaDoImovel, situacaoEsgotoDoImovel);

        } else {

            return buscarIncentivoDetalhadoClienteYipoEspecialEGeral(numeroParcelas, quantidadeDeFaturaEmAberto, valorDasFaturas, 
                ultimaRefenciaDaFaturaEmAberto, diaEHoraDeHoje, porcentagemDaEntrada, grupoDeConsumoDoImovel, situacaoAguaDoImovel, situacaoEsgotoDoImovel);
        }
    }

    /**************************************
     * MÉTODOS PRIVADOS
     */

    private FaturamentoParcelamento executarRotinaCadastrarParcela(Integer cdCliente, Integer matriculaImovel, String loginUsuario, 
        float porcentagemDaCorrecaoMonetaria, Integer quantidadeDeParcelas, BigDecimal descontoDaNegociacao, Short diaPagamentoDasParcelas, 
        BigDecimal totalAgua, BigDecimal valorEntrada,BigDecimal totalEsgoto, BigDecimal totalOutrosServicos, BigDecimal totalJuros, 
        BigDecimal totalMulta, BigDecimal descontoAutomaticoPrincipal, BigDecimal descontoAutomaticoEncargosFinanceiros, 
        BigDecimal descontoCreditoCliente, BigDecimal correcaoMonetaria) {

        FaturamentoParcelamento novoParcelamentoFatura = faturamentoParcelamentoCrudRegra.cadastrarNovaParcela(cdCliente, matriculaImovel, loginUsuario, 
            porcentagemDaCorrecaoMonetaria, quantidadeDeParcelas, descontoDaNegociacao, diaPagamentoDasParcelas, valorEntrada, totalAgua, totalEsgoto, totalOutrosServicos, 
            totalJuros, totalMulta, descontoAutomaticoPrincipal, descontoAutomaticoEncargosFinanceiros, descontoCreditoCliente, correcaoMonetaria);

        faturamentoParcelamentoRepository.save(novoParcelamentoFatura);

        return faturamentoParcelamentoRepository.save(novoParcelamentoFatura);
    }

    private void  executarCadastroDosLancamentosDasParcelasNoBancoDados(Integer cdParcelamento, BigDecimal valorDaEntrada, 
        LocalDate dataPagamentoDaEntrada, List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas) {

        List<FaturamentoParcelamentoParcela> listaDeParcelasDoParcelamento =  
            faturamentoParcelamentoParcelaCrudRegra.cadastrarNovasParcelasDoFaturamentoParcelamentoParcela(cdParcelamento, valorDaEntrada, dataPagamentoDaEntrada, 
                listaParcelas);

        faturamentoParcelamentoParcelaRepository.saveAll(listaDeParcelasDoParcelamento);
    }

    private void executarLancamentoDoParcelamentoNaContabildiade(BigDecimal totalAgua, BigDecimal totalEsgoto, BigDecimal totalOutrosServicos, 
        BigDecimal totalJuros, BigDecimal totalMulta, BigDecimal correcaoMonetaria,BigDecimal totalANegociar, Integer cdCliente, Integer matriculaImovel,
        Integer cdParcelamento) {

        BigDecimal somaAguaComOutrosServicos = totalAgua.add(totalOutrosServicos);
        BigDecimal somaDosJurosMultaECorrecoesLiquidas = totalJuros.add(totalMulta.add(correcaoMonetaria));
        BigDecimal totalDaDividaLiquida = somaAguaComOutrosServicos.add(totalEsgoto.add((somaDosJurosMultaECorrecoesLiquidas)));

        BigDecimal valorProporcionalDaAguaEOutrosServicos = calcularPropocaoDaDividaLiquida(somaAguaComOutrosServicos, totalDaDividaLiquida, totalANegociar);
        BigDecimal valorPropocionalDoEsgoto = calcularPropocaoDaDividaLiquida(totalEsgoto, totalDaDividaLiquida, totalANegociar);
        BigDecimal valorPropocionalDosMurosMultasECorrecoesLiquidas = calcularPropocaoDaDividaLiquida(somaDosJurosMultaECorrecoesLiquidas, totalDaDividaLiquida,
            totalANegociar);

        BigDecimal valorDaComparacao = verificarSeValorFicouExato(valorProporcionalDaAguaEOutrosServicos, valorPropocionalDoEsgoto, valorPropocionalDosMurosMultasECorrecoesLiquidas,
            totalANegociar);

        valorProporcionalDaAguaEOutrosServicos = valorProporcionalDaAguaEOutrosServicos.add(valorDaComparacao);

        executarLancamentoContabilDoParcelamento(cdCliente, matriculaImovel, valorProporcionalDaAguaEOutrosServicos, valorPropocionalDoEsgoto,
            valorPropocionalDosMurosMultasECorrecoesLiquidas, cdParcelamento);
    }

    private BigDecimal verificarSeValorFicouExato(BigDecimal valorProporcionalDaAguaEOutrosServicos, BigDecimal valorPropocionalDoEsgoto, 
        BigDecimal valorPropocionalDosMurosMultasECorrecoesLiquidas, BigDecimal totalDaDividaLiquida) {

        BigDecimal totalSomandoOsValoresCalculados = valorProporcionalDaAguaEOutrosServicos.add(valorPropocionalDoEsgoto
            .add(valorPropocionalDosMurosMultasECorrecoesLiquidas));

        BigDecimal fazendoAComparacao = totalDaDividaLiquida.subtract(totalSomandoOsValoresCalculados);

        if (fazendoAComparacao.compareTo(BigDecimal.ZERO) != 0) {
            return fazendoAComparacao;
        }
        return BigDecimal.ZERO;
    }

    private void executarLancamentoContabilDoParcelamento(Integer cdCliente, Integer matriculaImovel, 
        BigDecimal  valorProporcionalDaAguaEOutrosServicos, BigDecimal valorPropocionalDoEsgoto, BigDecimal valorPropocionalDosMurosMultasECorrecoesLiquidas,
        Integer cdParcelamento) {

        Short eventoParaNovoParcelamento = 40;
        String debitoOuCredito = "D";
        Short servicoParaEncerrarFaturas = 1407;
        Integer numeroDaContaContabilCredito = 112110001;
        short numeroParcela = 0;

        if (valorProporcionalDaAguaEOutrosServicos.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoValorProporcionalDaAguaEOutrosServicos = 311190001;
            
            List<LancamentoContabil> listaDosLancamentosContabeislDoParcelamentoAguaEOutrosServicos = lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra.
                lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra(eventoParaNovoParcelamento, 
                    matriculaImovel, valorProporcionalDaAguaEOutrosServicos, cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, 
                    cdParcelamento, numeroParcela, numeroDaContaContabilDebitoValorProporcionalDaAguaEOutrosServicos, numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaDosLancamentosContabeislDoParcelamentoAguaEOutrosServicos);
        }

        if (valorPropocionalDoEsgoto.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoValorPropocionalDoEsgoto = 312190001;
            
            List<LancamentoContabil> listaDoslancamentosContabeisDoEsgoto = lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra.
                lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra(eventoParaNovoParcelamento, 
                    matriculaImovel, valorPropocionalDoEsgoto, cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, 
                    cdParcelamento, numeroParcela, numeroDaContaContabilDebitoValorPropocionalDoEsgoto, numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaDoslancamentosContabeisDoEsgoto);
        }

        if (valorPropocionalDosMurosMultasECorrecoesLiquidas.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoValorPropocionalDosMurosMultasECorrecoesLiquidas = 321170001;
            
            List<LancamentoContabil> listaDosLancamentosContabeisDosJurosMultasECorrecoesLiquidas = lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra.
                lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra(eventoParaNovoParcelamento, 
                    matriculaImovel, valorPropocionalDosMurosMultasECorrecoesLiquidas, cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, 
                    cdParcelamento, numeroParcela, numeroDaContaContabilDebitoValorPropocionalDosMurosMultasECorrecoesLiquidas, numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaDosLancamentosContabeisDosJurosMultasECorrecoesLiquidas);
        }
    }

    private BigDecimal calcularPropocaoDaDividaLiquida(BigDecimal valorACaclular, BigDecimal totalDaDividaLiquida, BigDecimal totalANegociar) {

        if (valorACaclular.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal porcentagem = valorACaclular.multiply(totalANegociar).setScale(2, RoundingMode.HALF_EVEN);
            return porcentagem.divide(totalDaDividaLiquida, 2, RoundingMode.HALF_EVEN);
        }
        return BigDecimal.ZERO;
    }

    private void executarBaixaDasReferenciasSelecionada(List<LocalDate> listadasReferencias, Integer matriculaImovel, Integer cdCliente) {

        List<Integer> listaReferenciasSelecionadasEmInteger = converterListaLocalDateEmListaInteger(listadasReferencias);

        List<Fatura> listaFaturaSelecionadas =  faturaRepository.findByIdFaturaMatriculaImovelAndCdClienteAndIdFaturaRefFaturaIn(matriculaImovel, cdCliente,
            listaReferenciasSelecionadasEmInteger);

        executarBaixaDasFaturas(listaFaturaSelecionadas, matriculaImovel, cdCliente);
    }

    private void executarBaixaDasFaturas(List<Fatura> listaFaturaSelecionadas, Integer matriculaImovel, Integer cdCliente) {

        BigDecimal valorAguaEOutrosServicos = BigDecimal.ZERO;
        BigDecimal valorEsgoto =  BigDecimal.ZERO;
        BigDecimal valorImpostos = BigDecimal.ZERO;

        List<Short> listaDeServicosDeEsgoto = servicoRepository.listaDosServicosDeEsgoto();
        List<Short> listaServicosEncargosFinanceirosJuros = Arrays.asList((short)1405, (short)1421);

        LocalDate hoje = LocalDate.now();
        Short numeroTipoBaixaPorParcelamento = 4;
        for (Fatura fatura : listaFaturaSelecionadas) {

            fatura.setDiaBaixa((short) hoje.getDayOfMonth());
            fatura.setReferenciaBaixa(ConverterData.localDateEmIntegerReferenciaFormatoDB(hoje).shortValue());
            fatura.setReferenciaBaixaContabil(ConverterData.localDateEmIntegerReferenciaFormatoDB(hoje));
            fatura.setTpBaixa(numeroTipoBaixaPorParcelamento);

            List<LancamentosDaFatura> listaDelancamentosDaFatura = lancamentosDaFaturaRepository.findByIdFatura(fatura.getId());

            BigDecimal valorAguaDoLancamento = BigDecimal.ZERO;
            BigDecimal valorEsgotoLancamento =  BigDecimal.ZERO;
            BigDecimal valorImpostoslancamento = BigDecimal.ZERO;
    
            for (LancamentosDaFatura lancamentos : listaDelancamentosDaFatura) {
    
                if(listaDeServicosDeEsgoto.contains(lancamentos.getCdServico())){
                    valorEsgoto = valorEsgoto.add(lancamentos.getValorServico());
                    valorEsgotoLancamento = valorEsgotoLancamento.add(lancamentos.getValorServico());
                }
    
                if(listaServicosEncargosFinanceirosJuros.contains(lancamentos.getCdServico())){
                    valorImpostos = valorImpostos.add(lancamentos.getValorServico());
                    valorImpostoslancamento = valorImpostoslancamento.add(lancamentos.getValorServico());
                }
            }
    
            BigDecimal somaEsgotoEEncargosFincanceiros = valorEsgotoLancamento.add(valorImpostoslancamento);
            valorAguaDoLancamento = fatura.getValorFatura().subtract(somaEsgotoEEncargosFincanceiros);
            valorAguaEOutrosServicos = valorAguaEOutrosServicos.add(valorAguaDoLancamento);  
        }
        lancarValoresParaEncerarOTotalizadorDasFaturas(valorAguaEOutrosServicos, valorEsgoto, valorImpostos, matriculaImovel, cdCliente);
    }


    private void lancarValoresParaEncerarOTotalizadorDasFaturas(BigDecimal valorAguaEOutrosServicos, BigDecimal valorEsgoto, BigDecimal valorImpostos,
        Integer matriculaImovel, Integer cdCliente) {

        Short eventoParaEncerrarAsFaturas = 40;
        String debitoOuCredito = "D";
        Short servicoParaEncerrarFaturas = 1407;
        Integer refFatura = 0;
        short origemFatura = 0;
        Integer numeroDaContaContabilCredito = 112110001;

        if (valorAguaEOutrosServicos.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoAgua = 311190001;
            
            List<LancamentoContabil> listaBaixaDasFaturasSelecionadasDeAguaEOutrosServicos = lancamentoContabilParaCrudFaturaRegra.
                encerrarFaturaPorNegociacaoDoParcelamentoEscolhendoContasContabis(eventoParaEncerrarAsFaturas, matriculaImovel, valorAguaEOutrosServicos, 
                    cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, refFatura, origemFatura, numeroDaContaContabilDebitoAgua, 
                    numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaBaixaDasFaturasSelecionadasDeAguaEOutrosServicos);
        }

        if (valorEsgoto.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoEsgoto = 312190001;
            
            List<LancamentoContabil> listaBaixaDasFaturasSelecionadasDoEsgoto = lancamentoContabilParaCrudFaturaRegra.
                encerrarFaturaPorNegociacaoDoParcelamentoEscolhendoContasContabis(eventoParaEncerrarAsFaturas, matriculaImovel, valorEsgoto, 
                    cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, refFatura, origemFatura, numeroDaContaContabilDebitoEsgoto, 
                    numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaBaixaDasFaturasSelecionadasDoEsgoto);
        }

        if (valorImpostos.compareTo(BigDecimal.ZERO) != 0) {

            Integer numeroDaContaContabilDebitoImpostos = 321170001;
            
            List<LancamentoContabil> listaBaixaDasFaturasSelecionadasJurosMultasEEncargosFinanceiros = lancamentoContabilParaCrudFaturaRegra.
                encerrarFaturaPorNegociacaoDoParcelamentoEscolhendoContasContabis(eventoParaEncerrarAsFaturas,  matriculaImovel, valorImpostos, 
                    cdCliente, servicoParaEncerrarFaturas, debitoOuCredito, refFatura, origemFatura, numeroDaContaContabilDebitoImpostos, 
                    numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaBaixaDasFaturasSelecionadasJurosMultasEEncargosFinanceiros);
        }
    }

    private void executarRotinaCobrancaFatura(Integer codigoParcelamento, Integer matriculaImovel, List<LocalDate> listadasReferencias, BigDecimal totalJuros, 
        BigDecimal totalMulta,  BigDecimal somarioDescontosAutomaticosFatura, BigDecimal somatorioDescontosAutomaticosJuros,
        BigDecimal somatorioDescontosAutomaticosJurosMora, Integer cdCliente) {

        List<Integer> listaReferenciasSelecionadasEmInteger = converterListaLocalDateEmListaInteger(listadasReferencias);

        List<DetalheDaFaturaParcelada> listaDetalheDaFaturaParcelada = detalheDaFaturaParceladaCrudRegra.cadastrarDetalheDaFaturaParcelada(codigoParcelamento, 
            matriculaImovel, cdCliente, listaReferenciasSelecionadasEmInteger, totalJuros, totalMulta, somarioDescontosAutomaticosFatura, 
            somatorioDescontosAutomaticosJuros, somatorioDescontosAutomaticosJurosMora);

        detalheDaFaturaParceladaRepository.saveAll(listaDetalheDaFaturaParcelada);
    }

    private void executarBaixaDosCreditosCasoHouver(BigDecimal descontoCreditoCliente, Integer cdCliente, Integer matriculaImovel, String loginUsuario, 
        Integer cdParcelamento) {

        BigDecimal valorTotalCreditoASerBaixado = BigDecimal.ZERO;
        
        if (descontoCreditoCliente.compareTo(BigDecimal.ZERO) != 0) {
            Integer dataEncerramentoEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now() );

            Integer referenciaEncerramento = 0;
            List<Credito> findByIdCreditoMatriculaAndCdCliente = creditoRepository.findByIdCreditoMatriculaAndCdClienteAndRefEncerramento(matriculaImovel, cdCliente,
                referenciaEncerramento).get();

            for (Credito credito : findByIdCreditoMatriculaAndCdCliente) {

                valorTotalCreditoASerBaixado = valorTotalCreditoASerBaixado.add(credito.getValorSaldo());

                String campoJustificativa = executarJustificativaParaEncerrarCredito(cdParcelamento, cdCliente, matriculaImovel);
                credito.setDcAnotacao(campoJustificativa);
                credito.setRefEncerramento(dataEncerramentoEmInteger);
                credito.setValorSaldo(BigDecimal.valueOf(0));
                credito.setUsuario(loginUsuario);
                creditoRepository.save(credito);
            }

            Short eventoParaEncerrarCredito = 41;
            String debitoOuCredito = "D";
            Short servicoParaEncerrarFaturas = 1407;
            Integer numeroDaContaContabilCredito = 112110001;
            Integer numeroDaContaContabilDebito = null;
            short cdCredito = 0;

            List<LancamentoContabil> listaDalancamentoContabeisDasBaixarCredito = lancamentoContabilParaCrudCreditoRegra.
                lancamentoContabilParaCreditoEscolhendoContasContabis(eventoParaEncerrarCredito, matriculaImovel,
                    valorTotalCreditoASerBaixado, cdCliente, cdCredito, servicoParaEncerrarFaturas, debitoOuCredito, numeroDaContaContabilDebito, 
                    numeroDaContaContabilCredito);

            lancamentoContabilRepository.saveAll(listaDalancamentoContabeisDasBaixarCredito);
        }
    }

    private String executarJustificativaParaEncerrarCredito(Integer cdParcelamento, Integer cdCliente, Integer matriculaImovel) {

        StringBuilder dcAnotacao = new StringBuilder("Encerrando Crédito para creditar na Negociação do parcelamento com ID ");
        dcAnotacao.append(cdParcelamento);
        dcAnotacao.append(" do Cliente ID ");
        dcAnotacao.append(cdCliente);
        dcAnotacao.append(" e da matricula do Imóvel de ID ");
        dcAnotacao.append(matriculaImovel);

        return dcAnotacao.toString();
    }

    private void executarAuditoriaDoParcelamento(Long idUsuario, IncentivoClienteDetalhe incentivoDetalheCliente, Integer codigoParcelamento, Integer matriculaImovel, Integer cdCliente, BigDecimal 
        totalANegociar, BigDecimal totalJuros, BigDecimal totalMulta, BigDecimal descontoAutomaticoPrincipal, BigDecimal descontoAutomaticoEncargosFinanceiros, 
        BigDecimal descontoCreditoCliente, BigDecimal correcaoMonetaria, List<LocalDate> listadasReferencias, BigDecimal descontoDaNegociacao) {

        List<Integer> listaReferenciasEmInteger = converterListaLocalDateEmListaInteger(listadasReferencias);

        List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> listaDeRefrenciaEOrigemDaFatura = faturaRepository.buscarAsReferenciasDaFaturaEAOrigemDaFatura(matriculaImovel, 
            cdCliente, listaReferenciasEmInteger);

        var dtoDaAuditoria = instanciarDtoParaAuditoria(idUsuario, incentivoDetalheCliente, codigoParcelamento, matriculaImovel, cdCliente, totalANegociar, totalJuros, 
            totalMulta, descontoAutomaticoPrincipal, descontoAutomaticoEncargosFinanceiros, descontoCreditoCliente, correcaoMonetaria, listaDeRefrenciaEOrigemDaFatura,
            descontoDaNegociacao);

        String jsonDtoAuditoria = ConvertObjectToJsonCesan.execute(dtoDaAuditoria);
            
        auditoriaService.gerarAuditoriaCadastramento(cdCliente.longValue(), jsonDtoAuditoria, 24L, "cliente", idUsuario);
    }

    private ParcelamentoDaFaturaAuditoriaDTO instanciarDtoParaAuditoria(Long idUsuario, IncentivoClienteDetalhe incentivoDetalheCliente, Integer codigoParcelamento, 
        Integer matriculaImovel, Integer cdCliente, BigDecimal totalANegociar, BigDecimal totalJuros, BigDecimal totalMulta, BigDecimal descontoAutomaticoPrincipal, 
        BigDecimal descontoAutomaticoEncargosFinanceiros, BigDecimal descontoCreditoCliente, BigDecimal correcaoMonetaria,  
        List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> listaDeRefrenciaEOrigemDaFatura, BigDecimal descontoDaNegociacao) {

        var dtoDaAuditoria = new ParcelamentoDaFaturaAuditoriaDTO();
        dtoDaAuditoria.setIdUsuario(idUsuario);
        if (incentivoDetalheCliente != null) dtoDaAuditoria.setIdIncentivoDetalheCliente(incentivoDetalheCliente.getId());
        dtoDaAuditoria.setCdParcelamento(codigoParcelamento);
        dtoDaAuditoria.setDataHoraProcessamento(LocalDateTime.now());
        dtoDaAuditoria.setMatriculaImovel(matriculaImovel);
        dtoDaAuditoria.setCdCliente(cdCliente);
        dtoDaAuditoria.setTotalANegociar(totalANegociar);
        dtoDaAuditoria.setTotalJuros(totalJuros);
        dtoDaAuditoria.setTotalMulta(totalMulta);
        dtoDaAuditoria.setDescontoAutomaticoPrincipal(descontoAutomaticoPrincipal);
        dtoDaAuditoria.setDescontoAutomaticoEncargosFinanceiros(descontoAutomaticoEncargosFinanceiros);
        dtoDaAuditoria.setDescontoCreditoCliente(descontoCreditoCliente);
        dtoDaAuditoria.setDescontoDaNegociacao(descontoDaNegociacao);
        dtoDaAuditoria.setCorrecaoMonetaria(correcaoMonetaria);
        dtoDaAuditoria.setListadasReferencias(listaDeRefrenciaEOrigemDaFatura);

        Integer refEncerramento = ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now());
        List<Long> listaDeIdDeCreditos = creditoRepository.buscarListaDeIdDaMattriculaEClienteComSaldo(matriculaImovel, cdCliente, refEncerramento);

        if (!listaDeIdDeCreditos.isEmpty()) dtoDaAuditoria.setIdCredito(listaDeIdDeCreditos);

        return dtoDaAuditoria;
    }

    // Inicio dos métodos de calculos
    private ParcelamentoFaturaSimulacaoDTO converterDadosParaRealizacaoCalculo(ParcelamentoFaturaSelecionarFaturaDTO faturasSelecionadasDTO) {

        List<LocalDate> listaReferenciaDoValorAvista = buscarAListaRTeferenciaCAsoSejaPagametoAVista(faturasSelecionadasDTO.getRefFaturaSelecionadas(),
            faturasSelecionadasDTO.getMatriculaImovel(), faturasSelecionadasDTO.getCdCliente());

        if(faturasSelecionadasDTO.getRefFaturaSelecionadas() == null) faturasSelecionadasDTO.setRefFaturaSelecionadas(listaReferenciaDoValorAvista);

        List<Integer> listaRefFaturaEmInteger = converterListaLocalDateEmListaInteger(faturasSelecionadasDTO.getRefFaturaSelecionadas());

        List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaFaturaEmAberto = faturaRepository.
            listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovelSelecionados(faturasSelecionadasDTO.getCdCliente(), faturasSelecionadasDTO.getMatriculaImovel(),
            listaRefFaturaEmInteger) ;

        ParcelamentoFaturaContasEmAbertoDoClienteDTO contasEmAbertoDoCliente = converterListaDasFaturasEmUmaListaDetalhada(listaFaturaEmAberto, 
            faturasSelecionadasDTO.isIncentivoJuridico(), faturasSelecionadasDTO.getValorEntrada(), faturasSelecionadasDTO.getMatriculaImovel(),
            faturasSelecionadasDTO.getNumeroDeParcelas());

        ParcelamentoFaturaSimulacaoDTO simulacaoDTO = parcelamentoFaturaConverterDTOMapper.toDto(contasEmAbertoDoCliente);

        simulacaoDTO.setCdCliente(faturasSelecionadasDTO.getCdCliente());
        simulacaoDTO.setMatriculaImovel(faturasSelecionadasDTO.getMatriculaImovel());
        simulacaoDTO.setIncentivoJuridico(faturasSelecionadasDTO.isIncentivoJuridico());
        simulacaoDTO.setCorrecaoMonetario(faturasSelecionadasDTO.isCorrecaoMonetario());
        simulacaoDTO.setAutorizacaoDescontarCredito(faturasSelecionadasDTO.isAutorizacaoDescontarCredito());
        simulacaoDTO.setDescontoDaNegociacao(faturasSelecionadasDTO.getDescontoDaNegociacao());
        simulacaoDTO.setValorEntrada(faturasSelecionadasDTO.getValorEntrada());
        
        return simulacaoDTO;
    }

    private ParcelamentoFaturaContasEmAbertoDoClienteDTO converterListaDasFaturasEmUmaListaDetalhada(
        List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaFaturaEmAberto, boolean incentivoJuridico, BigDecimal valorEntrada, 
        Integer matriculaImovel, Short numeroParcelas) {

        List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturasEmAbertoConvertido = parcelamentoFaturaFaturasMapper.toDto(listaFaturaEmAberto);

        Integer quantidadeDeFaturaEmAberto = listaFaturaEmAberto.size();

        calculoDoQuadroJurosEIncentivo(listaFaturasEmAbertoConvertido, incentivoJuridico, numeroParcelas, quantidadeDeFaturaEmAberto, matriculaImovel, 
            buscarUltimaReferenciaDeFatura(listaFaturaEmAberto), valorEntrada);
        
        return calculandoSomatorioDosValores(listaFaturasEmAbertoConvertido);
    }

    private void calculoDoQuadroJurosEIncentivo(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturaEmAberto, boolean incentivoJuridico, Short numeroParcelas, 
        Integer quantidadeDeFaturaEmAberto, Integer matriculaImovel, Integer ultimaRefenciaDaFaturaEmAberto, BigDecimal valorEntrada) {

        calularJurosEMultaDasFaturas(listaFaturaEmAberto);

        calularValoresDeIncentivoDetalhadoCliente(listaFaturaEmAberto, incentivoJuridico, numeroParcelas,
                quantidadeDeFaturaEmAberto, matriculaImovel, ultimaRefenciaDaFaturaEmAberto, valorEntrada);
    }

    private void calularJurosEMultaDasFaturas(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturaEmAberto) {
        for (ParcelamentoFaturaListaFaturaEmAbertoDTO parcelamentoFatura : listaFaturaEmAberto) {

            if (parcelamentoFatura.getValorFatura().compareTo(BigDecimal.ZERO) != 0 ){
                calculoDosJurosCompostosSimplesEMora(parcelamentoFatura);
            }
        }
    }

    private void calularValoresDeIncentivoDetalhadoCliente(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturaEmAberto, boolean incentivoJuridico, 
        Short numeroParcelas, Integer quantidadeDeFaturaEmAberto, Integer matriculaImovel, Integer ultimaRefenciaDaFaturaEmAberto,
        BigDecimal valorEntrada) {

        BigDecimal valorDasFaturas = listaFaturaEmAberto.stream().map(ParcelamentoFaturaListaFaturaEmAbertoDTO::getValorFatura)
            .filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal porcentagemDaEntrada = valorEntrada == null ? BigDecimal.ZERO : valorEntrada.divide(valorDasFaturas, 2, RoundingMode.HALF_EVEN);

        //Quando executar primeira pesquisa
        numeroParcelas = numeroParcelas == null || numeroParcelas == 0 ? 1 : numeroParcelas;

        IncentivoClienteDetalhe incentivoClienteDetalhado = verificarSeExisteIncentivoDetalhadoCliente(incentivoJuridico, porcentagemDaEntrada, 
                numeroParcelas, quantidadeDeFaturaEmAberto, matriculaImovel, valorDasFaturas, ultimaRefenciaDaFaturaEmAberto);

        for (ParcelamentoFaturaListaFaturaEmAbertoDTO parcelamentoFatura : listaFaturaEmAberto) {
            calcularDescontosAutomaticos(parcelamentoFatura, incentivoClienteDetalhado);
        }
    }

    private void calculoDosJurosCompostosSimplesEMora(ParcelamentoFaturaListaFaturaEmAbertoDTO parcelamentoFatura) {

        BigDecimal jurosMora = parcelamentoFatura.getValorFatura().multiply(BigDecimal.valueOf(0.02)).setScale(2, RoundingMode.HALF_UP);

        LocalDate diaHoje = LocalDate.now();
        int dia = diaHoje.getDayOfMonth();

        Integer mesesEmTrasado = calcularMesesEmAtraso(diaHoje, parcelamentoFatura.getRefFatura() );

        //Valores Juros compostos por Meses
        Double valorBruto = parcelamentoFatura.getValorFatura().doubleValue() * Math.pow((1 + 0.01), mesesEmTrasado);
        BigDecimal valorBrutoDaDivida = BigDecimal.valueOf(valorBruto).setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorJuros = valorBrutoDaDivida.subtract(parcelamentoFatura.getValorFatura());

        //Pegar valor diario - Juros compostos
        BigDecimal valorDoJurosDoMesCheio = BigDecimal.valueOf(parcelamentoFatura.getValorFatura().doubleValue() * Math.pow((1 + 0.01), 1));
        Double propocaoDoMesPeloDias = Double.valueOf(dia) / 30;
        BigDecimal valorJurosPropocional = valorDoJurosDoMesCheio.multiply(BigDecimal.valueOf(propocaoDoMesPeloDias)).setScale(2, RoundingMode.HALF_UP);
            
        //Soma dos valores
        BigDecimal jurosCompostosMesesMaisJurosSimplesDias = valorJuros.add(valorJurosPropocional);

        parcelamentoFatura.setJurosMora(jurosMora);
        parcelamentoFatura.setValorJuros(jurosCompostosMesesMaisJurosSimplesDias);
    }

    // metodos para calcular valores dos descontros 
    private void calcularDescontosAutomaticos(ParcelamentoFaturaListaFaturaEmAbertoDTO parcelamentoFatura, IncentivoClienteDetalhe incentivoClienteDetalhado) {

        BigDecimal valorDescontoSobreValorFatura = BigDecimal.ZERO;
        BigDecimal valorDescontoSobreValorJuros = BigDecimal.ZERO;
        BigDecimal valorDescontoSobreMulta = BigDecimal.ZERO;

        if (incentivoClienteDetalhado != null) {

            if(incentivoClienteDetalhado.getDescontoValorPrincipal() != 0 ){
                valorDescontoSobreValorFatura = calcularPorcentagemDoValorPassadoPorParametro(parcelamentoFatura.getDescontoFatura(), 
                    incentivoClienteDetalhado.getDescontoValorPrincipal());
            } 
    
            if(incentivoClienteDetalhado.getDescontoJuros() != 0){
                valorDescontoSobreValorJuros = calcularPorcentagemDoValorPassadoPorParametro(parcelamentoFatura.getDescontoJuros(), 
                    incentivoClienteDetalhado.getDescontoJuros());
            }
               
            if(incentivoClienteDetalhado.getDescontoMultas() != 0){
                valorDescontoSobreMulta = calcularPorcentagemDoValorPassadoPorParametro(parcelamentoFatura.getDescontoJurosMora(), 
                    incentivoClienteDetalhado.getDescontoMultas());
            }
    
            parcelamentoFatura.setDescontoFatura(valorDescontoSobreValorFatura);
            parcelamentoFatura.setDescontoJuros(valorDescontoSobreValorJuros);
            parcelamentoFatura.setDescontoJurosMora(valorDescontoSobreMulta);
        }  
    }

    private IncentivoClienteDetalhe buscarIncentivoClienteDetalhado(BigDecimal porcentagemDaEntrada, Optional<Short> idParamentoIncentivo, Short numeroParcelas, 
        Integer quantidadeDeFaturaEmAberto, BigDecimal valorTotalDasFaturaEmAberto, Short grupoDeConsumoDoImovel, Short situacaoAguaDoImovel, Short situacaoEsgotoDoImovel, 
        Integer ultimaRefenciaDaFaturaEmAberto) {

        Short numeroDiasDaUltimaFatura = buscarQuantosDiasAUltimaReferenciasEstaEmAtrado(ultimaRefenciaDaFaturaEmAberto);

        if (idParamentoIncentivo.isPresent()) {

            Optional<IncentivoClienteDetalhe> idIncentivoDetalhadoCliente = incentivoClienteDetalheRepository.buscarIncentivoClienteDetalhePorIdIncentivoEntidade(
                idParamentoIncentivo.get(), porcentagemDaEntrada, numeroParcelas, quantidadeDeFaturaEmAberto, valorTotalDasFaturaEmAberto, numeroDiasDaUltimaFatura);
            
            if (idIncentivoDetalhadoCliente.isPresent()) {
               
                boolean existeCadastroParaEsseIncentidoDetalhadoCliente = incentivoClienteDetalheRepository.verificarSeIdIncentivoDetalhadoClienteEstaDeAcordoComRegraDeNegocio(
                    situacaoEsgotoDoImovel, situacaoAguaDoImovel, grupoDeConsumoDoImovel, idIncentivoDetalhadoCliente.get());

                if (existeCadastroParaEsseIncentidoDetalhadoCliente) {
                    return idIncentivoDetalhadoCliente.get();
                }
            }            
        }
        return null;            
    }

    private BigDecimal calcularPorcentagemDoValorPassadoPorParametro(BigDecimal valor, int porcentagemDaEntrada) {

        return valor.multiply(BigDecimal.valueOf(porcentagemDaEntrada / 100)).setScale(2, RoundingMode.HALF_EVEN);
    }
    // fim dos metodos para calcular valores dos descontros

    private ParcelamentoFaturaContasEmAbertoDoClienteDTO calculandoSomatorioDosValores(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturasEmAbertoConvertido) {

        BigDecimal somatorioValorFatura = BigDecimal.ZERO;
        BigDecimal somatorioValorJuros = BigDecimal.ZERO;
        BigDecimal somatorioJurosMora = BigDecimal.ZERO;

        BigDecimal somarioDescontoFatura = BigDecimal.ZERO;
        BigDecimal somatorioDescontoJuros = BigDecimal.ZERO;
        BigDecimal somatorioDescontoJurosMora = BigDecimal.ZERO;

        for (ParcelamentoFaturaListaFaturaEmAbertoDTO parcelamentoFaturaEmAbertoDTO : listaFaturasEmAbertoConvertido) {

            if (parcelamentoFaturaEmAbertoDTO.getValorFatura() != null && parcelamentoFaturaEmAbertoDTO.getValorFatura().compareTo(BigDecimal.ZERO) != 0 ){
                somatorioValorFatura = somatorioValorFatura.add(parcelamentoFaturaEmAbertoDTO.getValorFatura());
            }
            if (parcelamentoFaturaEmAbertoDTO.getValorJuros() != null && parcelamentoFaturaEmAbertoDTO.getValorJuros().compareTo(BigDecimal.ZERO) != 0){
                somatorioValorJuros = somatorioValorJuros.add(parcelamentoFaturaEmAbertoDTO.getValorJuros());
            }
            if (parcelamentoFaturaEmAbertoDTO.getJurosMora() != null && parcelamentoFaturaEmAbertoDTO.getJurosMora().compareTo(BigDecimal.ZERO) != 0){
                somatorioJurosMora = somatorioJurosMora.add(parcelamentoFaturaEmAbertoDTO.getJurosMora());
            }

            // ---------------------------------------------------------------------------------------------------------------------------------------------
            if (parcelamentoFaturaEmAbertoDTO.getDescontoFatura() != null && parcelamentoFaturaEmAbertoDTO.getDescontoFatura().compareTo(BigDecimal.ZERO) != 0){
                somarioDescontoFatura = somarioDescontoFatura.add(parcelamentoFaturaEmAbertoDTO.getDescontoFatura());
            }
            if (parcelamentoFaturaEmAbertoDTO.getDescontoJuros() != null && parcelamentoFaturaEmAbertoDTO.getDescontoJuros().compareTo(BigDecimal.ZERO) != 0){
                somatorioDescontoJuros = somatorioDescontoJuros.add(parcelamentoFaturaEmAbertoDTO.getDescontoJuros());
            }
            if (parcelamentoFaturaEmAbertoDTO.getDescontoJurosMora() != null && parcelamentoFaturaEmAbertoDTO.getDescontoJurosMora().compareTo(BigDecimal.ZERO) != 0){
                somatorioDescontoJurosMora = somatorioDescontoJurosMora.add(parcelamentoFaturaEmAbertoDTO.getDescontoJurosMora());
            }
        }

        return instanciarEntidadeParaRetorno(listaFaturasEmAbertoConvertido, somatorioValorFatura, somatorioValorJuros, somatorioJurosMora,
            somarioDescontoFatura, somatorioDescontoJuros, somatorioDescontoJurosMora );
    }

    private ParcelamentoFaturaContasEmAbertoDoClienteDTO instanciarEntidadeParaRetorno(List<ParcelamentoFaturaListaFaturaEmAbertoDTO> listaFaturasEmAbertoConvertido, 
        BigDecimal somatorioValorFatura, BigDecimal somatorioValorJuros, BigDecimal somatorioJurosMora, BigDecimal somarioDescontoFatura, 
        BigDecimal somatorioDescontoJuros, BigDecimal somatorioDescontoJurosMora ) {

        ParcelamentoFaturaContasEmAbertoDoClienteDTO contasEmAbertoDoCliente = new ParcelamentoFaturaContasEmAbertoDoClienteDTO();

        contasEmAbertoDoCliente.setSomatorioValorFatura(somatorioValorFatura);
        contasEmAbertoDoCliente.setSomatorioValorJuros(somatorioValorJuros);
        contasEmAbertoDoCliente.setSomatorioJurosMora(somatorioJurosMora);

        contasEmAbertoDoCliente.setSomarioDescontosAutomaticosFatura(somarioDescontoFatura);
        contasEmAbertoDoCliente.setSomatorioDescontosAutomaticosJuros(somatorioDescontoJuros);
        contasEmAbertoDoCliente.setSomatorioDescontosAutomaticosJurosMora(somatorioDescontoJurosMora);

        contasEmAbertoDoCliente.setListaDeFaturaEmAberto(listaFaturasEmAbertoConvertido);

        return contasEmAbertoDoCliente;
    }

    private void calcularValoresPropocionaisDoPrincipal(BigDecimal somatorioValorFatura, Integer cliente, Integer matriculaImovel, 
        ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao, List<LocalDate> listaReferenciasSelecionda) {

        List<Integer> listaRefrenciaEmInteger = converterListaLocalDateEmListaInteger(listaReferenciasSelecionda);

        ParcelamentoFaturaListaFaturaEmAbertoParaSimulacaoParcelamentoProjectionDTO proporcaoDaDivida = faturaRepository.
            buscaParaRetornarPropocaoAguaEsgotoEOoutrosDaFaturaEmAberto(cliente, matriculaImovel, listaRefrenciaEmInteger);

        BigDecimal valorPropocionalAgua = somatorioValorFatura.multiply(BigDecimal.valueOf(proporcaoDaDivida.getValorAgua())).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorPropocionalEsgoto = somatorioValorFatura.multiply(BigDecimal.valueOf(proporcaoDaDivida.getValorEsgoto())).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal valorPropocionalOutros = somatorioValorFatura.multiply(BigDecimal.valueOf(proporcaoDaDivida.getOutrosServicos())).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal valorDaDiferenca = verificarSeValorEstaExatoComFatura(valorPropocionalAgua, valorPropocionalEsgoto, valorPropocionalOutros, somatorioValorFatura);

        if (valorPropocionalAgua.compareTo(BigDecimal.ZERO) != 0){
            valorPropocionalAgua = valorPropocionalAgua.subtract(valorDaDiferenca);
        }else {
            valorPropocionalEsgoto = valorPropocionalEsgoto.subtract(valorDaDiferenca);
        }

        quadroDaSimulacao.setTotalAgua(valorPropocionalAgua);
        quadroDaSimulacao.setTotalEsgoto(valorPropocionalEsgoto);
        quadroDaSimulacao.setTotalOutrosServicos(valorPropocionalOutros);
    }

    private BigDecimal verificarSeValorEstaExatoComFatura(BigDecimal valorPropocionalAgua, BigDecimal valorPropocionalEsgoto,
        BigDecimal valorPropocionalOutros, BigDecimal somatorioValorFatura) {

        BigDecimal somaDasPropocoes = valorPropocionalAgua.add(valorPropocionalEsgoto.add(valorPropocionalOutros));

        if (somaDasPropocoes.compareTo(somatorioValorFatura) > 0) {
            return somaDasPropocoes.subtract(somatorioValorFatura);
        }
        return BigDecimal.ZERO;
    }

    private void calcularSomaDosJuros(ParcelamentoFaturaSimulacaoDTO simulacaoDTO, ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao) {
        quadroDaSimulacao.setTotalJuros(simulacaoDTO.getSomatorioValorJuros());
        quadroDaSimulacao.setTotalMulta(simulacaoDTO.getSomatorioJurosMora());
    }      

    private void somatorioDosValoresASeremDescontados(BigDecimal somarioDescontosAutomaticoFatura, BigDecimal somatorioDescontoAutomaticoJuros, 
        BigDecimal somatorioDescontoAutomaticoJurosMora, BigDecimal descontoDaNegociacao, ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao) {

        somarioDescontosAutomaticoFatura = somarioDescontosAutomaticoFatura == null ? BigDecimal.ZERO : somarioDescontosAutomaticoFatura;
        somatorioDescontoAutomaticoJuros = somatorioDescontoAutomaticoJuros == null ? BigDecimal.ZERO : somatorioDescontoAutomaticoJuros;
        somatorioDescontoAutomaticoJurosMora = somatorioDescontoAutomaticoJurosMora == null ? BigDecimal.ZERO : somatorioDescontoAutomaticoJurosMora;

        descontoDaNegociacao = descontoDaNegociacao == null ? BigDecimal.ZERO : descontoDaNegociacao;

        quadroDaSimulacao.setDescontoAutomaticoPrincipal(somarioDescontosAutomaticoFatura);
        quadroDaSimulacao.setDescontoAutomaticoEncargosFinanceiros(somatorioDescontoAutomaticoJuros.add(somatorioDescontoAutomaticoJurosMora));

        quadroDaSimulacao.setDescontoDaNegociacao(descontoDaNegociacao);
    }

    private void calcularValorFinalASerDecontato(ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao) {

        BigDecimal somaDosDescontosAutomativosEManuais = quadroDaSimulacao.getDescontoAutomaticoPrincipal().add(quadroDaSimulacao.getDescontoAutomaticoEncargosFinanceiros()
            .add(quadroDaSimulacao.getDescontoDaNegociacao()));

        BigDecimal somaDosDescontosECredito = somaDosDescontosAutomativosEManuais.add(quadroDaSimulacao.getDescontoCreditoCliente());

        quadroDaSimulacao.setSubTotalDescontando(quadroDaSimulacao.getTotalDevido().subtract(somaDosDescontosECredito));
    }

    private void calculoDoDescontoCreditoDoCliente(boolean autorizacaoDescontarCredito, ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao,
        ParcelamentoFaturaSimulacaoDTO simulacaoDTO) {

        BigDecimal valorCredito = BigDecimal.ZERO;

        if (autorizacaoDescontarCredito) {

            Integer referenciaEncerramento = 0;
            Optional<List<Credito>> listaCredito = creditoRepository.findByIdCreditoMatriculaAndCdClienteAndRefEncerramento(simulacaoDTO.getMatriculaImovel(),
                 simulacaoDTO.getCdCliente(), referenciaEncerramento);

            if (listaCredito.isPresent()) {
                
                BigDecimal somaDosvalorCredito = listaCredito.get().stream().map(Credito::getValorSaldo).reduce(BigDecimal.ZERO, BigDecimal::add);
                valorCredito = verificarSeValorCreditoEMaiorOuIgualValorDaVivida(somaDosvalorCredito, quadroDaSimulacao);
            }
        }

        quadroDaSimulacao.setDescontoCreditoCliente(valorCredito);
    }

    private BigDecimal verificarSeValorCreditoEMaiorOuIgualValorDaVivida(BigDecimal somaDosvalorCredito, ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao) {

        BigDecimal somaDosDescontosAutomaticosEManuais = quadroDaSimulacao.getDescontoAutomaticoPrincipal().add(quadroDaSimulacao.getDescontoAutomaticoEncargosFinanceiros())
            .add(quadroDaSimulacao.getDescontoDaNegociacao());

        BigDecimal valorASerPaggoSemContarOCredito = quadroDaSimulacao.getTotalDevido().subtract(somaDosDescontosAutomaticosEManuais);

        if (somaDosvalorCredito.compareTo(valorASerPaggoSemContarOCredito) >= 0) {
            return valorASerPaggoSemContarOCredito;
        }

        return somaDosvalorCredito;
    }

    private ParcelamentoFaturaSimulacaoRespostaDTO execultarCalculoDaNegociacao(ParcelamentoFaturaSimulacaoDTO simulacaoDTO, Integer quantidadeDeFaturaEmAberto, 
        Integer ultimaRefenciaDaFaturaEmAberto, List<LocalDate> listaReferenciasSelecionda) {

        ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao = new ParcelamentoFaturaSimulacaoRespostaDTO();

        calcularValoresPropocionaisDoPrincipal(simulacaoDTO.getSomatorioValorFatura(), simulacaoDTO.getCdCliente(), simulacaoDTO.getMatriculaImovel(), quadroDaSimulacao, 
            listaReferenciasSelecionda);

        quadroDaSimulacao.setSubTotal(quadroDaSimulacao.getTotalAgua().add(quadroDaSimulacao.getTotalEsgoto().add(quadroDaSimulacao.getTotalOutrosServicos())));

        calcularSomaDosJuros(simulacaoDTO, quadroDaSimulacao);

        quadroDaSimulacao.setTotalDevido(quadroDaSimulacao.getSubTotal().add(quadroDaSimulacao.getTotalJuros().add(quadroDaSimulacao.getTotalMulta())));

        somatorioDosValoresASeremDescontados(simulacaoDTO.getSomarioDescontosAutomaticosFatura(), simulacaoDTO.getSomatorioDescontosAutomaticosJuros(),
            simulacaoDTO.getSomatorioDescontosAutomaticosJurosMora(), simulacaoDTO.getDescontoDaNegociacao() , quadroDaSimulacao);
        
        calculoDoDescontoCreditoDoCliente(simulacaoDTO.isAutorizacaoDescontarCredito(), quadroDaSimulacao, simulacaoDTO);

        calcularValorFinalASerDecontato(quadroDaSimulacao);

        buscarPorcentagemDaCorrecaoMonetaria(quadroDaSimulacao, simulacaoDTO, quantidadeDeFaturaEmAberto, ultimaRefenciaDaFaturaEmAberto);

        quadroDaSimulacao.setCorrecaoMonetaria(quadroDaSimulacao.getSubTotalDescontando()
            .multiply(simulacaoDTO.getPorcentagemDaCorrecaoMonetaria()).setScale(2, RoundingMode.HALF_UP));

        quadroDaSimulacao.setTotalANegociar(quadroDaSimulacao.getSubTotalDescontando().add(quadroDaSimulacao.getCorrecaoMonetaria()));

        parcelamentoFaturaSimulacaoValidacao.validacaoValorFinaDarDividaEMenorQueAEntradaOuDescontoDaNegociacao(quadroDaSimulacao.getTotalANegociar(), 
            simulacaoDTO.getValorEntrada(), quadroDaSimulacao.getDescontoDaNegociacao());

        return quadroDaSimulacao;
    }

    private void buscarPorcentagemDaCorrecaoMonetaria(ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao, ParcelamentoFaturaSimulacaoDTO simulacaoDTO,
        Integer quantidadeDeFaturaEmAberto, Integer ultimaRefenciaDaFaturaEmAberto) {

        BigDecimal valorCorrecaoMonetaria;

        if (simulacaoDTO.isCorrecaoMonetario()) {

            boolean tipoIncentivoJurifico = simulacaoDTO.isIncentivoJuridico();

            valorCorrecaoMonetaria = buscarPorcentagemDoIncentivo(tipoIncentivoJurifico, simulacaoDTO.getValorEntrada(), quadroDaSimulacao.getSubTotalDescontando(), 
                simulacaoDTO.getNumeroDeParcelas(), quantidadeDeFaturaEmAberto, simulacaoDTO.getSomarioDescontosAutomaticosFatura(), simulacaoDTO.getMatriculaImovel(), ultimaRefenciaDaFaturaEmAberto);
        }else { 
            valorCorrecaoMonetaria = BigDecimal.ZERO;
        }
        simulacaoDTO.setPorcentagemDaCorrecaoMonetaria(valorCorrecaoMonetaria);
    }

    private BigDecimal buscarPorcentagemDoIncentivo(boolean incentivoJuridico, BigDecimal valorEntrada, BigDecimal valorDaDividaAntesCorrecao, Short numeroDeParcelas, 
        Integer quantidadeDeFaturaEmAberto, BigDecimal valorTotalDasFaturaEmAberto, Integer matriculaImovel, Integer ultimaRefenciaDaFaturaEmAberto) {

        LocalDateTime diaEHoraDeHoje = LocalDateTime.now();

        BigDecimal porcentagemDaEntrada = valorEntrada == null ? BigDecimal.ZERO : valorEntrada.divide(
            valorDaDividaAntesCorrecao, 2, RoundingMode.HALF_EVEN);

        Short grupoDeConsumoDoImovel = imovelRepository.buscarGrupoDeConsumoAtravesDaMatriculaIMovel(matriculaImovel);
        Short situacaoAguaDoImovel = imovelRepository.buscarSituacaoAguaDoImovel(matriculaImovel);
        Short situacaoEsgotoDoImovel = imovelRepository.buscarSituacaoEsgotoDoImovel(matriculaImovel);

        if (incentivoJuridico) {
            
            IncentivoClienteDetalhe incentivoClienteDetalhado = buscarIncentivoDetalhadoClienteDoTipoJuridco(numeroDeParcelas, quantidadeDeFaturaEmAberto,
                valorTotalDasFaturaEmAberto, ultimaRefenciaDaFaturaEmAberto, diaEHoraDeHoje, porcentagemDaEntrada, grupoDeConsumoDoImovel, 
                situacaoAguaDoImovel, situacaoEsgotoDoImovel);
                    
            return incentivoClienteDetalhado == null ? BigDecimal.ZERO : incentivoClienteDetalhado.getValorCorrecaoMonetaria();
        }else {

            IncentivoClienteDetalhe incentivoClienteDetalhado = buscarIncentivoDetalhadoClienteYipoEspecialEGeral(numeroDeParcelas, quantidadeDeFaturaEmAberto,
                valorTotalDasFaturaEmAberto, ultimaRefenciaDaFaturaEmAberto, diaEHoraDeHoje, porcentagemDaEntrada, grupoDeConsumoDoImovel, 
                situacaoAguaDoImovel, situacaoEsgotoDoImovel);

            return incentivoClienteDetalhado == null ? BigDecimal.ZERO : incentivoClienteDetalhado.getValorCorrecaoMonetaria();
        }
    }

    private List<ParcelamentoFaturaListaValoresDaParcelaDTO>  calcularValorParcelas(BigDecimal valorASerParceladoMenosAEntrada, Short numeroParcelas) {

        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDeParcelas = new ArrayList<>();

        if (valorASerParceladoMenosAEntrada.compareTo(BigDecimal.ZERO) > 0) {

            BigDecimal valorParcelado = valorASerParceladoMenosAEntrada.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_EVEN);        

            BigDecimal valorMultriplicadoParaVerificarArredondamento = valorParcelado.multiply(BigDecimal.valueOf(numeroParcelas)).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal valorDaDiferencaAPagarEValorDasParcelas = valorASerParceladoMenosAEntrada.subtract(valorMultriplicadoParaVerificarArredondamento);

            for (short x = 1 ; x <= numeroParcelas ; x++) {

                ParcelamentoFaturaListaValoresDaParcelaDTO daParcelaDTO = new ParcelamentoFaturaListaValoresDaParcelaDTO();

                if (x == 1 && valorDaDiferencaAPagarEValorDasParcelas.compareTo(BigDecimal.ZERO) > 0) {
                    daParcelaDTO.setNumeroDeParcelas(x);
                    daParcelaDTO.setValorParcelas(valorParcelado.add(valorDaDiferencaAPagarEValorDasParcelas));
                    listaDeParcelas.add(daParcelaDTO);
                    continue;
                }   
                
                daParcelaDTO.setNumeroDeParcelas(x);
                daParcelaDTO.setValorParcelas(valorParcelado);
                daParcelaDTO.setDataVencimento(LocalDate.now());
                listaDeParcelas.add(daParcelaDTO);
            }
            
        }
        return listaDeParcelas;
    }

    private ParcelamentoFaturaSimulacaoPagamentoDTO instanciandoValoresParaQuadroParcelamento(LocalDate dataDaEntrada,
        ParcelamentoFaturaSimulacaoRespostaDTO quadroDaSimulacao,  List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelas, BigDecimal valorDaEntrada){

        ParcelamentoFaturaSimulacaoPagamentoDTO quadroDoParcelamento = new ParcelamentoFaturaSimulacaoPagamentoDTO();

        quadroDoParcelamento.setListaParcelas(listaDasParcelas);
        quadroDoParcelamento.setValorDaEntrada(valorDaEntrada);
        quadroDoParcelamento.setDataPagamentoDaEntrada(dataDaEntrada);
        quadroDoParcelamento.setTotal(quadroDaSimulacao.getTotalANegociar());

        return quadroDoParcelamento;
    }
    
    private String construirJustificativaParaSimulacao(LocalDate dataHoje, BigDecimal valorEntrada, LocalDate dataPagamentoDaEntrada, Short quantidadeDeParcelas, 
        BigDecimal valorPrimeiraParcela,  BigDecimal valordasRestanteDasParcelas) {

        DateTimeFormatter formatoDiaMesAno = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder dcAnotacao = new StringBuilder("Simulação de parcelamento realizada no dia ");
        dcAnotacao.append(dataHoje.format(formatoDiaMesAno));
        
        if (valorEntrada != null){
            dcAnotacao.append("com entrada de R$");
            dcAnotacao.append(valorEntrada);
            dcAnotacao.append(" pagando no dia ");
            dcAnotacao.append(dataPagamentoDaEntrada.format(formatoDiaMesAno));
        } else {
            dcAnotacao.append( " com ");
        }

        if (valorEntrada != null && quantidadeDeParcelas != 0) {
            dcAnotacao.append( " e ");
        }

        if (quantidadeDeParcelas != 0) {

            dcAnotacao.append(quantidadeDeParcelas);
            dcAnotacao.append(" parcelas");

            dcAnotacao.append(" de R$");
            dcAnotacao.append(valorPrimeiraParcela);

            if (valordasRestanteDasParcelas.compareTo(BigDecimal.ZERO) != 0 && valordasRestanteDasParcelas.compareTo(valorPrimeiraParcela) != 0) {
        
                dcAnotacao.append(", sendo a primeira parcela de R$");
                dcAnotacao.append(valorPrimeiraParcela);
                dcAnotacao.append(", e as outras parcelas de R$");
                dcAnotacao.append(valordasRestanteDasParcelas);
                dcAnotacao.append(".");
            }            
        }
        return dcAnotacao.toString();
    }

    private void executarDossieDoImovelNoParcelamento(String dcAnotacao, Integer matriculaImovel, Short dv, Integer cdCliente, 
        Short dvCliente, String loginUsuario) {

        DossieImovel dossieIMovelParcelamentoFatura = dossieImovelParcelamentofatura.construirDossieImovelParaParcelamentoFatura(dcAnotacao, matriculaImovel, 
            dv, cdCliente, dvCliente, loginUsuario);

        dossieImovelRepository.save(dossieIMovelParcelamentoFatura);
    }


    private void executarDossieClienteNoParcelamento(Integer cdCliente, String dcAnotacao, String dcObjeto, String loginUsuario) {

        DossieCliente dossieCliente = dossieClienteFaturamentoRegra.cadastrarDossieClienteNOFaturamentoFatura(cdCliente, dcAnotacao, dcObjeto, loginUsuario);

        dossieClienteRepository.save(dossieCliente);
    }

     private LocalDate calculandoPrimeiraParcelaSemEntrada(Short diaPagamentoDasParcelas,  List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValoresEDatas,
        ParcelamentoFaturaListaValoresDaParcelaDTO parcelaDTO) {

        LocalDate dataPgamamentoDias;
        int diaDeHoje = LocalDate.now().getDayOfMonth();

         if (diaDeHoje > diaPagamentoDasParcelas) {

             LocalDate auxDatPagamento = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), diaPagamentoDasParcelas);
             dataPgamamentoDias = auxDatPagamento.plusMonths(2L);

             parcelaDTO.setDataVencimento(dataPgamamentoDias);

             listaDasParcelasComValoresEDatas.add(parcelaDTO);
         }else {

            LocalDate auxDatPagamento = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), diaPagamentoDasParcelas);
            dataPgamamentoDias = auxDatPagamento.plusMonths(1L);

            parcelaDTO.setDataVencimento(dataPgamamentoDias);

            listaDasParcelasComValoresEDatas.add(parcelaDTO);
         }
        return dataPgamamentoDias;
    }

    private LocalDate calculandoPrimeiraParcelaComEntrada(Short diaPagamentoDasParcelas, LocalDate dataDeOagamentoDaEntrada,
        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValoresEDatas, ParcelamentoFaturaListaValoresDaParcelaDTO parcelaDTO) {

        LocalDate dataPgamamentoDias;
        int diaPagamentoDaEntrada = dataDeOagamentoDaEntrada.getDayOfMonth();

         if (diaPagamentoDaEntrada > diaPagamentoDasParcelas) {
             
            LocalDate dataPagamentoModificandoSomenteDia = LocalDate.of
                (dataDeOagamentoDaEntrada.getYear(), dataDeOagamentoDaEntrada.getMonth(), diaPagamentoDasParcelas);

             dataPgamamentoDias = dataPagamentoModificandoSomenteDia.plusMonths(2L);

             parcelaDTO.setDataVencimento(dataPgamamentoDias);

             listaDasParcelasComValoresEDatas.add(parcelaDTO);
         }else {

            LocalDate dataPagamentoModificandoSomenteDia = LocalDate.of
                (dataDeOagamentoDaEntrada.getYear(), dataDeOagamentoDaEntrada.getMonth(), diaPagamentoDasParcelas);

            dataPgamamentoDias = dataPagamentoModificandoSomenteDia.plusMonths(1L);

            parcelaDTO.setDataVencimento(dataPgamamentoDias);

            listaDasParcelasComValoresEDatas.add(parcelaDTO);
         }
        return dataPgamamentoDias;
    }

    private List<ParcelamentoFaturaListaValoresDaParcelaDTO> colocarDataNasParcelas(List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValores, 
        Short diaPagamentoDasParcelas, LocalDate dataDeOagamentoDaEntrada) {

        List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaDasParcelasComValoresEDatas = new ArrayList<>();

        int primeiraParcela = 1;
        LocalDate dataPgamamentoDias = null;
        
        for (ParcelamentoFaturaListaValoresDaParcelaDTO parcelaDTO : listaDasParcelasComValores) {

            if (primeiraParcela == 1) {
                
                if (dataDeOagamentoDaEntrada == null) {
                    
                    dataPgamamentoDias = calculandoPrimeiraParcelaSemEntrada(diaPagamentoDasParcelas, listaDasParcelasComValoresEDatas, parcelaDTO);

                } else {

                    dataPgamamentoDias = calculandoPrimeiraParcelaComEntrada(diaPagamentoDasParcelas, dataDeOagamentoDaEntrada, listaDasParcelasComValoresEDatas, 
                        parcelaDTO);
                    
                }
                primeiraParcela++;
            }else{

                LocalDate dataDaParcelasCom30 = dataPgamamentoDias.plusMonths(1L);

                parcelaDTO.setDataVencimento(dataDaParcelasCom30);

                listaDasParcelasComValoresEDatas.add(parcelaDTO); 
                dataPgamamentoDias = dataDaParcelasCom30;
            }            
        }        
        return listaDasParcelasComValoresEDatas;
    }

    private List<Integer> converterListaLocalDateEmListaInteger(List<LocalDate> refFaturaSelecionadas) {
        return refFaturaSelecionadas.stream()
            .map(ConverterData::localDateEmIntegerReferenciaFormatoDB)
            .collect(Collectors.toList());
    }

    private Integer verificarCdCliente(Integer cdCliente, String cpfOuCnpj) {

        return cpfOuCnpj == null ? cdCliente : clienteRepository.buscarCdClientePorCpfOuCnpj(cpfOuCnpj);
    }

    private Integer verificarCdCliente(String cdCliente) {

        return cdCliente.length() > 9 ? clienteRepository.buscarCdClientePorCpfOuCnpj(cdCliente) : Integer.parseInt(cdCliente) ;
    }

    private Integer calcularMesesEmAtraso(LocalDate diaHoje, Integer refFatura) {

        int anoDaRefFatura = refFatura / 100;
        int mesesDaRefenciaFatura = refFatura % 100;

        LocalDate dataDaFatura = LocalDate.of(anoDaRefFatura, mesesDaRefenciaFatura, 1);

        long diferencaEmMes = ChronoUnit.MONTHS.between(dataDaFatura, diaHoje);

        return (int) diferencaEmMes;
    }

    private BigDecimal buscarValorDaEntrada(BigDecimal valorDaEntrada, boolean pagamentoAVista, BigDecimal totalANegociar) {

        if (pagamentoAVista) {
            return totalANegociar;
        } else if(valorDaEntrada == null ){
            return BigDecimal.ZERO;
        }else {
           return valorDaEntrada;
        }
    }

    private Integer buscarUltimaReferenciaDeFatura(List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaFaturaEmAberto) {

        return listaFaturaEmAberto.stream().map(ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO::getRefFatura)
        .max(Comparator.comparing(Integer::valueOf))
        .get();
    }

    private Integer buscarUltimaReferenciaDeFaturaLocalDate(List<LocalDate> refFaturaSelecionadas) {

        LocalDate ultimaReferenciaEmAberto = refFaturaSelecionadas.stream().max(LocalDate::compareTo).get();

        return ConverterData.localDateEmIntegerDataFormatoDB(ultimaReferenciaEmAberto);
    }

    private Integer buscarUltimaReferenciaDeFaturaLocalDatePorReferencia(List<LocalDate> refFaturaSelecionadas) {

        LocalDate ultimaReferenciaEmAberto = refFaturaSelecionadas.stream().max(LocalDate::compareTo).get();

        return ConverterData.localDateEmIntegerReferenciaFormatoDB(ultimaReferenciaEmAberto);
    }

    private IncentivoClienteDetalhe buscarIncentivoDetalhadoClienteDoTipoJuridco(Short numeroDeParcelas, Integer quantidadeDeFaturaEmAberto,
        BigDecimal valorTotalDasFaturaEmAberto, Integer ultimaRefenciaDaFaturaEmAberto, LocalDateTime diaEHoraDeHoje, BigDecimal porcentagemDaEntrada, Short grupoDeConsumoDoImovel, Short situacaoAguaDoImovel, Short situacaoEsgotoDoImovel) {

        Short tipoDoIncentivoJuridico = (short)3;

        Optional<Short> idParamentoIncentivo = incentivoClienteRepository.buscarIncentivoPorTipoIncentivoJuridico(tipoDoIncentivoJuridico, diaEHoraDeHoje);

        if (!idParamentoIncentivo.isPresent()) {
            idParamentoIncentivo = incentivoClienteRepository.buscarUltimoIncentivoInativoPorTipoIncentivo(tipoDoIncentivoJuridico, diaEHoraDeHoje);
        }

        return buscarIncentivoClienteDetalhado(porcentagemDaEntrada, idParamentoIncentivo, numeroDeParcelas, quantidadeDeFaturaEmAberto, 
            valorTotalDasFaturaEmAberto, grupoDeConsumoDoImovel, situacaoAguaDoImovel, situacaoEsgotoDoImovel, ultimaRefenciaDaFaturaEmAberto);
    }


    private IncentivoClienteDetalhe buscarIncentivoDetalhadoClienteYipoEspecialEGeral(Short numeroDeParcelas, Integer quantidadeDeFaturaEmAberto,
        BigDecimal valorTotalDasFaturaEmAberto, Integer ultimaRefenciaDaFaturaEmAberto, LocalDateTime diaEHoraDeHoje, BigDecimal porcentagemDaEntrada, 
        Short grupoDeConsumoDoImovel, Short situacaoAguaDoImovel, Short situacaoEsgotoDoImovel) {

        Optional<Short> listaIdParametroIncentivo = incentivoClienteRepository.buscarIncentivoPorTipoIncentivoGeralEEspecial(diaEHoraDeHoje);

        if (!listaIdParametroIncentivo.isPresent()) {            
            listaIdParametroIncentivo = incentivoClienteRepository.buscarUltimoIncentivoInativoPorTipoIncentivo((short) 1, diaEHoraDeHoje);
        }

        return buscarIncentivoClienteDetalhado(porcentagemDaEntrada, listaIdParametroIncentivo, numeroDeParcelas, quantidadeDeFaturaEmAberto, valorTotalDasFaturaEmAberto, 
            grupoDeConsumoDoImovel, situacaoAguaDoImovel, situacaoEsgotoDoImovel, ultimaRefenciaDaFaturaEmAberto);
    }

    private List<LocalDate> buscarAListaRTeferenciaCAsoSejaPagametoAVista(List<LocalDate> refFaturaSelecionadas, Integer matriculaImovel, Integer cdCliente) {

        if (refFaturaSelecionadas == null || refFaturaSelecionadas.isEmpty()) {

            List<Integer> listaReferenciaEmInteger = faturaRepository.listaDeTodasReferenciasEmAberto(cdCliente, matriculaImovel);
            
            return listaReferenciaEmInteger.stream()
                .map(ConverterData::integerEmLocalDateReferenciaFormatoDB)
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> buscarListaDeReferenciasEmAberto(List<LocalDate> refFaturaSelecionadas, Integer matriculaImovel,
        Integer cdCliente) {

        if (refFaturaSelecionadas == null || refFaturaSelecionadas.isEmpty()) {
            return faturaRepository.listaDeFaturaEmAbertoDoClienteEMatricula(cdCliente, matriculaImovel);
        }else{
            List<Integer> listaEmIntegerDasReferencias = converterListaLocalDateEmListaInteger(refFaturaSelecionadas);
            return faturaRepository.listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovelSelecionados(cdCliente, matriculaImovel, listaEmIntegerDasReferencias);
        }
    }

    private Short buscarQuantosDiasAUltimaReferenciasEstaEmAtrado(Integer ultimaRefenciaDaFaturaEmAberto) {

        LocalDate dataDaUltimaFatura = ConverterData.integerEmLocalDateReferenciaFormatoDB(ultimaRefenciaDaFaturaEmAberto);

        long diasEmAtraso = dataDaUltimaFatura.until(LocalDate.now(), ChronoUnit.DAYS); 

        return (short) diasEmAtraso;
    }

    private void executarDadosParaListaDeParcelas(List<ParcelamentoFaturaSituacaoDasParcelasDTO> listaDasParcelas, 
        List<FaturamentoParcelamentoParcela> listaDaParcela) {

        for (FaturamentoParcelamentoParcela faturamentoParcelamentoParcela : listaDaParcela) {

            ParcelamentoFaturaSituacaoDasParcelasDTO dasParcelasDTO = new ParcelamentoFaturaSituacaoDasParcelasDTO();

            if (faturamentoParcelamentoParcela.getIdFaturamentoParcelamentoParcela().getNumeroParcela() == 0) {

                dasParcelasDTO.setNumero("Entrada");
            }else {
                dasParcelasDTO.setNumero(faturamentoParcelamentoParcela.getIdFaturamentoParcelamentoParcela().getNumeroParcela().toString());
            }

            dasParcelasDTO.setValor(faturamentoParcelamentoParcela.getValorParcela());
            
            dasParcelasDTO.setDataVencimento(ConverterData.integerEmLocalDateFormatoDB(faturamentoParcelamentoParcela.getDataVencimento()));

            if (faturamentoParcelamentoParcela.getDataPagamento() != null) dasParcelasDTO.setDataPagamento(
                ConverterData.integerEmLocalDateFormatoDB(faturamentoParcelamentoParcela.getDataPagamento()));

            dasParcelasDTO.setJuros(faturamentoParcelamentoParcela.getValorJuros());
            
            dasParcelasDTO.setMulta(faturamentoParcelamentoParcela.getValorMulta());

            dasParcelasDTO.setStatus(buscarSituacaoDaBaixa(faturamentoParcelamentoParcela.getTipoBaixa()));

            listaDasParcelas.add(dasParcelasDTO);                     
        }
    }

    private String buscarSituacaoDaBaixa(Short tipoBaixa) {

        return faturaRepository.buscarSituacaoDasBaixa(tipoBaixa);
    }

    private BigDecimal calculoSomaDasParcelasAindaNaoPagas(List<ParcelamentoFaturaSituacaoDasParcelasDTO> listaDasParcelas) {

        BigDecimal total = BigDecimal.ZERO;

        for (ParcelamentoFaturaSituacaoDasParcelasDTO parcelas : listaDasParcelas) {

            if (parcelas.getDataPagamento() == null) {

                total = total.add(parcelas.getValor());               
            }
        }
        return total;
    }

}
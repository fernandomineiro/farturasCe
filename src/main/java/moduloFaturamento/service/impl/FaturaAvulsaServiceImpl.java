package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.FaturaAvulsaComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCampoLancamentoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaListaLancamentoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaListaRespostaDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaLocalidadeDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaParamServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaTotalServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaValidarSSFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaVerificarClienteDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaBuscarMatriculaImovelProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaEnderecoClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaExibirDadosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaFiltroBaixaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaLancamentosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaPesquisaLoteArrecadacaoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaPesquisaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaServicosValidosProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.FaturaAvulsaCampoLancamentoMapper;
import moduloFaturamento.model.FaturaAvulsa;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.model.LancamentosFaturaAvulsa;
import moduloFaturamento.model.common.DossieCliente;
import moduloFaturamento.model.common.IdSolicitacaoServico;
import moduloFaturamento.model.common.SolicitacaoServico;
import moduloFaturamento.regras.dossieCliente.DossieClienteFaturaAvulsaRegra;
import moduloFaturamento.regras.faturaAvulsa.FaturaAvulsaCadastroRegra;
import moduloFaturamento.regras.faturaAvulsa.FaturaAvulsaEncerrarFaturaAvulsaRegra;
import moduloFaturamento.regras.lancamentoContabil.LancamentoContabilParaCrudFaturaAvulsaRegra;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.FaturaAvulsaRepository;
import moduloFaturamento.repository.LancamentoContabilRepository;
import moduloFaturamento.repository.LancamentosFaturaAvulsaRepository;
import moduloFaturamento.repository.ServicoImpostoRepository;
import moduloFaturamento.repository.common.DossieClienteRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.LocalidadeRepository;
import moduloFaturamento.repository.common.ServicoRepository;
import moduloFaturamento.repository.common.SolicitacaoServicoRepository;
import moduloFaturamento.service.FaturaAvulsaService;
import moduloFaturamento.service.impl.common.ServicoNaoTarifadoServiceImpl;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.faturaAvulsa.FaturaAvulsaCrudValidacao;
import moduloFaturamento.validacoes.faturaAvulsa.FaturaAvulsaValidacaoPesquisa;

@Service
@Transactional
public class FaturaAvulsaServiceImpl implements FaturaAvulsaService {

    @Autowired
    private FaturaAvulsaRepository repository;
    @Autowired
    private FaturaAvulsaValidacaoPesquisa faturaAvulsaValidacaoPesquisa;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FaturaAvulsaCampoLancamentoMapper faturaAvulsaCampoLancamentoMapper;
    @Autowired
    private LocalidadeRepository localidadeRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private FaturaAvulsaCrudValidacao faturaAvulsaCrudValido;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private FaturaAvulsaCadastroRegra faturaAvulsaCadastroRegra;
    @Autowired
    private LancamentosFaturaAvulsaRepository lancamentosFaturaAvulsaRepository;
    @Autowired
    private LancamentoContabilParaCrudFaturaAvulsaRegra lancamentoContabilParaCadastrarFaturaAvulsaRegra;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private DossieClienteFaturaAvulsaRegra dossieClienteFaturaAvulsaRegra;
    @Autowired
    private DossieClienteRepository dossieClienteRepository;
    @Autowired
    private FaturaAvulsaEncerrarFaturaAvulsaRegra faturaAvulsaEncerrarFaturaAvulsa;
    @Autowired
    private ServicoNaoTarifadoServiceImpl servicoNaoTarifadoServiceImpl;
    @Autowired
    private ServicoImpostoRepository servicoImpostoRepository;
    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;
    @Autowired
    private SolicitacaoServicoRepository solicicitacaoServicoRepository;

    private static final String CATEGORIA_FATURAAVULSA = "Fatura Avulsa";

    private List<Short> listaServicosParaCreditarEmVezDeDebitar = Arrays.asList((short)1994, (short)1996, (short)1997);

    @Override
    public GenericoWrapperDTO<List<FaturaAvulsaPesquisaDTO>> buscarFaturaAvulsas(FaturaAvulsaPesquisaFilterDTO filterDTTO, Pageable pageable) {

        faturaAvulsaValidacaoPesquisa.gerarExcecoesParaPesquisa(filterDTTO.getCdCliente(), filterDTTO.getDvCliente(), filterDTTO.getMatriculaImovel(), 
                filterDTTO.getDvMatriculaImovel() ,filterDTTO.getRefFatura());

        Integer refFaturaInteger = converterLocalDateEmInteger(filterDTTO.getRefFatura());

        List<FaturaAvulsaPesquisaProjectionDTO> avulsaPesquisaDTO = repository.buscarFaturas(filterDTTO.getCdCliente(), filterDTTO.getMatriculaImovel(), 
            refFaturaInteger, filterDTTO.getIdBaixa());

        List<FaturaAvulsaPesquisaDTO> dto = converterEntidadeProjectionParaDTO(avulsaPesquisaDTO);

        List<FaturaAvulsaPesquisaDTO> listaFaturaAvulsaDTO = dto.stream()
                .map(x -> {
                    Integer refFaturaEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(x.getRefFatura());
                    Integer anoRefFatura = refFaturaEmInteger / 100;
                    Short mesRefFatura = (short) (refFaturaEmInteger % 100);
                    x.setDvMatriculaImovel(imovelRepository.buscarDvDoImovelPelaMatricula(x.getMatriculaImovel()));
                    FaturaAvulsaPesquisaLoteArrecadacaoProjectionDTO buscaLoteArrecadacao = 
                        repository.buscarPesquisaLosteArrecadacao(x.getCdCliente(), x.getOrigemFatura(), anoRefFatura, mesRefFatura);

                    if(buscaLoteArrecadacao != null){
                        x.setAgenciaArrecadadora(buscaLoteArrecadacao.getAgenciaArredadora());
                        x.setAgenteArrecadador(buscaLoteArrecadacao.getAgenteArrecadador());
                        x.setDataProcessamento(buscaLoteArrecadacao.getDataProcedimentoDeArrecadacao());
                        x.setNumeroLote(buscaLoteArrecadacao.getNumeroLote());
                    }         
                    return x;
                }).collect(Collectors.toList());

        return Paginacao.paginarCampoUnico(new FaturaAvulsaComparator(), pageable,  listaFaturaAvulsaDTO);
    }

    private LocalDate executarDataCompleta(Integer referenciaBaixa, Short diaDaBaixa) {
        String diaDaBaixaEmString = "";

        if (diaDaBaixa < 9) {
            diaDaBaixaEmString = "0" + diaDaBaixa;
        } else {
            diaDaBaixaEmString =  "" + diaDaBaixa;
        }

        String dataBaixaCompletoEmString = referenciaBaixa + diaDaBaixaEmString;
        Integer dataEmInteger = Integer.parseInt(dataBaixaCompletoEmString);
        return ConverterData.integerEmLocalDateDataFormatoDB(dataEmInteger);
    }

    @Override
    public List<FaturaAvulsaFiltroBaixaProjectionDTO>  buscarTipoBaixa() {
        return repository.buscarTipoBaixa();
    }

    @Override
    public FaturaAvulsaEnderecoClienteProjectionDTO buscarEnderedoCliente(Integer cdCliente) {
        return clienteRepository.buscarEnderecoCliente(cdCliente);
    }

    @Override
    public FaturaAvulsaCampoLancamentoDTO buscarDadosFaturaAvulsa(Long id) {

        FaturaAvulsaExibirDadosProjectionDTO exibirFaturaAvulsa = repository.buscarDadosFaturaAvulsa(id);

         FaturaAvulsaCampoLancamentoDTO faturaAvulsaDTO = faturaAvulsaCampoLancamentoMapper.toDto(exibirFaturaAvulsa);

         Short inserirDvMatriculaIMovel = inserirDvMatriculaIMovel(faturaAvulsaDTO.getMatriculaImovel());
         faturaAvulsaDTO.setDvMatrioculaImovel(inserirDvMatriculaIMovel);

         return faturaAvulsaDTO;
    }

    @Override
    public List<FaturaAvulsaLancamentosProjectionDTO> buscarLancamentoFaturaAvulsa(Long id) {
        return repository.buscarLancamentoFatutaAvulsa(id);
    }

    @Override
    public List<FaturaAvulsaLocalidadeDTO> listaLocalidade() {
        return localidadeRepository.listaLocalidadeComIdENomeCidade();
    }

    @Override
    public FaturaAvulsaBuscarMatriculaImovelProjectionDTO buscarMatriculaImovel(Integer matriculaImovel, Short dvMatriculaImovel) {
        faturaAvulsaValidacaoPesquisa.gerarExcecaoParaValidarMatricula(matriculaImovel, dvMatriculaImovel);
        return imovelRepository.buscarCdCidadeEDescricaoCidadePelaMatricula(matriculaImovel);
    }

    @Override
    public FaturaAvulsaVerificarClienteDTO retornarImpostoDeUmClienteCliente(Integer cdCliente, Short dvCliente) {
        faturaAvulsaCrudValido.gerarExcecaoParaValidarMatricula(cdCliente, dvCliente);

        Short valorOrigemfatura = repository.buscarUmaNovaOuSequenciaDaOrigemFatura(cdCliente, converterDataHojeParaInteger());

        List<FaturaAvulsaListaLancamentoDTO> listaServicosParaClientesComRetencaoImposto = lancarImpostoCasoClienteTenhaRetencao(cdCliente);

        return new FaturaAvulsaVerificarClienteDTO(LocalDate.now(), valorOrigemfatura, listaServicosParaClientesComRetencaoImposto);
    }

    @Override
    public List<FaturaAvulsaServicosValidosProjectionDTO> buscarServicosValidosParaCadastrarFaturaAvulsa(Short cdCidade) {

        List<FaturaAvulsaServicosValidosProjectionDTO> listaServicos = servicoRepository.buscarServicosComLancamentoADEbitoEmFatura_S();

        List<FaturaAvulsaServicosValidosProjectionDTO> listaServicosValidos = new ArrayList<>();

        for (FaturaAvulsaServicosValidosProjectionDTO servicos : listaServicos) {

            Long numeroContaContabilSap = servicoRepository.buscarNumeroContaContabilNoEvento10(servicos.getCdServico());

            Optional<Long> servicoAutorizadoPelaCidade = servicoRepository.buscarSeServicoPodeSerUtilizadoPelaCidade(cdCidade, numeroContaContabilSap);

            if (servicoAutorizadoPelaCidade.isPresent()) {
                listaServicosValidos.add(servicos);
            }
        }

        return listaServicosValidos;
    }

    @Override
    public FaturaAvulsaTotalServicoDTO verificarServicoSelecioandoETotalLancamento(FaturaAvulsaParamServicoDTO listaServico) {

        List<FaturaAvulsaListaRespostaDTO> listaComValores = lancarValoresEmServicosComValoresPreEstabelecido(listaServico.getListaLancamentos(), listaServico.getCdCidade());

        BigDecimal totalLancamento = buscarSomatorioDosServicos(listaComValores);
        
        return new FaturaAvulsaTotalServicoDTO(totalLancamento, listaComValores);
    }


    @Override
    public void cadastrarUmFaturaAvusa(FaturaAvulsaCadastrarDTO cadastroFaturaAvulsaDTO, String token) {

        faturaAvulsaCrudValido.gerarExcecaoParaCadastrarFaturaAvulsa(cadastroFaturaAvulsaDTO.getRefFatura(), cadastroFaturaAvulsaDTO.getDataVenciemtno(),
            cadastroFaturaAvulsaDTO.getRefAtendimento(), cadastroFaturaAvulsaDTO.getCdAtendimento(), cadastroFaturaAvulsaDTO.getSeqSS() );
        
        executarCadastrarFaturaAvulsaValido(cadastroFaturaAvulsaDTO, token); 
    }

    @Override
    public void encerrarUmaFaturaAvulsa(Long id, String token) {
        
        faturaAvulsaCrudValido.gerarExcecaoParaEncerrarFaturaAvulsa(id);

        executarEncerraramentoDeUmaFaturaAvulsaPendenteValido(id, token);
    }

    @Override
    public boolean validarUmaSS(FaturaAvulsaValidarSSFilterDTO faturaAvulsaValidarSSDTO) {

        IdSolicitacaoServico id = new IdSolicitacaoServico(
                faturaAvulsaValidarSSDTO.getCdAtendimento(), faturaAvulsaValidarSSDTO.getRefAtendimento(), faturaAvulsaValidarSSDTO.getSeqSS());

        Optional<SolicitacaoServico> solicitacaoServicoOptional = this.solicicitacaoServicoRepository.findById(id);

        return solicitacaoServicoOptional.isPresent();
    }

    /**
     * MÉTODOS PRIVADOS
     */
    private void executarCadastrarFaturaAvulsaValido(FaturaAvulsaCadastrarDTO cadastroFaturaAvulsaDTO, String token){
        
        String loginUsuario = jwtTokenProvider.buscarLogin(token);
        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        Integer refFaturaEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(cadastroFaturaAvulsaDTO.getRefFatura());
        Integer dataVencimentoEmInteger = ConverterData.localDateEmIntegerDataFormatoDB(cadastroFaturaAvulsaDTO.getDataVenciemtno());

        Short grupoConsumo = 30;
        FaturaAvulsa cadastrarUmaFaturaAvulsa = faturaAvulsaCadastroRegra.cadastrarUmaFaturaAvulsa(cadastroFaturaAvulsaDTO.getCdCliente(), refFaturaEmInteger, cadastroFaturaAvulsaDTO.getOrigemFatura(), 
                        cadastroFaturaAvulsaDTO.getRefAtendimento(), cadastroFaturaAvulsaDTO.getCdAtendimento(), cadastroFaturaAvulsaDTO.getSeqSS(), 
                        cadastroFaturaAvulsaDTO.getLocalidade(), dataVencimentoEmInteger, cadastroFaturaAvulsaDTO.getDvCliente(), 
                        cadastroFaturaAvulsaDTO.getMensagem01(), cadastroFaturaAvulsaDTO.getMensagem02(), 
                        cadastroFaturaAvulsaDTO.getValorTotal(), grupoConsumo, cadastroFaturaAvulsaDTO.getMatriculaImovel() );
        repository.save(cadastrarUmaFaturaAvulsa);

        String jsonLeituraAntesDaAlteracao = ConvertObjectToJsonCesan.execute(cadastroFaturaAvulsaDTO);

        List<LancamentosFaturaAvulsa> cadastrarListaDeLancamentoFaturaAvulsa = faturaAvulsaCadastroRegra.cadastrarListaDeLancamentoFaturaAvulsa(
                        cadastroFaturaAvulsaDTO.getCdCliente(), refFaturaEmInteger, cadastroFaturaAvulsaDTO.getOrigemFatura(),
                        cadastroFaturaAvulsaDTO.getListaLancamentoFaturaAvulsa() );
        lancamentosFaturaAvulsaRepository.saveAll(cadastrarListaDeLancamentoFaturaAvulsa);

        Short eventoCadastrarLancametoContabilFatuaAvulsa = (short) 10 ;
        lancamentoContabilParaListaDeLancamentoFaturaAvulsa(cadastrarListaDeLancamentoFaturaAvulsa, cadastrarUmaFaturaAvulsa.getMatriculaImovel(),
                        (int)cadastrarUmaFaturaAvulsa.getCdCidade(), cadastrarUmaFaturaAvulsa.getIdFaturaAvulsa().getRefFatura(), 
                        cadastrarUmaFaturaAvulsa.getIdFaturaAvulsa().getOrigemFatura(), eventoCadastrarLancametoContabilFatuaAvulsa);
        
        DossieCliente dossieCliente = dossieClienteFaturaAvulsaRegra.cadastrarDosseiClienteEmfaturaAvulsa(cadastrarUmaFaturaAvulsa.getIdFaturaAvulsa().getCdCliente(), 
                        cadastrarUmaFaturaAvulsa.getMensagem01() + " " + cadastrarUmaFaturaAvulsa.getMensagem02(), CATEGORIA_FATURAAVULSA, loginUsuario);
        dossieClienteRepository.save(dossieCliente);
                
        auditoriaService.gerarAuditoriaCadastramento(cadastrarUmaFaturaAvulsa.getIdFaturaAvulsa().getCdCliente().longValue(), jsonLeituraAntesDaAlteracao, 
                        72L, CATEGORIA_FATURAAVULSA , idUsuario);
    }

    private void executarEncerraramentoDeUmaFaturaAvulsaPendenteValido(Long id, String token) {

        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        FaturaAvulsa faturaAvulsa = repository.findById(id).get();

        String jsonFaturaAvulsaAntesDaAlteracao = ConvertObjectToJsonCesan.execute(faturaAvulsa);
        jsonFaturaAvulsaAntesDaAlteracao = auditoriaService.forcarRegistroCamposEmAlteracao(jsonFaturaAvulsaAntesDaAlteracao, "refFatura", "origemFatura");
        
        FaturaAvulsa encerrarFaturaAvulsa = faturaAvulsaEncerrarFaturaAvulsa.encerrarFaturaAvulsa(id);
        repository.save(encerrarFaturaAvulsa);

        List<LancamentosFaturaAvulsa> listaDeLancamentoFaturaAvulsa = lancamentosFaturaAvulsaRepository.
            findByIdLancamentoFaturaAvulsaCdClienteAndIdLancamentoFaturaAvulsaRefFaturaAndIdLancamentoFaturaAvulsaOrigemFatura(
                        encerrarFaturaAvulsa.getIdFaturaAvulsa().getCdCliente(),
                        encerrarFaturaAvulsa.getIdFaturaAvulsa().getRefFatura(),
                        encerrarFaturaAvulsa.getIdFaturaAvulsa().getOrigemFatura() );

        String jsonFaturaDepoisAlteracao = ConvertObjectToJsonCesan.execute(encerrarFaturaAvulsa);

        Short eventoEncerrarLancamentoContabilAvulsa = (short) 11;
        lancamentoContabilParaListaDeLancamentoFaturaAvulsa(listaDeLancamentoFaturaAvulsa, encerrarFaturaAvulsa.getMatriculaImovel(), 
            (int)encerrarFaturaAvulsa.getCdCidade(), encerrarFaturaAvulsa.getIdFaturaAvulsa().getRefFatura(),
            encerrarFaturaAvulsa.getIdFaturaAvulsa().getOrigemFatura(), eventoEncerrarLancamentoContabilAvulsa);

        auditoriaService.gerarAuditoria(encerrarFaturaAvulsa.getIdFaturaAvulsa().getCdCliente().longValue(), jsonFaturaAvulsaAntesDaAlteracao, 
            jsonFaturaDepoisAlteracao, 72L, CATEGORIA_FATURAAVULSA, idUsuario);
        
    }

    private void lancamentoContabilParaListaDeLancamentoFaturaAvulsa(List<LancamentosFaturaAvulsa> cadastrarListaDeLancamentoFaturaAvulsa, Integer matriculaImovel,
        Integer cdCidade, Integer refFatura, Short origemFatura, Short eventoLancamentoFaturaAvulsa){

        for (LancamentosFaturaAvulsa lancamentosFaturaAvulsa : cadastrarListaDeLancamentoFaturaAvulsa) {

            String debitoOuCredito = listaServicosParaCreditarEmVezDeDebitar.contains(lancamentosFaturaAvulsa.getIdLancamentoFaturaAvulsa().getCdServico()) ? "C" : "D";

            List<LancamentoContabil> lancamentoContabilParaFaturaAvulsa = lancamentoContabilParaCadastrarFaturaAvulsaRegra.lancamentoContabilParaFaturaAvulsa(eventoLancamentoFaturaAvulsa, matriculaImovel, 
                        lancamentosFaturaAvulsa.getValorServico(), lancamentosFaturaAvulsa.getIdLancamentoFaturaAvulsa().getCdCliente(),
                        (short)0, lancamentosFaturaAvulsa.getIdLancamentoFaturaAvulsa().getCdServico(), debitoOuCredito, refFatura, origemFatura, cdCidade);

            lancamentoContabilRepository.saveAll(lancamentoContabilParaFaturaAvulsa);
        }
    }

    private Integer converterLocalDateEmInteger(LocalDate refFatura) {
        return refFatura == null ? null : ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura);
    }

    private Integer converterDataHojeParaInteger(){
        return ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now() );
    }

    private List<FaturaAvulsaListaLancamentoDTO>  lancarImpostoCasoClienteTenhaRetencao(Integer cdCliente) {

        List<FaturaAvulsaListaLancamentoDTO> lista = new ArrayList<>();

        if (clienteRepository.verificarSeClienteTemRetecaoImposto(cdCliente)) {

            for (Short valorServico : listaServicosParaCreditarEmVezDeDebitar) {

                FaturaAvulsaListaLancamentoDTO servico01 = new FaturaAvulsaListaLancamentoDTO(valorServico, servicoRepository.buscarNomeServico(valorServico),null) ;
                lista.add(servico01);                
            }
        }
        return lista;
    }


    private List<FaturaAvulsaListaRespostaDTO> lancarValoresEmServicosComValoresPreEstabelecido(List<FaturaAvulsaListaLancamentoDTO>  listaServico, Short cdLocalidade) {

        List<FaturaAvulsaListaRespostaDTO>  listaNova = new ArrayList<>();

        for (FaturaAvulsaListaLancamentoDTO lancFaturaAvulsa : listaServico) {

            if (lancFaturaAvulsa.getValorServico().compareTo(BigDecimal.ZERO) == 0) {

                List<BigDecimal> buscarValoresParaServico = servicoNaoTarifadoServiceImpl.buscarListaValorServicoNaoTarifadoPorCodigoServicoELocalidadade(
                    lancFaturaAvulsa.getCdServico(), cdLocalidade);

                FaturaAvulsaListaRespostaDTO lanc = new FaturaAvulsaListaRespostaDTO(lancFaturaAvulsa.getCdServico(), lancFaturaAvulsa.getDcServico(), null);

                switch (buscarValoresParaServico.size()) {
                    case 0:
                        listaNova.add(lanc);
                        break;

                    case 1:
                        lanc.getValorServico().add(buscarValoresParaServico.get(0) );
                        listaNova.add(lanc);
                        break;
                
                    default:
                        lanc.getValorServico().addAll(buscarValoresParaServico);
                        break;
                }  
                
            } else {
                List<BigDecimal> listBigdecimal = Arrays.asList(lancFaturaAvulsa.getValorServico());
                FaturaAvulsaListaRespostaDTO lanc = new FaturaAvulsaListaRespostaDTO(lancFaturaAvulsa.getCdServico(), lancFaturaAvulsa.getDcServico(), listBigdecimal);
                listaNova.add(lanc);
            }          
        }

        return listaNova;
    }

    private BigDecimal buscarSomatorioDosServicos(List<FaturaAvulsaListaRespostaDTO> listaComValores) {

        BigDecimal totalParaFront = BigDecimal.ZERO;

        boolean verificarSeExecutaRotinaDeCalculo = verificarSeTemValorList(listaComValores);

        if (verificarSeExecutaRotinaDeCalculo) {

            BigDecimal totalSemIMpostos = fazerSomatorioDosValoresDaLista(listaComValores);

            colocarValoresNosServicosComImpostos(listaComValores, totalSemIMpostos);
            
            totalParaFront = somarOsValoresComImpostos(listaComValores);
        }
        return totalParaFront;
    }
    
    private boolean verificarSeTemValorList(List<FaturaAvulsaListaRespostaDTO> listaComValores) {

        boolean verificarSeExecutaRotinaDeCalculo = false;        
        for (FaturaAvulsaListaRespostaDTO faturaAvulsaListaLancamentoDTO : listaComValores) {

            if (faturaAvulsaListaLancamentoDTO.getValorServico() == null) {
                continue;
            }

            List<BigDecimal> listBigdecimal = faturaAvulsaListaLancamentoDTO.getValorServico();

            if (listBigdecimal.size() > 1) {

                return verificarSeExecutaRotinaDeCalculo;                
            }
            
        }
        return true;
    }

    private BigDecimal somarOsValoresComImpostos(List<FaturaAvulsaListaRespostaDTO> listaComValores) {

        BigDecimal totalComImpostos = BigDecimal.ZERO;
        for (FaturaAvulsaListaRespostaDTO faturaAvulsaListaDTO : listaComValores) {

            if (faturaAvulsaListaDTO.getValorServico() != null) {

                if(listaServicosParaCreditarEmVezDeDebitar.contains(faturaAvulsaListaDTO.getCdServico()) ){

                    totalComImpostos = totalComImpostos.subtract(faturaAvulsaListaDTO.getValorServico().get(0));
                }else{
                    totalComImpostos = totalComImpostos.add(faturaAvulsaListaDTO.getValorServico().get(0));
                }
                
            }
        }
        return totalComImpostos;
    }

    private void colocarValoresNosServicosComImpostos(List<FaturaAvulsaListaRespostaDTO> listaComValores, BigDecimal total) {

        for (FaturaAvulsaListaRespostaDTO faturaAvulsaListaLancamentoDTO : listaComValores) {

            if (listaServicosParaCreditarEmVezDeDebitar.contains(faturaAvulsaListaLancamentoDTO.getCdServico()) && total.compareTo(BigDecimal.valueOf(10.00)) >= 0) {

                if(faturaAvulsaListaLancamentoDTO.getCdServico() == 1994) {

                    Double valorASerMultiplicado = servicoImpostoRepository.buscarPorcentagemDoServico((short) 1994)/100;
                    BigDecimal valorPocentagem = total.multiply(BigDecimal.valueOf(valorASerMultiplicado) ).setScale(2, RoundingMode.HALF_EVEN );
                    List<BigDecimal> listBigdecimal = Arrays.asList(valorPocentagem);
                    faturaAvulsaListaLancamentoDTO.setValorServico(listBigdecimal);
             
                } else if(faturaAvulsaListaLancamentoDTO.getCdServico() == 1996) {

                    Double valorASerMultiplicado = servicoImpostoRepository.buscarPorcentagemDoServico((short) 1996)/100;
                    BigDecimal valorPocentagem = total.multiply(BigDecimal.valueOf(valorASerMultiplicado).setScale(2, RoundingMode.HALF_EVEN) );
                    List<BigDecimal> listBigdecimal = Arrays.asList(valorPocentagem);
                    faturaAvulsaListaLancamentoDTO.setValorServico(listBigdecimal);                 
                    
                } else {

                    Double valorASerMultiplicado = servicoImpostoRepository.buscarPorcentagemDoServico((short) 1997)/100;
                    BigDecimal valorPocentagem = total.multiply(BigDecimal.valueOf(valorASerMultiplicado) ).setScale(2, RoundingMode.HALF_EVEN);
                    List<BigDecimal> listBigdecimal = Arrays.asList(valorPocentagem);
                    faturaAvulsaListaLancamentoDTO.setValorServico(listBigdecimal);
                    
                }                
            }
        }      
    }

    private BigDecimal fazerSomatorioDosValoresDaLista(List<FaturaAvulsaListaRespostaDTO> listaComValores) {

        BigDecimal total = BigDecimal.ZERO;

        for (FaturaAvulsaListaRespostaDTO lanc : listaComValores) {

            if (!listaServicosParaCreditarEmVezDeDebitar.contains(lanc.getCdServico()) && lanc.getValorServico() != null ) {

                total= total.add(lanc.getValorServico().get(0));
            }
        }
        return total;
    }

    private Short inserirDvMatriculaIMovel(Integer matriculaImovel) {

        if (Optional.ofNullable(matriculaImovel).orElse(0) != 0) {
            return imovelRepository.buscarDvDoImovelPelaMatricula(matriculaImovel);
        }
        return null;
    }

    private List<FaturaAvulsaPesquisaDTO> converterEntidadeProjectionParaDTO(List<FaturaAvulsaPesquisaProjectionDTO> avulsaPesquisaDTO) {

        List<FaturaAvulsaPesquisaDTO> converterEntidadeProjectionParaDTO = new ArrayList<>();

        for (FaturaAvulsaPesquisaProjectionDTO projection : avulsaPesquisaDTO) {
            
            FaturaAvulsaPesquisaDTO dto = new FaturaAvulsaPesquisaDTO();
            dto.setCdCliente(projection.getCdCliente());
            dto.setRefFatura(ConverterData.integerEmLocalDateReferenciaFormatoDB(projection.getRefFatura() ) );
            dto.setOrigemFatura(projection.getOrigemFatura());

            dto.setMatriculaImovel(projection.getMatriculaImovel());

            dto.setValorFatura(projection.getValorFatura());
        
            dto.setDataVencimento(ConverterData.integerEmLocalDateDataFormatoDB(projection.getDataVencimento()));
            dto.setNomeBaixa(projection.getNomeBaixa());
        
            if (projection.getDiaDaBaixa() != 0 && projection.getDiaDaBaixa() != null) {

                dto.setDataBaixaCompleto(executarDataCompleta(projection.getReferenciaBaixa(), projection.getDiaDaBaixa()) );

            }

            dto.setRefAtendimento(projection.getRefAtendimento());
            dto.setCdAtendimento(projection.getCdAtendimento());
            dto.setSeqSS(projection.getSeqSS());

            dto.setId(projection.getId());
        
            converterEntidadeProjectionParaDTO.add(dto);
            
        }
    
        return converterEntidadeProjectionParaDTO;
    }
}

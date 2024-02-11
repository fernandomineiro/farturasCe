package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.CreditoLancadoComparador;
import moduloFaturamento.comparator.CreditoPesquisaComparador;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.credito.CreditoAtualizarDTO;
import moduloFaturamento.dto.credito.CreditoCadastrarDTO;
import moduloFaturamento.dto.credito.CreditoCampoLancadoPesquisaDTO;
import moduloFaturamento.dto.credito.CreditoEncerrarDTO;
import moduloFaturamento.dto.credito.CreditoLancadoDTO;
import moduloFaturamento.dto.credito.CreditoParaEditarRespostaDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaCreditoLancadoFilterDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaDTO;
import moduloFaturamento.dto.credito.projection.CreditoCampoEditarProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoCampoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoLancadoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoParaPesqBancoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoServicosValidosprojectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.EnderecoBasicoDoImovelProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.CreditoCampoPesquisaMapper;
import moduloFaturamento.mapper.CreditoLancadoPesquisaMapper;
import moduloFaturamento.mapper.CreditoParaCampoEditarMapper;
import moduloFaturamento.mapper.CreditoPesquisaMapper;
import moduloFaturamento.model.Credito;
import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IdCredito;
import moduloFaturamento.model.IdFaturamentoParcelamentoParcela;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.regras.credito.CreditoCadastroRegra;
import moduloFaturamento.regras.dossie.DossieCreditoRegra;
import moduloFaturamento.regras.lancamentoContabil.LancamentoContabilParaCrudCreditoRegra;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoParcelaRepository;
import moduloFaturamento.repository.LancamentoContabilRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.ServicoRepository;
import moduloFaturamento.service.CreditoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.credito.CreditoValidacaoCrud;
import moduloFaturamento.validacoes.credito.CreditoValidarPesquisa;

@Service
@Transactional
public class CreditoServiceImpl implements CreditoService {

    @Autowired
    private CreditoValidarPesquisa validacaoPesquisa;
    @Autowired
    private CreditoRepository repository;
    @Autowired
    private CreditoPesquisaMapper creditoPesquisaMapper;
    @Autowired
    private CreditoCampoPesquisaMapper creditoCampoPesquisaMapper;
    @Autowired
    private CreditoLancadoPesquisaMapper creditoLancadoPesquisaMapper;
    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CreditoParaCampoEditarMapper creditoParaCampoEditarMapper;
    @Autowired
    private CreditoValidacaoCrud creditoValidacaoCrud;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private DossieImovelRepository dossieImovelRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private DossieCreditoRegra dossieCreditoRegra;
    @Autowired
    private CreditoCadastroRegra creditoCadastroRegra;
    @Autowired
    private LancamentoContabilParaCrudCreditoRegra lancamentoContabilParaCadastrarCreditoRegra;
    @Autowired
    private FaturamentoParcelamentoParcelaRepository faturamentoParcelamentoParcelaRepository;
    @Autowired
    private LancamentoContabilRepository lancamentoContabilRepository;

    String CATEGORIA_CREDITO = "Credito";

    @Override
    public GenericoWrapperDTO<List<CreditoPesquisaDTO>> pesquisaCreditoPorMatricula(Integer matricula, short dv, Pageable pageable) {

        validacaoPesquisa.gerenciarValidacaoPesquisaExigida(matricula);

        List<CreditoParaPesqBancoProjectionDTO> buscarCredito = repository.pesquisarMatriculaNoCredito(matricula);

        return Paginacao.paginarCampoUnico(new CreditoPesquisaComparador(), pageable, creditoPesquisaMapper.toDto(buscarCredito) );
    }

    @Override
    public CreditoCampoLancadoPesquisaDTO pesquisarCampoCredito(CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO) {

        validacaoPesquisa.gerenciaValidacaoBuscarCreditoLancado(creditoLancadoDTO.getMatricula(), creditoLancadoDTO.getCdCredito());

        CreditoCampoProjectionDTO campoCredito = repository.pesquisarCreditoCampo(creditoLancadoDTO.getMatricula(), creditoLancadoDTO.getCdCredito());

        return creditoCampoPesquisaMapper.toDto(campoCredito);
    }

    @Override
    public GenericoWrapperDTO<List<CreditoLancadoDTO>> pesquisarCreditoOcorrencias(CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO, Pageable pageable) {

        validacaoPesquisa.gerenciaValidacaoBuscarCreditoLancado(creditoLancadoDTO.getMatricula(), creditoLancadoDTO.getCdCredito());

        List<CreditoLancadoProjectionDTO> campoCredito = repository.pesquisarCreditoLancado(creditoLancadoDTO.getMatricula(), creditoLancadoDTO.getCdCredito());

        return Paginacao.paginarCampoUnico(new CreditoLancadoComparador(), pageable, creditoLancadoPesquisaMapper.toDto(campoCredito));
    }

    @Override
    public EnderecoBasicoDoImovelProjectionDTO pesquisarEnderecoCompleto(Integer matricula) {
        return imovelRepository.buscarEnderecoBasicoDoImovel(matricula);
    }
    
    @Override
    public List<CreditoServicosValidosprojectionDTO> pesquisarServicosQuePodemSerSelcionadoNoCredito() {
        return servicoRepository.pesquisarListaServicoComPermissaoLancamentoProxFatura();
    }

    @Override
    public CreditoParaEditarRespostaDTO buscarDadosEditarCredito(Long id) {

        CreditoCampoEditarProjectionDTO campoPesquisado = repository.pesquisarCreditoParaTelaEditar(id);

        return creditoParaCampoEditarMapper.toDto(campoPesquisado);
    }

    @Override
    public void atualizarCredito(CreditoAtualizarDTO atualizarDTO, String token) {

        Credito credito = repository.findById(new IdCredito(atualizarDTO.getMatricula(), atualizarDTO.getCdCredito())).get();
        creditoValidacaoCrud.gerenciarValidacaoVerificarAtualizar(credito, atualizarDTO);

        creditoValidacaoCrud.gerenciarValidacaoParaCadstrar(atualizarDTO.getMatricula(), atualizarDTO.getCdParcelamento(), atualizarDTO.getNumeroParcela(),
            atualizarDTO.getOrigemFatura(), atualizarDTO.getReferenciaFatura(), 
            atualizarDTO.getRefAtendimento(), atualizarDTO.getCodAtendimento(), atualizarDTO.getSeqSS());

        rotinaAtualizarCredito(token, atualizarDTO);
    }

    @Override
    public void cadastrarCredito(CreditoCadastrarDTO cadastroDTO, String token) {

        creditoValidacaoCrud.gerenciarValidacaoParaCadstrar(cadastroDTO.getMatricula(), cadastroDTO.getCdParcelamento(), cadastroDTO.getNumeroParcela(),
            cadastroDTO.getOrigemFatura(), cadastroDTO.getReferenciaFatura(), 
            cadastroDTO.getRefAtendimento(), cadastroDTO.getCodAtendimento(), cadastroDTO.getSeqSS());

        rotinaCadastrarCredito(cadastroDTO, token);
    }

    @Override
    public void encerrarCredito(CreditoEncerrarDTO creditoEncerrarDTO, String token) {

        creditoValidacaoCrud.gerenciarValidacaoParaEncerrarCredito(creditoEncerrarDTO.getId(), creditoEncerrarDTO.getCampoJustificativa() );
        
        rotinaEncerrarCredito(creditoEncerrarDTO.getId(), creditoEncerrarDTO.getCampoJustificativa(), token);        
    }

    public void rotinaAtualizarCredito(String token, CreditoAtualizarDTO atualizarDTO) {

        Credito credito = repository.findById(new IdCredito(atualizarDTO.getMatricula(), atualizarDTO.getCdCredito())).get();

        String loginUsuario = jwtTokenProvider.buscarLogin(token);
        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        String jsonAntesAtualizarcredito = ConvertObjectToJsonCesan.execute(credito);

        boolean verificarSeValorFoiAlteradoParaLancamentoContabil = verificarSeValorCreditoFoiAlteradoParaLancamentoContabil(credito.getValorCredito(),
                                                        atualizarDTO.getValorCredito(), credito.getRefEncerramento());
        boolean verificarSeJustificativaFoiModificadaParaDossie = verificarSeJustificativaFoiModificadaParaDossie(credito.getDcAnotacao(), 
                                                        atualizarDTO.getCampoJustificativa());

        BigDecimal valorSaldoAntesDaAlteracaoParaLancamentoContabil = credito.getValorSaldo(); 

        verificarSeCampoValorSeraModificado(atualizarDTO.getValorCredito(), credito);
        verificarSeCampoSSSeraModificado(atualizarDTO.getRefAtendimento(), atualizarDTO.getCodAtendimento(), atualizarDTO.getSeqSS() , credito);
        verificarSeCampoFaturamentoParcelamentoParcelaSeraModificado(atualizarDTO.getCdParcelamento(), atualizarDTO.getNumeroParcela(), credito);
        verificarSeCampoFaturaSeraModificado(atualizarDTO.getOrigemFatura(), atualizarDTO.getReferenciaFatura(), credito);
        verificarSeCampoDcAnotacaoSeraModificado(atualizarDTO.getCampoJustificativa(), credito);

        credito.setUsuario(loginUsuario);
        repository.save(credito);

        String jsonDepoisAtualizarCredito = ConvertObjectToJsonCesan.execute(credito);

        rotinaParaAtualizarDossier(verificarSeJustificativaFoiModificadaParaDossie, credito.getDcAnotacao(), credito.getIdCredito().getMatricula(), credito.getDv(), 
            credito.getCdCliente(), credito.getUsuario(), credito.getData());

        rotinaAtualizarLancamentoContabil(verificarSeValorFoiAlteradoParaLancamentoContabil, credito, valorSaldoAntesDaAlteracaoParaLancamentoContabil);

        rotinaDaAuditoria(credito.getIdCredito().getMatricula().longValue(), jsonAntesAtualizarcredito, jsonDepoisAtualizarCredito, 
            71L, CATEGORIA_CREDITO, idUsuario);
    }

    public void rotinaCadastrarCredito(CreditoCadastrarDTO cadastroDTO, String token) {

        String loginUsuario = jwtTokenProvider.buscarLogin(token);
        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        Credito credito = creditoCadastroRegra.construirCredito(cadastroDTO.getMatricula(), loginUsuario, cadastroDTO.getValorCredito(), cadastroDTO.getCdServico(), cadastroDTO.getDv(),
            cadastroDTO.getRefAtendimento(), cadastroDTO.getCodAtendimento(), cadastroDTO.getSeqSS(), 
            cadastroDTO.getCdParcelamento(), cadastroDTO.getNumeroParcela(),
            cadastroDTO.getOrigemFatura(), cadastroDTO.getReferenciaFatura(),
            cadastroDTO.getCampoJustificativa());

        repository.save(credito);
        Long idAuditoria = repository.buscarIDCredito(credito.getIdCredito().getMatricula(), credito.getIdCredito().getCredito());
        credito.setId(idAuditoria);
        String jsonDepoisCadastrarCredito = ConvertObjectToJsonCesan.execute(credito);

        DossieImovel dossie = dossieCreditoRegra.construirDossieImovel(credito.getDcAnotacao(), credito.getIdCredito().getMatricula(), credito.getDv(), 
            credito.getCdCliente(), clienteRepository.buscarDVDoCliente(credito.getCdCliente()), credito.getUsuario());
        dossieImovelRepository.save(dossie);

        fazerLancamentoContabil((short) 30, credito.getIdCredito().getMatricula(), credito.getValorCredito(), credito.getCdCliente(),
            credito.getIdCredito().getCredito(), credito.getCdServico()); 

        auditoriaService.gerarAuditoriaCadastramento(credito.getIdCredito().getMatricula().longValue(), jsonDepoisCadastrarCredito, 71L, CATEGORIA_CREDITO, idUsuario);
    }

    public void rotinaEncerrarCredito(Long id, String campoJustificativa, String token) {

        String loginUsuario = jwtTokenProvider.buscarLogin(token);
        Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

        Credito credito = repository.findById(id).get();
        String jsonAntesAtualizarcredito = ConvertObjectToJsonCesan.execute(credito);

        credito.setDcAnotacao(campoJustificativa);
        rotinaParaAtualizarDossier(true, credito.getDcAnotacao(), credito.getIdCredito().getMatricula(), credito.getDv(), credito.getCdCliente(), 
            credito.getUsuario(), credito.getData());

        verificarSeCreditoEstaEncerrado(credito); 

        Integer dataEncerramentoEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now() );
        credito.setRefEncerramento(dataEncerramentoEmInteger);
        credito.setValorSaldo(BigDecimal.valueOf(0));
        credito.setUsuario(loginUsuario);
        repository.save(credito);

        String jsonDepoisAtualizarCredito = ConvertObjectToJsonCesan.execute(credito);

        rotinaDaAuditoria(credito.getIdCredito().getMatricula().longValue(), jsonAntesAtualizarcredito, jsonDepoisAtualizarCredito, 
            71L, CATEGORIA_CREDITO, idUsuario);
    }

    /**
    * Metodos privados
    */
    private void rotinaAtualizarLancamentoContabil(boolean verificarSeValorFoiAlterado, Credito credito, BigDecimal valorSaldoAntesDaAlteracaoParaLancamentoContabil) {

        if (verificarSeValorFoiAlterado) {

            Short excluindoLancamentoErrado = 31;
            Short criadoNovoLancamentoContabil = 30;

            fazerLancamentoContabil(excluindoLancamentoErrado, credito.getIdCredito().getMatricula(), valorSaldoAntesDaAlteracaoParaLancamentoContabil, 
                credito.getCdCliente(), credito.getIdCredito().getCredito(), credito.getCdServico());

            fazerLancamentoContabil(criadoNovoLancamentoContabil, credito.getIdCredito().getMatricula(), credito.getValorSaldo(), credito.getCdCliente(), 
                credito.getIdCredito().getCredito(), credito.getCdServico());
        }
    }

    private void fazerLancamentoContabil(Short evento, Integer matricula, BigDecimal valorCredito, Integer cdCliente, Short cdCredito, Short cdServico){
        List<LancamentoContabil> lancamentoContabilParaCredito = lancamentoContabilParaCadastrarCreditoRegra.lancamentoContabilParaCredito(evento, 
            matricula, valorCredito, cdCliente, cdCredito, cdServico, "C");
        lancamentoContabilRepository.saveAll(lancamentoContabilParaCredito);
    }

    private void rotinaParaAtualizarDossier(boolean verificarSeJustificativaFoiModificada, String cdAnotacao, Integer matricula, Short dv, Integer cdCliente, 
        String usuario, Integer dataAnotacao) {

            if (verificarSeJustificativaFoiModificada) {

                DossieImovel dossie = 
                dossieCreditoRegra.atualizarDossieImovel(cdAnotacao, matricula, dv , cdCliente, clienteRepository.buscarDVDoCliente(cdCliente), usuario, dataAnotacao);

                dossieImovelRepository.save(dossie);                
            }            
    }       


    private void verificarSeCampoDcAnotacaoSeraModificado(String campoJustificativa, Credito credito) {
        if (!campoJustificativa.equalsIgnoreCase(credito.getDcAnotacao())) credito.setDcAnotacao(campoJustificativa);
    }

    private void verificarSeCampoFaturamentoParcelamentoParcelaSeraModificado(Integer cdParcelamento, Integer numeroParcela, Credito credito) {

        if (credito.getFaturamentoParcelamentoParcela() != null){

            if (cdParcelamento != credito.getFaturamentoParcelamentoParcela().getIdFaturamentoParcelamentoParcela().getCdParcelamento() &&
                numeroParcela != credito.getFaturamentoParcelamentoParcela().getIdFaturamentoParcelamentoParcela().getNumeroParcela()) {

                    if (cdParcelamento == null && numeroParcela == null) {
                            credito.setFaturamentoParcelamentoParcela(null);
                        
                    }else{

                        FaturamentoParcelamentoParcela faturamentoParcelamentoParcela = 
                            faturamentoParcelamentoParcelaRepository.findById(
                                new IdFaturamentoParcelamentoParcela(cdParcelamento, numeroParcela) ).get();

                        credito.setFaturamentoParcelamentoParcela(faturamentoParcelamentoParcela);
                        
                    }
            }         
        }else {

            if (cdParcelamento != null && numeroParcela != null) {
                IdFaturamentoParcelamentoParcela id = new IdFaturamentoParcelamentoParcela(cdParcelamento, numeroParcela);
                credito.setFaturamentoParcelamentoParcela(faturamentoParcelamentoParcelaRepository.findById(id).get() );
            }
        }
    }

    private void verificarSeCampoSSSeraModificado(Integer refAtendimento, Integer cdAtendimento, Short seqSS, Credito credito) {

        // Se Usuario não preencher nada os valores da Tela serão nulas, mas no banco entendo que Zero é valor nullo
        if (refAtendimento == null && cdAtendimento == null && seqSS == null) {
            refAtendimento = 0;
            cdAtendimento = 0;
            seqSS = 0;
        }

        if (refAtendimento != credito.getRefAtendimento() || refAtendimento != credito.getCdAtendimento() || seqSS != credito.getSeqSS() ) {

                if (refAtendimento == 0 && cdAtendimento == 0 && seqSS == 0) {

                    credito.setRefAtendimento(0);
                    credito.setCdAtendimento(0);
                    credito.setSeqSS((short)0);
                }else{

                    credito.setRefAtendimento(refAtendimento);
                    credito.setCdAtendimento(cdAtendimento);
                    credito.setSeqSS(seqSS);
                }
        }

    }

    private void verificarSeCampoValorSeraModificado(BigDecimal valorCredito, Credito credito) {

        if (credito.getRefEncerramento() != 0) {
            credito.setValorCredito(valorCredito);
            return;
        }

        if (!valorCredito.equals(credito.getValorCredito())){

            if (credito.getValorSaldo().equals(credito.getValorCredito()) ) {

                credito.setValorCredito(valorCredito);
                credito.setValorSaldo(valorCredito);                             
            }else {

                if(credito.getValorSaldo().equals(BigDecimal.ZERO)) {
                    credito.setValorCredito(valorCredito);
                }else{

                    BigDecimal valorSubtracaoParaVerDiferencaDosCreditos = valorCredito.subtract(credito.getValorCredito()).setScale(2);
                    credito.setValorCredito(valorCredito);
                    credito.setValorSaldo(valorSubtracaoParaVerDiferencaDosCreditos.add(credito.getValorSaldo()));  
                }
                
            }
        } 
    }

    private void verificarSeCampoFaturaSeraModificado(Short origemFatura, LocalDate referenciaFatura, Credito credito) {

        if (credito.getRefFatura() == null && credito.getOrigemFatura() == null) {

            if (referenciaFatura != null || origemFatura != null) {   
                Integer dataEmLocalDateDoCredito = ConverterData.localDateEmIntegerReferenciaFormatoDB(referenciaFatura);             
                credito.setRefFatura(dataEmLocalDateDoCredito);
                credito.setOrigemFatura(origemFatura);
            }
        }else {

            if (referenciaFatura == null && origemFatura == null) {
                credito.setRefFatura(null);
                credito.setOrigemFatura(null);
            }else{
                Integer dataEmLocalDate = ConverterData.localDateEmIntegerReferenciaFormatoDB(referenciaFatura);
                if (dataEmLocalDate != credito.getRefFatura() || origemFatura != credito.getOrigemFatura()) {
                    credito.setRefFatura(dataEmLocalDate);
                    credito.setOrigemFatura(origemFatura);
                }
            }
        }   
    }

    private boolean verificarSeValorCreditoFoiAlteradoParaLancamentoContabil(BigDecimal valorCredito, BigDecimal valorCreditoNoBD, Integer refEncerramento) {
        if (refEncerramento != 0) {
            return false;
        }

        return !valorCredito.equals(valorCreditoNoBD);
    }

    private Boolean verificarSeJustificativaFoiModificadaParaDossie(String anotacao, String campoJustificativa) {
        return !anotacao.equalsIgnoreCase(campoJustificativa);
    }

    private void rotinaDaAuditoria(Long matricula, String antes, String depois, Long codigo, String nomeCategoria, Long idUsuario ){
        auditoriaService.gerarAuditoria(matricula, antes, depois, codigo, nomeCategoria, idUsuario);
    }

    private void verificarSeCreditoEstaEncerrado(Credito credito) {

        if (credito.getRefEncerramento() == 0 && !credito.getValorSaldo().equals(BigDecimal.ZERO)) {
            fazerLancamentoContabil((short) 31, credito.getIdCredito().getMatricula(), credito.getValorSaldo(), credito.getCdCliente(),
                credito.getIdCredito().getCredito(), credito.getCdServico());
        }
    }
}

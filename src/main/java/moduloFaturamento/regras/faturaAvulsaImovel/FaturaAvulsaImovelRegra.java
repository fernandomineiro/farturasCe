package moduloFaturamento.regras.faturaAvulsaImovel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.faturaAvulsaImovel.FaturaAvulsaImovelCadastrarDTO;
import moduloFaturamento.excecoes.ExcecaoRegraNegocio;
import moduloFaturamento.model.ConfiguracaoContabil;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.model.IdFatura;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.model.LancamentosDaFatura;
import moduloFaturamento.model.LocalidadeFilialSap;
import moduloFaturamento.model.SapCentroCusto;
import moduloFaturamento.model.common.Cliente;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.model.common.Localidade;
import moduloFaturamento.model.common.Servico;
import moduloFaturamento.regras.calculoFaturaDetalhada.CalculoFaturaDetalhadaRegra;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.repository.ClienteRepository;
import moduloFaturamento.repository.ConfiguracaoContabilRepository;
import moduloFaturamento.repository.CreditoRepository;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaAvulsaImovelRepository;
import moduloFaturamento.repository.LancamentosDaFaturaRepository;
import moduloFaturamento.repository.LocalidadeFilialSapRepository;
import moduloFaturamento.repository.SapCentroCustoRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.LocalidadeRepository;
import moduloFaturamento.repository.common.ServicoRepository;
import moduloFaturamento.util.ConverterData;

@Service
public class FaturaAvulsaImovelRegra extends CalculoFaturaDetalhadaRegra {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ConfiguracaoContabilRepository configuracaoContabilRepository;

	@Autowired
	private CreditoRepository creditoRepository;

	@Autowired
	private LancamentosDaFaturaRepository lancamentosDaFaturaRepository;

	@Autowired
	private CronogramaFaturaRepository cronogramaFaturaRepository;

	@Autowired
	private LocalidadeFilialSapRepository localidadeFilialSapRepository;

	@Autowired
	private SapCentroCustoRepository sapCentroCustoRepository;

	@Autowired
	private FaturaAvulsaImovelRepository faturaAvulsaImovelRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public FaturaDetalheProcessamento calcularItens(Integer volume, Integer matricula, LocalDate referencia,
			Short... cdsServicos) {

		Optional<Imovel> imovelOP = imovelRepository.findById(matricula);

		if (imovelOP.isEmpty()) {

			throw new ExcecaoRegraNegocio("Imovel não encontrado.");
		}

		Imovel imovel = imovelOP.get();

		Short cdTarifa = localidadeRepository.buscarCodigoTarifaPorCodigoCidade(imovel.getCdCidade());

		Short grupoDeConsumo = imovel.getGrupoDeConsumo();

		Integer dataReferencia = ConverterData.localDateEmIntegerDataFormatoDB(referencia);

		FaturaDetalheProcessamento detalhe = new FaturaDetalheProcessamento();

		super.construirFaixasProcessamento(dataReferencia, grupoDeConsumo, cdTarifa, detalhe);

		setarTiposServicos(detalhe, cdsServicos);

		super.calcularFaturaFaixasProcessamento(detalhe);
		super.calcularFaturaDetalheProcessamento(detalhe);

		return detalhe;
	}

	private void setarTiposServicos(FaturaDetalheProcessamento detalhe, Short... cdsServicos) {

		List<Servico> servicos = servicoRepository.findAllById(List.of(cdsServicos));

		servicos.stream().forEach(servico -> {

			if (servico.getDcServico().toLowerCase().contains("agua")) {

				detalhe.setAgua(true);
			}
			if (servico.getDcServico().toLowerCase().contains("esgoto")) {

				detalhe.setEsgoto(TRATADO);
			}
			if (servico.getDcServico().toLowerCase().contains("disp")) {

				detalhe.setEsgoto(DISPONIBILIDADE);
			}
		});
	}

	private Fatura getFaturaNormalizada(FaturaAvulsaImovelCadastrarDTO dto, Fatura fatura) {

		IdFatura idFatura = new IdFatura(dto.getMatricula(), dto.getOrigemFatura(),
				ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100);
		IdCronogramaFatura idcronogramaFatura = new IdCronogramaFatura((short) fatura.getCdCidade(), fatura.getCiclo(),
				ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100);
		CronogramaFatura cronograma = cronogramaFaturaRepository.findByIdCronogramaFatura(idcronogramaFatura);
		fatura.setIdFatura(idFatura);
		fatura.setCodigoAtendimento(0);

		fatura.setCodigoTarifa(0);
		fatura.setCriterioFatDeciJudi((short) 0);
		fatura.setCsBaixaEnvio(" ");
		fatura.setCsNotificacao(" ");
		fatura.setDescontoEsgoto(0);
		fatura.setDevedorDuvidoso("N");
		fatura.setDiaBaixa((short) 0);
		fatura.setDiasConsumo(dto.getDiasVendas());
		fatura.setDiasVenda(dto.getDiasVendas());
		fatura.setDocumentoBaixa((short) 0);
		fatura.setDtVencimento(ConverterData.localDateEmIntegerDataFormatoDB(dto.getDataVencimento()));
		fatura.setFase((short) 5);
		fatura.setFaturaEmitida("S");
		fatura.setFormaEntrega((short) 0);
		fatura.setGrupoConsumo(dto.getGrupoConsumo());
		fatura.setIdLote(0);
		fatura.setMaint("");
		fatura.setMotivoRefaturamento((short) 0);
		fatura.setPercentualTarifa((short) 0);
		fatura.setReferenciaAtendimento((short) 0);
		fatura.setReferenciaBaixa(0);
		fatura.setReferenciaBaixaContabil(0);
		fatura.setReferenciaBaixaPerdas(0);
		fatura.setReferenciaContabil(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now())/100);
		fatura.setCodigoAtendimento(0);
		fatura.setSequenciaAtendimento((short) 0);
		fatura.setTipoConsumoFaturado((short) 0);
		fatura.setTipoEntregaFatura((short) 0);
		fatura.setTipoMedicaoEsg((short) 0);
		fatura.setTipoPdd((short) 0);
		fatura.setTpBaixa((short) 0);
		fatura.setTratamentoEsgoto(dto.isTratamentoEsgoto() ? "S" : "N");
		fatura.setUsuario(dto.getUsuario());
		fatura.setValorFatura(dto.getValorTotal());
		fatura.setVersaoFatura(dto.getVersaoFatura());
		fatura.setVolumeAgua(dto.getVolumeAgua());
		fatura.setVolumeEsgoto(dto.getVolumeEsgoto());
		fatura.setVolumeDispEsg(dto.getVolumeDisponivel());
		fatura.setMatriculaImovel(dto.getMatricula());
		fatura.setOrigemFatura(dto.getOrigemFatura());
		fatura.setRefFatura(ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()));
		fatura.setDataTarifa(cronograma != null ? cronograma.getDataTarifa() : 0);
		// fatura.setIdFatura(idFatura);
		// fatura.setMatriculaImovel(dto.getMatricula());
		// fatura.setRefFatura(ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()));
		// fatura.setOrigemFatura(dto.getOrigemFatura());

		// fatura.setTpBaixa(Short.parseShort("0"));
		// fatura.setCiclo(dto.getCiclo());
		// fatura.setDtVencimento(ConverterData.localDateEmIntegerDataFormatoDB(dto.getDataVencimento()));
		// fatura.setDv(dto.getDv());
		// fatura.setValorFatura(dto.getValorTotal());
		// fatura.setFase(Short.parseShort("5"));
		// fatura.setCdCliente(fatura.getCdCliente());
		// fatura.setDiaBaixa(Short.parseShort("0"));
		// fatura.setReferenciaBaixa(0);
		// fatura.setReferenciaBaixaContabil(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
		// fatura.setReferenciaContabil(0);
		// fatura.setReferenciaAtendimento(0);
		// fatura.setCodigoAtendimento(0);
		// fatura.setSequenciaAtendimento(Short.parseShort("0"));
		// fatura.setCodigoClientePrincipal(fatura.getCdCliente());
		// fatura.setCodigoTarifa(0);
		// fatura.setDescontoEsgoto(0);
		// fatura.setDevedorDuvidoso("N");
		// fatura.setDiasConsumo(dto.getDiasVendas());
		// fatura.setDiasVenda(dto.getDiasVendas());
		// fatura.setDocumentoBaixa(Short.parseShort("0"));

		// fatura.setDataTarifa(cronograma != null ? cronograma.getDataTarifa() : 0);
		// fatura.setFaturaEmitida("S");
		// fatura.setFormaEntrega(Short.parseShort("0"));
		// fatura.setGrupoConsumo(dto.getGrupoConsumo());
		// fatura.setUsuario(dto.getUsuario());
		// fatura.setMaint("");
		// fatura.setMatriculaImovel(dto.getMatricula());
		// fatura.setOrigemFatura(Short.parseShort("0"));
		// fatura.setPercentualTarifa(0);
		// fatura.setUnidadeConsumo(0);
		// fatura.setVolumeAgua(dto.getVolumeAgua());
		// fatura.setVolumeEsgoto(dto.getVolumeEsgoto());
		// fatura.setIdLote(0);
		// fatura.setCsBaixaEnvio("");
		// fatura.setCsNotificacao("");
		// fatura.setTipoPdd(Short.parseShort("0"));
		// fatura.setMotivoRefaturamento(Short.parseShort("0"));
		// fatura.setVolumeDispEsg(dto.getVolumeDisponivel());
		// fatura.setCriterioFatDeciJudi(Short.parseShort("0"));
		// fatura.setVersaoFatura(Short.parseShort("1"));
		// fatura.setTratamentoEsgoto(dto.isTratamentoEsgoto() ? "S" : "N");
		// fatura.setTipoMedicaoEsg(Short.parseShort("0"));
		// fatura.setTipoConsumoFaturado(Short.parseShort("0"));
		// fatura.setGrupoConsumo(dto.getGrupoConsumo());
		return fatura;
	}

	public Fatura criarFatura(FaturaAvulsaImovelCadastrarDTO dto) {
		Integer matricula = dto.getMatricula();
		Imovel imovel = imovelRepository.findById(matricula).get();
		Localidade localidade = localidadeRepository.findById(imovel.getCdCidade()).get();
		Fatura fatura = new Fatura();
		fatura.setCdCidade(imovel.getCdCidade());
		
		fatura.setCiclo(imovel.getCiclo());
		fatura.setCodigoClientePrincipal(dto.getCdCliente());
		fatura.setCdCliente(dto.getCdCliente());
		fatura.setUnidadeConsumo(imovel.getUnidadeConsumo());
		fatura.setUnidadeConsumoCad(imovel.getUnidadeConsumo());
		fatura.setCodigoTarifa(localidade.getCdTarifa());
		fatura.setDv(imovel.getDv());
		// Data da tarifa,
		fatura = this.getFaturaNormalizada(dto, fatura);

		return fatura;
	}

	public Fatura criarFatura(Integer matricula) {

		Imovel imovel = imovelRepository.findById(matricula).get();
		Localidade localidade = localidadeRepository.findById(imovel.getCdCidade()).get();
		Fatura fatura = new Fatura();
		fatura.setCdCidade(imovel.getCdCidade());
		fatura.setCiclo(imovel.getCiclo());

		fatura.setFase((short) 0);
		fatura.setTpBaixa((short) 0);
		fatura.setCdCliente(imovel.getCdCliente());
		fatura.setUnidadeConsumo(imovel.getUnidadeConsumo());
		fatura.setUnidadeConsumoCad(imovel.getUnidadeConsumo());
		fatura.setCodigoTarifa(localidade.getCdTarifa());

		return fatura;
	}

	private LancamentoContabil preencheLancamentoConfiguracao(LancamentoContabil lancamentoContabil,
			ConfiguracaoContabil configuracao, Fatura faturaDB) {
		lancamentoContabil.setCdEvento(configuracao.getCdEvento().shortValue());
		lancamentoContabil.setNumeroContaContabilSAP(configuracao.getNrContaCtbSap());
		lancamentoContabil.setDebitoCreditoContabilSAP(configuracao.getdCCtbSap());
		Optional<SapCentroCusto> sapCentroCustoOp = sapCentroCustoRepository.buscarCdCustoSapPorNumeroContaContabil(
				(short) faturaDB.getCdCidade(), lancamentoContabil.getNumeroContaContabilSAP());
		if (sapCentroCustoOp.isPresent()) {
			lancamentoContabil.setCdCentroCustoSap(sapCentroCustoOp.get().getCdCcustoSap());
		} else if (configuracao.getInformarCCusto().equals("N")) {
			lancamentoContabil.setCdCentroCustoSap("");
		} else {
			throw new ExcecaoRegraNegocio("Não existe centro custo para o Número Conta Contábil SAP : "
					+ lancamentoContabil.getNumeroContaContabilSAP());
		}

		Optional<LocalidadeFilialSap> localidadeFilialSapOp = localidadeFilialSapRepository
				.buscarCdFilialSap(faturaDB.getCdCidade());

		lancamentoContabil.setCdfilialSap(localidadeFilialSapOp.get().getIdFilialSap().shortValue());

		return lancamentoContabil;
	}

	private LancamentoContabil criarLancamento(ConfiguracaoContabil configuracao, LancamentosDaFatura lancamentoFatura) {
		IdFatura idfatura = new IdFatura();

		idfatura.setMatriculaImovel(lancamentoFatura.getMatriculaImovel());
		idfatura.setOrigemFatura(lancamentoFatura.getOrigemFatura());
		idfatura.setRefFatura(lancamentoFatura.getReferenciaFatura());
		Fatura faturaDB = faturaAvulsaImovelRepository.findById(idfatura).get();
		LancamentoContabil lancamentoContabil = new LancamentoContabil();
		lancamentoContabil.setDtContabil(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
		lancamentoContabil.setDtLancamento(lancamentoFatura.getDataLancamento());
		lancamentoContabil = preencheLancamentoConfiguracao(lancamentoContabil, configuracao, faturaDB);
		lancamentoContabil.setCdServico(lancamentoFatura.getCdServico());
		lancamentoContabil.setCdCliente(faturaDB.getCdCliente());
		lancamentoContabil.setCdCredito(Short.valueOf("0"));
		lancamentoContabil.setNumeroLoteArrecadacao(0);
		lancamentoContabil.setDebitoCreditoServico(lancamentoFatura.getDebitoOuCredito());
		lancamentoContabil.setValorLancamento(lancamentoFatura.getValorServico());
		lancamentoContabil.setMatriculaImovel(faturaDB.getMatriculaImovel());
		lancamentoContabil.setRefFatura(faturaDB.getIdFatura().getRefFatura());
		lancamentoContabil.setOrigemFatura(faturaDB.getOrigemFatura());
		lancamentoContabil.setCdParcelamento(0);
		lancamentoContabil.setNumeroParcela(Short.valueOf("0"));
		
		return lancamentoContabil;
	}

	private List<ConfiguracaoContabil> getListConfiguracaoContabil(LancamentosDaFatura lancamentoFatura) {
		List<ConfiguracaoContabil> listConfiguracaoContabeis = new ArrayList<>();
		listConfiguracaoContabeis = configuracaoContabilRepository
				.findByCdServicoAndCdEventoAndDcServico(lancamentoFatura.getCdServico());
		return listConfiguracaoContabeis;

	}

	private List<LancamentoContabil> criarLancamentosContabeis(LancamentosDaFatura lancamentoFatura) {
		List<LancamentoContabil> lancamentosContabeis = new ArrayList<>();

		List<ConfiguracaoContabil> listConfiguracaoContabeis = this.getListConfiguracaoContabil(lancamentoFatura);
		if (listConfiguracaoContabeis.isEmpty()) {
			Servico servico = servicoRepository.getOne(lancamentoFatura.getCdServico());
			throw new ExcecaoRegraNegocio(
					"Não existe configuração contábil para o  serviço : "
							+ servico.getDcServico());
		}
		listConfiguracaoContabeis.stream().forEach(configuracao -> {
			LancamentoContabil lancamentoContabil = this.criarLancamento(configuracao, lancamentoFatura);
			lancamentosContabeis.add(lancamentoContabil);
		});

		return lancamentosContabeis;
	}

	public List<LancamentoContabil> criarLancamentoContabil(LancamentosDaFatura lancamentoFatura,

			FaturaAvulsaImovelCadastrarDTO dto) {

		List<LancamentoContabil> listLancamentoContabil = new ArrayList<LancamentoContabil>();

		List<LancamentoContabil> listLancamentoContabilCredito = this
				.criarLancamentosContabeis(lancamentoFatura);
		listLancamentoContabil.addAll(listLancamentoContabilCredito);

		return listLancamentoContabil;
	}

	public List<LancamentoContabil> criarLancamentosContabeis(Integer matricula, LocalDate referencia,
			FaturaDetalheProcessamento detalhe,
			Short[] cdsServicos) {

		Imovel imovel = imovelRepository.findById(matricula).get();

		Integer referenciaDB = ConverterData.localDateEmIntegerReferenciaFormatoDB(referencia);

		List<Servico> servicos = servicoRepository.findAllById(List.of(cdsServicos));

		List<LancamentoContabil> lancamentos = new ArrayList<LancamentoContabil>();

		servicos.stream().forEach(servico -> {

			if (servico.getDcServico().toLowerCase().contains("agua")) {

				BigDecimal valor = detalhe.getTotalAgua();
				List<LancamentoContabil> listLancamentoAgua = getListlancamentoContabil((short) 20, matricula, valor,
						imovel.getCdCliente(), (short) 1, servico.getCdServico(),
						"D", referenciaDB, (short) 5, 1, (short) 1, imovel.getCdCidade(), 1, "", "", 0);

				lancamentos.addAll(listLancamentoAgua);
			}
			if (servico.getDcServico().toLowerCase().contains("esgoto")) {

				BigDecimal valor = detalhe.getTotalEsgotoAtivo();
				List<LancamentoContabil> listLancamentoEsgoto = getListlancamentoContabil((short) 20, matricula, valor,
						imovel.getCdCliente(), (short) 1, servico.getCdServico(),
						"D", referenciaDB, (short) 5, 1, (short) 1, imovel.getCdCidade(), 1, "", "", 0);
				lancamentos.addAll(listLancamentoEsgoto);
			}
			if (servico.getDcServico().toLowerCase().contains("disp")) {

				BigDecimal valor = detalhe.getTotalAgua();
				List<LancamentoContabil> listLancamentoDisponibilidade = getListlancamentoContabil((short) 20,
						matricula, valor,
						imovel.getCdCliente(), (short) 1,
						servico.getCdServico(), "D", referenciaDB, (short) 5, 1, (short) 1, imovel.getCdCidade(), 1,
						"", "", 0);
				lancamentos.addAll(listLancamentoDisponibilidade);
			}
		});
		return lancamentos;
	}

	protected List<LancamentoContabil> getListlancamentoContabil(Short cdEvento, Integer matricula, BigDecimal valor,
			Integer cdCliente, Short cdCredito, Short cdServico,
			String debitoOuCredito, Integer refFatura, Short origemFatura, Integer cdParcelamento, Short numeroParcela,
			Short cdCidade, Integer nuLoteArrec, String cdCcustoSap, String debitoCreditoContabilSAP,
			Integer numeroContaContabilSAP) {

		Short cdFilialSap = imovelRepository.buscarCdFilialSap(Integer.valueOf(cdCidade)).shortValue();

		List<ConfiguracaoContabil> partidaContabil = configuracaoContabilRepository
				.buscarPorCdServicoECdEvento(cdServico, (short) 20);

		List<LancamentoContabil> lancamentoContabils = new ArrayList<LancamentoContabil>();
		for (ConfiguracaoContabil configuracao : partidaContabil) {
			LancamentoContabil contabil = new LancamentoContabil();

			if (debitoOuCredito.equals(configuracao.getdCCtbSap())) {

				contabil.setNumeroContaContabilSAP(configuracao.getNrContaCtbSap());
				contabil.setDebitoCreditoContabilSAP(configuracao.getdCCtbSap());
				contabil.setCdCentroCustoSap(
						verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(Integer.valueOf(cdCidade), cdServico,
								cdEvento, configuracao.getNrContaCtbSap()));

			}
			contabil.setDtContabil(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
			contabil.setDtLancamento(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
			contabil.setCdEvento(Short.valueOf("10"));
			contabil.setCdfilialSap(cdFilialSap);

			contabil.setValorLancamento(valor);
			contabil.setMatriculaImovel(matricula);
			contabil.setDebitoCreditoContabilSAP(debitoCreditoContabilSAP);
			contabil.setCdCliente(cdCliente);
			contabil.setCdCentroCustoSap(cdCcustoSap);
			contabil.setRefFatura(refFatura);
			contabil.setOrigemFatura(origemFatura);

			contabil.setCdParcelamento(cdParcelamento);
			contabil.setNumeroParcela(numeroParcela);

			contabil.setCdCredito(cdCredito);
			contabil.setCdServico(cdServico);

			contabil.setDebitoCreditoServico(configuracao.getdCCtbSap());
			contabil.setNumeroLoteArrecadacao(nuLoteArrec);
			contabil.setNumeroContaContabilSAP(configuracao.getNrContaCtbSap());
			Optional<Long> numeroContaCTBSAPOp = servicoRepository.buscarSeServicoPodeSerUtilizadoPelaCidade(cdCidade,
					configuracao.getNrContaCtbSap());
			if (numeroContaCTBSAPOp.isPresent()) {
				contabil.setNumeroContaContabilSAP(Integer.parseInt(numeroContaCTBSAPOp.get().toString()));
			}
			lancamentoContabils.add(contabil);
		}

		return lancamentoContabils;
	}

	private String verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(Integer cdCidade, Short cdServico,
			Short evento, Integer nrConta) {

		String buscarSeExisteUmRegristro = creditoRepository
				.verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(cdCidade, cdServico, evento, nrConta);

		return Objects.requireNonNullElse(buscarSeExisteUmRegristro, "");
	}

	private BigDecimal getValorServico(Short cdServico, Fatura faturaDB) {
		// SERVICO_CSSL_ANTECIPADA(1994),
		// SERVICO_COFINS_ANTECIPADO(1996),
		// SERVICO_PASEP_ANTECIPADO(1997);

		switch (cdServico) {
			case 1994: //
				BigDecimal desconto1 = new BigDecimal(1).divide(new BigDecimal(100));
				BigDecimal result = faturaDB.getValorFatura().multiply(desconto1);
				return result;
			case 1996:
				BigDecimal desconto2 = new BigDecimal(3).divide(new BigDecimal(100));
				BigDecimal result1 = faturaDB.getValorFatura().multiply(desconto2);
				return result1;
			case 1997:
				BigDecimal desconto3 = new BigDecimal(65).divide(new BigDecimal(10000));
				BigDecimal result2 = faturaDB.getValorFatura().multiply(desconto3);
				return result2;
			default:
				return new BigDecimal(0);
		}

	}

	private LancamentosDaFatura criarLancamentoFaturaAutomatico(FaturaAvulsaImovelCadastrarDTO dto, Servico servico,
			Long idFatura, String tipoValorFatura) {
		Fatura faturaDB = faturaAvulsaImovelRepository.buscaFaturaPorId(idFatura);
		LancamentosDaFatura lancamentosFaturaAvulsa = new LancamentosDaFatura();
		lancamentosFaturaAvulsa.setCdServico(servico.getCdServico());
		lancamentosFaturaAvulsa.setDebitoOuCredito(tipoValorFatura);
		lancamentosFaturaAvulsa.setValorServico(this.getValorServico(servico.getCdServico(), faturaDB));
		lancamentosFaturaAvulsa.setIdFatura(idFatura);
		lancamentosFaturaAvulsa.setMatriculaImovel(dto.getMatricula());
		lancamentosFaturaAvulsa.setOrigemFatura(dto.getOrigemFatura());
		lancamentosFaturaAvulsa
				.setReferenciaFatura(ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100);
		lancamentosFaturaAvulsa
				.setDataLancamento(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
		return lancamentosFaturaAvulsa;
	}

	public List<LancamentosDaFatura> gerarLancamentosAutomaticosFaturaAvulsa(FaturaAvulsaImovelCadastrarDTO dto) {
		List<LancamentosDaFatura> lancamentos = new ArrayList<LancamentosDaFatura>();
		Integer refFatura = ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100;
		short origemFatura = dto.getOrigemFatura();
		Integer matriculaSemDv = dto.getMatricula();
		Long idFatura = faturaAvulsaImovelRepository.buscarIdFatura(refFatura, origemFatura, matriculaSemDv);
		Imovel imovel = imovelRepository.getOne(dto.getMatricula());
		Cliente cliente = clienteRepository.findByCdCliente(imovel.getCdCliente()).get();

		if (cliente.getReterImpostos().equals("S")) {
			List<Servico> servicos = servicoRepository.buscarServicosParaClienteMarcadoComoReterImpostos();
			servicos.stream().forEach(servico -> {
				LancamentosDaFatura lancamentosFaturaAvulsaCredito = this.criarLancamentoFaturaAutomatico(dto, servico,
						idFatura, "C");

				lancamentos.add(lancamentosFaturaAvulsaCredito);

			});
		}

		return lancamentos;
	}

	public List<LancamentosDaFatura> criarLancamentosFaturaAvulsa(FaturaAvulsaImovelCadastrarDTO dto) {
		List<LancamentosDaFatura> lancamentos = new ArrayList<LancamentosDaFatura>();
		Integer refFatura = ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100;
		short origemFatura = dto.getOrigemFatura();
		Integer matriculaSemDv = dto.getMatricula();
		Long idFatura = faturaAvulsaImovelRepository.buscarIdFatura(refFatura, origemFatura, matriculaSemDv);
		dto.getItens().forEach(servico -> {

			LancamentosDaFatura lancamentosFaturaAvulsa = new LancamentosDaFatura();
			lancamentosFaturaAvulsa.setCdServico(servico.getCdServico());
			lancamentosFaturaAvulsa.setDebitoOuCredito(servico.getTipoValorServico());
			lancamentosFaturaAvulsa.setValorServico(servico.getValor());
			lancamentosFaturaAvulsa.setIdFatura(idFatura);
			lancamentosFaturaAvulsa.setMatriculaImovel(dto.getMatricula());
			lancamentosFaturaAvulsa.setOrigemFatura(dto.getOrigemFatura());
			lancamentosFaturaAvulsa
					.setReferenciaFatura(ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100);
			lancamentosFaturaAvulsa.setDataLancamento(ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now()));
			lancamentos.add(lancamentosFaturaAvulsa);
		});
		return lancamentos;
	}

}

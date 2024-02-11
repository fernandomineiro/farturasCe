package moduloFaturamento.regras.calculoFaturaDetalhada;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.Tarifa;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheAguaTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheAplicarConsumoVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheDisponibilidadeTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheEsgotoColetadoTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheEsgotoTratadoTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheMetragemDisponivelCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.detalhe.DetalheSomatorioFinalTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua.FaixaAguaFixoCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua.FaixaAguaTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua.FaixaAguaVariavelCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaDisponibilidade.FaixaDisponibilidadeFixoCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaDisponibilidade.FaixaDisponibilidadeTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaDisponibilidade.FaixaDisponibilidadeVariavelCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoColetado.FaixaEsgotoColetadoFixoCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoColetado.FaixaEsgotoColetadoTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoColetado.FaixaEsgotoColetadoVariavelCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoTratado.FaixaEsgotoTratadoFixoCalcularVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoTratado.FaixaEsgotoTratadoTotalizarVisitor;
import moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoTratado.FaixaEsgotoTratadoVariavelCalcularVisitor;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.repository.TarifaRepository;
import moduloFaturamento.service.FaturaService;
import moduloFaturamento.util.ConverterData;

public abstract class CalculoFaturaDetalhadaRegra {

	public static final String COLETADO = "Coletado";
	public static final String TRATADO = "Tratado";
	public static final String DISPONIBILIDADE = "Disponibilidade";

	// ---------------------------------------------------------------- Service

	@Autowired
	private FaturaService faturaService;

	// ---------------------------------------------------------------- Repositories

	@Autowired
	private FaturaRepository faturaRepository;

	@Autowired
	private LeituraRepository leituraRepository;

	@Autowired
	private TarifaRepository tarifaRepository;

	// ---------------------------------------------------------------- Visitors

	@Autowired
	private DetalheMetragemDisponivelCalcularVisitor metragemDisponivelCalculoVisitor;

	@Autowired
	private DetalheAplicarConsumoVisitor consumoAplicarVisitor;

	@Autowired
	private DetalheAguaTotalizarVisitor detalheAguaTotalizarVisitor;

	@Autowired
	private DetalheEsgotoColetadoTotalizarVisitor detalheEsgotoColetadoTotalizarVisitor;

	@Autowired
	private DetalheEsgotoTratadoTotalizarVisitor dealheEsgotoTratadoTotalizarVisitor;

	@Autowired
	private DetalheDisponibilidadeTotalizarVisitor detalheDisponibilidadeTotalizarVisitor;

	@Autowired
	private DetalheSomatorioFinalTotalizarVisitor detalheSomatorioFinalTotalizarVisitor;

	@Autowired
	private FaixaAguaVariavelCalcularVisitor faixaAguaVariavelCalcularVisitor;

	@Autowired
	private FaixaAguaFixoCalcularVisitor faixaAguaFixoCalcularVisitor;

	@Autowired
	private FaixaAguaTotalizarVisitor faixaAguaTotalizarVisitor;

	@Autowired
	private FaixaEsgotoColetadoVariavelCalcularVisitor faixaEsgotoColetadoVariavelCalcularVisitor;

	@Autowired
	private FaixaEsgotoColetadoFixoCalcularVisitor faixaEsgotoColetadoFixoCalcularVisitor;

	@Autowired
	private FaixaEsgotoColetadoTotalizarVisitor faixaEsgotoColetadoTotalizarVisitor;

	@Autowired
	private FaixaEsgotoTratadoVariavelCalcularVisitor faixaEsgotoTratadoVariavelCalcularVisitor;

	@Autowired
	private FaixaEsgotoTratadoFixoCalcularVisitor faixaEsgotoTratadoFixoCalcularVisitor;

	@Autowired
	private FaixaEsgotoTratadoTotalizarVisitor faixaEsgotoTratadoTotalizarVisitor;

	@Autowired
	private FaixaDisponibilidadeVariavelCalcularVisitor faixaDisponibilidadeVariavelCalcularVisitor;

	@Autowired
	private FaixaDisponibilidadeFixoCalcularVisitor faixaDisponibilidadeFixoCalcularVisitor;

	@Autowired
	private FaixaDisponibilidadeTotalizarVisitor faixaDisponibilidadeTotalizarVisitor;

	public Fatura buscarFatura(Integer matricula, Integer referencia) {

		return faturaRepository.buscarUltimaFaturaDoImovel(matricula, referencia);
	}

	public Leitura buscarLeitura(Integer matricula, LocalDate referencia) {

		Integer refFatura = ConverterData.localDateEmIntegerReferenciaFormatoDB(referencia);
		return leituraRepository.buscarUltimaLeituraExistentePorMatriculaEReferenciaNative(matricula, refFatura);
	}

	public FaturaSituacaoAguaEsgotoProjection buscarSituacaoAguaEsgotoPelaFatura(Integer matricula, LocalDate referencia) {

		Integer refFatura = ConverterData.localDateEmIntegerReferenciaFormatoDB(referencia);

		return faturaService.buscarFaturaSituacaoAguaEsgotoProjectionPorMatriculaReferencia(matricula, refFatura);
	}

	protected BigDecimal buscarMediaConsumoMensal(Integer matricula, LocalDate refFatura) {

		Integer referencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura);
		BigDecimal mediaMensal = leituraRepository.buscarMediaMensal(matricula, referencia);

		return mediaMensal.setScale(1, RoundingMode.HALF_EVEN);
	}

	protected BigDecimal determinarConsumoPorLeitura(Leitura leitura, Integer matricula, LocalDate referencia) {

		BigDecimal consumo = null;
		
		if (leitura.getOcorrencia() != null && leitura.getOcorrencia() != 0) {

			consumo = buscarMediaConsumoMensal(matricula, referencia);
		} else {

			consumo = BigDecimal.valueOf(leitura.getMedido());
		}

		return consumo;
	}
	
	protected Boolean determinarSituacaoAgua(FaturaSituacaoAguaEsgotoProjection situacaoAguaEsgoto) {

		return situacaoAguaEsgoto.getValorAgua() != 0;
	}

	protected String determinarSituacaoEsgoto(FaturaSituacaoAguaEsgotoProjection situacaoAguaEsgoto) {

		String esgoto = null;

		Double valorEsgoto = situacaoAguaEsgoto.getValorEsgoto() == null ? 0 : situacaoAguaEsgoto.getValorEsgoto();
		Double valorDisponibilidade = situacaoAguaEsgoto.getValorDisponibilidade() == null ? 0 : situacaoAguaEsgoto.getValorDisponibilidade();

		if ("S".equals(situacaoAguaEsgoto.getTratamentoEsgoto()) && valorEsgoto != 0) {

			esgoto = TRATADO;
		} else if ("N".equals(situacaoAguaEsgoto.getTratamentoEsgoto()) && valorEsgoto != 0) {

			esgoto = COLETADO;
		} else if (valorDisponibilidade != 0) {

			esgoto = DISPONIBILIDADE;
		}

		return esgoto;
	}

	protected FaturaDetalheProcessamento construirDetalheProcessamento(Short diasVenda, BigDecimal consumo, Boolean agua, String esgoto) {

		FaturaDetalheProcessamento detalhe = new FaturaDetalheProcessamento();
		detalhe.setConsumo(consumo);
		detalhe.setDiasVenda(BigDecimal.valueOf(diasVenda));

		detalhe.setAgua(agua == null ? false : agua);
		detalhe.setEsgoto(esgoto);

		return detalhe;
	}

	protected void construirFaixasProcessamento(Integer dataReferencia, Short grupoConsumo, Short cdTarifa, FaturaDetalheProcessamento detalhe) {

		List<Tarifa> faixasTarifarias = tarifaRepository.buscarFaixasTarifarias(dataReferencia, grupoConsumo, cdTarifa);

		List<FaturaFaixaProcessamento> faixas = faixasTarifarias.stream().map(faixa -> {

			FaturaFaixaProcessamento faixaProcessamento = new FaturaFaixaProcessamento(detalhe);

			faixaProcessamento.setMetrosPorFaixaPadrao(BigDecimal.valueOf(faixa.getId().getLimiteFaixa()));

			faixaProcessamento.setAguaTarifaFixa(faixa.getValorAguaParcelaFixa());
			faixaProcessamento.setAguaTarifaVariavel(faixa.getValorAgua());

			faixaProcessamento.setEsgotoAtivoTarifaFixa(faixa.getValorEsgotoNaoTratadoParcelaFixa());
			faixaProcessamento.setEsgotoAtivoTarifaVariavel(faixa.getValorEsgotoNaoTratado());

			faixaProcessamento.setEsgotoTratadoTarifaFixa(faixa.getValorEsgotoTratadoParcelaFixa());
			faixaProcessamento.setEsgotoTratadoTarifaVariavel(faixa.getValorEsgotoTratado());

			faixaProcessamento.setDisponibilidadeTarifaFixa(faixa.getValorDisponibilidadeEsgotoParcelaFixa());
			faixaProcessamento.setDisponibilidadeTarifaVariavel(faixa.getValorDisponibilidadeEsgoto());

			faixaProcessamento.setFaixa(1 + faixasTarifarias.indexOf(faixa));

			return faixaProcessamento;

		}).collect(Collectors.toList());

		detalhe.setFaixas(faixas);

	}

	protected void calcularFaturaFaixasProcessamento(FaturaDetalheProcessamento detalhe) {

		detalhe.accept(metragemDisponivelCalculoVisitor);

		detalhe.accept(consumoAplicarVisitor);

		if (Boolean.TRUE.equals(detalhe.getAgua())) {

			detalhe.accept(faixaAguaVariavelCalcularVisitor);
			detalhe.accept(faixaAguaFixoCalcularVisitor);
			detalhe.accept(faixaAguaTotalizarVisitor);
		}

		if (COLETADO.equals(detalhe.getEsgoto())) {

			detalhe.accept(faixaEsgotoColetadoVariavelCalcularVisitor);
			detalhe.accept(faixaEsgotoColetadoFixoCalcularVisitor);
			detalhe.accept(faixaEsgotoColetadoTotalizarVisitor);
		}

		if (TRATADO.equals(detalhe.getEsgoto())) {

			detalhe.accept(faixaEsgotoTratadoVariavelCalcularVisitor);
			detalhe.accept(faixaEsgotoTratadoFixoCalcularVisitor);
			detalhe.accept(faixaEsgotoTratadoTotalizarVisitor);
		}

		if (DISPONIBILIDADE.equals(detalhe.getEsgoto())) {

			detalhe.accept(faixaDisponibilidadeVariavelCalcularVisitor);
			detalhe.accept(faixaDisponibilidadeFixoCalcularVisitor);
			detalhe.accept(faixaDisponibilidadeTotalizarVisitor);
		}
	}

	protected void calcularFaturaDetalheProcessamento(FaturaDetalheProcessamento detalhe) {

		detalhe.accept(detalheAguaTotalizarVisitor);
		detalhe.accept(detalheEsgotoColetadoTotalizarVisitor);
		detalhe.accept(dealheEsgotoTratadoTotalizarVisitor);
		detalhe.accept(detalheDisponibilidadeTotalizarVisitor);

		detalhe.accept(detalheSomatorioFinalTotalizarVisitor);
	}
}

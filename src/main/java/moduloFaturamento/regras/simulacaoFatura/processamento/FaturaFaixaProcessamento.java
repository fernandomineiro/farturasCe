package moduloFaturamento.regras.simulacaoFatura.processamento;

import java.math.BigDecimal;

public class FaturaFaixaProcessamento {

	private final FaturaDetalheProcessamento detalhe;

	private Integer faixa;

	private BigDecimal metrosPorFaixaPadrao = BigDecimal.ZERO;
	private BigDecimal metrosPorFaixaCorrente = BigDecimal.ZERO;
	private BigDecimal metrosUsadosFaixaCorrente = BigDecimal.ZERO;

	private BigDecimal aguaTarifaVariavel = BigDecimal.ZERO;
	private BigDecimal aguaTarifaFixa = BigDecimal.ZERO;
	private BigDecimal aguaParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal aguaParcelaFixa = BigDecimal.ZERO;
	private BigDecimal aguaTotal = BigDecimal.ZERO;

	private BigDecimal esgotoAtivoTarifaVariavel = BigDecimal.ZERO;
	private BigDecimal esgotoAtivoTarifaFixa = BigDecimal.ZERO;
	private BigDecimal esgotoAtivoParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal esgotoAtivoParcelaFixa = BigDecimal.ZERO;
	private BigDecimal esgotoAtivoTotal = BigDecimal.ZERO;

	private BigDecimal esgotoTratadoTarifaVariavel = BigDecimal.ZERO;
	private BigDecimal esgotoTratadoTarifaFixa = BigDecimal.ZERO;
	private BigDecimal esgotoTratadoParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal esgotoTratadoParcelaFixa = BigDecimal.ZERO;
	private BigDecimal esgotoTratadoTotal = BigDecimal.ZERO;

	private BigDecimal disponibilidadeTarifaVariavel = BigDecimal.ZERO;
	private BigDecimal disponibilidadeTarifaFixa = BigDecimal.ZERO;
	private BigDecimal disponibilidadeParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal disponibilidadeParcelaFixa = BigDecimal.ZERO;
	private BigDecimal disponibilidadeTotal = BigDecimal.ZERO;

	public FaturaFaixaProcessamento(FaturaDetalheProcessamento detalhe) {

		super();
		this.detalhe = detalhe;
	}

	public Integer getFaixa() {

		return faixa;
	}

	public void setFaixa(Integer faixa) {

		this.faixa = faixa;
	}

	public BigDecimal getMetrosPorFaixaPadrao() {

		return metrosPorFaixaPadrao;
	}

	public void setMetrosPorFaixaPadrao(BigDecimal metrosPorFaixaPadrao) {

		this.metrosPorFaixaPadrao = metrosPorFaixaPadrao;
	}

	public BigDecimal getMetrosPorFaixaCorrente() {

		return metrosPorFaixaCorrente;
	}

	public void setMetrosPorFaixaCorrente(BigDecimal metrosPorFaixaCorrente) {

		this.metrosPorFaixaCorrente = metrosPorFaixaCorrente;
	}

	public BigDecimal getMetrosUsadosFaixaCorrente() {

		return metrosUsadosFaixaCorrente;
	}

	public void setMetrosUsadosFaixaCorrente(BigDecimal metrosUsadosFaixaCorrente) {

		this.metrosUsadosFaixaCorrente = metrosUsadosFaixaCorrente;
	}

	public BigDecimal getAguaTarifaVariavel() {

		return aguaTarifaVariavel;
	}

	public void setAguaTarifaVariavel(BigDecimal aguaTarifaVariavel) {

		this.aguaTarifaVariavel = aguaTarifaVariavel;
	}

	public BigDecimal getAguaTarifaFixa() {

		return aguaTarifaFixa;
	}

	public void setAguaTarifaFixa(BigDecimal aguaTarifaFixa) {

		this.aguaTarifaFixa = aguaTarifaFixa;
	}

	public BigDecimal getAguaParcelaVariavel() {

		return aguaParcelaVariavel;
	}

	public void setAguaParcelaVariavel(BigDecimal aguaParcelaVariavel) {

		this.aguaParcelaVariavel = aguaParcelaVariavel;
	}

	public BigDecimal getAguaParcelaFixa() {

		return aguaParcelaFixa;
	}

	public void setAguaParcelaFixa(BigDecimal aguaParcelaFixa) {

		this.aguaParcelaFixa = aguaParcelaFixa;
	}

	public BigDecimal getAguaTotal() {

		return aguaTotal;
	}

	public void setAguaTotal(BigDecimal aguaTotal) {

		this.aguaTotal = aguaTotal;
	}

	public BigDecimal getEsgotoAtivoTarifaVariavel() {

		return esgotoAtivoTarifaVariavel;
	}

	public void setEsgotoAtivoTarifaVariavel(BigDecimal esgotoAtivoTarifaVariavel) {

		this.esgotoAtivoTarifaVariavel = esgotoAtivoTarifaVariavel;
	}

	public BigDecimal getEsgotoAtivoTarifaFixa() {

		return esgotoAtivoTarifaFixa;
	}

	public void setEsgotoAtivoTarifaFixa(BigDecimal esgotoAtivoTarifaFixa) {

		this.esgotoAtivoTarifaFixa = esgotoAtivoTarifaFixa;
	}

	public BigDecimal getEsgotoAtivoParcelaVariavel() {

		return esgotoAtivoParcelaVariavel;
	}

	public void setEsgotoAtivoParcelaVariavel(BigDecimal esgotoAtivoParcelaVariavel) {

		this.esgotoAtivoParcelaVariavel = esgotoAtivoParcelaVariavel;
	}

	public BigDecimal getEsgotoAtivoParcelaFixa() {

		return esgotoAtivoParcelaFixa;
	}

	public void setEsgotoAtivoParcelaFixa(BigDecimal esgotoAtivoParcelaFixa) {

		this.esgotoAtivoParcelaFixa = esgotoAtivoParcelaFixa;
	}

	public BigDecimal getEsgotoAtivoTotal() {

		return esgotoAtivoTotal;
	}

	public void setEsgotoAtivoTotal(BigDecimal esgotoAtivoTotal) {

		this.esgotoAtivoTotal = esgotoAtivoTotal;
	}

	public BigDecimal getEsgotoTratadoTarifaVariavel() {

		return esgotoTratadoTarifaVariavel;
	}

	public void setEsgotoTratadoTarifaVariavel(BigDecimal esgotoTratadoTarifaVariavel) {

		this.esgotoTratadoTarifaVariavel = esgotoTratadoTarifaVariavel;
	}

	public BigDecimal getEsgotoTratadoTarifaFixa() {

		return esgotoTratadoTarifaFixa;
	}

	public void setEsgotoTratadoTarifaFixa(BigDecimal esgotoTratadoTarifaFixa) {

		this.esgotoTratadoTarifaFixa = esgotoTratadoTarifaFixa;
	}

	public BigDecimal getEsgotoTratadoParcelaVariavel() {

		return esgotoTratadoParcelaVariavel;
	}

	public void setEsgotoTratadoParcelaVariavel(BigDecimal esgotoTratadoParcelaVariavel) {

		this.esgotoTratadoParcelaVariavel = esgotoTratadoParcelaVariavel;
	}

	public BigDecimal getEsgotoTratadoParcelaFixa() {

		return esgotoTratadoParcelaFixa;
	}

	public void setEsgotoTratadoParcelaFixa(BigDecimal esgotoTratadoParcelaFixa) {

		this.esgotoTratadoParcelaFixa = esgotoTratadoParcelaFixa;
	}

	public BigDecimal getEsgotoTratadoTotal() {

		return esgotoTratadoTotal;
	}

	public void setEsgotoTratadoTotal(BigDecimal esgotoTratadoTotal) {

		this.esgotoTratadoTotal = esgotoTratadoTotal;
	}

	public BigDecimal getDisponibilidadeTarifaVariavel() {

		return disponibilidadeTarifaVariavel;
	}

	public void setDisponibilidadeTarifaVariavel(BigDecimal disponibilidadeTarifaVariavel) {

		this.disponibilidadeTarifaVariavel = disponibilidadeTarifaVariavel;
	}

	public BigDecimal getDisponibilidadeTarifaFixa() {

		return disponibilidadeTarifaFixa;
	}

	public void setDisponibilidadeTarifaFixa(BigDecimal disponibilidadeTarifaFixa) {

		this.disponibilidadeTarifaFixa = disponibilidadeTarifaFixa;
	}

	public BigDecimal getDisponibilidadeParcelaVariavel() {

		return disponibilidadeParcelaVariavel;
	}

	public void setDisponibilidadeParcelaVariavel(BigDecimal disponibilidadeParcelaVariavel) {

		this.disponibilidadeParcelaVariavel = disponibilidadeParcelaVariavel;
	}

	public BigDecimal getDisponibilidadeParcelaFixa() {

		return disponibilidadeParcelaFixa;
	}

	public void setDisponibilidadeParcelaFixa(BigDecimal disponibilidadeParcelaFixa) {

		this.disponibilidadeParcelaFixa = disponibilidadeParcelaFixa;
	}

	public BigDecimal getDisponibilidadeTotal() {

		return disponibilidadeTotal;
	}

	public void setDisponibilidadeTotal(BigDecimal disponibilidadeTotal) {

		this.disponibilidadeTotal = disponibilidadeTotal;
	}

	public FaturaDetalheProcessamento getDetalhe() {

		return detalhe;
	}

}

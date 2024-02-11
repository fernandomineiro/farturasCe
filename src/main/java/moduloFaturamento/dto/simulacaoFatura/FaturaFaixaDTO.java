package moduloFaturamento.dto.simulacaoFatura;

import java.math.BigDecimal;

public class FaturaFaixaDTO {

	private Integer faixa;

	private BigDecimal metrosPorFaixa = BigDecimal.ZERO;
	private BigDecimal metrosUsadosFaixa = BigDecimal.ZERO;

	private BigDecimal aguaParcelaFixa = BigDecimal.ZERO;
	private BigDecimal aguaParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal aguaTotal = BigDecimal.ZERO;

	private BigDecimal esgotoParcelaFixa = BigDecimal.ZERO;
	private BigDecimal esgotoParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal esgotoTotal = BigDecimal.ZERO;

	private BigDecimal disponibilidadeParcelaFixa = BigDecimal.ZERO;
	private BigDecimal disponibilidadeParcelaVariavel = BigDecimal.ZERO;
	private BigDecimal disponibilidadeTotal = BigDecimal.ZERO;

	public Integer getFaixa() {

		return faixa;
	}

	public void setFaixa(Integer faixa) {

		this.faixa = faixa;
	}

	public BigDecimal getMetrosPorFaixa() {

		return metrosPorFaixa;
	}

	public void setMetrosPorFaixa(BigDecimal metrosPorFaixa) {

		this.metrosPorFaixa = metrosPorFaixa;
	}

	public BigDecimal getMetrosUsadosFaixa() {

		return metrosUsadosFaixa;
	}

	public void setMetrosUsadosFaixa(BigDecimal metrosUsadosFaixa) {

		this.metrosUsadosFaixa = metrosUsadosFaixa;
	}

	public BigDecimal getAguaParcelaFixa() {

		return aguaParcelaFixa;
	}

	public void setAguaParcelaFixa(BigDecimal aguaParcelaFixa) {

		this.aguaParcelaFixa = aguaParcelaFixa;
	}

	public BigDecimal getAguaParcelaVariavel() {

		return aguaParcelaVariavel;
	}

	public void setAguaParcelaVariavel(BigDecimal aguaParcelaVariavel) {

		this.aguaParcelaVariavel = aguaParcelaVariavel;
	}

	public BigDecimal getAguaTotal() {

		return aguaTotal;
	}

	public void setAguaTotal(BigDecimal aguaTotal) {

		this.aguaTotal = aguaTotal;
	}

	public BigDecimal getEsgotoParcelaFixa() {

		return esgotoParcelaFixa;
	}

	public void setEsgotoParcelaFixa(BigDecimal esgotoParcelaFixa) {

		this.esgotoParcelaFixa = esgotoParcelaFixa;
	}

	public BigDecimal getEsgotoParcelaVariavel() {

		return esgotoParcelaVariavel;
	}

	public void setEsgotoParcelaVariavel(BigDecimal esgotoParcelaVariavel) {

		this.esgotoParcelaVariavel = esgotoParcelaVariavel;
	}

	public BigDecimal getEsgotoTotal() {

		return esgotoTotal;
	}

	public void setEsgotoTotal(BigDecimal esgotoTotal) {

		this.esgotoTotal = esgotoTotal;
	}

	public BigDecimal getDisponibilidadeParcelaFixa() {

		return disponibilidadeParcelaFixa;
	}

	public void setDisponibilidadeParcelaFixa(BigDecimal disponibilidadeParcelaFixa) {

		this.disponibilidadeParcelaFixa = disponibilidadeParcelaFixa;
	}

	public BigDecimal getDisponibilidadeParcelaVariavel() {

		return disponibilidadeParcelaVariavel;
	}

	public void setDisponibilidadeParcelaVariavel(BigDecimal disponibilidadeParcelaVariavel) {

		this.disponibilidadeParcelaVariavel = disponibilidadeParcelaVariavel;
	}

	public BigDecimal getDisponibilidadeTotal() {

		return disponibilidadeTotal;
	}

	public void setDisponibilidadeTotal(BigDecimal disponibilidadeTotal) {

		this.disponibilidadeTotal = disponibilidadeTotal;
	}

}
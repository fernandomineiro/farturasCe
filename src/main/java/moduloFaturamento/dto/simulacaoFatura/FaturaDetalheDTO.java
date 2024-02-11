package moduloFaturamento.dto.simulacaoFatura;

import java.math.BigDecimal;
import java.util.List;

public class FaturaDetalheDTO {

	private List<FaturaFaixaDTO> faixas;

	private BigDecimal totalMetrosUsadosFaixa;
	private BigDecimal totalAgua;
	private BigDecimal totalEsgoto;
	private BigDecimal totalDisponibilidade;
	private BigDecimal totalTotalFatura;

	public List<FaturaFaixaDTO> getFaixas() {

		return faixas;
	}

	public void setFaixas(List<FaturaFaixaDTO> faixas) {

		this.faixas = faixas;
	}

	public BigDecimal getTotalMetrosUsadosFaixa() {

		return totalMetrosUsadosFaixa;
	}

	public void setTotalMetrosUsadosFaixa(BigDecimal totalMetrosUsadosFaixa) {

		this.totalMetrosUsadosFaixa = totalMetrosUsadosFaixa;
	}

	public BigDecimal getTotalAgua() {

		return totalAgua;
	}

	public void setTotalAgua(BigDecimal totalAgua) {

		this.totalAgua = totalAgua;
	}

	public BigDecimal getTotalEsgoto() {

		return totalEsgoto;
	}

	public void setTotalEsgoto(BigDecimal totalEsgoto) {

		this.totalEsgoto = totalEsgoto;
	}

	public BigDecimal getTotalDisponibilidade() {

		return totalDisponibilidade;
	}

	public void setTotalDisponibilidade(BigDecimal totalDisponibilidade) {

		this.totalDisponibilidade = totalDisponibilidade;
	}

	public BigDecimal getTotalTotalFatura() {

		return totalTotalFatura;
	}

	public void setTotalTotalFatura(BigDecimal totalTotalFatura) {

		this.totalTotalFatura = totalTotalFatura;
	}

}

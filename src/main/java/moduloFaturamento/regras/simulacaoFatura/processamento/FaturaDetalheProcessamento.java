package moduloFaturamento.regras.simulacaoFatura.processamento;

import java.math.BigDecimal;
import java.util.List;

import moduloFaturamento.designPattern.visitor.CesanVisitee;
import moduloFaturamento.designPattern.visitor.CesanVisitor;

public class FaturaDetalheProcessamento implements CesanVisitee<FaturaDetalheProcessamento> {

	private List<FaturaFaixaProcessamento> faixas;

	private BigDecimal diasVenda = BigDecimal.ZERO;
	private BigDecimal consumo = BigDecimal.ZERO;

	private Boolean agua = Boolean.TRUE;
	private String esgoto = "Tratado";

	private BigDecimal totalAgua = BigDecimal.ZERO;
	private BigDecimal totalEsgotoAtivo = BigDecimal.ZERO;
	private BigDecimal totalEsgotoTratado = BigDecimal.ZERO;
	private BigDecimal totalDisponibilidade = BigDecimal.ZERO;
	private BigDecimal totalFatura = BigDecimal.ZERO;

	public List<FaturaFaixaProcessamento> getFaixas() {

		return faixas;
	}

	public void setFaixas(List<FaturaFaixaProcessamento> faixas) {

		this.faixas = faixas;
	}

	public BigDecimal getDiasVenda() {

		return diasVenda;
	}

	public void setDiasVenda(BigDecimal diasVenda) {

		this.diasVenda = diasVenda;
	}

	public BigDecimal getConsumo() {

		return consumo;
	}

	public void setConsumo(BigDecimal consumo) {

		this.consumo = consumo;
	}

	public Boolean getAgua() {

		return agua;
	}

	public void setAgua(Boolean agua) {

		this.agua = agua;
	}

	public String getEsgoto() {

		return esgoto;
	}

	public void setEsgoto(String esgoto) {

		this.esgoto = esgoto;
	}

	public BigDecimal getTotalAgua() {

		return totalAgua;
	}

	public void setTotalAgua(BigDecimal totalAgua) {

		this.totalAgua = totalAgua;
	}

	public BigDecimal getTotalEsgotoAtivo() {

		return totalEsgotoAtivo;
	}

	public void setTotalEsgotoAtivo(BigDecimal totalEsgotoAtivo) {

		this.totalEsgotoAtivo = totalEsgotoAtivo;
	}

	public BigDecimal getTotalEsgotoTratado() {

		return totalEsgotoTratado;
	}

	public void setTotalEsgotoTratado(BigDecimal totalEsgotoTratado) {

		this.totalEsgotoTratado = totalEsgotoTratado;
	}

	public BigDecimal getTotalDisponibilidade() {

		return totalDisponibilidade;
	}

	public void setTotalDisponibilidade(BigDecimal totalDisponibilidade) {

		this.totalDisponibilidade = totalDisponibilidade;
	}

	public BigDecimal getTotalFatura() {

		return totalFatura;
	}

	public void setTotalFatura(BigDecimal totalFatura) {

		this.totalFatura = totalFatura;
	}

	@Override
	public void accept(CesanVisitor<FaturaDetalheProcessamento> visitor) {

		visitor.visit(this);
	}
}

package moduloFaturamento.dto.faturaAvulsaImovel;

import java.math.BigDecimal;

public class FaturaAvulsaImovelServicoItem {

	private Short cdServico;
	private BigDecimal valor;
	private String tipoServico;
	private String tipoValorServico;
	
	public Short getCdServico() {

		return cdServico;
	}

	public String getTipoValorServico() {
		return tipoValorServico;
	}

	public void setTipoValorServico(String tipoValorServico) {
		this.tipoValorServico = tipoValorServico;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public void setCdServico(Short cdServico) {

		this.cdServico = cdServico;
	}

	public BigDecimal getValor() {

		return valor;
	}

	public void setValor(BigDecimal valor) {

		this.valor = valor;
	}

}

package moduloFaturamento.dto.mensgemFatura;

import javax.validation.constraints.NotNull;

public class MensagemFaturaRemocaoDTO {

	@NotNull
	private Short ciclo;
	@NotNull
	private Short cdCidade;
	@NotNull
	private Integer referencia;

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Integer getReferencia() {

		return referencia;
	}

	public void setReferencia(Integer referencia) {

		this.referencia = referencia;
	}
}

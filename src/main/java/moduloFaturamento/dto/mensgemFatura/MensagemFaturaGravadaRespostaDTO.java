package moduloFaturamento.dto.mensgemFatura;

public class MensagemFaturaGravadaRespostaDTO {

	private Short cdCidade;

	private Short ciclo;

	private Integer referencia;

	private String linha01;

	private String linha02;

	private boolean cicloFechado;
	
	public Short getCdCidade() {

		return cdCidade;
	}


	public boolean isCicloFechado() {
		return cicloFechado;
	}


	public void setCicloFechado(boolean cicloFechado) {
		this.cicloFechado = cicloFechado;
	}


	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Integer getReferencia() {

		return referencia;
	}

	public void setReferencia(Integer referencia) {

		this.referencia = referencia;
	}

	public String getLinha01() {

		return linha01;
	}

	public void setLinha01(String linha01) {

		this.linha01 = linha01;
	}

	public String getLinha02() {

		return linha02;
	}

	public void setLinha02(String linha02) {

		this.linha02 = linha02;
	}

}

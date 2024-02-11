package moduloFaturamento.dto.mensgemFatura;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MensagemFaturaAlteracaoDTO {
	
	@NotNull
	private Short ciclo;
	@NotNull
	private Short cdCidade;
	@NotNull
	private Integer referencia;
	@NotNull
	@Size(min = 1, max = 50)
	private String linha01;
	@Size(min = 0, max = 50)
	private String linha02;

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

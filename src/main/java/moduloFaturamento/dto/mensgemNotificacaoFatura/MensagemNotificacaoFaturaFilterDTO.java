package moduloFaturamento.dto.mensgemNotificacaoFatura;

public class MensagemNotificacaoFaturaFilterDTO {

	private Integer refCronograma;

	private Short cdCidade;

	private Short ciclo;

	private Integer matricula;

	public Integer getRefCronograma() {

		return refCronograma;
	}

	public void setRefCronograma(Integer refCronograma) {

		this.refCronograma = refCronograma;
	}

	public Short getCdCidade() {

		return cdCidade;
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

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

}

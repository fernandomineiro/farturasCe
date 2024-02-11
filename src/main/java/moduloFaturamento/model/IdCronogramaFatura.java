package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdCronogramaFatura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CD_CIDADE")
	private Short cdCidade;

	@Column(name = "CICLO")
	private Short ciclo;

	@Column(name = "REF_CRONOGRAMA")
	private Integer refCronograma;

	public IdCronogramaFatura() {
	}

	public IdCronogramaFatura(Short cdCidade, Short ciclo, Integer refCronograma) {
		this.cdCidade = cdCidade;
		this.ciclo = ciclo;
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

	public Integer getRefCronograma() {

		return refCronograma;
	}

	public void setRefCronograma(Integer refCronograma) {

		this.refCronograma = refCronograma;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		result = prime * result + ((ciclo == null) ? 0 : ciclo.hashCode());
		result = prime * result + ((refCronograma == null) ? 0 : refCronograma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdCronogramaFatura other = (IdCronogramaFatura) obj;
		if (cdCidade == null) {

			if (other.cdCidade != null)
				return false;
		} else if (!cdCidade.equals(other.cdCidade))
			return false;
		if (ciclo == null) {

			if (other.ciclo != null)
				return false;
		} else if (!ciclo.equals(other.ciclo))
			return false;
		if (refCronograma == null) {

			if (other.refCronograma != null)
				return false;
		} else if (!refCronograma.equals(other.refCronograma))
			return false;
		return true;
	}

}

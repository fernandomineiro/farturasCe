package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdAmostrasRealizadas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	@Column(name = "REF_AMOSTRAS")
	private Integer refAmostras;

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Integer getRefAmostras() {

		return refAmostras;
	}

	public void setRefAmostras(Integer refAmostras) {

		this.refAmostras = refAmostras;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		result = prime * result + ((refAmostras == null) ? 0 : refAmostras.hashCode());
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
		IdAmostrasRealizadas other = (IdAmostrasRealizadas) obj;
		if (cdCidade == null) {

			if (other.cdCidade != null)
				return false;
		} else if (!cdCidade.equals(other.cdCidade))
			return false;
		if (refAmostras == null) {

			if (other.refAmostras != null)
				return false;
		} else if (!refAmostras.equals(other.refAmostras))
			return false;
		return true;
	}

}

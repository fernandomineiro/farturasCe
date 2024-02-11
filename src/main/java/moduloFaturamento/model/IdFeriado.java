package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdFeriado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	@Column(name = "DT_FERIADO", updatable = false)
	private Integer dtFeriado;

	public IdFeriado() {

	}

	public IdFeriado(Short cdCidade, Integer dtFeriado) {

		this.cdCidade = cdCidade;
		this.dtFeriado = dtFeriado;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Integer getDtFeriado() {

		return dtFeriado;
	}

	public void setDtFeriado(Integer dtFeriado) {

		this.dtFeriado = dtFeriado;
	}

	@Override
	public String toString() {

		return "{'cdCidade':" + this.cdCidade.toString() + ",'dtFeriado':" + this.dtFeriado.toString() + "}";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		result = prime * result + ((dtFeriado == null) ? 0 : dtFeriado.hashCode());
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
		IdFeriado other = (IdFeriado) obj;
		if (cdCidade == null) {

			if (other.cdCidade != null)
				return false;
		} else if (!cdCidade.equals(other.cdCidade))
			return false;
		if (dtFeriado == null) {

			if (other.dtFeriado != null)
				return false;
		} else if (!dtFeriado.equals(other.dtFeriado))
			return false;
		return true;
	}

}

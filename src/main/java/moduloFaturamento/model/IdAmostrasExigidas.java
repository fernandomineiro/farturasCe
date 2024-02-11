package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdAmostrasExigidas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	@Column(name = "DT_INICIO")
	private Integer dtInicio;

	public IdAmostrasExigidas() {

	}

	public IdAmostrasExigidas(Short cdCidade, Integer dtInicio) {

		this.cdCidade = cdCidade;
		this.dtInicio = dtInicio;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Integer getDtInicio() {

		return dtInicio;
	}

	public void setDtInicio(Integer dtInicio) {

		this.dtInicio = dtInicio;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
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
		IdAmostrasExigidas other = (IdAmostrasExigidas) obj;
		if (cdCidade == null) {

			if (other.cdCidade != null)
				return false;
		} else if (!cdCidade.equals(other.cdCidade))
			return false;
		if (dtInicio == null) {

			if (other.dtInicio != null)
				return false;
		} else if (!dtInicio.equals(other.dtInicio))
			return false;
		return true;
	}

}

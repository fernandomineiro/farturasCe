package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdMensagemFatura implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "CD_CIDADE")
	private Short cdCidade;
	@Column(name = "CICLO")
	private Short ciclo;
	@Column(name = "REF_FATURA")
	private Integer refFatura;

	public IdMensagemFatura() {

	}

	public IdMensagemFatura(Short cdCidade, Short ciclo, Integer refFatura) {

		super();
		this.cdCidade = cdCidade;
		this.ciclo = ciclo;
		this.refFatura = refFatura;
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

	public Integer getRefFatura() {

		return refFatura;
	}

	public void setRefFatura(Integer refFatura) {

		this.refFatura = refFatura;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCidade == null) ? 0 : cdCidade.hashCode());
		result = prime * result + ((ciclo == null) ? 0 : ciclo.hashCode());
		result = prime * result + ((refFatura == null) ? 0 : refFatura.hashCode());
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
		IdMensagemFatura other = (IdMensagemFatura) obj;
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
		if (refFatura == null) {

			if (other.refFatura != null)
				return false;
		} else if (!refFatura.equals(other.refFatura))
			return false;
		return true;
	}

}

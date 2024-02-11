package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import moduloFaturamento.util.ConvertObjectToJsonCesan;

@Embeddable
public class IdFatura implements Serializable {


	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;
	@Column(name = "ORIGEM_FATURA")
	private Short origemFatura;
	@Column(name = "REF_FATURA")
	private Integer refFatura;

	public IdFatura() {
	}
	
	public IdFatura(Integer matriculaImovel, Short origemFatura, Integer refFatura) {
		this.matriculaImovel = matriculaImovel;
		this.origemFatura = origemFatura;
		this.refFatura = refFatura;
	}

	public Integer getMatriculaImovel() {

		return matriculaImovel;
	}

	public void setMatriculaImovel(Integer matriculaImovel) {

		this.matriculaImovel = matriculaImovel;
	}

	public Short getOrigemFatura() {

		return origemFatura;
	}

	public void setOrigemFatura(Short origemFatura) {

		this.origemFatura = origemFatura;
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
		result = prime * result + ((matriculaImovel == null) ? 0 : matriculaImovel.hashCode());
		result = prime * result + ((origemFatura == null) ? 0 : origemFatura.hashCode());
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
		IdFatura other = (IdFatura) obj;
		if (matriculaImovel == null) {

			if (other.matriculaImovel != null)
				return false;
		} else if (!matriculaImovel.equals(other.matriculaImovel))
			return false;
		if (origemFatura == null) {

			if (other.origemFatura != null)
				return false;
		} else if (!origemFatura.equals(other.origemFatura))
			return false;
		if (refFatura == null) {

			if (other.refFatura != null)
				return false;
		} else if (!refFatura.equals(other.refFatura))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return ConvertObjectToJsonCesan.execute(this);
    }
}

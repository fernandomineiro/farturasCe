package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdDiasVencimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CICLO")
	private Short ciclo;

	@Column(name = "DIA")
	private Short dia;

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Short getDia() {

		return dia;
	}

	public void setDia(Short dia) {

		this.dia = dia;
	}

	public IdDiasVencimento() {

	}

	public IdDiasVencimento(Short ciclo, Short dia) {

		this.ciclo = ciclo;
		this.dia = dia;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciclo == null) ? 0 : ciclo.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
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
		IdDiasVencimento other = (IdDiasVencimento) obj;
		if (ciclo == null) {

			if (other.ciclo != null)
				return false;
		} else if (!ciclo.equals(other.ciclo))
			return false;
		if (dia == null) {

			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		return true;
	}
}

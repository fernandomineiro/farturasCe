package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CDTCD")
public class DiasVencimento {

	@EmbeddedId
	private IdDiasVencimento idDiasVencimentoFaturamento;
	
	@Column(name = "MAINT")
	private String maint;

	public IdDiasVencimento getIdDiasVencimentoFaturamento() {
		return idDiasVencimentoFaturamento;
	}

	public void setIdDiasVencimentoFaturamento(IdDiasVencimento idDiasVencimentoFaturamento) {
		this.idDiasVencimentoFaturamento = idDiasVencimentoFaturamento;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public DiasVencimento(IdDiasVencimento idDiasVencimentoFaturamento, String maint) {
		this.idDiasVencimentoFaturamento = idDiasVencimentoFaturamento;
		this.maint = maint;
	}

	public DiasVencimento() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDiasVencimentoFaturamento == null) ? 0 : idDiasVencimentoFaturamento.hashCode());
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
		DiasVencimento other = (DiasVencimento) obj;
		if (idDiasVencimentoFaturamento == null) {
			if (other.idDiasVencimentoFaturamento != null)
				return false;
		} else if (!idDiasVencimentoFaturamento.equals(other.idDiasVencimentoFaturamento))
			return false;
		return true;
	}

	
	
}

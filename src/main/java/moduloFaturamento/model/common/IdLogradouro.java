package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdLogradouro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CD_LOGRADOURO")
	private Short codLogradouro;

	@Column(name = "CD_CIDADE")
	private Short codLocalidade;

	public IdLogradouro() {

	}

	public IdLogradouro(Short codLogradouro, Short codLocalidade) {
		super();
		this.codLogradouro = codLogradouro;
		this.codLocalidade = codLocalidade;
	}

	public Short getCodLogradouro() {
		return codLogradouro;
	}

	public void setCodLogradouro(Short codLogradouro) {
		this.codLogradouro = codLogradouro;
	}

	public Short getCodLocalidade() {
		return codLocalidade;
	}

	public void setCodLocalidade(Short codLocalidade) {
		this.codLocalidade = codLocalidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codLocalidade == null) ? 0 : codLocalidade.hashCode());
		result = prime * result + ((codLogradouro == null) ? 0 : codLogradouro.hashCode());
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
		IdLogradouro other = (IdLogradouro) obj;
		if (codLocalidade == null) {
			if (other.codLocalidade != null)
				return false;
		} else if (!codLocalidade.equals(other.codLocalidade))
			return false;
		if (codLogradouro == null) {
			return other.codLogradouro == null;
		} else return codLogradouro.equals(other.codLogradouro);
	}

}

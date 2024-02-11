package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdTarifa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CD_TARIFA")
	private Integer idTarifa;

	@Column(name = "DT_TARIFA")
	private Integer dataTarifa;

	@Column(name = "GRUPO_CONSUMO")
	private Integer grupoConsumo;

	@Column(name = "LIMITE_FAIXA")
	private Integer limiteFaixa;

	public IdTarifa() {

	}

	public IdTarifa(Integer idTarifa, Integer dataTarifa, Integer grupoConsumo, Integer limiteFaixa) {

		this.idTarifa = idTarifa;
		this.dataTarifa = dataTarifa;
		this.grupoConsumo = grupoConsumo;
		this.limiteFaixa = limiteFaixa;
	}

	public Integer getIdTarifa() {

		return idTarifa;
	}

	public void setIdTarifa(Integer idTarifa) {

		this.idTarifa = idTarifa;
	}

	public Integer getDataTarifa() {

		return dataTarifa;
	}

	public void setDataTarifa(Integer dataTarifa) {

		this.dataTarifa = dataTarifa;
	}

	public Integer getGrupoConsumo() {

		return grupoConsumo;
	}

	public void setGrupoConsumo(Integer grupoConsumo) {

		this.grupoConsumo = grupoConsumo;
	}

	public Integer getLimiteFaixa() {

		return limiteFaixa;
	}

	public void setLimiteFaixa(Integer limiteFaixa) {

		this.limiteFaixa = limiteFaixa;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataTarifa == null) ? 0 : dataTarifa.hashCode());
		result = prime * result + ((grupoConsumo == null) ? 0 : grupoConsumo.hashCode());
		result = prime * result + ((idTarifa == null) ? 0 : idTarifa.hashCode());
		result = prime * result + ((limiteFaixa == null) ? 0 : limiteFaixa.hashCode());
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
		IdTarifa other = (IdTarifa) obj;
		if (dataTarifa == null) {

			if (other.dataTarifa != null)
				return false;
		} else if (!dataTarifa.equals(other.dataTarifa))
			return false;
		if (grupoConsumo == null) {

			if (other.grupoConsumo != null)
				return false;
		} else if (!grupoConsumo.equals(other.grupoConsumo))
			return false;
		if (idTarifa == null) {

			if (other.idTarifa != null)
				return false;
		} else if (!idTarifa.equals(other.idTarifa))
			return false;
		if (limiteFaixa == null) {

			if (other.limiteFaixa != null)
				return false;
		} else if (!limiteFaixa.equals(other.limiteFaixa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdTarifa [dataTarifa=" + dataTarifa + ", grupoConsumo=" + grupoConsumo + ", idTarifa=" + idTarifa
				+ ", limiteFaixa=" + limiteFaixa + "]";
	}

	
}

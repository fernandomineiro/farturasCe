package moduloFaturamento.model.common;

import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name ="CDARE")
public class Regional {
	
	@Id
	@Column(name = "CD_REGIONAL")
	private Integer codRegional;
	
	@Column(name = "DC_REGIONAL")
	private String nomeRegional;
	
	@Column(name = "MAINT")
	@JsonCesanNotSerializable
	private String status;
	
	@Column(name = "DATA_HORA_EXCLUSAO")
	@JsonCesanNotSerializable
	private Instant dataRemocao;
	

	public Integer getCodRegional() {
		return codRegional;
	}

	public void setCodRegional(Integer codRegional) {
		this.codRegional = codRegional;
	}

	public String getNomeRegional() {
		return nomeRegional;
	}

	public void setNomeRegional(String nomeRegional) {
		this.nomeRegional = nomeRegional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Instant getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Instant dataRemoção) {
		this.dataRemocao = dataRemoção;
	}
	
	public String toJson() {
		return ConvertObjectToJsonCesan.execute(this);	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codRegional == null) ? 0 : codRegional.hashCode());
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
		Regional other = (Regional) obj;
		if (codRegional == null) {
			if (other.codRegional != null)
				return false;
		} else if (!codRegional.equals(other.codRegional))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "codRegional";
	}

}

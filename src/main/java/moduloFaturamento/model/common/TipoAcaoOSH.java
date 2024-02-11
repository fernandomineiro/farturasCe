package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SRV_TP_ACAO_PARA_OSH")
public class TipoAcaoOSH {

	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="DESCRICAO") @Size(max=100)
	private String descricao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return id.toString();
	}

}

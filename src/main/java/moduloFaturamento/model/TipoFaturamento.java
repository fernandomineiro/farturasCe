package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoFaturamento {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DESCRICAO")
	private Integer descricao;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getDescricao() {

		return descricao;
	}

	public void setDescricao(Integer descricao) {

		this.descricao = descricao;
	}

}

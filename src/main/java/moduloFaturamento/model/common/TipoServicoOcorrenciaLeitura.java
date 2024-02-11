package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SRV_TP_SERVICO_OCORRENCIA_LEITURA")
public class TipoServicoOcorrenciaLeitura {

	@Id
	@Column(name="ID") @Size(max=1)
	private String id;
	@Column(name="DESCRICAO") @Size(max=100)
	private String descricao;

	public TipoServicoOcorrenciaLeitura() {
	}

	public TipoServicoOcorrenciaLeitura(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return id;
	}

}

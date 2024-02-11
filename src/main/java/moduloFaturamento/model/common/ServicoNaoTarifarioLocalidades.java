package moduloFaturamento.model.common;

import javax.persistence.*;

@Entity
@Table(name="SRV_SERVICO_NAO_TARIFARIO_LOCALIDADE")
public class ServicoNaoTarifarioLocalidades {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="CD_SRV_NT", updatable = false, insertable = false)
	private ServicoNaoTarifado srvNaoTarifado;
	@ManyToOne
	@JoinColumn(name="CD_LOCALIDADE", updatable = false, insertable = false)
	private Localidade localidade;
	@Column(name="CD_LOCALIDADE")
	private Short cdLocalidade;
	
	public ServicoNaoTarifado getSrvNaoTarifado() {
		return srvNaoTarifado;
	}
	public void setSrvNaoTarifado(ServicoNaoTarifado srvNaoTarifado) {
		this.srvNaoTarifado = srvNaoTarifado;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Short getCdLocalidade() {
		return cdLocalidade;
	}
	public void setCdLocalidade(Short cdLocalidade) {
		this.cdLocalidade = cdLocalidade;
	}
}

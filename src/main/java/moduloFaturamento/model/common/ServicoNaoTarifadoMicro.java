package moduloFaturamento.model.common;

import javax.persistence.*;

@Entity
@Table(name="SRV_SERVICO_NAO_TARIFARIO_MICRO")
public class ServicoNaoTarifadoMicro {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "CD_SERVICO", updatable = false, insertable = false)
	private Servico servico;
	@ManyToOne
	@JoinColumn(name="CD_SRV_NT", updatable = false, insertable = false)
	private ServicoNaoTarifado servicoNaoTarifado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public ServicoNaoTarifado getServicoNaoTarifado() {
		return servicoNaoTarifado;
	}
	public void setServicoNaoTarifado(ServicoNaoTarifado servicoNaoTarifado) {
		this.servicoNaoTarifado = servicoNaoTarifado;
	}

}

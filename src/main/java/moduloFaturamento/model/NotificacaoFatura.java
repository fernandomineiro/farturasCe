package moduloFaturamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_NOTIFICACAO_FATURA")
public class NotificacaoFatura {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "REF_FATURA")
	private Integer refFatura;
	@Column(name = "MENSAGEM")
	private String mensagem;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "notificacaoFatura")
	private List<NotificacaoFaturaMatricula> notificacaoFaturaMatriculas;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer getRefFatura() {

		return refFatura;
	}

	public void setRefFatura(Integer refFatura) {

		this.refFatura = refFatura;
	}

	public String getMensagem() {

		return mensagem;
	}

	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}

	public List<NotificacaoFaturaMatricula> getNotificacaoFaturaMatriculas() {

		return notificacaoFaturaMatriculas;
	}

	public void setNotificacaoFaturaMatriculas(List<NotificacaoFaturaMatricula> notificacaoFaturaMatriculas) {

		this.notificacaoFaturaMatriculas = notificacaoFaturaMatriculas;
	}

}

package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_NOTIFICACAO_FATURA_MATRICULA")
public class NotificacaoFaturaMatricula {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "FAT_NOTIFICACAO_FATURA_ID")
	private NotificacaoFatura notificacaoFatura;
	@Column(name = "MATRICULA")
	private Integer matricula;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public NotificacaoFatura getNotificacaoFatura() {

		return notificacaoFatura;
	}

	public void setNotificacaoFatura(NotificacaoFatura notificacaoFatura) {

		this.notificacaoFatura = notificacaoFatura;
	}

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

}

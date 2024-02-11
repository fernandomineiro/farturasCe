package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SRV_USUARIO")
public class Usuario {

	@Id
	private Long id;
	@Column(length = 50)
	private String nome;
	@Column(length = 50)
	private String login;
	@Column(length = 1)
	private String statusReg;

	public Usuario() {

	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(Long id, String nome, String login, String statusReg) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.statusReg = statusReg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getStatusReg() {
		return statusReg;
	}

	public void setStatusReg(String statusReg) {
		this.statusReg = statusReg;
	}

	@Override
	public String toString() {
		return this.id.toString();
	}

}

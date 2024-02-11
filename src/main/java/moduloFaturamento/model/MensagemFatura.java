package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTAME")
public class MensagemFatura {

	@EmbeddedId
	private IdMensagemFatura idMensagemFatura;

	@Column(name = "MAINT")
	private String maint;

	@Column(name = "MENSAGEM_01")
	private String mensagem01;

	@Column(name = "MENSAGEM_02")
	private String mensagem02;

	public IdMensagemFatura getIdMensagemFatura() {

		return idMensagemFatura;
	}

	public void setIdMensagemFatura(IdMensagemFatura idMensagemFatura) {

		this.idMensagemFatura = idMensagemFatura;
	}

	public String getMaint() {

		return maint;
	}

	public void setMaint(String maint) {

		this.maint = maint;
	}

	public String getMensagem01() {

		return mensagem01;
	}

	public void setMensagem01(String mensagem01) {

		this.mensagem01 = mensagem01;
	}

	public String getMensagem02() {

		return mensagem02;
	}

	public void setMensagem02(String mensagem02) {

		this.mensagem02 = mensagem02;
	}
}

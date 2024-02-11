package moduloFaturamento.dto.notificaoFatura;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NotificacaoFaturaAlteracaoDTO {

	@NotNull
	private Long Id;

	@NotEmpty
	private String mensagem;

	public Long getId() {

		return Id;
	}

	public void setId(Long id) {

		Id = id;
	}

	public String getMensagem() {

		return mensagem;
	}

	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}
}

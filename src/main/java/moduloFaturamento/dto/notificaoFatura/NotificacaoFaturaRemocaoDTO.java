package moduloFaturamento.dto.notificaoFatura;

import javax.validation.constraints.NotNull;

public class NotificacaoFaturaRemocaoDTO {

	@NotNull
	private Long id;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}
}

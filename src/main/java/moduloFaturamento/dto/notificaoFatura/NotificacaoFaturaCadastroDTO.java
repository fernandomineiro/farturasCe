package moduloFaturamento.dto.notificaoFatura;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotificacaoFaturaCadastroDTO {

	@NotNull
	private Integer referencia;

	@NotEmpty
	private List<Integer> matriculas;

	@NotNull
	@Size(min = 1, max = 1080)
	private String mensagem;

	public Integer getReferencia() {

		return referencia;
	}

	public void setReferencia(Integer referencia) {

		this.referencia = referencia;
	}

	public List<Integer> getMatriculas() {

		return matriculas;
	}

	public void setMatriculas(List<Integer> matriculas) {

		this.matriculas = matriculas;
	}

	public String getMensagem() {

		return mensagem;
	}

	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}

}

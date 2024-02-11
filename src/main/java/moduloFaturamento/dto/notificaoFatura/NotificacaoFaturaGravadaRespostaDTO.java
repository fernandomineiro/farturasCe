package moduloFaturamento.dto.notificaoFatura;

import java.util.List;

public class NotificacaoFaturaGravadaRespostaDTO {

	private Long Id;

	private Integer referencia;

	private List<Integer> matriculas;

	private String mensagem;

	private Boolean cicloFechado;

	public Long getId() {

		return Id;
	}

	public Boolean getCicloFechado() {

		return cicloFechado;
	}

	public void setCicloFechado(Boolean cicloFechado) {

		this.cicloFechado = cicloFechado;
	}

	public void setId(Long id) {

		Id = id;
	}

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

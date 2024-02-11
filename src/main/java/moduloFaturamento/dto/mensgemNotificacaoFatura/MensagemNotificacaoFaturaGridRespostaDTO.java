package moduloFaturamento.dto.mensgemNotificacaoFatura;

public class MensagemNotificacaoFaturaGridRespostaDTO {

	private Long idNotificacao;
	private Integer refCronograma;
	private Short ciclo;
	private Short cdCidade;
	private String dcCidade;
	private String mensagem;
	private Boolean flagMensagemLonga;
	private Boolean cicloFechado;

	public Long getIdNotificacao() {

		return idNotificacao;
	}

	public void setIdNotificacao(Long idNotificacao) {

		this.idNotificacao = idNotificacao;
	}

	public Integer getRefCronograma() {

		return refCronograma;
	}

	public void setRefCronograma(Integer refCronograma) {

		this.refCronograma = refCronograma;
	}

	public Short getCiclo() {

		return ciclo;
	}

	public void setCiclo(Short ciclo) {

		this.ciclo = ciclo;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public String getDcCidade() {

		return dcCidade;
	}

	public void setDcCidade(String dcCidade) {

		this.dcCidade = dcCidade;
	}

	public String getMensagem() {

		return mensagem;
	}

	public void setMensagem(String mensagem) {

		this.mensagem = mensagem;
	}

	public Boolean getFlagMensagemLonga() {

		return flagMensagemLonga;
	}

	public void setFlagMensagemLonga(Boolean flagMensagemLonga) {

		this.flagMensagemLonga = flagMensagemLonga;
	}

	public Boolean getCicloFechado() {

		return cicloFechado;
	}

	public void setCicloFechado(Boolean cicloFechado) {

		this.cicloFechado = cicloFechado;
	}

}

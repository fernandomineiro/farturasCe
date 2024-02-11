package moduloFaturamento.dto.notificaoFatura.projection;

public interface MensagemNotificacaoFaturaGridProjectionDTO {

	Long getIdNotificacao();

	Integer getRefCronograma();

	Short getCiclo();

	Short getCdCidade();

	String getDcCidade();

	String getMensagem();

	String getFlagMensagemLonga();

	String getCicloFechado();
}

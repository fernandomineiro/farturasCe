package moduloFaturamento.dto.notificaoFatura.projection;

public interface NotificacaoFaturaCriticaGridProjectionDTO {

	Short getCdCidade();

	String getDcCidade();

	Short getCiclo();

	Integer getReferencia();

	Integer getMatricula();
	
	Integer getDV();

	String getCicloFechado();

	String getExisteOutraNotificacao();
}

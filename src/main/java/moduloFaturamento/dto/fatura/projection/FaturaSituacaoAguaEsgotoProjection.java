package moduloFaturamento.dto.fatura.projection;

public interface FaturaSituacaoAguaEsgotoProjection {
	
	String getTratamentoEsgoto();
	
	Double getValorAgua();
	
	Double getValorEsgoto();
	
	Double getValorDisponibilidade();
	
}

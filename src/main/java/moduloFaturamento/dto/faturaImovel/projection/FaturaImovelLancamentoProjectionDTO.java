package moduloFaturamento.dto.faturaImovel.projection;

import java.math.BigDecimal;

public interface FaturaImovelLancamentoProjectionDTO {

	Short getCodigoServico();

	BigDecimal getValorServico();

	String getCreditoDebito();
	
	String getDcServico();
}

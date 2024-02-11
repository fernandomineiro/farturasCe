package moduloFaturamento.dto.faturaAvulsa.projection;

import java.math.BigDecimal;

public interface FaturaAvulsaLancamentosProjectionDTO {
    
    Short getCdServico();
    String getDescricaoServico();
    BigDecimal getValorServico();
    
}

package moduloFaturamento.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoLancadoDTO {

    private LocalDate data;
    private BigDecimal valorLancado;
    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public BigDecimal getValorLancado() {
        return valorLancado;
    }
    public void setValorLancado(BigDecimal valorLancado) {
        this.valorLancado = valorLancado;
    }

    
    
}

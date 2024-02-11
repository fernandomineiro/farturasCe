package moduloFaturamento.dto.leituraConsumoImovel;

import java.math.BigDecimal;

public class LeituraConsumoImovelFaixaConsumoAnaliseDTO {
    private final BigDecimal valorMaximo;
    private final BigDecimal valorMinimo;

    public LeituraConsumoImovelFaixaConsumoAnaliseDTO(BigDecimal valorMaximo, BigDecimal valorMinimo) {
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }

    public BigDecimal getValorMinimo() {
        return valorMinimo;
    }

    public BigDecimal getValorMaximo() {
        return valorMaximo;
    }
}

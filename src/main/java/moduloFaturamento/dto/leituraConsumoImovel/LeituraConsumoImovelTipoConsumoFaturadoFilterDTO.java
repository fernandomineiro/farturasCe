package moduloFaturamento.dto.leituraConsumoImovel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LeituraConsumoImovelTipoConsumoFaturadoFilterDTO {
    @NotNull
    private Integer consumoMedido;
    @NotNull
    private Short diasDeVenda;
    @NotNull
    private BigDecimal mediaDiaria;

    public Integer getConsumoMedido() {
        return consumoMedido;
    }

    public void setConsumoMedido(Integer consumoMedido) {
        this.consumoMedido = consumoMedido;
    }

    public Short getDiasDeVenda() {
        return diasDeVenda;
    }

    public void setDiasDeVenda(Short diasDeVenda) {
        this.diasDeVenda = diasDeVenda;
    }

    public BigDecimal getMediaDiaria() {
        return mediaDiaria;
    }

    public void setMediaDiaria(BigDecimal mediaDiaria) {
        this.mediaDiaria = mediaDiaria;
    }
}

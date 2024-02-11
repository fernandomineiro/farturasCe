package moduloFaturamento.dto.leituraConsumoImovel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LeituraConsumoImovelConsumoFaturadoFilterDTO {
    @NotNull
    private Integer valorLeituraInformada;
    @NotNull
    private Short diasDeVenda;
    @NotNull
    private BigDecimal mediaDiaria;

    public Integer getValorLeituraInformada() {
        return valorLeituraInformada;
    }

    public void setValorLeituraInformada(Integer valorLeituraInformada) {
        this.valorLeituraInformada = valorLeituraInformada;
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

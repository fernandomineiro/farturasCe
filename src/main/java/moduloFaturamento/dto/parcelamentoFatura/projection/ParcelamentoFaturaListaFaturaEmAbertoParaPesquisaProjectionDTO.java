package moduloFaturamento.dto.parcelamentoFatura.projection;

import java.math.BigDecimal;

public interface ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO {

    Integer getRefFatura();
    Short getOrigemFatura();
    String getGrupoDeConsumo();

    BigDecimal getValorFatura();
}
package moduloFaturamento.dto.parcelamentoFatura.projection;

import java.math.BigDecimal;

public interface ParcelamentoFaturaListaFaturaEmAbertoParaSimulacaoParcelamentoProjectionDTO {

    BigDecimal getPrincipal();
    float getValorAgua();
    float getValorEsgoto();
    float getOutrosServicos();


}

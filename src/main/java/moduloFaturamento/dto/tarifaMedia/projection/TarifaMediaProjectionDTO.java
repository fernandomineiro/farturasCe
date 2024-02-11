package moduloFaturamento.dto.tarifaMedia.projection;

import java.math.BigDecimal;

public interface TarifaMediaProjectionDTO {

    Short getIdGrupoConsumo();

    BigDecimal getValorMedio();

    BigDecimal getMediaAgua();

    BigDecimal getMediaEsgoto();

    BigDecimal getMediaDisponibilidadeEsgoto();
}

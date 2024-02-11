package moduloFaturamento.dto.tarifaMedia.projection;

import java.math.BigDecimal;

public interface TarifaMediaCalcularPorGrupoServicoProjectionDTO {
    
    Short getRegional();
    Short getGrupoDeConsumo();
    Integer getMatriculas();
    BigDecimal getSomaMatriculas();
    BigDecimal getMedia();
}

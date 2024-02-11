package moduloFaturamento.dto.tarifa.projection;

import java.math.BigDecimal;

public interface PesquisaTarifaProjectionDTO {
    
    Short getId();
    Integer getDataT();
    Short getGrupo();
    Integer getLimite();

    BigDecimal getAgua();

    BigDecimal getVrEsgotoTratado();
    BigDecimal getVrEsgotoNaoTratado();

    BigDecimal getVrDispEsgoto();

    BigDecimal getVrAguaPfixa();
    BigDecimal getVrEsgotoNaoTratadoPfixa();
    BigDecimal getVrEsgotoTratatoPFixo();
    BigDecimal getVrDispEsgotoPfixo();
    
}

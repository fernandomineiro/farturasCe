package moduloFaturamento.dto.faturaAvulsa.projection;

import java.time.LocalDateTime;

public interface FaturaAvulsaPesquisaLoteArrecadacaoProjectionDTO {

    Integer getAgenteArrecadador();
    Integer getAgenciaArredadora();
    LocalDateTime getDataProcedimentoDeArrecadacao();
    Integer getNumeroLote();
    
}

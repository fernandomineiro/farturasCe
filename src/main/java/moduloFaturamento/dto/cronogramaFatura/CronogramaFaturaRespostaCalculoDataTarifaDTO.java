package moduloFaturamento.dto.cronogramaFatura;

import java.time.LocalDate;

public class CronogramaFaturaRespostaCalculoDataTarifaDTO {
    private LocalDate dataAprovacaoTarifa;

    public LocalDate getDataAprovacaoTarifa() {
        return dataAprovacaoTarifa;
    }

    public void setDataAprovacaoTarifa(LocalDate dataAprovacaoTarifa) {
        this.dataAprovacaoTarifa = dataAprovacaoTarifa;
    }
}

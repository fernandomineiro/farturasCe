package moduloFaturamento.dto.cronogramaFatura;

import java.time.LocalDate;

public class CronogramaFaturaRespostaCalculoDataVencimentoDTO {
    private LocalDate dataVencimento;

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

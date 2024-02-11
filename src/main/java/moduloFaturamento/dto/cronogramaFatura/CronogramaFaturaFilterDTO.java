package moduloFaturamento.dto.cronogramaFatura;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CronogramaFaturaFilterDTO {
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReferencia;
    private Short cdCidade;
    private Short ciclo;

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public Short getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Short cdCidade) {
        this.cdCidade = cdCidade;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }
}

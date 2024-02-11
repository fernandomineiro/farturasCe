package moduloFaturamento.dto.cronogramaFatura;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CronogramaFaturaDataTarifaDTO {
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReferencia;
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataLeitura;
    @NotNull
    private Short ciclo;
    private Short cdCidade;

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public LocalDate getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(LocalDate dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public Short getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Short cdCidade) {
        this.cdCidade = cdCidade;
    }
}

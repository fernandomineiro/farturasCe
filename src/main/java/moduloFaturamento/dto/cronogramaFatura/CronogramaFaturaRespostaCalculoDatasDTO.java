package moduloFaturamento.dto.cronogramaFatura;

import java.time.LocalDate;

public class CronogramaFaturaRespostaCalculoDatasDTO {
    private LocalDate dataArquivoLeituraPrevista;
    private LocalDate dataFaturamentoPrevista;
    private LocalDate dataConsolidaPrevista;
    private LocalDate dataEmissaoPrevista;
    private LocalDate dataCortePrevista;

    public LocalDate getDataArquivoLeituraPrevista() {
        return dataArquivoLeituraPrevista;
    }

    public void setDataArquivoLeituraPrevista(LocalDate dataArquivoLeituraPrevista) {
        this.dataArquivoLeituraPrevista = dataArquivoLeituraPrevista;
    }

    public LocalDate getDataFaturamentoPrevista() {
        return dataFaturamentoPrevista;
    }

    public void setDataFaturamentoPrevista(LocalDate dataFaturamentoPrevista) {
        this.dataFaturamentoPrevista = dataFaturamentoPrevista;
    }

    public LocalDate getDataConsolidaPrevista() {
        return dataConsolidaPrevista;
    }

    public void setDataConsolidaPrevista(LocalDate dataConsolidaPrevista) {
        this.dataConsolidaPrevista = dataConsolidaPrevista;
    }

    public LocalDate getDataEmissaoPrevista() {
        return dataEmissaoPrevista;
    }

    public void setDataEmissaoPrevista(LocalDate dataEmissaoPrevista) {
        this.dataEmissaoPrevista = dataEmissaoPrevista;
    }

    public LocalDate getDataCortePrevista() {
        return dataCortePrevista;
    }

    public void setDataCortePrevista(LocalDate dataCortePrevista) {
        this.dataCortePrevista = dataCortePrevista;
    }
}

package moduloFaturamento.dto.cronogramaFatura.projection;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;

public class CronogramaFaturaRespostaGridProjectionDTO {
    private Long id;
    private LocalDate dataReferencia;
    private String localidade;
    private Short ciclo;
    private String nomeFase;
    private LocalDate dataVencimento;
    private LocalDate dataAprovacaoTarifa;
    private LocalDate dataGeracaoArquivoPrevista;
    private LocalDate dataGeracaoArquivoRealizada;
    private LocalDate dataLeituraPrevista;
    private LocalDate dataLeituraRealizada;
    private LocalDate dataFaturamentoPrevista;
    private LocalDate dataFaturamentoRealizada;
    private LocalDate dataConsolidacaoPrevista;
    private LocalDate dataConsolidacaoRealizada;
    private LocalDate dataEmissaPrevista;
    private LocalDate dataEmissaoRealizada;
    private LocalDate dataGeracaoArquivoCortePrevista;
    private LocalDate dataGeracaoArquivoCorteRealizada;

    public CronogramaFaturaRespostaGridProjectionDTO() {
    }

    public CronogramaFaturaRespostaGridProjectionDTO(Long id,Integer dataReferencia, String localidade, Short ciclo, Short idFase, String nomeFase,
                                                     Integer dataVencimento, Integer dataAprovacaoTarifa, Integer dataGeracaoArquivoPrevista,
                                                     Integer dataGeracaoArquivoRealizada, Integer dataLeituraPrevista, Integer dataLeituraRealizada,
                                                     Integer dataFaturamentoPrevista, Integer dataFaturamentoRealizada,
                                                     Integer dataConsolidacaoPrevista, Integer dataConsolidacaoRealizada,
                                                     Integer dataEmissaPrevista, Integer dataEmissaoRealizada, Integer dataGeracaoArquivoCortePrevista, Integer dataGeracaoArquivoCorteRealizada) {
        this.id = id;
        this.dataReferencia = ConverterData.integerEmLocalDateFormatoDB(dataReferencia);
        this.localidade = localidade;
        this.ciclo = ciclo;
        this.nomeFase = idFase+" - "+nomeFase;
        this.dataVencimento = ConverterData.integerEmLocalDateFormatoDB(dataVencimento);
        this.dataAprovacaoTarifa = ConverterData.integerEmLocalDateFormatoDB(dataAprovacaoTarifa);
        this.dataGeracaoArquivoPrevista = ConverterData.integerEmLocalDateFormatoDB(dataGeracaoArquivoPrevista);
        this.dataGeracaoArquivoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataGeracaoArquivoRealizada);
        this.dataLeituraPrevista = ConverterData.integerEmLocalDateFormatoDB(dataLeituraPrevista);
        this.dataLeituraRealizada = ConverterData.integerEmLocalDateFormatoDB(dataLeituraRealizada);
        this.dataFaturamentoPrevista = ConverterData.integerEmLocalDateFormatoDB(dataFaturamentoPrevista);
        this.dataFaturamentoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataFaturamentoRealizada);
        this.dataConsolidacaoPrevista = ConverterData.integerEmLocalDateFormatoDB(dataConsolidacaoPrevista);
        this.dataConsolidacaoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataConsolidacaoRealizada);
        this.dataEmissaPrevista = ConverterData.integerEmLocalDateFormatoDB(dataEmissaPrevista);
        this.dataEmissaoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataEmissaoRealizada);
        this.dataGeracaoArquivoCortePrevista = ConverterData.integerEmLocalDateFormatoDB(dataGeracaoArquivoCortePrevista);
        this.dataGeracaoArquivoCorteRealizada = ConverterData.integerEmLocalDateFormatoDB(dataGeracaoArquivoCorteRealizada);
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public String getLocalidade() {
        return localidade;
    }

    public Short getCiclo() {
        return ciclo;
    }

    public String getNomeFase() {
        return nomeFase;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataAprovacaoTarifa() {
        return dataAprovacaoTarifa;
    }

    public LocalDate getDataGeracaoArquivoPrevista() {
        return dataGeracaoArquivoPrevista;
    }

    public LocalDate getDataGeracaoArquivoRealizada() {
        return dataGeracaoArquivoRealizada;
    }

    public LocalDate getDataLeituraPrevista() {
        return dataLeituraPrevista;
    }

    public LocalDate getDataLeituraRealizada() {
        return dataLeituraRealizada;
    }

    public LocalDate getDataFaturamentoPrevista() {
        return dataFaturamentoPrevista;
    }

    public LocalDate getDataFaturamentoRealizada() {
        return dataFaturamentoRealizada;
    }

    public LocalDate getDataConsolidacaoPrevista() {
        return dataConsolidacaoPrevista;
    }

    public LocalDate getDataConsolidacaoRealizada() {
        return dataConsolidacaoRealizada;
    }

    public LocalDate getDataEmissaPrevista() {
        return dataEmissaPrevista;
    }

    public LocalDate getDataEmissaoRealizada() {
        return dataEmissaoRealizada;
    }

    public LocalDate getDataGeracaoArquivoCortePrevista() {
        return dataGeracaoArquivoCortePrevista;
    }

    public LocalDate getDataGeracaoArquivoCorteRealizada() {
        return dataGeracaoArquivoCorteRealizada;
    }

    public Long getId() {
        return id;
    }
}

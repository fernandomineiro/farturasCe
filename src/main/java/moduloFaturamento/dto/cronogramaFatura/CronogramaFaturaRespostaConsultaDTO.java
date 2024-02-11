package moduloFaturamento.dto.cronogramaFatura;

import java.time.LocalDate;

public class CronogramaFaturaRespostaConsultaDTO {
    private Long id;
    private Short cdCidade;
    private Short ciclo;
    private LocalDate dataReferencia;
    private LocalDate dataVencimento;
    private LocalDate dataAprovacaoTarifa;
    private LocalDate dataGeraArquivoPrevista;
    private LocalDate dataGeraArquivoRealizada;
    private LocalDate dataLeituraPrevista;
    private LocalDate dataLeituraRealizada;
    private LocalDate dataFaturamentoPrevista;
    private LocalDate dataFaturamentoRealizada;
    private LocalDate dataConsolidaPrevista;
    private LocalDate dataConsolidaRealizada;
    private LocalDate dataEmissaoPrevista;
    private LocalDate dataEmissaoRealizada;
    private LocalDate dataCortePrevista;
    private LocalDate dataCorteRealizada;
    private Short numeroFase;
    private String nomeFase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataAprovacaoTarifa() {
        return dataAprovacaoTarifa;
    }

    public void setDataAprovacaoTarifa(LocalDate dataAprovacaoTarifa) {
        this.dataAprovacaoTarifa = dataAprovacaoTarifa;
    }

    public LocalDate getDataGeraArquivoPrevista() {
        return dataGeraArquivoPrevista;
    }

    public void setDataGeraArquivoPrevista(LocalDate dataGeraArquivoPrevista) {
        this.dataGeraArquivoPrevista = dataGeraArquivoPrevista;
    }

    public LocalDate getDataGeraArquivoRealizada() {
        return dataGeraArquivoRealizada;
    }

    public void setDataGeraArquivoRealizada(LocalDate dataGeraArquivoRealizada) {
        this.dataGeraArquivoRealizada = dataGeraArquivoRealizada;
    }

    public LocalDate getDataLeituraPrevista() {
        return dataLeituraPrevista;
    }

    public void setDataLeituraPrevista(LocalDate dataLeituraPrevista) {
        this.dataLeituraPrevista = dataLeituraPrevista;
    }

    public LocalDate getDataLeituraRealizada() {
        return dataLeituraRealizada;
    }

    public void setDataLeituraRealizada(LocalDate dataLeituraRealizada) {
        this.dataLeituraRealizada = dataLeituraRealizada;
    }

    public LocalDate getDataFaturamentoPrevista() {
        return dataFaturamentoPrevista;
    }

    public void setDataFaturamentoPrevista(LocalDate dataFaturamentoPrevista) {
        this.dataFaturamentoPrevista = dataFaturamentoPrevista;
    }

    public LocalDate getDataFaturamentoRealizada() {
        return dataFaturamentoRealizada;
    }

    public void setDataFaturamentoRealizada(LocalDate dataFaturamentoRealizada) {
        this.dataFaturamentoRealizada = dataFaturamentoRealizada;
    }

    public LocalDate getDataConsolidaPrevista() {
        return dataConsolidaPrevista;
    }

    public void setDataConsolidaPrevista(LocalDate dataConsolidaPrevista) {
        this.dataConsolidaPrevista = dataConsolidaPrevista;
    }

    public LocalDate getDataConsolidaRealizada() {
        return dataConsolidaRealizada;
    }

    public void setDataConsolidaRealizada(LocalDate dataConsolidaRealizada) {
        this.dataConsolidaRealizada = dataConsolidaRealizada;
    }

    public LocalDate getDataEmissaoPrevista() {
        return dataEmissaoPrevista;
    }

    public void setDataEmissaoPrevista(LocalDate dataEmissaoPrevista) {
        this.dataEmissaoPrevista = dataEmissaoPrevista;
    }

    public LocalDate getDataEmissaoRealizada() {
        return dataEmissaoRealizada;
    }

    public void setDataEmissaoRealizada(LocalDate dataEmissaoRealizada) {
        this.dataEmissaoRealizada = dataEmissaoRealizada;
    }

    public LocalDate getDataCortePrevista() {
        return dataCortePrevista;
    }

    public void setDataCortePrevista(LocalDate dataCortePrevista) {
        this.dataCortePrevista = dataCortePrevista;
    }

    public LocalDate getDataCorteRealizada() {
        return dataCorteRealizada;
    }

    public void setDataCorteRealizada(LocalDate dataCorteRealizada) {
        this.dataCorteRealizada = dataCorteRealizada;
    }

    public String getNomeFase() {
        return nomeFase;
    }

    public void setNomeFase(String nomeFase) {
        this.nomeFase = nomeFase;
    }

    public Short getNumeroFase() {
        return numeroFase;
    }

    public void setNumeroFase(Short numeroFase) {
        this.numeroFase = numeroFase;
    }
}

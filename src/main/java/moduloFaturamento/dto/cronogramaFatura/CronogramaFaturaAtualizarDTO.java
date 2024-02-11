package moduloFaturamento.dto.cronogramaFatura;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CronogramaFaturaAtualizarDTO {
    @NotNull
    private Long id;
    @NotNull
    private Short cdCidade;
    @NotNull
    private Short ciclo;
    @NotNull
    private LocalDate dataReferencia;
    @NotNull
    private LocalDate aprovacaoTarifa;
    @NotNull
    private LocalDate vencimentoFatura;
    @NotNull
    private LocalDate geraArquivoPrevista;
    @NotNull
    private LocalDate leituraPrevista;
    @NotNull
    private LocalDate faturamentoPrevista;
    @NotNull
    private LocalDate consolidaPrevista;
    @NotNull
    private LocalDate emissaoPrevista;
    @NotNull
    private LocalDate cortePrevista;
    private boolean retornarFase;
    private boolean avancarFase;

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

    public LocalDate getAprovacaoTarifa() {
        return aprovacaoTarifa;
    }

    public void setAprovacaoTarifa(LocalDate aprovacaoTarifa) {
        this.aprovacaoTarifa = aprovacaoTarifa;
    }

    public LocalDate getVencimentoFatura() {
        return vencimentoFatura;
    }

    public void setVencimentoFatura(LocalDate vencimentoFatura) {
        this.vencimentoFatura = vencimentoFatura;
    }

    public LocalDate getGeraArquivoPrevista() {
        return geraArquivoPrevista;
    }

    public void setGeraArquivoPrevista(LocalDate geraArquivoPrevista) {
        this.geraArquivoPrevista = geraArquivoPrevista;
    }

    public LocalDate getLeituraPrevista() {
        return leituraPrevista;
    }

    public void setLeituraPrevista(LocalDate leituraPrevista) {
        this.leituraPrevista = leituraPrevista;
    }

    public LocalDate getFaturamentoPrevista() {
        return faturamentoPrevista;
    }

    public void setFaturamentoPrevista(LocalDate faturamentoPrevista) {
        this.faturamentoPrevista = faturamentoPrevista;
    }

    public LocalDate getConsolidaPrevista() {
        return consolidaPrevista;
    }

    public void setConsolidaPrevista(LocalDate consolidaPrevista) {
        this.consolidaPrevista = consolidaPrevista;
    }

    public LocalDate getEmissaoPrevista() {
        return emissaoPrevista;
    }

    public void setEmissaoPrevista(LocalDate emissaoPrevista) {
        this.emissaoPrevista = emissaoPrevista;
    }

    public LocalDate getCortePrevista() {
        return cortePrevista;
    }

    public void setCortePrevista(LocalDate cortePrevista) {
        this.cortePrevista = cortePrevista;
    }

    public boolean getRetornarFase() {
        return retornarFase;
    }

    public void setRetornarFase(boolean retornarFase) {
        this.retornarFase = retornarFase;
    }

    public boolean getAvancarFase() {
        return avancarFase;
    }

    public void setAvancarFase(boolean avancarFase) {
        this.avancarFase = avancarFase;
    }
}

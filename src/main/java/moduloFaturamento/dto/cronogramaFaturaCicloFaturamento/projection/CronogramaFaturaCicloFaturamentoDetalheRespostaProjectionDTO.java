package moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;

public class CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO {
    private final Long id;
    private final LocalDate dataReferencia;
    private final Short idLocalidade;
    private final String localidade;
    private final Short ciclo;
    private final LocalDate dataLeituraRealizada;
    private final LocalDate dataFaturamentoRealizada;
    private final LocalDate dataConsolidacaoRealizada;
    private Integer fase0QuantidadeFaturas;
    private Integer fase1QuantidadeFaturas;
    private Integer fase2QuantidadeFaturas;
    private Integer fase3QuantidadeFaturas;
    private Integer fase4QuantidadeFaturas;
    private Integer fase5QuantidadeFaturas;





    public CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO(Long id, Integer dataReferencia, Short idLocalidade, String localidade, Short ciclo, Integer dataLeituraRealizada,
                                                                        Integer dataFaturamentoRealizada, Integer dataConsolidacaoRealizada) {
        this.id = id;
        this.dataReferencia = ConverterData.integerEmLocalDateFormatoDB(dataReferencia);
        this.idLocalidade = idLocalidade;
        this.localidade = localidade;
        this.ciclo = ciclo;
        this.dataLeituraRealizada = ConverterData.integerEmLocalDateFormatoDB(dataLeituraRealizada);
        this.dataFaturamentoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataFaturamentoRealizada);
        this.dataConsolidacaoRealizada = ConverterData.integerEmLocalDateFormatoDB(dataConsolidacaoRealizada);
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

    public Long getId() {
        return id;
    }

    public Short getIdLocalidade() {
        return idLocalidade;
    }

    public LocalDate getDataLeituraRealizada() {
        return dataLeituraRealizada;
    }

    public LocalDate getDataFaturamentoRealizada() {
        return dataFaturamentoRealizada;
    }

    public LocalDate getDataConsolidacaoRealizada() {
        return dataConsolidacaoRealizada;
    }

    public Integer getFase1QuantidadeFaturas() {
        return fase1QuantidadeFaturas;
    }

    public void setFase1QuantidadeFaturas(Integer fase1QuantidadeFaturas) {
        this.fase1QuantidadeFaturas = fase1QuantidadeFaturas;
    }

    public Integer getFase2QuantidadeFaturas() {
        return fase2QuantidadeFaturas;
    }

    public void setFase2QuantidadeFaturas(Integer fase2QuantidadeFaturas) {
        this.fase2QuantidadeFaturas = fase2QuantidadeFaturas;
    }

    public Integer getFase3QuantidadeFaturas() {
        return fase3QuantidadeFaturas;
    }

    public void setFase3QuantidadeFaturas(Integer fase3QuantidadeFaturas) {
        this.fase3QuantidadeFaturas = fase3QuantidadeFaturas;
    }

    public Integer getFase4QuantidadeFaturas() {
        return fase4QuantidadeFaturas;
    }

    public void setFase4QuantidadeFaturas(Integer fase4QuantidadeFaturas) {
        this.fase4QuantidadeFaturas = fase4QuantidadeFaturas;
    }

    public Integer getFase5QuantidadeFaturas() {
        return fase5QuantidadeFaturas;
    }

    public void setFase5QuantidadeFaturas(Integer fase5QuantidadeFaturas) {
        this.fase5QuantidadeFaturas = fase5QuantidadeFaturas;
    }

    public Integer getFase0QuantidadeFaturas() {
        return fase0QuantidadeFaturas;
    }

    public void  setFase0QuantidadeFaturas(Integer fase0QuantidadeFaturas) {
        this.fase0QuantidadeFaturas = fase0QuantidadeFaturas;
    }
}

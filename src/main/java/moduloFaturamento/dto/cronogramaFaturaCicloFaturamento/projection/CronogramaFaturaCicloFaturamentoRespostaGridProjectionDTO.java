package moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;

public class CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO {
    private final Long id;
    private final LocalDate dataReferencia;
    private final Short idLocalidade;
    private final String localidade;
    private final Short ciclo;
    private final Short idFase;
    private final String nomeFase;
    private final String statusProcessamento;


    public CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO(Long id, Integer dataReferencia, Short idLocalidade, String localidade, Short ciclo, Short idFase, String nomeFase,
                                                                     String tipoStatusProcessamento) {
        this.id = id;
        this.dataReferencia = ConverterData.integerEmLocalDateFormatoDB(dataReferencia);
        this.idLocalidade = idLocalidade;
        this.localidade = localidade;
        this.ciclo = ciclo;
        this.idFase = idFase;
        this.nomeFase = nomeFase;
        this.statusProcessamento = tipoStatusProcessamento;
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

    public Long getId() {
        return id;
    }

    public Short getIdLocalidade() {
        return idLocalidade;
    }

    public Short getIdFase() {
        return idFase;
    }

    public String getStatusProcessamento() {
        return statusProcessamento;
    }
}

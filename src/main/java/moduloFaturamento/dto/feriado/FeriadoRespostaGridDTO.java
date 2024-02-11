package moduloFaturamento.dto.feriado;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;

public class FeriadoRespostaGridDTO {
    private Integer id;
    private LocalDate dataFeriado;
    private String tipoFeriado;
    private String nomeFeriado;
    private String siglaUnidadeFederacao;
    private String localidade;

    public FeriadoRespostaGridDTO(Integer id, Integer dataFeriado, String tipoFeriado, String nomeFeriado, String localidade, String siglaUnidade) {
        this.id = id;
        this.dataFeriado = ConverterData.integerEmLocalDateFormatoDB(dataFeriado);
        this.tipoFeriado = tipoFeriado;
        this.nomeFeriado = nomeFeriado;
        this.localidade = localidade;
        this.siglaUnidadeFederacao = siglaUnidade;
    }

    public FeriadoRespostaGridDTO() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataFeriado() {
        return dataFeriado;
    }

    public String getTipoFeriado() {
        return tipoFeriado;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public String getSiglaUnidadeFederacao() {
        return siglaUnidadeFederacao;
    }

    public String getLocalidade() {
        return localidade;
    }

}

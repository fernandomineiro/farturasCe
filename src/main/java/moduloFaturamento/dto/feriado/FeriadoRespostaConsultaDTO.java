package moduloFaturamento.dto.feriado;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;

public class FeriadoRespostaConsultaDTO {
    private Integer id;
    private LocalDate dataFeriado;
    private Integer dtFeriado;
    private Short idTipoFeriado;
    private Short cdCidade;
    private String dcFeriado;
    private Integer idUnidadeFederativa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataFeriado() {
        if (this.dataFeriado == null) {
            return ConverterData.integerEmLocalDateFormatoDB(this.dtFeriado);
        } else {
            return dataFeriado;
        }
    }

    public void setDataFeriado(LocalDate dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    public Short getIdTipoFeriado() {
        return idTipoFeriado;
    }

    public void setIdTipoFeriado(Short idTipoFeriado) {
        this.idTipoFeriado = idTipoFeriado;
    }

    public Short getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(Short cdCidade) {
        this.cdCidade = cdCidade;
    }

    public String getDcFeriado() {
        return dcFeriado;
    }

    public void setDcFeriado(String dcFeriado) {
        this.dcFeriado = dcFeriado;
    }

    public Integer getIdUnidadeFederativa() {
        return idUnidadeFederativa;
    }

    public void setIdUnidadeFederativa(Integer idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }

    public void setDtFeriado(Integer dtFeriado) {
        this.dtFeriado = dtFeriado;
    }
}

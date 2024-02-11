package moduloFaturamento.dto.feriado;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FeriadoCadastrarDTO {
    @NotBlank
    @Size(max = 30)
    private String nomeFeriado;
    @NotNull
    private LocalDate dataFeriado;
    @NotNull
    private Short idTipoFeriado;
    private Integer idUnidadeFederativa;
    private Short idLocalidade;

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }

    public LocalDate getDataFeriado() {
        return dataFeriado;
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

    public Integer getIdUnidadeFederativa() {
        return idUnidadeFederativa;
    }

    public void setIdUnidadeFederativa(Integer idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }

    public Short getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Short idLocalidade) {
        this.idLocalidade = Objects.requireNonNullElse(idLocalidade, (short)0);
    }
}

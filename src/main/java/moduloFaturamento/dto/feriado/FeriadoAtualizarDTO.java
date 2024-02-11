package moduloFaturamento.dto.feriado;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FeriadoAtualizarDTO {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(max = 30)
    private String nomeFeriado;
    @NotNull
    private Short idTipoFeriado;
    private Integer idUnidadeFederativa;
    private Short idLocalidade;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }

    public Short getIdTipoFeriado() {
        return idTipoFeriado;
    }

    public void setIdTipoFeriado(Short idTipoFeriado) {
        this.idTipoFeriado = idTipoFeriado;
    }

    public Short getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Short idLocalidade) {
        this.idLocalidade = Objects.requireNonNullElse(idLocalidade, (short)0);
    }

    public Integer getIdUnidadeFederativa() {
        return idUnidadeFederativa;
    }

    public void setIdUnidadeFederativa(Integer idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }
}

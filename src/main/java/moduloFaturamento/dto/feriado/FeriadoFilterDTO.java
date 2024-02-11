package moduloFaturamento.dto.feriado;

import moduloFaturamento.util.CriterioPesquisaComLikeCampoDeTexto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class FeriadoFilterDTO {
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFeriadoInicio;
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFeriadoFim;
    private Short idTipoFeriado;
    private String nomeFeriado;
    private Integer idUnidadeFederativa;
    private Short idLocalidade;

    public LocalDate getDataFeriadoInicio() {
        return dataFeriadoInicio;
    }

    public void setDataFeriadoInicio(LocalDate dataFeriadoInicio) {
        this.dataFeriadoInicio = dataFeriadoInicio;
    }

    public LocalDate getDataFeriadoFim() {
        return dataFeriadoFim;
    }

    public void setDataFeriadoFim(LocalDate dataFeriadoFim) {
        this.dataFeriadoFim = dataFeriadoFim;
    }

    public Short getIdTipoFeriado() {
        return idTipoFeriado;
    }

    public void setIdTipoFeriado(Short idTipoFeriado) {
        this.idTipoFeriado = idTipoFeriado;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }

    public void setNomeFeriado(String nomeFeriado) {
        Integer criterioPesquisaContendo = 1;
        this.nomeFeriado = CriterioPesquisaComLikeCampoDeTexto.incluirCriterio(nomeFeriado,criterioPesquisaContendo);
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
        this.idLocalidade = idLocalidade;
    }
}

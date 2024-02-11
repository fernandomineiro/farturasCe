package moduloFaturamento.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "FTAFE")
public class Feriado {
    @EmbeddedId
    private IdFeriado idFeriado;
    @Column(name = "ID",insertable = false,updatable = false)
    private Integer id;
    @Column(name="DATA_FERIADO", updatable = false)
    private LocalDate dataFeriado;
    @Column(name="DC_FERIADO")
    @Size(max = 30)
    private String dcFeriado;
    @Column(name="ID_USUARIO")
    @Size(max = 50)
    private String loginUsuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TP_FERIADO", referencedColumnName = "ID")
    private TipoFeriado tpFeriado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIDADE_FEDERATIVA", referencedColumnName = "ID")
    private TipoUnidadeFederativa unidadeFederativa;
    @Column(name="DATA_HORA_CADASTRO")
    private LocalDateTime datahoraCadastro;
    @Column(name="DATA_HORA_EXCLUSAO")
    private LocalDateTime dataHoraExclusao;
    @Column(name="MAINT") @Size(max = 1)
    private String maint;

    public Feriado() {
        this.maint = "A";
    }

    public IdFeriado getIdFeriado() {
        return idFeriado;
    }

    public void setIdFeriado(IdFeriado idFeriado) {
        this.idFeriado = idFeriado;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataFeriado() {
        return dataFeriado;
    }

    public void setDataFeriado(LocalDate dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    public String getDcFeriado() {
        return dcFeriado;
    }

    public void setDcFeriado(String dcFeriado) {
        dcFeriado = Objects.requireNonNullElse(dcFeriado, "");
        this.dcFeriado = dcFeriado.toUpperCase();
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public LocalDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public void setDatahoraCadastro(LocalDateTime datahoraCadastro) {
        this.datahoraCadastro = datahoraCadastro;
    }

    public TipoFeriado getTpFeriado() {
        return tpFeriado;
    }

    public void setTpFeriado(TipoFeriado tpFeriado) {
        this.tpFeriado = tpFeriado;
    }

    public TipoUnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(TipoUnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public LocalDateTime getDataHoraExclusao() {
        return dataHoraExclusao;
    }

    public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
        this.dataHoraExclusao = dataHoraExclusao;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package moduloFaturamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import moduloFaturamento.model.common.Usuario;

@Entity
@Table(name = "FAT_HISTORICO_INCENTIVO_CLIENTE")
public class IncentivoClienteHistorico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CD_PARAMETRO_INCENTIVO")
    private Integer cdParametroIncentivo;
    @Column(name = "DESCRICAO")
    @Size(max = 50)
    private String descricao;
    @Column(name = "DT_INI_VIGENCIA")
    private LocalDateTime dataInicioVigencia;
    @Column(name = "DT_FIM_VIGENCIA")
    private LocalDateTime dataFimVigencia;
    @Column(name = "DT_REGISTRO")
    private LocalDateTime dataRegistro;

    @Column(name = "ID_USUARIO")
    private String usuarioSgaus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO", referencedColumnName = "ID")
    private Usuario usuario;
    @Column(name = "DATA_HORA_EXCLUSAO")
    private LocalDateTime dataHoraExclusao;
    @Column(name = "DATA_REGISTRO_HISTORICO")
    private LocalDateTime dataHoraRegistroHistorico;
    @Column(name = "ID_TIPO_INCENTIVO")
    private Integer idTipoIncentivo;

    public IncentivoClienteHistorico() {
        this.dataRegistro = LocalDateTime.now();
    }

    public IncentivoClienteHistorico(IncentivoCliente entidade) {
        this.dataHoraRegistroHistorico = LocalDateTime.now();
        this.cdParametroIncentivo = entidade.getId();
        this.dataRegistro = entidade.getDataRegistro();
        this.descricao = entidade.getDescricao();
        this.dataInicioVigencia = entidade.getDataInicioVigencia();
        // this.dataInicioVigencia = LocalDateTime.now();
        this.dataFimVigencia = entidade.getDataFimVigencia();
        // this.dataFimVigencia = LocalDateTime.now();
        this.usuarioSgaus = entidade.getUsuarioSgaus();
        // this.usuarioSgaus = "Teste";
        this.usuario = entidade.getUsuario();
        this.dataHoraExclusao = null;
        this.idTipoIncentivo = entidade.getTipoIncentivoCliente().getId();
        this.idTipoIncentivo = 2;
    }

    public IncentivoClienteHistorico(Integer cdParametroIncentivo) {
        this.cdParametroIncentivo = cdParametroIncentivo;
    }

    public Integer getCdParametroIncentivo() {
        return cdParametroIncentivo;
    }

    public void setCdParametroIncentivo(Integer id) {
        this.cdParametroIncentivo = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDateTime dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getUsuarioSgaus() {
        return usuarioSgaus;
    }

    public void setUsuarioSgaus(String usuarioSgaus) {
        this.usuarioSgaus = usuarioSgaus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHoraExclusao() {
        return dataHoraExclusao;
    }

    public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
        this.dataHoraExclusao = dataHoraExclusao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTipoIncentivo() {
        return idTipoIncentivo;
    }

    public void setIdTipoIncentivo(Integer idTipoIncentivo) {
        this.idTipoIncentivo = idTipoIncentivo;
    }
}

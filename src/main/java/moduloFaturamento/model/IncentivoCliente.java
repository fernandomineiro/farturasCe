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
@Table(name = "FAT_INCENTIVO_CLIENTE")
public class IncentivoCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PARAMETRO_INCENTIVO")
    private Integer id;
    @Column(name = "DESCRICAO") @Size(max = 50)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INCENTIVO", referencedColumnName = "ID")
    private TipoIncentivoCliente tipoIncentivoCliente;


    public IncentivoCliente() {
        this.dataRegistro = LocalDateTime.now();
    }

    public IncentivoCliente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setUsuarioSgaus(String usuarioSgaus) {
        this.usuarioSgaus = usuarioSgaus;
    }

    public String getUsuarioSgaus() {
        return usuarioSgaus;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public TipoIncentivoCliente getTipoIncentivoCliente() {
        return tipoIncentivoCliente;
    }

    public void setTipoIncentivoCliente(TipoIncentivoCliente tipoIncentivoCliente) {
        this.tipoIncentivoCliente = tipoIncentivoCliente;
    }
}

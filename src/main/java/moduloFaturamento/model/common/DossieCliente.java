package moduloFaturamento.model.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAD_DOSSIE_CLIENTE")
public class DossieCliente implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_DOSSIE_CLIENTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossieCliente;

    @Column(name = "CD_CLIENTE")
    private Integer cdCliente;

    @Column(name = "DC_ANOTACAO")
    private String dcAnotacao;

    @Column(name = "DC_OBJETO")
    private String dcObjeto;

    @Column(name = "ID_USUARIO")
    private String loginUsuario;

    @Column(name = "DATA_HORA_INCLUSAO")
    private LocalDateTime dataHoraInclusao;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdDossieCliente() {
        return idDossieCliente;
    }

    public void setIdDossieCliente(Long idDossieCliente) {
        this.idDossieCliente = idDossieCliente;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getDcAnotacao() {
        return dcAnotacao;
    }

    public void setDcAnotacao(String dcAnotacao) {
        this.dcAnotacao = dcAnotacao;
    }

    public String getDcObjeto() {
        return dcObjeto;
    }

    public void setDcObjeto(String dcObjeto) {
        this.dcObjeto = dcObjeto;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDossieCliente == null) ? 0 : idDossieCliente.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DossieCliente other = (DossieCliente) obj;
        if (idDossieCliente == null) {
            if (other.idDossieCliente != null)
                return false;
        } else if (!idDossieCliente.equals(other.idDossieCliente))
            return false;
        return true;
    }
    
}

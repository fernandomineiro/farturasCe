package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CDABA")
public class Bairro{

    @EmbeddedId
    @JsonCesanNotSerializable
    private IdBairro idBairro;

    @Column(name="DC_BAIRRO")
    private String nomeBairro;

    @Column(name="MAINT")
    private char maint;

    @Column(name="CD_MUN_IMPRESSAO")
    private Short cdMunicipioImpressao;

    @Column(name="ID")
    @JsonCesanNotSerializable
    private Integer id;

    public Bairro() {

    }

    public Bairro(IdBairro idBairro) {
        this.idBairro = idBairro;
    }

    public IdBairro getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(IdBairro idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public char getMaint() {
        return maint;
    }

    public void setMaint(char maint) {
        this.maint = maint;
    }

    public Short getCdMunicipioImpressao() {
        return cdMunicipioImpressao;
    }

    public void setCdMunicipioImpressao(Short cdMunicipioImpressao) {
        this.cdMunicipioImpressao = cdMunicipioImpressao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

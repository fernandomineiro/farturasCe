package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "HITIN")
public class HidrometroInstalado implements Serializable {

    @Id
    @Column(name = "MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    @Column(name = "DV")
    private Short dv;

    @Column(name = "CD_HIDROMETRO")
    private String codHidrometro;

    @Column(name = "LEITURA_INSTALACAO")
    private Integer leituraInstalacao;

    @Column(name = "DT_INSTALACAO")
    @JsonCesanNotSerializable
    private Integer dataInstalacao;

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
    }

    public String getCodHidrometro() {
        return codHidrometro;
    }

    public void setCodHidrometro(String codHidrometro) {
        this.codHidrometro = codHidrometro;
    }


    public Integer getLeituraInstalacao() {
        return leituraInstalacao;
    }

    public void setLeituraInstalacao(Integer leituraInstalacao) {
        this.leituraInstalacao = leituraInstalacao;
    }

    public Integer getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Integer dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }
}

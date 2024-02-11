package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HITRE")
public class HidrometroRetirado {

    @EmbeddedId
    @JsonCesanNotSerializable
    private IdHidrometroRetirado idHidrometroRetirado;

    @Column(name = "ID", insertable = false, updatable = false)
    private Long id;

    @Column(name = "LEITURA_RETIRADA")
    private Integer leituraRetirada;

    @Column(name = "DT_INSTALACAO")
    private Integer dataInstalacao;


    public IdHidrometroRetirado getIdHidrometroRetirado() {
        return idHidrometroRetirado;
    }

    public void setIdHidrometroRetirado(IdHidrometroRetirado idHidrometroRetirado) {
        this.idHidrometroRetirado = idHidrometroRetirado;
    }

    public Integer getLeituraRetirada() {
        return leituraRetirada;
    }

    public void setLeituraRetirada(Integer leituraRetirada) {
        this.leituraRetirada = leituraRetirada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Integer dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }
}

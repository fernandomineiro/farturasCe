package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.*;

@Entity
@Table(name = "HIAHI")
public class Hidrometro {

    @Id
    @Column(name = "CD_HIDROMETRO")
    private String codHidrometro;

    @Column(name = "ID")
    @JsonCesanNotSerializable
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARAC_HIDROMETRO")
    private HidrometroCaracteristicas hidrometroCaracteristicas;

    public String getCodHidrometro() {
        return codHidrometro;
    }

    public void setCodHidrometro(String codHidrometro) {
        this.codHidrometro = codHidrometro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HidrometroCaracteristicas getHidrometroCaracteristicas() {
        return hidrometroCaracteristicas;
    }

    public void setHidrometroCaracteristicas(HidrometroCaracteristicas hidrometroCaracteristicas) {
        this.hidrometroCaracteristicas = hidrometroCaracteristicas;
    }
}

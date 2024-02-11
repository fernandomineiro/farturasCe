package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HIACA")
public class HidrometroCaracteristicas {

    @Id
    @Column(name = "CARAC_HIDROMETRO")
    @JsonCesanNotSerializable
    private Short codCaracteristicaHidro;

    @Column(name = "PONTEIROS_HIDRO")
    private Short ponteirosHidro;

    public Short getCodCaracteristicaHidro() {
        return codCaracteristicaHidro;
    }

    public void setCodCaracteristicaHidro(Short codCaracteristicaHidro) {
        this.codCaracteristicaHidro = codCaracteristicaHidro;
    }

    public Short getPonteirosHidro() {
        return ponteirosHidro;
    }

    public void setPonteirosHidro(Short ponteirosHidro) {
        this.ponteirosHidro = ponteirosHidro;
    }
}

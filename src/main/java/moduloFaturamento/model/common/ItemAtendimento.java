package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ATAIM")
public class ItemAtendimento {

    @EmbeddedId
    @JsonCesanNotSerializable
    private IdItemAtendimento idItemAtendimento;

    public ItemAtendimento() {
    }

    public ItemAtendimento(IdItemAtendimento idItemAtendimento) {
        this.idItemAtendimento = idItemAtendimento;
    }

    public IdItemAtendimento getIdItemAtendimento() {
        return idItemAtendimento;
    }

    public void setIdItemAtendimento(IdItemAtendimento idItemAtendimento) {
        this.idItemAtendimento = idItemAtendimento;
    }

    @Override
    public String toString() {
        return "ItemAtendimento{" +
                "idItemAtendimento=" + idItemAtendimento +
                '}';
    }
}

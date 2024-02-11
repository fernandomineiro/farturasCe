package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FAT_TP_CONSUMO_FATURADO")
public class TipoConsumoFaturado {
    @Id
    @Column(name = "ID")
    private Short id;
    @Column(name = "DESCRICAO") @Size(max = 100)
    private String descricao;

    public TipoConsumoFaturado() {
    }

    public TipoConsumoFaturado(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}

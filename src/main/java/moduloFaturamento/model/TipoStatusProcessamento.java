package moduloFaturamento.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="FAT_TP_STATUS_PROCESSAMENTO")
public class TipoStatusProcessamento {
    @Id
    @Column(name="ID")
    private Short id;
    @Column(name="DESCRICAO")
    @Size(max = 100)
    private String descricao;

    public TipoStatusProcessamento() {
    }

    public TipoStatusProcessamento(Short id) {
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
        return (this.id != null ? this.id.toString(): null);
    }
}

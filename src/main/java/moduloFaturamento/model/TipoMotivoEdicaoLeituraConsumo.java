package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FAT_TP_MOTIVO_EDICAO_LEITURA_CONSUMO")
public class TipoMotivoEdicaoLeituraConsumo {

    @Id
    @Column(name = "ID")
    private Short id;
    @Column(name = "DESCRICAO")
    private String descricao;

    public TipoMotivoEdicaoLeituraConsumo() {
    }

    public TipoMotivoEdicaoLeituraConsumo(Short id) {
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

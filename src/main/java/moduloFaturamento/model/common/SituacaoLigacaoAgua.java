package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CAD_SIT_LIGACAO_AGUA")
public class SituacaoLigacaoAgua implements Serializable {

    @Id
    @Column(name = "ID_SIT_LIGACAO_AGUA")
    private Integer id;
    @Column(name = "DC_DESCRICAO")
    private String descricao;



    public SituacaoLigacaoAgua() {
    }
    public SituacaoLigacaoAgua(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
        return this.id.toString();
    }

}
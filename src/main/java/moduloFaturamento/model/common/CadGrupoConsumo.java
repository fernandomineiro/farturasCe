package moduloFaturamento.model.common;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAD_GRUPO_CONSUMO")
public class CadGrupoConsumo implements Serializable {

    @Id
    @Column(name = "ID_GRUPO_CONSUMO")
    private Integer id;
    @Column(name = "CS_CATEGORIA")
    private Short csCategoria;
    @Column(name = "DC_GRUPO_CONSUMO")
    private String descricao;
    @Column(name = "CS_TIPO")
    private Short csTipo;

    public CadGrupoConsumo() {
    }

    public CadGrupoConsumo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getCsCategoria() {
        return csCategoria;
    }

    public void setCsCategoria(Short csCategoria) {
        this.csCategoria = csCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Short getCsTipo() {
        return csTipo;
    }

    public void setCsTipo(Short csTipo) {
        this.csTipo = csTipo;
    }
}

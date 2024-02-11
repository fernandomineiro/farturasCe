package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_INCENTIVO_GRUPO_CONSUMO")
public class IncentivoClienteGrupoConsumo {

    @EmbeddedId
    private IdIncentivoClienteGrupoConsumo id;
    @Column(name = "ID",insertable = false,updatable = false)
    private Long idAuditoria;

    public IncentivoClienteGrupoConsumo() {
    }

    public IncentivoClienteGrupoConsumo(IdIncentivoClienteGrupoConsumo id) {
        this.id = id;
    }

    public IdIncentivoClienteGrupoConsumo getId() {
        return id;
    }

    public void setId(IdIncentivoClienteGrupoConsumo id) {
        this.id = id;
    }

    public Long getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Long idAuditoria) {
        this.idAuditoria = idAuditoria;
    }
}

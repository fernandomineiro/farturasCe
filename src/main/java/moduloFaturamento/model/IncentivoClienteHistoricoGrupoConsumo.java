package moduloFaturamento.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAT_HISTORICO_INCENTIVO_GRUPO_CONSUMO")
public class IncentivoClienteHistoricoGrupoConsumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CD_PARAMETRO_DETALHE")
    private Integer cdParametroDetalhe;
    @Column(name = "ID_GRUPO_CONSUMO")
    private Integer idGrupoConsumo;
    @Column(name = "DATA_REGISTRO_HISTORICO")
    private LocalDateTime dataHoraRegistroHistorico;

    public IncentivoClienteHistoricoGrupoConsumo() {
    }

    public IncentivoClienteHistoricoGrupoConsumo(IncentivoClienteGrupoConsumo entidade) {
        this.cdParametroDetalhe = entidade.getId().getIncentivoClienteDetalhe().getId();
        this.idGrupoConsumo = entidade.getId().getCadGrupoConsumo().getId();
        this.dataHoraRegistroHistorico = LocalDateTime.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCdParametroDetalhe() {
        return cdParametroDetalhe;
    }

    public void setCdParametroDetalhe(Integer cdParametroDetalhe) {
        this.cdParametroDetalhe = cdParametroDetalhe;
    }

    public Integer getIdGrupoConsumo() {
        return idGrupoConsumo;
    }

    public void setIdGrupoConsumo(Integer idGrupoConsumo) {
        this.idGrupoConsumo = idGrupoConsumo;
    }
}

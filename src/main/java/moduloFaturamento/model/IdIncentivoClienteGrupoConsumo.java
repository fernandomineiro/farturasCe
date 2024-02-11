package moduloFaturamento.model;

import moduloFaturamento.model.common.CadGrupoConsumo;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class IdIncentivoClienteGrupoConsumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PARAMETRO_DETALHE", referencedColumnName = "CD_PARAMETRO_DETALHE")
    private IncentivoClienteDetalhe incentivoClienteDetalhe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO_CONSUMO", referencedColumnName = "ID_GRUPO_CONSUMO")
    private CadGrupoConsumo cadGrupoConsumo;

    public IdIncentivoClienteGrupoConsumo() {
    }

    public IdIncentivoClienteGrupoConsumo(IncentivoClienteDetalhe incentivoClienteDetalhe, CadGrupoConsumo cadGrupoConsumo) {
        this.incentivoClienteDetalhe = incentivoClienteDetalhe;
        this.cadGrupoConsumo = cadGrupoConsumo;
    }

    public IncentivoClienteDetalhe getIncentivoClienteDetalhe() {
        return incentivoClienteDetalhe;
    }

    public void setIncentivoClienteDetalhe(IncentivoClienteDetalhe incentivoClienteDetalhe) {
        this.incentivoClienteDetalhe = incentivoClienteDetalhe;
    }

    public CadGrupoConsumo getCadGrupoConsumo() {
        return cadGrupoConsumo;
    }

    public void setCadGrupoConsumo(CadGrupoConsumo cadGrupoConsumo) {
        this.cadGrupoConsumo = cadGrupoConsumo;
    }
}

package moduloFaturamento.dto.tarifaMedia;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarifaMediaPesquisaDTO {

    private Short cdRegional;

    private Short idGrupoConsumo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate refTarifaMedia;

    public Short getCdRegional() {
        return cdRegional;
    }

    public void setCdRegional(Short cdRegional) {
        this.cdRegional = cdRegional;
    }

    public Short getIdGrupoConsumo() {
        return idGrupoConsumo;
    }

    public void setIdGrupoConsumo(Short idGrupoConsumo) {
        this.idGrupoConsumo = idGrupoConsumo;
    }

    public LocalDate getRefTarifaMedia() {
        return refTarifaMedia;
    }

    public void setRefTarifaMedia(LocalDate refTarifaMedia) {
        this.refTarifaMedia = refTarifaMedia;
    }

    
    
}

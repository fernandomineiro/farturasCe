package moduloFaturamento.dto.tarifa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParamametrosPesquisaTarifaFilterDTO {

    private Short idLocalidade;

    private Short idTarifca;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTarifa;

    public Short getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Short idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Short getIdTarifca() {
        return idTarifca;
    }

    public void setIdTarifca(Short idTarifca) {
        this.idTarifca = idTarifca;
    }

    public LocalDate getDataTarifa() {
        return dataTarifa;
    }

    public void setDataTarifa(LocalDate dataTarifa) {
        this.dataTarifa = dataTarifa;
    }

    public ParamametrosPesquisaTarifaFilterDTO(Short idLocalidade, Short idTarifca, LocalDate dataTarifa,
            Integer grupoConsumo) {
        this.idLocalidade = idLocalidade;
        this.idTarifca = idTarifca;
        this.dataTarifa = dataTarifa;
    }
    
}

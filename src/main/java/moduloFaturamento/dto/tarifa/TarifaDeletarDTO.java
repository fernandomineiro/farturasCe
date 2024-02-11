package moduloFaturamento.dto.tarifa;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TarifaDeletarDTO {

    @NotNull
    private Integer idTarifa;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTarifa;

    public Integer getIdTarifa() {
        return idTarifa;
    }
    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }
    public LocalDate getDataTarifa() {
        return dataTarifa;
    }
    public void setDataTarifa(LocalDate dataTarifa) {
        this.dataTarifa = dataTarifa;
    }

    
    
}

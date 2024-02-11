package moduloFaturamento.dto.diasCiclos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FaturamentoDiasVencimentoNovoDTO {

    @NotNull
	private Short ciclo;
	
    @NotNull
    @Min(value = 1)
    @Max(value = 28)
	private Short dia;

    private String maint = "A";

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public Short getDia() {
        return dia;
    }

    public void setDia(Short dia) {
        this.dia = dia;
    }

    public void setMaint(String maint) {
        this.maint = maint;
    }

    public String getMaint() {
        return maint;
    }
    
    
}

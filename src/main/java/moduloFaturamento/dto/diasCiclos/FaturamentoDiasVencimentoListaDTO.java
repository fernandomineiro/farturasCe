package moduloFaturamento.dto.diasCiclos;

import javax.validation.constraints.NotEmpty;

public class FaturamentoDiasVencimentoListaDTO {

    @NotEmpty
    private Short ciclo;
    @NotEmpty
    private Short dia;
    private String maint;
    
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
    public String getMaint() {
        return maint;
    }
    public void setMaint(String maint) {
        this.maint = maint;
    }
    
}

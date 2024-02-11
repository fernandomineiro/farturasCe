package moduloFaturamento.dto.diasCiclos;

import javax.validation.constraints.NotNull;

public class FaturamentoDiasVencimentoDeletarDTO {

    @NotNull
    private Short ciclo;
    @NotNull
    private Short dia;
    private String maint="A";
    
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

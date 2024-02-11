package moduloFaturamento.dto.credito;

import javax.validation.constraints.NotNull;

public class CreditoPesquisaCreditoLancadoFilterDTO {

    @NotNull
    private Integer matricula;
    private Short dv;
    @NotNull
    private Short cdCredito;
    
    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public Short getCdCredito() {
        return cdCredito;
    }
    public void setCdCredito(Short cdCredito) {
        this.cdCredito = cdCredito;
    }
    public Short getDv() {
        return dv;
    }
    public void setDv(Short dv) {
        this.dv = dv;
    }
    
}

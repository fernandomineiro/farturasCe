package moduloFaturamento.dto.bairroCiclo;

import javax.validation.constraints.NotNull;

public class LocalidadeNomeBairroCicloFilterDTO {
    
    @NotNull
    private Short cdCidade;
    private Short cdBairro;
    private Short ciclo;
    
    public Short getCdCidade() {
        return cdCidade;
    }
    public void setCdCidade(Short cdCidade) {
        this.cdCidade = cdCidade;
    }
    public Short getCdBairro() {
        return cdBairro;
    }
    public void setCdBairro(Short cdBairro) {
        this.cdBairro = cdBairro;
    }
    public Short getCiclo() {
        return ciclo;
    }
    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    
}

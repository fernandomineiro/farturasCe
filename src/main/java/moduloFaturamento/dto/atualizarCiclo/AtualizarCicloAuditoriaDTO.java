package moduloFaturamento.dto.atualizarCiclo;

public class AtualizarCicloAuditoriaDTO {

    private Short ciclo;

    public Short getCiclo() {
        return ciclo;
    }

    public void setCiclo(Short ciclo) {
        this.ciclo = ciclo;
    }

    public AtualizarCicloAuditoriaDTO() {
    }

    public AtualizarCicloAuditoriaDTO(Short ciclo) {
        this.ciclo = ciclo;
    }    
}

package moduloFaturamento.dto.credito;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreditoEncerrarDTO {

    @NotNull
    private Long id;

    @NotEmpty
    private String campoJustificativa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampoJustificativa() {
        return campoJustificativa;
    }

    public void setCampoJustificativa(String campoJustificativa) {
        this.campoJustificativa = campoJustificativa;
    }
    
}

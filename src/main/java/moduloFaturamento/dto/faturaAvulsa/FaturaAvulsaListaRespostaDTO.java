package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

public class FaturaAvulsaListaRespostaDTO {

    @NotNull
    private Short cdServico;
    @NotNull
    private String dcServico;

    private List<BigDecimal> valorServico;

    public FaturaAvulsaListaRespostaDTO() {
    }

    public FaturaAvulsaListaRespostaDTO(@NotNull Short cdServico, @NotNull String dcServico,
            List<BigDecimal> valorServico) {
        this.cdServico = cdServico;
        this.dcServico = dcServico;
        this.valorServico = valorServico;
    }

    public Short getCdServico() {
        return cdServico;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }

    public String getDcServico() {
        return dcServico;
    }

    public void setDcServico(String dcServico) {
        this.dcServico = dcServico;
    }

    public List<BigDecimal> getValorServico() {
        return valorServico;
    }

    public void setValorServico(List<BigDecimal> valorServico) {
        this.valorServico = valorServico;
    }

    @Override
    public String toString() {
        return "FaturaAvulsaListaRespostaDTO [cdServico=" + cdServico + ", dcServico=" + dcServico + ", valorServico="
                + valorServico + "]";
    }

    
}

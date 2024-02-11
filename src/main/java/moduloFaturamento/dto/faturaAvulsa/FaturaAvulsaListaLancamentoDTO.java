package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class FaturaAvulsaListaLancamentoDTO {

    @NotNull
    private Short cdServico;
    @NotNull
    private String dcServico;

    private BigDecimal valorServico;
    
    public FaturaAvulsaListaLancamentoDTO() {
    }
    
    public FaturaAvulsaListaLancamentoDTO(Short cdServico, String dcServico, BigDecimal valorServico) {
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
    public BigDecimal getValorServico() {
        return valorServico;
    }
    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }
    @Override
    public String toString() {
        return "FaturaAvulsaListaLancamentoDTO [cdServico=" + cdServico + ", dcServico=" + dcServico + ", valorServico="
                + valorServico + "]";
    }
    
}

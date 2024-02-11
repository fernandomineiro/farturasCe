package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTEL")
public class LancamentosFaturaAvulsa {

    @EmbeddedId
    private IdLancamentoFaturaAvulsa idLancamentoFaturaAvulsa;

    @Column(name = "DC_SERVICO")
    private String dcServico;

    @Column(name = "MAINT")
    private Character maint = 'A';

    @Column(name = "VR_SERVICO")
    private BigDecimal valorServico;

    public LancamentosFaturaAvulsa() {
    }

    public LancamentosFaturaAvulsa(IdLancamentoFaturaAvulsa idLancamentoFaturaAvulsa, String dcServico, BigDecimal valorServico) {
        this.idLancamentoFaturaAvulsa = idLancamentoFaturaAvulsa;
        this.dcServico = dcServico;
        this.valorServico = valorServico;
    }

    public IdLancamentoFaturaAvulsa getIdLancamentoFaturaAvulsa() {
        return idLancamentoFaturaAvulsa;
    }

    public void setIdLancamentoFaturaAvulsa(IdLancamentoFaturaAvulsa idLancamentoFaturaAvulsa) {
        this.idLancamentoFaturaAvulsa = idLancamentoFaturaAvulsa;
    }

    public String getDcServico() {
        return dcServico;
    }

    public void setDcServico(String dcServico) {
        this.dcServico = dcServico;
    }

    public Character getMaint() {
        return maint;
    }

    public void setMaint(Character maint) {
        this.maint = maint;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }
    
}

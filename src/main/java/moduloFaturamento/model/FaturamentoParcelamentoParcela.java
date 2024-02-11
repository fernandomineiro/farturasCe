package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTPD")
public class FaturamentoParcelamentoParcela {

    @EmbeddedId
    private IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela;

    @Column(name = "VR_PARCELA")
    private BigDecimal valorParcela;

    @Column(name = "DT_VENCIMENTO")
    private Integer dataVencimento;

    @Column(name = "DT_PAGAMENTO")
    private Integer dataPagamento;
    
    @Column(name = "HORA_PAGAMENTO")
    private Integer horaPagamento;

    @Column(name = "REF_BXA_CONTABIL")
    private Integer referenciaBaixaContabil;

    @Column(name = "VR_BAIXA_ARRECADACAO")
    private BigDecimal valorBaixaArrecadacao;

    @Column(name = "TP_BAIXA")
    private Short tipoBaixa;

    @Column(name = "ID_LOTE")
    private Integer idLote;

    @Column(name = "CS_BAIXA_ENVIO")
    private String csBaixaEnvio;

    @Column(name = "NU_LOTE_ARREC")
    private Integer numeroLoteArrecadacao;

    @Column(name = "VR_JUROS")
    private BigDecimal valorJuros;

    @Column(name = "VR_MULTA")
    private BigDecimal valorMulta;

    public FaturamentoParcelamentoParcela() {
    }

    public FaturamentoParcelamentoParcela(IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela) {
        this.idFaturamentoParcelamentoParcela = idFaturamentoParcelamentoParcela;
    }

    public IdFaturamentoParcelamentoParcela getIdFaturamentoParcelamentoParcela() {
        return idFaturamentoParcelamentoParcela;
    }

    public void setIdFaturamentoParcelamentoParcela(IdFaturamentoParcelamentoParcela idFaturamentoParcelamentoParcela) {
        this.idFaturamentoParcelamentoParcela = idFaturamentoParcelamentoParcela;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Integer getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Integer dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Integer getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Integer dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Integer getReferenciaBaixaContabil() {
        return referenciaBaixaContabil;
    }

    public void setReferenciaBaixaContabil(Integer referenciaBaixaContabil) {
        this.referenciaBaixaContabil = referenciaBaixaContabil;
    }

    public BigDecimal getValorBaixaArrecadacao() {
        return valorBaixaArrecadacao;
    }

    public void setValorBaixaArrecadacao(BigDecimal valorBaixaArrecadacao) {
        this.valorBaixaArrecadacao = valorBaixaArrecadacao;
    }

    public Short getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(Short tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getCsBaixaEnvio() {
        return csBaixaEnvio;
    }

    public void setCsBaixaEnvio(String csBaixaEnvio) {
        this.csBaixaEnvio = csBaixaEnvio;
    }

    public Integer getNumeroLoteArrecadacao() {
        return numeroLoteArrecadacao;
    }

    public void setNumeroLoteArrecadacao(Integer numeroLoteArrecadacao) {
        this.numeroLoteArrecadacao = numeroLoteArrecadacao;
    }

    public Integer getHoraPagamento() {
        return horaPagamento;
    }

    public void setHoraPagamento(Integer horaPagamento) {
        this.horaPagamento = horaPagamento;
    }

    public BigDecimal getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idFaturamentoParcelamentoParcela == null) ? 0 : idFaturamentoParcelamentoParcela.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FaturamentoParcelamentoParcela other = (FaturamentoParcelamentoParcela) obj;
        if (idFaturamentoParcelamentoParcela == null) {
            if (other.idFaturamentoParcelamentoParcela != null)
                return false;
        } else if (!idFaturamentoParcelamentoParcela.equals(other.idFaturamentoParcelamentoParcela))
            return false;
        return true;
    }
    
}

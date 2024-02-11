package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CTTLA")
public class LancamentoContabil {

    @Id
    @Column(name="ROWID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="DT_CONTABIL")
    private Integer dataContabil;

    @Column(name="DT_LANCAMENTO")
    private Integer dataLancamento;

    @Column(name="CD_EVENTO")
    private Short cdEvento;

    @Column(name="CD_FILIAL_SAP")
    private Short cdfilialSap;

    @Column(name="NR_CONTA_CTB_SAP")
    private Integer numeroContaContabilSAP;

    @Column(name="D_C_CTB_SAP", length = 1)
    private String debitoCreditoContabilSAP;

    @Column(name="CD_CCUSTO_SAP", length = 10)
    private String cdCentroCustoSap;

    @Column(name="VR_LANCAMENTO")
    private BigDecimal valorLancamento;

    @Column(name="MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    @Column(name="CD_CLIENTE")
    private Integer cdCliente;

    @Column(name="REF_FATURA")
    private Integer refFatura;

    @Column(name="ORIGEM_FATURA")
    private Short origemFatura;

    @Column(name="CD_PARCELAMENTO")
    private Integer cdParcelamento;

    @Column(name="NUMERO_PARCELA")
    private Short numeroParcela;

    @Column(name="CD_CREDITO")
    private Short cdCredito;

    @Column(name="CD_SERVICO")
    private Short cdServico;

    @Column(name="D_C_SERVICO", length = 1)
    private String debitoCreditoServico;

    @Column(name="NU_LOTE_ARREC")
    private Integer numeroLoteArrecadacao;

    
    public LancamentoContabil() {
    }

    public LancamentoContabil(Long id, Integer dataContabil, Integer dataLancamento, Short cdEvento, Short cdfilialSap,
            Integer numeroContaContabilSAP, String debitoCreditoContabilSAP, String cdCentroCustoSap,
            BigDecimal valorLancamento, Integer matriculaImovel, Integer cdCliente, Integer refFatura,
            Short origemFatura, Integer cdParcelamento, Short numeroParcela, Short cdCredito, Short cdServico,
            String debitoCreditoServico, Integer numeroLoteArrecadacao) {
        this.id = id;
        this.dataContabil = dataContabil;
        this.dataLancamento = dataLancamento;
        this.cdEvento = cdEvento;
        this.cdfilialSap = cdfilialSap;
        this.numeroContaContabilSAP = numeroContaContabilSAP;
        this.debitoCreditoContabilSAP = debitoCreditoContabilSAP;
        this.cdCentroCustoSap = cdCentroCustoSap;
        this.valorLancamento = valorLancamento;
        this.matriculaImovel = matriculaImovel;
        this.cdCliente = cdCliente;
        this.refFatura = refFatura;
        this.origemFatura = origemFatura;
        this.cdParcelamento = cdParcelamento;
        this.numeroParcela = numeroParcela;
        this.cdCredito = cdCredito;
        this.cdServico = cdServico;
        this.debitoCreditoServico = debitoCreditoServico;
        this.numeroLoteArrecadacao = numeroLoteArrecadacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDtContabil() {
        return dataContabil;
    }

    public void setDtContabil(Integer dtContabil) {
        this.dataContabil = dtContabil;
    }

    public Integer getDtLancamento() {
        return dataLancamento;
    }

    public void setDtLancamento(Integer dtLancamento) {
        this.dataLancamento = dtLancamento;
    }

    public Short getCdEvento() {
        return cdEvento;
    }

    public void setCdEvento(Short cdEvento) {
        this.cdEvento = cdEvento;
    }

    public Short getCdfilialSap() {
        return cdfilialSap;
    }

    public void setCdfilialSap(Short cdfilialSap) {
        this.cdfilialSap = cdfilialSap;
    }

    public Integer getNumeroContaContabilSAP() {
        return numeroContaContabilSAP;
    }

    public void setNumeroContaContabilSAP(Integer numeroContaContabilSAP) {
        this.numeroContaContabilSAP = numeroContaContabilSAP;
    }

    public String getDebitoCreditoContabilSAP() {
        return debitoCreditoContabilSAP;
    }

    public void setDebitoCreditoContabilSAP(String debitoCreditoContabilSAP) {
        this.debitoCreditoContabilSAP = debitoCreditoContabilSAP;
    }

    public String getCdCentroCustoSap() {
        return cdCentroCustoSap;
    }

    public void setCdCentroCustoSap(String cdCentroCustoSap) {
        this.cdCentroCustoSap = cdCentroCustoSap;
    }

    public BigDecimal getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    public Integer getCdParcelamento() {
        return cdParcelamento;
    }

    public void setCdParcelamento(Integer cdParcelamento) {
        this.cdParcelamento = cdParcelamento;
    }

    public Short getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Short numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public Short getCdCredito() {
        return cdCredito;
    }

    public void setCdCredito(Short cdCredito) {
        this.cdCredito = cdCredito;
    }

    public Short getCdServico() {
        return cdServico;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }

    public String getDebitoCreditoServico() {
        return debitoCreditoServico;
    }

    public void setDebitoCreditoServico(String debitoCreditoServico) {
        this.debitoCreditoServico = debitoCreditoServico;
    }

    public Integer getNumeroLoteArrecadacao() {
        return numeroLoteArrecadacao;
    }

    public void setNumeroLoteArrecadacao(Integer numeroLoteArrecadacao) {
        this.numeroLoteArrecadacao = numeroLoteArrecadacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        LancamentoContabil other = (LancamentoContabil) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
  
    
}

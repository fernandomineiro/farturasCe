package moduloFaturamento.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoCampoLancadoPesquisaDTO {

    private Integer matricula;
    private Short dv;
    private Short cdCredito;

    private Integer cdServico;
    private String descServico;

    private LocalDate refEncerramento;
    
    private BigDecimal valorCredito;
    private BigDecimal saldo;

    private Integer refAtendimento;
    private Integer cdAtendimento;
    private Short seqSS;

    private Integer cdParcelamento;
    private Integer numeroParcela;
    private Short origemFatura;
    private LocalDate referenciaFatura;

    public Short getCdCredito() {
        return cdCredito;
    }
    public void setCdCredito(Short cdCredito) {
        this.cdCredito = cdCredito;
    }
    public Integer getCdServico() {
        return cdServico;
    }
    public void setCdServico(Integer cdServico) {
        this.cdServico = cdServico;
    }
    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public BigDecimal getValorCredito() {
        return valorCredito;
    }
    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    public String getDescServico() {
        return descServico;
    }
    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }
    public Short getDv() {
        return dv;
    }
    public void setDv(Short dv) {
        this.dv = dv;
    }
    public Integer getCdParcelamento() {
        return cdParcelamento;
    }
    public void setCdParcelamento(Integer cdParcelamento) {
        this.cdParcelamento = cdParcelamento;
    }
    public Integer getNumeroParcela() {
        return numeroParcela;
    }
    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }
    public LocalDate getReferenciaFatura() {
        return referenciaFatura;
    }
    public Short getOrigemFatura() {
        return origemFatura;
    }
    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }
    public void setReferenciaFatura(LocalDate referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }
    public LocalDate getRefEncerramento() {
        return refEncerramento;
    }
    public void setRefEncerramento(LocalDate refEncerramento) {
        this.refEncerramento = refEncerramento;
    }
    public Integer getRefAtendimento() {
        return refAtendimento;
    }
    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }
    public Integer getCdAtendimento() {
        return cdAtendimento;
    }
    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }
    public Short getSeqSS() {
        return seqSS;
    }
    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }
    
}

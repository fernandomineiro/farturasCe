package moduloFaturamento.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoPesquisaDTO {

    private Short cdCredito;
    private String cdServico;
    private String nomeServico;
    private BigDecimal valorCredito;
    private LocalDate dataInicio;
    private BigDecimal saldo;
    private LocalDate refEncerramento;
    private String usuario;

    private Integer refAtendimento;
    private Integer cdAtendimento;
    private Short seqSS;

    private Long id;
    private Integer cdParcelamento;
    private Integer numeroParcela;
    private Short origemFatura;
    private LocalDate referenciaFatura;
    
    public CreditoPesquisaDTO() {
    }

    public Short getCdCredito() {
        return cdCredito;
    }

    public void setCdCredito(Short cdCredito) {
        this.cdCredito = cdCredito;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
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

    public LocalDate getRefEncerramento() {
        return refEncerramento;
    }

    public void setRefEncerramento(LocalDate refEncerramento) {
        this.refEncerramento = refEncerramento;
    }

    public LocalDate getReferenciaFatura() {
        return referenciaFatura;
    }

    public void setReferenciaFatura(LocalDate referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }


    
}

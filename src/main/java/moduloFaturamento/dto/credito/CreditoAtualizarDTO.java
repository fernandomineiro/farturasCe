package moduloFaturamento.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class CreditoAtualizarDTO {

    @NotNull
    private Integer matricula;

    @NotNull
    private Short dv;
    
    @NotNull
    private Short cdCredito;

    @NotNull
    private Integer cdServico;

    @NotNull
    private BigDecimal valorCredito;
    
    private Integer refAtendimento;
    private Integer codAtendimento;
	private Short seqSS;

    private Integer cdParcelamento;
    private Integer numeroParcela;

    private Short origemFatura;
    private LocalDate referenciaFatura;

    private String campoJustificativa;

    public Integer getMatricula() {
        return matricula;
    }
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
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public BigDecimal getValorCredito() {
        return valorCredito;
    }
    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }
    public Integer getRefAtendimento() {
        return refAtendimento;
    }
    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }
    public Integer getCodAtendimento() {
        return codAtendimento;
    }
    public void setCodAtendimento(Integer codAtendimento) {
        this.codAtendimento = codAtendimento;
    }
    public Short getSeqSS() {
        return seqSS;
    }
    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }
    public String getCampoJustificativa() {
        return campoJustificativa;
    }
    public void setCampoJustificativa(String campoJustificativa) {
        this.campoJustificativa = campoJustificativa;
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
    public Short getOrigemFatura() {
        return origemFatura;
    }
    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }
    public LocalDate getReferenciaFatura() {
        return referenciaFatura;
    }
    public void setReferenciaFatura(LocalDate referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }
    
}

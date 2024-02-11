package moduloFaturamento.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditoParaEditarRespostaDTO {

    private Integer matricula;
    private Short dv;
    private Short cdCredito;

    private Integer cdServico; 
    private String descricaoServico;

    private BigDecimal valorCredito;
    private BigDecimal valorSaldo;

    private LocalDate encerramento;

    private Integer refAtendimento;
    private Integer codAtendimento;
	private Integer seqSS;

    private String usuario;

    private Long id;
    private Integer cdParcelamento;
    private Short numeroParcela;
    
    private Short origemFatura;
    private LocalDate referenciaFatura;

    private String justificativa;

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

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(BigDecimal valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public LocalDate getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(LocalDate encerramento) {
        this.encerramento = encerramento;
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

    public Integer getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Integer seqSS) {
        this.seqSS = seqSS;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
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

    public Short getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Short numeroParcela) {
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

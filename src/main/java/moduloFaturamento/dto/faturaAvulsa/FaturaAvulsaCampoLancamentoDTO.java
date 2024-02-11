package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FaturaAvulsaCampoLancamentoDTO {

    private Integer cdCliente;
    private Short dvCliente;
    private String nomeCliente;

    private Integer matriculaImovel;
    private Short dvMatrioculaImovel;

    private String nomeLocalidade;

    private LocalDate refFatura;
    private Short origemFatura;

    private BigDecimal valorFatura;

    private LocalDate dataVencimento;

    private String tipoBaixa;

    private LocalDate dataBaixaCompleto;

    private String mensagem1;
    private String mensagem2;

	private Integer refAtendimento;
	private Integer codAtendimento;
	private Short seqSS;

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Short getDvCliente() {
        return dvCliente;
    }

    public void setDvCliente(Short dvCliente) {
        this.dvCliente = dvCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    public LocalDate getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(LocalDate refFatura) {
        this.refFatura = refFatura;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(String tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    public LocalDate getDataBaixaCompleto() {
        return dataBaixaCompleto;
    }

    public void setDataBaixaCompleto(LocalDate dataBaixaCompleto) {
        this.dataBaixaCompleto = dataBaixaCompleto;
    }

    public String getMensagem1() {
        return mensagem1;
    }

    public void setMensagem1(String mensagem1) {
        this.mensagem1 = mensagem1;
    }

    public BigDecimal getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(BigDecimal valorFatura) {
        this.valorFatura = valorFatura;
    }

    public Short getDvMatrioculaImovel() {
        return dvMatrioculaImovel;
    }

    public void setDvMatrioculaImovel(Short dvMatrioculaImovel) {
        this.dvMatrioculaImovel = dvMatrioculaImovel;
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

    public String getMensagem2() {
        return mensagem2;
    }

    public void setMensagem2(String mensagem2) {
        this.mensagem2 = mensagem2;
    }

}
    

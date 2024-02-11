package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;

public class ParcelamentoFaturaSimulacaoDTO {
    
    private Integer cdCliente;
    private Integer matriculaImovel;

    private BigDecimal somatorioValorFatura;
    private BigDecimal somatorioValorJuros;
    private BigDecimal somatorioJurosMora;

    private BigDecimal somarioDescontosAutomaticosFatura;
    private BigDecimal somatorioDescontosAutomaticosJuros;
    private BigDecimal somatorioDescontosAutomaticosJurosMora;

    private BigDecimal descontoDaNegociacao;

    private boolean correcaoMonetario;
    private BigDecimal porcentagemDaCorrecaoMonetaria;

    private boolean autorizacaoDescontarCredito;

    private boolean incentivoJuridico;
    private BigDecimal valorEntrada;

    private Short numeroDeParcelas;
    
    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }


    public BigDecimal getSomatorioValorFatura() {
        return somatorioValorFatura;
    }

    public void setSomatorioValorFatura(BigDecimal somatorioValorFatura) {
        this.somatorioValorFatura = somatorioValorFatura;
    }

    public BigDecimal getSomatorioValorJuros() {
        return somatorioValorJuros;
    }

    public void setSomatorioValorJuros(BigDecimal somatorioValorJuros) {
        this.somatorioValorJuros = somatorioValorJuros;
    }

    public BigDecimal getSomatorioJurosMora() {
        return somatorioJurosMora;
    }

    public void setSomatorioJurosMora(BigDecimal somatorioJurosMora) {
        this.somatorioJurosMora = somatorioJurosMora;
    }

    public BigDecimal getSomarioDescontosAutomaticosFatura() {
        return somarioDescontosAutomaticosFatura;
    }

    public void setSomarioDescontosAutomaticosFatura(BigDecimal somarioDescontosAutomaticosFatura) {
        this.somarioDescontosAutomaticosFatura = somarioDescontosAutomaticosFatura;
    }

    public BigDecimal getSomatorioDescontosAutomaticosJuros() {
        return somatorioDescontosAutomaticosJuros;
    }

    public void setSomatorioDescontosAutomaticosJuros(BigDecimal somatorioDescontosAutomaticosJuros) {
        this.somatorioDescontosAutomaticosJuros = somatorioDescontosAutomaticosJuros;
    }

    public BigDecimal getSomatorioDescontosAutomaticosJurosMora() {
        return somatorioDescontosAutomaticosJurosMora;
    }

    public void setSomatorioDescontosAutomaticosJurosMora(BigDecimal somatorioDescontosAutomaticosJurosMora) {
        this.somatorioDescontosAutomaticosJurosMora = somatorioDescontosAutomaticosJurosMora;
    }

    public BigDecimal getDescontoDaNegociacao() {
        return descontoDaNegociacao;
    }

    public void setDescontoDaNegociacao(BigDecimal descontoDaNegociacao) {
        this.descontoDaNegociacao = descontoDaNegociacao;
    }

    public boolean isCorrecaoMonetario() {
        return correcaoMonetario;
    }

    public void setCorrecaoMonetario(boolean correcaoMonetario) {
        this.correcaoMonetario = correcaoMonetario;
    }

    public BigDecimal getPorcentagemDaCorrecaoMonetaria() {
        return porcentagemDaCorrecaoMonetaria;
    }

    public void setPorcentagemDaCorrecaoMonetaria(BigDecimal porcentagemDaCorrecaoMonetaria) {
        this.porcentagemDaCorrecaoMonetaria = porcentagemDaCorrecaoMonetaria;
    }

    public boolean isAutorizacaoDescontarCredito() {
        return autorizacaoDescontarCredito;
    }

    public void setAutorizacaoDescontarCredito(boolean autorizacaoDescontarCredito) {
        this.autorizacaoDescontarCredito = autorizacaoDescontarCredito;
    }

    public boolean isIncentivoJuridico() {
        return incentivoJuridico;
    }

    public void setIncentivoJuridico(boolean incentivoJuridico) {
        this.incentivoJuridico = incentivoJuridico;
    }

    public BigDecimal getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Short getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Short numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }


}

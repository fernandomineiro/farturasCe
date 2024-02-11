package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParcelamentoFaturaSimuladoDTO {
    
    private Integer cdCliente;
    private Integer matriculaImovel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    List<LocalDate> refFaturaSelecionadas;

    private BigDecimal descontoDaNegociacao;

    private boolean correcaoMonetario;
    private float porcentagemDaCorrecaoMonetaria;

    private boolean autorizacaoDescontarCredito;

    private boolean incentivoJuridico;

    private BigDecimal valorEntrada;

    private boolean pagamentoAVista;

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

    public List<LocalDate> getRefFaturaSelecionadas() {
        return refFaturaSelecionadas;
    }

    public void setRefFaturaSelecionadas(List<LocalDate> refFaturaSelecionadas) {
        this.refFaturaSelecionadas = refFaturaSelecionadas;
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

    public float getPorcentagemDaCorrecaoMonetaria() {
        return porcentagemDaCorrecaoMonetaria;
    }

    public void setPorcentagemDaCorrecaoMonetaria(float porcentagemDaCorrecaoMonetaria) {
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

    public boolean isPagamentoAVista() {
        return pagamentoAVista;
    }

    public void setPagamentoAVista(boolean pagamentoAVista) {
        this.pagamentoAVista = pagamentoAVista;
    }

    public Short getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Short numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}

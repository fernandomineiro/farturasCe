package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;

public class ParcelamentoFaturaSimulacaoRespostaDTO {

    private BigDecimal totalAgua;
    private BigDecimal totalEsgoto;
    private BigDecimal totalOutrosServicos;

    private BigDecimal subTotal;

    private BigDecimal totalJuros;
    private BigDecimal totalMulta;

    private BigDecimal totalDevido;

    private BigDecimal descontoAutomaticoPrincipal;
    private BigDecimal descontoAutomaticoEncargosFinanceiros;

    private BigDecimal descontoDaNegociacao;

    private BigDecimal descontoCreditoCliente;

    private BigDecimal subTotalDescontando;

    private BigDecimal correcaoMonetaria;

    private BigDecimal totalANegociar;

    private float porcentagemDaCorrecaoMonetaria;
    
    public BigDecimal getTotalAgua() {
        return totalAgua;
    }
    public void setTotalAgua(BigDecimal totalAgua) {
        this.totalAgua = totalAgua;
    }
    public BigDecimal getTotalEsgoto() {
        return totalEsgoto;
    }
    public void setTotalEsgoto(BigDecimal totalEsgoto) {
        this.totalEsgoto = totalEsgoto;
    }
    public BigDecimal getTotalOutrosServicos() {
        return totalOutrosServicos;
    }
    public void setTotalOutrosServicos(BigDecimal totalOutrosServicos) {
        this.totalOutrosServicos = totalOutrosServicos;
    }
    public BigDecimal getDescontoCreditoCliente() {
        return descontoCreditoCliente;
    }
    public void setDescontoCreditoCliente(BigDecimal descontoCreditoCliente) {
        this.descontoCreditoCliente = descontoCreditoCliente;
    }
    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    public BigDecimal getTotalJuros() {
        return totalJuros;
    }
    public void setTotalJuros(BigDecimal totalJuros) {
        this.totalJuros = totalJuros;
    }
    public BigDecimal getTotalMulta() {
        return totalMulta;
    }
    public void setTotalMulta(BigDecimal totalMulta) {
        this.totalMulta = totalMulta;
    }
    public BigDecimal getTotalDevido() {
        return totalDevido;
    }
    public void setTotalDevido(BigDecimal totalDevido) {
        this.totalDevido = totalDevido;
    }
    public BigDecimal getDescontoAutomaticoPrincipal() {
        return descontoAutomaticoPrincipal;
    }
    public void setDescontoAutomaticoPrincipal(BigDecimal descontoAutomaticoPrincipal) {
        this.descontoAutomaticoPrincipal = descontoAutomaticoPrincipal;
    }
    public BigDecimal getDescontoAutomaticoEncargosFinanceiros() {
        return descontoAutomaticoEncargosFinanceiros;
    }
    public BigDecimal getDescontoDaNegociacao() {
        return descontoDaNegociacao;
    }
    public void setDescontoDaNegociacao(BigDecimal descontoDaNegociacao) {
        this.descontoDaNegociacao = descontoDaNegociacao;
    }
    public void setDescontoAutomaticoEncargosFinanceiros(BigDecimal descontoAutomaticoEncargosFinanceiros) {
        this.descontoAutomaticoEncargosFinanceiros = descontoAutomaticoEncargosFinanceiros;
    }
    public BigDecimal getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }
    public void setCorrecaoMonetaria(BigDecimal correcaoMonetaria) {
        this.correcaoMonetaria = correcaoMonetaria;
    }

    public BigDecimal getSubTotalDescontando() {
        return subTotalDescontando;
    }
    public void setSubTotalDescontando(BigDecimal subTotalDescontando) {
        this.subTotalDescontando = subTotalDescontando;
    }
    public BigDecimal getTotalANegociar() {
        return totalANegociar;
    }
    public void setTotalANegociar(BigDecimal totalANegociar) {
        this.totalANegociar = totalANegociar;
    }
    public float getPorcentagemDaCorrecaoMonetaria() {
        return porcentagemDaCorrecaoMonetaria;
    }
    public void setPorcentagemDaCorrecaoMonetaria(float porcentagemDaCorrecaoMonetaria) {
        this.porcentagemDaCorrecaoMonetaria = porcentagemDaCorrecaoMonetaria;
    }

}

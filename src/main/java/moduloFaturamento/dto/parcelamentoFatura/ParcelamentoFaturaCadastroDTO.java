package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParcelamentoFaturaCadastroDTO {

    private Integer cdCliente;
    private Integer matriculaImovel;

    private BigDecimal valorDaEntrada;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamentoDaEntrada;

    private Short diaPagamentoDasParcelas;

    private List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private List<LocalDate> listadasReferencias;
    
    private BigDecimal totalAgua;
    private BigDecimal totalEsgoto;
    private BigDecimal totalOutrosServicos;

    private BigDecimal totalJuros;
    private BigDecimal totalMulta;

    private BigDecimal descontoAutomaticoPrincipal;
    private BigDecimal descontoAutomaticoEncargosFinanceiros;

    private BigDecimal descontoDaNegociacao;

    private BigDecimal descontoCreditoCliente;

    private BigDecimal correcaoMonetaria;

    private BigDecimal totalANegociar;

    private float porcentagemDaCorrecaoMonetaria;

    private BigDecimal somarioDescontosAutomaticosFatura;
    private BigDecimal somatorioDescontosAutomaticosJuros;
    private BigDecimal somatorioDescontosAutomaticosJurosMora;

    private boolean incentivoJuridico;

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

    public BigDecimal getValorDaEntrada() {
        return valorDaEntrada;
    }

    public void setValorDaEntrada(BigDecimal valorDaEntrada) {
        this.valorDaEntrada = valorDaEntrada;
    }

    public LocalDate getDataPagamentoDaEntrada() {
        return dataPagamentoDaEntrada;
    }

    public void setDataPagamentoDaEntrada(LocalDate dataPagamentoDaEntrada) {
        this.dataPagamentoDaEntrada = dataPagamentoDaEntrada;
    }

    public List<ParcelamentoFaturaListaValoresDaParcelaDTO> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }

    public List<LocalDate> getListadasReferencias() {
        return listadasReferencias;
    }

    public void setListadasReferencias(List<LocalDate> listadasReferencias) {
        this.listadasReferencias = listadasReferencias;
    }

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

    public BigDecimal getDescontoAutomaticoPrincipal() {
        return descontoAutomaticoPrincipal;
    }

    public void setDescontoAutomaticoPrincipal(BigDecimal descontoAutomaticoPrincipal) {
        this.descontoAutomaticoPrincipal = descontoAutomaticoPrincipal;
    }

    public BigDecimal getDescontoAutomaticoEncargosFinanceiros() {
        return descontoAutomaticoEncargosFinanceiros;
    }

    public void setDescontoAutomaticoEncargosFinanceiros(BigDecimal descontoAutomaticoEncargosFinanceiros) {
        this.descontoAutomaticoEncargosFinanceiros = descontoAutomaticoEncargosFinanceiros;
    }

    public BigDecimal getDescontoDaNegociacao() {
        return descontoDaNegociacao;
    }

    public void setDescontoDaNegociacao(BigDecimal descontoDaNegociacao) {
        this.descontoDaNegociacao = descontoDaNegociacao;
    }

    public BigDecimal getDescontoCreditoCliente() {
        return descontoCreditoCliente;
    }

    public void setDescontoCreditoCliente(BigDecimal descontoCreditoCliente) {
        this.descontoCreditoCliente = descontoCreditoCliente;
    }
    
    public BigDecimal getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }

    public void setCorrecaoMonetaria(BigDecimal correcaoMonetaria) {
        this.correcaoMonetaria = correcaoMonetaria;
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

    public Short getDiaPagamentoDasParcelas() {
        return diaPagamentoDasParcelas;
    }

    public void setDiaPagamentoDasParcelas(Short diaPagamentoDasParcelas) {
        this.diaPagamentoDasParcelas = diaPagamentoDasParcelas;
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

    public boolean isIncentivoJuridico() {
        return incentivoJuridico;
    }

    public void setIncentivoJuridico(boolean incentivoJuridico) {
        this.incentivoJuridico = incentivoJuridico;
    }
   
}

package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ParcelamentoDaFaturaAuditoriaDTO {

    private long idUsuario;

    private Integer idIncentivoDetalheCliente;

    private Integer cdParcelamento;

    private LocalDateTime dataHoraProcessamento;

    private Integer matriculaImovel;

    private Integer cdCliente;

    private BigDecimal totalANegociar;

    private  BigDecimal totalJuros;

    private BigDecimal totalMulta;

    private BigDecimal descontoAutomaticoPrincipal;

    private BigDecimal descontoAutomaticoEncargosFinanceiros;

    private BigDecimal descontoCreditoCliente;

    private BigDecimal descontoDaNegociacao;

    private BigDecimal correcaoMonetaria;
    
    private List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> listadasReferencias;

    private List<Long> idCredito;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdIncentivoDetalheCliente() {
        return idIncentivoDetalheCliente;
    }

    public void setIdIncentivoDetalheCliente(Integer idIncentivoDetalheCliente) {
        this.idIncentivoDetalheCliente = idIncentivoDetalheCliente;
    }

    public Integer getCdParcelamento() {
        return cdParcelamento;
    }

    public void setCdParcelamento(Integer cdParcelamento) {
        this.cdParcelamento = cdParcelamento;
    }

    public LocalDateTime getDataHoraProcessamento() {
        return dataHoraProcessamento;
    }

    public void setDataHoraProcessamento(LocalDateTime dataHoraProcessamento) {
        this.dataHoraProcessamento = dataHoraProcessamento;
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

    public BigDecimal getTotalANegociar() {
        return totalANegociar;
    }

    public void setTotalANegociar(BigDecimal totalANegociar) {
        this.totalANegociar = totalANegociar;
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

    public List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> getListadasReferencias() {
        return listadasReferencias;
    }

    public void setListadasReferencias(List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> listadasReferencias) {
        this.listadasReferencias = listadasReferencias;
    }

    public BigDecimal getDescontoDaNegociacao() {
        return descontoDaNegociacao;
    }

    public void setDescontoDaNegociacao(BigDecimal descontoDaNegociacao) {
        this.descontoDaNegociacao = descontoDaNegociacao;
    }

    public List<Long> getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(List<Long> idCredito) {
        this.idCredito = idCredito;
    }
    
}

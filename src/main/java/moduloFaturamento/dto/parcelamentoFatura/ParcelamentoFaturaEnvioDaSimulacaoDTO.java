package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParcelamentoFaturaEnvioDaSimulacaoDTO {

    private List<String> email;

    private Integer matriculaImovel;
    private Integer cdCliente;

    private Short quantidadeDeParcelas;
    private BigDecimal valorPrimeiraParcela;
    private BigDecimal valordasRestanteDasParcelas;
    
    private BigDecimal valorEntrada;

    @JsonFormat(pattern = "dd/MM/yyy")
    private LocalDate dataPagamentoDaEntrada;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public Short getQuantidadeDeParcelas() {
        return quantidadeDeParcelas;
    }

    public void setQuantidadeDeParcelas(Short quantidadeDeParcelas) {
        this.quantidadeDeParcelas = quantidadeDeParcelas;
    }

    public BigDecimal getValorPrimeiraParcela() {
        return valorPrimeiraParcela;
    }

    public void setValorPrimeiraParcela(BigDecimal valorPrimeiraParcela) {
        this.valorPrimeiraParcela = valorPrimeiraParcela;
    }

    public BigDecimal getValordasRestanteDasParcelas() {
        return valordasRestanteDasParcelas;
    }

    public void setValordasRestanteDasParcelas(BigDecimal valordasRestanteDasParcelas) {
        this.valordasRestanteDasParcelas = valordasRestanteDasParcelas;
    }

    public BigDecimal getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public LocalDate getDataPagamentoDaEntrada() {
        return dataPagamentoDaEntrada;
    }

    public void setDataPagamentoDaEntrada(LocalDate dataPagamentoDaEntrada) {
        this.dataPagamentoDaEntrada = dataPagamentoDaEntrada;
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
    
    
}

package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ParcelamentoFaturaSimulacaoPagamentoDTO {

    private BigDecimal valorDaEntrada;
    private LocalDate dataPagamentoDaEntrada;
    private List<ParcelamentoFaturaListaValoresDaParcelaDTO> listaParcelas;
    private BigDecimal total;


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
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

        
}

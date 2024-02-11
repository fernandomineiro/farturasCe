package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ParcelamentoFaturaListaValoresDaParcelaDTO {

    private Short numeroDeParcelas;
    private BigDecimal valorParcelas;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    
    public Short getNumeroDeParcelas() {
        return numeroDeParcelas;
    }
    public void setNumeroDeParcelas(Short numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
    public BigDecimal getValorParcelas() {
        return valorParcelas;
    }
    public void setValorParcelas(BigDecimal valorParcelas) {
        this.valorParcelas = valorParcelas;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    
    
}

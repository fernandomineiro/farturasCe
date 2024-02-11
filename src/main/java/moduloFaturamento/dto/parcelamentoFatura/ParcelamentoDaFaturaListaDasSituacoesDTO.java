package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;
import java.util.List;

public class ParcelamentoDaFaturaListaDasSituacoesDTO {

    private BigDecimal total;

    private List<ParcelamentoFaturaSituacaoDasParcelasDTO> listaDasParcelas;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ParcelamentoFaturaSituacaoDasParcelasDTO> getListaDasParcelas() {
        return listaDasParcelas;
    }

    public void setListaDasParcelas(List<ParcelamentoFaturaSituacaoDasParcelasDTO> listaDasParcelas) {
        this.listaDasParcelas = listaDasParcelas;
    }

    
    
}

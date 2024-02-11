package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;
import java.util.List;

public class FaturaAvulsaTotalServicoDTO {

    private BigDecimal totalLancamento;

    private List<FaturaAvulsaListaRespostaDTO> listaLancamentos;

    public FaturaAvulsaTotalServicoDTO() {
    }

    public FaturaAvulsaTotalServicoDTO(BigDecimal totalLancamento,
            List<FaturaAvulsaListaRespostaDTO> listaLancamentos) {
        this.totalLancamento = totalLancamento;
        this.listaLancamentos = listaLancamentos;
    }

    public BigDecimal getTotalLancamento() {
        return totalLancamento;
    }

    public void setTotalLancamento(BigDecimal totalLancamento) {
        this.totalLancamento = totalLancamento;
    }

    public List<FaturaAvulsaListaRespostaDTO> getListaLancamentos() {
        return listaLancamentos;
    }

    public void setListaLancamentos(List<FaturaAvulsaListaRespostaDTO> listaLancamentos) {
        this.listaLancamentos = listaLancamentos;
    }

    
    
}

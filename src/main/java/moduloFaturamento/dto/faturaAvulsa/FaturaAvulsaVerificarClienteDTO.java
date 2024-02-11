package moduloFaturamento.dto.faturaAvulsa;

import java.time.LocalDate;
import java.util.List;

public class FaturaAvulsaVerificarClienteDTO {

    private LocalDate refFatura;
    private Short origemFatura;

    private List<FaturaAvulsaListaLancamentoDTO> listaLancamentoFaturaAvulsa;
    
    public FaturaAvulsaVerificarClienteDTO() {
    }
    public FaturaAvulsaVerificarClienteDTO(LocalDate refFatura, Short origemFatura,
            List<FaturaAvulsaListaLancamentoDTO> listaLancamentoFaturaAvulsa) {
        this.refFatura = refFatura;
        this.origemFatura = origemFatura;
        this.listaLancamentoFaturaAvulsa = listaLancamentoFaturaAvulsa;
    }
    public LocalDate getRefFatura() {
        return refFatura;
    }
    public void setRefFatura(LocalDate refFatura) {
        this.refFatura = refFatura;
    }
    public Short getOrigemFatura() {
        return origemFatura;
    }
    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }
    public List<FaturaAvulsaListaLancamentoDTO> getListaLancamentoFaturaAvulsa() {
        return listaLancamentoFaturaAvulsa;
    }
    public void setListaLancamentoFaturaAvulsa(List<FaturaAvulsaListaLancamentoDTO> listaLancamentoFaturaAvulsa) {
        this.listaLancamentoFaturaAvulsa = listaLancamentoFaturaAvulsa;
    }

    
}

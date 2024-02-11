package moduloFaturamento.dto.faturaAvulsa;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FaturaAvulsaParamServicoDTO {

    @NotNull
    private Short cdCidade;

    @NotNull
    @Valid
    private List<FaturaAvulsaListaLancamentoDTO> listaLancamentos;

    public FaturaAvulsaParamServicoDTO() {
    }
    public FaturaAvulsaParamServicoDTO(Short cdCidade, List<FaturaAvulsaListaLancamentoDTO> listaLancamentos) {
        this.cdCidade = cdCidade;
        this.listaLancamentos = listaLancamentos;
    }

    public Short getCdCidade() {
        return cdCidade;
    }
    public void setCdCidade(Short cdCidade) {
        this.cdCidade = cdCidade;
    }
    public List<FaturaAvulsaListaLancamentoDTO> getListaLancamentos() {
        return listaLancamentos;
    }

    public void setListaLancamentos(List<FaturaAvulsaListaLancamentoDTO> listaLancamentos) {
        this.listaLancamentos = listaLancamentos;
    }

    
    
}

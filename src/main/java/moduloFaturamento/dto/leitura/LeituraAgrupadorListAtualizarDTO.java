package moduloFaturamento.dto.leitura;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class LeituraAgrupadorListAtualizarDTO {
    
    @NotEmpty
    private List<@Valid LeituraParaAtualizarDTO> listaAtualizarDTO;

    public LeituraAgrupadorListAtualizarDTO() {
    }

    public LeituraAgrupadorListAtualizarDTO(List<LeituraParaAtualizarDTO> listaAtualizarDTO) {
        this.listaAtualizarDTO = listaAtualizarDTO;
    }
    
    public List<LeituraParaAtualizarDTO> getListaAtualizarDTO() {
        return listaAtualizarDTO;
    }

    public void setListaAtualizarDTO(List<LeituraParaAtualizarDTO> listaAtualizarDTO) {
        this.listaAtualizarDTO = listaAtualizarDTO;
    }

}

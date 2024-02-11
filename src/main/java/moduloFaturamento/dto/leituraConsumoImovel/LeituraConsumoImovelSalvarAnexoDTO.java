package moduloFaturamento.dto.leituraConsumoImovel;

import moduloFaturamento.dto.leitura.LeituraAnexoDTO;

import javax.validation.constraints.NotNull;

public class LeituraConsumoImovelSalvarAnexoDTO {

    @NotNull
    private Long idLeitura;
    @NotNull
    private LeituraAnexoDTO anexoDTO;

    public Long getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(Long idLeitura) {
        this.idLeitura = idLeitura;
    }

    public LeituraAnexoDTO getAnexoDTO() {
        return anexoDTO;
    }

    public void setAnexoDTO(LeituraAnexoDTO anexoDTO) {
        this.anexoDTO = anexoDTO;
    }
}

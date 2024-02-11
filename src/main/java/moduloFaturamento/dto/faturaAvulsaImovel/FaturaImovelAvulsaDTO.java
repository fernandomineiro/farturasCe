package moduloFaturamento.dto.faturaAvulsaImovel;

import lombok.Data;
import moduloFaturamento.model.Fatura;

@Data
public class FaturaImovelAvulsaDTO {
    private Long id;
    private Integer matriculaImovel;
    private Short origemFatura;
    private Integer refFatura;
    private Short dv;

    public FaturaImovelAvulsaDTO(Fatura fatura) {
        this.id = fatura.getId();
        this.matriculaImovel = fatura.getMatriculaImovel();
        this.origemFatura = fatura.getOrigemFatura();
        this.refFatura = fatura.getRefFatura();
        this.dv = fatura.getDv();
    }
}

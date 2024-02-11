package moduloFaturamento.dto.leituraAnexo;

import moduloFaturamento.dto.leituraAnexo.projection.LeituraAnexoRespostaGridProjectionDTO;

import java.util.List;

public class LeituraAnexoRespostaWrapperDTO {

    private List<LeituraAnexoRespostaGridProjectionDTO> anexos;
    private Integer totalArquivo;
    private long totalRegistro;

    public List<LeituraAnexoRespostaGridProjectionDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<LeituraAnexoRespostaGridProjectionDTO> anexos) {
        this.anexos = anexos;
    }

    public Integer getTotalArquivo() {
        return totalArquivo;
    }

    public void setTotalArquivo(Integer totalArquivo) {
        this.totalArquivo = totalArquivo;
    }

    public long getTotalRegistro() {
        return totalRegistro;
    }

    public void setTotalRegistro(long totalRegistro) {
        this.totalRegistro = totalRegistro;
    }
}

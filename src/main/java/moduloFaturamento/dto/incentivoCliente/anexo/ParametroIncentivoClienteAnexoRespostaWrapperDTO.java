package moduloFaturamento.dto.incentivoCliente.anexo;

import moduloFaturamento.dto.incentivoCliente.anexo.projection.ParametroIncentivoAnexoRespostaGridProjectionDTO;

import java.util.List;

public class ParametroIncentivoClienteAnexoRespostaWrapperDTO {

    private List<ParametroIncentivoAnexoRespostaGridProjectionDTO> anexos;
    private Integer totalArquivo;
    private long totalRegistro;

    public List<ParametroIncentivoAnexoRespostaGridProjectionDTO> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<ParametroIncentivoAnexoRespostaGridProjectionDTO> anexos) {
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

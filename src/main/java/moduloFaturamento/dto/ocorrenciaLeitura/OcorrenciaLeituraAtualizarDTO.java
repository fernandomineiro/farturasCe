package moduloFaturamento.dto.ocorrenciaLeitura;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OcorrenciaLeituraAtualizarDTO {
    @NotNull
    @Max(99)
    private Short codigo;
    @NotBlank
    @Size(max = 30)
    private String descricao;
    @NotBlank @Size(max = 1)
    private String idTipoOcorrencia;
    private Boolean leituraVirtual;

    public OcorrenciaLeituraAtualizarDTO() {
        this.leituraVirtual = false;
    }

    public Short getCodigo() {
        return codigo;
    }

    public void setCodigo(Short codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdTipoOcorrencia() {
        return idTipoOcorrencia;
    }

    public void setIdTipoOcorrencia(String idTipoOcorrencia) {
        this.idTipoOcorrencia = idTipoOcorrencia;
    }

    public Boolean getLeituraVirtual() {
        return leituraVirtual;
    }

    public void setLeituraVirtual(Boolean leituraVirtual) {
        this.leituraVirtual = leituraVirtual;
    }
}

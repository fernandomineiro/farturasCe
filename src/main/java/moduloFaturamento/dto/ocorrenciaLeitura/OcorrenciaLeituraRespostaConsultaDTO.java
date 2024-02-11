package moduloFaturamento.dto.ocorrenciaLeitura;

public class OcorrenciaLeituraRespostaConsultaDTO {
    private Short codigo;
    private String descricao;
    private String idTipoOcorrencia;
    private Boolean leituraVirtual;

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

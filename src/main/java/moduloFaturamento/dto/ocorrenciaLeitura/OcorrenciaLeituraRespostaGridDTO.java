package moduloFaturamento.dto.ocorrenciaLeitura;

public class OcorrenciaLeituraRespostaGridDTO {
    private Short cdOcorrencia;
    private String descricao;
    private String tipo;
    private String leituraVirual;


    public OcorrenciaLeituraRespostaGridDTO() {
    }

    public OcorrenciaLeituraRespostaGridDTO(Short cdOcorrencia, String descricao, String tipo, String leituraVirual) {
        this.cdOcorrencia = cdOcorrencia;
        this.descricao = descricao;
        this.tipo = tipo;
        this.leituraVirual = (leituraVirual.equals("S") ? "SIM" : "NÃO");
    }

    public Short getCdOcorrencia() {
        return cdOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLeituraVirual() {
        return leituraVirual;
    }
}

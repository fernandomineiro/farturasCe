package moduloFaturamento.dto.ocorrenciaLeitura;

public class OcorrenciaLeituraRespostaDropDownDTO {

    private Short id;
    private String descricao;


    public OcorrenciaLeituraRespostaDropDownDTO(Short id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Short getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}

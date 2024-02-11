package moduloFaturamento.dto.leitura;

import java.util.Objects;

public class LeituraAnexoDTO {

    private String descricao;
	private String nomeArquivo;
	private String data;


    public String getNomeArquivo() {
        return nomeArquivo;
    }
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = Objects.requireNonNullElse(descricao, "");
    }
}

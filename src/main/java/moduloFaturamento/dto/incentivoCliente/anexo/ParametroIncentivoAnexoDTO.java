package moduloFaturamento.dto.incentivoCliente.anexo;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ParametroIncentivoAnexoDTO {

    @NotBlank(message = "Favor informar um anexo")
    private String descricao;
    @NotBlank(message = "Favor informar anexo")
    private String nomeArquivo;
    @NotBlank(message = "Favor informar anexo")
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

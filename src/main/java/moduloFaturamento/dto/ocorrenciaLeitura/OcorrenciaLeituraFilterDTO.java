package moduloFaturamento.dto.ocorrenciaLeitura;

import moduloFaturamento.util.CriterioPesquisaComLikeCampoDeTexto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OcorrenciaLeituraFilterDTO {
    private Short id;
    @Size(max = 30)
    private String descricao;
    @Size(max = 1)
    private String idTipoServicoOcorrencia;
    @Pattern(regexp = "[SNsn] {0,1}", message="Parâmetro leitura virtual: ${validatedValue} está inválido. Enviar com S ou N")
    private String leituraVirtual;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        Integer criterioPesquisaContendo = 1;
        this.descricao = CriterioPesquisaComLikeCampoDeTexto.incluirCriterio(descricao,criterioPesquisaContendo);
    }

    public String getIdTipoServicoOcorrencia() {
        return idTipoServicoOcorrencia;
    }

    public void setIdTipoServicoOcorrencia(String idTipoServicoOcorrencia) {
        this.idTipoServicoOcorrencia = idTipoServicoOcorrencia;
    }

    public String getLeituraVirtual() {
        return leituraVirtual;
    }

    public void setLeituraVirtual(String leituraVirtual) {
        this.leituraVirtual = leituraVirtual;
    }
}

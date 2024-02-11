package moduloFaturamento.dto.incentivoCliente;

import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheCadastroDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class IncentivoClienteCadastroDTO {

    @NotBlank @Size(max = 50, message = "Favor informar uma descrição com no máximo 50 dígitos")
    private String descricao;
    @NotNull
    private LocalDateTime dataInicioVigencia;
    @NotNull
    private LocalDateTime dataFimVigencia;
    @NotEmpty(message="Favor informar parâmetro de desconto")
    private List<@Valid IncentivoClienteParametroDetalheCadastroDTO> listaDeDetalhes;
    @NotNull(message="Favor informar anexo")
    private @Valid ParametroIncentivoAnexoDTO anexo;
    @NotNull
    private Integer tipoIncentivo;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDateTime getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDateTime dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public List<IncentivoClienteParametroDetalheCadastroDTO> getListaDeDetalhes() {
        return listaDeDetalhes;
    }

    public void setListaDeDetalhes(List<IncentivoClienteParametroDetalheCadastroDTO> listaDeDetalhes) {
        this.listaDeDetalhes = listaDeDetalhes;
    }

    public ParametroIncentivoAnexoDTO getAnexo() {
        return anexo;
    }

    public void setAnexo(ParametroIncentivoAnexoDTO anexo) {
        this.anexo = anexo;
    }

    public Integer getTipoIncentivo() {
        return tipoIncentivo;
    }

    public void setTipoIncentivo(Integer tipoIncentivo) {
        this.tipoIncentivo = tipoIncentivo;
    }
}

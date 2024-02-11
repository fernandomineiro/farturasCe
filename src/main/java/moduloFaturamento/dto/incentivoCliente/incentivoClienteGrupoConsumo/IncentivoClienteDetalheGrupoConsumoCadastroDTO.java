package moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo;

import javax.validation.constraints.NotNull;

public class IncentivoClienteDetalheGrupoConsumoCadastroDTO {
    @NotNull
    private Integer codigo;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}

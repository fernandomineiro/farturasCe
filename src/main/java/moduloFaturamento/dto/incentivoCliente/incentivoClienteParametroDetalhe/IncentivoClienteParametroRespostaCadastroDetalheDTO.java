package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe;

import java.util.List;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO;

public class IncentivoClienteParametroRespostaCadastroDetalheDTO {

    private final Integer codigoDetalhe;
    private final List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> codigoGrupoConsumo;

    public IncentivoClienteParametroRespostaCadastroDetalheDTO(Integer codigoDetalhe, List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> codigoGrupoConsumo) {
        this.codigoDetalhe = codigoDetalhe;
        this.codigoGrupoConsumo = codigoGrupoConsumo;
    }

    public Integer getCodigoDetalhe() {
        return codigoDetalhe;
    }

    public List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> getCodigoGrupoConsumo() {
        return codigoGrupoConsumo;
    }
}

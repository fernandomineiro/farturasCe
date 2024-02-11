package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe;

import java.util.List;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO;

public class IncentivoClienteParametroRespostaAtualizarDetalheDTO {

    private final Integer codigoDetalhe;
    private final List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> codigoGrupoConsumo;

    public IncentivoClienteParametroRespostaAtualizarDetalheDTO(Integer codigoDetalhe, List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> codigoGrupoConsumo) {
        this.codigoDetalhe = codigoDetalhe;
        this.codigoGrupoConsumo = codigoGrupoConsumo;
    }

    public Integer getCodigoDetalhe() {
        return codigoDetalhe;
    }

    public List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> getCodigoGrupoConsumo() {
        return codigoGrupoConsumo;
    }
}

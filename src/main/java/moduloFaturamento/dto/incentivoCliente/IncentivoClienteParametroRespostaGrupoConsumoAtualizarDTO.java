package moduloFaturamento.dto.incentivoCliente;

public class IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO {
    private final Integer idParametroDetalhe;
    private final Integer idGrupoConsumo;

    public IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO(Integer idParametroDetalhe, Integer idGrupoConsumo) {
        this.idParametroDetalhe = idParametroDetalhe;
        this.idGrupoConsumo = idGrupoConsumo;
    }

    public Integer getIdParametroDetalhe() {
        return idParametroDetalhe;
    }

    public Integer getIdGrupoConsumo() {
        return idGrupoConsumo;
    }
}

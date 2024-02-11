package moduloFaturamento.dto.incentivoCliente;

public class IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO {
    private final Integer idParametroDetalhe;
    private final Integer idGrupoConsumo;

    public IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO(Integer idParametroDetalhe, Integer idGrupoConsumo) {
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

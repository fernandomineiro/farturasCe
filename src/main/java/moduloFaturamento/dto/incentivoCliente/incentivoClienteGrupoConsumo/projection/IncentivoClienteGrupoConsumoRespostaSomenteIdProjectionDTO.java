package moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection;

public class IncentivoClienteGrupoConsumoRespostaSomenteIdProjectionDTO {

    private final Integer codigo;

    public IncentivoClienteGrupoConsumoRespostaSomenteIdProjectionDTO(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
}

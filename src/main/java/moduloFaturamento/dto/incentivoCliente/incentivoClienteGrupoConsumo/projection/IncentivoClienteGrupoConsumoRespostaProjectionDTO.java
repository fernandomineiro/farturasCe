package moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection;

public class IncentivoClienteGrupoConsumoRespostaProjectionDTO {

    private final Integer codigo;
    private final String nome;

    public IncentivoClienteGrupoConsumoRespostaProjectionDTO(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}

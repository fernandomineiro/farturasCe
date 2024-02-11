package moduloFaturamento.dto.imovel.projection;

public class CodigoClienteDoImovelDTO {
    private Integer codigo;
    private Short dv;

    public CodigoClienteDoImovelDTO() {
    }

    public CodigoClienteDoImovelDTO(Integer codigo, Short dv) {
        this.codigo = codigo;
        this.dv = dv;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Short getDv() {
        return dv;
    }
}

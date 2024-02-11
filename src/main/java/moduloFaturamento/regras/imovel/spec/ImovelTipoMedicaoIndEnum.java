package moduloFaturamento.regras.imovel.spec;

public enum ImovelTipoMedicaoIndEnum {
    NAO_ESPECIFICADO(null),
    MATRICULA_ISOLADA((short)0),
    MATRICULA_MACRO((short)1),
    MATRICULA_MICRO((short)2);

    private final Short tipo;

    ImovelTipoMedicaoIndEnum(Short tipo) {
        this.tipo = tipo;
    }

    public Short getTipo() {
        return tipo;
    }
}

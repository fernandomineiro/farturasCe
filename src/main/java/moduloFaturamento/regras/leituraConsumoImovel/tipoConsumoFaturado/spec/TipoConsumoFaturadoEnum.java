package moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.spec;

public enum TipoConsumoFaturadoEnum {
    SEM_CONSUMO((short)0),
    MEDIDO((short)1),
    BASICO((short)2),
    MEDIA((short)3),
    MINIMO((short)4),
    LIMINFERIOR((short)5),
    LIMSUPERIOR((short)6),
    INFORMADO((short)7),
    PROJETADO((short)8),
    CONTRATADO((short)9);

    private final Short tipo;

    TipoConsumoFaturadoEnum(Short tipo) {
        this.tipo = tipo;
    }

    public Short getTipo() {
        return tipo;
    }
}

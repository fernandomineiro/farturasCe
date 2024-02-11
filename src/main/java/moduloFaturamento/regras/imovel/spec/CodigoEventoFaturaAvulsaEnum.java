package moduloFaturamento.regras.imovel.spec;

public enum CodigoEventoFaturaAvulsaEnum {
  
    FATURAMENTO((short) 10),
    ESTORNO_FATURAMENTO_REFATURAMENTO((short) 12),
    REFATURAMENTO_NOVO_VALOR((short) 13);
    
    private final Short tipo;

    CodigoEventoFaturaAvulsaEnum(Short tipo) {
        this.tipo = tipo;
    }

    public Short getTipo() {
        return tipo;
    }
}

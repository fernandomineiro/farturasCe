package moduloFaturamento.regras.cobrancaFatura.spec;

public enum CobrancaFaturaTipoNumeroSolicitacaoEnum {
    SOLICITACAO_SERVICO((short)0),
    ITEM_ATENDIMENTO((short)1);

    private final Short tipo;

    CobrancaFaturaTipoNumeroSolicitacaoEnum(Short tipo) {
        this.tipo = tipo;
    }

    public Short getTipo() {
        return tipo;
    }
}

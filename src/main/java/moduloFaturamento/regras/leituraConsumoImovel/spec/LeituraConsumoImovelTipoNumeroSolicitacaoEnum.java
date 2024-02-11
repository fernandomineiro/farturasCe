package moduloFaturamento.regras.leituraConsumoImovel.spec;

public enum LeituraConsumoImovelTipoNumeroSolicitacaoEnum {
    SOLICITACAO_SERVICO((short)0),
    ITEM_ATENDIMENTO((short)1);

    private final Short tipo;

    LeituraConsumoImovelTipoNumeroSolicitacaoEnum(Short tipo) {
        this.tipo = tipo;
    }

    public Short getTipo() {
        return tipo;
    }
}

package moduloFaturamento.regras.cronogramaFaturamento.spec;

public enum CronogramaFaturamentoFaseEnum {

    GERAR_ESPELHO((short)0),
    INCLUIR_LEITURA((short)1),
    FATURAR((short)2),
    CONSOLIDAR((short)3),
    EMITIR_FATURA((short)4),
    ENCERRADO((short)5);

    private Short valor;

    CronogramaFaturamentoFaseEnum(Short valor) {
        this.valor = valor;
    }

    public Short getValor() {
        return valor;
    }
}

package moduloFaturamento.dto.imovelArrecadacao;

public class ArrecadacaoImovelDadosDebitoContaCorrenteDTO {
    private Short codigoAgenciaArrecadadora;
    private String numeroContaCorrente;

    public Short getCodigoAgenciaArrecadadora() {
        return codigoAgenciaArrecadadora;
    }

    public void setCodigoAgenciaArrecadadora(Short codigoAgenciaArrecadadora) {
        this.codigoAgenciaArrecadadora = codigoAgenciaArrecadadora;
    }

    public String getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }
}

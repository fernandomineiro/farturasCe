package moduloFaturamento.regras.mecanicas.tarifa.spec;

public enum TarifaArquivoParcelaSpec {
	CODIGO_DA_TARIFA(" "), 
	DATA_TARIFA(" "), 
	GRUPO_DE_CONSUMO(" "), 
	LIMITE_DE_FAIXAS(" "), 
	AGUA_PARCELAS_FIXA("Parcela Fixa"), 
	AGUA_PARCELAS_VARIAVEL("Parcela Variável"), 
	ESGOTO_PARCELAS_FIXA("Parcela Fixa"), 
	ESGOTO_PARCELAS_VARIAVEL("Parcela Variável"), 
	ESGOTO_CAT_PARCELAS_FIXA("Parcela Fixa"), 
	ESGOTO_CAT_PARCELAS_VARIAVEL("Parcela Variável"), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_FIXA("Parcela Fixa"), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_VARIAVEL("Parcela Variável");

	private final String nomeCabecalho;

	TarifaArquivoParcelaSpec(String nomeCabecalho) {

		this.nomeCabecalho = nomeCabecalho;
	}

	public String getNomeCabecalho() {

		return nomeCabecalho;
	}
}

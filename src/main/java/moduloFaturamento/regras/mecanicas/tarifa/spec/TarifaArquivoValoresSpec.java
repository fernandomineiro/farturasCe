package moduloFaturamento.regras.mecanicas.tarifa.spec;

public enum TarifaArquivoValoresSpec {
	CODIGO_DA_TARIFA("Código da Tarifa"), 
	DATA_TARIFA("Data da Tarifa"), 
	GRUPO_DE_CONSUMO("Grupo de Consumo"), 
	LIMITE_DE_FAIXAS("Limites de Faixas"), 
	AGUA_PARCELAS_FIXA("R$/mês"), 
	AGUA_PARCELAS_VARIAVEL("R$/m3"), 
	ESGOTO_PARCELAS_FIXA("R$/mês"), 
	ESGOTO_PARCELAS_VARIAVEL("R$/m3"), 
	ESGOTO_CAT_PARCELAS_FIXA("R$/mês"), 
	ESGOTO_CAT_PARCELAS_VARIAVEL("R$/m3"), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_FIXA("R$/mês"), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_VARIAVEL("R$/m3");
	

	private final String nomeCabecalho;

	TarifaArquivoValoresSpec(String nomeCabecalho) {

		this.nomeCabecalho = nomeCabecalho;
	}

	public String getNomeCabecalho() {

		return nomeCabecalho;
	}
}

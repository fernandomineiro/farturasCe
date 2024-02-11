package moduloFaturamento.regras.mecanicas.tarifa.spec;

public enum TarifaArquivoEntidadeSpec {	
	CODIGO_DA_TARIFA(" "), 
	DATA_TARIFA(" "), 
	GRUPO_DE_CONSUMO(" "), 
	LIMITE_DE_FAIXAS(" "), 
	AGUA_PARCELAS_FIXA("Água"), 
	AGUA_PARCELAS_VARIAVEL(" "), 
	ESGOTO_PARCELAS_FIXA("Esgoto CAT"), 
	ESGOTO_PARCELAS_VARIAVEL(" "), 
	ESGOTO_CAT_PARCELAS_FIXA("Esgoto CA"), 
	ESGOTO_CAT_PARCELAS_VARIAVEL(" "), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_FIXA("Disponibilidade Esgoto CA"), 
	DISPONIBILIDADE_ESGOTO_CA_PARCELAS_VARIAVEL(" ");

	private final String nomeCabecalho;

	TarifaArquivoEntidadeSpec(String nomeCabecalho) {

		this.nomeCabecalho = nomeCabecalho;
	}

	public String getNomeCabecalho() {

		return nomeCabecalho;
	}

}

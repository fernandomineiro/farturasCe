package moduloFaturamento.regras.mecanicas.qualidadeDaAgua.spec;

public enum AmostrasMinimasExigidasSpec {

	REFERENCIA("Referência"), LOCALIDADE("Localidade"), CLORO("Cloro"), COLIFORMES_TOTAIS("Coliformes totais"), COR("Cor"), FLUOR("Flúor"),
	TURBIDEZ("Turbidez"), ESCHERICHIA_COLI("Escherichia Coli");

	private final String nomeCabecalho;

	AmostrasMinimasExigidasSpec(String nomeCabecalho) {

		this.nomeCabecalho = nomeCabecalho;
	}

	public String getNomeCabecalho() {

		return nomeCabecalho;
	}

}

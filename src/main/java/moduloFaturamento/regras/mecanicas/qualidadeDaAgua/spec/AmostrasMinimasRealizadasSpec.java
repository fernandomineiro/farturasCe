package moduloFaturamento.regras.mecanicas.qualidadeDaAgua.spec;

public enum AmostrasMinimasRealizadasSpec {

	REF_AMOSTRAS("Ref_Amostras"), CODIGO("Código"), CLORO_R("Cloro_R"), COLIFORMES_R("Coliformes_R"), COR_R("Cor_R"), FLUOR_R("Flúor_R"), E_COLI_R("E_Coli_R"),
	TURBIDEZ_R("Turbidez_R"), CLORO_A("Cloro_A"), COLIFORMES_A("Coliformes_A"), COR_A("Cor_A"), FLUOR_A("Flúor_A"), E_COLI_A("E_Coli_A"),
	TURBIDEZ_A("Turbidez_A"), CONCLUSÃO("Conclusão");

	private final String nomeCabecalho;

	AmostrasMinimasRealizadasSpec(String nomeCabecalho) {

		this.nomeCabecalho = nomeCabecalho;
	}

	public String getNomeCabecalho() {

		return nomeCabecalho;
	}

}

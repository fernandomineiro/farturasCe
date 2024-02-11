package moduloFaturamento.dto.parcelamentoFatura.projection;

public interface ParcelamentoFaturaPesquisaResponseProjectionDTO {

    Integer getMatriculaImovel();
	Short getDvMatriculaImovel();
	String getDescricaoGrupoDeConsumo();
	String getNomeDaCidade();
	String getNomeDoBairro();
	String getNomeDaRua();
	Short getNumeroImovel();
	String getComplemento();
}

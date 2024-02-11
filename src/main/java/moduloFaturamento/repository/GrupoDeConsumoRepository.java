package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.model.GrupoDeConsumo;

@Repository
public interface GrupoDeConsumoRepository extends JpaRepository<GrupoDeConsumo, Short> {

	@Query(nativeQuery = true, value = "SELECT GRUPO_CONSUMO AS grupoDeConsumo, DC_GRUPO_CONSUMO as dcGrupoDeConsumo FROM CDTGC WHERE PERM_SEL_FILTRO_PESQUISA = 'S'")
	List<GrupoConsumoDropDownProjectionDTO> buscarGruposDeConsumoParaPesquisa();

	@Query(nativeQuery = true, value = "SELECT grupoDeConsumo.GRUPO_CONSUMO AS grupoDeConsumo, grupoDeConsumo.DC_GRUPO_CONSUMO as dcGrupoDeConsumo FROM CDTGC grupoDeConsumo "
			+ " JOIN CDAIM imovel ON imovel.GRUPO_CONSUMO = grupoDeConsumo.GRUPO_CONSUMO WHERE imovel.MATRICULA_IMOVEL = :matricula")
	GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorMatriculaImovel(Integer matricula);

	@Query(nativeQuery = true, value = "SELECT GRUPO_CONSUMO AS grupoDeConsumo, DC_GRUPO_CONSUMO as dcGrupoDeConsumo FROM CDTGC WHERE GRUPO_CONSUMO = :grupoDeConsumo")
	GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorCodigo(Short grupoDeConsumo);

	@Query(nativeQuery = true, value = "SELECT DISTINCT(GRUPO_CONSUMO) FROM FTTFA WHERE REF_FATURA = :refParaCalculoTarifaMedia")
    List<Short> buscarListaDeGupoDeConsumoDaRefTarifaMedia(Integer refParaCalculoTarifaMedia);
}

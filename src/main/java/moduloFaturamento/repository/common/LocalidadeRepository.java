package moduloFaturamento.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.bairroCiclo.projection.CicloEBairroProjectionDTO;
import moduloFaturamento.dto.bairroCiclo.projection.LocalidadeIdNomeProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaLocalidadeDTO;
import moduloFaturamento.model.common.Localidade;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Short> {

	List<Localidade> findByDataHoraExclusaoIsNullOrderByDcCidade();

	@Query(nativeQuery = true, value = "SELECT DISTINCT CICLO FROM CDAIM WHERE CD_CIDADE = :cdCidade AND CICLO <> 0")
	List<Short> buscarCiclosdaLocalidade(Short cdCidade);

	@Query(nativeQuery = true, value = "SELECT DISTINCT bairro.CD_BAIRRO AS id, bairro.DC_BAIRRO AS nome" + " FROM CDABA bairro" + " RIGHT JOIN CDAIM im"
			+ " ON bairro.CD_BAIRRO = im.CD_BAIRRO AND bairro.CD_CIDADE = im.CD_CIDADE " + " WHERE bairro.CD_CIDADE = :cdCidade" + " ORDER BY bairro.DC_BAIRRO")
	List<LocalidadeIdNomeProjectionDTO> buscarBairrodaLocalidade(Short cdCidade);

	@Query(nativeQuery = true, value = "SELECT DISTINCT bairro.CD_BAIRRO AS id, bairro.DC_BAIRRO AS nome, im.CICLO AS ciclo" + " FROM CDABA bairro"
			+ " INNER JOIN CDAIM im" + " on bairro.CD_CIDADE = im.CD_CIDADE AND bairro.CD_Bairro = im.CD_BAIRRO"
			+ " WHERE bairro.CD_CIDADE = :cdCidade AND im.CICLO <> 0" + " AND (:cdBairro is null or bairro.CD_BAIRRO = :cdBairro)"
			+ " AND (:ciclo is null or im.CICLO = :ciclo)")
	List<CicloEBairroProjectionDTO> pesquisarLocalidadeEBairroECiclo(Short cdCidade, @Param("cdBairro") Short cdBairro, @Param("ciclo") Short ciclo);

	@Query(value = "SELECT localidade FROM Localidade localidade WHERE localidade.cdCidade = :cdCidade AND localidade.dataHoraExclusao IS NULL")
	Optional<Localidade> buscarLocalidadePorCodigoCidade(@Param("cdCidade") Short cdCidade);

	@Query(value = "SELECT localidade.cdTarifa FROM Localidade localidade WHERE localidade.cdCidade = :cdCidade AND localidade.dataHoraExclusao IS NULL")
	Short buscarCodigoTarifaPorCodigoCidade(@Param("cdCidade") Short cdCidade);

	@Query(nativeQuery = true, value = "SELECT * FROM CDACI WHERE CD_TARIFA <> 0 AND DATA_HORA_EXCLUSAO IS NULL ORDER BY DC_CIDADE")
	List<Localidade> buscarLocalidadesQueTemFaturamento();

	@Query(nativeQuery = true, value = "SELECT CD_CIDADE AS cdCidade, DC_CIDADE AS descricaoCidade"
			+ " FROM CDACI WHERE CD_CIDADE <> 0 AND DC_CIDADE NOT LIKE '%DESATIVADA%' ORDER BY DC_CIDADE ASC")
	List<FaturaAvulsaLocalidadeDTO> listaLocalidadeComIdENomeCidade();
	
	@Query(nativeQuery = true, value = "SELECT cidade.* FROM CDACI cidade JOIN CDAIM imovel ON cidade.CD_CIDADE = imovel.CD_CIDADE WHERE imovel.MATRICULA_IMOVEL = :matricula")
	Optional<Localidade> buscarLocalidadePorMatriculaImovel(Integer matricula);
}

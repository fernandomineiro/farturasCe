package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.imovelAtualizacaoRota.SiscomGisRotaProjection;
import moduloFaturamento.model.common.Imovel;

@Repository
public interface ImovelAtualizacaoRotaRepository extends JpaRepository<Imovel, Integer> {

	@Query(value = "IF OBJECT_ID(N'tempdb..#ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO') IS NOT NULL BEGIN DROP TABLE #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO END "
			+ " IF OBJECT_ID(N'tempdb..#ATUALIZACAO_ROTA_SISCOM') IS NOT NULL BEGIN DROP TABLE #ATUALIZACAO_ROTA_SISCOM END "
			+ " IF OBJECT_ID(N'tempdb..#ATUALIZACAO_ROTA_GIS') IS NOT NULL BEGIN DROP TABLE #ATUALIZACAO_ROTA_GIS END SELECT "
			+ " (imovel.MATRICULA_IMOVEL * 10) + imovel.DV AS siscomMatricula, imovel.CICLO AS siscomCiclo, "
			+ " imovel.CD_CIDADE AS siscomCdCidade, imovel.SEQ_ROTA AS siscomSeq INTO #ATUALIZACAO_ROTA_SISCOM FROM "
			+ " CDAIM imovel WHERE imovel.CD_CIDADE = :cdCidade AND imovel.CICLO = :ciclo SELECT "
			+ " CAST(DC.MATRICULA AS INT) AS gisMatricula, CAST(C.CD_CIDADE AS INT) AS gisCdCidade, CAST(C.CICLO AS INT) AS gisCiclo, "
			+ " ROW_NUMBER() OVER ( ORDER BY DC.ORDEN_RUTA) AS gisSeq INTO #ATUALIZACAO_ROTA_GIS FROM "
			+ " [GISSERVER].[GISC_CORE].[UGISC].[DERIVACAO_CAMINHAMENTO_MV] DC INNER JOIN [GISSERVER].[GISC_CORE].[UGISC].[CAMINHAMENTO_MV] C ON "
			+ " DC.ID_CAMINHAMENTO = C.ID_CAMINHAMENTO WHERE C.CD_CIDADE = :cdCidade OR C.CICLO = :ciclo ORDER BY DC.ORDEN_RUTA "
			+ " SELECT * INTO #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO FROM #ATUALIZACAO_ROTA_SISCOM "
			+ " FULL JOIN #ATUALIZACAO_ROTA_GIS ON #ATUALIZACAO_ROTA_SISCOM.siscomMatricula = #ATUALIZACAO_ROTA_GIS.gisMatricula "
			+ " DROP TABLE #ATUALIZACAO_ROTA_SISCOM DROP TABLE #ATUALIZACAO_ROTA_GIS SELECT * FROM #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO "
			+ " WHERE gisCdCidade = :cdCidade AND gisCiclo = :ciclo AND siscomMatricula = gisMatricula UNION "
			+ " SELECT * FROM #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO WHERE siscomCiclo <> gisCiclo AND siscomMatricula = gisMatricula UNION "
			+ " SELECT * FROM #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO WHERE siscomCdCidade <> gisCdCidade AND siscomMatricula = gisMatricula UNION "
			+ " SELECT * FROM #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO WHERE gisCdCidade = :cdCidade AND gisCiclo = :ciclo AND siscomMatricula IS NULL "
			+ " UNION SELECT * FROM #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO "
			+ " WHERE siscomCdCidade = :cdCidade AND siscomCiclo = :ciclo AND gisMatricula IS NULL "
			+ " DROP TABLE #ATUALIZACAO_ROTA_SISCOM_GIS_JUNCAO", nativeQuery = true)
	List<SiscomGisRotaProjection> buscarAtualizacaoSiscomGisRotaProjection(Integer cdCidade, Integer ciclo);
}

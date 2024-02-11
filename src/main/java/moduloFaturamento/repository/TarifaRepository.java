package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import moduloFaturamento.dto.tarifa.projection.PesquisaTarifaLocalidadeProjectionDTO;
import moduloFaturamento.dto.tarifa.projection.PesquisaTarifaProjectionDTO;
import moduloFaturamento.model.IdTarifa;
import moduloFaturamento.model.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, IdTarifa> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT tarifa.CD_TARIFA AS id,"
                                            + " tarifa.DT_TARIFA AS dataT,"
                                            + " tarifa.GRUPO_CONSUMO AS grupo,"
                                            + " tarifa.LIMITE_FAIXA AS limite,"
                                            + " tarifa.VR_AGUA AS agua,"
                                            + " tarifa.VR_AGUA_PFIXA AS vrAguaPfixa," 

                                            + " tarifa.VR_ESG_TRATADO AS vrEsgotoTratado,"
                                            + " tarifa.VR_ESG_TRAT_PFIXA AS vrEsgotoTratatoPfixo,"
                                           
                                            + " tarifa.VR_ESG_N_TRATADO AS vrEsgotoNaoTratado,"
                                            + " tarifa.VR_ESG_N_TRAT_PFIXA AS vrEsgotoNaoTratadoPfixa,"

                                            + " tarifa.VR_DISP_ESG AS vrDispEsgoto,"
                                            + " tarifa.VR_DISP_ESG_PFIXA AS vrDispEsgotoPfixo"
                                        + " FROM CDACI local"
                                        + " INNER JOIN FTTTR tarifa"
                                        + " on local.CD_TARIFA = tarifa.CD_TARIFA"
                                        + " WHERE (:idLocalidade is null or local.CD_TARIFA = :idLocalidade)"
                                        + " AND (:idTarifca is null or tarifa.CD_TARIFA = :idTarifca)"
                                        + " AND (:dataTarifa is null or tarifa.DT_TARIFA <= :dataTarifa)")
    List<PesquisaTarifaProjectionDTO> pesquisaTarifaComLocalidade(@Param("idLocalidade") Short idLocalidade, 
                                                                 @Param("idTarifca") Short idTarifca,
                                                                 @Param("dataTarifa") Integer dataTarifa);

    @Query(nativeQuery = true, value = "SELECT DISTINCT tarifa.CD_TARIFA AS id,"
                                                    + " tarifa.DT_TARIFA AS dataT,"
                                                    + " tarifa.GRUPO_CONSUMO AS grupo,"
                                                    + " tarifa.LIMITE_FAIXA AS limite,"
                                                    + " tarifa.VR_AGUA AS agua,"
                                                    + " tarifa.VR_AGUA_PFIXA AS vrAguaPfixa," 
        
                                                    + " tarifa.VR_ESG_TRATADO AS vrEsgotoTratado,"
                                                    + " tarifa.VR_ESG_TRAT_PFIXA AS vrEsgotoTratatoPfixo,"
                                                   
                                                    + " tarifa.VR_ESG_N_TRATADO AS vrEsgotoNaoTratado,"
                                                    + " tarifa.VR_ESG_N_TRAT_PFIXA AS vrEsgotoNaoTratadoPfixa,"
        
                                                    + " tarifa.VR_DISP_ESG AS vrDispEsgoto,"
                                                    + " tarifa.VR_DISP_ESG_PFIXA AS vrDispEsgotoPfixo"
                                                + " FROM FTTTR tarifa"
                                                + " WHERE (:idTarifca is null or tarifa.CD_TARIFA = :idTarifca)"
                                                + " AND (:dataTarifa is null or tarifa.DT_TARIFA <= :dataTarifa)"
                                                + " ORDER BY tarifa.DT_TARIFA DESC")
    List<PesquisaTarifaProjectionDTO> pesquisaTarifaSemLocalidade(@Param("idTarifca") Short idTarifca, @Param("dataTarifa") Integer dataTarifa);

    @Query(nativeQuery = true, value = "SELECT DISTINCT tarifa.CD_TARIFA AS id," 
                                        + " localidade.DC_CIDADE AS nome"
                                        + " FROM CDACI localidade INNER JOIN FTTTR tarifa" 
                                        + " ON localidade.CD_TARIFA = tarifa.CD_TARIFA")
    List<PesquisaTarifaLocalidadeProjectionDTO> buscarBairrodaLocalidade();

    List<Tarifa> findByIdIdTarifaAndIdDataTarifa(Integer idTarifa, Integer dataTarifaInteger);

    @Query(nativeQuery = true, value = "SELECT TOP 1 ID FROM FTTTR WHERE CD_TARIFA = :idTarifa AND DT_TARIFA = :dataTarifa ORDER BY ID DESC")
    Long buscarCampoIdAuditoria(Integer idTarifa, Integer dataTarifa);

    @Query(nativeQuery = true, value = "SELECT TOP 1 fatura.DT_TARIFA as dataexistente" 
                                        + " FROM FTTFA AS fatura"
                                        + " WHERE fatura.CD_TARIFA = :idTarifa AND fatura.DT_TARIFA = :dataTarifaInteger")
    Integer pesquisarTarifaDataEmFatura(Integer idTarifa, Integer dataTarifaInteger);

    @Query(nativeQuery = true, value = "SELECT TOP 1 crono.DT_TARIFA AS datat"
                                        + " FROM CDACI local"
                                        + " LEFT JOIN FTTCR crono"
                                        + " ON crono.CD_CIDADE = local.CD_CIDADE"
                                        + " WHERE local.CD_TARIFA = :idTarifa AND crono.DT_TARIFA = :dataTarifaInteger")
    Integer pesquisarTarifaCronogramaLocalidade(Integer idTarifa, Integer dataTarifaInteger);


    @Query(value = "SELECT TOP 1 MAX(DT_TARIFA) FROM FTTTR WHERE CD_TARIFA = :cdTarifa AND DT_TARIFA < :dataLeitura", nativeQuery = true)
    Integer buscarMaiorDataTarifaMenorQueDataLeituraCronograma(Short cdTarifa, Integer dataLeitura);

	@Query(nativeQuery = true, value = "DECLARE @DATA_TARIFA INT SET @DATA_TARIFA = (SELECT MAX(DT_TARIFA) FROM FTTTR "
			+ " WHERE DT_TARIFA <= :dataReferencia AND GRUPO_CONSUMO = :grupoConsumo AND CD_TARIFA = :cdTarifa) SELECT "
			+ " * FROM FTTTR WHERE DT_TARIFA = @DATA_TARIFA AND GRUPO_CONSUMO = :grupoConsumo AND CD_TARIFA = :cdTarifa ORDER BY LIMITE_FAIXA ASC")
    List<Tarifa> buscarFaixasTarifarias(Integer dataReferencia, Short grupoConsumo, Short cdTarifa);
    
}

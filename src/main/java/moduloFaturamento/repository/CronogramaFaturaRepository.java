package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaCicloProjectionDTO;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.IdCronogramaFatura;

@Repository
public interface CronogramaFaturaRepository extends JpaRepository<CronogramaFatura, IdCronogramaFatura> {

	Optional<CronogramaFatura> findById(Long id);

	CronogramaFatura findByIdCronogramaFatura(IdCronogramaFatura id);

	List<CronogramaFatura> findByIdCronogramaFatura_CicloAndIdCronogramaFatura_RefCronograma(Short ciclo, Integer dataReferencia);

	@Query(value = "SELECT cronograma.idCronogramaFatura.cdCidade FROM CronogramaFatura  cronograma WHERE cronograma.idCronogramaFatura.ciclo = :ciclo AND cronograma.idCronogramaFatura.refCronograma = :dataReferencia")
	List<Short> buscarCidadesCadastradasPorCicloEReferencia(@Param("ciclo") Short ciclo, @Param("dataReferencia") Integer dataReferencia);

	@Query(value = "SELECT TOP 1 CD_CIDADE FROM FTTCR  WHERE CICLO = :ciclo AND REF_CRONOGRAMA = :dataReferencia", nativeQuery = true)
	Short buscarPeloMenosUmaCidadeCadastradaPorCicloEReferencia(Short ciclo, Integer dataReferencia);

	@Query(value = "SELECT TOP 1 DT_LEITURA_R FROM FTTCR  WHERE CICLO = :ciclo AND REF_CRONOGRAMA = :dataReferencia AND CD_CIDADE = :cdCidade", nativeQuery = true)
	Integer buscarPeloMenosUmaDataLeituraPorCicloReferenciaECidade(Short ciclo, Integer dataReferencia, Short cdCidade);

	@Query(value = "SELECT TOP 1 * FROM FTTCR  WHERE CICLO = :ciclo AND REF_CRONOGRAMA = :dataReferencia AND CD_CIDADE = :cdCidade AND FASE = 5", nativeQuery = true)
	CronogramaFatura buscarPeloMenosUmaLeituraPorCicloReferenciaECidadeQueEstejaComCicloFechado(Short ciclo, Integer dataReferencia, Short cdCidade);

	@Query(value = "SELECT cronograma.id FROM CronogramaFatura cronograma " +
			"WHERE cronograma.idCronogramaFatura.cdCidade = :cdCidade AND cronograma.idCronogramaFatura.ciclo = :ciclo AND cronograma.idCronogramaFatura.refCronograma = :dataReferencia")
	Long buscarIdCronogramaPorCdCidadeCicloDataReferencia(@Param("cdCidade") Short cdCidade, @Param("ciclo") Short ciclo, @Param("dataReferencia") Integer dataReferencia);

	@Query(value = "SELECT cronograma FROM CronogramaFatura cronograma WHERE cronograma.id = :id")
	CronogramaFatura buscarCronogramaExistentePorId(@Param("id") Long id);

	@Query(value = "SELECT new moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO(cronograma.id, " +
			"cronograma.idCronogramaFatura.refCronograma, localidade.cdCidade, localidade.dcCidade, cronograma.idCronogramaFatura.ciclo, cronograma.dataLeituraRealizada, " +
			"cronograma.dataFaturamentoRealizada, cronograma.dataConsolidaRealizada) " +
			"FROM CronogramaFatura cronograma " +
			"INNER JOIN Localidade localidade " +
			"ON cronograma.idCronogramaFatura.cdCidade =  localidade.cdCidade " +
			"WHERE cronograma.id = :id")
	CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO buscarDetalheCicloFaturamentoDeUmCronogramaExistentePorId(@Param("id") Long id);

	@Query(value = "SELECT cronograma.fase.id FROM CronogramaFatura cronograma WHERE cronograma.id = :id")
	Short buscarFaseCronogramaExistentePorId(@Param("id") Long id);

	@Query(value = "SELECT cronograma FROM CronogramaFatura cronograma " +
			"WHERE cronograma.idCronogramaFatura.cdCidade = :cdCidade AND cronograma.idCronogramaFatura.ciclo = :ciclo AND cronograma.idCronogramaFatura.refCronograma = :dataReferencia")
	CronogramaFatura buscarCronogramaExistentePorCdCidadeCicloDataReferencia(@Param("cdCidade") Short cdCidade, @Param("ciclo") Short ciclo, @Param("dataReferencia") Integer dataReferencia);

	@Query(nativeQuery = true, value = "SELECT DISTINCT CICLO FROM FTTCR WHERE CD_CIDADE = :cdCidade")
	List<Short> buscarCiclosPorLocalidade(Short cdCidade);
	
	@Query(nativeQuery = true, value = "SELECT CICLO AS ciclo, CASE WHEN FASE = 0 THEN 'N' ELSE 'S' END AS cicloFechado"
			+ " FROM FTTCR WHERE REF_CRONOGRAMA = :refCronograma AND CD_CIDADE = :cdCidade AND ciclo = :ciclo")
	Optional<CronogramaFaturaCicloProjectionDTO> buscarCronogramaPorLocalidadeEReferenciaCiclo(Short ciclo, Short cdCidade, Integer refCronograma);

	
	@Query(nativeQuery = true, value = "SELECT CICLO AS ciclo, CASE WHEN FASE = 0 THEN 'N' ELSE 'S' END AS cicloFechado"
			+ " FROM FTTCR WHERE REF_CRONOGRAMA = :refCronograma AND CD_CIDADE = :cdCidade")
	List<CronogramaFaturaCicloProjectionDTO> buscarCiclosPorLocalidadeEReferencia(Short cdCidade, Integer refCronograma);


	@Query(value = "SELECT new moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO(cronograma.id, cronograma.idCronogramaFatura.refCronograma, localidade.dcCidade, " +
			"	    cronograma.idCronogramaFatura.ciclo,faseCronograma.id, faseCronograma.descricao, cronograma.dataVencimento, cronograma.dataTarifa, cronograma.dataGeraArquivoPrevista, cronograma.dataGeraArquivoRealizada," +
			"		cronograma.dataLeituraPrevista, cronograma.dataLeituraRealizada, cronograma.dataFaturamentoPrevista, cronograma.dataFaturamentoRealizada, cronograma.dataConsolidaPrevista, " +
			"		cronograma.dataConsolidaRealizada, cronograma.dataEmissaoPrevista, cronograma.dataEmissaoRealizada, cronograma.dataCortePrevista, cronograma.dataCorteRealizada) " +
			"		FROM CronogramaFatura cronograma " +
			"		INNER JOIN Localidade localidade " +
			"		ON cronograma.idCronogramaFatura.cdCidade =  localidade.cdCidade " +
			"		INNER JOIN TipoFaseCronograma faseCronograma " +
			"		ON cronograma.fase.id = faseCronograma.id " +
			"		WHERE " +
			"		cronograma.idCronogramaFatura.refCronograma = :dataReferencia " +
			"		AND (:cdCidade is null or cronograma.idCronogramaFatura.cdCidade = :cdCidade )" +
			"		AND (:ciclo is null or cronograma.idCronogramaFatura.ciclo = :ciclo )")
	List<CronogramaFaturaRespostaGridProjectionDTO> buscarListaCronogramaFaturasPorFiltro(@Param("dataReferencia") Integer dataReferencia, @Param("cdCidade") Short cdCidade, @Param("ciclo") Short ciclo);


	@Query(value = "SELECT new moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO(" +
			"		cronograma.id, cronograma.idCronogramaFatura.refCronograma, localidade.cdCidade, localidade.dcCidade, cronograma.idCronogramaFatura.ciclo, faseCronograma.id, " +
			"		faseCronograma.descricao, statusProcessamento.descricao) " +
			"		FROM CronogramaFatura cronograma " +
			"		INNER JOIN Localidade localidade " +
			"		ON cronograma.idCronogramaFatura.cdCidade =  localidade.cdCidade " +
			"		INNER JOIN TipoFaseCronograma faseCronograma " +
			"		ON cronograma.fase.id = faseCronograma.id " +
			"		FULL JOIN TipoStatusProcessamento statusProcessamento ON statusProcessamento.id = cronograma.tipoStatusProcessamento.id " +
			"		WHERE " +
			"		cronograma.idCronogramaFatura.refCronograma = :dataReferencia " +
			"		AND (:cdCidade is null or cronograma.idCronogramaFatura.cdCidade = :cdCidade )" +
			"		AND (:ciclo is null or cronograma.idCronogramaFatura.ciclo = :ciclo )")
	List<CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO> buscarListaCronogramaFaturasParaCicloFaturamentoPorFiltro(@Param("dataReferencia") Integer dataReferencia,
																															  @Param("cdCidade") Short cdCidade, @Param("ciclo") Short ciclo);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE FTTCR"
										+ " SET FTTCR.DT_LEITURA_R = :dataLeituraR, FTTCR.FASE = 2"
										+ " WHERE FTTCR.CD_CIDADE = :idCidade"
										+ " AND FTTCR.CICLO = :ciclo"
										+ " AND FTTCR.REF_CRONOGRAMA = :refFatura")
	void atualizarFaseReferenciaDe01Para02(Integer dataLeituraR, Integer idCidade, Short ciclo, Integer refFatura);

	@Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(REF_CRONOGRAMA) > 0 THEN CAST(0 AS BIT) ELSE CAST(1 AS BIT) END" 
										+ " FROM FTTCR WHERE REF_CRONOGRAMA = :refTarifaMedia AND fase < 5")
	boolean verificarSeCronogramaFaturaEstaComTodosFase5(Integer refTarifaMedia);

	@Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(*) > 2 THEN CAST(0 AS BIT) ELSE CAST(1 AS BIT) END"
		+ " FROM FTTCR WHERE REF_CRONOGRAMA IN :buscarRefernciasAtualEPosterior AND CD_CIDADE = :cidade AND CICLO = :cicloNoRegistroAtualizarCiclo")
    boolean verificarSeExisteCicloECidadeNasTresReferencias(Short cidade, Short cicloNoRegistroAtualizarCiclo, List<Integer> buscarRefernciasAtualEPosterior);
  
	@Query(nativeQuery = true, value = "SELECT TOP 3 A.*"
	    +" FROM FTTCR A JOIN CDAIM B ON A.CICLO = B.CICLO  WHERE B.MATRICULA_IMOVEL = :matriculaImovel AND A.DT_GERA_ARQUIVO_P LIKE %:ref% ORDER BY ID DESC")   
		List<CronogramaFatura> bucarPorMatricula(@Param("matriculaImovel") Integer matriculaImovel, @Param("ref") Integer ref);

}

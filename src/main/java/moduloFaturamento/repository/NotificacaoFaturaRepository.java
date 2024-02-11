package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.dto.notificaoFatura.projection.MensagemNotificacaoFaturaGridProjectionDTO;
import moduloFaturamento.dto.notificaoFatura.projection.NotificacaoFaturaCriticaGridProjectionDTO;
import moduloFaturamento.model.NotificacaoFatura;

public interface NotificacaoFaturaRepository extends JpaRepository<NotificacaoFatura, Long> {

	@Query(nativeQuery = true, value = "SELECT localidade.CD_CIDADE AS cdCidade, localidade.DC_CIDADE AS dcCidade, cronograma.CICLO AS ciclo, "
			+ " cronograma.REF_CRONOGRAMA AS referencia, imovel.MATRICULA_IMOVEL AS matricula, "
			+ " imovel.DV AS dv, CASE WHEN cronograma.FASE = 0 THEN 'N' ELSE 'S' END AS cicloFechado, CASE WHEN ( SELECT MATRICULA FROM "
			+ " FAT_NOTIFICACAO_FATURA_MATRICULA WHERE MATRICULA = imovel.MATRICULA_IMOVEL "
			+ " GROUP BY MATRICULA) IS NULL THEN 'N' ELSE 'S' END AS existeOutraNotificacao FROM "
			+ " FTTCR cronograma JOIN CDAIM imovel ON imovel.CD_CIDADE = cronograma.CD_CIDADE AND imovel.CICLO = cronograma.CICLO "
			+ " JOIN CDACI localidade ON cronograma .CD_CIDADE = localidade.CD_CIDADE WHERE "
			+ " cronograma.REF_CRONOGRAMA = :referencia AND imovel.MATRICULA_IMOVEL in (:matriculas)")
	List<NotificacaoFaturaCriticaGridProjectionDTO> buscarDadosCriticaNotificacoesFatura(Integer referencia, List<Integer> matriculas);

	@Query(nativeQuery = true, value = "SELECT NULL AS idNotificacao, mensagemFatura.REF_FATURA AS refCronograma, "
			+ " mensagemFatura.CICLO AS ciclo, CASE WHEN mensagemFatura.CD_CIDADE IS NULL THEN 0 ELSE mensagemFatura.CD_CIDADE "
			+ " END AS cdCidade, CASE WHEN mensagemFatura.CD_CIDADE = 0 THEN 'Todas' ELSE cidade.DC_CIDADE "
			+ " END AS dcCidade, mensagemFatura.MENSAGEM_01 + ' ' + mensagemFatura.MENSAGEM_02 AS mensagem, 'N' AS flagMensagemLonga, "
			+ " CASE WHEN cronograma.FASE = 0 THEN 'N' ELSE 'S' END AS cicloFechado FROM FTAME mensagemFatura "
			+ "LEFT JOIN FTTCR cronograma ON mensagemFatura.REF_FATURA = cronograma.REF_CRONOGRAMA AND mensagemFatura.CICLO = cronograma.CICLO "
			+ " AND mensagemFatura.CD_CIDADE = cronograma.CD_CIDADE LEFT JOIN CDACI cidade ON mensagemFatura.CD_CIDADE = cidade.CD_CIDADE "
			+ "WHERE (:referencia IS NULL OR mensagemFatura.REF_FATURA = :referencia) AND (:cdCidade IS NULL "
			+ " OR mensagemFatura.CD_CIDADE = :cdCidade) AND (:ciclo IS NULL OR mensagemFatura.CICLO = :ciclo) UNION SELECT "
			+ " DISTINCT notificacaoFatura.ID AS idNotificacao, notificacaoFatura.REF_FATURA AS refCronograma, cronograma.CICLO AS ciclo, "
			+ " cidade.CD_CIDADE AS cdCidade, cidade.DC_CIDADE AS dcCidade, notificacaoFatura.MENSAGEM AS mensagem, "
			+ " 'S' AS flagMensagemLonga, CASE WHEN cronograma.fase = 0 THEN 'N' ELSE 'S' END AS cicloFechado FROM "
			+ " FAT_NOTIFICACAO_FATURA notificacaoFatura JOIN FAT_NOTIFICACAO_FATURA_MATRICULA notificacaoFaturaMatricula ON "
			+ " notificacaoFatura.ID = notificacaoFaturaMatricula.FAT_NOTIFICACAO_FATURA_ID JOIN CDAIM imovel ON "
			+ " imovel.MATRICULA_IMOVEL = notificacaoFaturaMatricula.MATRICULA JOIN CDACI cidade ON imovel.CD_CIDADE = cidade.CD_CIDADE "
			+ "JOIN FTTCR cronograma ON cronograma.CD_CIDADE = imovel.CD_CIDADE AND cronograma.CICLO = imovel.CICLO "
			+ " AND cronograma.REF_CRONOGRAMA = notificacaoFatura.REF_FATURA WHERE (:referencia IS NULL "
			+ " OR notificacaoFatura.REF_FATURA = :referencia) AND (:cdCidade IS NULL OR :cdCidade = 0 "
			+ " OR cidade.CD_CIDADE = :cdCidade) AND (:ciclo IS NULL OR :ciclo = 0 OR imovel.CICLO = :ciclo)")
	List<MensagemNotificacaoFaturaGridProjectionDTO> buscarListaMensagensNotificacoesFatura(Integer referencia, Short cdCidade, Short ciclo);

	@Query(nativeQuery = true, value = "SELECT DISTINCT notificacaoFatura.ID AS idNotificacao, notificacaoFatura.REF_FATURA AS refCronograma, "
			+ " cronograma.CICLO AS ciclo, cidade.CD_CIDADE AS cdCidade, cidade.DC_CIDADE AS dcCidade, "
			+ " notificacaoFatura.MENSAGEM AS mensagem, 'S' AS flagMensagemLonga, CASE WHEN cronograma.fase = 0 THEN 'N' "
			+ " ELSE 'S' END AS cicloFechado FROM FAT_NOTIFICACAO_FATURA notificacaoFatura "
			+ " JOIN FAT_NOTIFICACAO_FATURA_MATRICULA notificacaoFaturaMatricula ON "
			+ " notificacaoFatura.ID = notificacaoFaturaMatricula.FAT_NOTIFICACAO_FATURA_ID JOIN CDAIM imovel ON "
			+ " imovel.MATRICULA_IMOVEL = notificacaoFaturaMatricula.MATRICULA JOIN CDACI cidade ON imovel.CD_CIDADE = cidade.CD_CIDADE "
			+ " JOIN FTTCR cronograma ON cronograma.CD_CIDADE = imovel.CD_CIDADE AND cronograma.CICLO = imovel.CICLO "
			+ " AND cronograma.REF_CRONOGRAMA = notificacaoFatura.REF_FATURA WHERE (:referencia IS NULL "
			+ " OR notificacaoFatura.REF_FATURA = :referencia) AND (:matricula IS NULL OR notificacaoFaturaMatricula.MATRICULA = :matricula)")
	List<MensagemNotificacaoFaturaGridProjectionDTO> buscarListaNotificacoesFatura(Integer referencia, Integer matricula);

	@Query(nativeQuery = true, value = "SELECT COUNT(imovel.MATRICULA_IMOVEL) FROM FTTCR cronograma JOIN CDAIM imovel ON "
			+ " imovel.CD_CIDADE = cronograma.CD_CIDADE AND imovel.CICLO = cronograma.CICLO WHERE cronograma.FASE <> 0 "
			+ " AND cronograma.REF_CRONOGRAMA = :referencia AND imovel.MATRICULA_IMOVEL in (:matriculas)")
	Long buscarMatriculasEmCicloFechado(Integer referencia, List<Integer> matriculas);

	@Query(nativeQuery = true, value = "SELECT cronograma.FASE, cronograma.CD_CIDADE, cronograma.CICLO, cronograma.REF_CRONOGRAMA "
			+ " FROM FAT_NOTIFICACAO_FATURA notificacao JOIN FAT_NOTIFICACAO_FATURA_MATRICULA notificacaoMatricula ON "
			+ " notificacao.ID = notificacaoMatricula.FAT_NOTIFICACAO_FATURA_ID "
			+ " JOIN CDAIM imovel ON imovel.MATRICULA_IMOVEL = notificacaoMatricula.MATRICULA JOIN FTTCR cronograma ON "
			+ " imovel.CD_CIDADE = cronograma.CD_CIDADE AND imovel.CICLO = cronograma.CICLO "
			+ " AND notificacao.REF_FATURA = cronograma.REF_CRONOGRAMA WHERE notificacao.ID = :idNotificacao")
	List<Short> buscarFasesCronogramaMatriculasPorNotificacaoId(Long idNotificacao);

}

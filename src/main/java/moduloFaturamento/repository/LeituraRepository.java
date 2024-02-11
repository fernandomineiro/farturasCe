package moduloFaturamento.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.dto.leitura.projection.LeituraCicloProjectionDTO;
import moduloFaturamento.dto.leitura.projection.LeituraOcorrenciasProjectionDTO;
import moduloFaturamento.dto.leitura.projection.PesquisarNomeEIdLocalidadeProjectionDTO;
import moduloFaturamento.model.IdLeitura;
import moduloFaturamento.model.Leitura;
import org.springframework.data.repository.query.Param;

public interface LeituraRepository extends JpaRepository<Leitura, IdLeitura> {

	@Query(value = "SELECT TOP 3 * FROM FTTLE leitura WHERE leitura.MATRICULA_IMOVEL = :matricula ORDER BY id DESC", nativeQuery = true)
	List<Leitura> buscarLeituraExistentePorMatricula(@Param("matricula") Integer matricula);

	Optional<Leitura> findById(Long id);

	Optional<Leitura> findByIdLeituraFaturamento(IdLeitura id);

	@Query(value = "SELECT leitura FROM Leitura leitura WHERE leitura.id = :id")
	Leitura buscarLeituraExistentePorId(@Param("id") Long id);

	@Query(value = "SELECT leitura FROM Leitura leitura WHERE leitura.idLeituraFaturamento.matriculaImovel = :matricula AND leitura.idLeituraFaturamento.refFatura = :refFatura")
	Leitura buscarLeituraExistentePorMatriculaEReferencia(@Param("matricula") Integer matricula, @Param("refFatura") Integer refFatura);

	@Query(value = "SELECT TOP 1 * FROM FTTLE leitura WHERE leitura.MATRICULA_IMOVEL = :matricula AND leitura.REF_FATURA <= :refFatura ORDER BY leitura.REF_FATURA DESC", nativeQuery = true)
	Leitura buscarUltimaLeituraExistentePorMatriculaEReferenciaNative(Integer matricula, Integer refFatura);

	@Query(value = "SELECT new moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO(leitura.id, imovel.matriculaImovel, imovel.dv, leitura.medido) "
			+ "FROM Leitura leitura " + "INNER JOIN Imovel imovel ON imovel.matriculaImovel = leitura.idLeituraFaturamento.matriculaImovel "
			+ "WHERE leitura.idLeituraFaturamento.matriculaImovel = :matricula " + "AND leitura.idLeituraFaturamento.refFatura = :refFatura")
	LeituraConsumoImovelMicroRespostaGridProjectionDTO buscarLeituraDeMatriculaMicroPorFiltro(@Param("matricula") Integer matricula,
			@Param("refFatura") Integer refFatura);

	@Query(value = "SELECT new moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO(leitura.id, leitura.idLeituraFaturamento.refFatura, "
			+ "leitura.dataDeleitura, leitura.leitura, leitura.leituraCriada, ocorrencia1.cdOcorrencia, ocorrencia2.cdOcorrencia, "
			+ "ocorrencia3.cdOcorrencia, leitura.medido, leitura.mediaDiaria, leitura.diasVenda, leitura.diasConsumo, leitura.consumoFaturarAgua, "
			+ "leitura.consumoFaturarEsgoto, leitura.tipoConsumoFaturado.id, leitura.tipoConsumoFaturado.descricao, leitura.cdAnormalidade, leitura.alterado, leitura.dataHoraEdicao) "
			+ "FROM Leitura leitura " + "LEFT JOIN OcorrenciaLeitura ocorrencia1 ON leitura.ocorrencia = ocorrencia1.cdOcorrencia "
			+ "LEFT JOIN OcorrenciaLeitura ocorrencia2 ON leitura.ocorrencia2 = ocorrencia2.cdOcorrencia "
			+ "LEFT JOIN OcorrenciaLeitura ocorrencia3 ON leitura.ocorrencia3 = ocorrencia3.cdOcorrencia "
			+ "WHERE leitura.idLeituraFaturamento.matriculaImovel = :matricula "
			+ "AND (:refFatura is null or leitura.idLeituraFaturamento.refFatura = :refFatura)")
	List<LeituraConsumoImovelRespostaGridProjectionDTO> buscarListaLeituraPorFiltro(@Param("matricula") Integer matricula,
			@Param("refFatura") Integer refFatura);

	@Query(nativeQuery = true, value = "SELECT localidade.CD_CIDADE AS id, localidade.DC_CIDADE AS nome" + " FROM CDACI localidade"
			+ " ORDER BY localidade.DC_CIDADE")
	List<PesquisarNomeEIdLocalidadeProjectionDTO> buscarCiclosdaLocalidade();

	@Query(nativeQuery = true, value = "SELECT TOP 1 fatura.MATRICULA_IMOVEL" + " FROM FTTCR cronograma"
			+ " JOIN FTTFA fatura ON cronograma.REF_CRONOGRAMA = fatura.REF_FATURA and cronograma.CD_CIDADE = fatura.CD_CIDADE and cronograma.CICLO = fatura.CICLO and fatura.ORIGEM_FATURA = 1 AND fatura.FASE = 1 AND cronograma.FASE = 1"
			+ " WHERE cronograma.CD_CIDADE = :cidade" + " AND cronograma.CICLO = :ciclo" + " AND cronograma.REF_CRONOGRAMA = :referencia")
	Integer verificarPeloMenosUmLeituraNaFase01(Integer cidade, Short ciclo, Integer referencia);

	@Query(nativeQuery = true, value = "SELECT TOP 1 cronograma.DT_LEITURA_P" + " FROM FTTCR cronograma"
			+ " JOIN FTTFA fatura ON cronograma.REF_CRONOGRAMA = fatura.REF_FATURA and cronograma.CD_CIDADE = fatura.CD_CIDADE and cronograma.CICLO = fatura.CICLO and fatura.ORIGEM_FATURA = 1 AND fatura.FASE = 1 AND cronograma.FASE = 1"
			+ " WHERE cronograma.CD_CIDADE = :cidade" + " AND cronograma.CICLO = :ciclo" + " AND cronograma.REF_CRONOGRAMA = :referencia")
	Integer verificarDatadaleituraP(Integer cidade, Short ciclo, Integer referencia);

	@Query(nativeQuery = true, value = "SELECT top 500 fatura.MATRICULA_IMOVEL" + " FROM FTTCR cronograma"
			+ " JOIN FTTFA fatura ON cronograma.REF_CRONOGRAMA = fatura.REF_FATURA and cronograma.CD_CIDADE = fatura.CD_CIDADE and cronograma.CICLO = fatura.CICLO and fatura.ORIGEM_FATURA = 1 AND fatura.FASE = 1"
			+ " WHERE cronograma.CD_CIDADE = :cidade" + " AND cronograma.CICLO = :ciclo" + " AND cronograma.REF_CRONOGRAMA = :referencia")
	List<Integer> buscarMatriculasNafase01(Integer cidade, Short ciclo, Integer referencia);

	List<Leitura> findByIdLeituraFaturamentoMatriculaImovelInAndIdLeituraFaturamentoRefFaturaOrderBySeqRotaAsc(List<Integer> listaMatricula,
			Integer referenciaAnterior);

	@Query(nativeQuery = true, value = "SELECT top 1 imovel.DV" + " FROM CDAIM imovel" + " WHERE imovel.MATRICULA_IMOVEL = :matricual")
	Short bucarDvDaMatricula(Integer matricual);

	@Query(value = "SELECT DISTINCT(CICLO) AS ciclo FROM CDTCD", nativeQuery = true)
	List<LeituraCicloProjectionDTO> listaCiclos();

	@Query(nativeQuery = true, value = "SELECT oco.CD_OCORRENCIA AS id," + " oco.DC_OCORRENCIA AS nome," + " nomeOco.DESCRICAO AS nomeDaOcorrencia"
			+ " FROM FTTOL oco" + " INNER JOIN SRV_TP_SERVICO_OCORRENCIA_LEITURA nomeOco ON oco.TP_OCORRENCIA = nomeOco.ID")
	List<LeituraOcorrenciasProjectionDTO> listaOcorrencias();

	@Query(value = "SELECT TOP 12 * FROM FTTLE  WHERE MATRICULA_IMOVEL = :matricula AND REF_FATURA < :referenciaFatura AND EXCLUI_CALC_MEDIA != 'S' ORDER BY ID DESC", nativeQuery = true)
	List<Leitura> buscarUltimasDozeLeiturasQueNaoPossuemExcluirCalculoMedia(Integer matricula, Integer referenciaFatura);

	@Query(nativeQuery = true, value = "SELECT SEQ_ROTA FROM FTTLE WHERE MATRICULA_IMOVEL = :matriculaImovel AND REF_FATURA = :refFatura")
	Integer buscarARotaDaMatriculaERefFaturaDaPesquisa(Integer matriculaImovel, Integer refFatura);

	@Query(value = "SELECT TOP 1 MEDIA_DIARIA * DIAS_VENDA AS MEDIA_MENSAL FROM FTTLE WHERE MATRICULA_IMOVEL = :matricula AND REF_FATURA <= :referencia AND MEDIA_DIARIA <> 0 ORDER BY REF_FATURA DESC ", nativeQuery = true)
	BigDecimal buscarMediaMensal(Integer matricula, Integer referencia);

}

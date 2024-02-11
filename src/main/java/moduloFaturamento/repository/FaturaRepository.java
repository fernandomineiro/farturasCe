package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloImovelRespostaProjectionDTO;
import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaAdutoriaDaFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaCabecalhoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaListaFaturaEmAbertoParaSimulacaoParcelamentoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaPesquisaResponseProjectionDTO;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdFatura;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, IdFatura> {

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE FTTFA SET FTTFA.FASE = 2 WHERE FTTFA.MATRICULA_IMOVEL = :matriculaImovel"
			+ " AND FTTFA.ORIGEM_FATURA = :numeroDaOrigemFatura AND FTTFA.REF_FATURA = :refFatura")
	void mudarFaturDeFase01aParafase02(Integer matriculaImovel, short numeroDaOrigemFatura, Integer refFatura);

	List<Fatura> findByIdFatura_MatriculaImovel(Integer matricula);

	List<Fatura> findByIdFatura_MatriculaImovelAndIdFatura_RefFatura(Integer matricula, Integer referencia);

	Optional<List<Fatura>> findByCdCidadeAndCicloAndIdFatura_RefFatura(Short cdCidade, Short ciclo, Integer dataReferencia);

	@Query(nativeQuery = true, value = "SELECT COUNT(DT_TARIFA) FROM FTTFA fatura WHERE fatura.ORIGEM_FATURA IN (1, 2) "
			+ " AND fatura.TP_BAIXA = 0 AND fatura.DT_VENCIMENTO < :novoVencimento "
			+ " AND fatura.CICLO = :ciclo AND fatura.CD_CIDADE = :cdCidade AND fatura.REF_FATURA = :referencia AND fatura.FASE IN (:fases)")
	Integer consultarQuantidadeFaturasParaAlteracaoEmLote(Integer referencia, Short cdCidade, Short ciclo, Integer novoVencimento, List<Short> fases);

	@Query(nativeQuery = true, value = "SELECT * FROM FTTFA fatura WHERE fatura.ORIGEM_FATURA IN (1, 2) "
			+ " AND fatura.TP_BAIXA = 0 AND fatura.DT_VENCIMENTO < :novoVencimento "
			+ " AND fatura.CICLO = :ciclo AND fatura.CD_CIDADE = :cdCidade AND fatura.REF_FATURA = :referencia AND fatura.FASE IN (:fases)")
	List<Fatura> buscarFaturasParaAlteracaoEmLote(Integer referencia, Short cdCidade, Short ciclo, Integer novoVencimento, List<Short> fases);

	@Query(value = "SELECT TOP 1 * FROM FTTFA WHERE MATRICULA_IMOVEL = :matriculaImovel AND (:refFatura is null or REF_FATURA = :refFatura) ORDER BY ID DESC", nativeQuery = true)
	Fatura buscarUltimaFaturaDoImovel(Integer matriculaImovel, Integer refFatura);

	@Query(nativeQuery = true, value = "SELECT FAT.TRATAMENTO_ESGOTO AS tratamentoEsgoto, FLA.VR_SERVICO AS valorAgua, "
			+ "	FLE.VR_SERVICO AS valorEsgoto, FLD.VR_SERVICO AS valorDisponibilidade FROM FTTFA FAT "
			+ " LEFT JOIN FTTLA FLA ON FAT.ID = FLA.ID_FATURA AND FLA.D_C = 'D' AND FLA.CD_SERVICO IN (SELECT CD_SRV_AGUA FROM FTTSV WHERE GRUPO_CONSUMO = FAT.GRUPO_CONSUMO) "
			+ " LEFT JOIN FTTLA FLE ON FAT.ID = FLE.ID_FATURA AND FLE.D_C = 'D' AND FLE.CD_SERVICO IN (SELECT CD_SRV_ESGT_TRAT FROM FTTSV WHERE GRUPO_CONSUMO = FAT.GRUPO_CONSUMO) "
			+ " LEFT JOIN FTTLA FLD ON FAT.ID = FLD.ID_FATURA AND FLD.D_C = 'D' AND FLD.CD_SERVICO IN (SELECT CD_SRV_DISP_ESG FROM FTTSV WHERE GRUPO_CONSUMO = FAT.GRUPO_CONSUMO) "
			+ " WHERE FAT.REF_FATURA = :refFatura AND FAT.MATRICULA_IMOVEL = :matricula")
	Optional<FaturaSituacaoAguaEsgotoProjection> buscarFaturaSituacaoAguaEsgotoProjectionPorMatriculaReferencia(Integer matricula, Integer refFatura);


	@Query(value = "SELECT COUNT(*) FROM FTTFA WHERE CICLO = :ciclo AND REF_FATURA = :refFatura AND FASE = :fase AND CD_CIDADE = :cdCidade", nativeQuery = true)
	Integer contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(Short ciclo, Integer refFatura, Short fase, Short cdCidade);

	@Query(value = "SELECT new moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloImovelRespostaProjectionDTO(fatura.fase, fatura.idFatura.matriculaImovel, fatura.dv) " +
			"FROM Fatura fatura " +
			"WHERE fatura.ciclo = :ciclo " +
			"AND fatura.idFatura.refFatura = :refFatura " +
			"AND fatura.fase IN (:fase) " +
			"AND fatura.cdCidade = :cdCidade ORDER BY fatura.fase ASC")
	List<CronogramaFaturaCicloImovelRespostaProjectionDTO> buscarImovelDasFaturasCadastradasParaCronogramaEmDeterminadaFase(Short ciclo, Integer refFatura, List<Short> fase, Short cdCidade);

	@Query(nativeQuery = true, value = "SELECT DISTINCT" 
				+ " fatura.MATRICULA_IMOVEL AS matriculaImovel," 
				+ " imovel.DV AS dvMatriculaImovel,"
				+ " grupoConsumo.DC_GRUPO_CONSUMO AS descricaoGrupoDeConsumo,"
				+ " localidade.DC_CIDADE AS nomeDaCidade,"
				+ " bairro.DC_BAIRRO AS nomeDoBairro,"
				+ " logradouro.SIGLA_LOGRADOURO + ' ' + logradouro.DC_LOGRADOURO AS nomeDaRua,"
				+ " imovel.NUM_ENDERECO AS numeroImovel,"
				+ " imovel.COMPL_ENDERECO AS complemento"
			+ " FROM FTTFA fatura"
			+ " JOIN CDAIM imovel ON imovel.MATRICULA_IMOVEL = fatura.MATRICULA_IMOVEL"
			+ " JOIN CDACI localidade ON localidade.CD_CIDADE = imovel.CD_CIDADE"
			+ " INNER JOIN CDABA bairro ON bairro.CD_BAIRRO = imovel.CD_BAIRRO AND imovel.CD_CIDADE = bairro.CD_CIDADE"
			+ " INNER JOIN CDALO logradouro ON imovel.CD_LOGRADOURO =  logradouro.CD_LOGRADOURO AND logradouro.CD_CIDADE = imovel.CD_CIDADE"
			+ " JOIN CDTGC grupoConsumo ON imovel.GRUPO_CONSUMO = grupoConsumo.GRUPO_CONSUMO "
				+ " WHERE fatura.CD_CLIENTE = :cdCliente"
				+ " AND fatura.TP_BAIXA = 0"
				+ " AND fatura.VR_FATURA <> 0")
    List<ParcelamentoFaturaPesquisaResponseProjectionDTO> buscarDividasEmAbertaPorCliente(Integer cdCliente);

	@Query(nativeQuery = true, value = "SELECT DISTINCT"
			+ " imovel.CD_CLIENTE AS cdClienteImovel,"
			+ " (SELECT DV FROM CDACL WHERE CD_CLIENTE = imovel.CD_CLIENTE) AS dvClienteImovel,"
			+ " (SELECT NOME FROM CDACL WHERE CD_CLIENTE = imovel.CD_CLIENTE) AS nomeClienteImovel,"

			+ " fatura.MATRICULA_IMOVEL AS matriculaImovel,"
			+ " (SELECT DV FROM CDAIM WHERE MATRICULA_IMOVEL = :matriculaImovel) AS dvMatriculaImovel,"
			
			+ " grupoConsumo.DC_GRUPO_CONSUMO AS descricaoGrupoDeConsumo,"
			+ " localidade.DC_CIDADE AS nomeDaCidade,"
			+ " bairro.DC_BAIRRO AS nomeDoBairro,"
			+ " logradouro.SIGLA_LOGRADOURO + ' ' + logradouro.DC_LOGRADOURO AS nomeDaRua,"
			+ " imovel.NUM_ENDERECO AS numeroImovel,"
			+ " imovel.COMPL_ENDERECO AS complemento,"

			+ " cliente.CD_CLIENTE AS cdClienteFatura,"
			+ " cliente.DV  AS dvClienteFatura,"
			+ " cliente.NOME AS nomeClienteFatura,"
			+ " cliente.CPF_CNPJ AS cpfOuCnpj"
		+ " FROM FTTFA fatura"
		+ " JOIN CDACL cliente ON fatura.CD_CLIENTE = cliente.CD_CLIENTE"
		+ " JOIN CDAIM imovel ON imovel.MATRICULA_IMOVEL = fatura.MATRICULA_IMOVEL"
		+ " JOIN CDACI localidade ON localidade.CD_CIDADE = imovel.CD_CIDADE"
		+ " INNER JOIN CDABA bairro ON bairro.CD_BAIRRO = imovel.CD_BAIRRO AND imovel.CD_CIDADE = bairro.CD_CIDADE"
		+ " INNER JOIN CDALO logradouro ON imovel.CD_LOGRADOURO =  logradouro.CD_LOGRADOURO AND logradouro.CD_CIDADE = imovel.CD_CIDADE"
		+ " JOIN CDTGC grupoConsumo ON imovel.GRUPO_CONSUMO = grupoConsumo.GRUPO_CONSUMO "
		+ " WHERE fatura.CD_CLIENTE = :cdCliente"
			+ " AND fatura.MATRICULA_IMOVEL = :matriculaImovel"
			+ " AND fatura.TP_BAIXA = 0")
	ParcelamentoFaturaCabecalhoProjectionDTO buscarPorClienteFaturaDadosDoProprietarioEClienteDaFatura(Integer cdCliente, Integer matriculaImovel);

	@Query(nativeQuery = true, value = "SELECT fatura.REF_FATURA AS refFatura,"
		+ " fatura.ORIGEM_FATURA AS origemFatura,"
		+ " grupo.DC_GRUPO_CONSUMO AS grupoDeConsumo,"
		+ " fatura.VR_FATURA AS valorFatura"
	+ " FROM FTTFA fatura"
	+ " INNER JOIN CDTGC grupo ON grupo.GRUPO_CONSUMO = fatura.GRUPO_CONSUMO"
	+ " WHERE fatura.CD_CLIENTE = :cdCliente AND fatura.MATRICULA_IMOVEL = :matriculaImovel AND fatura.TP_BAIXA = 0"
	+ " ORDER BY fatura.REF_FATURA ASC")
	List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaDeFaturaEmAbertoDoClienteEMatricula(Integer cdCliente, Integer matriculaImovel);

	@Query(nativeQuery = true, value = "SELECT SUM(fatura.VR_FATURA) AS principal,"
	+ " CAST(CASE WHEN SUM(LAG.VR_SERVICO) IS NULL THEN 0 ElSE SUM(LAG.VR_SERVICO) END / SUM(fatura.VR_FATURA) AS DECIMAL(3,2) ) AS valorAgua,"
	+ " CAST(CASE WHEN SUM(LEG.VR_SERVICO) IS NULL THEN 0 ElSE SUM(LEG.VR_SERVICO) END / SUM(fatura.VR_FATURA) AS DECIMAL(3,2) ) AS valorEsgoto,"
+ " CAST( (SUM(fatura.VR_FATURA) - (CASE WHEN SUM(LAG.VR_SERVICO) IS NULL THEN 0 ElSE SUM(LAG.VR_SERVICO) END + CASE WHEN SUM(LEG.VR_SERVICO) IS NULL THEN 0 ElSE SUM(LEG.VR_SERVICO) END) ) / SUM(fatura.VR_FATURA) AS DECIMAL(3,2) ) AS outrosServicos"
+ " FROM FTTFA fatura"
+ " LEFT OUTER JOIN FTTLA LAG (NOLOCK) ON fatura.MATRICULA_IMOVEL = LAG.MATRICULA_IMOVEL AND fatura.REF_FATURA = LAG.REF_FATURA AND fatura.ORIGEM_FATURA = LAG.ORIGEM_FATURA"
	+ " AND LAG.CD_SERVICO IN ( SELECT conf.CD_SRV_AGUA FROM FTTSV conf )"
+ " LEFT OUTER JOIN FTTLA LEG (NOLOCK) ON fatura.MATRICULA_IMOVEL = LEG.MATRICULA_IMOVEL AND fatura.REF_FATURA = LEG.REF_FATURA AND fatura.ORIGEM_FATURA = LEG.ORIGEM_FATURA"
	+ " AND LEG.CD_SERVICO IN ("
		+ " SELECT CD_SRV_ESGT_N_TRAT AS CD_SERVICO FROM FTTSV"
		+ " UNION"
		+ " SELECT CD_SRV_ESGT_TRAT AS CD_SERVICO FROM FTTSV"
		+ " UNION"
		+ " SELECT CD_SRV_DISP_ESG AS CD_SERVICO FROM FTTSV)"
+ " WHERE fatura.CD_CLIENTE = :cdCliente AND fatura.MATRICULA_IMOVEL = :matriculaImovel AND fatura.TP_BAIXA = 0 AND fatura.REF_FATURA IN (:listaReferenciaSelecionadas)")
    ParcelamentoFaturaListaFaturaEmAbertoParaSimulacaoParcelamentoProjectionDTO buscaParaRetornarPropocaoAguaEsgotoEOoutrosDaFaturaEmAberto(Integer cdCliente, Integer matriculaImovel,
		List<Integer> listaReferenciaSelecionadas);

	@Query(nativeQuery = true, value = "SELECT fatura.REF_FATURA AS refFatura,"
		+ " fatura.ORIGEM_FATURA AS origemFatura,"
		+ " grupo.DC_GRUPO_CONSUMO AS grupoDeConsumo,"
		+ " fatura.VR_FATURA AS valorFatura"
	+ " FROM FTTFA fatura"
	+ " INNER JOIN CDTGC grupo ON grupo.GRUPO_CONSUMO = fatura.GRUPO_CONSUMO"
	+ " WHERE fatura.CD_CLIENTE = :cdCliente AND fatura.MATRICULA_IMOVEL = :matriculaImovel AND fatura.TP_BAIXA = 0 AND fatura.REF_FATURA in :listaFaturaSelecionada"
	+ " ORDER BY fatura.REF_FATURA DESC")
	List<ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovelSelecionados(Integer cdCliente, 
		Integer matriculaImovel, List<Integer> listaFaturaSelecionada);

	@Query(nativeQuery = true, value = "SELECT REF_FATURA AS refFatura"
			+ " FROM FTTFA"
			+ " WHERE CD_CLIENTE = :cdCliente AND MATRICULA_IMOVEL = :matriculaImovel AND TP_BAIXA = 0")
	List<Integer> listaDeTodasReferenciasEmAberto(Integer cdCliente, Integer matriculaImovel);

	@Query(nativeQuery = true, value = "SELECT count(*) FROM FTTFA WHERE MATRICULA_IMOVEL = :matriculaImovel AND CD_CLIENTE = :cdCliente AND TP_BAIXA = 0;")
    Integer buscarQuantiodadeFaturaEmAbertodaMatriculaECliente(Integer matriculaImovel, Integer cdCliente);

    List<Fatura> findByIdFaturaMatriculaImovelAndCdClienteAndIdFaturaRefFaturaIn(Integer matriculaImovel, Integer cdCliente, 
		List<Integer> listaReferenciasSelecionadasEmInteger);

	@Query(value = "SELECT new moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaAdutoriaDaFaturaDTO("
		+ " fatura.idFatura.refFatura, fatura.idFatura.origemFatura)"
		+ " FROM Fatura fatura WHERE fatura.idFatura.matriculaImovel = :matriculaImovel AND fatura.cdCliente = :cdCliente"
		+ " AND fatura.idFatura.refFatura IN (:listaReferenciasEmInteger)")
    List<ParcelamentoDaFaturaAdutoriaDaFaturaDTO> buscarAsReferenciasDaFaturaEAOrigemDaFatura(Integer matriculaImovel, Integer cdCliente, 
		List<Integer> listaReferenciasEmInteger);

	@Query(nativeQuery = true, value = "SELECT DESCRICAO FROM CAD_TP_BAIXA WHERE ID = :id")
    String buscarSituacaoDasBaixa(Short id);

}

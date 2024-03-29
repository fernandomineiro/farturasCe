package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelCabecalhoProjectionDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdFatura;

@Repository
public interface FaturaImovelRepository extends JpaRepository<Fatura, IdFatura> {

	// @Query(value = "DECLARE @referencia AS INT "
	// + "DECLARE @referenciaAnterior AS INT "
	// + "DECLARE @matricula AS INT "
	// + "DECLARE @ano AS INT "
	// + "DECLARE @mes AS INT "
	// + "DECLARE @cdCliente AS INT "
	// + "DECLARE @origem AS INT "
	// + " "
	// + "DECLARE @totalLigacoesD AS NUMERIC(9,2) "
	// + "DECLARE @totalEconomiasD AS NUMERIC(9,2) "
	// + "DECLARE @totalServicosD AS NUMERIC(9,2) "
	// + " "
	// + "DECLARE @totalLigacoesC AS NUMERIC(9,2) "
	// + "DECLARE @totalEconomiasC AS NUMERIC(9,2) "
	// + "DECLARE @totalServicosC AS NUMERIC(9,2) "
	// + " "
	// + "DECLARE @totalLigacoes AS NUMERIC(9,2) "
	// + "DECLARE @totalEconomias AS NUMERIC(9,2) "
	// + "DECLARE @totalServicos AS NUMERIC(9,2) "
	// + " "
	// + "SELECT "
	// + " @matricula = MATRICULA_IMOVEL, "
	// + " @referencia = REF_FATURA, "
	// + " @mes = REF_FATURA % 100, "
	// + " @ano = REF_FATURA / 100, "
	// + " @cdCliente = CD_CLIENTE, "
	// + " @origem = ORIGEM_FATURA "
	// + "FROM "
	// + " FTTFA fatura "
	// + "WHERE "
	// + " fatura.ID = :idFatura "
	// + " "
	// + "SELECT @totalLigacoesD = ISNULL(SUM(VR_SERVICO_1ECO), 0.00),
	// @totalEconomiasD = ISNULL(SUM(VR_SERVICO_MECO), 0.00), @totalServicosD =
	// ISNULL(SUM(VR_SERVICO), 0.00) FROM FTTLA WHERE D_C = 'D' AND ID_FATURA =
	// :idFatura "
	// + "SELECT @totalLigacoesC = ISNULL(SUM(VR_SERVICO_1ECO), 0.00),
	// @totalEconomiasC = ISNULL(SUM(VR_SERVICO_MECO), 0.00), @totalServicosC =
	// ISNULL(SUM(VR_SERVICO), 0.00) FROM FTTLA WHERE D_C = 'C' AND ID_FATURA =
	// :idFatura "
	// + " "
	// + "SELECT @totalLigacoes = @totalLigacoesD - @totalLigacoesC, @totalEconomias
	// = @totalEconomiasD - @totalEconomiasC, @totalServicos = @totalServicosD -
	// @totalServicosC "
	// + " "
	// + "SELECT "
	// + " @totalLigacoes = "
	// + " CASE "
	// + " WHEN @totalLigacoes = 0 THEN @totalServicos "
	// + " ELSE @totalLigacoes "
	// + " END, "
	// + " @totalEconomias = "
	// + " CASE "
	// + " WHEN @totalEconomias = 0 THEN @totalServicos "
	// + " ELSE @totalEconomias "
	// + " END "
	// + "SELECT "
	// + " TOP 1 @referenciaAnterior = REF_FATURA "
	// + "FROM "
	// + " FTTFA fatura "
	// + "WHERE "
	// + " fatura.MATRICULA_IMOVEL = @matricula "
	// + " AND fatura.REF_FATURA < @referencia "
	// + "ORDER BY "
	// + " fatura.REF_FATURA DESC "
	// + " "
	// + "SELECT "
	// + " fatura.ID AS id, "
	// + " arquivoRecebido.DATA_PAGAMENTO_EFETIVADO AS dataArrecadacao, "
	// + " (fatura.REF_BAIXA * 100) + fatura.DIA_BAIXA AS dataBaixa, "
	// + " imovel.MATRICULA_IMOVEL AS matricula, "
	// + " fatura.REF_FATURA AS referencia, "
	// + " imovel.DV AS dv, "
	// + " fatura.CICLO AS ciclo, "
	// + " fatura.ORIGEM_FATURA AS origem, "
	// + " fatura.VERSAO_FATURA AS versao, "
	// + " cliente.CD_CLIENTE AS cdCliente, "
	// + " cliente.DV AS dvCliente, "
	// + " cliente.NOME AS nomeCliente, "
	// + " leitura.DT_LEITURA AS dataLeitura, "
	// + " leituraAnterior.LEITURA AS leituraAnterior, "
	// + " leitura.LEITURA AS leituraAtual, "
	// + " leitura.MEDIDO AS medido, "
	// + " leitura.CD_OCORRENCIA AS cdOcorrencia1, "
	// + " leitura.CD_OCORRENCIA2 AS cdOcorrencia2, "
	// + " leitura.CD_OCORRENCIA3 AS cdOcorrencia3, "
	// + " leitura.TP_CONSUMO_FATURAR AS tipoConsumoFaturamento, "
	// + " leitura.CONSUMO_FATURAR AS consumoFaturadoAgua, "
	// + " leitura.CONSUMO_FATURAR_ESGOTO AS consumoFaturadoEsgoto, "
	// + " fatura.VOL_DISP_ESG AS consumoDisponibilidadeFaturado, "
	// + " fatura.DESC_ESGOTO AS descontoEsgoto, "
	// + " fatura.PERC_TARIFA AS subvencao, "
	// + " leitura.DIAS_CONSUMO AS diasConsumo, "
	// + " leitura.DIAS_VENDA AS diasVenda, "
	// + " tipoMedicaoEsgoto.DESCRICAO AS tipoMedicaoEsgoto, "
	// + " tipoFaseFatura.DESCRICAO AS situacaoFatura, "
	// + " fatura.DT_VENCIMENTO AS dataVencimento, "
	// + " fatura.DT_TARIFA AS dataTarifa, "
	// + " grupoConsumo.DC_GRUPO_CONSUMO AS grupoConsumo, "
	// + " imovel.TRATAMENTO_ESGOTO AS tratamentoEsgoto, "
	// + " situacaoAgua.DC_DESCRICAO AS situacaoAgua, "
	// + " situacaoEsgoto.DC_DESCRICAO AS situacaoEsgoto, "
	// + " fatura.UN_CONSUMO AS numeroEconomiasFaturamento, "
	// + " fatura.UN_CONSUMO_CAD AS numeroEconomiasCadastro, "
	// + " @totalServicos AS valorTotal, "
	// + " @totalLigacoes AS valorTotalLigacao, "
	// + " @totalEconomias AS valorTotalEconomias, "
	// + " RTRIM(LTRIM(cidade.DC_CIDADE)) AS localidade, "
	// + " RTRIM(LTRIM(bairro.DC_BAIRRO)) AS bairro, "
	// + " RTRIM(LTRIM(logradouro.SIGLA_LOGRADOURO)) AS sigla, "
	// + " RTRIM(LTRIM(logradouro.DC_LOGRADOURO)) AS logradouro, "
	// + " imovel.NUM_ENDERECO AS numero, "
	// + " RTRIM(LTRIM(imovel.COMPL_ENDERECO)) AS complemento "
	// + "FROM "
	// + " FTTLE leitura "
	// + "JOIN FTTLE leituraAnterior ON "
	// + " leitura.MATRICULA_IMOVEL = leituraAnterior.MATRICULA_IMOVEL "
	// + " AND leituraAnterior.REF_FATURA = @referenciaAnterior "
	// + "JOIN FTTFA fatura ON "
	// + " fatura.MATRICULA_IMOVEL = leitura.MATRICULA_IMOVEL "
	// + " AND fatura.REF_FATURA = leitura.REF_FATURA "
	// + "JOIN CDACL cliente ON "
	// + " fatura.CD_CLIENTE = cliente.CD_CLIENTE "
	// + "JOIN CDAIM imovel ON "
	// + " imovel.MATRICULA_IMOVEL = fatura.MATRICULA_IMOVEL "
	// + "JOIN CDACI cidade ON "
	// + " imovel.CD_CIDADE = cidade.CD_CIDADE "
	// + "JOIN CDABA bairro ON "
	// + " imovel.CD_BAIRRO = bairro.CD_BAIRRO "
	// + " AND bairro.CD_CIDADE = imovel.CD_CIDADE "
	// + "JOIN CDALO logradouro ON "
	// + " imovel.CD_LOGRADOURO = logradouro.CD_LOGRADOURO "
	// + " AND imovel.CD_CIDADE = logradouro.CD_CIDADE "
	// + "JOIN CAD_TP_MEDICAO_ESGOTO tipoMedicaoEsgoto ON "
	// + " tipoMedicaoEsgoto.ID = leitura.TP_MEDICAO_ESG "
	// + "JOIN FAT_TP_FASE_FATURA tipoFaseFatura ON "
	// + " fatura.FASE = tipoFaseFatura.ID "
	// + "JOIN CDTGC grupoConsumo ON "
	// + " imovel.GRUPO_CONSUMO = grupoConsumo.GRUPO_CONSUMO "
	// + "JOIN CAD_SIT_LIGACAO_AGUA situacaoAgua ON "
	// + " situacaoAgua.ID_SIT_LIGACAO_AGUA = imovel.SIT_LIGACAO_AGUA "
	// + "JOIN CAD_SIT_LIGACAO_ESGOTO situacaoEsgoto ON "
	// + " situacaoEsgoto.ID_SIT_LIGACAO_ESGOTO = imovel.SIT_LIGACAO_ESGOTO "
	// + "LEFT JOIN EBL_RA_ITENS_ARQUIVOS_RECEBIMENTOS arquivoRecebido ON "
	// + " arquivoRecebido.DESC_INSTALACAO_SEM_DIGITO = @cdCliente "
	// + " AND arquivoRecebido.NUME_TIPO_FATURA = @origem "
	// + " AND arquivoRecebido.NUME_ANO = @ano "
	// + " AND arquivoRecebido.NUME_MES = @mes "
	// + "WHERE "
	// + " leitura.MATRICULA_IMOVEL = @matricula "
	// + " AND leitura.REF_FATURA = @referencia", nativeQuery = true)
	// FaturaImovelCabecalhoProjectionDTO
	// buscarFaturaImovelDetalhadaCabecalho(Integer idFatura);

	@Query(value = "   SELECT   "
			+ "  (CASE WHEN ConsultaFatura.valorTotalLigacao = 0 THEN ConsultaFatura.valorTotal ELSE ConsultaFatura.valorTotalLigacao  END) valorTotalLigacao,    "
			+ "  (CASE WHEN ConsultaFatura.valorTotalEconomias = 0 THEN ConsultaFatura.valorTotal ELSE ConsultaFatura.valorTotalEconomias  END) valorTotalEconomias,    "
			+ "  ConsultaFatura.valorTotal,    "
			+ "  ConsultaFatura.id,     "
			+ "  ConsultaFatura.dataArrecadacao,     "
			+ "  ConsultaFatura.dataBaixa,     "
			+ "  ConsultaFatura.matricula,     "
			+ "  ConsultaFatura.referencia,     "
			+ "  ConsultaFatura.dv,     "
			+ "  ConsultaFatura.ciclo,     "
			+ "  ConsultaFatura.origem,     "
			+ "  ConsultaFatura.versao,     "
			+ "  ConsultaFatura.cdCliente,     "
			+ "  ConsultaFatura.dvCliente,     "
			+ "  ConsultaFatura.nomeCliente,     "
			+ "  ConsultaFatura.dataLeitura,     "
			+ "  ConsultaFatura.leituraAnterior,    "
			+ "  ConsultaFatura.leituraAtual,     "
			+ "  ConsultaFatura.medido,     "
			+ "  ConsultaFatura.cdOcorrencia1,     "
			+ "  ConsultaFatura.cdOcorrencia2,     "
			+ "  ConsultaFatura.cdOcorrencia3,     "
			+ "  ConsultaFatura.tipoConsumoFaturamento,     "
			+ "  ConsultaFatura.consumoFaturadoAgua,     "
			+ "  ConsultaFatura.consumoFaturadoEsgoto,     "
			+ "  ConsultaFatura.consumoDisponibilidadeFaturado,     "
			+ "  ConsultaFatura.descontoEsgoto,     "
			+ "  ConsultaFatura.subvencao,     "
			+ "  ConsultaFatura.diasConsumo,     "
			+ "  ConsultaFatura.diasVenda,     "
			+ "  ConsultaFatura.tipoMedicaoEsgoto,     "
			+ "  ConsultaFatura.situacaoFatura,     "
			+ "  ConsultaFatura.dataVencimento,     "
			+ "  ConsultaFatura.dataTarifa,     "
			+ "  ConsultaFatura.grupoConsumo,     "
			+ "  ConsultaFatura.tratamentoEsgoto,     "
			+ "  ConsultaFatura.situacaoAgua,     "
			+ "  ConsultaFatura.situacaoEsgoto,     "
			+ "  ConsultaFatura.numeroEconomiasFaturamento,     "
			+ "  ConsultaFatura.numeroEconomiasCadastro,     "
			+ "  ConsultaFatura.localidade,     "
			+ "  ConsultaFatura.bairro,     "
			+ "  ConsultaFatura.sigla,     "
			+ "  ConsultaFatura.logradouro,     "
			+ "  ConsultaFatura.numero,     "
			+ "  ConsultaFatura.complemento    "
			+ "  FROM (    "
			+ "  SELECT     "
			+ "  (SubConsulta.valorTotalLigacaoDebito- SubConsulta.valorTotalLigacaoCredito) valorTotalLigacao,    "
			+ "  (SubConsulta.valorTotalEconomiasDebito- SubConsulta.valorTotalEconomiasCredito) valorTotalEconomias,    "
			+ "  (SubConsulta.valorTotalDebito- SubConsulta.valorTotalCredito) valorTotal,    "
			+ "  SubConsulta.id,     "
			+ "  SubConsulta.dataArrecadacao,     "
			+ "  SubConsulta.dataBaixa,     "
			+ "  SubConsulta.matricula,     "
			+ "  SubConsulta.referencia,     "
			+ "  SubConsulta.dv,     "
			+ "  SubConsulta.ciclo,     "
			+ "  SubConsulta.origem,     "
			+ "  SubConsulta.versao,     "
			+ "  SubConsulta.cdCliente,     "
			+ "  SubConsulta.dvCliente,     "
			+ "  SubConsulta.nomeCliente,     "
			+ "  SubConsulta.dataLeitura,     "
			+ "  SubConsulta.leituraAnterior,    "
			+ "  SubConsulta.leituraAtual,     "
			+ "  SubConsulta.medido,     "
			+ "  SubConsulta.cdOcorrencia1,     "
			+ "  SubConsulta.cdOcorrencia2,     "
			+ "  SubConsulta.cdOcorrencia3,     "
			+ "  SubConsulta.tipoConsumoFaturamento,     "
			+ "  SubConsulta.consumoFaturadoAgua,     "
			+ "  SubConsulta.consumoFaturadoEsgoto,     "
			+ "  SubConsulta.consumoDisponibilidadeFaturado,     "
			+ "  SubConsulta.descontoEsgoto,     "
			+ "  SubConsulta.subvencao,     "
			+ "  SubConsulta.diasConsumo,     "
			+ "  SubConsulta.diasVenda,     "
			+ "  SubConsulta.tipoMedicaoEsgoto,     "
			+ "  SubConsulta.situacaoFatura,     "
			+ "  SubConsulta.dataVencimento,     "
			+ "  SubConsulta.dataTarifa,     "
			+ "  SubConsulta.grupoConsumo,     "
			+ "  SubConsulta.tratamentoEsgoto,     "
			+ "  SubConsulta.situacaoAgua,     "
			+ "  SubConsulta.situacaoEsgoto,     "
			+ "  SubConsulta.numeroEconomiasFaturamento,     "
			+ "  SubConsulta.numeroEconomiasCadastro,     "
			+ "  SubConsulta.localidade,     "
			+ "  SubConsulta.bairro,     "
			+ "  SubConsulta.sigla,     "
			+ "  SubConsulta.logradouro,     "
			+ "  SubConsulta.numero,     "
			+ "  SubConsulta.complemento    "
			+ "  FROM (    "
			+ "  SELECT     "
			+ "  	fatura.ID AS id,     "
			+ "  	arquivoRecebido.DATA_PAGAMENTO_EFETIVADO AS dataArrecadacao,     "
			+ "  	(fatura.REF_BAIXA * 100) + fatura.DIA_BAIXA AS dataBaixa,     "
			+ "  	imovel.MATRICULA_IMOVEL AS matricula,     "
			+ "  	fatura.REF_FATURA AS referencia,     "
			+ "  	imovel.DV AS dv,     "
			+ "  	fatura.CICLO AS ciclo,     "
			+ "  	fatura.ORIGEM_FATURA AS origem,     "
			+ "  	fatura.VERSAO_FATURA AS versao,     "
			+ "  	cliente.CD_CLIENTE AS cdCliente,     "
			+ "  	cliente.DV AS dvCliente,     "
			+ "  	cliente.NOME AS nomeCliente,     "
			+ "  	leitura.DT_LEITURA AS dataLeitura,     "
			+ "  	leituraAnterior.LEITURA AS leituraAnterior,     "
			+ "  	leitura.LEITURA AS leituraAtual,     "
			+ "  	leitura.MEDIDO AS medido,     "
			+ "  	leitura.CD_OCORRENCIA AS cdOcorrencia1,     "
			+ "  	leitura.CD_OCORRENCIA2 AS cdOcorrencia2,     "
			+ "  	leitura.CD_OCORRENCIA3 AS cdOcorrencia3,     "
			+ "  	leitura.TP_CONSUMO_FATURAR AS tipoConsumoFaturamento,     "
			+ "  	fatura.VOL_AGUA AS consumoFaturadoAgua,     "
			+ "  	fatura.VOL_ESGOTO AS consumoFaturadoEsgoto,     "
			+ "  	fatura.VOL_DISP_ESG AS consumoDisponibilidadeFaturado,     "
			+ "  	fatura.DESC_ESGOTO AS descontoEsgoto,     "
			+ "  	fatura.PERC_TARIFA AS subvencao,     "
			+ "  	fatura.DIAS_CONSUMO AS diasConsumo,     "
			+ "  	fatura.DIAS_VENDA AS diasVenda,     "
			+ "  	tipoMedicaoEsgoto.DESCRICAO AS tipoMedicaoEsgoto,     "
			+ "  	baixa.DESCRICAO AS situacaoFatura,     "
			+ "  	fatura.DT_VENCIMENTO AS dataVencimento,     "
			+ "  	fatura.DT_TARIFA AS dataTarifa,     "
			+ "  	grupoConsumo.DC_GRUPO_CONSUMO AS grupoConsumo,     "
			+ "  	imovel.TRATAMENTO_ESGOTO AS tratamentoEsgoto,     "
			+ "  	situacaoAgua.DC_DESCRICAO AS situacaoAgua,     "
			+ "  	situacaoEsgoto.DC_DESCRICAO AS situacaoEsgoto,     "
			+ "  	fatura.UN_CONSUMO AS numeroEconomiasFaturamento,     "
			+ "  	fatura.UN_CONSUMO_CAD AS numeroEconomiasCadastro,     "
			+ "  	ISNULL((SELECT SUM(LF.VR_SERVICO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'D'),0) AS valorTotalDebito,    "
			+ "  	ISNULL((SELECT SUM(LF.VR_SERVICO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'C'),0) AS valorTotalCredito,    "
			+ "  	ISNULL((SELECT SUM(LF.VR_SERVICO_1ECO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'D'),0) AS valorTotalLigacaoDebito,     "
			+ "  		ISNULL((SELECT SUM(LF.VR_SERVICO_1ECO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'C'),0) AS valorTotalLigacaoCredito,     "
			+ "  	ISNULL((SELECT SUM(LF.VR_SERVICO_MECO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'D'),0) AS valorTotalEconomiasDebito,     "
			+ "  	ISNULL((SELECT SUM(LF.VR_SERVICO_MECO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'C'),0) AS valorTotalEconomiasCredito,     "
			+ "  	RTRIM(LTRIM(cidade.DC_CIDADE)) AS localidade,     "
			+ "  	RTRIM(LTRIM(bairro.DC_BAIRRO)) AS bairro,     "
			+ "  	RTRIM(LTRIM(logradouro.SIGLA_LOGRADOURO)) AS sigla,     "
			+ "  	RTRIM(LTRIM(logradouro.DC_LOGRADOURO)) AS logradouro,     "
			+ "  	imovel.NUM_ENDERECO AS numero,     "
			+ "  	RTRIM(LTRIM(imovel.COMPL_ENDERECO)) AS complemento     "
			+ "  FROM FTTFA FATURA    "
			+ "  LEFT JOIN FTTLE LEITURA ON LEITURA.REF_FATURA = FATURA.REF_FATURA AND FATURA.MATRICULA_IMOVEL = LEITURA.MATRICULA_IMOVEL    "
			+ "  LEFT JOIN FTTLE leituraAnterior on leituraAnterior.MATRICULA_IMOVEL = leitura.MATRICULA_IMOVEL AND leituraAnterior.REF_FATURA = (    "
			+ "  SELECT TOP 1 FaturaAnterior.REF_FATURA FROM FTTFA FaturaAnterior WHERE 1=1    "
			+ "  AND FaturaAnterior.MATRICULA_IMOVEL = FATURA.MATRICULA_IMOVEL    "
			+ "  AND FaturaAnterior.REF_FATURA < FATURA.REF_FATURA    "
			+ "  ORDER BY 1 DESC    )    "
			+ "  JOIN CDACL cliente ON     "
			+ "  	fatura.CD_CLIENTE = cliente.CD_CLIENTE     "
			+ "  LEFT JOIN CDAIM imovel ON     "
			+ "  	imovel.MATRICULA_IMOVEL = fatura.MATRICULA_IMOVEL     "
			+ "  LEFT JOIN CDACI cidade ON     "
			+ "  	imovel.CD_CIDADE = cidade.CD_CIDADE     "
			+ "  LEFT JOIN CDABA bairro ON     "
			+ "  	imovel.CD_BAIRRO = bairro.CD_BAIRRO     "
			+ "  	AND bairro.CD_CIDADE = imovel.CD_CIDADE     "
			+ "  LEFT JOIN CDALO logradouro ON     "
			+ "  	imovel.CD_LOGRADOURO = logradouro.CD_LOGRADOURO     "
			+ "  	AND imovel.CD_CIDADE = logradouro.CD_CIDADE     "
			+ "  LEFT JOIN CAD_TP_MEDICAO_ESGOTO tipoMedicaoEsgoto ON     "
			+ "  	tipoMedicaoEsgoto.ID = leitura.TP_MEDICAO_ESG     "
			+ "  LEFT JOIN CAD_TP_BAIXA baixa ON   "
			+ "	baixa.ID = fatura.TP_BAIXA     "
			+ "  LEFT JOIN CDTGC grupoConsumo ON     "
			+ "  	imovel.GRUPO_CONSUMO = grupoConsumo.GRUPO_CONSUMO     "
			+ "  LEFT JOIN CAD_SIT_LIGACAO_AGUA situacaoAgua ON     "
			+ "  	situacaoAgua.ID_SIT_LIGACAO_AGUA = imovel.SIT_LIGACAO_AGUA     "
			+ "  LEFT JOIN CAD_SIT_LIGACAO_ESGOTO situacaoEsgoto ON     "
			+ "  	situacaoEsgoto.ID_SIT_LIGACAO_ESGOTO = imovel.SIT_LIGACAO_ESGOTO    "
			+ "  LEFT JOIN EBL_RA_ITENS_ARQUIVOS_RECEBIMENTOS arquivoRecebido     "
			+ "  ON arquivoRecebido.DESC_INSTALACAO_SEM_DIGITO = imovel.MATRICULA_IMOVEL     "
			+ "  AND arquivoRecebido.NUME_TIPO_FATURA = FATURA.ORIGEM_FATURA     "
			+ "  AND arquivoRecebido.NUME_ANO = (FATURA.REF_FATURA / 100)     "
			+ "  AND arquivoRecebido.NUME_MES = (FATURA.REF_FATURA % 100)    "
			+ "  	) SubConsulta    "
			+ "  	) ConsultaFatura    "
			+ "  WHERE ConsultaFatura.ID = :idFatura  ", nativeQuery = true)
	FaturaImovelCabecalhoProjectionDTO buscarFaturaImovelDetalhadaCabecalho(Integer idFatura);

	@Query(value = "   SELECT  "
			+ "  lancamentoFatura.CD_SERVICO AS codigoServico,   "
			+ "  lancamentoFatura.VR_SERVICO AS valorServico,    "
			+ "  lancamentoFatura.D_C AS creditoDebito,  "
			+ "  servico.DC_SERVICO AS dcServico "
			+ "  FROM FTTLA lancamentoFatura     "
			+ "  INNER JOIN SRASE servico on servico.CD_SERVICO = lancamentoFatura.CD_SERVICO "
			+ "  WHERE lancamentoFatura.ID_FATURA = :idFatura    "
			+ "  GROUP BY lancamentoFatura.CD_SERVICO, lancamentoFatura.VR_SERVICO, lancamentoFatura.D_C, servico.DC_SERVICO ", nativeQuery = true)
	List<FaturaImovelLancamentoProjectionDTO> buscarFaturaImovelDetalhadaLancamentos(Integer idFatura);
}

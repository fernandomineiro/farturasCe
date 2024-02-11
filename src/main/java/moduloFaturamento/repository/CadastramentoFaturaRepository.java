package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.cadastramentoFatura.ClienteDropDownDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridProjectionDTO;
import moduloFaturamento.dto.cadastramentoFatura.InformacoesImovelDTO;
import moduloFaturamento.dto.cadastramentoFatura.SituacaoBaixaDropDownDTO;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdFatura;

@Repository
public interface CadastramentoFaturaRepository extends JpaRepository<Fatura, IdFatura> {

	@Query(value = "SELECT * FROM CAD_TP_BAIXA tipoBaixa", nativeQuery = true)
	List<SituacaoBaixaDropDownDTO> buscarListaSituacaoDropDown();

	@Query(value = "SELECT DISTINCT cliente.CD_CLIENTE cdCliente, cliente.NOME nome FROM FTTFA fatura  "
			+ "JOIN CDACL cliente ON fatura.CD_CLIENTE = cliente.CD_CLIENTE  "
			+ "WHERE MATRICULA_IMOVEL = :matricula " + "", nativeQuery = true)
	List<ClienteDropDownDTO> buscarListaClientesDropDown(Integer matricula);

	@Query(value = "SELECT "
			+ "	LTRIM(RTRIM(cliente.CD_CLIENTE)) AS cdCliente, "
			+ "	LTRIM(RTRIM(cliente.DV)) AS dvCliente, "
			+ "	LTRIM(RTRIM(cliente.NOME)) AS nomeCliente, "
			+ "	LTRIM(RTRIM(cidade.DC_CIDADE)) AS descricaoCidade, "
			+ "	LTRIM(RTRIM(bairro.DC_BAIRRO)) AS descricaoBairro, "
			+ "	LTRIM(RTRIM(logradouro.DC_LOGRADOURO)) AS descricaoLogradouro, "
			+ "	imovel.NUM_ENDERECO AS numeroEndereco, "
			+ "	LTRIM(RTRIM(imovel.COMPL_ENDERECO)) AS complemento, "
			+ "	LTRIM(RTRIM(imovel.NOME_IMOVEL)) AS nomeImovel, "
			+ "	imovel.GRUPO_CONSUMO AS grupoConsumo, "
			+ "	1 AS numeroEconomias, "
			+ "	'tipo de ligação' AS descricaoTipoLigacaoAgua "
			+ " FROM "
			+ "	CDAIM imovel "
			+ " JOIN "
			+ "	CDACL cliente ON "
			+ "	imovel.CD_CLIENTE = cliente.CD_CLIENTE "
			+ " JOIN CDACI cidade ON "
			+ "	cidade.CD_CIDADE = imovel.CD_CIDADE "
			+ " JOIN CDABA bairro ON "
			+ "	bairro.CD_CIDADE = imovel.CD_CIDADE "
			+ "	AND bairro.CD_BAIRRO = imovel.CD_BAIRRO "
			+ " JOIN CDALO logradouro ON "
			+ "	logradouro.CD_CIDADE = imovel.CD_CIDADE "
			+ "	AND logradouro.CD_LOGRADOURO = imovel.CD_LOGRADOURO "
			+ " WHERE "
			+ "	imovel.MATRICULA_IMOVEL = :matricula", nativeQuery = true)
	InformacoesImovelDTO buscarInformacoesImovel(Integer matricula);

	@Query(value = " SELECT "
			+ "  SubConsulta.id,  "
			+ " 	SubConsulta.refFatura,  "
			+ " 	SubConsulta.origemFatura,  "
			+ " 	SubConsulta.versaoFatura,  "
			+ " 	(SubConsulta.valorTotalDebito- SubConsulta.valorTotalCredito) valorFatura,   "
			+ " 	SubConsulta. dataVencimento,  "
			+ " 	SubConsulta. dataPagamento,  "
			+ "	SubConsulta.dvCliente, "
			+ "  SubConsulta.refBaixa,  "
			+ "  SubConsulta. diaBaixa,  "
			+ " 	SubConsulta.situacaoBaixa,  "
			+ " 	SubConsulta. codigoCliente,  "
			+ " 	SubConsulta. nomeCliente  "
			+ "  FROM ( "
			+ "  SELECT  "
			+ " 	fatura.ID AS id,  "
			+ " 	fatura.REF_FATURA AS refFatura,  "
			+ " 	fatura.ORIGEM_FATURA AS origemFatura,  "
			+ " 	fatura.VERSAO_FATURA versaoFatura,  "
			+ " 	ISNULL((SELECT SUM(LF.VR_SERVICO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'D'),0) AS valorTotalDebito,     "
			+ "   	ISNULL((SELECT SUM(LF.VR_SERVICO) FROM FTTLA LF WHERE LF.ID_FATURA = FATURA.ID AND LF.D_C = 'C'),0) AS valorTotalCredito,     "
			+ " 	fatura.DT_VENCIMENTO AS dataVencimento,  "
			+ " 	(CONVERT(VARCHAR, fatura.REF_BAIXA) + '01') dataPagamento,  "
			+ "	cliente.DV dvCliente, "
			+ "  fatura.REF_BAIXA refBaixa,  "
			+ "  fatura.DIA_BAIXA diaBaixa,  "
			+ " 	baixa.DESCRICAO AS situacaoBaixa,  "
			+ " 	cliente.CD_CLIENTE AS codigoCliente,  "
			+ " 	cliente.NOME as nomeCliente  "
			+ "  FROM  "
			+ " 	FTTFA fatura  "
			+ "  LEFT JOIN CDACL cliente ON  "
			+ " 	fatura.CD_CLIENTE = cliente.CD_CLIENTE  "
			+ "  LEFT JOIN CAD_TP_BAIXA baixa ON  "
			+ " 	baixa.ID = fatura.TP_BAIXA  "
			+ " 	WHERE 1=1 "
			+ " 	AND fatura.MATRICULA_IMOVEL = :matricula  "
			+ " 	AND (:refFatura IS NULL  "
			+ " 	OR fatura.REF_FATURA = :refFatura)  "
			+ " 	AND (:tpBaixa IS NULL  "
			+ " 	OR fatura.TP_BAIXA = :tpBaixa)  "
			+ " 	AND (:cdCliente IS NULL  "
			+ " 	OR fatura.CD_CLIENTE = :cdCliente) "
			+ " 	) SubConsulta "

			, nativeQuery = true)
	List<FaturaGridProjectionDTO> listarFaturasGrid(Integer matricula, Integer refFatura, Integer cdCliente,
			Short tpBaixa);
}

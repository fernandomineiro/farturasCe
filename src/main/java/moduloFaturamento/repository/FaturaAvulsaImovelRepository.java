package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.faturaAvulsaImovel.projection.DadosImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaAvulsaCriadaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaPorTipoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaProjectionDTO;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdFatura;

@Repository
public interface FaturaAvulsaImovelRepository extends JpaRepository<Fatura, IdFatura> {

	@Query(nativeQuery = true, value = "SELECT 5 AS origem,  1 versao, imovel.TRATAMENTO_ESGOTO tratamentoEsgoto, imovel.CICLO AS ciclo, cliente.CD_CLIENTE AS cdCliente, cliente.DV dvCliente, "
			+ " cliente.NOME AS nomeCliente, cidade.DC_CIDADE AS dcCidade, bairro.DC_BAIRRO AS dcBairro, "
			+ " logradouro.DC_LOGRADOURO AS dcLogradouro, imovel.NUM_ENDERECO AS numero, imovel.COMPL_ENDERECO AS complemento FROM "
			+ " CDAIM imovel JOIN CDACL cliente ON cliente.CD_CLIENTE = imovel.CD_CLIENTE JOIN CDACI cidade ON "
			+ " cidade.CD_CIDADE = imovel.CD_CIDADE JOIN CDABA bairro ON bairro.CD_CIDADE = imovel.CD_CIDADE "
			+ " AND bairro.CD_BAIRRO = imovel.CD_BAIRRO JOIN CDALO logradouro ON logradouro.CD_CIDADE = imovel.CD_CIDADE "
			+ " AND logradouro.CD_LOGRADOURO = imovel.CD_LOGRADOURO WHERE imovel.MATRICULA_IMOVEL = :matricula")
	DadosImovelClienteProjectionDTO buscarDadosImovelClienteCriacaoFaturaAvulsa(Integer matricula);

	@Query(nativeQuery = true, value = "SELECT fatura.ORIGEM_FATURA AS origem, CASE WHEN "
			+ " fatura.VERSAO_FATURA IS NULL THEN 1 ELSE fatura.VERSAO_FATURA END AS versao, fatura.CICLO AS ciclo, "
			+ " imovel.CD_CLIENTE AS cdCliente, cliente.NOME AS nomeCliente, cidade.DC_CIDADE AS dcCidade, "
			+ " bairro.DC_BAIRRO AS dcBairro, logradouro.DC_LOGRADOURO AS dcLogradouro, imovel.NUM_ENDERECO AS numero, "
			+ " imovel.COMPL_ENDERECO AS complemento FROM CDAIM imovel JOIN FTTFA fatura ON "
			+ " imovel.MATRICULA_IMOVEL = fatura.MATRICULA_IMOVEL JOIN CDACL cliente ON cliente.CD_CLIENTE = imovel.CD_CLIENTE "
			+ " JOIN CDACI cidade ON cidade.CD_CIDADE = imovel.CD_CIDADE JOIN CDABA bairro ON bairro.CD_CIDADE = imovel.CD_CIDADE "
			+ " AND bairro.CD_BAIRRO = imovel.CD_BAIRRO JOIN CDALO logradouro ON logradouro.CD_CIDADE = imovel.CD_CIDADE "
			+ " AND logradouro.CD_LOGRADOURO = imovel.CD_LOGRADOURO WHERE imovel.MATRICULA_IMOVEL = :matricula AND fatura.REF_FATURA = :referencia")
	DadosImovelClienteProjectionDTO buscarDadosImovelClienteEdicaoFaturaAvulsa(Integer matricula, Integer referencia);

	@Query(nativeQuery = true, value = " IF OBJECT_ID(N'tempdb..#configuracao') IS NOT NULL BEGIN DROP TABLE #configuracao END "
			+ "SELECT imovel.CD_CIDADE AS cdCidadeImovel, configuracao.CD_CIDADE AS cdCidadeConfiguracao, configuracao.CD_SRV_AGUA AS cdServico, servico.DC_SERVICO AS dcServico, configuracao.REF_FATURA_INI, configuracao.REF_FATURA_FIM "
			+ "INTO #configuracao  FROM FTTSV configuracao  JOIN CDAIM imovel ON imovel.GRUPO_CONSUMO = configuracao.GRUPO_CONSUMO "
			+ "JOIN SRASE servico ON  servico.CD_SERVICO = configuracao.CD_SRV_AGUA  WHERE imovel.MATRICULA_IMOVEL = :matricula   "
			+ "INSERT INTO #configuracao "
			+ "SELECT imovel.CD_CIDADE AS cdCidadeImovel, configuracao.CD_CIDADE AS cdCidadeConfiguracao, configuracao.CD_SRV_ESGT_TRAT AS cdServico, servico.DC_SERVICO AS dcServico, configuracao.REF_FATURA_INI, configuracao.REF_FATURA_FIM "
			+ "FROM FTTSV configuracao  JOIN CDAIM imovel ON imovel.GRUPO_CONSUMO = configuracao.GRUPO_CONSUMO  JOIN SRASE servico ON "
			+ "servico.CD_SERVICO = configuracao.CD_SRV_ESGT_TRAT  WHERE imovel.MATRICULA_IMOVEL = :matricula    INSERT INTO #configuracao "
			+ "SELECT imovel.CD_CIDADE AS cdCidadeImovel, configuracao.CD_CIDADE AS cdCidadeConfiguracao, configuracao.CD_SRV_ESGT_N_TRAT AS cdServico, servico.DC_SERVICO AS dcServico, configuracao.REF_FATURA_INI, configuracao.REF_FATURA_FIM "
			+ "FROM FTTSV configuracao  JOIN CDAIM imovel ON imovel.GRUPO_CONSUMO = configuracao.GRUPO_CONSUMO  JOIN SRASE servico ON "
			+ "servico.CD_SERVICO = configuracao.CD_SRV_ESGT_N_TRAT  WHERE imovel.MATRICULA_IMOVEL = :matricula    INSERT INTO #configuracao "
			+ "SELECT imovel.CD_CIDADE AS cdCidadeImovel, configuracao.CD_CIDADE AS cdCidadeConfiguracao, configuracao.CD_SRV_DISP_ESG AS cdServico, servico.DC_SERVICO AS dcServico, configuracao.REF_FATURA_INI, configuracao.REF_FATURA_FIM "
			+ "FROM FTTSV configuracao  JOIN CDAIM imovel ON imovel.GRUPO_CONSUMO = configuracao.GRUPO_CONSUMO  JOIN SRASE servico ON "
			+ "servico.CD_SERVICO = configuracao.CD_SRV_DISP_ESG  WHERE imovel.MATRICULA_IMOVEL = :matricula   "
			+ "SELECT cdCidadeImovel, cdCidadeConfiguracao, cdServico, dcServico from #configuracao "
			+ "WHERE #configuracao.REF_FATURA_INI <= :referencia AND #configuracao.REF_FATURA_FIM >= :referencia "
			+ "GROUP BY cdCidadeImovel, cdCidadeConfiguracao, cdServico, dcServico  ORDER BY cdCidadeConfiguracao    DROP TABLE #configuracao")
	List<ServicoFaturaProjectionDTO> buscarServicosDropDown(Integer matricula, Integer referencia);

	@Query(nativeQuery = true, value = "SELECT DISTINCT    "
			+ "   Consulta.cdServico,     "
			+ "  Consulta.cddcServico,   "
			+ "  Consulta.permiteInclusao,       "
			+ "  CASE WHEN Consulta.tipoServico = 'C' THEN 'Credito' ELSE 'Debito' END tpServico       "
			+ "  FROM (  "
			+ "  SELECT servico.PERM_FAT_CREDITO,    "
			+ "  servico.CD_SERVICO cdServico,   "
			+ "  servico.DC_SERVICO dcServico,   "
			+ "  imovel.MATRICULA_IMOVEL,   "
			+ "  (CONVERT(VARCHAR,servico.CD_SERVICO) +' - '+ servico.DC_SERVICO) cddcServico,   "
			+ "  CASE WHEN servico.PERM_FAT_CREDITO = 'S' THEN 'C' ELSE CASE WHEN servico.PERM_FAT_DEBITO = 'S' THEN 'D' ELSE 'A' END   END tipoServico ,    "
			+ "  (CASE WHEN servico.PERM_FAT_AVULSA_IMOVEL = 'S' THEN 'true' ELSE 'false' END) as permiteInclusao       "
			+ "  FROM SRASE servico  "
			+ "  INNER JOIN CTTCF configuracao on configuracao.CD_SERVICO = servico.CD_SERVICO "
			+ "  INNER JOIN SAP_CENTRO_CUSTOS centroCusto on centroCusto.NR_CONTA_CONTABIL_SAP = configuracao.NR_CONTA_CTB_SAP "
			+ "  INNER JOIN CDAIM imovel on imovel.CD_CIDADE = centroCusto.CD_CIDADE "
			+ "	 WHERE configuracao.CD_EVENTO in (10,12,13) "
			+ "  ) Consulta  "
			+ "  WHERE Consulta.tipoServico = (CASE WHEN  :tipoServico IS NULL THEN Consulta.tipoServico ELSE :tipoServico END) AND Consulta.permiteInclusao = 'true'  AND Consulta.MATRICULA_IMOVEL = :matriculaSemDv")
	List<ServicoFaturaPorTipoProjectionDTO> buscarServicosDropDown(String tipoServico, Integer matriculaSemDv);

	@Query(nativeQuery = true, value = "SELECT DISTINCT    "
			+ "   Consulta.cdServico,     "
			+ "  Consulta.cddcServico,   "
			+ "  Consulta.permiteInclusao,       "
			+ "  Consulta.dcServico,	"
			+ "  CASE WHEN Consulta.tipoServico = 'C' THEN 'Credito' ELSE 'Debito' END tpServico       "
			+ "  FROM (  "
			+ "  SELECT servico.PERM_FAT_CREDITO,    "
			+ "  servico.CD_SERVICO cdServico,   "
			+ "  servico.DC_SERVICO dcServico,   "
			+ "  (CONVERT(VARCHAR,servico.CD_SERVICO) +' - '+ servico.DC_SERVICO) cddcServico,   "
			+ "  CASE WHEN servico.PERM_FAT_CREDITO = 'S' THEN 'C' ELSE CASE WHEN servico.PERM_FAT_DEBITO = 'S' THEN 'D' ELSE 'A' END   END tipoServico ,    "
			+ "  (CASE WHEN servico.PERM_FAT_AVULSA_IMOVEL = 'S' THEN 'true' ELSE 'false' END) as permiteInclusao       "
			+ "  FROM SRASE servico  "
			+ "  ) Consulta  "
			+ "  WHERE Consulta.tipoServico = (CASE WHEN  :tipoServico IS NULL THEN Consulta.tipoServico ELSE :tipoServico END) AND Consulta.permiteInclusao = 'true'")
	List<ServicoFaturaPorTipoProjectionDTO> buscarServicosDropDown(String tipoServico);

	@Query(nativeQuery = true, value = " SELECT "
			+ "  Consulta.cdCliente, "
			+ "  Consulta.dvCliente, "
			+ "  Consulta.nomeCliente    "
			+ "  FROM (  "
			+ "  SELECT DISTINCT CLIENTE.CD_CLIENTE cdCliente, CLIENTE.DV dvCliente, CLIENTE.NOME nomeCliente, FATURA.MATRICULA_IMOVEL  FROM FTTFA FATURA  "
			+ "  INNER JOIN CDACL CLIENTE ON CLIENTE.CD_CLIENTE = FATURA.CD_CLIENTE  "
			+ "  ) Consulta  "
			+ "  WHERE Consulta.MATRICULA_IMOVEL = :matriculaSemDv   ")
	List<FaturaImovelClienteProjectionDTO> buscarClientesDropDown(Integer matriculaSemDv);

	@Query(nativeQuery = true, value = "SELECT ID FROM FTTFA WHERE REF_FATURA = :refFatura AND ORIGEM_FATURA = :origemFatura AND MATRICULA_IMOVEL = :matriculaSemDv ")
	Long buscarIdFatura(Integer refFatura, short origemFatura, Integer matriculaSemDv);

	@Query(nativeQuery = true, value = "SELECT *FROM FTTFA WHERE ID = :idFatura")
	Fatura buscaFaturaPorId(Long idFatura);
}

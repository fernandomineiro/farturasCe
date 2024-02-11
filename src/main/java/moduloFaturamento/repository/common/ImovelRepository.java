package moduloFaturamento.repository.common;

import java.util.List;
import java.util.Optional;

import moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaBuscarMatriculaImovelProjectionDTO;
import moduloFaturamento.dto.imovel.projection.CodigoClienteDoImovelDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.EnderecoBasicoDoImovelProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelCabecalhoDTO;
import moduloFaturamento.dto.notificaoFatura.projection.NotificacaoMatriculaCicloProjectionDTO;
import moduloFaturamento.model.common.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

	@Query(value = "select im.matriculaImovel as matricula, im.ciclo as ciclo from Imovel im where im.matriculaImovel in (:matriculas)")
	List<NotificacaoMatriculaCicloProjectionDTO> buscarMatriculasComCiclos(List<Integer> matriculas);

	Optional<Imovel> findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(Integer matriculaImovel, Short dv);

	@Query(value = "SELECT imovel.cdCidade FROM Imovel imovel WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dv = :dv AND imovel.dataHoraExclusao IS NULL")
	Short buscarCidadeDoImovelExistentePelaMatricula(@Param(value = "matriculaImovel") Integer matriculaImovel, @Param("dv") Short dv);

	@Query(value = "SELECT imovel.cdCidade FROM Imovel imovel WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dataHoraExclusao IS NULL")
	Short buscarCidadeDoImovelExistentePelaMatricula(@Param(value = "matriculaImovel") Integer matriculaImovel);

	@Query(value = "SELECT imovel.dv FROM Imovel imovel WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dataHoraExclusao IS NULL")
	Short buscarDvDoImovelPelaMatricula(@Param(value = "matriculaImovel") Integer matriculaImovel);

	@Query(value = "SELECT imovel.ciclo FROM Imovel imovel WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dataHoraExclusao IS NULL")
	Short buscarCicloDoImovelPelaMatricula(@Param(value = "matriculaImovel") Integer matriculaImovel);

	Imovel findByMatriculaImovelAndDataHoraExclusaoIsNull(Integer matricula);

	@Query(value = "SELECT imovel.matriculaImovel FROM Imovel imovel WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.tipoMedicaoInd = 1 AND imovel.dataHoraExclusao IS NULL")
	Integer buscarSeMatriculaEhMacro(@Param(value = "matriculaImovel") Integer matriculaImovel);

	@Query(value = "SELECT new moduloFaturamento.dto.imovel.projection.CodigoClienteDoImovelDTO(imovel.cdCliente, imovel.dvCliente) " +
			"FROM Imovel imovel " +
			"WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dv = :dv AND imovel.dataHoraExclusao IS NULL")
	CodigoClienteDoImovelDTO buscarClientePelaMatriculaDoImovel(@Param(value = "matriculaImovel") Integer matriculaImovel, @Param("dv") Short dv);

	@Query(value = "SELECT new moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelCabecalhoDTO(" +
			"cliente.nome, cliente.clienteEspecial, cliente.tipoCliente, logradouro.siglaLogradouro, logradouro.nomeLogradouro, bairro.nomeBairro, localidade.dcCidade, imovel.complEndereco, imovel.numEndereco, imovel.ciclo, imovel.sequenciaRota," +
			"situacaoLigacaoAgua.descricao, situacaoLigacaoEsgoto.descricao, hidrometroInstalado.codHidrometro, hidrometroInstalado.dataInstalacao, hidrometroInstalado.leituraInstalacao, " +
			"imovel.dataReativacaAgua, hidrometroCaracteristicas.ponteirosHidro, imovel.tipoMedicaoInd, imovel.matriculaMacro, criterioFatDecJudicial.descricao) " +
			"" +
			"FROM Imovel imovel " +
			"INNER JOIN Cliente cliente ON imovel.cdCliente = cliente.cdCliente " +
			"INNER JOIN Logradouro logradouro ON logradouro.idLogradouro.codLogradouro = imovel.cdLogradouro AND logradouro.idLogradouro.codLocalidade = imovel.cdCidade " +
			"INNER JOIN Bairro bairro ON bairro.idBairro.cdBairro = imovel.cdBairro AND bairro.idBairro.cdLocalidade = imovel.cdCidade " +
			"INNER JOIN Localidade localidade  ON localidade.cdCidade = imovel.cdCidade " +
			"FULL JOIN SituacaoLigacaoAgua situacaoLigacaoAgua ON situacaoLigacaoAgua.id = imovel.situacaoLigacaoAgua.id " +
			"FULL JOIN SituacaoLigacaoEsgoto situacaoLigacaoEsgoto ON situacaoLigacaoEsgoto.id = imovel.situacaoLigacaoEsgoto.id " +
			"FULL JOIN HidrometroInstalado hidrometroInstalado ON hidrometroInstalado.matriculaImovel = imovel.matriculaImovel " +
			"FULL JOIN Hidrometro hidrometro ON hidrometro.codHidrometro = hidrometroInstalado.codHidrometro " +
			"FULL JOIN HidrometroCaracteristicas hidrometroCaracteristicas ON  hidrometroCaracteristicas.codCaracteristicaHidro = hidrometro.hidrometroCaracteristicas.codCaracteristicaHidro " +
			"FULL JOIN CriterioFaturamentoDecisaoJudicial criterioFatDecJudicial ON criterioFatDecJudicial.id = imovel.criticaFaturamentoDecisaoJudicial " +

			"WHERE imovel.matriculaImovel = :matriculaImovel AND imovel.dataHoraExclusao IS NULL")
	LeituraConsumoImovelCabecalhoDTO buscarImovelParaCabecalhoLeituraConsumoPelaMatricula(@Param(value = "matriculaImovel") Integer matriculaImovel);


	@Query(value = "SELECT gc.MINIMO from CDAIM imovel INNER JOIN CDTGC gc ON gc.GRUPO_CONSUMO = imovel.GRUPO_CONSUMO WHERE imovel.MATRICULA_IMOVEL = :matricula", nativeQuery = true)
	Integer buscarValorMinimoAtravesDoGrupoDeConsumoDoImovel(Integer matricula);

	@Query(value = "SELECT gc.BASICO from CDAIM imovel INNER JOIN CDTGC gc ON gc.GRUPO_CONSUMO = imovel.GRUPO_CONSUMO WHERE imovel.MATRICULA_IMOVEL = :matricula", nativeQuery = true)
	Integer buscarValorBasicoAtravesDoGrupoDeConsumoDoImovel(Integer matricula);

	@Query(value = "SELECT imovel.matriculaImovel FROM Imovel imovel WHERE imovel.matriculaMacro = :matriculaMacro AND imovel.tipoMedicaoInd = 2 AND imovel.dataHoraExclusao IS NULL")
	List<Integer> buscarMatriculaMicroDeImovelMacro(@Param("matriculaMacro") Integer matriculaMacro);

	@Query(value = "SELECT new moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO(imovel.matriculaImovel, imovel.dv) " +
			"FROM Imovel imovel WHERE imovel.matriculaMacro = :matriculaMacro AND imovel.tipoMedicaoInd = 2 AND imovel.dataHoraExclusao IS NULL")
	List<ImovelMatriculaComDvProjectionDTO> buscarMatriculaMicroComDvDeImovelMacro(@Param("matriculaMacro") Integer matriculaMacro);

	@Query(value = "select MATRICULA_IMOVEL FROM CDAIM WHERE MATRICULA_MAE = :matriculaMae", nativeQuery = true)
	List<Integer> retornarMatriculasSecundariasMatriculaMae(Integer matriculaMae);

	@Query(nativeQuery = true, value = "select CD_CIDADE from CDAIM where MATRICULA_IMOVEL = :matricula")
	Integer buscarCdCidadeDoImovel(Integer matricula);

	@Query(nativeQuery = true, value = "SELECT ID_FILIAL_SAP FROM CAD_LOCALIDADE_SICAT_FILIAL_SAP WHERE CD_CIDADE = :cdCidade")
	Integer buscarCdFilialSap(Integer cdCidade);

	@Query(nativeQuery = true, value = "SELECT CD_CLIENTE FROM CDAIM WHERE MATRICULA_IMOVEL = :matricula")
    Integer buscarCdClienteDoImovel(Integer matricula);

	@Query(nativeQuery = true, value = "select imovel.MATRICULA_IMOVEL AS matricula,"
											+ " localidade.DC_CIDADE AS nomeDaCidade,"
											+ " bairro.DC_BAIRRO AS nomeDoBairro,"
											+ " logradouro.DC_LOGRADOURO AS nomeDaRua,"
											+ " imovel.NUM_ENDERECO AS numeroImovel,"
											+ " imovel.COMPL_ENDERECO AS complemento"
											+ " from CDAIM imovel"
											+ " INNER JOIN CDACI localidade ON localidade.CD_CIDADE = imovel.CD_CIDADE"
											+ " INNER JOIN CDABA bairro ON bairro.CD_BAIRRO = imovel.CD_BAIRRO AND imovel.CD_CIDADE = bairro.CD_CIDADE"
											+ " INNER JOIN CDALO logradouro ON imovel.CD_LOGRADOURO =  logradouro.CD_LOGRADOURO AND logradouro.CD_CIDADE = imovel.CD_CIDADE"
											+ " WHERE imovel.MATRICULA_IMOVEL = :matricula")
	EnderecoBasicoDoImovelProjectionDTO buscarEnderecoBasicoDoImovel(Integer matricula);

	@Query(nativeQuery = true, value = "select imovel.CD_CIDADE AS cdCidade, localidade.DC_CIDADE AS descricaoCidade"
							+ " from CDAIM imovel INNER JOIN CDACI localidade ON localidade.CD_CIDADE = imovel.CD_CIDADE"
							+ " where imovel.MATRICULA_IMOVEL = :matriculaImovel")
	FaturaAvulsaBuscarMatriculaImovelProjectionDTO buscarCdCidadeEDescricaoCidadePelaMatricula(Integer matriculaImovel);

	@Query(nativeQuery = true, value = "SELECT GRUPO_CONSUMO FROM CDAIM WHERE MATRICULA_IMOVEL = :matriculaImovel")
	Short buscarGrupoDeConsumoAtravesDaMatriculaIMovel(Integer matriculaImovel);

	@Query(nativeQuery = true, value = "SELECT SIT_LIGACAO_AGUA FROM CDAIM WHERE MATRICULA_IMOVEL = :matriculaImovel")
	Short buscarSituacaoAguaDoImovel(Integer matriculaImovel);
	
	@Query(nativeQuery = true, value = "SELECT SIT_LIGACAO_AGUA from CDAIM c WHERE CD_CLIENTE = :cdCliente")
	Short buscarSituacaoAguaDoImovelPorCdCliente(Integer cdCliente);

	@Query(nativeQuery = true, value = "SELECT SIT_LIGACAO_ESGOTO FROM CDAIM WHERE MATRICULA_IMOVEL = :matriculaImovel")
	Short buscarSituacaoEsgotoDoImovel(Integer matriculaImovel);
}

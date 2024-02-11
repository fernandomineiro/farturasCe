package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasArquivosProjectionRespostaDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasRealizadasProjectionRespostaDTO;
import moduloFaturamento.model.AmostrasRealizadas;
import moduloFaturamento.model.IdAmostrasRealizadas;

@Repository
public interface AmostrasRealizadasRepository extends JpaRepository<AmostrasRealizadas, IdAmostrasRealizadas> {

	@Query(nativeQuery = true, value = "SELECT * FROM ATTAR WHERE REF_AMOSTRAS = :refAmostras")
	List<AmostrasRealizadas> buscarAmostrasRealizadasPorRefAmostras(Integer refAmostras);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(REF_AMOSTRAS) FROM ATTAR WHERE REF_AMOSTRAS = :refAmostras")
	Integer contarAmostrasRealizadasPorRefAmostras(Integer refAmostras);
	
	@Query(nativeQuery = true, value = "SELECT localidade.CD_CIDADE AS cdCidade, localidade.DC_CIDADE AS dcCidade FROM ATTAR amostra "
			+ "JOIN CDACI localidade ON amostra.CD_CIDADE = localidade.CD_CIDADE WHERE REF_AMOSTRAS = :refAmostras")
	List<PesquisaAmostrasRealizadasProjectionRespostaDTO> pesquisarAmostrasRealizadasPorReferencia(Integer refAmostras);

	@Query(nativeQuery = true, value = "SELECT DT_INICIO AS referencia, 'Amostras exigidas' AS tipoArquivo, CAST(1 AS BIT) AS flagAmostraExigida, USUARIO AS usuario, DATA_INSERCAO AS dataInsercao FROM ATTAE "
			+ "UNION "
			+ "SELECT REF_AMOSTRAS AS referencia, 'Amostras realizadas' AS tipoArquivo, CAST(0 AS BIT) AS flagAmostraExigida, USUARIO AS usuario, DATA_INSERCAO AS dataInsercao FROM ATTAR")
	List<PesquisaAmostrasArquivosProjectionRespostaDTO> pesquisarAmostrasArquivos();

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM ATTAR WHERE REF_AMOSTRAS = :refAmostras")
	void removerPorRefAmostras(Integer refAmostras);

	

}

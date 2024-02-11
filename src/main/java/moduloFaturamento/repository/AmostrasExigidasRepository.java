package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.AmostrasExigidas;
import moduloFaturamento.model.IdAmostrasExigidas;

@Repository
public interface AmostrasExigidasRepository extends JpaRepository<AmostrasExigidas, IdAmostrasExigidas> {

	@Query(nativeQuery = true, value = "SELECT * FROM ATTAE WHERE DT_INICIO = :dtInicio")
	List<AmostrasExigidas> buscarAmostrasExigidasPorDtInicio(Integer dtInicio);

	@Query(nativeQuery = true, value = "SELECT COUNT(DT_INICIO) FROM ATTAE WHERE DT_INICIO = :dtInicio")
	Integer contarAmostrasExigidasPorDtInicio(Integer dtInicio);

	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM ATTAE WHERE DT_INICIO = :dtInicio")
	void removerPorDtInicio(Integer dtInicio);
}

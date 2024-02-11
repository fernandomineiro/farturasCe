package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.model.DiasVencimento;
import moduloFaturamento.model.IdDiasVencimento;

public interface FaturamentoDiasVencimentoRepository extends JpaRepository<DiasVencimento, IdDiasVencimento> {

	@Query(value = "SELECT DISTINCT(CICLO) FROM CDTCD", nativeQuery = true)
	List<Integer> listaCiclos();

	List<DiasVencimento> findByIdDiasVencimentoFaturamentoCicloIn(List<Short> ciclos);

}

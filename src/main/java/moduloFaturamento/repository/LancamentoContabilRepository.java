package moduloFaturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.LancamentoContabil;

@Repository
public interface LancamentoContabilRepository extends JpaRepository<LancamentoContabil, Long> {

    
}

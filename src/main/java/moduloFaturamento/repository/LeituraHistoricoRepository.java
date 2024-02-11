package moduloFaturamento.repository;

import moduloFaturamento.model.LeituraHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeituraHistoricoRepository extends JpaRepository<LeituraHistorico, Long>{

    Optional<LeituraHistorico> findById(Long id);
}

package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivoClienteHistoricoRepository extends JpaRepository<IncentivoClienteHistorico, Long> {
}

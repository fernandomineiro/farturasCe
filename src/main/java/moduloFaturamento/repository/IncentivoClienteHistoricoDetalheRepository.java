package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteHistoricoDetalhe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivoClienteHistoricoDetalheRepository extends JpaRepository<IncentivoClienteHistoricoDetalhe, Integer> {
}

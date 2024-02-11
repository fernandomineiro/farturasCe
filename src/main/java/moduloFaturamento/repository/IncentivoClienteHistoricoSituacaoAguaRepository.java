package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteHistoricoSituacaoAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivoClienteHistoricoSituacaoAguaRepository extends JpaRepository<IncentivoClienteHistoricoSituacaoAgua, Integer> {
}

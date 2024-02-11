package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteHistoricoSituacaoEsgoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivoClienteHistoricoSituacaoEsgotoRepository extends JpaRepository<IncentivoClienteHistoricoSituacaoEsgoto, Integer> {
}

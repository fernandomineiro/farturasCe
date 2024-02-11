package moduloFaturamento.repository;

import moduloFaturamento.model.IncentivoClienteHistoricoGrupoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncentivoClienteHistoricoGrupoConsumoRepository extends JpaRepository<IncentivoClienteHistoricoGrupoConsumo, Integer> {
}

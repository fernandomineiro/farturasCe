package moduloFaturamento.repository;

import moduloFaturamento.model.TipoFaseCronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaseCronogramaRepository extends JpaRepository<TipoFaseCronograma, Short> {
}

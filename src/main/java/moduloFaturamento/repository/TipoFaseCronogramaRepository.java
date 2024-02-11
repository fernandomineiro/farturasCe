package moduloFaturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.TipoFaseCronograma;

@Repository
public interface TipoFaseCronogramaRepository extends JpaRepository<TipoFaseCronograma, Short> {

    
}

package moduloFaturamento.repository;

import moduloFaturamento.model.TipoFeriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFeriadoRepository extends JpaRepository<TipoFeriado, Short> {
}

package moduloFaturamento.repository;

import moduloFaturamento.model.TipoUnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeFederativaRepository extends JpaRepository<TipoUnidadeFederativa, Integer> {
}

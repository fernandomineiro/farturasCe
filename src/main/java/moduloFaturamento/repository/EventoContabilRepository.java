package moduloFaturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.EventoContabil;

@Repository
public interface EventoContabilRepository extends JpaRepository<EventoContabil, Short> {

}

package moduloFaturamento.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.common.DossieCliente;

@Repository
public interface DossieClienteRepository extends JpaRepository<DossieCliente, Long> {
    
}

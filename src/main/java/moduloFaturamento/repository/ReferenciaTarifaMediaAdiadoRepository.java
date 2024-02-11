package moduloFaturamento.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.ReferenciaTarifaMediaAdiado;

@Repository
public interface ReferenciaTarifaMediaAdiadoRepository extends JpaRepository<ReferenciaTarifaMediaAdiado, Integer> {

    Optional<ReferenciaTarifaMediaAdiado> findByRefTarifaMediaAdiado(LocalDate refParaCalculoTarifaMedia);
    
}

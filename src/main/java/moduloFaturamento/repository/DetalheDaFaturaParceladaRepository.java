package moduloFaturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.DetalheDaFaturaParcelada;
import moduloFaturamento.model.IdDetalheDaFaturaParcelada;

@Repository
public interface DetalheDaFaturaParceladaRepository extends JpaRepository<DetalheDaFaturaParcelada, IdDetalheDaFaturaParcelada>{
    
}

package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.LancamentosDaFatura;

@Repository
public interface LancamentosDaFaturaRepository extends JpaRepository<LancamentosDaFatura, Long>{

    List<LancamentosDaFatura> findByIdFatura(Long idFatura);
    
}

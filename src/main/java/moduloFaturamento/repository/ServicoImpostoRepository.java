package moduloFaturamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.ServicoImposto;

@Repository
public interface ServicoImpostoRepository extends JpaRepository<ServicoImposto, Long>{

    @Query(nativeQuery = true, value = "SELECT PORCENTAGEM FROM FAT_IMPOSTO WHERE CD_SERVICO = :cdServico")
    Double buscarPorcentagemDoServico(short cdServico);
    
}

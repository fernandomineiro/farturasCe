package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.IdSolicitacaoServico;
import moduloFaturamento.model.common.SolicitacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, IdSolicitacaoServico>{

    Optional<SolicitacaoServico> findById(IdSolicitacaoServico idSolicitacaoServico);
}

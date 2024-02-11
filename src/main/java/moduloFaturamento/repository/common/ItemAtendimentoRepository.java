package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.IdItemAtendimento;
import moduloFaturamento.model.common.ItemAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemAtendimentoRepository extends JpaRepository<ItemAtendimento, IdItemAtendimento> {

    Optional<ItemAtendimento> findById(IdItemAtendimento idItemAtendimento);
}

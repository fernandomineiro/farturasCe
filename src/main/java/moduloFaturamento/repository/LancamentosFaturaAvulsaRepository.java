package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.IdLancamentoFaturaAvulsa;
import moduloFaturamento.model.LancamentosFaturaAvulsa;

@Repository
public interface LancamentosFaturaAvulsaRepository extends JpaRepository<LancamentosFaturaAvulsa, IdLancamentoFaturaAvulsa> {

    List<LancamentosFaturaAvulsa> findByIdLancamentoFaturaAvulsaCdClienteAndIdLancamentoFaturaAvulsaRefFaturaAndIdLancamentoFaturaAvulsaOrigemFatura(
            Integer cdCliente, Integer refFatura, Short origemFatura);
}

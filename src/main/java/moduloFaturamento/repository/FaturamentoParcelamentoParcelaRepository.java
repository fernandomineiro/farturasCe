package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.FaturamentoParcelamentoParcela;
import moduloFaturamento.model.IdFaturamentoParcelamentoParcela;

@Repository
public interface FaturamentoParcelamentoParcelaRepository extends JpaRepository<FaturamentoParcelamentoParcela, IdFaturamentoParcelamentoParcela>{

    @Query(nativeQuery = true, value = "select top 1 CD_PARCELAMENTO from FTTPD where CD_PARCELAMENTO = :cdParcelamento AND NUMERO_PARCELA = :numeroParcelamento")
    Integer buscarSeExisteUMRegistro(Integer cdParcelamento, Integer numeroParcelamento);

    List<FaturamentoParcelamentoParcela> findByIdFaturamentoParcelamentoParcelaCdParcelamento(Integer codigoParcelamento);
    
}

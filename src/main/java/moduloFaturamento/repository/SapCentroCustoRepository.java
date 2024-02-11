package moduloFaturamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.model.SapCentroCusto;

public interface SapCentroCustoRepository extends JpaRepository<SapCentroCusto, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM SAP_CENTRO_CUSTOS WHERE CD_CIDADE = :cdCidade AND NR_CONTA_CONTABIL_SAP = :numeroContaContabilSap")
    Optional<SapCentroCusto> buscarCdCustoSapPorNumeroContaContabil(Short cdCidade, Integer numeroContaContabilSap);


    
}

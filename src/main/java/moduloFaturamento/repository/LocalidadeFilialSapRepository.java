package moduloFaturamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.model.LocalidadeFilialSap;

public interface LocalidadeFilialSapRepository extends JpaRepository<LocalidadeFilialSap, Integer> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM CAD_LOCALIDADE_SICAT_FILIAL_SAP WHERE CD_CIDADE = :cdCidade")
    Optional<LocalidadeFilialSap> buscarCdFilialSap(Integer cdCidade);
}

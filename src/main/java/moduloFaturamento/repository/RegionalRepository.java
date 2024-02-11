package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaRegionalProjectionDTO;
import moduloFaturamento.model.common.Regional;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Integer> {

    @Query(nativeQuery = true, value = "SELECT CD_REGIONAL AS cdRegional, DC_REGIONAL AS dcRegional FROM CDARE")
    List<TarifaMediaRegionalProjectionDTO> buscarListaRegionais();

    @Query(nativeQuery = true, value = "SELECT CD_REGIONAL FROM CDARE")
    List<Short> buscaIdRegionais();
    
}

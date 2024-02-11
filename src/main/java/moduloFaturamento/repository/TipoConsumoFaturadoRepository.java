package moduloFaturamento.repository;

import moduloFaturamento.model.TipoConsumoFaturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoConsumoFaturadoRepository extends JpaRepository<TipoConsumoFaturado, Short> {

    @Query(value = "SELECT * FROM FAT_TP_CONSUMO_FATURADO WHERE ID IN (1,3,4,7,8)", nativeQuery = true)
    List<TipoConsumoFaturado> buscarListaConsumoFaturadoLeituraConsumoImovel();
}

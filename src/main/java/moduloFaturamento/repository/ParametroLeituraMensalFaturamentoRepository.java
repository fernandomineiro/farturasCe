package moduloFaturamento.repository;

import moduloFaturamento.model.ParametroLeituraMensalFaturamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroLeituraMensalFaturamentoRepository extends JpaRepository<ParametroLeituraMensalFaturamento, Integer> {

    @Query(value = "SELECT TOP 1 * FROM FAT_PARAMETROS", nativeQuery = true)
    ParametroLeituraMensalFaturamento buscarParametro();

    @Query(value = "SELECT parametro FROM ParametroLeituraMensalFaturamento parametro WHERE parametro.id = :id")
    ParametroLeituraMensalFaturamento buscarParametroExistente(Integer id);
}

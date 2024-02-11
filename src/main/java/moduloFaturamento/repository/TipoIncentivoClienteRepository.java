package moduloFaturamento.repository;

import moduloFaturamento.model.TipoIncentivoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoIncentivoClienteRepository extends JpaRepository<TipoIncentivoCliente, Integer> {

    @Query(value = "SELECT tipo.id FROM TipoIncentivoCliente tipo ")
    List<Integer> buscarIdTodosIncentivoCLiente();

    @Query(value = "SELECT tipo FROM TipoIncentivoCliente tipo WHERE (:id is null or tipo.id = :id)")
    TipoIncentivoCliente buscarTipoExistentePorId(Integer id);
}

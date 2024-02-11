package moduloFaturamento.repository;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.model.IncentivoClienteGrupoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncentivoClienteGrupoConsumoRepository extends JpaRepository<IncentivoClienteGrupoConsumo, Integer> {


    @Query(value = "SELECT new moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO(cadGC.id, cadGC.descricao) " +
            "FROM IncentivoClienteGrupoConsumo incentivoGC " +
            "INNER JOIN CadGrupoConsumo cadGC ON incentivoGC.id.cadGrupoConsumo.id = cadGC.id " +
            "WHERE incentivoGC.id.incentivoClienteDetalhe.id = :idParametroDetalhe")
    List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> buscarGrupoConsumoDoParametroIncentivoCliente(Integer idParametroDetalhe);

    @Query(value = "SELECT ID FROM FAT_INCENTIVO_GRUPO_CONSUMO WHERE CD_PARAMETRO_DETALHE = :idParametroDetalhe AND ID_GRUPO_CONSUMO = :idCadGrupoConsumo", nativeQuery = true)
    Long buscarIdIncentivoClienteGrupoConsumoPorChaveComposta(Integer idParametroDetalhe, Integer idCadGrupoConsumo);

    Optional<List<IncentivoClienteGrupoConsumo>> findById_IncentivoClienteDetalhe_Id(Integer idParametroDetalhe);

}

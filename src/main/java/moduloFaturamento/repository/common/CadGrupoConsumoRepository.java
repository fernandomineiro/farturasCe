package moduloFaturamento.repository.common;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.model.common.CadGrupoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadGrupoConsumoRepository extends JpaRepository<CadGrupoConsumo, Integer> {

    @Query(value = "SELECT new moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO(gc.id, gc.descricao)  " +
            " FROM CadGrupoConsumo gc ORDER BY gc.descricao ASC")
    List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarGrupoDeConsumo();
}

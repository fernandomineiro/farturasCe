package moduloFaturamento.dto.tipoConsumoFaturado.mapper;

import moduloFaturamento.dto.tipoConsumoFaturado.TipoConsumoFaturadoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoConsumoFaturado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoConsumoFaturadoMapper extends EntityMapper <TipoConsumoFaturadoDTO, TipoConsumoFaturado>{
}

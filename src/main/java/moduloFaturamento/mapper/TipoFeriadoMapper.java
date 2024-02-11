package moduloFaturamento.mapper;

import moduloFaturamento.dto.TipoFeriadoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoFeriado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoFeriadoMapper extends EntityMapper<TipoFeriadoDTO, TipoFeriado>{
}

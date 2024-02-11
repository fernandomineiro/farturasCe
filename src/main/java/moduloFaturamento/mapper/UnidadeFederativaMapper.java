package moduloFaturamento.mapper;

import moduloFaturamento.dto.UnidadeFederativaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoUnidadeFederativa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadeFederativaMapper extends  EntityMapper<UnidadeFederativaDTO, TipoUnidadeFederativa> {
}

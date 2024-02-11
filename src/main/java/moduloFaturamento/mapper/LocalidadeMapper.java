package moduloFaturamento.mapper;

import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.Localidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalidadeMapper extends EntityMapper<LocalidadeDTO, Localidade> {
}

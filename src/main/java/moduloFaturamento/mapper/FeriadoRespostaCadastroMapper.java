package moduloFaturamento.mapper;

import moduloFaturamento.dto.feriado.FeriadoRespostaCadastrarDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.Feriado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeriadoRespostaCadastroMapper extends EntityMapper<FeriadoRespostaCadastrarDTO, Feriado>{
}

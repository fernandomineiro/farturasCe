package moduloFaturamento.dto.parametroLeituraMensalFaturamento.mapper;

import moduloFaturamento.dto.parametroLeituraMensalFaturamento.ParametroLeituraMensalFaturamentoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.ParametroLeituraMensalFaturamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface ParametroLeituraMensalFaturamentoMapper extends EntityMapper<ParametroLeituraMensalFaturamentoDTO, ParametroLeituraMensalFaturamento> {

    @Mapping(source = "tipoGeracaoParametros.id", target = "tipoGeracao")
    ParametroLeituraMensalFaturamentoDTO toDto(ParametroLeituraMensalFaturamento entity);
}

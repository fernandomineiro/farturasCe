package moduloFaturamento.dto.incentivoCliente.mapper;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteCadastroDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.IncentivoCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteCadastroMapper extends EntityMapper<IncentivoClienteCadastroDTO, IncentivoCliente> {

    @Mapping(target = "tipoIncentivoCliente.id", source = "tipoIncentivo")
    IncentivoCliente toEntity(IncentivoClienteCadastroDTO dto);

}

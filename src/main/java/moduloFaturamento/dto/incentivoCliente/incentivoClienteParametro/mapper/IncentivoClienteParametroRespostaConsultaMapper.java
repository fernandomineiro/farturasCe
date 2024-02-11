package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.IncentivoCliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface IncentivoClienteParametroRespostaConsultaMapper extends ProjectionDtoMapper<IncentivoClienteParametroRespostaConsultaDTO, IncentivoCliente> {

    @Mapping(target = "idTipoIncentivo", source = "tipoIncentivoCliente.id")
    IncentivoClienteParametroRespostaConsultaDTO toDto(IncentivoCliente incentivoCliente);
}

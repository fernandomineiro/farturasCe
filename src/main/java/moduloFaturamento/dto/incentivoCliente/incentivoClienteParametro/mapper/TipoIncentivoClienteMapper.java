package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoIncentivoCliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface TipoIncentivoClienteMapper extends EntityMapper<IncentivoClienteParametroTipoDTO, TipoIncentivoCliente> {
}

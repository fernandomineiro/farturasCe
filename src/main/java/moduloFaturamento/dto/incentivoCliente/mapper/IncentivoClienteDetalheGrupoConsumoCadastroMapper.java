package moduloFaturamento.dto.incentivoCliente.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.IncentivoClienteDetalheGrupoConsumoCadastroDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.IncentivoClienteGrupoConsumo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteDetalheGrupoConsumoCadastroMapper extends EntityMapper<IncentivoClienteDetalheGrupoConsumoCadastroDTO, IncentivoClienteGrupoConsumo> {

}

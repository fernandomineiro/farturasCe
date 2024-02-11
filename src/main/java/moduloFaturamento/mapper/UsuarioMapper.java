package moduloFaturamento.mapper;

import org.mapstruct.Mapper;

import moduloFaturamento.dto.UsuarioDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
    

}

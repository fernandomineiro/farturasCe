package moduloFaturamento.mapper;

import moduloFaturamento.dto.feriado.FeriadoCadastrarDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.Feriado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FeriadoCadastroMapper extends EntityMapper<FeriadoCadastrarDTO, Feriado> {

    @Mappings({
            @Mapping(source = "nomeFeriado", target = "dcFeriado"),
            @Mapping(source = "idTipoFeriado", target = "tpFeriado.id"),
            @Mapping(source = "idLocalidade", target = "idFeriado.cdCidade"),
            @Mapping(source = "idUnidadeFederativa", target = "unidadeFederativa.id")
    })
    Feriado toEntity(FeriadoCadastrarDTO feriadoCadastrarDTO);
}

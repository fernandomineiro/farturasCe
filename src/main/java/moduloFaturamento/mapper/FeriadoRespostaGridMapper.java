package moduloFaturamento.mapper;

import moduloFaturamento.dto.feriado.FeriadoRespostaGridDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.Feriado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeriadoRespostaGridMapper extends EntityMapper<FeriadoRespostaGridDTO, Feriado> {

    /*@Mappings({
            @Mapping(source = "idFeriado.cdCidade", target = "cdCidade"),
            @Mapping(source = "idFeriado.dtFeriado", target = "dtFeriado"),
            @Mapping(source = "tpFeriado.id", target = "idTipoFeriado"),
            @Mapping(source = "unidadeFederativa.id", target = "idUnidadeFederativa")
    })
    List<FeriadoRespostaGridDTO> toDto(List<Feriado> feriadoList);*/
}

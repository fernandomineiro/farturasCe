package moduloFaturamento.mapper;

import moduloFaturamento.dto.feriado.FeriadoRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.Feriado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FeriadoRespostaConsultaMapper extends EntityMapper<FeriadoRespostaConsultaDTO, Feriado> {
    @Mappings({
        @Mapping(source = "idFeriado.cdCidade", target = "cdCidade"),
        @Mapping(source = "idFeriado.dtFeriado", target = "dtFeriado"),
        @Mapping(source = "tpFeriado.id", target = "idTipoFeriado"),
        @Mapping(source = "unidadeFederativa.id", target = "idUnidadeFederativa")
    })
    FeriadoRespostaConsultaDTO toDto(Feriado feriado);
}

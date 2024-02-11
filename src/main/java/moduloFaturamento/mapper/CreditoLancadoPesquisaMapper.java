package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import moduloFaturamento.dto.credito.CreditoLancadoDTO;
import moduloFaturamento.dto.credito.projection.CreditoLancadoProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CreditoLancadoPesquisaMapper extends ProjectionDtoMapper<CreditoLancadoDTO, CreditoLancadoProjectionDTO> {

    @Mapping(source = "data", target = "data", qualifiedByName = "integerParaLocalDate")
    CreditoLancadoDTO toDto(CreditoLancadoProjectionDTO projection);

    @Named(value = "integerParaLocalDate")
    default LocalDate integerParaLocalDate(Integer dataEmInteger){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(dataEmInteger);
    }
    
}

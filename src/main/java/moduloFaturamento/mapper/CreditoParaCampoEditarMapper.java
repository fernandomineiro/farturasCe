package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.credito.CreditoParaEditarRespostaDTO;
import moduloFaturamento.dto.credito.projection.CreditoCampoEditarProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CreditoParaCampoEditarMapper extends ProjectionDtoMapper<CreditoParaEditarRespostaDTO, CreditoCampoEditarProjectionDTO> {

    @Mappings({  @Mapping(source = "encerramento", target = "encerramento", qualifiedByName = "integerParaLocalDate"),
                @Mapping(source = "referenciaFatura", target = "referenciaFatura", qualifiedByName = "integerParaLocalDate2") } )
    CreditoParaEditarRespostaDTO toDto(CreditoCampoEditarProjectionDTO projection);

    @Named(value = "integerParaLocalDate")
    default LocalDate integerParaLocalDate(Integer encerramento){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(encerramento);
    }

    @Named(value = "integerParaLocalDate2")
    default LocalDate integerParaLocalDate2(Integer encerramento){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(encerramento);
    }
    
}

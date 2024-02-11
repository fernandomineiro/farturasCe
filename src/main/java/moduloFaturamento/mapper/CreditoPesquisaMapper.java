package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.credito.CreditoPesquisaDTO;
import moduloFaturamento.dto.credito.projection.CreditoParaPesqBancoProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CreditoPesquisaMapper extends ProjectionDtoMapper<CreditoPesquisaDTO, CreditoParaPesqBancoProjectionDTO> {

    @Mappings( { @Mapping(source = "encerramento", target = "refEncerramento", qualifiedByName = "integerParaLocalDate"), 
                @Mapping(source = "refFatura", target = "referenciaFatura", qualifiedByName = "integerParaLocalDate") } )  
    CreditoPesquisaDTO toDto(CreditoParaPesqBancoProjectionDTO projection);

    @Named(value = "integerParaLocalDate")
    default LocalDate integerParaLocalDate(Integer encerramento){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(encerramento);
    }   
    
}

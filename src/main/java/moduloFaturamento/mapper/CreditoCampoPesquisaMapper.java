package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.credito.CreditoCampoLancadoPesquisaDTO;
import moduloFaturamento.dto.credito.projection.CreditoCampoProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CreditoCampoPesquisaMapper extends ProjectionDtoMapper<CreditoCampoLancadoPesquisaDTO, CreditoCampoProjectionDTO> {

    @Mappings( { @Mapping(source = "encerramento", target = "refEncerramento", qualifiedByName = "integerParaLocalDate"), 
                @Mapping(source = "cdCredito", target = "cdCredito"), 
                @Mapping(source = "refFatura", target = "referenciaFatura", qualifiedByName = "integerParaLocalDate") } )  
    CreditoCampoLancadoPesquisaDTO toDto(CreditoCampoProjectionDTO projection);

    @Named(value = "integerParaLocalDate")
    default LocalDate integerParaLocalDate(Integer dataEmInteger){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(dataEmInteger);
    }
    
    
}

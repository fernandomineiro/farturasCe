package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCampoLancamentoDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaExibirDadosProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface FaturaAvulsaCampoLancamentoMapper extends ProjectionDtoMapper<FaturaAvulsaCampoLancamentoDTO, FaturaAvulsaExibirDadosProjectionDTO> {

    @Mapping(source = "refFatura", target = "refFatura", qualifiedByName = "integerParaLocalDate")
    @Mapping(source = "dataVencimento", target = "dataVencimento", qualifiedByName = "integerParaLocalDateCompleto")
    @Mapping(source = "dataBaixaCompleto", target = "dataBaixaCompleto", qualifiedByName = "integerParaLocalDateString") 
    FaturaAvulsaCampoLancamentoDTO toDto(FaturaAvulsaExibirDadosProjectionDTO projection);

    @Named(value = "integerParaLocalDate")
    default LocalDate integerParaLocalDate(Integer dataEmInteger){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(dataEmInteger);
    }

    @Named(value = "integerParaLocalDateCompleto")
    default LocalDate integerParaLocalDateCompleto(Integer dataEmInteger){
        return ConverterData.integerEmLocalDateDataFormatoDB(dataEmInteger);
    }

    @Named(value = "integerParaLocalDateString")
    default LocalDate integerParaLocalDateString(String dataEmString){

        String[] tirandoEspacoCasoVenhaZero = dataEmString.split(" ");

        Integer dataEmInteger = Integer.parseInt(tirandoEspacoCasoVenhaZero[tirandoEspacoCasoVenhaZero.length - 1]);
        return ConverterData.integerEmLocalDateDataFormatoDB(dataEmInteger);
    }


}

package moduloFaturamento.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.cadastramentoFatura.FaturaGridProjectionDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridRespostaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CadastramentoFaturaListaGridProjectionDtoMapper
		extends ProjectionDtoMapper<FaturaGridRespostaDTO, FaturaGridProjectionDTO> {

	@Mappings({ @Mapping(source = "refFatura", target = "refFatura", qualifiedByName = "integerReferenciaLocalDate"),
			@Mapping(source = "dataVencimento", target = "dataVencimento", qualifiedByName = "integerDataLocalDate"),
			@Mapping(source = "dataPagamento", target = "dataPagamento", qualifiedByName = "integerDataLocalDate") })
	FaturaGridRespostaDTO toDto(FaturaGridProjectionDTO projection);

	@Named(value = "integerReferenciaLocalDate")
	default LocalDate integerReferenciaLocalDate(Integer dataEmInteger) {

		return ConverterData.integerEmLocalDateReferenciaFormatoDB(dataEmInteger);
	}

	@Named(value = "integerDataLocalDate")
	default LocalDate integerDataLocalDate(Integer dataEmInteger) {

		return ConverterData.integerEmLocalDateDataFormatoBR(dataEmInteger);
	}
}

package moduloFaturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloFaturamento.dto.faturaImovel.FaturaImovelDetalhadaDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelCabecalhoProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;

@Mapper(componentModel = "spring")
public interface FaturaImovelDetalhadaCabecalhoMapper extends ProjectionDtoMapper<FaturaImovelDetalhadaDTO, FaturaImovelCabecalhoProjectionDTO> {

	@Mappings({ 
			@Mapping(source = "referencia", target = "referencia", qualifiedByName = "converterIntegerLocalDateReferencia"),
			@Mapping(source = "dataLeitura", target = "dataLeitura", qualifiedByName = "converterIntegerLocalDateData"),
			@Mapping(source = "dataVencimento", target = "dataVencimento", qualifiedByName = "converterIntegerLocalDateData"),
			@Mapping(source = "dataTarifa", target = "dataTarifa", qualifiedByName = "converterIntegerLocalDateData"),
			@Mapping(source = "dataBaixa", target = "dataBaixa", qualifiedByName = "converterIntegerLocalDateData"),
			@Mapping(source = "dataArrecadacao", target = "dataArrecadacao", qualifiedByName = "converterLocalDateTimeParaLocalDate")
		})
	FaturaImovelDetalhadaDTO toDto(FaturaImovelCabecalhoProjectionDTO projection);
}

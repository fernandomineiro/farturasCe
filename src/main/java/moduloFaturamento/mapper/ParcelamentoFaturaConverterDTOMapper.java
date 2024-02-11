package moduloFaturamento.mapper;

import org.mapstruct.Mapper;

import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaContasEmAbertoDoClienteDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;

@Mapper(componentModel = "spring")
public interface ParcelamentoFaturaConverterDTOMapper extends ProjectionDtoMapper<ParcelamentoFaturaSimulacaoDTO, ParcelamentoFaturaContasEmAbertoDoClienteDTO> {

    ParcelamentoFaturaSimulacaoDTO toDto(ParcelamentoFaturaContasEmAbertoDoClienteDTO projection);
}

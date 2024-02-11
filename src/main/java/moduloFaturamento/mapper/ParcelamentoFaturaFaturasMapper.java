package moduloFaturamento.mapper;

import org.mapstruct.Mapper;

import moduloFaturamento.dto.credito.projection.CreditoParaPesqBancoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaFaturaEmAbertoDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;

@Mapper(componentModel = "spring")
public interface ParcelamentoFaturaFaturasMapper extends ProjectionDtoMapper<ParcelamentoFaturaListaFaturaEmAbertoDTO, ParcelamentoFaturaListaFaturaEmAbertoParaPesquisaProjectionDTO> {

    ParcelamentoFaturaListaFaturaEmAbertoDTO toDto(CreditoParaPesqBancoProjectionDTO projection);


    
}

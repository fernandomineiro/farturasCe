package moduloFaturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;

@Mapper(componentModel = "spring")
public interface ParcelamentoFaturaCalculoParcelamentoMapper extends ProjectionDtoMapper<ParcelamentoFaturaSelecionarFaturaDTO, 
    ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO> {

    @Mapping(source = "incentivoJuridico", target = "incentivoJuridico")
    ParcelamentoFaturaSelecionarFaturaDTO toDto(ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO dto);
}

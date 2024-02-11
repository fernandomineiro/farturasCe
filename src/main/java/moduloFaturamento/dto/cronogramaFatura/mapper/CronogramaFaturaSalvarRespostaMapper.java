package moduloFaturamento.dto.cronogramaFatura.mapper;

import org.mapstruct.Mapper;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaSalvarRespostaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.CronogramaFatura;

@Mapper(componentModel = "spring")
public interface CronogramaFaturaSalvarRespostaMapper extends ProjectionDtoMapper<CronogramaFaturaSalvarRespostaDTO, CronogramaFatura> {
}

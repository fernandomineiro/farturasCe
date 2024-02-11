package moduloFaturamento.dto.cronogramaFatura.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaCicloProjectionDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;

@Mapper(componentModel = "spring")
public interface CronogramaFaturaCicloProjectionMapper extends ProjectionDtoMapper<CronogramaFaturaCicloDropDownDTO, CronogramaFaturaCicloProjectionDTO> {

	@Override
	@Mapping(source = "cicloFechado", target = "cicloFechado", qualifiedByName = "cicloFechadoStringToBoolean")
	CronogramaFaturaCicloDropDownDTO toDto(CronogramaFaturaCicloProjectionDTO entity);

	@Named(value = "cicloFechadoStringToBoolean")
	default boolean cicloFechadoStringToBoolean(String cicloFechado) {

		return "S".equals(cicloFechado);
	}
}

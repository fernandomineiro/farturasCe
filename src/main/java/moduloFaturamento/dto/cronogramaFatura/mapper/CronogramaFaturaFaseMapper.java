package moduloFaturamento.dto.cronogramaFatura.mapper;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaFaseDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoFaseCronograma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CronogramaFaturaFaseMapper extends EntityMapper<CronogramaFaturaFaseDTO, TipoFaseCronograma> {
}

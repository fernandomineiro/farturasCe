package moduloFaturamento.dto.cobrancaFatura.mapper;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaRespostaSalvarDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.CobrancaServicoFatura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobrancaFaturaRespostaSalvarMapper extends ProjectionDtoMapper<CobrancaFaturaRespostaSalvarDTO, CobrancaServicoFatura> {
}

package moduloFaturamento.mapper;

import moduloFaturamento.dto.ocorrenciaLeitura.TipoServicoOcorrenciaLeituraDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.TipoServicoOcorrenciaLeitura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoServicoOcorrenciaLeituraMapper extends EntityMapper<TipoServicoOcorrenciaLeituraDTO, TipoServicoOcorrenciaLeitura> {
}

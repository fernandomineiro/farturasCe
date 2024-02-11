package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaSituacaoEsgotoDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteParametroRespostaSituacaoEsgotoMapper extends ProjectionDtoMapper<IncentivoClienteParametroRespostaSituacaoEsgotoDTO, SituacaoLigacaoEsgoto> {

}

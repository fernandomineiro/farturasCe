package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaSituacaoAguaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteParametroRespostaSituacaoAguaMapper extends ProjectionDtoMapper<IncentivoClienteParametroRespostaSituacaoAguaDTO, SituacaoLigacaoAgua> {

}

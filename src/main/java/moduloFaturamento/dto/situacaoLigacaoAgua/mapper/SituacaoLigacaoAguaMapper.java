package moduloFaturamento.dto.situacaoLigacaoAgua.mapper;

import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface SituacaoLigacaoAguaMapper extends EntityMapper<SituacaoLigacaoAguaDTO, SituacaoLigacaoAgua> {
}

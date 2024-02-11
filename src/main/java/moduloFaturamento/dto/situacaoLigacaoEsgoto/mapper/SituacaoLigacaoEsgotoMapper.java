package moduloFaturamento.dto.situacaoLigacaoEsgoto.mapper;

import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface SituacaoLigacaoEsgotoMapper extends EntityMapper<SituacaoLigacaoEsgotoDTO, SituacaoLigacaoEsgoto> {

}

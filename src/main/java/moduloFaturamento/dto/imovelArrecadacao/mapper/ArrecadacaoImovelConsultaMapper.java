package moduloFaturamento.dto.imovelArrecadacao.mapper;

import moduloFaturamento.dto.imovelArrecadacao.ArrecadacaoImovelDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.ArrecadacaoImovel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArrecadacaoImovelConsultaMapper extends EntityMapper<ArrecadacaoImovelDTO, ArrecadacaoImovel> {
}

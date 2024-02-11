package moduloFaturamento.dto.leituraConsumoImovel.mapper;

import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelTipoMotivoEdicaoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.TipoMotivoEdicaoLeituraConsumo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeituraConsumoImovelTipoMotivoEdicaoMapper extends EntityMapper<LeituraConsumoImovelTipoMotivoEdicaoDTO, TipoMotivoEdicaoLeituraConsumo> {
}

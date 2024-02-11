package moduloFaturamento.dto.cobrancaFatura.mapper;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaAtualizarDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.CobrancaServicoFatura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CobrancaFaturaAtualizarMapper extends EntityMapper<CobrancaFaturaAtualizarDTO, CobrancaServicoFatura> {
}

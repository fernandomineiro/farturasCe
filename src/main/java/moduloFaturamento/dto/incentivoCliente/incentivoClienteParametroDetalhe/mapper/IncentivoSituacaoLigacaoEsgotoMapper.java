package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoSituacaoLigacaoEsgotoMapper extends EntityMapper<IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO, SituacaoLigacaoEsgoto> {


    @Mapping(target = "nome", source = "descricao")
    IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO toDto(SituacaoLigacaoAgua entity);
}

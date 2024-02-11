package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper;

import moduloFaturamento.dto.incentivoCliente.IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO;
import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoSituacaoLigacaoAguaMapper extends EntityMapper<IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO, SituacaoLigacaoAgua> {


    @Mapping(target = "nome", source = "descricao")
    IncentivoClienteGrupoRespostaIdNomeIdentidadeDTO toDto(SituacaoLigacaoAgua entity);
}

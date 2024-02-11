package moduloFaturamento.dto.incentivoCliente.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheCadastroDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteDetalheCadastroMapper extends EntityMapper<IncentivoClienteParametroDetalheCadastroDTO, IncentivoClienteDetalhe> {


    @Mapping(source = "correcaoMonetaria", target = "indicativoCorrecaoMonetaria", qualifiedByName = "converterBooleanParaBinario")
    IncentivoClienteDetalhe toEntity(IncentivoClienteParametroDetalheCadastroDTO dto);
}

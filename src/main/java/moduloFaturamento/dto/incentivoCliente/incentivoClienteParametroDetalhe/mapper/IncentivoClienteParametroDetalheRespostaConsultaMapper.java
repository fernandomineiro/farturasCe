package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface IncentivoClienteParametroDetalheRespostaConsultaMapper extends ProjectionDtoMapper<IncentivoClienteParametroDetalheRespostaConsultaDTO, IncentivoClienteDetalhe> {

    @Mapping(target = "possuiCorrecaoMonetaria", source = "indicativoCorrecaoMonetaria", qualifiedByName = "converterNumeroBinarioParaBoolean")
    IncentivoClienteParametroDetalheRespostaConsultaDTO toDto(IncentivoClienteDetalhe incentivoClienteDetalhe);

    @Named(value = "converterNumeroBinarioParaBoolean")
    default boolean converterNumeroBinarioParaBoolean(Short numero) {
        return numero == 1;
    }
}

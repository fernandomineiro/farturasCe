package moduloFaturamento.mapper;

import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraCadastrarDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.OcorrenciaLeitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface OcorrenciaLeituraCadastroMapper extends EntityMapper<OcorrenciaLeituraCadastrarDTO, OcorrenciaLeitura> {
    @Mappings({
            @Mapping(source = "codigo", target = "cdOcorrencia"),
            @Mapping(source = "descricao", target = "dcOcorrencia"),
            @Mapping(source = "idTipoOcorrencia", target = "tipoOcorrencia.id"),
            @Mapping(source = "leituraVirtual", target = "leituraVirtual", qualifiedByName = "converterLeituraVirtualBooleanParaString")
    })
    OcorrenciaLeitura toEntity(OcorrenciaLeituraCadastrarDTO ocorrenciaLeituraCadastrarDTO);

    @Named(value = "converterLeituraVirtualBooleanParaString")
    default String converterLeituraVirtualBooleanParaString(Boolean leituraVirtual){
        leituraVirtual = Objects.requireNonNullElse(leituraVirtual, false);
        return (leituraVirtual?"S":"N");
    }
}

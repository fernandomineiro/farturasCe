package moduloFaturamento.mapper;

import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.OcorrenciaLeitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OcorrenciaLeituraRespostaConsultaMapper extends EntityMapper<OcorrenciaLeituraRespostaConsultaDTO, OcorrenciaLeitura> {

    @Mappings({
            @Mapping(source = "cdOcorrencia", target = "codigo"),
            @Mapping(source = "tipoOcorrencia.id", target = "idTipoOcorrencia"),
            @Mapping(source = "dcOcorrencia", target = "descricao"),
            @Mapping(source = "leituraVirtual", target = "leituraVirtual", qualifiedByName = "converterParaBooleanLeituraVirtual")
    })
    OcorrenciaLeituraRespostaConsultaDTO toDto(OcorrenciaLeitura ocorrenciaLeitura);

    @Named(value = "converterParaBooleanLeituraVirtual")
    default Boolean converterParaBooleanLeituraVirtual(String leituraVirtual){
        return (leituraVirtual.equals("S"));
    }
}

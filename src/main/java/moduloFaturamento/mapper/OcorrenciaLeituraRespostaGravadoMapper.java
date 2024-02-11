package moduloFaturamento.mapper;

import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraGravadoRespostaIdentidadeDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.OcorrenciaLeitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OcorrenciaLeituraRespostaGravadoMapper extends EntityMapper<OcorrenciaLeituraGravadoRespostaIdentidadeDTO, OcorrenciaLeitura> {

    @Mapping(source = "cdOcorrencia", target = "codigo")
    OcorrenciaLeituraGravadoRespostaIdentidadeDTO toDto(OcorrenciaLeitura ocorrenciaLeitura);

}

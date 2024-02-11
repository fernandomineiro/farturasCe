package moduloFaturamento.dto.leituraConsumoImovel.mapper;

import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.Leitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LeituraConsumoImovelRespostaConsultaMapper extends ProjectionDtoMapper<LeituraConsumoImovelRespostaConsultaDTO, Leitura> {

    @Mappings({
            @Mapping(source = "idLeituraFaturamento.refFatura", target = "refFatura"),
            @Mapping(source = "ocorrencia", target = "ocorrenciaCodigo"),
            @Mapping(source = "ocorrencia2", target = "ocorrencia2Codigo"),
            @Mapping(source = "ocorrencia3", target = "ocorrencia3Codigo"),
            @Mapping(source = "consumoFaturarAgua", target = "consumoFaturarAgua"),
            @Mapping(source = "consumoFaturarEsgoto", target = "consumoFaturarEsgoto"),
            @Mapping(source = "leituraCriada", target = "leituraCriada", qualifiedByName = "converterFlagStringParaBoolean"),
            @Mapping(source = "excluirCalculoMedia", target = "excluirCalculoMedia", qualifiedByName = "converterFlagStringParaBoolean"),
            @Mapping(source = "tipoConsumoFaturado.id", target = "idTipoConsumoFaturado"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.codAtendimento", target = "ssCdAtendimento"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.refAtendimento", target = "ssRefAtendimento"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.seqSS", target = "seqSS"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.codAtendimento", target = "itemCdAtendimento"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.refAtendimento", target = "itemRefAtendimento"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.seqAtendimento", target = "itemSeq"),
            @Mapping(source = "consumoMedidoMicroModificado", target = "consumoMedidoMicroModificado", qualifiedByName = "converterFlagStringParaBoolean"),
            @Mapping(source = "dataDeleitura", target = "dataDeleitura", qualifiedByName = "converterIntegerLocalDateData"),
    })
    LeituraConsumoImovelRespostaConsultaDTO toDto(Leitura leitura);

    @Named(value = "converterFlagStringParaBoolean")
    default boolean converterFlagStringParaBoolean(String flagString) {
        return "S".equalsIgnoreCase(flagString);
    }


}

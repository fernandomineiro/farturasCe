package moduloFaturamento.dto.cobrancaFatura.mapper;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.CobrancaServicoFatura;
import moduloFaturamento.util.ConverterData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface CobrancaFaturaRespostaConsultaMapper extends ProjectionDtoMapper<CobrancaFaturaRespostaConsultaDTO, CobrancaServicoFatura> {


    @Mappings({
            @Mapping(source = "idCobrancaServicoFatura.matriculaImovel", target = "matriculaImovel"),
            @Mapping(source = "servico.cdServico", target = "codigoServico"),
            @Mapping(source = "dataExecucao", target = "dtExecucao", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "refFaturar", target = "referenciaFaturar", qualifiedByName = "converterDataReferenciaNumeroEmLocalDate"),
            @Mapping(source = "refFatura", target = "referenciaFaturada", qualifiedByName = "converterDataReferenciaNumeroEmLocalDate"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.codAtendimento", target = "ssCdAtendimento"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.refAtendimento", target = "ssRefAtendimento"),
            @Mapping(source = "solicitacaoServico.idSolicitacaoServico.seqSS", target = "seqSS"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.codAtendimento", target = "itemCdAtendimento"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.refAtendimento", target = "itemRefAtendimento"),
            @Mapping(source = "itemAtendimento.idItemAtendimento.seqAtendimento", target = "itemSeq"),
    })
    CobrancaFaturaRespostaConsultaDTO toDto(CobrancaServicoFatura cobrancaServicoFatura);

    @Named(value = "converterDataNumeroEmLocalDate")
    default LocalDate converterDataNumeroEmLocalDate(Integer data){
        return ConverterData.integerEmLocalDateDataFormatoDB(data);
    }

    @Named(value = "converterDataReferenciaNumeroEmLocalDate")
    default LocalDate converterDataRefenciaNumeroEmLocalDate(Integer data){
        return ConverterData.integerEmLocalDateReferenciaFormatoDB(data);
    }
}

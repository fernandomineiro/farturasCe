package moduloFaturamento.dto.cronogramaFatura.mapper;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaConsultaDTO;
import moduloFaturamento.mapper.definition.ProjectionDtoMapper;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.util.ConverterData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface CronogramaFaturaRespostaConsultaProjectionMapper extends ProjectionDtoMapper<CronogramaFaturaRespostaConsultaDTO, CronogramaFatura> {

    @Mappings({
            @Mapping(source = "idCronogramaFatura.cdCidade", target = "cdCidade"),
            @Mapping(source = "idCronogramaFatura.refCronograma", target = "dataReferencia", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "idCronogramaFatura.ciclo", target = "ciclo"),
            @Mapping(source = "dataTarifa", target = "dataAprovacaoTarifa", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataVencimento", target = "dataVencimento", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataGeraArquivoPrevista", target = "dataGeraArquivoPrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataGeraArquivoRealizada", target = "dataGeraArquivoRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataLeituraPrevista", target = "dataLeituraPrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataLeituraRealizada", target = "dataLeituraRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataFaturamentoPrevista", target = "dataFaturamentoPrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataFaturamentoRealizada", target = "dataFaturamentoRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataConsolidaPrevista", target = "dataConsolidaPrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataConsolidaRealizada", target = "dataConsolidaRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataEmissaoPrevista", target = "dataEmissaoPrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataEmissaoRealizada", target = "dataEmissaoRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataCortePrevista", target = "dataCortePrevista", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "dataCorteRealizada", target = "dataCorteRealizada", qualifiedByName = "converterDataNumeroEmLocalDate"),
            @Mapping(source = "fase.id", target = "numeroFase"),
            @Mapping(source = "fase.descricao", target = "nomeFase")
    })
    CronogramaFaturaRespostaConsultaDTO toDto(CronogramaFatura cronogramaFatura);

    @Named(value = "converterDataNumeroEmLocalDate")
    default LocalDate converterDataNumeroEmLocalDate(Integer data){
        return ConverterData.integerEmLocalDateFormatoDB(data);
    }
}

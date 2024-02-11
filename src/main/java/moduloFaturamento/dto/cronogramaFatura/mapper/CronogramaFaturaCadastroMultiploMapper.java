package moduloFaturamento.dto.cronogramaFatura.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCadastroMultiploDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CronogramaFaturaCadastroMultiploMapper extends EntityMapper<CronogramaFaturaCadastroMultiploDTO, CronogramaFatura> {

    @Mappings({
            @Mapping(source = "dataReferencia", target = "idCronogramaFatura.refCronograma", qualifiedByName = "converterDataLocalDateEmNumeroAnoMes"),
            @Mapping(source = "ciclo", target = "idCronogramaFatura.ciclo"),
            @Mapping(source = "aprovacaoTarifa", target = "dataTarifa", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "vencimentoFatura", target = "dataVencimento", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "geraArquivoPrevista", target = "dataGeraArquivoPrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "leituraPrevista", target = "dataLeituraPrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "faturamentoPrevista", target = "dataFaturamentoPrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "consolidaPrevista", target = "dataConsolidaPrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "emissaoPrevista", target = "dataEmissaoPrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "cortePrevista", target = "dataCortePrevista", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia")
    })
    CronogramaFatura toEntity(CronogramaFaturaCadastroMultiploDTO dto);

    @Named(value = "converterDataLocalDateEmNumeroAnoMes")
    default Integer converterDataLocalDateEmNumeroAnoMes(LocalDate data){
        return ConverterData.localDateEmIntegerReferenciaFormatoDB(data);
    }

    @Named(value = "converterDataLocalDateEmNumeroAnoMesDia")
    default Integer converterDataLocalDateEmNumeroAnoMesDia(LocalDate data){
        return ConverterData.localDateEmIntegerDataFormatoDB(data);
    }
}

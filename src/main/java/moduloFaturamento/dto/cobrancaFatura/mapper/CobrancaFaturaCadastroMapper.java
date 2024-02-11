package moduloFaturamento.dto.cobrancaFatura.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import moduloFaturamento.dto.cobrancaFatura.CobrancaFaturaCadastrarDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.CobrancaServicoFatura;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface CobrancaFaturaCadastroMapper extends EntityMapper<CobrancaFaturaCadastrarDTO, CobrancaServicoFatura> {

    @Mappings({
            @Mapping(source = "matriculaImovel", target = "idCobrancaServicoFatura.matriculaImovel"),
            @Mapping(source = "codigoServico", target = "servico.cdServico"),
            @Mapping(source = "dataDeExecucao", target = "dataExecucao", qualifiedByName = "converterDataLocalDateEmNumeroAnoMesDia"),
            @Mapping(source = "referenciaFaturar", target = "refFaturar", qualifiedByName = "converterDataLocalDateEmNumeroAnoMes")
    })
    CobrancaServicoFatura toEntity(CobrancaFaturaCadastrarDTO dto);

    @Named(value = "converterDataLocalDateEmNumeroAnoMesDia")
    default Integer converterDataLocalDateEmNumeroAnoMesDia(LocalDate data){
        return (data != null ? ConverterData.localDateEmIntegerDataFormatoDB(data) : 0);
    }

    @Named(value = "converterDataLocalDateEmNumeroAnoMes")
    default Integer converterDataLocalDateEmNumeroAnoMes(LocalDate data){
        return (data != null ? ConverterData.localDateEmIntegerReferenciaFormatoDB(data) : 0);
    }
}

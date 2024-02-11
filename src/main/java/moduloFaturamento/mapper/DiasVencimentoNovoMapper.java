package moduloFaturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoNovoDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.DiasVencimento;


@Mapper(componentModel = "spring")
public interface DiasVencimentoNovoMapper extends EntityMapper<FaturamentoDiasVencimentoNovoDTO, DiasVencimento>{

    @Mappings({ @Mapping(source = "ciclo", target = "idDiasVencimentoFaturamento.ciclo"),
			@Mapping(source = "dia", target = "idDiasVencimentoFaturamento.dia"),
			@Mapping(source = "maint", target = "maint") })
	DiasVencimento toEntity(FaturamentoDiasVencimentoNovoDTO faturamentoDiasVencimentoNew);

    
}

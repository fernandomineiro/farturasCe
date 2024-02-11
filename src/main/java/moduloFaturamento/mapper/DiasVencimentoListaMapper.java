package moduloFaturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoListaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.DiasVencimento;


@Mapper(componentModel = "spring")
public interface DiasVencimentoListaMapper extends EntityMapper<FaturamentoDiasVencimentoListaDTO, DiasVencimento>{

    @Mappings({ @Mapping(source = "ciclo", target = "idDiasVencimentoFaturamento.ciclo"),
			@Mapping(source = "dia", target = "idDiasVencimentoFaturamento.dia") })
	DiasVencimento toEntity(FaturamentoDiasVencimentoListaDTO faturamentoDiasVencimentoListaDTO);

	@Mappings({ @Mapping(source = "idDiasVencimentoFaturamento.ciclo", target = "ciclo"),
			@Mapping(source = "idDiasVencimentoFaturamento.dia", target = "dia") })
	FaturamentoDiasVencimentoListaDTO toDto(DiasVencimento faturamentoDiasVencimento);
    
}

package moduloFaturamento.dto.notificaoFatura.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaGravadaRespostaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.NotificacaoFatura;
import moduloFaturamento.model.NotificacaoFaturaMatricula;

@Mapper(componentModel = "spring")
public interface NotificacaoFaturaGravadaRespostaMapper extends EntityMapper<NotificacaoFaturaGravadaRespostaDTO, NotificacaoFatura> {

	@Mapping(source = "refFatura", target = "referencia")
	@Mapping(source = "notificacaoFaturaMatriculas", target = "matriculas", qualifiedByName = "converterMatriculas")
	NotificacaoFaturaGravadaRespostaDTO toDto(NotificacaoFatura entity);

	default List<Integer> converterMatriculas(List<NotificacaoFaturaMatricula> notificacaoFaturaMatriculas) {

		return notificacaoFaturaMatriculas.stream().map(notificacaoFaturaMatricula -> {

			return notificacaoFaturaMatricula.getMatricula();
		}).collect(Collectors.toList());
	}
}

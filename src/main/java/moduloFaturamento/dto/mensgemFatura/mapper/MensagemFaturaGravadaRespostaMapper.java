package moduloFaturamento.dto.mensgemFatura.mapper;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import moduloFaturamento.dto.mensgemFatura.MensagemFaturaGravadaRespostaDTO;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.MensagemFatura;
import moduloFaturamento.util.ConverterData;

@Mapper(componentModel = "spring")
public interface MensagemFaturaGravadaRespostaMapper extends EntityMapper<MensagemFaturaGravadaRespostaDTO, MensagemFatura> {

	@Mapping(source = "idMensagemFatura.cdCidade", target = "cdCidade")
	@Mapping(source = "idMensagemFatura.ciclo", target = "ciclo")
	@Mapping(source = "idMensagemFatura.refFatura", target = "referencia", qualifiedByName = "refIntegerToLocalDate")
	@Mapping(source = "mensagem01", target = "linha01")
	@Mapping(source = "mensagem02", target = "linha02")
	MensagemFaturaGravadaRespostaDTO toDto(MensagemFatura entity);

	@Mapping(target = "idMensagemFatura.cdCidade", source = "cdCidade")
	@Mapping(target = "idMensagemFatura.ciclo", source = "ciclo")
	@Mapping(target = "idMensagemFatura.refFatura", source = "referencia", qualifiedByName = "refLocalDateToInteger")
	@Mapping(target = "mensagem01", source = "linha01")
	@Mapping(target = "mensagem02", source = "linha02")
	MensagemFatura toEntity(MensagemFaturaGravadaRespostaDTO dto);

	@Named(value = "refIntegerToLocalDate")
	default LocalDate refIntegerToLocalDate(Integer date) {

		return ConverterData.integerEmLocalDateFormatoDB(date);
	}

	@Named(value = "refLocalDateToInteger")
	default Integer refLocalDateToInteger(LocalDate date) {

		return ConverterData.localDateEmIntegerReferenciaFormatoDB(date);
	}
}

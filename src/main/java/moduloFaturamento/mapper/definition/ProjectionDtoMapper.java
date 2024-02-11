package moduloFaturamento.mapper.definition;

import org.mapstruct.Named;

import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectionDtoMapper<D, P> {

	D toDto(P projection);

	List<D> toDto(List<P> projectionList);

	@Named(value = "converterSiglaStringParaBoolean")
	default boolean converterSiglaStringParaBoolean(String sigla) {

		return "S".equals(sigla);
	}

	@Named(value = "converterIntegerLocalDateReferencia")
	default LocalDate converterIntegerLocaldateReferencia(Integer referencia) {

		return ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia);
	}

	@Named(value = "converterIntegerLocalDateData")
	default LocalDate converterIntegerLocaldateData(Integer referencia) {

		return ConverterData.integerEmLocalDateDataFormatoDB(referencia);
	}

	@Named(value = "converterLocalDateTimeParaLocalDate")
	default LocalDate converterLocalDateTimeParaLocalDate(LocalDateTime dateTime) {

		if (dateTime != null) {

			return dateTime.toLocalDate();
		}
		return null;
	}
}

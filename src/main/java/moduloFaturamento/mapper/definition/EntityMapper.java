package moduloFaturamento.mapper.definition;
import moduloFaturamento.util.ConverterData;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
 
public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    @Named(value = "converterDataLocalDateEmNumeroAnoMesDia")
    default Integer converterDataLocalDateEmNumeroAnoMesDia(LocalDate data) {
        return ConverterData.localDateEmIntegerDataFormatoDB(data);
    }

    @Named(value = "converterSiglaStringParaBoolean")
    default boolean converterSiglaStringParaBoolean(String sigla) {
        return "S".equals(sigla);
    }

    @Named(value = "converterBooleanParaStringSOuN")
    default String converterBooleanParaStringSOuN(boolean condicao) {
        return condicao ? "S" : "N";
    }

    @Named(value = "converterBooleanParaBinario")
    default Short converterBooleanParaBinario(Boolean condicao) {
        return (condicao ? (short) 1 : (short) 0);
    }
}
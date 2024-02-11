package moduloFaturamento.regras.diaUtilCalendario;

import moduloFaturamento.repository.FeriadoRepository;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;

public abstract class DiaUtilCalendarioRegra {

    @Autowired
    private FeriadoRepository feriadoRepository;

    protected Boolean retornarSeDataInfomadaEhUmFeriadoVitoriaOuNacional(LocalDate data){
        return this.feriadoRepository.retornarSeDataInformadaEUmFeriadoVitoriaOuNacional(ConverterData.localDateEmIntegerDataFormatoDB(data));
    }

    protected Boolean retornarSeDataInformadaEstaEmUmFinalDeSemana(LocalDate data){
        return data.getDayOfWeek().equals(DayOfWeek.SATURDAY) || data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    protected Boolean retornarSeDataInfomadaEhUmFeriadoMunicipalDaCidadeSelecionada(LocalDate data, Integer cdCidade){
        return this.feriadoRepository.retornarSeDataInfomadaEhUmFeriadoMunicipalDaCidadeSelecionada(ConverterData.localDateEmIntegerDataFormatoDB(data), cdCidade);
    }
}

package moduloFaturamento.regras.tarifa;

import moduloFaturamento.util.ConverterData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TarifaLocalidadeDataAprovacaoConsultaRegra extends TarifaLocalidadeRegra{

    public LocalDate retornarDataAprovacaoTarifa(Short cidade, LocalDate dataLeituraCronograma){
        Short codigoTarifa = super.obterCodigoTarifaDeUmaLocalidade(cidade);
        Integer dataAprovacaoTarifa = super.obterMaiorDataTarifaPorCodigoTarifaQueSejaMenorQueDataLeituraCronograma(codigoTarifa,ConverterData.localDateEmIntegerDataFormatoDB(dataLeituraCronograma));
        return ConverterData.integerEmLocalDateFormatoDB(dataAprovacaoTarifa);
    }

}

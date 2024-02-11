package moduloFaturamento.regras.tarifa;

import moduloFaturamento.repository.TarifaRepository;
import moduloFaturamento.repository.common.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TarifaLocalidadeRegra {

    @Autowired
    private LocalidadeRepository localidadeRepository;
    @Autowired
    private TarifaRepository tarifaRepository;

    protected Short obterCodigoTarifaDeUmaLocalidade(Short cdCidade){
        return this.localidadeRepository.buscarCodigoTarifaPorCodigoCidade(cdCidade);
    }

    protected Integer obterMaiorDataTarifaPorCodigoTarifaQueSejaMenorQueDataLeituraCronograma(Short codigoTarifa, Integer dataLeituraCronograma){
        return this.tarifaRepository.buscarMaiorDataTarifaMenorQueDataLeituraCronograma(codigoTarifa, dataLeituraCronograma);
    }
}

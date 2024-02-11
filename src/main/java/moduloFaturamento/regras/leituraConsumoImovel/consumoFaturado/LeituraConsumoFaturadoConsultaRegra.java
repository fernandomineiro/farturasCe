package moduloFaturamento.regras.leituraConsumoImovel.consumoFaturado;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class LeituraConsumoFaturadoConsultaRegra extends LeituraConsumoFaturadoRegra {

    public BigDecimal consultarValorConsumoFaturado(Short idTipoConsumoFaturado, Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada, Short diasDeVenda, BigDecimal mediaDiaria){
        return super.obterValorCalculadoConsumoFaturado(idTipoConsumoFaturado, matricula, dataReferencia, valorLeituraInformada, diasDeVenda, mediaDiaria).setScale(1, RoundingMode.FLOOR);
    }
}

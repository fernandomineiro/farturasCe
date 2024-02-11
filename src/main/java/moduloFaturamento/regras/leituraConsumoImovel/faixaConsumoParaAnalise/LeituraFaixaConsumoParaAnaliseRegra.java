package moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise;

import java.math.BigDecimal;

abstract class LeituraFaixaConsumoParaAnaliseRegra {

    BigDecimal obterCalculoDoConsumoMedioDiarioParaTrintaDias(BigDecimal mediaDiariaDaLeitura){
        return mediaDiariaDaLeitura.multiply(new BigDecimal(30));
    }

}

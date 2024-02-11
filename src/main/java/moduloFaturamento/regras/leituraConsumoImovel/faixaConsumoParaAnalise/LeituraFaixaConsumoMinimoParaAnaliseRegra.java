package moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public abstract class LeituraFaixaConsumoMinimoParaAnaliseRegra extends LeituraFaixaConsumoParaAnaliseRegra {

    protected BigDecimal obterCalculoDoValorConsumoParaAnalise(@NotNull BigDecimal mediaDiariaDaLeitura) {
        BigDecimal consumoMedioPara30Dias = obterCalculoDoConsumoMedioDiarioParaTrintaDias(mediaDiariaDaLeitura);

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(29)) <= 0){
            return BigDecimal.ZERO;
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(29)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(60)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(0.4));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(60)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(150)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(0.5));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(150)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(250)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(0.6));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(250)) > 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(0.7));
        }

        return BigDecimal.ZERO;
    }

}


/*

Volume mínimo para a faixa de consumo para análise:
# Se o consumo médio para 30 dias for menor ou igual a 29, o limite inferior será zero;
# Se o consumo médio para 30 dias for maior que 29 e menor ou igual a 60, o limite inferior será o consumo médio para 30 dias multiplicado por 0.4;
# Se o consumo médio para 30 dias for maior que 60 e menor ou igual a 150, o limite inferior será o consumo médio para 30 dias multiplicado por 0.5;
#Se o consumo médio para 30 dias for maior que 150 e menor ou igual a 250, o limite inferior será o consumo médio para 30 dias multiplicado por 0.6;
Se o consumo médio para 30 dias for maior que 250, o limite inferior será o consumo médio para 30 dias multiplicado por 0.7;


 */
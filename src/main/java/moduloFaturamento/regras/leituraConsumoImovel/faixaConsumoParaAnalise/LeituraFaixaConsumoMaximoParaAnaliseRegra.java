package moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public abstract class LeituraFaixaConsumoMaximoParaAnaliseRegra extends LeituraFaixaConsumoParaAnaliseRegra {

    protected BigDecimal obterConsumoMedioMensal(BigDecimal mediaDiariaDaLeitura, @NotNull Short diasVendaDaLeitura){
        return mediaDiariaDaLeitura.multiply(new BigDecimal(diasVendaDaLeitura.intValue()));
    }

    protected BigDecimal obterCalculoDoValorConsumoParaAnalise(@NotNull BigDecimal mediaDiariaDaLeitura){
        BigDecimal consumoMedioPara30Dias = obterCalculoDoConsumoMedioDiarioParaTrintaDias(mediaDiariaDaLeitura);

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(10)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(5.0));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(10)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(29)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(4.5));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(29)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(59)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(4.0));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(59)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(149)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(3.0));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(149)) > 0 && consumoMedioPara30Dias.compareTo(new BigDecimal(249)) <= 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(2.5));
        }

        if(consumoMedioPara30Dias.compareTo(new BigDecimal(249)) > 0){
            return consumoMedioPara30Dias.multiply(BigDecimal.valueOf(2.0));
        }
        return BigDecimal.ZERO;
    }
}
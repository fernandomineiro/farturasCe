package moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LeituraFaixaConsumoMinimoParaAnaliseConsultaRegra extends LeituraFaixaConsumoMinimoParaAnaliseRegra {

    public BigDecimal obterValorFaixa(BigDecimal mediaDiariaDaLeitura){
        return super.obterCalculoDoValorConsumoParaAnalise(mediaDiariaDaLeitura);
    }
}

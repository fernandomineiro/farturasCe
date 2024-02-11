package moduloFaturamento.regras.leituraConsumoImovel.faixaConsumoParaAnalise;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LeituraFaixaConsumoMaximoParaAnaliseConsultaRegra extends LeituraFaixaConsumoMaximoParaAnaliseRegra {

    public BigDecimal obterValorFaixa(BigDecimal mediaDiariaDaLeitura){
        return super.obterCalculoDoValorConsumoParaAnalise(mediaDiariaDaLeitura);
    }
}

package moduloFaturamento.regras.lancamentoContabil;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.LancamentoContabil;

@Service
public class LancamentoContabilParaCrudFaturaAvulsaRegra extends LancamentoContabilRegra {

    Integer cdParcelamento = 0;
    Short numeroParcela = 0;
    Integer nuLoteArrec = 0;

    public List<LancamentoContabil> lancamentoContabilParaFaturaAvulsa(Short evento, Integer matricula, BigDecimal valorCredito, 
                                    Integer cdCliente, Short cdCredito, Short cdServico, String debitoOuCredito, 
                                    Integer refFatura, Short origemFatura, Integer cdCidade) {
        return super.lancamentoContabil(evento, matricula, valorCredito, cdCliente, cdCredito, cdServico, debitoOuCredito, refFatura, 
                                origemFatura, cdParcelamento, numeroParcela, cdCidade, nuLoteArrec);
    }
}

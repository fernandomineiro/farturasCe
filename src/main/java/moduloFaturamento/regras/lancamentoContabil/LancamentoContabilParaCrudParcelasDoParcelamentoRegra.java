package moduloFaturamento.regras.lancamentoContabil;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.LancamentoContabil;

@Service
public class LancamentoContabilParaCrudParcelasDoParcelamentoRegra extends LancamentoContabilRegra{

    private Integer nuLoteArrec = 0;
    private Integer refFatura= 0;
    private Short origemFatura = 0;
    private Short cdCredito = 0;
    private Integer cdCidade = 0;

    public List<LancamentoContabil> lancamentoContabilParaCadastrarParcelasDoParcelamentoRegra(Short evento, Integer matricula, BigDecimal valorASerLancado, 
        Integer cdCliente, Short cdServico, String debitoOuCredito, Integer cdParcelamento, Short numeroParcela, Integer numeroDaContaContabilDebito, 
        Integer numeroDaContaContabilCredito){

        return super.lancamentoContabilManual(evento, matricula, valorASerLancado, cdCliente, cdCredito, cdServico, debitoOuCredito, refFatura, 
            origemFatura, cdParcelamento, numeroParcela, cdCidade, nuLoteArrec, numeroDaContaContabilDebito, numeroDaContaContabilCredito);
    }
}

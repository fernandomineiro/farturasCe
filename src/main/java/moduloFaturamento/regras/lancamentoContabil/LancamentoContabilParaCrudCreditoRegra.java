package moduloFaturamento.regras.lancamentoContabil;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.LancamentoContabil;

@Service
public class LancamentoContabilParaCrudCreditoRegra extends LancamentoContabilRegra {

    private Integer cdParcelamento = 0;
    private Short numeroParcela = 0;
    private Integer nuLoteArrec = 0;
    private Integer refFatura= 0;
    private Short origemFatura = 0;
    private Integer cdCidade = 0;

    public List<LancamentoContabil> lancamentoContabilParaCredito(Short evento, Integer matricula, BigDecimal valorCredito, Integer cdCliente, 
                                                                Short cdCredito, Short cdServico, String debitoOuCredito){

        return super.lancamentoContabil(evento, matricula, valorCredito, cdCliente, cdCredito, cdServico, debitoOuCredito,
                                     refFatura, origemFatura, cdParcelamento, numeroParcela, cdCidade, nuLoteArrec);
    }

    public List<LancamentoContabil> lancamentoContabilParaCreditoEscolhendoContasContabis(Short evento, Integer matricula, BigDecimal valorASerLancado, 
        Integer cdCliente, Short cdCredito, Short cdServico, String debitoOuCredito, Integer numeroDaContaContabilDebito, 
        Integer numeroDaContaContabilCredito){

        return super.lancamentoContabilManual(evento, matricula, valorASerLancado, cdCliente, cdCredito, cdServico, debitoOuCredito, refFatura, 
            origemFatura, cdParcelamento, numeroParcela, cdCidade, nuLoteArrec, numeroDaContaContabilDebito, numeroDaContaContabilCredito);
    }
}

package moduloFaturamento.regras.lancamentoContabil;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.LancamentoContabil;

@Service
public class LancamentoContabilParaCrudFaturaRegra extends LancamentoContabilRegra{

    private  Integer cdParcelamento = 0;
    private Short numeroParcela = 0;
    private Integer cdCidade = 0;
    private Integer nuLoteArrec = 0;
    private Short cdCredito = 0;

    public List<LancamentoContabil> encerrarFaturaPorNegociacaoDoParcelamentoEscolhendoContasContabis(Short evento, Integer matricula, BigDecimal valorASerLancado, 
        Integer cdCliente, Short cdServico, String debitoOuCredito, Integer refFatura, Short origemFatura, Integer numeroDaContaContabilDebito, 
        Integer numeroDaContaContabilCredito ){

        return lancamentoContabilManual(evento, matricula, valorASerLancado, cdCliente, cdCredito, cdServico, debitoOuCredito, refFatura, 
            origemFatura, cdParcelamento, numeroParcela, cdCidade, nuLoteArrec, numeroDaContaContabilDebito, numeroDaContaContabilCredito);
    }
}

package moduloFaturamento.regras.credito;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.Credito;

@Service
public class CreditoCadastroRegra  extends CreditoRegras{

    @Override
    public Credito construirCredito(Integer matricula, String loginUsuario, BigDecimal valorCredito, Short cdServico, Short dv,
        Integer refAtedimento, Integer cdAtendimento, Short seqSS, 
        Integer cdParcelamento,  Integer numeroParcela, 
        Short origemFatura, LocalDate referenciaFatura,
        String dcAnotacao){

        return super.construirCredito(matricula, loginUsuario, valorCredito, cdServico, dv,
            refAtedimento, cdAtendimento, seqSS, 
            cdParcelamento, numeroParcela, 
            origemFatura, referenciaFatura,
            dcAnotacao);
    }
    
}

package moduloFaturamento.regras.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaEdicaoRegra extends CobrancaFaturaRegra{

    public void validarReferenciaFatura(Integer referenciaFaturada){
        super.impedirQualquerModificacaoCobrancaComReferenciaFaturadaCadastrada(referenciaFaturada);
    }
}

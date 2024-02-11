package moduloFaturamento.regras.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaCadastroRegra extends CobrancaFaturaRegra{

    public Short obterProximoCodigoCdCobrancaQuePodeSerUsadoParaCadastrarCobranca(Integer matriculaImovel){
        return super.retonarValorCdCobrancaParaCadastrarNovoRegistro(matriculaImovel).shortValue();
    }
}

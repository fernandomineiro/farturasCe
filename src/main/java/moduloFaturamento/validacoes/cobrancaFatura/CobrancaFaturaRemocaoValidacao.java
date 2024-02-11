package moduloFaturamento.validacoes.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaRemocaoValidacao extends CobrancaFaturaValidacao {

    public void validar(Long id){
        super.gerarExececaoCobrancaFaturaNaoEncontrado(id);
    }
}

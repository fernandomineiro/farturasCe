package moduloFaturamento.validacoes.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaConsultaValidacao extends CobrancaFaturaValidacao {

    public void validar(Long id){
        super.gerarExececaoCobrancaFaturaNaoEncontrado(id);
    }
}

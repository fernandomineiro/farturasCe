package moduloFaturamento.validacoes.diasVencimento;

import org.springframework.stereotype.Service;

@Service
public class DiasVencimentoCadastrarValidacao extends DiasVencimentoValidacao {

    public void gerenciarValidacaoParaCadastrar(Short ciclo, Short dia, String acao){
        super.verificarSeDiaExisteDiaNoCiclo(ciclo, dia, acao);
    }
    
}

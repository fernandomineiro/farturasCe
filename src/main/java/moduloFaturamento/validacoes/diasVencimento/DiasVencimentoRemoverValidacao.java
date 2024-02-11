package moduloFaturamento.validacoes.diasVencimento;

import org.springframework.stereotype.Service;

@Service
public class DiasVencimentoRemoverValidacao extends DiasVencimentoValidacao {

    public void gerenciarValidacaoParaRemover(Short ciclo, Short dia, String acao){
        super.verificarSeDiaExisteDiaNoCiclo(ciclo, dia, acao);
    }
    
}

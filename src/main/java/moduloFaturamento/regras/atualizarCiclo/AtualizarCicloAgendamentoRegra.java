package moduloFaturamento.regras.atualizarCiclo;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.AtualizarCiclo;

@Service
public class AtualizarCicloAgendamentoRegra extends AtualizarCicloRegra{

    public AtualizarCiclo atualizarCicloNoAgendamentoNaSituacaoUm(AtualizarCiclo atualizarCiclo){
        return super.atualizarCicloParaSituacaoUm(atualizarCiclo);
    }

    public AtualizarCiclo atualizarCicloNoAgendamentoNaSituacaoDoisDuplicidade(AtualizarCiclo atualizarCiclo) {
        return super.atualizarCicloParaSituacaoDoisDuplicidade(atualizarCiclo);
    }

    public AtualizarCiclo atualizarCicloNoAgendamentoNaSituacaoDoisInexistente(AtualizarCiclo atualizarCiclo) {
        return super.atualizarCicloParaSituacaoDoisInexistente(atualizarCiclo);
    }

    public AtualizarCiclo atualizarCicloNoAgendamentoNaSituacaoDoisCoincidente(AtualizarCiclo atualizarCiclo) {
        return super.atualizarCicloParaSituacaoDoisCoincidente(atualizarCiclo);
    }
    
}

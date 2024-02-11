package moduloFaturamento.regras.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoAtualizarRegra extends CronogramaFaturamentoRegra {

    public void gerenciarRegrasCronogramaExistenteParaEditar(Short faseAtual){
        this.impedirEdicaoCronogramaPoisFaseEstaEmEncerrada(faseAtual);
    }

    public Short retornarFaseAtualizadaPelaAcaoAvancarRetornarFase(Short faseAtual,  boolean retornarFase, boolean avancarFase){
        return super.retornarFaseCronogramaAtualizadaAtravesAcaoAvancarOuRetornar(faseAtual, retornarFase, avancarFase);
    }
}


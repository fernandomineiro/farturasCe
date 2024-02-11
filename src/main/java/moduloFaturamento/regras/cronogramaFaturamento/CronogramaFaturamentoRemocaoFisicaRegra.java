package moduloFaturamento.regras.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoRemocaoFisicaRegra extends CronogramaFaturamentoRegra{

    public void gerenciarRegrasCronogramaExistenteParaExcluir(Short faseCronograma, Short cdCidade, Short ciclo, Integer dataReferencia){
        super.impedirRemocaoCronogramaPoisFaseDiferenteDeGerarEspelho(faseCronograma);
        super.impedirRemocaoCronogramaPoisExisteFaturaParaCronograma(cdCidade, ciclo, dataReferencia);
    }
}


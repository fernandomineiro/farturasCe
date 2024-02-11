package moduloFaturamento.regras.cronogramaFaturamento.validacoes;


import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoFaturaLancadaRemocaoValidacaoRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validar(Short cdCidade, Short ciclo, Integer dataReferencia){
        super.gerarExcecaoRemoverCronogramaPoisFaturaExistenteParaCronograma(cdCidade, ciclo, dataReferencia);
    }
}

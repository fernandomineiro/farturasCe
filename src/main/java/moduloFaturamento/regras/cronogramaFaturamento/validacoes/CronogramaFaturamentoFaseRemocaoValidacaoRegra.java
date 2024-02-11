package moduloFaturamento.regras.cronogramaFaturamento.validacoes;


import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoFaseRemocaoValidacaoRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validar(Short faseCronograma){
        super.gerarExcecaoRemoverCronogramaQueEstaEmFaseDiferenteGerarEspelho(faseCronograma);
    }
}

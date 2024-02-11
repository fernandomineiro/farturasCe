package moduloFaturamento.regras.cronogramaFaturamento.validacoes;


import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoFaseEdicaoValidacaoRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validar(Short faseCronograma){
        super.gerarExcecaoEditarCronogramaQueEstaFechado(faseCronograma);
    }
}

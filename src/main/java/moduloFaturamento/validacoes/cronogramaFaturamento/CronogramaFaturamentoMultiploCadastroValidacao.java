package moduloFaturamento.validacoes.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoMultiploCadastroValidacao extends CronogramaFaturamentoValidacao {

    public void gerenciarValidacaoParaCadastrar(Short cdCidade, Short ciclo, Integer dataReferencia){
        super.gerarExcecaoCronogramaJaCadastrado(cdCidade, ciclo, dataReferencia);
    }
}

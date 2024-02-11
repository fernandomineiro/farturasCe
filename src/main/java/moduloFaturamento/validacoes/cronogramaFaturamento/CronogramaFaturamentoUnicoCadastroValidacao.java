package moduloFaturamento.validacoes.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoUnicoCadastroValidacao extends CronogramaFaturamentoValidacao {

    public void gerenciarValidacaoParaCadastrar(Short cdCidade, Short ciclo, Integer dataReferencia){
        super.gerarExcecaoCronogramaJaCadastrado(cdCidade, ciclo, dataReferencia);
    }
}

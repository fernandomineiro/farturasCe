package moduloFaturamento.validacoes.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoAtualizacaoValidacao extends CronogramaFaturamentoValidacao {

    public void gerenciarValidacaoParaAtualizar(Short cdCidade, Short ciclo, Integer dataReferencia, Long idCronograma, boolean avancarFase, boolean retornarFase){
        super.gerarExcecaoAvancarFaseTrueRetonarFaseTrue(retornarFase, avancarFase);
        super.gerarExcecaoAvancarFasePoremFaseAtualNaoEstaEmIncluirLeitura(idCronograma, avancarFase);
        super.gerarExcecaoRetonarFasePoremFaseAtualEstaEmGerarEspelho(idCronograma, retornarFase);
    }
}

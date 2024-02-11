package moduloFaturamento.validacoes.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoRemocaoFisicaValidacao extends CronogramaFaturamentoValidacao{

    public void gerenciarValidacaoDadosParaDeletar(Long idCronograma){
        super.gerarExcecaoCronogramaNaoEncontrado(idCronograma);
    }
}

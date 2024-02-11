package moduloFaturamento.validacoes.cronogramaFaturamento;

import org.springframework.stereotype.Service;

@Service
public class CronogramaFaturamentoConsultaValidacao extends CronogramaFaturamentoValidacao {

    public void validarConsulta(Long idCronograma){
        super.gerarExcecaoCronogramaNaoEncontrado(idCronograma);
    }
}

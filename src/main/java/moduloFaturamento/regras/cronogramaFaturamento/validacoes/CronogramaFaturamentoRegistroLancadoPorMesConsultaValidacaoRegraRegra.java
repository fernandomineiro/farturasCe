package moduloFaturamento.regras.cronogramaFaturamento.validacoes;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CronogramaFaturamentoRegistroLancadoPorMesConsultaValidacaoRegraRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validarConsulta(Short ciclo, LocalDate dataReferenciaAnterior){
        super.gerarExcecaoCronogramaAnteriorInexistente(ciclo, dataReferenciaAnterior);
    }

}

package moduloFaturamento.regras.cronogramaFaturamento.validacoes;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CronogramaFaturamentoDataTarifaConsultaValidacaoRegraRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validarConsulta(Short cdCidade, Short ciclo, LocalDate dataReferenciaAnterior){
        super.sufixoExcecaoCronogramaAnteriorInexistente = ", para identificar localidade e obter tarifa";
        super.gerarExcecaoCronogramaAnteriorInexistente(cdCidade, ciclo, dataReferenciaAnterior);
    }
}

package moduloFaturamento.regras.cronogramaFaturamento.validacoes;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CronogramaFaturamentoDataVencimentoConsultaValidacaoRegraRegra extends CronogramaFaturamentoValidacaoRegra {

    public void validarConsulta(Short cdCidade, Short ciclo, LocalDate dataReferenciaAnterior){
        super.sufixoExcecaoCronogramaAnteriorInexistente = ", para obter nova data de vencimento";
        super.gerarExcecaoCronogramaAnteriorInexistente(cdCidade, ciclo, dataReferenciaAnterior);
    }
}

package moduloFaturamento.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moduloFaturamento.service.TarifaMediaAgendamentoService;

@Component
public class TarifaMediaAgendamento {

    @Autowired
    private TarifaMediaAgendamentoService tarifaMediaAgendamentoService;

    //private static final String TIME_ZONE = "America/Sao_Paulo";

  //Agendamento desativado para implantacao em Producao
    //@Scheduled(cron = "0 0 7 10 * ?", zone = TIME_ZONE)
    public void executarRotinaParaCalcularTarifaMedia() {

        tarifaMediaAgendamentoService.executarRotinaTarifaMediaAgendamento();
    }

    //Agendamento desativado para implantacao em Producao
    //@Scheduled(cron = "0 0 6 * * ?", zone = TIME_ZONE)
    public void executarRotinaVerificarSeHaReferenciaAdiada() {

        tarifaMediaAgendamentoService.verificarSeExisteReferenciaAExecutar();
    }

}

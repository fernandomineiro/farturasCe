package moduloFaturamento.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moduloFaturamento.service.AtualizacaoCicloService;

@Component
public class AtualizacaoCicloAgendamento {

    @Autowired
    private AtualizacaoCicloService atualizacaoCicloService;

    //Agendamento desativado para implantacao em Producao
    //private static final String TIME_ZONE = "America/Sao_Paulo";
    
    //Agendamento desativado para implantacao em Producao
    //@Scheduled(cron = "0 0 5 ? * MON-FRI", zone = TIME_ZONE)
    public void executarAtualizacaoDeCiclo(){

        atualizacaoCicloService.executarAtualizacaoDeCiclo();
    }

}
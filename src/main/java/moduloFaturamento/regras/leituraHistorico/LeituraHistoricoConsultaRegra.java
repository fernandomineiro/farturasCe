package moduloFaturamento.regras.leituraHistorico;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeituraHistoricoConsultaRegra extends LeituraHistoricoRegra {

    public boolean retornarVerdadeiroSeCicloCronogramaEstiverFechado(Integer matricula, LocalDate dataLeitura, Integer refFatura){
        return super.retornarVerdadeiroSeCicloCronogramaEstiverFechado(matricula, dataLeitura, refFatura);
    }

}

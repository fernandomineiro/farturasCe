package moduloFaturamento.regras.leituraConsumoImovel.dias.diasVenda;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeituraConsumoDiasVendaConsultaRegra extends LeituraConsumoDiasVendaRegra {

    public Short obterValorDiasDeVenda(Integer matricula, LocalDate dataLeitura, LocalDate refFatura){
        return super.obterValorDiasDeVenda(matricula, dataLeitura, refFatura);
    }
}

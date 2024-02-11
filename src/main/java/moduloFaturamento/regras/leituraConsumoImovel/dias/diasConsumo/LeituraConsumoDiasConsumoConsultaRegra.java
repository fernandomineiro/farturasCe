package moduloFaturamento.regras.leituraConsumoImovel.dias.diasConsumo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LeituraConsumoDiasConsumoConsultaRegra extends LeituraConsumoDiasConsumoRegra{

    public short obterValorDiasDeConsumo(Integer matricula, LocalDate dataLeituraAtual, LocalDate refFatura){
        return super.obterValorDiasDeConsumo(matricula, dataLeituraAtual, refFatura);
    }
}

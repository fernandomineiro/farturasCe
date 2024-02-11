package moduloFaturamento.regras.leituraConsumoImovel.mediaDiaria;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LeituraConsumoMediaDiariaConsultaRegra extends LeituraConsumoMediaDiariaRegra{

    public BigDecimal consultarMediaDiaria(Integer matriculaImovel, LocalDate refFatura){
        return super.buscarValorCalculadoMediaDiaria(matriculaImovel, refFatura);
    }
}

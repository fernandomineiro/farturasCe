package moduloFaturamento.regras.leituraConsumoImovel.dias;

import moduloFaturamento.regras.leituraConsumoImovel.LeituraConsumoRegra;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.HidrometroInstaladoRepository;
import moduloFaturamento.repository.common.HidrometroRetiradoRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public abstract class LeituraConsumoDiasRegra extends LeituraConsumoRegra {

    @Autowired
    protected HidrometroInstaladoRepository hidrometroInstaladoRepository;
    @Autowired
    protected HidrometroRetiradoRepository hidrometroRetiradoRepository;
    @Autowired
    protected CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    protected ImovelRepository imovelRepository;


    protected long retornarDiasVendaCalculadoConformeReferenciaAnteriorEAtual(LocalDate dataLeituraAnterior, LocalDate dataLeituraAtual){
        return dataLeituraAnterior.until(dataLeituraAtual,ChronoUnit.DAYS);
    }

    protected long retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(LocalDate dataLeituraAnterior, LocalDate dataLeituraAtual){
        return dataLeituraAnterior.until(dataLeituraAtual,ChronoUnit.DAYS);
    }
}

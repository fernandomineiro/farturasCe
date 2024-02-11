package moduloFaturamento.regras.leituraHistorico;

import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public abstract class LeituraHistoricoRegra {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;

    protected boolean retornarVerdadeiroSeCicloCronogramaEstiverFechado(Integer matricula, LocalDate dataLeitura, Integer refFatura){
        Short cicloDoImovel = this.imovelRepository.buscarCicloDoImovelPelaMatricula(matricula);
        Short cidadeDoImovel = this.imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matricula);

        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarPeloMenosUmaLeituraPorCicloReferenciaECidadeQueEstejaComCicloFechado(cicloDoImovel, refFatura, cidadeDoImovel);

        return cronogramaFatura != null;
    }
}

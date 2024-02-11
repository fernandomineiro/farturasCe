package moduloFaturamento.service;

import java.util.List;

import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoDeletarDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoListaDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoNovoDTO;

public interface FaturamentoCicloService {

    List<Integer> buscarListaCiclos();

    List<FaturamentoDiasVencimentoListaDTO> buscarDiasDosCiclos(List<Short> ciclos);

    FaturamentoDiasVencimentoListaDTO novoDiaPorCiclo(FaturamentoDiasVencimentoNovoDTO ciclo, String token);

    void deletarDiaDoCiclo(FaturamentoDiasVencimentoDeletarDTO ciclo, String token);

}

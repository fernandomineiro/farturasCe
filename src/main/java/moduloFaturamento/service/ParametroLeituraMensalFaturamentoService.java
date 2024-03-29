package moduloFaturamento.service;

import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO;
import moduloFaturamento.dto.parametroLeituraMensalFaturamento.ParametroLeituraMensalFaturamentoDTO;

public interface ParametroLeituraMensalFaturamentoService {

    ParametroLeituraMensalFaturamentoDTO buscarParametro();
    CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO executarFluxoAtualizarPorCronogramaFaturaCicloFaturamento(CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO dto, String token);
}

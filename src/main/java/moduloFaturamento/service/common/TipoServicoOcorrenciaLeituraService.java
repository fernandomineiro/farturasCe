package moduloFaturamento.service.common;

import moduloFaturamento.dto.ocorrenciaLeitura.TipoServicoOcorrenciaLeituraDTO;

import java.util.List;

public interface TipoServicoOcorrenciaLeituraService {
    List<TipoServicoOcorrenciaLeituraDTO> buscarTodosTipoOcorrenciaLeitura();

}

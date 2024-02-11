package moduloFaturamento.service;

import moduloFaturamento.dto.TipoFeriadoDTO;

import java.util.List;

public interface TipoFeriadoService {
    List<TipoFeriadoDTO> buscarTodosTipoFeriado();
}

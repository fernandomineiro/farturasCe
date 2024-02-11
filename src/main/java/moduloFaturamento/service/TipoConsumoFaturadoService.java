package moduloFaturamento.service;

import moduloFaturamento.dto.tipoConsumoFaturado.TipoConsumoFaturadoDTO;

import java.time.LocalDate;
import java.util.List;

public interface TipoConsumoFaturadoService {
    List<TipoConsumoFaturadoDTO> buscarListaTipoConsumoFaturadoParaLeituraConsumoImovel(LocalDate referencia);
}

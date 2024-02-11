package moduloFaturamento.service.common;

import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;

import java.util.List;

public interface SituacaoLigacaoAguaService {

    List<SituacaoLigacaoAguaDTO> buscarTodasAsLigacoes();
}

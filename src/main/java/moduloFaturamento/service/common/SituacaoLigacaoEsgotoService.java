package moduloFaturamento.service.common;

import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;

import java.util.List;

public interface SituacaoLigacaoEsgotoService {

    List<SituacaoLigacaoEsgotoDTO> buscarTodasAsLigacoes();
}

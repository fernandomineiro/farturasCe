package moduloFaturamento.service.common;

import moduloFaturamento.dto.imovelArrecadacao.ArrecadacaoImovelDTO;

public interface ImovelArrecadacaoService {
    ArrecadacaoImovelDTO buscarArrecadacaoPorMatriculaImovel(Integer matriculaImovel);
}

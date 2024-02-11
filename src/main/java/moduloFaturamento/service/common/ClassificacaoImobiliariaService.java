package moduloFaturamento.service.common;

import moduloFaturamento.dto.classificacaoImobiliaria.LeituraConsumoImovelCabecalhoClassificacaoImovelDTO;

import java.util.List;

public interface ClassificacaoImobiliariaService {

    List<LeituraConsumoImovelCabecalhoClassificacaoImovelDTO> buscarListaClassificacaoImobiliariaParaCabecalhoLeituraConsumoImovel(Integer matriculaImovel);
    Integer buscarTotalEconomias(Integer matriculaImovel);
}

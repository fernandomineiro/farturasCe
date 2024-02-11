package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.faturaImovel.FaturaImovelDetalhadaDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;

public interface FaturaImovelService {

	FaturaImovelDetalhadaDTO buscarFaturaImovelDetalhada(Integer idFatura, String token);

	GenericoWrapperDTO<List<FaturaImovelLancamentoProjectionDTO>> buscarFaturaImovelDetalhadaLancamentos(Integer idFatura, String token, Pageable pageable);

}

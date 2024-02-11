package moduloFaturamento.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.comparator.FaturaImovelLancamentoComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.faturaImovel.FaturaImovelDetalhadaDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelCabecalhoProjectionDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;
import moduloFaturamento.mapper.FaturaImovelDetalhadaCabecalhoMapper;
import moduloFaturamento.repository.FaturaImovelRepository;
import moduloFaturamento.service.FaturaImovelService;
import moduloFaturamento.util.Paginacao;

@Service
@Transactional
public class FaturaImovelServiceImpl implements FaturaImovelService {

	@Autowired
	private FaturaImovelRepository faturaImovelRepository;

	@Autowired
	private FaturaImovelDetalhadaCabecalhoMapper faturaImovelDetalhadaCabecalhoMapper;

	@Override
	public FaturaImovelDetalhadaDTO buscarFaturaImovelDetalhada(Integer idFatura, String token) {

		FaturaImovelCabecalhoProjectionDTO faturaImovelDetalhadaCabecalho = faturaImovelRepository
				.buscarFaturaImovelDetalhadaCabecalho(idFatura);
		if (faturaImovelDetalhadaCabecalho == null) {
			throw new RuntimeException("Fatura não encontrada");
		}
		List<FaturaImovelLancamentoProjectionDTO> faturaImovelDetalhadaLancamentos = faturaImovelRepository
				.buscarFaturaImovelDetalhadaLancamentos(idFatura);
		if (faturaImovelDetalhadaCabecalho != null) {
			FaturaImovelDetalhadaDTO dto = faturaImovelDetalhadaCabecalhoMapper.toDto(faturaImovelDetalhadaCabecalho);

			dto.setLancamentosFatura(faturaImovelDetalhadaLancamentos);
			return dto;
		}

		return new FaturaImovelDetalhadaDTO();
	}

	@Override
	public GenericoWrapperDTO<List<FaturaImovelLancamentoProjectionDTO>> buscarFaturaImovelDetalhadaLancamentos(
			Integer idFatura, String token,
			Pageable pageable) {

		List<FaturaImovelLancamentoProjectionDTO> lancamentoListaDTO = faturaImovelRepository
				.buscarFaturaImovelDetalhadaLancamentos(idFatura);

		GenericoWrapperDTO<List<FaturaImovelLancamentoProjectionDTO>> wrapperListaDTO = Paginacao.paginarCampoUnico(
				new FaturaImovelLancamentoComparator(),
				pageable, lancamentoListaDTO);

		return wrapperListaDTO;
	}
}

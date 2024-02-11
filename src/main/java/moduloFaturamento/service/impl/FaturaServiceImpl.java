package moduloFaturamento.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.MensagemTipoGenericoDTO;
import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.FaturamentoParcelamentoRepository;
import moduloFaturamento.service.FaturaService;

@Service
public class FaturaServiceImpl implements FaturaService {

	@Autowired
	private FaturaRepository faturaRepository;
	
	@Autowired
	private FaturamentoParcelamentoRepository faturamentoParcelamentoRepository;

	@Override
	public MensagemTipoGenericoDTO<Boolean> validarExclusaoImovel(Integer matricula) {
		MensagemTipoGenericoDTO<Boolean> mesagemGenericoDTO = new MensagemTipoGenericoDTO<>();
		mesagemGenericoDTO.setMensagem(faturaRepository.findByIdFatura_MatriculaImovel(matricula)
				.stream().noneMatch(e -> e.getTpBaixa().shortValue() == 0));
		if (mesagemGenericoDTO.getMensagem())
			mesagemGenericoDTO.setMensagem(faturamentoParcelamentoRepository.findByImovel(new Imovel(matricula))
					.stream().noneMatch(e -> e.getStatusParcelamento().shortValue() == 0));

		return mesagemGenericoDTO;

	}

	@Override
	public Fatura retornarEntidadeUltimoFaturamentoDoImovel(Integer matricula, Integer refFatura){
		return this.faturaRepository.buscarUltimaFaturaDoImovel(matricula, refFatura);
	}

	@Override
	public FaturaSituacaoAguaEsgotoProjection buscarFaturaSituacaoAguaEsgotoProjectionPorMatriculaReferencia(Integer matricula, Integer refFatura) {
		
		Optional<FaturaSituacaoAguaEsgotoProjection> projectionOP = faturaRepository.buscarFaturaSituacaoAguaEsgotoProjectionPorMatriculaReferencia(matricula, refFatura);
		
		return projectionOP.orElseThrow();
	}

}

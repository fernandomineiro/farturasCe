package moduloFaturamento.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaFilterDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaFilterDTO;
import moduloFaturamento.regras.simulacaoFatura.FaturaDetalhadaMatriculaReferenciaRegra;
import moduloFaturamento.regras.simulacaoFatura.FaturaDetalhadaSimuladaRegra;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.repository.common.OcorrenciaLeituraRepository;
import moduloFaturamento.service.SimulacaoFaturaService;
import moduloFaturamento.validacoes.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaValidacoes;
import moduloFaturamento.validacoes.simulacaoFatura.SimulacaoFaturaSimuladaValidacoes;

@Service
@Transactional
public class SimulacaoFaturaServiceImpl implements SimulacaoFaturaService {

	@Autowired
	private FaturaDetalhadaSimuladaRegra faturaDetalhadaSimuladaRegra;

	@Autowired
	private FaturaDetalhadaMatriculaReferenciaRegra faturaDetalhadaMatriculaReferenciaRegra;

	@Autowired
	private OcorrenciaLeituraRepository ocorrenciaLeituraRepository;

	// ------------------------------------------------------------- Validações

	@Autowired
	private SimulacaoFaturaSimuladaValidacoes simulacaoFaturaSimuladaValidacoes;

	@Autowired
	private SimulacaoFaturaMatriculaReferenciaValidacoes simulacaoFaturaMatriculaReferenciaValidacoes;

	@Override
	public List<OcorrenciaLeituraDropDownProjectionDTO> buscarDropDownOcorrenciaLeitura() {

		return ocorrenciaLeituraRepository.buscarListaDropDownOcorrenciaLeitura();
	}

	@Override
	public FaturaDetalhadaRespostaDTO buscarFaturaPorMatriculaReferencia(SimulacaoFaturaMatriculaReferenciaFilterDTO dto, String token) {

		simulacaoFaturaMatriculaReferenciaValidacoes.gerenciarValidacao(dto);
		
		FaturaDetalheProcessamento detalhe = faturaDetalhadaMatriculaReferenciaRegra.construirDetalheProcessamentoPorDtoMatricula(dto);
		faturaDetalhadaMatriculaReferenciaRegra.construirFaixasProcessamentoPorDtoSimulacao(dto, detalhe);
		faturaDetalhadaMatriculaReferenciaRegra.calcularFaturaFaixasProcessamento(detalhe);
		
		faturaDetalhadaMatriculaReferenciaRegra.calcularFaturaDetalheProcessamento(detalhe);

		return faturaDetalhadaMatriculaReferenciaRegra.mapearFaturaDetalheProcessamentoParaDTO(dto, detalhe);
	}

	@Override
	public FaturaDetalhadaRespostaDTO buscarFaturaSimulada(SimulacaoFaturaFilterDTO dto, String token) {

		simulacaoFaturaSimuladaValidacoes.gerenciarValidacao(dto);

		FaturaDetalheProcessamento detalhe = faturaDetalhadaSimuladaRegra.construirDetalheProcessamentoPorDto(dto);

		faturaDetalhadaSimuladaRegra.construirFaixasProcessamentoPorDto(dto, detalhe);
		faturaDetalhadaSimuladaRegra.calcularFaturaFaixasProcessamento(detalhe);

		faturaDetalhadaSimuladaRegra.calcularFaturaDetalheProcessamento(detalhe);

		return faturaDetalhadaSimuladaRegra.mapearFaturaDetalheProcessamentoParaDTO(dto, detalhe);
	}
}

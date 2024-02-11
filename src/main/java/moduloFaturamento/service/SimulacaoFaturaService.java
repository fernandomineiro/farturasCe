package moduloFaturamento.service;

import java.util.List;

import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaFilterDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaFilterDTO;

public interface SimulacaoFaturaService {
	
	List<OcorrenciaLeituraDropDownProjectionDTO> buscarDropDownOcorrenciaLeitura();

	FaturaDetalhadaRespostaDTO buscarFaturaPorMatriculaReferencia(SimulacaoFaturaMatriculaReferenciaFilterDTO dto, String token);

	FaturaDetalhadaRespostaDTO buscarFaturaSimulada(SimulacaoFaturaFilterDTO dto, String token);

}

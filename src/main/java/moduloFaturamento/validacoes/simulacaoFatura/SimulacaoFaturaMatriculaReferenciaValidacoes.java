package moduloFaturamento.validacoes.simulacaoFatura;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaFilterDTO;

@Service
public class SimulacaoFaturaMatriculaReferenciaValidacoes extends SimulacaoFaturaValidacoes {

	public void gerenciarValidacao(SimulacaoFaturaMatriculaReferenciaFilterDTO dto) {

		super.gerarExcessaoMatriculaInexistente(dto.getMatricula());
		
		super.gerarExcessaoFaturaInexistenteParaReferencia(dto.getMatricula(), dto.getReferencia());
	}

}

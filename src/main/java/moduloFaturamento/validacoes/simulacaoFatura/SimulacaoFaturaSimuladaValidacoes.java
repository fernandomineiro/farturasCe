package moduloFaturamento.validacoes.simulacaoFatura;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaFilterDTO;

@Service
public class SimulacaoFaturaSimuladaValidacoes extends SimulacaoFaturaValidacoes {

	public void gerenciarValidacao(SimulacaoFaturaFilterDTO dto) {
		
		super.gerarExcessaoMatriculaInexistenteSeInformada(dto.getMatricula());
		super.gerarExcessaoOcorrenciaDeLeituraComMatriculaInexistente(dto.getMatricula(), dto.getCdOcorrencia());
	}

}

package moduloFaturamento.validacoes.qualidadeDaAgua;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.repository.AmostrasExigidasRepository;
import moduloFaturamento.repository.AmostrasRealizadasRepository;

public abstract class QualidadeDaAguaValidacoes {

	@Autowired
	private AmostrasExigidasRepository amostrasExigidasRepository;
	@Autowired
	private AmostrasRealizadasRepository amostrasRealizadasRepository;

	protected void gerarExcessaoAmostrasExigidasExistentes(Integer dtInicio) {

		Integer total = amostrasExigidasRepository.contarAmostrasExigidasPorDtInicio(dtInicio);
		
		if(total > 0) {
			throw new MsgGenericaPersonalizadaException(String.format("Já existem amostras exigidas para a data de início %d.", dtInicio));
		}
	}

	protected void gerarExcessaoAmostrasRealizadasExistentes(Integer refAmostras) {

		Integer total = amostrasRealizadasRepository.contarAmostrasRealizadasPorRefAmostras(refAmostras);
		
		if(total > 0) {
			throw new MsgGenericaPersonalizadaException(String.format("Já existem amostras realizadas para a data de início %d.", refAmostras));
		}
	}

	protected void gerarExcessaoAmostrasExigidasInexistentes(Integer dtInicio) {
		
		Integer total = amostrasExigidasRepository.contarAmostrasExigidasPorDtInicio(dtInicio);
		
		if(total == 0) {
			throw new MsgGenericaPersonalizadaException(String.format("Não existem amostras exigidas para a data de início %d.", dtInicio));
		}
	}

	protected void gerarExcessaoAmostrasRealizadasInexistentes(Integer refAmostras) {

		Integer total = amostrasRealizadasRepository.contarAmostrasRealizadasPorRefAmostras(refAmostras);
		
		if(total == 0) {
			throw new MsgGenericaPersonalizadaException(String.format("Não existem amostras realizadas para a data de início %d.", refAmostras));
		}
	}
}

package moduloFaturamento.validacoes.qualidadeDaAgua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class AmostrasRealizadasValidacoes extends QualidadeDaAguaValidacoes {

	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	public void gerenciarValidacaoPesquisaAmostrasRealizadas(Integer refAmostras) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refAmostras);
	}

	public void gerenciarValidacaoGravacaoAmostrasRealizadas(Integer refAmostras) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refAmostras);
		super.gerarExcessaoAmostrasRealizadasExistentes(refAmostras);
	}

	public void gerenciarValidacaoExclusaoAmostrasRealizadas(Integer refAmostras) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refAmostras);
		super.gerarExcessaoAmostrasRealizadasInexistentes(refAmostras);
	}
}

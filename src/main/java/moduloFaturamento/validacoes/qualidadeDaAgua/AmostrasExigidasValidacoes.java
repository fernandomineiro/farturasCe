package moduloFaturamento.validacoes.qualidadeDaAgua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class AmostrasExigidasValidacoes extends QualidadeDaAguaValidacoes {

	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;
	
	public void gerenciarValidacaoGravacaoAmostrasExigidas(Integer dtInicio) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(dtInicio);
		super.gerarExcessaoAmostrasExigidasExistentes(dtInicio);
	}

	public void gerenciarValidacaoExclusaoAmostrasExigidas(Integer dtInicio) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(dtInicio);
		super.gerarExcessaoAmostrasExigidasInexistentes(dtInicio);
	}
}

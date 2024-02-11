package moduloFaturamento.validacoes.mensagemFatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class MensagemFaturaSiscomValidacao extends MensagemFaturaValidacao {
	
	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	public void gerenciarValidacaoParaBuscar(Short ciclo, Short cdCidade, Integer refFatura) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);		
		super.gerarExcessaoMensagemInexistenteParaCicloCidadeEReferencia(ciclo, cdCidade, refFatura);
	}
	
	public void gerenciarValidacaoParaCadastrar(Short ciclo, Short cdCidade, Integer refFatura) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
		super.gerarExcessaoCicloCronogramaFechado(ciclo, cdCidade, refFatura);
		super.gerarExcessaoMensagemDuplicadaParaCicloCidadeEReferencia(ciclo, cdCidade, refFatura);
	}
	
	public void gerenciarValidacaoParaAtualizar(Short ciclo, Short cdCidade, Integer refFatura) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
		super.gerarExcessaoCicloCronogramaFechado(ciclo, cdCidade, refFatura);
		super.gerarExcessaoMensagemInexistenteParaCicloCidadeEReferencia(ciclo, cdCidade, refFatura);
	}
	
	public void gerenciarValidacaoParaRemover(Short ciclo, Short cdCidade, Integer refFatura) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
		super.gerarExcessaoCicloCronogramaFechado(ciclo, cdCidade, refFatura);
	}
}

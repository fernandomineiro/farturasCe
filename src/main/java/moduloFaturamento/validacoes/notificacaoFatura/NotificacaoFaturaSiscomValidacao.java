package moduloFaturamento.validacoes.notificacaoFatura;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class NotificacaoFaturaSiscomValidacao extends NotificacaoFaturaValidacao {
	
	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	public void gerenciarValidacaoBuscarNotificao(Long idNotificacao) {
		
		super.gerarExcessaoNotificacaoInexistente(idNotificacao);
	}
	
	public void gerenciarValidacaoCadastramentoNotificao(Integer refFatura, List<Integer> matriculas) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refFatura);
		super.gerarExcessaoMatriculasEmCicloFechado(refFatura, matriculas);
	}

	public void gerenciarValidacaoAlteracaoNotificaoExistente(Long idNotificacao) {

		super.gerarExcessaoNotificacaoInexistente(idNotificacao);
		super.gerarExcessaoCicloCronogramaFechado(idNotificacao);
	}

	public void gerenciarValidacaoRemocaoNotificao(Long idNotificacao) {

		super.gerarExcessaoNotificacaoInexistente(idNotificacao);
	}
	
	public void gerenciarValidacaoReferenciaCritica(Integer refCronograma) {

		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(refCronograma);
	}
}

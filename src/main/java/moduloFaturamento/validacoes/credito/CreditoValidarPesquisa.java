package moduloFaturamento.validacoes.credito;

import org.springframework.stereotype.Service;

@Service
public class CreditoValidarPesquisa extends CreditoValidacao{

  public void gerenciarValidacaoPesquisaExigida(Integer matricula) {
		super.gerarExcessaoSeMatriculaExiste(matricula);
	}

  public void gerenciaValidacaoBuscarCreditoLancado(Integer matricula, short credito){
    super.gerarExcessaoSeExiteCreditoLancado(matricula, credito);
  }
}

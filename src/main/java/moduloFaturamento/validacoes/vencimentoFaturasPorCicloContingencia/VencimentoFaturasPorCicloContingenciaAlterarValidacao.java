package moduloFaturamento.validacoes.vencimentoFaturasPorCicloContingencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoAlterarFilterDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoVerificarFilterDTO;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class VencimentoFaturasPorCicloContingenciaAlterarValidacao extends VencimentoFaturasPorCicloContingenciaValidacoes {

	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	public void gerenciarConsultaParaAlteracao(FaturasVencimentoVerificarFilterDTO dto) {
		
		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(dto.getReferencia());
		commonUtilValidacoes.gerarExcessaoDataInvalidaFormatoBR(dto.getNovoVencimento());
		
		//super.gerarExcecaoLimiteNovoVencimentoAnteriorDataAtual(dto.getNovoVencimento());
		super.gerarExcecaoLimiteMaximoNovoVencimento(dto.getNovoVencimento());
		super.gerarExcecaoNovoVencimentoAnteriorAReferencia(dto.getReferencia(), dto.getNovoVencimento());
		super.gerarExcecaoFasesFaturaAceitas(dto.getFases());
	}
	
	public void gerenciarParametrosParaAlteracao(FaturasVencimentoAlterarFilterDTO dto) {
		
		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(dto.getReferencia());
		commonUtilValidacoes.gerarExcessaoDataInvalidaFormatoBR(dto.getNovoVencimento());
		
		//super.gerarExcecaoLimiteNovoVencimentoAnteriorDataAtual(dto.getNovoVencimento());
		super.gerarExcecaoLimiteMaximoNovoVencimento(dto.getNovoVencimento());
		super.gerarExcecaoNovoVencimentoAnteriorAReferencia(dto.getReferencia(), dto.getNovoVencimento());
		super.gerarExcecaoFasesFaturaAceitas(dto.getFases());
	}

}

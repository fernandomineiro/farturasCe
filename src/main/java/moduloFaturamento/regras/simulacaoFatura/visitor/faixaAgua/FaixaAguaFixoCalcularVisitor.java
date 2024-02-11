package moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.calculoFaturaDetalhada.CalculoFaturaDetalhadaAux;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class FaixaAguaFixoCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		FaturaFaixaProcessamento faixaAtingida = null;

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {

			if (faixa.getFaixa() == 1 || !faixa.getMetrosUsadosFaixaCorrente().equals(BigDecimal.ZERO)) {

				faixaAtingida = faixa;
			}
		}

		if (faixaAtingida != null) {

			faixaAtingida.setAguaParcelaFixa(
					CalculoFaturaDetalhadaAux.proporcao(CalculoFaturaDetalhadaAux.MES_COMERCIAL, visitee.getDiasVenda(), faixaAtingida.getAguaTarifaFixa()));
		}
	}
}

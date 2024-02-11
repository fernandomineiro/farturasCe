package moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoColetado;

import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class FaixaEsgotoColetadoVariavelCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {

			faixa.setEsgotoAtivoParcelaVariavel(
					faixa.getMetrosUsadosFaixaCorrente().multiply(faixa.getEsgotoAtivoTarifaVariavel()).setScale(2, RoundingMode.HALF_EVEN));
		}
	}

}

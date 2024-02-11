package moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua;

import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class FaixaAguaVariavelCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {

			faixa.setAguaParcelaVariavel(faixa.getMetrosUsadosFaixaCorrente().multiply(faixa.getAguaTarifaVariavel()).setScale(2, RoundingMode.HALF_EVEN));
		}
	}

}

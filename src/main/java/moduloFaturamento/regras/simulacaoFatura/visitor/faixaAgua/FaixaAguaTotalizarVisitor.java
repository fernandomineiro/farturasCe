package moduloFaturamento.regras.simulacaoFatura.visitor.faixaAgua;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class FaixaAguaTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		visitee.getFaixas().stream().forEach(faixa -> {

			BigDecimal aguaTotal = faixa.getAguaParcelaVariavel().add(faixa.getAguaParcelaFixa());
			faixa.setAguaTotal(aguaTotal);
		});
	}

}

package moduloFaturamento.regras.simulacaoFatura.visitor.detalhe;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class DetalheAplicarConsumoVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		BigDecimal consumo = visitee.getConsumo();

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {

			if (consumo.compareTo(faixa.getMetrosPorFaixaCorrente()) > 0) {

				faixa.setMetrosUsadosFaixaCorrente(faixa.getMetrosPorFaixaCorrente());
				consumo = consumo.subtract(faixa.getMetrosUsadosFaixaCorrente());
			} else {

				faixa.setMetrosUsadosFaixaCorrente(consumo);
				consumo = BigDecimal.ZERO;
			}

			if (consumo.compareTo(BigDecimal.ZERO) <= 0) {

				break;
			}
		}
	}

}

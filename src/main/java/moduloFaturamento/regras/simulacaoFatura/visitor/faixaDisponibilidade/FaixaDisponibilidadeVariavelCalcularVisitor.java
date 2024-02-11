package moduloFaturamento.regras.simulacaoFatura.visitor.faixaDisponibilidade;

import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class FaixaDisponibilidadeVariavelCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {

			faixa.setDisponibilidadeParcelaVariavel(
					faixa.getMetrosUsadosFaixaCorrente().multiply(faixa.getDisponibilidadeTarifaVariavel()).setScale(2, RoundingMode.HALF_EVEN));
		}
	}

}

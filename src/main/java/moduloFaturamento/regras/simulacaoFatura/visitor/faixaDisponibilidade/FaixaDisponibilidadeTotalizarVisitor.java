package moduloFaturamento.regras.simulacaoFatura.visitor.faixaDisponibilidade;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class FaixaDisponibilidadeTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		visitee.getFaixas().stream().forEach(faixa -> {

			BigDecimal disponibilidadeTotal = faixa.getDisponibilidadeParcelaVariavel().add(faixa.getDisponibilidadeParcelaFixa());
			faixa.setDisponibilidadeTotal(disponibilidadeTotal);
		});
	}

}

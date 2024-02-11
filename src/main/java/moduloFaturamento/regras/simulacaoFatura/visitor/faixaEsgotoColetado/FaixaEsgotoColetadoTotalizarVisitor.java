package moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoColetado;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class FaixaEsgotoColetadoTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		visitee.getFaixas().stream().forEach(faixa -> {

			BigDecimal esgotoAtivoTotal = faixa.getEsgotoAtivoParcelaVariavel().add(faixa.getEsgotoAtivoParcelaFixa());
			faixa.setEsgotoAtivoTotal(esgotoAtivoTotal);
		});
	}

}

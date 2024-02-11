package moduloFaturamento.regras.simulacaoFatura.visitor.detalhe;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class DetalheEsgotoTratadoTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		BigDecimal totalEsgotoTratado = visitee.getFaixas().stream().map(faixa -> {

			return faixa.getEsgotoTratadoTotal();
		}).reduce(BigDecimal.ZERO, (total, totalFaixa) -> {

			return total.add(totalFaixa);
		});
		
		visitee.setTotalEsgotoTratado(totalEsgotoTratado);
	}

}

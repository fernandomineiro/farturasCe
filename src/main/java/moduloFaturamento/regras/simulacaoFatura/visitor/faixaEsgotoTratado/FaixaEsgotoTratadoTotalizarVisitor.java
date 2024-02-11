package moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoTratado;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class FaixaEsgotoTratadoTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		visitee.getFaixas().stream().forEach(faixa -> {

			BigDecimal esgotoTratadoTotal = faixa.getEsgotoTratadoParcelaVariavel().add(faixa.getEsgotoTratadoParcelaFixa());
			faixa.setEsgotoTratadoTotal(esgotoTratadoTotal);
		});
	}
}

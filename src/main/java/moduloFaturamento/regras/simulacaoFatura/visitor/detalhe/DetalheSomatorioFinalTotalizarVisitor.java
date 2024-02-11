package moduloFaturamento.regras.simulacaoFatura.visitor.detalhe;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class DetalheSomatorioFinalTotalizarVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		BigDecimal totalAgua = visitee.getTotalAgua();
		BigDecimal totalEsgotoAtivo = visitee.getTotalEsgotoAtivo();
		BigDecimal totalEsgotoTratado = visitee.getTotalEsgotoTratado();
		BigDecimal totalDisponibilidade = visitee.getTotalDisponibilidade();
		
		BigDecimal total = totalAgua.add(totalEsgotoAtivo).add(totalEsgotoTratado).add(totalDisponibilidade);
				
		visitee.setTotalFatura(total);
	}

}

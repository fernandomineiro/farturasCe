package moduloFaturamento.regras.simulacaoFatura.visitor.detalhe;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.calculoFaturaDetalhada.CalculoFaturaDetalhadaAux;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class DetalheMetragemDisponivelCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento>{
	
	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		BigDecimal limiteFaixaAnterior = BigDecimal.ZERO;

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {
			
			if(BigDecimal.TEN.multiply(BigDecimal.TEN).compareTo(faixa.getMetrosPorFaixaPadrao()) < 0) {
				faixa.setMetrosPorFaixaCorrente(faixa.getMetrosPorFaixaPadrao());
			}
			
			BigDecimal metrosFaixaCorrente = faixa.getMetrosPorFaixaPadrao().subtract(limiteFaixaAnterior);

			metrosFaixaCorrente = CalculoFaturaDetalhadaAux.proporcao(CalculoFaturaDetalhadaAux.MES_COMERCIAL, visitee.getDiasVenda(),
					metrosFaixaCorrente);

			faixa.setMetrosPorFaixaCorrente(metrosFaixaCorrente);

			limiteFaixaAnterior = faixa.getMetrosPorFaixaPadrao();
		}
	}
}

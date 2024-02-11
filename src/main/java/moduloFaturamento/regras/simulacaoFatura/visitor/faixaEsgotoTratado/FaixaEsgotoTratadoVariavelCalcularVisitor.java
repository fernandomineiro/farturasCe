package moduloFaturamento.regras.simulacaoFatura.visitor.faixaEsgotoTratado;

import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import moduloFaturamento.designPattern.visitor.CesanVisitor;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaFaixaProcessamento;

@Service
public class FaixaEsgotoTratadoVariavelCalcularVisitor implements CesanVisitor<FaturaDetalheProcessamento> {

	@Override
	public void visit(FaturaDetalheProcessamento visitee) {

		for (FaturaFaixaProcessamento faixa : visitee.getFaixas()) {
			
			faixa.setEsgotoTratadoParcelaVariavel(faixa.getMetrosUsadosFaixaCorrente().multiply(faixa.getEsgotoTratadoTarifaVariavel()).setScale(2,
					RoundingMode.HALF_EVEN));
		}
	}

}

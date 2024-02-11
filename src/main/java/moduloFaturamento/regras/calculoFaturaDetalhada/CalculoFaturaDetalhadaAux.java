package moduloFaturamento.regras.calculoFaturaDetalhada;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoFaturaDetalhadaAux {

	private CalculoFaturaDetalhadaAux() {

	}

	public static final BigDecimal MES_COMERCIAL = BigDecimal.valueOf(30).setScale(2);
	public static final BigDecimal MESES_ANO = BigDecimal.valueOf(12).setScale(2);

	public static BigDecimal proporcao(BigDecimal base, BigDecimal proporcao, BigDecimal valor) {

		return valor.setScale(4).divide(base, RoundingMode.HALF_EVEN).multiply(proporcao).setScale(2, RoundingMode.HALF_EVEN);
	}
}

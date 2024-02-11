package moduloFaturamento.regras.fatura;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FaturaSimulacaoRegra extends FaturaRegra {

}

class FaturaDetalhe {

	List<FaturaFaixa> faixas;

	BigDecimal total;
}


class FaturaFaixa {
	
	BigDecimal consumo;
	
}



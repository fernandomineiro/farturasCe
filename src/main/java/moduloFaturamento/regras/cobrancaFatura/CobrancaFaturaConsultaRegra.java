package moduloFaturamento.regras.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaConsultaRegra extends CobrancaFaturaRegra {

    public void validar(Short codigoServico){
        super.impedirQualquerModificacaoCobrancaComServicoJurosEMultas(codigoServico);
    }
}

package moduloFaturamento.regras.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaRemocaoRegra extends CobrancaFaturaRegra {

    public void validarDadosEntidadeExistente(Short codigoServico, Integer referenciaFaturada){
        super.impedirQualquerModificacaoCobrancaComServicoJurosEMultas(codigoServico);
        super.impedirQualquerModificacaoCobrancaComReferenciaFaturadaCadastrada(referenciaFaturada);
    }
}

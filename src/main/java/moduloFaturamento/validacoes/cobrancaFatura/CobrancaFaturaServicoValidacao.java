package moduloFaturamento.validacoes.cobrancaFatura;

import org.springframework.stereotype.Service;

@Service
public class CobrancaFaturaServicoValidacao extends CobrancaFaturaValidacao{

    public void impedirQualquerModificacaoCobrancaParaDeterminadoServico(Short cdServico){
        super.gerarExcecaoQualquerModificacaoCobrancaComServicoJurosEMultas(cdServico);
    }
}

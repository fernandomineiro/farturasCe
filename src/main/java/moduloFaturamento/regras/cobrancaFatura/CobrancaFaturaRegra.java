package moduloFaturamento.regras.cobrancaFatura;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.repository.CobrancaServicoFaturaRepository;
import moduloFaturamento.validacoes.cobrancaFatura.CobrancaFaturaServicoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Objects;

abstract class CobrancaFaturaRegra {
    @Autowired
    private CobrancaServicoFaturaRepository cobrancaServicoFaturaRepository;
    @Autowired
    private CobrancaFaturaServicoValidacao cobrancaFaturaServicoValidacao;

    protected void impedirQualquerModificacaoCobrancaComServicoJurosEMultas(Short codigoServico){
        this.cobrancaFaturaServicoValidacao.impedirQualquerModificacaoCobrancaParaDeterminadoServico(codigoServico);
    }

    protected void impedirQualquerModificacaoCobrancaComReferenciaFaturadaCadastrada(Integer referenciaFaturada){
        if (Objects.isNull(referenciaFaturada) || !referenciaFaturada.equals(0)) {
            throw new MsgGenericaPersonalizadaException("Não é possível modificar ou remover cobrança com referência faturada", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected Integer retonarValorCdCobrancaParaCadastrarNovoRegistro(Integer matriculaImovel){
        Short maiorCodigoCobrancaRegistradoParaMatriculaImovel = this.cobrancaServicoFaturaRepository.buscarMaiorCdCobrancaPorMatriculaImovel(matriculaImovel);
        return (maiorCodigoCobrancaRegistradoParaMatriculaImovel != null ? maiorCodigoCobrancaRegistradoParaMatriculaImovel + 1 : 1);
    }
}

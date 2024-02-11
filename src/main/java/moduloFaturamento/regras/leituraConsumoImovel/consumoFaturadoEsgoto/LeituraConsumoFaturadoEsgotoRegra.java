package moduloFaturamento.regras.leituraConsumoImovel.consumoFaturadoEsgoto;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public abstract class LeituraConsumoFaturadoEsgotoRegra {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private LeituraRepository leituraRepository;

    protected List<Integer> retornarMatriculasSecundariaDeUmaMatriculaMae(Integer matricula){
        return this.imovelRepository.retornarMatriculasSecundariasMatriculaMae(matricula);
    }

    protected BigDecimal retornarSomatorioConsumoFaturadoDeMatriculasSecundarias(List<Integer> matriculas, Integer refFatura){
        BigDecimal somatorioConsumoFaturadoEsgoto = BigDecimal.ZERO;

        for (Integer matriculaSecundaria : matriculas){
            Leitura leitura = this.leituraRepository.buscarLeituraExistentePorMatriculaEReferencia(matriculaSecundaria, refFatura);
            if(leitura != null){
                BigDecimal valorConsumoFaturadoDaLeitura = Optional.ofNullable(leitura.getConsumoFaturarEsgoto()).orElse(BigDecimal.ZERO);
                somatorioConsumoFaturadoEsgoto = somatorioConsumoFaturadoEsgoto.add(valorConsumoFaturadoDaLeitura);
            }
        }

        return somatorioConsumoFaturadoEsgoto;
    }

    protected BigDecimal retornarNovoConsumoFaturadoEsgotoParaMatriculaMae(BigDecimal consumoFaturadoCalculado, BigDecimal consumoFaturadoInformadoPeloCliente){
            return consumoFaturadoInformadoPeloCliente.add(consumoFaturadoCalculado);
    }

    protected boolean retornarSeHouveAlteracaoMatriculaSecundaria(List<Integer> matriculasSecundarias, Integer refFatura){
        boolean houveAlteracao = false;
        for (Integer matriculaMicro : matriculasSecundarias) {
            Leitura leitura = this.leituraRepository.buscarLeituraExistentePorMatriculaEReferencia(matriculaMicro, refFatura);
            if(leitura != null){
                if(leitura.getConsumoFaturadoEsgotoSecundarioModificado() != null && leitura.getConsumoFaturadoEsgotoSecundarioModificado().equals("S")){
                    houveAlteracao = true;
                }
            }
        }
        return houveAlteracao;
    }


}

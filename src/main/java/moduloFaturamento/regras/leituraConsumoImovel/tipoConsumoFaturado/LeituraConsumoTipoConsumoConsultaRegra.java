package moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LeituraConsumoTipoConsumoConsultaRegra extends LeituraConsumoTipoConsumoRegra {

    public Short consultarTipoConsumo(Integer matricula, Short idTipoConsumoFaturado, LocalDate referenciaAtual, Integer consumoMedido, Short diasDeVenda, BigDecimal mediaDiaria){
        super.gerarExcecaoReferenciaMaiorQueOutubro2021ComTipoConsumoMinimo(idTipoConsumoFaturado, referenciaAtual);

        Short novoTipoRegraUm = super.obterNovoTipoConsumoDeFaturamentoAteSetembro2021ConsiderandoConsumoMedidoEValorMinimoMensal(matricula, idTipoConsumoFaturado, referenciaAtual, consumoMedido, diasDeVenda);
        Short novoTipoRegraDois = super.obterNovoTipoConsumoDeFaturamentoAteSetembro2021ConsiderandoConsumoMedioMensalEValorMinimoMensal(matricula, idTipoConsumoFaturado, referenciaAtual, mediaDiaria, diasDeVenda);

        if(novoTipoRegraUm != null){
            return novoTipoRegraUm;
        }else if(novoTipoRegraDois != null){
            return novoTipoRegraDois;
        }else{
            return idTipoConsumoFaturado;
        }
    }
}

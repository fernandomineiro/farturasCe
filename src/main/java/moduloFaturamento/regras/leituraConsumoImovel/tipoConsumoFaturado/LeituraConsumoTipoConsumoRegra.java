package moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado;

import moduloFaturamento.regras.leituraConsumoImovel.LeituraConsumoRegra;
import moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.spec.TipoConsumoFaturadoEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public abstract class LeituraConsumoTipoConsumoRegra extends LeituraConsumoRegra {

    Short obterNovoTipoConsumoDeFaturamentoAteSetembro2021ConsiderandoConsumoMedidoEValorMinimoMensal(Integer matricula, Short idTipoConsumoFaturado, LocalDate referenciaAtual, Integer consumoMedido, Short diasVenda){
        LocalDate referenciaLimiteParaAceitarFaturamento = LocalDate.of(2021,9,1);

        if(referenciaAtual.isBefore(referenciaLimiteParaAceitarFaturamento)){
            Integer consumoMinimoMensal = super.obterValorMinimoGrupoConsumoDoImovel(matricula, diasVenda);

            if(consumoMedido < consumoMinimoMensal){
                return TipoConsumoFaturadoEnum.MINIMO.getTipo();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    Short obterNovoTipoConsumoDeFaturamentoAteSetembro2021ConsiderandoConsumoMedioMensalEValorMinimoMensal(Integer matricula, Short idTipoConsumoFaturado, LocalDate referenciaAtual, BigDecimal mediaDiaria, Short diasVenda){
        LocalDate referenciaLimiteParaAceitarFaturamento = LocalDate.of(2021,9,1);

        if(referenciaAtual.isBefore(referenciaLimiteParaAceitarFaturamento) && idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.MEDIA.getTipo())){
            BigDecimal mediaMensal = super.obterMediaMensalLeitura(mediaDiaria, diasVenda);
            Integer consumoMinimoMensal = super.obterValorMinimoGrupoConsumoDoImovel(matricula, diasVenda);

            if(mediaMensal.compareTo(new BigDecimal(consumoMinimoMensal)) < 0){
                return TipoConsumoFaturadoEnum.MINIMO.getTipo();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}

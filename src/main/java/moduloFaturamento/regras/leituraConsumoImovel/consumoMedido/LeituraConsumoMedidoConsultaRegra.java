package moduloFaturamento.regras.leituraConsumoImovel.consumoMedido;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class LeituraConsumoMedidoConsultaRegra extends LeituraConsumoMedidoRegra {

    public Integer consultaConsumoMedido(Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada){
        return super.retornarValorCalculadoDoConsumoMedido(matricula, dataReferencia, valorLeituraInformada);
    }

    public boolean consultaFlagAbrirPopupConsumoMedidoForaDaFaixa(Integer consumoMedido, BigDecimal faixaMaximaConsumo, BigDecimal faixaMinimaConsumo){
        return super.retornarSeConsumoMedidoEstaForaDaFaixaConsumoParaAnalise(consumoMedido, faixaMaximaConsumo, faixaMinimaConsumo);
    }

    public boolean retornarSeDeveSerAbertoUmPopupQuandoConsumoMedidoEhModificadoEmMatriculaMicro(Integer matricula, LocalDate dataReferencia, Integer valorConsumoMedido){
        return super.retornarSeDeveSerAbertoUmPopupQuandoConsumoMedidoEhModificadoEmMatriculaMicro(matricula, dataReferencia, valorConsumoMedido);
    }

    public short retornarCodigoAnormalidadeSeHouver(Integer valorConsumoMedido, BigDecimal faixaMaximaConsumo, BigDecimal faixaMinimaConsumo){
        return super.retornarCodigoAnormalidadeSeConsumoMedidoEstaForaDaFaixaConsumoParaAnalise(valorConsumoMedido, faixaMaximaConsumo, faixaMinimaConsumo);
    }


}

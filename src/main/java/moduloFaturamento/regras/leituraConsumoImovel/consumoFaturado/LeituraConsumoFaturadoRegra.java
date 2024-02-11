package moduloFaturamento.regras.leituraConsumoImovel.consumoFaturado;

import moduloFaturamento.regras.leituraConsumoImovel.LeituraConsumoRegra;
import moduloFaturamento.regras.leituraConsumoImovel.consumoMedido.LeituraConsumoMedidoConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.mediaDiaria.LeituraConsumoMediaDiariaConsultaRegra;
import moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.spec.TipoConsumoFaturadoEnum;
import moduloFaturamento.repository.LeituraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public abstract class LeituraConsumoFaturadoRegra extends LeituraConsumoRegra {

   @Autowired
   private LeituraConsumoMedidoConsultaRegra leituraConsumoMedidoConsultaRegra;
   @Autowired
   private LeituraConsumoMediaDiariaConsultaRegra leituraConsumoMediaDiariaConsultaRegra;
   @Autowired
   private LeituraRepository leituraRepository;


   protected BigDecimal obterValorCalculadoConsumoFaturado(Short idTipoConsumoFaturado, Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada, Short diasDeVenda, BigDecimal mediaDiaria){

      BigDecimal consumoFaturarAgua = BigDecimal.ZERO;

      if(idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.MEDIDO.getTipo())){
         return new BigDecimal(this.leituraConsumoMedidoConsultaRegra.consultaConsumoMedido(matricula, dataReferencia, valorLeituraInformada));

      }else if(idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.MINIMO.getTipo())){
         return new BigDecimal(super.obterValorMinimoGrupoConsumoDoImovel(matricula, diasDeVenda));

      }else if(idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.MEDIA.getTipo())){
         return super.obterMediaMensalLeitura(mediaDiaria, diasDeVenda);

      }else if(idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.BASICO.getTipo())){
         return new BigDecimal(super.obterValorBasicogrupoConsumoDoImovel(matricula));

      }else{
         return consumoFaturarAgua;
      }
   }
}
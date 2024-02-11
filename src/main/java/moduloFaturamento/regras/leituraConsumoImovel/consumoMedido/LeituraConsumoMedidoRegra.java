package moduloFaturamento.regras.leituraConsumoImovel.consumoMedido;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.common.HidrometroInstalado;
import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.regras.leituraConsumoImovel.LeituraConsumoRegra;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.service.common.HidrometroInstaladoService;
import moduloFaturamento.service.common.HidrometroRetiradoService;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public abstract class LeituraConsumoMedidoRegra extends LeituraConsumoRegra {

    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private HidrometroInstaladoService hidrometroInstaladoService;
    @Autowired
    private HidrometroRetiradoService hidrometroRetiradoService;

    protected Integer retornarValorCalculadoDoConsumoMedido(Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada){
        boolean houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior = this.retornarSeHouveMovimentacaoHidrometroDentroDoIntervaloLeituraAtualEAnterior(matricula, dataReferencia);

        if(houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior){
            return this.retornarValorCalculadoConsumoConsiderandoLeituraDaHidrometria(matricula, dataReferencia, valorLeituraInformada);
        }else{
            return this.retornarValorCalculadoConsumoDesconsiderandoLeituraDaHidrometria(matricula, dataReferencia, valorLeituraInformada);
        }
    }

    protected boolean retornarSeDeveSerAbertoUmPopupQuandoConsumoMedidoEhModificadoEmMatriculaMicro(Integer matricula, LocalDate dataReferencia, Integer valorConsumoMedido){
        Leitura leituraAntesDeQualquerModificacao = this.leituraRepository.buscarLeituraExistentePorMatriculaEReferencia(matricula, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferencia));
        Integer consumoMedidoAntesModificacao = Optional.ofNullable(leituraAntesDeQualquerModificacao).map(Leitura::getMedido).orElse(0);
        return !valorConsumoMedido.equals(consumoMedidoAntesModificacao);
    }


    protected boolean retornarSeConsumoMedidoEstaForaDaFaixaConsumoParaAnalise(Integer valorConsumoMedido, BigDecimal faixaMaximaConsumo, BigDecimal faixaMinimaConsumo) {
        boolean foraDaFaixaDeConsumo = false;
        if (new BigDecimal(valorConsumoMedido).compareTo(faixaMinimaConsumo) < 0) {
            return true;
        }

        if (new BigDecimal(valorConsumoMedido).compareTo(faixaMaximaConsumo) > 0) {
            return true;
        }

        return foraDaFaixaDeConsumo;
    }

    protected Short retornarCodigoAnormalidadeSeConsumoMedidoEstaForaDaFaixaConsumoParaAnalise(Integer valorConsumoMedido, BigDecimal faixaMaximaConsumo, BigDecimal faixaMinimaConsumo) {
        final short SEM_ANORMALIDADE = -1;
        final short ANORMALIDADE_DESCRESCIMO = 1;
        final short ANORMALIDADE_ACRESCIMO = 2;

        if (new BigDecimal(valorConsumoMedido).compareTo(faixaMinimaConsumo) < 0) {
            return ANORMALIDADE_DESCRESCIMO;
        }

        if (new BigDecimal(valorConsumoMedido).compareTo(faixaMaximaConsumo) > 0) {
            return ANORMALIDADE_ACRESCIMO;
        }

        return SEM_ANORMALIDADE;
    }


    private boolean retornarSeHouveMovimentacaoHidrometroDentroDoIntervaloLeituraAtualEAnterior(Integer matricula, LocalDate dataReferencia){
        HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoService.retornarEntidadeUltimoHidrometroInstaladoDaMatriculaImovel(matricula);
        HidrometroRetirado hidrometroRetirado = this.hidrometroRetiradoService.retornarEntidadeUltimoHidrometroRetiradoDaMatriculaImovel(matricula);

        LocalDate intervaloMinimo = dataReferencia.minusMonths(1);
        boolean houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior = true;

        if(hidrometroRetirado != null){
            LocalDate dataLeituraHidrometroRetirado = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroRetirado.getIdHidrometroRetirado().getDataRetirada());
            if(dataLeituraHidrometroRetirado.isBefore(intervaloMinimo) || dataLeituraHidrometroRetirado.isAfter(dataReferencia)){
                houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior = false;
            }
        }

        if(hidrometroInstalado != null){
            LocalDate dataLeituraHidrometroInstalado = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroInstalado.getDataInstalacao());
            if(dataLeituraHidrometroInstalado.isBefore(intervaloMinimo) || dataLeituraHidrometroInstalado.isAfter(dataReferencia)){
                houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior = false;
            }
        }

        return houveMovimentacaoDentroDoIntervaloLeituraAtualEAnterior;

    }

    private Integer retornarValorCalculadoConsumoDesconsiderandoLeituraDaHidrometria(Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada){
        Optional<Leitura> leituraOptional = super.obterLeituraDaReferenciaAnteriorADataInformada(matricula, dataReferencia);

        Integer valorLeituraReferenciaAnterior = 0;
        String leituraVirtual = "N";

        if(leituraOptional.isPresent()){
            valorLeituraReferenciaAnterior = leituraOptional.get().getLeitura();
            leituraVirtual = (leituraOptional.get().getLeituraCriada() != null ? leituraOptional.get().getLeituraCriada() : "N");
        }

        if(valorLeituraInformada > valorLeituraReferenciaAnterior){
            return valorLeituraInformada - valorLeituraReferenciaAnterior;
        }else if(valorLeituraInformada < valorLeituraReferenciaAnterior && leituraVirtual.equals("S")){
            return 0;
        }else{
            return 0;
        }
    }

    private Integer retornarValorCalculadoConsumoConsiderandoLeituraDaHidrometria(Integer matricula, LocalDate dataReferencia, Integer valorLeituraInformada){
        HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoService.retornarEntidadeUltimoHidrometroInstaladoDaMatriculaImovel(matricula);
        HidrometroRetirado hidrometroRetirado = this.hidrometroRetiradoService.retornarEntidadeUltimoHidrometroRetiradoDaMatriculaImovel(matricula);
        Optional<Leitura> leituraOptional = super.obterLeituraDaReferenciaAnteriorADataInformada(matricula, dataReferencia);

        Integer valorLeituraReferenciaAnterior = 0;

        Integer leituraHidrometroRetirado = hidrometroRetirado.getLeituraRetirada();
        Integer leituraHidrometroInstalado = hidrometroInstalado.getLeituraInstalacao();

        if(leituraOptional.isPresent()){
            valorLeituraReferenciaAnterior = leituraOptional.get().getLeitura();
        }

        return (leituraHidrometroRetirado - valorLeituraReferenciaAnterior) + (valorLeituraInformada - leituraHidrometroInstalado);
    }
}

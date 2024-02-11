package moduloFaturamento.regras.leituraConsumoImovel;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IdLeitura;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.regras.leituraConsumoImovel.tipoConsumoFaturado.spec.TipoConsumoFaturadoEnum;
import moduloFaturamento.repository.LeituraRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public abstract class LeituraConsumoRegra {

    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private ImovelRepository imovelRepository;

    protected void gerarExcecaoReferenciaMaiorQueOutubro2021ComTipoConsumoMinimo(Short idTipoConsumoFaturado, LocalDate referencia){
        LocalDate dataLimiteParaAceitarTipoConsumoFaturadoMinino = LocalDate.of(2021,10,1);

        if(referencia.isAfter(dataLimiteParaAceitarTipoConsumoFaturadoMinino) && idTipoConsumoFaturado.equals(TipoConsumoFaturadoEnum.MINIMO.getTipo())){
            throw new MsgGenericaPersonalizadaException("Para os faturamentos realizados a partir da referência 10/2021 não será permitido utilizar o tipo de consumo faturado mínimo", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected Optional<Leitura> obterLeituraDaReferenciaAnteriorADataInformada(Integer matricula, LocalDate dataReferencia) {
        Integer refFatura = ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferencia.minusMonths(1));
        IdLeitura id = new IdLeitura(matricula, refFatura);

        return this.leituraRepository.findByIdLeituraFaturamento(id);
    }

    protected Integer obterValorMinimoGrupoConsumoDoImovel(Integer matricula, Short diasVenda){
        Integer valorMinimoPara30Dias = this.imovelRepository.buscarValorMinimoAtravesDoGrupoDeConsumoDoImovel(matricula);
        if(diasVenda.equals(30)){
            return valorMinimoPara30Dias;
        }else{
            return (diasVenda * valorMinimoPara30Dias) / 30;
        }
    }

    protected Integer obterValorBasicogrupoConsumoDoImovel(Integer matricula){
        return this.imovelRepository.buscarValorBasicoAtravesDoGrupoDeConsumoDoImovel(matricula);
    }

    protected BigDecimal obterMediaMensalLeitura(BigDecimal mediaDiaria, Short diasVenda){
        return mediaDiaria.multiply(new BigDecimal(diasVenda));
    }
}

package moduloFaturamento.regras.cronogramaFaturamento.validacoes;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

abstract class CronogramaFaturamentoValidacaoRegra {
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private FaturaRepository faturamentoImovelRepository;
    protected String sufixoExcecaoCronogramaAnteriorInexistente = "";


    protected void gerarExcecaoCronogramaAnteriorInexistente(Short cdCidade, Short ciclo, LocalDate dataReferenciaAnterior){
        IdCronogramaFatura idCronogramaFatura = new IdCronogramaFatura(cdCidade, ciclo, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior));
        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.findByIdCronogramaFatura(idCronogramaFatura);
        if (cronogramaFatura == null){
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/yyyy");
            String msg = "Não foi encontrado registro de referência do mês anterior "+formatador.format(dataReferenciaAnterior) + sufixoExcecaoCronogramaAnteriorInexistente;
            throw new MsgGenericaPersonalizadaException(msg);
        }
    }


    protected void gerarExcecaoCronogramaAnteriorInexistente(Short ciclo, LocalDate dataReferenciaAnterior){
        Integer referenciaMesAnterior = ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior);
        List<CronogramaFatura> cronogramaFaturaList = this.cronogramaFaturaRepository.findByIdCronogramaFatura_CicloAndIdCronogramaFatura_RefCronograma(ciclo, referenciaMesAnterior);
        if (cronogramaFaturaList == null){
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/yyyy");
            String msg = "Não foi encontrado cronograma do mês anterior "+formatador.format(dataReferenciaAnterior) + "";
            throw new MsgGenericaPersonalizadaException(msg);
        }
    }

    protected void gerarExcecaoRemoverCronogramaQueEstaEmFaseDiferenteGerarEspelho(Short faseCronograma){
        if(!faseCronograma.equals(CronogramaFaturamentoFaseEnum.GERAR_ESPELHO.getValor())){
            throw new MsgGenericaPersonalizadaException("Não é possível remover cronograma que está em fase diferente de 'Gerar Espelho'", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcecaoEditarCronogramaQueEstaFechado(Short faseCronograma){
        if(faseCronograma.equals(CronogramaFaturamentoFaseEnum.ENCERRADO.getValor())){
            throw new MsgGenericaPersonalizadaException("Não é possível editar cronograma que está em fase  'Encerrado'", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    protected void gerarExcecaoRemoverCronogramaPoisFaturaExistenteParaCronograma(Short cdCidade, Short ciclo, Integer dataReferencia ){
        Optional<List<Fatura>> faturasVinculadasAoCronograma = this.faturamentoImovelRepository.findByCdCidadeAndCicloAndIdFatura_RefFatura(cdCidade, ciclo, dataReferencia);

        if(faturasVinculadasAoCronograma.isPresent()){
            throw new MsgGenericaPersonalizadaException("Não é possivel remover esse cronograma, pois existem faturas para esse cronograma", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

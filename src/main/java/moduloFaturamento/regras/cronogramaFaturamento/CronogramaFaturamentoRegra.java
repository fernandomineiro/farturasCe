package moduloFaturamento.regras.cronogramaFaturamento;

import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaCicloProjectionDTO;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoFaseEdicaoValidacaoRegra;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoFaseRemocaoValidacaoRegra;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoFaturaLancadaRemocaoValidacaoRegra;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoRegistroLancadoPorMesConsultaValidacaoRegraRegra;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.util.ConverterData;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class CronogramaFaturamentoRegra {

    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private FaturaRepository faturamentoImovelRepository;
    @Autowired
    private CronogramaFaturamentoRegistroLancadoPorMesConsultaValidacaoRegraRegra cronogramaFaturamentoRegistroLancadoPorMesConsultaValidacaoRegra;
    @Autowired
    private CronogramaFaturamentoFaseRemocaoValidacaoRegra cronogramaFaturamentoFaseRemocaoValidacaoRegra;
    @Autowired
    private CronogramaFaturamentoFaturaLancadaRemocaoValidacaoRegra cronogramaFaturamentoFaturaLancadaRemocaoValidacaoRegra;
    @Autowired
    private CronogramaFaturamentoFaseEdicaoValidacaoRegra cronogramaFaturamentoFaseEdicaoValidacaoRegra;
	
	protected List<CronogramaFaturaCicloProjectionDTO> buscarDropDownCicloProjection(Short cdCidade, Integer referencia) {

		return cronogramaFaturaRepository.buscarCiclosPorLocalidadeEReferencia(cdCidade, referencia);
	}
    
    protected Short retornarFaseCadastrarUmNovoCronograma(){
        return CronogramaFaturamentoFaseEnum.GERAR_ESPELHO.getValor();
    }

    protected List<Short> retornarCidadesCadastradasPorCicloEMesReferencia(Short ciclo, LocalDate dataReferencia){
        Integer mesAnoReferencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferencia);
        this.cronogramaFaturamentoRegistroLancadoPorMesConsultaValidacaoRegra.validarConsulta(ciclo, dataReferencia);
        return this.cronogramaFaturaRepository.buscarCidadesCadastradasPorCicloEReferencia(ciclo, mesAnoReferencia);
    }

    protected Short retornarFaseCronogramaAtualizadaAtravesAcaoAvancarOuRetornar(Short faseAtual, boolean retornarFase, boolean avancarFase){
        Integer faseCronogramaAtualizada = faseAtual.intValue();
        if(avancarFase){
            faseCronogramaAtualizada = faseAtual + 1;
        }

        if(retornarFase){
            faseCronogramaAtualizada = faseAtual - 1;
        }

        return faseCronogramaAtualizada.shortValue();
    }

    protected void impedirEdicaoCronogramaPoisFaseEstaEmEncerrada(Short faseCronograma){
        this.cronogramaFaturamentoFaseEdicaoValidacaoRegra.validar(faseCronograma);
    }

    protected void impedirRemocaoCronogramaPoisFaseDiferenteDeGerarEspelho(Short faseCronograma){
        this.cronogramaFaturamentoFaseRemocaoValidacaoRegra.validar(faseCronograma);
    }

    protected void impedirRemocaoCronogramaPoisExisteFaturaParaCronograma(Short cdCidade, Short ciclo, Integer dataReferencia ){
        this.cronogramaFaturamentoFaturaLancadaRemocaoValidacaoRegra.validar(cdCidade, ciclo, dataReferencia);
    }
}

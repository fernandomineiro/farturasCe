package moduloFaturamento.regras.cronogramaFaturamento;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CronogramaFaturamentoCalculaSugestaoDatasRegra extends CronogramaFaturamentoDatasRegra {

    public LocalDate obterDataSugestaoArquivoLeituraPrevista(LocalDate dataLeitura){
        return super.retornarCalculoSugestaoDeDataGeracaoArquivoLeituraPrevista(dataLeitura);
    }

    public LocalDate obterDataSugestaoFaturamentoPrevista(LocalDate dataLeitura){
        return super.retornarCalculoSugestaoDeDataFaturamentoPrevista(dataLeitura);
    }

    public LocalDate obterDataSugestaoConsolidacaoPrevista(LocalDate dataLeitura){
        return super.retornarCalculoSugestaoDeDataConsolidacaoPrevista(dataLeitura);
    }

    public LocalDate obterDataSugestaoEmissaoFaturaPrevista(LocalDate dataLeitura){
        return super.retornarCalculoSugestaoDeDataEmissaoFaturaPrevista(dataLeitura);
    }

    public LocalDate obterDataSugestaoArquivoCortePrevista(LocalDate dataLeitura){
        return super.retornarCalculoSugestaoDeDataGeracaoArquivoCortePrevista(dataLeitura);
    }

    public LocalDate obterDataSugestaoVencimento(Short cdCidade, Short ciclo, LocalDate dataReferencia){
        return super.retornarCalculoSugestaoDeDataVencimento(cdCidade, ciclo, dataReferencia);
    }

    public LocalDate obterDataSugestaoAprovacaoTarifa(Short cdCidade, Short ciclo, LocalDate dataReferencia, LocalDate dataLeitura) {
        return super.retornarCalculoSugestaoDeDataTarifa(cdCidade, ciclo, dataReferencia, dataLeitura);
    }
}

package moduloFaturamento.regras.cronogramaFaturamento;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoDataTarifaConsultaValidacaoRegraRegra;
import moduloFaturamento.regras.cronogramaFaturamento.validacoes.CronogramaFaturamentoDataVencimentoConsultaValidacaoRegraRegra;
import moduloFaturamento.regras.diaUtilCalendario.DiaUtilCalendarioMunicipioVitoriaConsultaRegra;
import moduloFaturamento.regras.tarifa.TarifaLocalidadeDataAprovacaoConsultaRegra;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FeriadoRepository;
import moduloFaturamento.util.ConverterData;

public abstract class CronogramaFaturamentoDatasRegra {
    @Autowired
    private FeriadoRepository feriadoRepository;
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private DiaUtilCalendarioMunicipioVitoriaConsultaRegra diaUtilCalendarioMunicipioVitoriaConsultaRegra;
    @Autowired
    private CronogramaFaturamentoDataVencimentoConsultaValidacaoRegraRegra cronogramaFaturamentoDataVencimentoConsultaValidacaoRegra;
    @Autowired
    private CronogramaFaturamentoDataTarifaConsultaValidacaoRegraRegra cronogramaFaturamentoDataTarifaConsultaValidacaoRegra;
    @Autowired
    private TarifaLocalidadeDataAprovacaoConsultaRegra tarifaLocalidadeDataAprovacaoConsultaRegra;

    protected LocalDate retornarCalculoSugestaoDeDataGeracaoArquivoLeituraPrevista(LocalDate dataLeitura) {
        final int regraQuantidadeDiasQueDeveSerSubtraido = 2;
        return dataLeitura.minusDays(this.retornarQuantidadeDiasUteisParaSubtrairBaseadoDataLeitura(regraQuantidadeDiasQueDeveSerSubtraido, dataLeitura));
    }

    protected LocalDate retornarCalculoSugestaoDeDataFaturamentoPrevista(LocalDate dataLeitura) {
        final int regraQuantidadeDiasQueDeveSerAdicionado = 1;
        return dataLeitura.plusDays(this.retornarQuantidadeDiasUteisParaAdicionarBaseadoDataLeitura(regraQuantidadeDiasQueDeveSerAdicionado, dataLeitura));
    }

    protected LocalDate retornarCalculoSugestaoDeDataConsolidacaoPrevista(LocalDate dataLeitura) {
        final int regraQuantidadeDiasQueDeveSerAdicionado = 3;
        return dataLeitura.plusDays(this.retornarQuantidadeDiasUteisParaAdicionarBaseadoDataLeitura(regraQuantidadeDiasQueDeveSerAdicionado, dataLeitura));
    }

    protected LocalDate retornarCalculoSugestaoDeDataEmissaoFaturaPrevista(LocalDate dataLeitura) {
        final int regraQuantidadeDiasQueDeveSerAdicionado = 3;
        return dataLeitura.plusDays(this.retornarQuantidadeDiasUteisParaAdicionarBaseadoDataLeitura(regraQuantidadeDiasQueDeveSerAdicionado, dataLeitura));
    }

    protected LocalDate retornarCalculoSugestaoDeDataGeracaoArquivoCortePrevista(LocalDate dataLeitura) {
        final int regraQuantidadeDiasQueDeveSerAdicionado = 3;
        return dataLeitura.plusDays(this.retornarQuantidadeDiasUteisParaAdicionarBaseadoDataLeitura(regraQuantidadeDiasQueDeveSerAdicionado, dataLeitura));
    }

    protected LocalDate retornarCalculoSugestaoDeDataVencimento(Short cdCidade, Short ciclo, LocalDate dataReferencia) {
        LocalDate dataReferenciaAnterior = dataReferencia.minusMonths(1);

        if(cdCidade == null){
            cdCidade = this.cronogramaFaturaRepository.buscarPeloMenosUmaCidadeCadastradaPorCicloEReferencia(ciclo, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior));

        }

        this.cronogramaFaturamentoDataVencimentoConsultaValidacaoRegra.validarConsulta(cdCidade, ciclo, dataReferenciaAnterior);

        IdCronogramaFatura idCronogramaFatura = new IdCronogramaFatura(cdCidade, ciclo, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior));
        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.findByIdCronogramaFatura(idCronogramaFatura);
        LocalDate dataVencimentoCronogramaAnterior = ConverterData.integerEmLocalDateFormatoDB(cronogramaFatura.getDataVencimento());
        return dataVencimentoCronogramaAnterior.plusMonths(1);
    }


    private int retornarQuantidadeDiasUteisParaSubtrairBaseadoDataLeitura(int regraQuantidadeDiasQueDeveSerSubtraido, LocalDate dataLeitura) {
        int diaParaSubtrairConsiderandoAcrescimoFeriadoEFinalDeSemana = regraQuantidadeDiasQueDeveSerSubtraido;
        for (int dia = 1; dia < (diaParaSubtrairConsiderandoAcrescimoFeriadoEFinalDeSemana + 1); dia++) {
            LocalDate dataSubtraidaMenosUmDia = dataLeitura.minusDays(dia);

            if (this.diaUtilCalendarioMunicipioVitoriaConsultaRegra.retornarDataInformadaNaoPertenceAUmDiaUtil(dataSubtraidaMenosUmDia)) {
                diaParaSubtrairConsiderandoAcrescimoFeriadoEFinalDeSemana++;
            }
        }
        return diaParaSubtrairConsiderandoAcrescimoFeriadoEFinalDeSemana;
    }

    private int retornarQuantidadeDiasUteisParaAdicionarBaseadoDataLeitura(int regraQuantidadeDiasQueDeveSerAdicionado, LocalDate dataLeitura) {
        int diaParaAdicionarConsiderandoAcrescimoFeriadoEFinalDeSemana = regraQuantidadeDiasQueDeveSerAdicionado;
        for (int dia = 1; dia < (diaParaAdicionarConsiderandoAcrescimoFeriadoEFinalDeSemana + 1); dia++) {
            LocalDate dataAdcionadoMaisUmDia = dataLeitura.plusDays(dia);

            if (this.diaUtilCalendarioMunicipioVitoriaConsultaRegra.retornarDataInformadaNaoPertenceAUmDiaUtil(dataAdcionadoMaisUmDia)) {
                diaParaAdicionarConsiderandoAcrescimoFeriadoEFinalDeSemana++;
            }
        }
        return diaParaAdicionarConsiderandoAcrescimoFeriadoEFinalDeSemana;
    }


    protected LocalDate retornarCalculoSugestaoDeDataTarifa(Short cdCidade, Short ciclo, LocalDate dataReferencia, LocalDate dataLeitura){
        LocalDate dataReferenciaAnterior = dataReferencia.minusMonths(1);
        if(cdCidade == null){
            cdCidade = this.cronogramaFaturaRepository.buscarPeloMenosUmaCidadeCadastradaPorCicloEReferencia(ciclo, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior));
            this.cronogramaFaturamentoDataTarifaConsultaValidacaoRegra.validarConsulta(cdCidade, ciclo, dataReferenciaAnterior);
        }


        return this.tarifaLocalidadeDataAprovacaoConsultaRegra.retornarDataAprovacaoTarifa(cdCidade, dataLeitura);
    }
}

package moduloFaturamento.regras.leituraConsumoImovel.dias.diasVenda;

import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.common.HidrometroInstalado;
import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.regras.leituraConsumoImovel.dias.LeituraConsumoDiasRegra;
import moduloFaturamento.util.ConverterData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public abstract class LeituraConsumoDiasVendaRegra extends LeituraConsumoDiasRegra {

    protected Short obterValorDiasDeVenda(Integer matricula, LocalDate dataLeitura, LocalDate refFatura){
        Optional<Leitura> leituraOptional = super.obterLeituraDaReferenciaAnteriorADataInformada(matricula, refFatura);
        long dias;
        boolean imovelNovo = leituraOptional.isEmpty();
        if(imovelNovo){
            dias = this.obterDiasDeVendaParaImovelNovo(matricula, dataLeitura, refFatura);
        }else{
            LocalDate dataLeituraAnterior = ConverterData.integerEmLocalDateDataFormatoDB(leituraOptional.get().getDataDeleitura());
            dias = this.obterDiasDeVendaParaImovelNaoNovo(matricula, dataLeitura, refFatura, dataLeituraAnterior);

        }

        return (short) dias;
    }

    private long obterDiasDeVendaParaImovelNovo(Integer matricula, LocalDate dataLeitura, LocalDate refFatura) {
        long dias;
        Imovel imovel = this.imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matricula);
        if(imovel.getDataLigacaoAgua() != null && imovel.getDataLigacaoAgua() != 0){
            if(imovel.getDataReativacaAgua() == null || imovel.getDataReativacaAgua() == 0){
                LocalDate dataLigacaoAgua = ConverterData.integerEmLocalDateDataFormatoDB(imovel.getDataLigacaoAgua());
                dias = super.retornarDiasVendaCalculadoConformeReferenciaAnteriorEAtual(dataLigacaoAgua, dataLeitura);
            }else{
                LocalDate dataReativacaoAgua = ConverterData.integerEmLocalDateDataFormatoDB(imovel.getDataReativacaAgua());
                dias = super.retornarDiasVendaCalculadoConformeReferenciaAnteriorEAtual(dataReativacaoAgua, dataLeitura);
            }
        }else{
            dias = this.obterDiasDeVendaBaseadoNoCronogramaDeFaturamento(matricula, dataLeitura, refFatura);
        }
        return dias;
    }

    private Long obterDiasDeVendaParaImovelNaoNovo(Integer matricula, LocalDate dataLeitura, LocalDate refFatura, LocalDate dataLeituraAnterior) {
        Long dias;
        HidrometroInstalado hidrometroInstaladoAposLeituraAnterior = this.hidrometroInstaladoRepository.buscarSeHidrometroInstaladoOcorreuDepoisDaDataInformada(matricula, ConverterData.localDateEmIntegerDataFormatoDB(dataLeituraAnterior));
        //HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoRepository.buscarUltimoHidrometroInstaladoPelaMatriculaImovel(matricula);
        //HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoRepository.buscarHidrometroInstalado(matricula, ConverterData.localDateEmIntegerDataFormatoDB(dataLeituraAnterior));

        HidrometroRetirado hidrometroRetirado = this.hidrometroRetiradoRepository.buscarSeHouveInstalacaoHidrometroDentroDeUmPeriodoDeTempo(matricula, ConverterData.localDateEmIntegerDataFormatoDB(dataLeituraAnterior), ConverterData.localDateEmIntegerDataFormatoDB(dataLeitura));

        if(hidrometroInstaladoAposLeituraAnterior != null || hidrometroRetirado != null){
            dias = super.retornarDiasVendaCalculadoConformeReferenciaAnteriorEAtual(dataLeituraAnterior, dataLeitura);
        }else{
            dias = this.obterDiasDeVendaBaseadoNoCronogramaDeFaturamento(matricula, dataLeitura, refFatura);
        }
        return dias;
    }


    private long obterDiasDeVendaBaseadoNoCronogramaDeFaturamento(Integer matricula, LocalDate dataLeitura, LocalDate refFatura) {
        Short cicloDoImovel = this.imovelRepository.buscarCicloDoImovelPelaMatricula(matricula);
        Short cidadeDoImovel = this.imovelRepository.buscarCidadeDoImovelExistentePelaMatricula(matricula);

        Integer dataLeituraRealizada = this.cronogramaFaturaRepository.buscarPeloMenosUmaDataLeituraPorCicloReferenciaECidade(cicloDoImovel, ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura), cidadeDoImovel);

        return super.retornarDiasVendaCalculadoConformeReferenciaAnteriorEAtual(ConverterData.integerEmLocalDateDataFormatoDB(dataLeituraRealizada), dataLeitura);
    }


}

package moduloFaturamento.regras.leituraConsumoImovel.dias.diasConsumo;


import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.common.HidrometroInstalado;
import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.regras.leituraConsumoImovel.dias.LeituraConsumoDiasRegra;
import moduloFaturamento.util.ConverterData;

import java.time.LocalDate;
import java.util.Optional;

public abstract class LeituraConsumoDiasConsumoRegra extends LeituraConsumoDiasRegra {

    protected short obterValorDiasDeConsumo(Integer matricula, LocalDate dataLeituraAtual, LocalDate refFatura) {
        Optional<Leitura> leituraOptional = super.obterLeituraDaReferenciaAnteriorADataInformada(matricula, refFatura);
        HidrometroInstalado hidrometroInstalado = this.hidrometroInstaladoRepository.buscarUltimoHidrometroInstaladoPelaMatriculaImovel(matricula);

        HidrometroRetirado hidrometroRetirado = this.hidrometroRetiradoRepository.buscarSeHouveInstalacaoHidrometroDentroDeUmPeriodoDeTempo(matricula, ConverterData.localDateEmIntegerDataFormatoDB(dataLeituraAtual));

        Imovel imovel = this.imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matricula);
        LocalDate dataLigacaoAguaImovel = ConverterData.integerEmLocalDateDataFormatoDB(imovel.getDataLigacaoAgua());

        boolean imovelNovo = leituraOptional.isEmpty();
        long dias;

        if (imovelNovo) {
            dias = this.obterDiasConsumoImovelNovo(matricula, dataLeituraAtual, hidrometroInstalado, hidrometroRetirado, dataLigacaoAguaImovel);
        } else {
            Leitura leitura = leituraOptional.get();

            dias = this.obterDiasConsumoImovelNaoNovo(matricula, dataLeituraAtual, leitura, hidrometroInstalado, hidrometroRetirado);

        }
        return (short) dias;
    }

    private Long obterDiasConsumoImovelNaoNovo(Integer matricula, LocalDate dataLeituraAtual, Leitura leitura, HidrometroInstalado hidrometroInstalado, HidrometroRetirado hidrometroRetirado) {
        long dias;
        if (hidrometroInstalado != null) {

            LocalDate dataInstalacaoHidrometro;
            if(hidrometroRetirado == null){
                dataInstalacaoHidrometro = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroInstalado.getDataInstalacao());
            }else{
                dataInstalacaoHidrometro = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroRetirado.getDataInstalacao());
            }

            LocalDate dataLeituraAnterior = ConverterData.integerEmLocalDateDataFormatoDB(leitura.getDataDeleitura());

            if (dataInstalacaoHidrometro.isAfter(dataLeituraAnterior) && dataInstalacaoHidrometro.isBefore(dataLeituraAtual)) {
                dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataInstalacaoHidrometro, dataLeituraAtual);
            } else {
                dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataLeituraAnterior, dataLeituraAtual);
            }

        } else {
            LocalDate dataLeituraAnterior = ConverterData.integerEmLocalDateDataFormatoDB(leitura.getDataDeleitura());
            dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataLeituraAnterior, dataLeituraAtual);
        }
        return dias;
    }

    private Long obterDiasConsumoImovelNovo(Integer matricula, LocalDate dataLeituraAtual, HidrometroInstalado hidrometroInstalado, HidrometroRetirado hidrometroRetirado, LocalDate dataLigacaoAguaImovel) {
        long dias;
        Imovel imovel = this.imovelRepository.findByMatriculaImovelAndDataHoraExclusaoIsNull(matricula);

        if (hidrometroInstalado != null) {
            LocalDate dataInstalacaoHidrometro;
            if(hidrometroRetirado == null){
                dataInstalacaoHidrometro = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroInstalado.getDataInstalacao());
            }else{
                dataInstalacaoHidrometro = ConverterData.integerEmLocalDateDataFormatoDB(hidrometroRetirado.getDataInstalacao());
            }

            if (imovel.getDataReativacaAgua() == null || imovel.getDataReativacaAgua() == 0) {
                dataLigacaoAguaImovel = ConverterData.integerEmLocalDateDataFormatoDB(imovel.getDataLigacaoAgua());
            } else {
                dataLigacaoAguaImovel = ConverterData.integerEmLocalDateDataFormatoDB(imovel.getDataReativacaAgua());
            }

            if (dataInstalacaoHidrometro.isAfter(dataLigacaoAguaImovel)) {
                dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataInstalacaoHidrometro, dataLeituraAtual);
            } else {
                dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataLigacaoAguaImovel, dataLeituraAtual);
            }
        } else {
            dias = super.retornarDiasConsumoCalculadoConformeReferenciaAnteriorEAtual(dataLigacaoAguaImovel, dataLeituraAtual);
        }

        return dias;
    }
}

package moduloFaturamento.comparator;

import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO;

public class CronogramaFaturamentoGridComparator extends AbstractDTOComparator<CronogramaFaturaRespostaGridProjectionDTO>{

    @Override
    public int compare(CronogramaFaturaRespostaGridProjectionDTO o1, CronogramaFaturaRespostaGridProjectionDTO o2) {
       switch (getCampo()) {
            case "dataReferencia":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getDataReferencia() != null && o1.getDataReferencia() != null) {
                        return o2.getDataReferencia().compareTo(o1.getDataReferencia());
                    }
                    if (o2.getDataReferencia() == null && o1.getDataReferencia() != null) {
                        return -1;
                    }
                    if (o2.getDataReferencia() != null && o1.getDataReferencia() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getDataReferencia() != null && o2.getDataReferencia() != null) {
                        return o1.getDataReferencia().compareTo(o2.getDataReferencia());
                    }
                    if (o1.getDataReferencia() == null && o2.getDataReferencia() != null) {
                        return -1;
                    }
                    if (o1.getDataReferencia() != null && o2.getDataReferencia() == null) {
                        return 1;
                    }
                    return 0;
                }
           case "localidade":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getLocalidade() != null && o1.getLocalidade() != null) {
                       return o2.getLocalidade().compareTo(o1.getLocalidade());
                   }
                   if (o2.getLocalidade() == null && o1.getLocalidade() != null) {
                       return -1;
                   }
                   if (o2.getLocalidade() != null && o1.getLocalidade() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getLocalidade() != null && o2.getLocalidade() != null) {
                       return o1.getLocalidade().compareTo(o2.getLocalidade());
                   }
                   if (o1.getLocalidade() == null && o2.getLocalidade() != null) {
                       return -1;
                   }
                   if (o1.getLocalidade() != null && o2.getLocalidade() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "ciclo":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getCiclo() != null && o1.getCiclo() != null) {
                       return o2.getCiclo().compareTo(o1.getCiclo());
                   }
                   if (o2.getCiclo() == null && o1.getCiclo() != null) {
                       return -1;
                   }
                   if (o2.getCiclo() != null && o1.getCiclo() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getCiclo() != null && o2.getCiclo() != null) {
                       return o1.getCiclo().compareTo(o2.getCiclo());
                   }
                   if (o1.getCiclo() == null && o2.getCiclo() != null) {
                       return -1;
                   }
                   if (o1.getCiclo() != null && o2.getCiclo() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "nomeFase":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getNomeFase() != null && o1.getNomeFase() != null) {
                       return o2.getNomeFase().compareTo(o1.getNomeFase());
                   }
                   if (o2.getNomeFase() == null && o1.getNomeFase() != null) {
                       return -1;
                   }
                   if (o2.getNomeFase() != null && o1.getNomeFase() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getNomeFase() != null && o2.getNomeFase() != null) {
                       return o1.getNomeFase().compareTo(o2.getNomeFase());
                   }
                   if (o1.getNomeFase() == null && o2.getNomeFase() != null) {
                       return -1;
                   }
                   if (o1.getNomeFase() != null && o2.getNomeFase() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataVencimento":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataVencimento() != null && o1.getDataVencimento() != null) {
                       return o2.getDataVencimento().compareTo(o1.getDataVencimento());
                   }
                   if (o2.getDataVencimento() == null && o1.getDataVencimento() != null) {
                       return -1;
                   }
                   if (o2.getDataVencimento() != null && o1.getDataVencimento() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataVencimento() != null && o2.getDataVencimento() != null) {
                       return o1.getDataVencimento().compareTo(o2.getDataVencimento());
                   }
                   if (o1.getDataVencimento() == null && o2.getDataVencimento() != null) {
                       return -1;
                   }
                   if (o1.getDataVencimento() != null && o2.getDataVencimento() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataAprovacaoTarifa":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataAprovacaoTarifa() != null && o1.getDataAprovacaoTarifa() != null) {
                       return o2.getDataAprovacaoTarifa().compareTo(o1.getDataAprovacaoTarifa());
                   }
                   if (o2.getDataAprovacaoTarifa() == null && o1.getDataAprovacaoTarifa() != null) {
                       return -1;
                   }
                   if (o2.getDataAprovacaoTarifa() != null && o1.getDataAprovacaoTarifa() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataAprovacaoTarifa() != null && o2.getDataAprovacaoTarifa() != null) {
                       return o1.getDataAprovacaoTarifa().compareTo(o2.getDataAprovacaoTarifa());
                   }
                   if (o1.getDataAprovacaoTarifa() == null && o2.getDataAprovacaoTarifa() != null) {
                       return -1;
                   }
                   if (o1.getDataAprovacaoTarifa() != null && o2.getDataAprovacaoTarifa() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataGeracaoArquivoPrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataGeracaoArquivoPrevista() != null && o1.getDataGeracaoArquivoPrevista() != null) {
                       return o2.getDataGeracaoArquivoPrevista().compareTo(o1.getDataGeracaoArquivoPrevista());
                   }
                   if (o2.getDataGeracaoArquivoPrevista() == null && o1.getDataGeracaoArquivoPrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataGeracaoArquivoPrevista() != null && o1.getDataGeracaoArquivoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataGeracaoArquivoPrevista() != null && o2.getDataGeracaoArquivoPrevista() != null) {
                       return o1.getDataGeracaoArquivoPrevista().compareTo(o2.getDataGeracaoArquivoPrevista());
                   }
                   if (o1.getDataGeracaoArquivoPrevista() == null && o2.getDataGeracaoArquivoPrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataGeracaoArquivoPrevista() != null && o2.getDataGeracaoArquivoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataGeracaoArquivoRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataGeracaoArquivoRealizada() != null && o1.getDataGeracaoArquivoRealizada() != null) {
                       return o2.getDataGeracaoArquivoRealizada().compareTo(o1.getDataGeracaoArquivoRealizada());
                   }
                   if (o2.getDataGeracaoArquivoRealizada() == null && o1.getDataGeracaoArquivoRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataGeracaoArquivoRealizada() != null && o1.getDataGeracaoArquivoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataGeracaoArquivoRealizada() != null && o2.getDataGeracaoArquivoRealizada() != null) {
                       return o1.getDataGeracaoArquivoRealizada().compareTo(o2.getDataGeracaoArquivoRealizada());
                   }
                   if (o1.getDataGeracaoArquivoRealizada() == null && o2.getDataGeracaoArquivoRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataGeracaoArquivoRealizada() != null && o2.getDataGeracaoArquivoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataLeituraPrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataLeituraPrevista() != null && o1.getDataLeituraPrevista() != null) {
                       return o2.getDataLeituraPrevista().compareTo(o1.getDataLeituraPrevista());
                   }
                   if (o2.getDataLeituraPrevista() == null && o1.getDataLeituraPrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataLeituraPrevista() != null && o1.getDataLeituraPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataLeituraPrevista() != null && o2.getDataLeituraPrevista() != null) {
                       return o1.getDataLeituraPrevista().compareTo(o2.getDataLeituraPrevista());
                   }
                   if (o1.getDataLeituraPrevista() == null && o2.getDataLeituraPrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataLeituraPrevista() != null && o2.getDataLeituraPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataLeituraRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataLeituraRealizada() != null && o1.getDataLeituraRealizada() != null) {
                       return o2.getDataLeituraRealizada().compareTo(o1.getDataLeituraRealizada());
                   }
                   if (o2.getDataLeituraRealizada() == null && o1.getDataLeituraRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataLeituraRealizada() != null && o1.getDataLeituraRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataLeituraRealizada() != null && o2.getDataLeituraRealizada() != null) {
                       return o1.getDataLeituraRealizada().compareTo(o2.getDataLeituraRealizada());
                   }
                   if (o1.getDataLeituraRealizada() == null && o2.getDataLeituraRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataLeituraRealizada() != null && o2.getDataLeituraRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataFaturamentoPrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataFaturamentoPrevista() != null && o1.getDataFaturamentoPrevista() != null) {
                       return o2.getDataFaturamentoPrevista().compareTo(o1.getDataFaturamentoPrevista());
                   }
                   if (o2.getDataFaturamentoPrevista() == null && o1.getDataFaturamentoPrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataFaturamentoPrevista() != null && o1.getDataFaturamentoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataFaturamentoPrevista() != null && o2.getDataFaturamentoPrevista() != null) {
                       return o1.getDataFaturamentoPrevista().compareTo(o2.getDataFaturamentoPrevista());
                   }
                   if (o1.getDataFaturamentoPrevista() == null && o2.getDataFaturamentoPrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataFaturamentoPrevista() != null && o2.getDataFaturamentoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataFaturamentoRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataFaturamentoRealizada() != null && o1.getDataFaturamentoRealizada() != null) {
                       return o2.getDataFaturamentoRealizada().compareTo(o1.getDataFaturamentoRealizada());
                   }
                   if (o2.getDataFaturamentoRealizada() == null && o1.getDataFaturamentoRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataFaturamentoRealizada() != null && o1.getDataFaturamentoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataFaturamentoRealizada() != null && o2.getDataFaturamentoRealizada() != null) {
                       return o1.getDataFaturamentoRealizada().compareTo(o2.getDataFaturamentoRealizada());
                   }
                   if (o1.getDataFaturamentoRealizada() == null && o2.getDataFaturamentoRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataFaturamentoRealizada() != null && o2.getDataFaturamentoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataConsolidacaoPrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataConsolidacaoPrevista() != null && o1.getDataConsolidacaoPrevista() != null) {
                       return o2.getDataConsolidacaoPrevista().compareTo(o1.getDataConsolidacaoPrevista());
                   }
                   if (o2.getDataConsolidacaoPrevista() == null && o1.getDataConsolidacaoPrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataConsolidacaoPrevista() != null && o1.getDataConsolidacaoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataConsolidacaoPrevista() != null && o2.getDataConsolidacaoPrevista() != null) {
                       return o1.getDataConsolidacaoPrevista().compareTo(o2.getDataConsolidacaoPrevista());
                   }
                   if (o1.getDataConsolidacaoPrevista() == null && o2.getDataConsolidacaoPrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataConsolidacaoPrevista() != null && o2.getDataConsolidacaoPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataConsolidacaoRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataConsolidacaoRealizada() != null && o1.getDataConsolidacaoRealizada() != null) {
                       return o2.getDataConsolidacaoRealizada().compareTo(o1.getDataConsolidacaoRealizada());
                   }
                   if (o2.getDataConsolidacaoRealizada() == null && o1.getDataConsolidacaoRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataConsolidacaoRealizada() != null && o1.getDataConsolidacaoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataConsolidacaoRealizada() != null && o2.getDataConsolidacaoRealizada() != null) {
                       return o1.getDataConsolidacaoRealizada().compareTo(o2.getDataConsolidacaoRealizada());
                   }
                   if (o1.getDataConsolidacaoRealizada() == null && o2.getDataConsolidacaoRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataConsolidacaoRealizada() != null && o2.getDataConsolidacaoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataEmissaPrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataEmissaPrevista() != null && o1.getDataEmissaPrevista() != null) {
                       return o2.getDataEmissaPrevista().compareTo(o1.getDataEmissaPrevista());
                   }
                   if (o2.getDataEmissaPrevista() == null && o1.getDataEmissaPrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataEmissaPrevista() != null && o1.getDataEmissaPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataEmissaPrevista() != null && o2.getDataEmissaPrevista() != null) {
                       return o1.getDataEmissaPrevista().compareTo(o2.getDataEmissaPrevista());
                   }
                   if (o1.getDataEmissaPrevista() == null && o2.getDataEmissaPrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataEmissaPrevista() != null && o2.getDataEmissaPrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataEmissaoRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataEmissaoRealizada() != null && o1.getDataEmissaoRealizada() != null) {
                       return o2.getDataEmissaoRealizada().compareTo(o1.getDataEmissaoRealizada());
                   }
                   if (o2.getDataEmissaoRealizada() == null && o1.getDataEmissaoRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataEmissaoRealizada() != null && o1.getDataEmissaoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataEmissaoRealizada() != null && o2.getDataEmissaoRealizada() != null) {
                       return o1.getDataEmissaoRealizada().compareTo(o2.getDataEmissaoRealizada());
                   }
                   if (o1.getDataEmissaoRealizada() == null && o2.getDataEmissaoRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataEmissaoRealizada() != null && o2.getDataEmissaoRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataGeracaoArquivoCortePrevista":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataGeracaoArquivoCortePrevista() != null && o1.getDataGeracaoArquivoCortePrevista() != null) {
                       return o2.getDataGeracaoArquivoCortePrevista().compareTo(o1.getDataGeracaoArquivoCortePrevista());
                   }
                   if (o2.getDataGeracaoArquivoCortePrevista() == null && o1.getDataGeracaoArquivoCortePrevista() != null) {
                       return -1;
                   }
                   if (o2.getDataGeracaoArquivoCortePrevista() != null && o1.getDataGeracaoArquivoCortePrevista() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataGeracaoArquivoCortePrevista() != null && o2.getDataGeracaoArquivoCortePrevista() != null) {
                       return o1.getDataGeracaoArquivoCortePrevista().compareTo(o2.getDataGeracaoArquivoCortePrevista());
                   }
                   if (o1.getDataGeracaoArquivoCortePrevista() == null && o2.getDataGeracaoArquivoCortePrevista() != null) {
                       return -1;
                   }
                   if (o1.getDataGeracaoArquivoCortePrevista() != null && o2.getDataGeracaoArquivoCortePrevista() == null) {
                       return 1;
                   }
                   return 0;
               }
           case "dataGeracaoArquivoCorteRealizada":
               if ("DESC".equalsIgnoreCase(getOrdem())) {
                   if (o2.getDataGeracaoArquivoCorteRealizada() != null && o1.getDataGeracaoArquivoCorteRealizada() != null) {
                       return o2.getDataGeracaoArquivoCorteRealizada().compareTo(o1.getDataGeracaoArquivoCorteRealizada());
                   }
                   if (o2.getDataGeracaoArquivoCorteRealizada() == null && o1.getDataGeracaoArquivoCorteRealizada() != null) {
                       return -1;
                   }
                   if (o2.getDataGeracaoArquivoCorteRealizada() != null && o1.getDataGeracaoArquivoCorteRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }else{
                   if (o1.getDataGeracaoArquivoCorteRealizada() != null && o2.getDataGeracaoArquivoCorteRealizada() != null) {
                       return o1.getDataGeracaoArquivoCorteRealizada().compareTo(o2.getDataGeracaoArquivoCorteRealizada());
                   }
                   if (o1.getDataGeracaoArquivoCorteRealizada() == null && o2.getDataGeracaoArquivoCorteRealizada() != null) {
                       return -1;
                   }
                   if (o1.getDataGeracaoArquivoCorteRealizada() != null && o2.getDataGeracaoArquivoCorteRealizada() == null) {
                       return 1;
                   }
                   return 0;
               }
               default:
                if (o1.getDataReferencia() != null && o2.getDataReferencia() != null) {
                    return o1.getDataReferencia().compareTo(o2.getDataReferencia());
                }
                if (o1.getDataReferencia() == null && o2.getDataReferencia() != null) {
                    return -1;
                }
                if (o1.getDataReferencia() != null && o2.getDataReferencia() == null) {
                    return 1;
                }
                return 0;
        }
    }
}

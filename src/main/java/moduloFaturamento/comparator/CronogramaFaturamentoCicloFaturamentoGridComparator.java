package moduloFaturamento.comparator;

import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO;

public class CronogramaFaturamentoCicloFaturamentoGridComparator extends AbstractDTOComparator<CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO> {

    @Override
    public int compare(CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO o1, CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO o2) {
        switch (getCampo()) {
            case "id":
                return compareSafeForNull(o1.getId(), o2.getId());
            case "dataReferencia":
                return compareSafeForNull(o1.getDataReferencia(), o2.getDataReferencia());
            case "localidade":
                return compareSafeForNull(o1.getLocalidade(), o2.getLocalidade());
            case "ciclo":
                return compareSafeForNull(o1.getCiclo(), o2.getCiclo());
            case "nomeFase":
                return compareSafeForNull(o1.getNomeFase(), o2.getNomeFase());
            case "statusProcessamento":
                return compareSafeForNull(o1.getStatusProcessamento(), o2.getStatusProcessamento());
            default:
                return 0;
        }
    }
}

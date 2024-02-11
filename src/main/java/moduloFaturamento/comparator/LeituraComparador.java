package moduloFaturamento.comparator;

import moduloFaturamento.dto.leitura.LeituraPesquisarDTO;

public class LeituraComparador extends AbstractDTOComparator<LeituraPesquisarDTO>  {

    @Override
    public int compare(LeituraPesquisarDTO o1, LeituraPesquisarDTO o2) {
        
        switch (getCampo()) {

            case "seqRota":
                return compareSafeForNull(o1.getSeqRota(), o2.getSeqRota());
    
            case "matricula":
                return compareSafeForNull(o1.getMatricula(), o2.getMatricula());

            case "leituraAnterior":
                return compareSafeForNull(o1.getLeituraAnterior(), o2.getLeituraAnterior());
    
            default:
                return 0;
            }
    }

    
    
}

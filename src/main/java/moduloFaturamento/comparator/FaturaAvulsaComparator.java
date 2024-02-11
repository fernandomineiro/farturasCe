package moduloFaturamento.comparator;

import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaDTO;

public class FaturaAvulsaComparator extends AbstractDTOComparator<FaturaAvulsaPesquisaDTO>  {

    @Override
    public int compare(FaturaAvulsaPesquisaDTO o1, FaturaAvulsaPesquisaDTO o2) {
        
        switch (getCampo()) {

            case "refFatura":
                return compareSafeForNull(o1.getRefFatura(), o2.getRefFatura());
    
            case "origemFatura":
                return compareSafeForNull(o1.getOrigemFatura(), o2.getOrigemFatura());

            case "matriculaImovel":
                return compareSafeForNull(o1.getMatriculaImovel(), o2.getMatriculaImovel());
    
            case "valorFatura":
                return compareSafeForNull(o1.getValorFatura(), o2.getValorFatura());

            case "dataVencimento":
                return compareSafeForNull(o1.getDataVencimento(), o2.getDataVencimento());

            default:
                return 0;
            }
    }

    
    
}

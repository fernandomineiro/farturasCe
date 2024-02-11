package moduloFaturamento.comparator;

import moduloFaturamento.dto.credito.CreditoLancadoDTO;

public class CreditoLancadoComparador extends AbstractDTOComparator<CreditoLancadoDTO> {

    
    @Override
    public int compare(CreditoLancadoDTO o1, CreditoLancadoDTO o2) {
        
        switch (getCampo()) {

            case "data":
                return compareSafeForNull(o1.getData(), o2.getData());
    
            case "valorLancado":
                return compareSafeForNull(o1.getValorLancado(), o2.getValorLancado());
    
            default:
                return 0;
            }
    }
    
}

package moduloFaturamento.comparator;

import moduloFaturamento.dto.credito.CreditoPesquisaDTO;

public class CreditoPesquisaComparador extends AbstractDTOComparator<CreditoPesquisaDTO> {

    @Override
    public int compare(CreditoPesquisaDTO o1, CreditoPesquisaDTO o2) {
        
        switch (getCampo()) {

            case "cdCredito":
                return compareSafeForNull(o1.getCdCredito(), o2.getCdCredito());
    
            case "cdServico":
                return compareSafeForNull(o1.getCdServico(), o2.getCdServico());
            
            case "nomeServico":
                return compareSafeForNull(o1.getNomeServico(), o2.getNomeServico());

            case "valorCredito":
                return compareSafeForNull(o1.getValorCredito(), o2.getValorCredito());

            case "saldo":
                return compareSafeForNull(o1.getSaldo(), o2.getSaldo());

            case "cdParcelamento":
                return compareSafeForNull(o1.getDataInicio(), o2.getDataInicio());

            case "numeroParcela":
                return compareSafeForNull(o1.getDataInicio(), o2.getDataInicio());

            case "origemParcela":
                return compareSafeForNull(o1.getDataInicio(), o2.getDataInicio());

            case "refFatura":
                return compareSafeForNull(o1.getDataInicio(), o2.getDataInicio());

            case "refEncerramento":
                return compareSafeForNull(o1.getDataInicio(), o2.getDataInicio());

            default:
                return 0;
            }
    }
    
}

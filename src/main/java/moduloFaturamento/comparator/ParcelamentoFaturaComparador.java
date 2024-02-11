package moduloFaturamento.comparator;

import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaPesquisaResponseProjectionDTO;

public class ParcelamentoFaturaComparador extends AbstractDTOComparator<ParcelamentoFaturaPesquisaResponseProjectionDTO> {

    @Override
    public int compare(ParcelamentoFaturaPesquisaResponseProjectionDTO o1, ParcelamentoFaturaPesquisaResponseProjectionDTO o2) {
        
        switch (getCampo()) {

            case "matriculaImovel":
                return compareSafeForNull(o1.getMatriculaImovel(), o2.getMatriculaImovel());
    
            case "grupoDeConsumo":
                return compareSafeForNull(o1.getDescricaoGrupoDeConsumo(), o2.getDescricaoGrupoDeConsumo());
            
            case "nomeDaRua":
                return compareSafeForNull(o1.getNomeDaRua(), o2.getNomeDaRua());

            case "nomeDoBairro":
                return compareSafeForNull(o1.getNomeDoBairro(), o2.getNomeDoBairro());

            case "numeroImovel":
                return compareSafeForNull(o1.getNumeroImovel(), o2.getNumeroImovel());

            case "nomeDacidade":
                return compareSafeForNull(o1.getNomeDaCidade(), o2.getNomeDaCidade());
            default:
                return 0;
        }
    }
}

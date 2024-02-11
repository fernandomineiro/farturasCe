package moduloFaturamento.comparator;

import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaProjectionDTO;

public class TarifaMediaComparador extends AbstractDTOComparator<TarifaMediaProjectionDTO> {

    @Override
    public int compare(TarifaMediaProjectionDTO o1, TarifaMediaProjectionDTO o2) {
        
        switch (getCampo()) {

            case "idGrupoConsumo":
                return compareSafeForNull(o1.getIdGrupoConsumo(), o2.getIdGrupoConsumo());
    
            case "valorMedio":
                return compareSafeForNull(o1.getValorMedio(), o2.getValorMedio());
            
            case "mediaAgua":
                return compareSafeForNull(o1.getMediaAgua(), o2.getMediaAgua());

            case "mediaEsgoto":
                return compareSafeForNull(o1.getMediaEsgoto(), o2.getMediaEsgoto());

            case "mediaDisponibilidadeEsgoto":
                return compareSafeForNull(o1.getMediaDisponibilidadeEsgoto(), o2.getMediaDisponibilidadeEsgoto());

            default:
                return 0;
            }
    }
    
}

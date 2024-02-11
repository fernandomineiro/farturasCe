package moduloFaturamento.comparator;

import moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO;

public class LeituraConsumoImovelMatriculasMicroGridComparator extends AbstractDTOComparator<ImovelMatriculaComDvProjectionDTO>  {

    @Override
    public int compare(ImovelMatriculaComDvProjectionDTO o1, ImovelMatriculaComDvProjectionDTO o2) {

        switch (getCampo()) {

            case "matricula":
                return compareSafeForNull(o1.getMatricula(), o2.getMatricula());

            case "dv":
                return compareSafeForNull(o1.getDv(), o2.getDv());

            default:
                return 0;
        }
    }
}

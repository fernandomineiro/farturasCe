package moduloFaturamento.comparator;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;

public class IncentivoClienteParametroGridComparator extends AbstractDTOComparator<IncentivoClienteParametroRespostaGridProjectionDTO> {

    @Override
    public int compare(IncentivoClienteParametroRespostaGridProjectionDTO o1, IncentivoClienteParametroRespostaGridProjectionDTO o2) {

        switch (getCampo()) {

            case "id":
                return compareSafeForNull(o1.getId(), o2.getId());

            case "nomeDeliberacao":
                return compareSafeForNull(o1.getNomeDeliberacao(), o2.getNomeDeliberacao());

            case "dataInicioVigencia":
                return compareSafeForNull(o1.getDataInicioVigencia(), o2.getDataInicioVigencia());

            case "dataFimVigencia":
                return compareSafeForNull(o1.getDataFimVigencia(), o2.getDataFimVigencia());

            case "tipoIncentivo":
                return compareSafeForNull(o1.getTipoIncentivo(), o2.getTipoIncentivo());

            default:
                return 0;
        }
    }


}

package moduloFaturamento.comparator;

import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;

public class TarifaGridComparator extends AbstractDTOComparator<PesquisaTarifaRespostaDTO> {

    @Override
    public int compare(PesquisaTarifaRespostaDTO o1, PesquisaTarifaRespostaDTO o2) {
        switch (getCampo()) {
            case "grupo":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getGrupo().compareTo(o1.getGrupo());
                } else {
                    return o1.getGrupo().compareTo(o2.getGrupo());
                }
            case "idTarifa":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getIdTarifa().compareTo(o1.getIdTarifa());
                } else {
                    return o1.getIdTarifa().compareTo(o2.getIdTarifa());
                }
            case "limite":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getLimite().compareTo(o1.getLimite());
                } else {
                    return o1.getLimite().compareTo(o2.getLimite());
                }
            default:
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getDataTarifa().compareTo(o1.getDataTarifa());
                } else {
                    return o1.getDataTarifa().compareTo(o2.getDataTarifa());
                }
        }
    }
}

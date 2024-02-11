package moduloFaturamento.comparator;

import moduloFaturamento.dto.feriado.FeriadoRespostaGridDTO;

public class FeriadoGridComparator extends AbstractDTOComparator<FeriadoRespostaGridDTO>{


    @Override
    public int compare(FeriadoRespostaGridDTO o1, FeriadoRespostaGridDTO o2) {
        switch (getCampo()) {
            case "dataFeriado":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getDataFeriado() != null && o1.getDataFeriado() != null) {
                        return o2.getDataFeriado().compareTo(o1.getDataFeriado());
                    }
                    if (o2.getDataFeriado() == null && o1.getDataFeriado() != null) {
                        return -1;
                    }
                    if (o2.getDataFeriado() != null && o1.getDataFeriado() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getDataFeriado() != null && o2.getDataFeriado() != null) {
                        return o1.getDataFeriado().compareTo(o2.getDataFeriado());
                    }
                    if (o1.getDataFeriado() == null && o2.getDataFeriado() != null) {
                        return -1;
                    }
                    if (o1.getDataFeriado() != null && o2.getDataFeriado() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "tipoFeriado":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getTipoFeriado().compareTo(o1.getTipoFeriado());
                } else {
                    return o1.getTipoFeriado().compareTo(o2.getTipoFeriado());
                }
            case "nomeFeriado":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getNomeFeriado().compareTo(o1.getNomeFeriado());
                } else {
                    return o1.getNomeFeriado().compareTo(o2.getNomeFeriado());
                }
            case "siglaUnidadeFederacao":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getSiglaUnidadeFederacao() != null && o1.getSiglaUnidadeFederacao() != null) {
                        return o2.getSiglaUnidadeFederacao().compareTo(o1.getSiglaUnidadeFederacao());
                    }
                    if (o2.getSiglaUnidadeFederacao() == null && o1.getSiglaUnidadeFederacao() != null) {
                        return -1;
                    }
                    if (o2.getSiglaUnidadeFederacao() != null && o1.getSiglaUnidadeFederacao() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getSiglaUnidadeFederacao() != null && o2.getSiglaUnidadeFederacao() != null) {
                        return o1.getSiglaUnidadeFederacao().compareTo(o2.getSiglaUnidadeFederacao());
                    }
                    if (o1.getSiglaUnidadeFederacao() == null && o2.getSiglaUnidadeFederacao() != null) {
                        return -1;
                    }
                    if (o1.getSiglaUnidadeFederacao() != null && o2.getSiglaUnidadeFederacao() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "localidade":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getLocalidade() != null && o1.getLocalidade() != null) {
                        return o2.getLocalidade().compareTo(o1.getLocalidade());
                    }
                    if (o2.getLocalidade() == null && o1.getLocalidade() != null) {
                        return -1;
                    }
                    if (o2.getLocalidade() != null && o1.getLocalidade() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getLocalidade() != null && o2.getLocalidade() != null) {
                        return o1.getLocalidade().compareTo(o2.getLocalidade());
                    }
                    if (o1.getLocalidade() == null && o2.getLocalidade() != null) {
                        return -1;
                    }
                    if (o1.getLocalidade() != null && o2.getLocalidade() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "id":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getId().compareTo(o1.getId());
                } else {
                    return o1.getId().compareTo(o2.getId());
                }
            default:
                if (o1.getDataFeriado() != null && o2.getDataFeriado() != null) {
                    return o1.getDataFeriado().compareTo(o2.getDataFeriado());
                }
                if (o1.getDataFeriado() == null && o2.getDataFeriado() != null) {
                    return -1;
                }
                if (o1.getDataFeriado() != null && o2.getDataFeriado() == null) {
                    return 1;
                }
                return 0;
        }
    }
}

package moduloFaturamento.comparator;

import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaGridDTO;

public class OcorrenciaLeituraGridComparator extends AbstractDTOComparator<OcorrenciaLeituraRespostaGridDTO>{


    @Override
    public int compare(OcorrenciaLeituraRespostaGridDTO o1, OcorrenciaLeituraRespostaGridDTO o2) {
        switch (getCampo()) {
            case "idTipoServicoOcorrencia":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getTipo() != null && o1.getTipo() != null) {
                        return o2.getTipo().compareTo(o1.getTipo());
                    }
                    if (o2.getTipo() == null && o1.getTipo() != null) {
                        return -1;
                    }
                    if (o2.getTipo() != null && o1.getTipo() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getTipo() != null && o2.getTipo() != null) {
                        return o1.getTipo().compareTo(o2.getTipo());
                    }
                    if (o1.getTipo() == null && o2.getTipo() != null) {
                        return -1;
                    }
                    if (o1.getTipo() != null && o2.getTipo() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "descricao":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getDescricao() != null && o1.getDescricao() != null) {
                        return o2.getDescricao().compareTo(o1.getDescricao());
                    }
                    if (o2.getDescricao() == null && o1.getDescricao() != null) {
                        return -1;
                    }
                    if (o2.getDescricao() != null && o1.getDescricao() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getDescricao() != null && o2.getDescricao() != null) {
                        return o1.getDescricao().compareTo(o2.getDescricao());
                    }
                    if (o1.getDescricao() == null && o2.getDescricao() != null) {
                        return -1;
                    }
                    if (o1.getDescricao() != null && o2.getDescricao() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "leituraVirtual":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getLeituraVirual() != null && o1.getLeituraVirual() != null) {
                        return o2.getLeituraVirual().compareTo(o1.getLeituraVirual());
                    }
                    if (o2.getLeituraVirual() == null && o1.getLeituraVirual() != null) {
                        return -1;
                    }
                    if (o2.getLeituraVirual() != null && o1.getLeituraVirual() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getLeituraVirual() != null && o2.getLeituraVirual() != null) {
                        return o1.getLeituraVirual().compareTo(o2.getLeituraVirual());
                    }
                    if (o1.getLeituraVirual() == null && o2.getLeituraVirual() != null) {
                        return -1;
                    }
                    if (o1.getLeituraVirual() != null && o2.getLeituraVirual() == null) {
                        return 1;
                    }
                    return 0;
                }
            case "id":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getCdOcorrencia().compareTo(o1.getCdOcorrencia());
                } else {
                    return o1.getCdOcorrencia().compareTo(o2.getCdOcorrencia());
                }
            default:
                if (o1.getCdOcorrencia() != null && o2.getCdOcorrencia() != null) {
                    return o1.getCdOcorrencia().compareTo(o2.getCdOcorrencia());
                }
                if (o1.getCdOcorrencia() == null && o2.getCdOcorrencia() != null) {
                    return -1;
                }
                if (o1.getCdOcorrencia() != null && o2.getCdOcorrencia() == null) {
                    return 1;
                }
                return 0;
        }
    }
}

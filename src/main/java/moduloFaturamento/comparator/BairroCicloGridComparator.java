package moduloFaturamento.comparator;

import moduloFaturamento.dto.bairroCiclo.CicloBairroRespostaDTO;

public class BairroCicloGridComparator extends AbstractDTOComparator<CicloBairroRespostaDTO>{


    @Override
    public int compare(CicloBairroRespostaDTO o1, CicloBairroRespostaDTO o2) {
        switch (getCampo()) {
            case "nome":
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    if (o2.getNome() != null && o1.getNome() != null) {
                        return o2.getNome().compareTo(o1.getNome());
                    }
                    if (o2.getNome() == null && o1.getNome() != null) {
                        return -1;
                    }
                    if (o2.getNome() != null && o1.getNome() == null) {
                        return 1;
                    }
                    return 0;
                }else{
                    if (o1.getNome() != null && o2.getNome() != null) {
                        return o1.getId().compareTo(o2.getId());
                    }
                    if (o1.getNome() == null && o2.getNome() != null) {
                        return -1;
                    }
                    if (o1.getNome() != null && o2.getNome() == null) {
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
                if ("DESC".equalsIgnoreCase(getOrdem())) {
                    return o2.getCiclo().compareTo(o1.getCiclo());
                } else {
                    return o1.getCiclo().compareTo(o2.getCiclo());
                }
        }
    }
}

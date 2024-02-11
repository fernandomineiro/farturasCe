package moduloFaturamento.comparator;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;

public class IncentivoClienteParametroDetalheGridComparator extends AbstractDTOComparator<IncentivoClienteParametroDetalheRespostaGridProjectionDTO> {

    @Override
    public int compare(IncentivoClienteParametroDetalheRespostaGridProjectionDTO o1, IncentivoClienteParametroDetalheRespostaGridProjectionDTO o2) {

        switch (getCampo()) {

            case "id":
                return compareSafeForNull(o1.getId(), o2.getId());

            case "descricaoDesconto":
                return compareSafeForNull(o1.getDescricaoDesconto(), o2.getDescricaoDesconto());

            case "percentualEntradaMinimo":
                return compareSafeForNull(o1.getPercentualEntradaMinimo(), o2.getPercentualEntradaMinimo());

            case "percentualEntradaMaximo":
                return compareSafeForNull(o1.getPercentualEntradaMaximo(), o2.getPercentualEntradaMaximo());


            case "descontoMultas":
                return compareSafeForNull(o1.getDescontoMultas(), o2.getDescontoMultas());

            case "descontoJuros":
                return compareSafeForNull(o1.getDescontoJuros(), o2.getDescontoJuros());

            case "descontoValorPrincipal":
                return compareSafeForNull(o1.getDescontoValorPrincipal(), o2.getDescontoValorPrincipal());

            case "numeroMaximoParcelas":
                return compareSafeForNull(o1.getNumeroMaximoParcelas(), o2.getNumeroMaximoParcelas());

            case "numeroMinimoDebitos":
                return compareSafeForNull(o1.getNumeroMinimoDebitos(), o2.getNumeroMinimoDebitos());

            case "valorMinimoDebitos":
                return compareSafeForNull(o1.getValorMinimoDebitos(), o2.getValorMinimoDebitos());

            case "valorMinimoParcela":
                return compareSafeForNull(o1.getValorMinimoParcela(), o2.getValorMinimoParcela());

            case "numeroMinimoDiasDesconto":
                return compareSafeForNull(o1.getNumeroMinimoDiasDesconto(), o2.getNumeroMinimoDiasDesconto());

            case "numeroMaximoParcelasAgencia":
                return compareSafeForNull(o1.getNumeroMaximoParcelasAgencia(), o2.getNumeroMaximoParcelasAgencia());

            case "valorMaximoDebitoAgencia":
                return compareSafeForNull(o1.getValorMaximoDebitoAgencia(), o2.getValorMaximoDebitoAgencia());

            case "indicativoCorrecaoMonetaria":
                return compareSafeForNull(o1.getCorrecaoMonetaria(), o2.getCorrecaoMonetaria());

            case "valorCorrecaoMonetaria":
                return compareSafeForNull(o1.getValorCorrecaoMonetaria(), o2.getValorCorrecaoMonetaria());

            case "listaSituacaoAgua":
                return compareSafeForNull(o1.getListaSituacaoAgua().toString(), o2.getListaSituacaoAgua().toString());

            case "listaSituacaoEsgoto":
                return compareSafeForNull(o1.getListaSituacaoEsgoto().toString(), o2.getListaSituacaoEsgoto().toString());

            case "listaGrupoConsumo":
                return compareSafeForNull(o1.getListaGrupoConsumo().toString(), o2.getListaGrupoConsumo().toString());

            default:
                return 0;
        }
    }


}

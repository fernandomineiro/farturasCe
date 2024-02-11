package moduloFaturamento.comparator;

import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasRealizadasProjectionRespostaDTO;

public class PesquisaAmostrasRealizadasProjectionRespostaComparator extends AbstractDTOComparator<PesquisaAmostrasRealizadasProjectionRespostaDTO> {

	@Override
	public int compare(PesquisaAmostrasRealizadasProjectionRespostaDTO o1, PesquisaAmostrasRealizadasProjectionRespostaDTO o2) {

		switch (getCampo()) {

		case "cdCidade":
			return compareSafeForNull(o1.getCdCidade(), o2.getCdCidade());

		case "dcCidade":
			return compareSafeForNull(o1.getDcCidade(), o2.getDcCidade());

		default:
			return 0;
		}

	}

}

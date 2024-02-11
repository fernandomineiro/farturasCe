package moduloFaturamento.comparator;

import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasArquivosProjectionRespostaDTO;

public class PesquisaAmostrasArquivosProjectionRespostaComparator extends AbstractDTOComparator<PesquisaAmostrasArquivosProjectionRespostaDTO> {

	@Override
	public int compare(PesquisaAmostrasArquivosProjectionRespostaDTO o1, PesquisaAmostrasArquivosProjectionRespostaDTO o2) {

		switch (getCampo()) {

		case "dataInsercao":
			return compareSafeForNull(o1.getDataInsercao(), o2.getDataInsercao());

		case "referencia":
			return compareSafeForNull(o1.getReferencia(), o2.getReferencia());

		case "tipoArquivo":
			return compareSafeForNull(o1.getTipoArquivo(), o2.getTipoArquivo());

		default:
			return 0;
		}
	}

}

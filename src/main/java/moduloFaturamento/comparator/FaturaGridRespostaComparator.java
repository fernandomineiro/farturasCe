package moduloFaturamento.comparator;

import moduloFaturamento.dto.cadastramentoFatura.FaturaGridRespostaDTO;

public class FaturaGridRespostaComparator extends AbstractDTOComparator<FaturaGridRespostaDTO> {

	@Override
	public int compare(FaturaGridRespostaDTO o1, FaturaGridRespostaDTO o2) {

		switch (getCampo()) {

		case "refFatura":
			return compareSafeForNull(o1.getRefFatura(), o2.getRefFatura());

		case "origemFatura":
			return compareSafeForNull(o1.getOrigemFatura(), o2.getOrigemFatura());

		case "versaoFatura":
			return compareSafeForNull(o1.getVersaoFatura(), o2.getVersaoFatura());

		case "valorFatura":
			return compareSafeForNull(o1.getValorFatura(), o2.getValorFatura());

		case "dataVencimento":
			return compareSafeForNull(o1.getDataVencimento(), o2.getDataVencimento());

		case "dataPagamento":
			return compareSafeForNull(o1.getDataPagamento(), o2.getDataPagamento());

		case "situacaoBaixa":
			return compareSafeForNull(o1.getSituacaoBaixa(), o2.getSituacaoBaixa());

		case "codigoCliente":
			return compareSafeForNull(o1.getCodigoCliente(), o2.getCodigoCliente());

		case "nomeCliente":
			return compareSafeForNull(o1.getNomeCliente(), o2.getNomeCliente());

		default:
			setOrdem(DESC);
			setCampo("refFatura");
			return compare(o1, o2);
		}
	}

}

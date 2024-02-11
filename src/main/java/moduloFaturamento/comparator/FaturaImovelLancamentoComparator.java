package moduloFaturamento.comparator;

import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;

public class FaturaImovelLancamentoComparator extends AbstractDTOComparator<FaturaImovelLancamentoProjectionDTO> {

	@Override
	public int compare(FaturaImovelLancamentoProjectionDTO o1, FaturaImovelLancamentoProjectionDTO o2) {

		boolean isDesc = "DESC".equalsIgnoreCase(getOrdem());

		switch (getCampo()) {

		case "codigoServico":

			if (isDesc) {

				return o2.getCodigoServico().compareTo(o1.getCodigoServico());
			}
			return o1.getCodigoServico().compareTo(o2.getCodigoServico());

		case "valorServico":
			if (isDesc) {

				return o2.getValorServico().compareTo(o1.getValorServico());
			}
			return o1.getValorServico().compareTo(o2.getValorServico());

		case "creditoDebito":
			if (isDesc) {

				return o2.getCreditoDebito().compareTo(o1.getCreditoDebito());
			}
			return o1.getCreditoDebito().compareTo(o2.getCreditoDebito());
		}

		return 0;
	}
}

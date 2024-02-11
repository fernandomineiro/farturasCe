package moduloFaturamento.comparator;

import moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO;

public class CobrancaFaturaGridComparator extends AbstractDTOComparator<CobrancaFaturaRespostaGridProjectionDTO> {

	@Override
	public int compare(CobrancaFaturaRespostaGridProjectionDTO o1, CobrancaFaturaRespostaGridProjectionDTO o2) {

		switch (getCampo()) {

		case "id":
			return compareSafeForNull(o1.getId(), o2.getId());

		case "cdCobranca":
			return compareSafeForNull(o1.getCdCobranca(), o2.getCdCobranca());

		case "servico":
			return compareSafeForNull(o1.getServico(), o2.getServico());

		case "valorCobranca":
			return compareSafeForNull(o1.getValorCobranca(), o2.getValorCobranca());

		case "dataInclusao":
			return compareSafeForNull(o1.getDataInclusao(), o2.getDataInclusao());

		case "responsavelInclusao":
			return compareSafeForNull(o1.getResponsavelInclusao(), o2.getResponsavelInclusao());

		case "referenciaFaturada":
			return compareSafeForNull(o1.getReferenciaFaturada(), o2.getReferenciaFaturada());

		case "numeroItemAtendimento":
			return compareSafeForNull(o1.getNumeroItemAtendimento(), o2.getNumeroItemAtendimento());

		case "numeroSolicitacaoServico":
			return compareSafeForNull(o1.getNumeroSolicitacaoServico(), o2.getNumeroSolicitacaoServico());

		default:
			return 0;
		}
	}

}

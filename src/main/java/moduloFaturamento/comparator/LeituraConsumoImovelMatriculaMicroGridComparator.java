package moduloFaturamento.comparator;

import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO;

public class LeituraConsumoImovelMatriculaMicroGridComparator extends AbstractDTOComparator<LeituraConsumoImovelMicroRespostaGridProjectionDTO> {

	@Override
	public int compare(LeituraConsumoImovelMicroRespostaGridProjectionDTO o1, LeituraConsumoImovelMicroRespostaGridProjectionDTO o2) {

		switch (getCampo()) {

		case "id":
			return compareSafeForNull(o1.getId(), o2.getId());

		case "matricula":
			return compareSafeForNull(o1.getMatricula(), o2.getMatricula());

		case "dv":
			return compareSafeForNull(o1.getDv(), o2.getDv());

		case "consumoMedido":
			return compareSafeForNull(o1.getConsumoMedido(), o2.getConsumoMedido());

		default:
			return 0;
		}
	}

}

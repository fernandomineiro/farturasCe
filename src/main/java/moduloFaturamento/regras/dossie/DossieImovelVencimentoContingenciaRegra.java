package moduloFaturamento.regras.dossie;

import org.springframework.stereotype.Service;

import moduloFaturamento.model.common.DossieImovel;

@Service
public class DossieImovelVencimentoContingenciaRegra extends DossieImovelRegra {
	
	public DossieImovel construirDossieImovel(String dcAnotacao, Integer matriculaImovel, Short dv, String idUsuario) {

		return super.construirDossieImovel((short) 0, 0, dcAnotacao, "Dossiê", matriculaImovel, dv, (short) 0, idUsuario);
	}
}

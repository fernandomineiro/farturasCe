package moduloFaturamento.regras.dossieCliente;

import java.time.LocalDateTime;

import moduloFaturamento.model.common.DossieCliente;

public abstract class DossieClienteRegra {

	protected DossieCliente construirDossieImovel(Integer cdCliente, String dcAnotacao, String dcObjeto, String loginUsuario) {

		DossieCliente dossieCliente = new DossieCliente();

		dossieCliente.setCdCliente(cdCliente);
		dossieCliente.setDcAnotacao(dcAnotacao);
		dossieCliente.setDcObjeto(dcObjeto);
		dossieCliente.setLoginUsuario(loginUsuario);

		LocalDateTime dataHoraAtual = LocalDateTime.now();
		dossieCliente.setDataHoraInclusao(dataHoraAtual);
	
		return dossieCliente;
	}

}

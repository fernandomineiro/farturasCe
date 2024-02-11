package moduloFaturamento.regras.dossie;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.model.common.IdDossieImovel;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.util.ConverterData;

public abstract class DossieImovelRegra {

	@Autowired
	private DossieImovelRepository dossieImovelRepository;

	protected DossieImovel construirDossieImovel(Short cdAnotacao, Integer cdCliente, String dcAnotacao, String dcObjeto, Integer matricula, Short dv, Short dvCliente,
			String idUsuario) {

		DossieImovel dossieImovel = new DossieImovel();

		IdDossieImovel idDossieImovel = new IdDossieImovel();

		Integer anoMesDia = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());
		idDossieImovel.setDtAnotacao(anoMesDia);
		idDossieImovel.setMatriculaImovel(matricula);
		idDossieImovel.setSeqAnotacao(dossieImovelRepository.buscarSequenciaPorDataEMatricula(matricula));

		dossieImovel.setIdDossieImovel(idDossieImovel);

		LocalDateTime now = LocalDateTime.now();
		dossieImovel.setDataHoraInclusao(now);

		dossieImovel.setHrAnotacao((short)now.getHour());
		dossieImovel.setMinAnotacao((short)now.getMinute());

		dossieImovel.setMaint('A');

		dossieImovel.setDv(dv);

		dossieImovel.setCdAnotacao(cdAnotacao);
		dossieImovel.setDcAnotacao(dcAnotacao);

		dossieImovel.setCdCliente(cdCliente);
		dossieImovel.setDvC(dvCliente);

		dossieImovel.setDcObjeto(dcObjeto);

		dossieImovel.setIdUsuario(idUsuario);

		return dossieImovel;
	}

	protected DossieImovel atualizarDossieImovel(Short cdAnotacao, Integer cdCliente, String dcAnotacao, String dcObjeto, Integer matricula, Short dv, Short dvCliente,
			String idUsuario, Integer dataAnotacao) {

		DossieImovel dossieImovel = new DossieImovel();

		IdDossieImovel idDossieImovel = new IdDossieImovel();

		idDossieImovel.setDtAnotacao(dataAnotacao);
		idDossieImovel.setMatriculaImovel(matricula);
		idDossieImovel.setSeqAnotacao(dossieImovelRepository.buscarSequenciaPorDataEMatricula(matricula));

		dossieImovel.setIdDossieImovel(idDossieImovel);

		LocalDateTime now = LocalDateTime.now();
		dossieImovel.setDataHoraInclusao(now);

		dossieImovel.setHrAnotacao((short)now.getHour());
		dossieImovel.setMinAnotacao((short)now.getMinute());

		dossieImovel.setMaint('A');

		dossieImovel.setDv(dv);

		dossieImovel.setCdAnotacao(cdAnotacao);
		dossieImovel.setDcAnotacao(dcAnotacao);

		dossieImovel.setCdCliente(cdCliente);
		dossieImovel.setDvC(dvCliente);

		dossieImovel.setDcObjeto(dcObjeto);

		dossieImovel.setIdUsuario(idUsuario);

		return dossieImovel;
	}
}

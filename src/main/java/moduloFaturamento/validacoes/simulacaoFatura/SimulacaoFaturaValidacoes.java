package moduloFaturamento.validacoes.simulacaoFatura;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.util.ConverterData;

abstract class SimulacaoFaturaValidacoes {

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private FaturaRepository faturaRepository;

	protected void gerarExcessaoMatriculaInexistente(Integer matricula) {

		Optional<Imovel> optional = imovelRepository.findById(matricula);

		if (optional.isEmpty()) {

			throw new MsgGenericaPersonalizadaException(String.format("Não existe imóvel para a matrícula %d.", matricula));
		}
	}

	protected void gerarExcessaoMatriculaInexistenteSeInformada(Integer matricula) {

		if (matricula != null) {

			gerarExcessaoMatriculaInexistente(matricula);
		}
	}

	protected void gerarExcessaoFaturaInexistenteParaReferencia(Integer matricula, LocalDate refFatura) {

		Integer referencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(refFatura);

		Fatura fatura = faturaRepository.buscarUltimaFaturaDoImovel(matricula, referencia);
		
		if(fatura == null) {
			String refText = ConverterData.localDateParaReferenciaApresentacao(refFatura);
			throw new MsgGenericaPersonalizadaException(String.format("Não existe fatura para a referecia %s.", refText));
		}
	}

	protected void gerarExcessaoOcorrenciaDeLeituraComMatriculaInexistente(Integer matricula, Short cdOcorrencia) {

		if(cdOcorrencia != null && matricula == null) {
			throw new MsgGenericaPersonalizadaException(String.format("Não é possivel fazer simulação sem matrícula quando existe ocorrencia de leitura."));
		}
	}
}

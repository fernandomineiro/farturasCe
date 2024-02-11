package moduloFaturamento.validacoes.mensagemFatura;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaCicloProjectionDTO;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.IdMensagemFatura;
import moduloFaturamento.model.MensagemFatura;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.MensagemFaturaRepository;

public abstract class MensagemFaturaValidacao {

	@Autowired
	private CronogramaFaturaRepository cronogramaFaturaRepository;

	@Autowired
	private MensagemFaturaRepository mensagemFaturaRepository;

	protected void gerarExcessaoCicloCronogramaFechado(Short ciclo, Short cdCidade, Integer refFatura) {

		Optional<CronogramaFaturaCicloProjectionDTO> optional = cronogramaFaturaRepository.buscarCronogramaPorLocalidadeEReferenciaCiclo(ciclo, cdCidade,
				refFatura);

		if (optional.isPresent() && "S".equals(optional.get().getCicloFechado())) {

			throw new MsgGenericaPersonalizadaException("O ciclo está fechado para esta localidade e referência");
		}
	}

	protected void gerarExcessaoMensagemDuplicadaParaCicloCidadeEReferencia(Short ciclo, Short cdCidade, Integer refFatura) {

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura();
		idMensagemFatura.setCiclo(ciclo);
		idMensagemFatura.setCdCidade(cdCidade);
		idMensagemFatura.setRefFatura(refFatura);

		Optional<MensagemFatura> optional = this.mensagemFaturaRepository.findById(idMensagemFatura);
		if (optional.isPresent()) {

			throw new MsgGenericaPersonalizadaException("Já existe mensagem para este ciclo, localidade, referência.");
		}
	}

	protected void gerarExcessaoMensagemInexistenteParaCicloCidadeEReferencia(Short ciclo, Short cdCidade, Integer refFatura) {

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura();
		idMensagemFatura.setCiclo(ciclo);
		idMensagemFatura.setCdCidade(cdCidade);
		idMensagemFatura.setRefFatura(refFatura);

		Optional<MensagemFatura> optional = this.mensagemFaturaRepository.findById(idMensagemFatura);
		if (optional.isEmpty()) {

			throw new MsgGenericaPersonalizadaException("Mensagem não cadastrada para este ciclo, localidade, referencia");
		}
	}
}

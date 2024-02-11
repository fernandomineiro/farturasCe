package moduloFaturamento.validacoes.notificacaoFatura;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.model.NotificacaoFatura;
import moduloFaturamento.model.NotificacaoFaturaMatricula;
import moduloFaturamento.repository.NotificacaoFaturaRepository;

public abstract class NotificacaoFaturaValidacao {

	@Autowired
	private NotificacaoFaturaRepository notificacaoFaturaRepository;

	protected void gerarExcessaoCicloCronogramaFechado(Long idNotificacao) {

		this.gerarExcessaoNotificacaoInexistente(idNotificacao);

		NotificacaoFatura notificacaoFatura = notificacaoFaturaRepository.findById(idNotificacao).get();

		Integer refFatura = notificacaoFatura.getRefFatura();
		List<NotificacaoFaturaMatricula> faturaMatriculas = notificacaoFatura.getNotificacaoFaturaMatriculas();
		List<Integer> matriculas = faturaMatriculas.stream().map(fm -> {

			return fm.getMatricula();
		}).collect(Collectors.toList());

		this.gerarExcessaoMatriculasEmCicloFechado(refFatura, matriculas);
	}

	protected void gerarExcessaoNotificacaoInexistente(Long idNotificacao) {

		Optional<NotificacaoFatura> optional = this.notificacaoFaturaRepository.findById(idNotificacao);

		if (optional.isEmpty()) {

			throw new MsgGenericaPersonalizadaException("Notificacao não encontrada");
		}
	}

	protected void gerarExcessaoMatriculasEmCicloFechado(Integer refFatura, List<Integer> matriculas) {

		Long count = notificacaoFaturaRepository.buscarMatriculasEmCicloFechado(refFatura, matriculas);

		if (count != 0) {

			throw new MsgGenericaPersonalizadaException("Existem matrículas em ciclo fechado na lista.");
		}
	}
}

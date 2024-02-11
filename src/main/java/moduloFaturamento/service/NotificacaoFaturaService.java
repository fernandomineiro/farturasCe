package moduloFaturamento.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaFilterDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaAlteracaoDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCadastroDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaRemocaoDTO;

public interface NotificacaoFaturaService {

	GenericoWrapperDTO<List<NotificacaoFaturaCriticaGridRespostaDTO>> executarFluxoCriticaListar(@Valid NotificacaoFaturaCriticaDTO dto, Pageable pageable,
			String token);

	GenericoWrapperDTO<List<MensagemNotificacaoFaturaGridRespostaDTO>> executarFluxoMensagemNotificacaoFaturaListar(
			@Valid MensagemNotificacaoFaturaFilterDTO dto, Pageable pageable, String token);

	NotificacaoFaturaGravadaRespostaDTO buscarNotificacaoFatura(Long id, String token);
	
	NotificacaoFaturaGravadaRespostaDTO executarFluxoCadastrar(NotificacaoFaturaCadastroDTO dto, String token);

	NotificacaoFaturaGravadaRespostaDTO executarFluxoAtualizar(NotificacaoFaturaAlteracaoDTO dto, String token);

	void executarFluxoRemover(NotificacaoFaturaRemocaoDTO dto, String token);

}

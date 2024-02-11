package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaAlteracaoDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaCadastroDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaRemocaoDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaFilterDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaAlteracaoDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCadastroDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaRemocaoDTO;
import moduloFaturamento.service.MensagemFaturaService;
import moduloFaturamento.service.NotificacaoFaturaService;
import moduloFaturamento.service.common.LocalidadeService;

@RestController
@RequestMapping("/backend-faturamento/faturamentoMensagem")
public class MensagemNotificacaoFaturaController {

	@Autowired
	private LocalidadeService localidadeService;

	@Autowired
	private MensagemFaturaService mensagemFaturaService;

	@Autowired
	private NotificacaoFaturaService notificacaoFaturaService;

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/listarLocalidades")
	public List<LocalidadeDTO> listarLocalidades(@RequestHeader("Authorization") String token) {

		return localidadeService.buscarTodasLocalidades();
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarDropDownCiclo")
	public List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(@NotNull Short cdCidade, @NotNull Integer referencia,
			@RequestHeader("Authorization") String token) {

		return mensagemFaturaService.buscarDropDownCiclo(cdCidade, referencia);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/mensagemNotificacaoFaturaListar")
	public GenericoWrapperDTO<List<MensagemNotificacaoFaturaGridRespostaDTO>> mensagemNotificacaoFaturaListar(@Valid MensagemNotificacaoFaturaFilterDTO dto,
			Pageable pageable, @RequestHeader("Authorization") String token) {

		return notificacaoFaturaService.executarFluxoMensagemNotificacaoFaturaListar(dto, pageable, token);
	}

	// ----------------------------------------------------------------------------- Mensagem (mensagem curta)

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarMensagemFatura")
	public MensagemFaturaGravadaRespostaDTO buscarMensagemFatura(Short cdCidade, Short ciclo, Integer referencia,
			@RequestHeader("Authorization") String token) {

		return mensagemFaturaService.buscarMensagemFatura(cdCidade, ciclo, referencia, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/mensagemFaturaCadastrar")
	public MensagemFaturaGravadaRespostaDTO mensagemFaturaCadastrar(@RequestBody @Valid MensagemFaturaCadastroDTO dto,
			@RequestHeader("Authorization") String token) {

		return mensagemFaturaService.executarFluxoCadastrar(dto, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/mensagemFaturaAlterar")
	public MensagemFaturaGravadaRespostaDTO mensagemFaturaAlterar(@RequestBody @Valid MensagemFaturaAlteracaoDTO dto,
			@RequestHeader("Authorization") String token) {

		return mensagemFaturaService.executarFluxoAtualizar(dto, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/mensagemFaturaRemover")
	public void mensagemFaturaRemover(@Valid MensagemFaturaRemocaoDTO dto, @RequestHeader("Authorization") String token) {

		mensagemFaturaService.executarFluxoRemover(dto, token);
	}
	// ----------------------------------------------------------------------------- Notificação (mensagem longa)

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarNotificacaoFatura")
	public NotificacaoFaturaGravadaRespostaDTO buscarNotificacaoFatura(Long id, @RequestHeader("Authorization") String token) {
		return notificacaoFaturaService.buscarNotificacaoFatura(id, token);
	}
	
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/notificacaoFaturaCritica")
	public GenericoWrapperDTO<List<NotificacaoFaturaCriticaGridRespostaDTO>> notificacaoFaturaCriticaListar(@Valid NotificacaoFaturaCriticaDTO dto,
			Pageable pageable, @RequestHeader("Authorization") String token) {

		return notificacaoFaturaService.executarFluxoCriticaListar(dto, pageable, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/notificacaoFaturaCadastrar")
	public NotificacaoFaturaGravadaRespostaDTO notificacaoFaturaCadastrar(@RequestBody @Valid NotificacaoFaturaCadastroDTO dto,
			@RequestHeader("Authorization") String token) {

		return notificacaoFaturaService.executarFluxoCadastrar(dto, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/notificacaoFaturaAlterar")
	public NotificacaoFaturaGravadaRespostaDTO notificacaoFaturaAlterar(@RequestBody @Valid NotificacaoFaturaAlteracaoDTO dto,
			@RequestHeader("Authorization") String token) {

		return notificacaoFaturaService.executarFluxoAtualizar(dto, token);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/notificacaoFaturaRemover")
	public void notificacaoFaturaRemover(@Valid NotificacaoFaturaRemocaoDTO dto, @RequestHeader("Authorization") String token) {

		notificacaoFaturaService.executarFluxoRemover(dto, token);
	}

}

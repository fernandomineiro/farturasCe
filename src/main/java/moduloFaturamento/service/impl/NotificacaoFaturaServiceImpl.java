package moduloFaturamento.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.MensagemNotificacaoFaturaGridComparator;
import moduloFaturamento.comparator.NotificacaoFaturaCriticaGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaFilterDTO;
import moduloFaturamento.dto.mensgemNotificacaoFatura.MensagemNotificacaoFaturaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaAlteracaoDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCadastroDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaCriticaGridRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.notificaoFatura.NotificacaoFaturaRemocaoDTO;
import moduloFaturamento.dto.notificaoFatura.mapper.NotificacaoFaturaGravadaRespostaMapper;
import moduloFaturamento.dto.notificaoFatura.projection.MensagemNotificacaoFaturaGridProjectionDTO;
import moduloFaturamento.dto.notificaoFatura.projection.NotificacaoFaturaCriticaGridProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.NotificacaoFatura;
import moduloFaturamento.model.NotificacaoFaturaMatricula;
import moduloFaturamento.regras.mecanicas.notificacaoFatura.NotificacaoFaturaCriticaRegra;
import moduloFaturamento.repository.NotificacaoFaturaRepository;
import moduloFaturamento.service.NotificacaoFaturaService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterMatricula;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.notificacaoFatura.NotificacaoFaturaSiscomValidacao;

@Service
@Transactional
public class NotificacaoFaturaServiceImpl implements NotificacaoFaturaService {

	// --------------------------------------------------------- Repository

	@Autowired
	private NotificacaoFaturaRepository notificacaoFaturaRepository;

	// --------------------------------------------------------- Validacao

	@Autowired
	private NotificacaoFaturaSiscomValidacao notificacaoFaturaSiscomValidacao;

	// --------------------------------------------------------- Regra

	@Autowired
	private NotificacaoFaturaCriticaRegra notificacaoFaturaCriticaRegra;

	// --------------------------------------------------------- Mapper

	@Autowired
	private NotificacaoFaturaGravadaRespostaMapper notificacaoFaturaGravadaRespostaMapper;

	// --------------------------------------------------------- Services

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// --------------------------------------------------------- Externo

	@Override
	public NotificacaoFaturaGravadaRespostaDTO buscarNotificacaoFatura(Long id, String token) {

		notificacaoFaturaSiscomValidacao.gerenciarValidacaoBuscarNotificao(id);

		NotificacaoFatura entity = notificacaoFaturaRepository.findById(id).get();
		List<Short> fases = notificacaoFaturaRepository.buscarFasesCronogramaMatriculasPorNotificacaoId(id);

		Integer sum = fases.stream().mapToInt(e -> e).sum();

		NotificacaoFaturaGravadaRespostaDTO dto = notificacaoFaturaGravadaRespostaMapper.toDto(entity);

		dto.setCicloFechado(sum != 0);

		return dto;
	}

	@Override
	public GenericoWrapperDTO<List<NotificacaoFaturaCriticaGridRespostaDTO>> executarFluxoCriticaListar(@Valid NotificacaoFaturaCriticaDTO dto,
			Pageable pageable, String token) {

		Integer refCronograma = dto.getRefCronograma();
		notificacaoFaturaSiscomValidacao.gerenciarValidacaoReferenciaCritica(refCronograma);

		List<Integer> matriculas = notificacaoFaturaCriticaRegra.validarEConverterMatriculas(dto.getMatriculas());

		List<NotificacaoFaturaCriticaGridProjectionDTO> criticaNotificacoesFatura = notificacaoFaturaRepository
				.buscarDadosCriticaNotificacoesFatura(refCronograma, matriculas);

		List<NotificacaoFaturaCriticaGridRespostaDTO> collect = criticaNotificacoesFatura.stream().map(projection -> {

			NotificacaoFaturaCriticaGridRespostaDTO dtoResposta = new NotificacaoFaturaCriticaGridRespostaDTO();

			dtoResposta.setCdCidade(projection.getCdCidade());
			dtoResposta.setDcCidade(projection.getDcCidade());
			dtoResposta.setCiclo(projection.getCiclo());
			dtoResposta.setRefCronograma(projection.getReferencia());
			dtoResposta.setMatricula(projection.getMatricula());
			dtoResposta.setMatriculaDv(projection.getMatricula() + "-" + projection.getDV());
			dtoResposta.setCicloFechado("S".equals(projection.getCicloFechado()));
			dtoResposta.setExisteOutraNotificacao("S".equals(projection.getExisteOutraNotificacao()));

			List<String> criticas = new ArrayList<>();

			if (dtoResposta.getCicloFechado())
				criticas.add("Ciclo Fechado");
			if (dtoResposta.getExisteOutraNotificacao())
				criticas.add("Existe outra notificação");

			if (criticas.size() == 0) {

				criticas.add("Ok");
			}

			dtoResposta.setCriticas(criticas);

			return dtoResposta;
		}).collect(Collectors.toList());

		GenericoWrapperDTO<List<NotificacaoFaturaCriticaGridRespostaDTO>> wrapperDTO = Paginacao.paginarCampoUnico(new NotificacaoFaturaCriticaGridComparator(),
				pageable, collect);

		return wrapperDTO;

	}

	@Override
	public GenericoWrapperDTO<List<MensagemNotificacaoFaturaGridRespostaDTO>> executarFluxoMensagemNotificacaoFaturaListar(
			MensagemNotificacaoFaturaFilterDTO dto, Pageable pageable, String token) {

		Integer refCronograma = dto.getRefCronograma();
		Short cdCidade = dto.getCdCidade();
		Short ciclo = dto.getCiclo();
		Integer matricula = ConverterMatricula.matriculaSemDV(dto.getMatricula());

		List<MensagemNotificacaoFaturaGridProjectionDTO> mensagensNotificacoesFatura = matricula == null
				? notificacaoFaturaRepository.buscarListaMensagensNotificacoesFatura(refCronograma, cdCidade, ciclo)
				: notificacaoFaturaRepository.buscarListaNotificacoesFatura(refCronograma, matricula);
		
		List<MensagemNotificacaoFaturaGridRespostaDTO> collect = mensagensNotificacoesFatura.stream().map(projection -> {

			MensagemNotificacaoFaturaGridRespostaDTO respostaDto = new MensagemNotificacaoFaturaGridRespostaDTO();

			respostaDto.setIdNotificacao(projection.getIdNotificacao());
			respostaDto.setRefCronograma(projection.getRefCronograma());
			respostaDto.setCiclo(projection.getCiclo());
			respostaDto.setCdCidade(projection.getCdCidade());
			respostaDto.setDcCidade(projection.getDcCidade());
			respostaDto.setMensagem(projection.getMensagem());
			respostaDto.setFlagMensagemLonga("S".equals(projection.getFlagMensagemLonga()));
			respostaDto.setCicloFechado("S".equals(projection.getCicloFechado()));

			return respostaDto;
		}).collect(Collectors.toList());

		GenericoWrapperDTO<List<MensagemNotificacaoFaturaGridRespostaDTO>> wrapperDTO = Paginacao
				.paginarCampoUnico(new MensagemNotificacaoFaturaGridComparator(), pageable, collect);

		return wrapperDTO;

	}

	@Override
	public NotificacaoFaturaGravadaRespostaDTO executarFluxoCadastrar(NotificacaoFaturaCadastroDTO dto, String token) {

		Integer referencia = dto.getReferencia();

		List<Integer> matriculas = dto.getMatriculas();

		notificacaoFaturaSiscomValidacao.gerenciarValidacaoCadastramentoNotificao(referencia, matriculas);

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		NotificacaoFatura notificacao = new NotificacaoFatura();

		notificacao.setRefFatura(referencia);
		notificacao.setMensagem(dto.getMensagem());

		List<NotificacaoFaturaMatricula> notificacaoFaruraMatriculas = matriculas.stream().map(matricula -> {

			NotificacaoFaturaMatricula faturaMatricula = new NotificacaoFaturaMatricula();
			faturaMatricula.setMatricula(matricula);
			faturaMatricula.setNotificacaoFatura(notificacao);
			return faturaMatricula;
		}).collect(Collectors.toList());

		notificacao.setNotificacaoFaturaMatriculas(notificacaoFaruraMatriculas);

		NotificacaoFatura gravada = cadastrarNotificacaoFaturaValidada(notificacao, idUsuario);

		return notificacaoFaturaGravadaRespostaMapper.toDto(gravada);
	}

	@Override
	public NotificacaoFaturaGravadaRespostaDTO executarFluxoAtualizar(NotificacaoFaturaAlteracaoDTO dto, String token) {

		Long idNotificacao = dto.getId();

		notificacaoFaturaSiscomValidacao.gerenciarValidacaoAlteracaoNotificaoExistente(idNotificacao);

		NotificacaoFatura notificacaoFatura = notificacaoFaturaRepository.findById(idNotificacao).get();

		String mensagem = dto.getMensagem();

		notificacaoFatura.setMensagem(mensagem != null && mensagem.length() != 0 ? mensagem : notificacaoFatura.getMensagem());

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		NotificacaoFatura gravada = atualizarNotificacaoFaturaValidada(notificacaoFatura, idUsuario);

		return notificacaoFaturaGravadaRespostaMapper.toDto(gravada);
	}

	@Override
	public void executarFluxoRemover(NotificacaoFaturaRemocaoDTO dto, String token) {

		notificacaoFaturaSiscomValidacao.gerenciarValidacaoRemocaoNotificao(dto.getId());

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		removerNotificacaoFaturaValidada(dto.getId(), idUsuario);
	}

	// --------------------------------------------------------- interno

	private NotificacaoFatura cadastrarNotificacaoFaturaValidada(NotificacaoFatura notificacao, Long idUsuario) {

		String entidadeDepois = ConvertObjectToJsonCesan.execute(notificacao);

		notificacao = notificacaoFaturaRepository.save(notificacao);

		gerarAuditoria(notificacao.getId(), Constants.EMPTY_STRING, entidadeDepois, idUsuario);

		return notificacao;
	}

	private NotificacaoFatura atualizarNotificacaoFaturaValidada(NotificacaoFatura notificacao, Long idUsuario) {

		String entidadeAntes = ConvertObjectToJsonCesan.execute(notificacao);

		notificacao = notificacaoFaturaRepository.save(notificacao);

		String entidadeDepois = ConvertObjectToJsonCesan.execute(notificacao);

		gerarAuditoria(notificacao.getId(), entidadeAntes, entidadeDepois, idUsuario);

		return notificacao;
	}

	private void removerNotificacaoFaturaValidada(Long idNotificacao, Long idUsuario) {

		NotificacaoFatura notificacaoFatura = notificacaoFaturaRepository.findById(idNotificacao).get();

		String entidadeAntes = ConvertObjectToJsonCesan.execute(notificacaoFatura);

		notificacaoFaturaRepository.delete(notificacaoFatura);

		gerarAuditoria(notificacaoFatura.getId(), entidadeAntes, Constants.EMPTY_STRING, idUsuario);
	}

	private void gerarAuditoria(Long id, String entidadeAntes, String entidadeDepois, Long idUsuario) {

		auditoriaService.gerarAuditoria(id, entidadeAntes, entidadeDepois, 63L, "Notificacao fatura", idUsuario);
	}
}

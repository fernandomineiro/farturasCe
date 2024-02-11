package moduloFaturamento.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaAlteracaoDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaCadastroDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaRemocaoDTO;
import moduloFaturamento.dto.mensgemFatura.mapper.MensagemFaturaGravadaRespostaMapper;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IdMensagemFatura;
import moduloFaturamento.model.MensagemFatura;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoCicloRegra;
import moduloFaturamento.repository.MensagemFaturaRepository;
import moduloFaturamento.service.MensagemFaturaService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.validacoes.mensagemFatura.MensagemFaturaSiscomValidacao;

@Service
@Transactional
public class MensagemFaturaServiceImpl implements MensagemFaturaService {

	// ------------------------------------------------------------ Repository

	@Autowired
	private MensagemFaturaRepository mensagemFaturaRepository;

	// ------------------------------------------------------------ Validacao

	@Autowired
	private MensagemFaturaSiscomValidacao mensagemFaturaSiscomValidacao;

	// ------------------------------------------------------------ Regra

	@Autowired
	private CronogramaFaturamentoCicloRegra cronogramaFaturamentoCicloRegra;

	// ------------------------------------------------------------ Mapper

	@Autowired
	private MensagemFaturaGravadaRespostaMapper mensagemFaturaGravadaRespostaMapper;

	// ------------------------------------------------------------ Services

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// ------------------------------------------------------------ Externo

	@Override
	public MensagemFaturaGravadaRespostaDTO buscarMensagemFatura(Short cdCidade, Short ciclo, Integer referencia, String token) {

		mensagemFaturaSiscomValidacao.gerenciarValidacaoParaBuscar(ciclo, cdCidade, referencia);

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura(cdCidade, ciclo, referencia);

		MensagemFaturaGravadaRespostaDTO dto = mensagemFaturaGravadaRespostaMapper.toDto(mensagemFaturaRepository.findById(idMensagemFatura).get());

		Optional<Short> fase = mensagemFaturaRepository.buscarFaseCronogramaDaMensagem(cdCidade, ciclo, referencia);
		dto.setCicloFechado(fase.isEmpty() || (fase.isPresent() && fase.get() != 0));
		
//		Integer fasesNaoZero = mensagemFaturaRepository.buscarCronogramasFaseNaoZeroParaReferencia(referencia);
//		dto.setCicloFechado(fasesNaoZero != 0);

		return dto;
	}

	@Override
	public List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(Short cdCidade, Integer referencia) {

		return cronogramaFaturamentoCicloRegra.buscarDropDownCiclo(cdCidade, referencia);
	}

	@Override
	public MensagemFaturaGravadaRespostaDTO executarFluxoCadastrar(MensagemFaturaCadastroDTO dto, String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		Short ciclo = dto.getCiclo() == null ? 0 : dto.getCiclo();
		Short cdCidade = dto.getCdCidade() == null ? 0 : dto.getCdCidade();
		Integer referencia = dto.getReferencia();

		mensagemFaturaSiscomValidacao.gerenciarValidacaoParaCadastrar(ciclo, cdCidade, referencia);

		MensagemFatura mensagem = new MensagemFatura();

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura(cdCidade, ciclo, referencia);

		mensagem.setIdMensagemFatura(idMensagemFatura);
		mensagem.setMaint("A");
		mensagem.setMensagem01(dto.getLinha01());
		mensagem.setMensagem02(dto.getLinha02() == null ? "" : dto.getLinha02());

		mensagem = cadastrarMensagemFaturaValidada(mensagem, idUsuario);

		return mensagemFaturaGravadaRespostaMapper.toDto(mensagem);
	}

	@Override
	public MensagemFaturaGravadaRespostaDTO executarFluxoAtualizar(MensagemFaturaAlteracaoDTO dto, String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		Short ciclo = dto.getCiclo();
		Short cdCidade = dto.getCdCidade();
		Integer referencia = dto.getReferencia();

		mensagemFaturaSiscomValidacao.gerenciarValidacaoParaAtualizar(ciclo, cdCidade, referencia);

		MensagemFatura mensagem = new MensagemFatura();

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura(cdCidade, ciclo, referencia);

		mensagem.setIdMensagemFatura(idMensagemFatura);
		mensagem.setMaint("A");
		mensagem.setMensagem01(dto.getLinha01());
		mensagem.setMensagem02(dto.getLinha02() == null ? "" : dto.getLinha02());

		mensagem = atualizarMensagemFaturaValidada(mensagem, idUsuario);

		return mensagemFaturaGravadaRespostaMapper.toDto(mensagem);

	}

	@Override
	public void executarFluxoRemover(MensagemFaturaRemocaoDTO dto, String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		Short ciclo = dto.getCiclo();
		Short cdCidade = dto.getCdCidade();
		Integer referencia = dto.getReferencia();

		mensagemFaturaSiscomValidacao.gerenciarValidacaoParaRemover(ciclo, cdCidade, referencia);

		IdMensagemFatura idMensagemFatura = new IdMensagemFatura(cdCidade, ciclo, referencia);

		removerMensagemFaturaValidada(idMensagemFatura, idUsuario);
	}

	// ------------------------------------------------------------ Interno

	private MensagemFatura cadastrarMensagemFaturaValidada(MensagemFatura mensagem, Long idUsuario) {

		mensagem = mensagemFaturaRepository.save(mensagem);

		String entityNovaJSON = ConvertObjectToJsonCesan.execute(mensagem);

		gerarAuditoria(mensagem.getIdMensagemFatura(), Constants.EMPTY_STRING, entityNovaJSON, idUsuario);

		return mensagem;
	}

	private MensagemFatura atualizarMensagemFaturaValidada(MensagemFatura mensagem, Long idUsuario) {

		Optional<MensagemFatura> mensgemFaturaAntigaOP = mensagemFaturaRepository.findById(mensagem.getIdMensagemFatura());

		MensagemFatura mensagemFaturaAntiga = mensgemFaturaAntigaOP.get();

		mensagem = mensagemFaturaRepository.save(mensagem);

		String entityAntigaJSON = ConvertObjectToJsonCesan.execute(mensagemFaturaAntiga);
		String entityNovaJSON = ConvertObjectToJsonCesan.execute(mensagem);

		gerarAuditoria(mensagem.getIdMensagemFatura(), entityAntigaJSON, entityNovaJSON, idUsuario);

		return mensagem;
	}

	private void removerMensagemFaturaValidada(IdMensagemFatura idMensagemFatura, Long idUsuario) {

		Optional<MensagemFatura> mensgemFaturaAntigaOP = mensagemFaturaRepository.findById(idMensagemFatura);

		MensagemFatura mensagemFaturaAntiga = mensgemFaturaAntigaOP.get();

		mensagemFaturaRepository.deleteById(idMensagemFatura);

		String entityAntigaJSON = ConvertObjectToJsonCesan.execute(mensagemFaturaAntiga);

		gerarAuditoria(idMensagemFatura, entityAntigaJSON, Constants.EMPTY_STRING, idUsuario);

	}

	private void gerarAuditoria(IdMensagemFatura idMensagemFatura, String entidadeAntes, String entidadeDepois, Long idUsuario) {

		Long composta = gerarIdAuditoriaComposta(idMensagemFatura);

		auditoriaService.gerarAuditoria(composta, entidadeAntes, entidadeDepois, 62L, "Mensagem fatura", idUsuario);
	}

	private Long gerarIdAuditoriaComposta(IdMensagemFatura idMensagemFatura) {

		return Long.valueOf(idMensagemFatura.getRefFatura().toString() + idMensagemFatura.getCdCidade() + idMensagemFatura.getCiclo());
	}

}

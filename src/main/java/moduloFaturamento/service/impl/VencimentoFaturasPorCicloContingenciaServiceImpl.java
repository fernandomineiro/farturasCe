package moduloFaturamento.service.impl;

import static moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum.EMITIR_FATURA;
import static moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum.ENCERRADO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaFaseDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoAlterarFilterDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoAlterarResumoRespostaDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoVerificarFilterDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoVerificarResumoRespostaDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdFatura;
import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.rabbitmq.produtores.mensagens.AuditoriaMsgProdutor;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoCicloRegra;
import moduloFaturamento.regras.dossie.DossieImovelVencimentoContingenciaRegra;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.repository.common.DossieImovelRepository;
import moduloFaturamento.service.CronogramaFaturamentoService;
import moduloFaturamento.service.VencimentoFaturasPorCicloContingenciaService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.validacoes.vencimentoFaturasPorCicloContingencia.VencimentoFaturasPorCicloContingenciaAlterarValidacao;

@Service
@Transactional
public class VencimentoFaturasPorCicloContingenciaServiceImpl implements VencimentoFaturasPorCicloContingenciaService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	// ------------------------------------------------------------ Validacao

	@Autowired
	private VencimentoFaturasPorCicloContingenciaAlterarValidacao vencimentoFaturasPorCicloContingenciaAlterarValidacao;

	// ------------------------------------------------------------ Service

	@Autowired
	private CronogramaFaturamentoService cronogramaFaturamentoService;

	@Autowired
	private FaturaRepository faturaRepository;

	@Autowired
	private DossieImovelRepository dossieImovelRepository;

	// ------------------------------------------------------------ Regra

	@Autowired
	private CronogramaFaturamentoCicloRegra cronogramaFaturamentoCicloRegra;

	@Autowired
	private DossieImovelVencimentoContingenciaRegra dossieImovelVencimentoContingenciaRegra;

	// ------------------------------------------------------------ Auditoria

	@Autowired
	private AuditoriaService auditoriaService;

	@Override
	public List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(Short cdCidade, Integer referencia) {

		return cronogramaFaturamentoCicloRegra.buscarDropDownCiclo(cdCidade, referencia);
	}

	@Override
	public List<CronogramaFaturaFaseDTO> buscarFasesEmitirFaturaEEncerrado() {

		return cronogramaFaturamentoService.buscarFasesCronograma(EMITIR_FATURA, ENCERRADO);
	}

	@Override
	public FaturasVencimentoVerificarResumoRespostaDTO verificarVencimentosParaAlterarEmLote(FaturasVencimentoVerificarFilterDTO dto) {

		vencimentoFaturasPorCicloContingenciaAlterarValidacao.gerenciarConsultaParaAlteracao(dto);

		Integer referencia = dto.getReferencia();
		Short cdCidade = dto.getCdCidade();
		Short ciclo = dto.getCiclo();
		Integer novoVencimento = ConverterData.localDateEmIntegerDataFormatoDB(ConverterData.stringEmLocalDateDataFormatoBR(dto.getNovoVencimento()));
		List<Short> fases = dto.getFases();

		Integer quantidadeFaturas = faturaRepository.consultarQuantidadeFaturasParaAlteracaoEmLote(referencia, cdCidade, ciclo, novoVencimento, fases);

		FaturasVencimentoVerificarResumoRespostaDTO respostaDTO = new FaturasVencimentoVerificarResumoRespostaDTO();

		respostaDTO.setQuantidadeDeFaturas(quantidadeFaturas);
		respostaDTO.setReferencia(ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia));

		return respostaDTO;
	}

	@Override
	public FaturasVencimentoAlterarResumoRespostaDTO executarFluxoAlterarVencimentosEmLote(FaturasVencimentoAlterarFilterDTO dto, String token) {

		vencimentoFaturasPorCicloContingenciaAlterarValidacao.gerenciarParametrosParaAlteracao(dto);

		Integer referencia = dto.getReferencia();
		Short cdCidade = dto.getCdCidade();
		Short ciclo = dto.getCiclo();
		Integer novoVencimento = ConverterData.localDateEmIntegerDataFormatoDB(ConverterData.stringEmLocalDateDataFormatoBR(dto.getNovoVencimento()));
		List<Short> fases = dto.getFases();

		List<Fatura> loteFaturas = faturaRepository.buscarFaturasParaAlteracaoEmLote(referencia, cdCidade, ciclo, novoVencimento, fases);

		List<Fatura> faturasGravadas = alterarDatasDeVencimentoEmLoteValidas(loteFaturas, novoVencimento, token);

		FaturasVencimentoAlterarResumoRespostaDTO respostaDTO = new FaturasVencimentoAlterarResumoRespostaDTO();

		respostaDTO.setQuantidadeDeFaturas(faturasGravadas.size());
		respostaDTO.setReferencia(ConverterData.integerEmLocalDateReferenciaFormatoDB(referencia));

		return respostaDTO;

	}

	private List<Fatura> alterarDatasDeVencimentoEmLoteValidas(List<Fatura> loteFaturas, Integer novoVencimento, String token) {

		String login = jwtTokenProvider.buscarLogin(token);
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		LocalDate dtNovoVencimento = ConverterData.integerEmLocalDateDataFormatoDB(novoVencimento);

		List<Fatura> loteFaturasAGravar = new ArrayList<>();
		List<DossieImovel> loteDossiesAGravar = new ArrayList<>();
		Map<Integer, String> jsonsAntesAuditoriaFatura = new HashMap<>();
		Map<Integer, String> jsonsDepoisAuditoriaFatura = new HashMap<>();

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (Fatura fatura : loteFaturas) {

			LocalDate dtVencimentoOriginal = ConverterData.integerEmLocalDateDataFormatoDB(fatura.getDtVencimento());
			LocalDate dtVencimentoMaisTrinta = dtVencimentoOriginal.plusDays(30);

			LocalDate dtVencimentoFinal = dtNovoVencimento.compareTo(dtVencimentoMaisTrinta) >= 0 ? dtVencimentoMaisTrinta : dtNovoVencimento;

			String jsonFaturaAntes = ConvertObjectToJsonCesan.execute(fatura);
			fatura.setDtVencimento(ConverterData.localDateEmIntegerDataFormatoDB(dtVencimentoFinal));
			String jsonFaturaDepois = ConvertObjectToJsonCesan.execute(fatura);

			String formatDtOriginal = dtVencimentoOriginal.format(format);
			String formatDtFinal = dtVencimentoFinal.format(format);

			String dcAnotacao = String.format("Faturamento - Alterar Vencimento de Ciclo: %s -> %s", formatDtOriginal, formatDtFinal);

			DossieImovel dossieImovel = dossieImovelVencimentoContingenciaRegra.construirDossieImovel(dcAnotacao, fatura.getIdFatura().getMatriculaImovel(),
					fatura.getDv(), login);

			loteDossiesAGravar.add(dossieImovel);
			loteFaturasAGravar.add(fatura);

			jsonsAntesAuditoriaFatura.put(fatura.getIdFatura().getMatriculaImovel(), jsonFaturaAntes);
			jsonsDepoisAuditoriaFatura.put(fatura.getIdFatura().getMatriculaImovel(), jsonFaturaDepois);
		}

		List<Fatura> loteFaturasGravadas = faturaRepository.saveAll(loteFaturasAGravar);

		dossieImovelRepository.saveAll(loteDossiesAGravar);

		List<AuditoriaMsgProdutor> auditoriaAlteracaoVecimentosList = loteFaturasGravadas.stream().map(faturaPar -> {

			Integer matriculaImovel = faturaPar.getIdFatura().getMatriculaImovel();
			String jsonFaturaAntes = jsonsAntesAuditoriaFatura.get(matriculaImovel);
			String jsonFaturaDepois = jsonsDepoisAuditoriaFatura.get(matriculaImovel);

			jsonFaturaAntes = auditoriaService.forcarRegistroCamposEmAlteracao(jsonFaturaAntes, "matriculaImovel", "origemFatura", "refFatura");

			return AuditoriaMsgProdutor.fabricarAlteracao(matriculaImovel.longValue(), jsonFaturaAntes, jsonFaturaDepois, 70L,
					"Faturamento - Alterar Vencimento de Ciclo", idUsuario);
		}).collect(Collectors.toList());

		auditoriaService.gerarAuditoriaAlteracaoLote(auditoriaAlteracaoVecimentosList);

		return loteFaturasGravadas;
	}

	public static void main(String[] args) {

		IdFatura idFatura = new IdFatura();
		System.out.println(idFatura);
	}
}

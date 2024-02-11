package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaAvulsaImovelCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaAvulsaImovelServicoItem;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaImovelAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.ParametroDropdownServicosFaturaAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.DadosImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaAvulsaCriadaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaPorTipoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaProjectionOutDTO;
import moduloFaturamento.dto.faturaImovel.BuscaValorServicoFilterDTO;
import moduloFaturamento.dto.grupoDeConsumo.TipoFaturamentoDropDownProjectionDTO;
import moduloFaturamento.excecoes.ExcecaoRegraNegocio;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.Fatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.model.IdFatura;
import moduloFaturamento.model.LancamentoContabil;
import moduloFaturamento.model.LancamentosDaFatura;
import moduloFaturamento.model.LancamentosFaturaAvulsa;
import moduloFaturamento.regras.faturaAvulsaImovel.FaturaAvulsaImovelRegra;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaAvulsaImovelRepository;
import moduloFaturamento.repository.FaturaImovelRepository;
import moduloFaturamento.repository.LancamentoContabilRepository;
import moduloFaturamento.repository.LancamentosDaFaturaRepository;
import moduloFaturamento.repository.LancamentosFaturaAvulsaRepository;
import moduloFaturamento.repository.TipoFaturamentoRepository;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.service.FaturaAvulsaImovelService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.ConverterMatricula;
import moduloFaturamento.validacoes.faturaAvulsaImovel.FaturaAvulsaImovelValidacao;

@Service
@Transactional
public class FaturaAvulsaImovelServiceImpl implements FaturaAvulsaImovelService {
	private static final String CATEGORIA_FATURA_AVULSA_IMOVEL = "Fatura Avulsa Imóvel";

	@Autowired
	private FaturaAvulsaImovelValidacao faturaAvulsaImovelValidacao;

	@Autowired
	private FaturaAvulsaImovelRegra faturaAvulsaImovelRegra;

	@Autowired
	private FaturaAvulsaImovelRepository faturaAvulsaImovelRepository;

	@Autowired
	private TipoFaturamentoRepository tipoFaturamentoRepository;

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private LancamentoContabilRepository lancamentoContabilRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CronogramaFaturaRepository cronogramaFaturaRepository;

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private LancamentosFaturaAvulsaRepository lancamentosFaturaAvulsaRepository;

	@Autowired
	private LancamentosDaFaturaRepository lancamentosDaFaturaRepository;

	@Override
	public void cadastrarFaturaAvulsaImovel(FaturaAvulsaImovelCadastrarDTO dto, String token) {

		// Cria e grava no banco a fatura avulsa
		this.cadastrarFaturaAvulsa(dto, token);


		List<LancamentosDaFatura> listLancamentosFaturaImovel = this.salvarLancamentosFaturaAvulsa(dto);

		// Cria e salva os Lançamentos contábeis
		this.salvarLancamentosContabeis(listLancamentosFaturaImovel, dto);

	}

	private void salvarLancamentosContabeis(List<LancamentosDaFatura> listLancamentosFaturaImovel,
			FaturaAvulsaImovelCadastrarDTO dto) {

		List<LancamentoContabil> listLancamentoContabil = new ArrayList<LancamentoContabil>();
		listLancamentosFaturaImovel.forEach(lancamentoFatura -> {
			List<LancamentoContabil> listLancamentosContabeis = faturaAvulsaImovelRegra
					.criarLancamentoContabil(lancamentoFatura, dto);

			listLancamentoContabil.addAll(listLancamentosContabeis);
		});
		if (!listLancamentoContabil.isEmpty()) {
			lancamentoContabilRepository.saveAll(listLancamentoContabil);
		}
	}

	private void cadastrarFaturaAvulsa(FaturaAvulsaImovelCadastrarDTO dto, String token) {

		String usuario = jwtTokenProvider.buscarLogin(token);
		Long idusuario = jwtTokenProvider.buscarIdUsuario(token);
		dto.setUsuario(usuario);
		LocalDate dataAtual = LocalDate.now();
		Integer mesAtual = dataAtual.getMonthValue();
		Integer anoAtual = dataAtual.getYear();

		Integer mes = dto.getRefFatura().getMonthValue();
		Integer ano = dto.getRefFatura().getYear();

		// Lançar execeção se referencia for maior que a data atual
		if (ano > anoAtual || (ano.equals(anoAtual) && mes > mesAtual)) {
			throw new ExcecaoRegraNegocio("A referência da fatura deve ser anterior a data atual.");
		}
		Fatura fatura = faturaAvulsaImovelRegra.criarFatura(dto);
	
		Optional<Fatura> faturaOp = faturaAvulsaImovelRepository.findById(fatura.getIdFatura());
		if (faturaOp.isPresent()) {
			throw new ExcecaoRegraNegocio("Essa fatura já existe!");
		}
		Fatura faturaCriada = faturaAvulsaImovelRepository.save(fatura);
		String dadosDepois = ConvertObjectToJsonCesan.execute(faturaCriada);
		Integer refFatura = ConverterData.localDateEmIntegerDataFormatoDB(dto.getRefFatura()) / 100;
		short origemFatura = dto.getOrigemFatura();
		Integer matriculaSemDv = dto.getMatricula();
		Long idFatura = faturaAvulsaImovelRepository.buscarIdFatura(refFatura, origemFatura, matriculaSemDv);
		auditoriaService.gerarAuditoriaCadastramento(idFatura, dadosDepois, 72L,
				CATEGORIA_FATURA_AVULSA_IMOVEL, idusuario);
	}

	private List<LancamentosDaFatura> salvarLancamentosFaturaAvulsa(
			FaturaAvulsaImovelCadastrarDTO dto) {

		List<LancamentosDaFatura> listLancamentosFaturaImovel = faturaAvulsaImovelRegra
				.criarLancamentosFaturaAvulsa(dto);

		// Verifica se existem lançamentos automáticos para serem gravados./
		List<LancamentosDaFatura> listaLancamentosAutomaticos = faturaAvulsaImovelRegra
				.gerarLancamentosAutomaticosFaturaAvulsa(dto);
		listLancamentosFaturaImovel.addAll(listaLancamentosAutomaticos);

		listLancamentosFaturaImovel = lancamentosDaFaturaRepository.saveAll(listLancamentosFaturaImovel);

		// Para cada lançamento da fatura avulsa, cria e salva o lançamento contábil
		// para crédito e débito, respectivamente.
		return listLancamentosFaturaImovel;

	}

	@Override
	public BigDecimal buscarValorServico(BuscaValorServicoFilterDTO filter) {
		Short cdServico = filter.getCdServico();
		Integer volume = filter.getVolume();
		Integer matricula = filter.getMatricula() / 10;

		LocalDate referencia = LocalDate.parse(filter.getReferencia());

		FaturaDetalheProcessamento calcularItens = faturaAvulsaImovelRegra.calcularItens(volume, matricula, referencia,
				cdServico);

		return calcularItens.getTotalFatura();
	}

	@Override
	public BigDecimal buscarValorServico(Short cdServico, Integer volume, Integer matricula, LocalDate referencia) {

		FaturaDetalheProcessamento calcularItens = faturaAvulsaImovelRegra.calcularItens(volume, matricula, referencia,
				cdServico);

		return calcularItens.getTotalFatura();
	}

	@Override
	public List<TipoFaturamentoDropDownProjectionDTO> buscarTipoFaturamentoDropDown() {

		return tipoFaturamentoRepository.buscarTipoFaturamentoDropDown();
	}

	@Override
	public DadosImovelClienteProjectionDTO buscarDadosImovelClienteCriacaoFaturaAvulsa(Integer matricula) {

		matricula = ConverterMatricula.matriculaSemDV(matricula);

		return faturaAvulsaImovelRepository.buscarDadosImovelClienteCriacaoFaturaAvulsa(matricula);

	}

	@Override
	public DadosImovelClienteProjectionDTO buscarDadosImovelClienteEdicaoFaturaAvulsa(Integer matricula,
			LocalDate referencia) {

		matricula = ConverterMatricula.matriculaSemDV(matricula);
		Integer intReferencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(referencia);

		return faturaAvulsaImovelRepository.buscarDadosImovelClienteEdicaoFaturaAvulsa(matricula, intReferencia);
	}

	@Override
	public List<ServicoFaturaPorTipoProjectionDTO> buscarServicosDropDown(
			ParametroDropdownServicosFaturaAvulsaDTO filter) {
		// return
		// faturaAvulsaImovelRepository.buscarServicosDropDown(filter.getTipoServico(),
		// filter.getMatriculaSemDV());
		return faturaAvulsaImovelRepository.buscarServicosDropDown(filter.getTipoServico());
	}

	@Override
	public List<ServicoFaturaProjectionOutDTO> buscarServicosDropDown(Integer matricula, Integer referencia) {
		matricula = matricula / 10;
		Integer cdCidade = imovelRepository.buscarCdCidadeDoImovel(matricula);

		List<ServicoFaturaProjectionDTO> projection = faturaAvulsaImovelRepository.buscarServicosDropDown(matricula,
				referencia);

		List<ServicoFaturaProjectionDTO> collect = projection.stream()
				.filter(prj -> prj.getCdCidadeImovel() == cdCidade).collect(Collectors.toList());

		if (collect.isEmpty()) {

			collect = projection.stream().filter(prj -> prj.getCdCidadeConfiguracao() == 0)
					.collect(Collectors.toList());
		}

		return collect.stream().map(prj -> new ServicoFaturaProjectionOutDTO(prj.getCdServico(), prj.getDcServico()))
				.collect(Collectors.toList());
	}

	@Override
	public List<FaturaImovelClienteProjectionDTO> buscarClientesDropDown(Integer matriculaSemDv) {

		return faturaAvulsaImovelRepository.buscarClientesDropDown(matriculaSemDv);
	}

}

package moduloFaturamento.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.PesquisaAmostrasArquivosProjectionRespostaComparator;
import moduloFaturamento.comparator.PesquisaAmostrasRealizadasProjectionRespostaComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasArquivosProjectionRespostaDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasRealizadasProjectionRespostaDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.AmostrasExigidas;
import moduloFaturamento.model.AmostrasRealizadas;
import moduloFaturamento.regras.mecanicas.qualidadeDaAgua.QualidadeDaAguaArquivoRegra;
import moduloFaturamento.repository.AmostrasExigidasRepository;
import moduloFaturamento.repository.AmostrasRealizadasRepository;
import moduloFaturamento.service.QualidadeDaAguaArquivosAmostrasService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;
import moduloFaturamento.validacoes.qualidadeDaAgua.AmostrasExigidasValidacoes;
import moduloFaturamento.validacoes.qualidadeDaAgua.AmostrasRealizadasValidacoes;

@Service
@Transactional
public class QualidadeDaAguaArquivosAmostrasServiceImpl implements QualidadeDaAguaArquivosAmostrasService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	@Autowired
	private AmostrasExigidasValidacoes amostrasExigidasValidacoes;

	@Autowired
	private AmostrasRealizadasValidacoes amostrasRealizadasValidacoes;

	@Autowired
	private AuditoriaService auditoriaService;

	@Autowired
	private AmostrasExigidasRepository amostrasExigidasRepository;

	@Autowired
	private AmostrasRealizadasRepository amostrasRealizadasRepository;

	@Autowired
	private QualidadeDaAguaArquivoRegra qualidadeDaAguaArquivoRegra;

	@Override
	public GenericoWrapperDTO<List<PesquisaAmostrasArquivosProjectionRespostaDTO>> pesquisaAmostrasArquivos(Pageable pageable, String token) {

		List<PesquisaAmostrasArquivosProjectionRespostaDTO> amostrasArquivos = amostrasRealizadasRepository.pesquisarAmostrasArquivos();

		return Paginacao.paginarCampoUnico(new PesquisaAmostrasArquivosProjectionRespostaComparator(), pageable, amostrasArquivos);
	}

	@Override
	public GenericoWrapperDTO<List<PesquisaAmostrasRealizadasProjectionRespostaDTO>> pesquisaAmostrasRealizadas(Integer refAmostras, Pageable pageable,
			String token) {

		amostrasRealizadasValidacoes.gerenciarValidacaoPesquisaAmostrasRealizadas(refAmostras);
		
		List<PesquisaAmostrasRealizadasProjectionRespostaDTO> amostrasRealizadas = amostrasRealizadasRepository
				.pesquisarAmostrasRealizadasPorReferencia(refAmostras);

		return Paginacao.paginarCampoUnico(new PesquisaAmostrasRealizadasProjectionRespostaComparator(), pageable, amostrasRealizadas);
	}

	@Override
	public Resource arquivoModeloAmostrasMinimasExigidas(String token) {

		Workbook criarArquivoModeloAmostrasMinimasExigidas = qualidadeDaAguaArquivoRegra.criarArquivoModeloAmostrasMinimasExigidas();

		return qualidadeDaAguaArquivoRegra.transformarWorkBookEmResource(criarArquivoModeloAmostrasMinimasExigidas);
	}

	@Override
	public Resource arquivoModeloAmostrasMinimasRealizadas(String token) {

		Workbook criarArquivoModeloAmostrasMinimasRealizadas = qualidadeDaAguaArquivoRegra.criarArquivoModeloAmostrasMinimasRealizadas();

		return qualidadeDaAguaArquivoRegra.transformarWorkBookEmResource(criarArquivoModeloAmostrasMinimasRealizadas);
	}

	@Override
	public Resource downloadAmostrasExigidas(Integer dtInicio, String token) {

		List<AmostrasExigidas> listAmostrasExigidas = amostrasExigidasRepository.buscarAmostrasExigidasPorDtInicio(dtInicio);

		Workbook workbook = qualidadeDaAguaArquivoRegra.gerarArquivoAmostrasExigidasDownload(listAmostrasExigidas);

		return qualidadeDaAguaArquivoRegra.transformarWorkBookEmResource(workbook);
	}

	@Override
	public Resource downloadAmostrasRealizadas(Integer refAmostras, String token) {

		List<AmostrasRealizadas> listAmostrasRealizadas = amostrasRealizadasRepository.buscarAmostrasRealizadasPorRefAmostras(refAmostras);

		Workbook workbook = qualidadeDaAguaArquivoRegra.gerarArquivoAmostrasRealizadasDownload(listAmostrasRealizadas);

		return qualidadeDaAguaArquivoRegra.transformarWorkBookEmResource(workbook);

	}

	@Override
	public void uploadAmostrasExigidas(MultipartFile file, String token) {

		String usuario = jwtTokenProvider.buscarLogin(token);
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		Workbook workbook = qualidadeDaAguaArquivoRegra.criarWorkbookDoUpload(file);

		List<AmostrasExigidas> listAmostrasExigidas = qualidadeDaAguaArquivoRegra.gerarRegistrosAmostrasExigidasUpload(workbook, usuario);

		commonUtilValidacoes.gerarExcecaoColecaoNulaOuVazia(listAmostrasExigidas, "Lista de amostras exigidas");

		Integer dtInicio = listAmostrasExigidas.get(0).getIdAmostrasExigidas().getDtInicio();

		amostrasExigidasValidacoes.gerenciarValidacaoGravacaoAmostrasExigidas(dtInicio);

		List<AmostrasExigidas> savedList = amostrasExigidasRepository.saveAll(listAmostrasExigidas);

		String dadosDepois = ConvertObjectToJsonCesan.execute(savedList);

		auditoriaService.gerarAuditoria((long) dtInicio, Constants.EMPTY_STRING, dadosDepois, 65L, "Amostras Exigidas", idUsuario);

	}

	@Override
	public void uploadAmostrasRealizadas(MultipartFile file, String token) {

		String usuario = jwtTokenProvider.buscarLogin(token);
		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		Workbook workbook = qualidadeDaAguaArquivoRegra.criarWorkbookDoUpload(file);

		List<AmostrasRealizadas> listAmostrasRealizadas = qualidadeDaAguaArquivoRegra.gerarRegistrosAmostrasRealizadasUpload(workbook, usuario);

		commonUtilValidacoes.gerarExcecaoColecaoNulaOuVazia(listAmostrasRealizadas, "Lista de amostras realizadas");

		Integer refAmostras = listAmostrasRealizadas.get(0).getIdAmostrasRealizadas().getRefAmostras();

		amostrasRealizadasValidacoes.gerenciarValidacaoGravacaoAmostrasRealizadas(refAmostras);

		List<AmostrasRealizadas> savedList = amostrasRealizadasRepository.saveAll(listAmostrasRealizadas);

		String dadosDepois = ConvertObjectToJsonCesan.execute(savedList);

		auditoriaService.gerarAuditoria((long) refAmostras, Constants.EMPTY_STRING, dadosDepois, 66L, "Amostras Realizadas", idUsuario);

	}

	@Override
	public void removerAmostrasExigidas(Integer dtInicio, String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		amostrasExigidasValidacoes.gerenciarValidacaoExclusaoAmostrasExigidas(dtInicio);

		List<AmostrasExigidas> listSaved = amostrasExigidasRepository.buscarAmostrasExigidasPorDtInicio(dtInicio);

		amostrasExigidasRepository.removerPorDtInicio(dtInicio);

		String dadosAntes = ConvertObjectToJsonCesan.execute(listSaved);

		auditoriaService.gerarAuditoria((long) dtInicio, dadosAntes, Constants.EMPTY_STRING, 65L, "Amostras Exigidas", idUsuario);
	}

	@Override
	public void removerAmostrasRealizadas(Integer refAmostras, String token) {

		Long idUsuario = jwtTokenProvider.buscarIdUsuario(token);

		amostrasRealizadasValidacoes.gerenciarValidacaoExclusaoAmostrasRealizadas(refAmostras);

		List<AmostrasRealizadas> listSaved = amostrasRealizadasRepository.buscarAmostrasRealizadasPorRefAmostras(refAmostras);

		amostrasRealizadasRepository.removerPorRefAmostras(refAmostras);

		String dadosAntes = ConvertObjectToJsonCesan.execute(listSaved);

		auditoriaService.gerarAuditoria((long) refAmostras, dadosAntes, Constants.EMPTY_STRING, 66L, "Amostras Realizadas", idUsuario);
	}
}

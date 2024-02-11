package moduloFaturamento.service.impl.common;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.OcorrenciaLeituraGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraAtualizarDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraCadastrarDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraFilterDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraGravadoRespostaIdentidadeDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaConsultaDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaDropDownDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaGridDTO;
import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.OcorrenciaLeituraCadastroMapper;
import moduloFaturamento.mapper.OcorrenciaLeituraRespostaConsultaMapper;
import moduloFaturamento.mapper.OcorrenciaLeituraRespostaGravadoMapper;
import moduloFaturamento.model.common.OcorrenciaLeitura;
import moduloFaturamento.model.common.TipoServicoOcorrenciaLeitura;
import moduloFaturamento.repository.common.OcorrenciaLeituraRepository;
import moduloFaturamento.service.common.OcorrenciaLeituraService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.ocorrenciaLeitura.OcorrenciaLeituraAtualizacaoValidacao;
import moduloFaturamento.validacoes.ocorrenciaLeitura.OcorrenciaLeituraCadastroValidacao;
import moduloFaturamento.validacoes.ocorrenciaLeitura.OcorrenciaLeituraConsultaValidacao;
import moduloFaturamento.validacoes.ocorrenciaLeitura.OcorrenciaLeituraRemocaoValidacao;

@Service
@Transactional
public class OcorreciaLeituraServiceImpl implements OcorrenciaLeituraService {

	@Autowired
	private OcorrenciaLeituraRepository ocorrenciaLeituraRepository;
	@Autowired
	private OcorrenciaLeituraConsultaValidacao ocorrenciaLeituraConsultaValidacao;
	@Autowired
	private OcorrenciaLeituraRemocaoValidacao ocorrenciaLeituraRemocaoValidacao;
	@Autowired
	private OcorrenciaLeituraCadastroValidacao ocorrenciaLeituraCadastroValidacao;
	@Autowired
	private OcorrenciaLeituraAtualizacaoValidacao ocorrenciaLeituraAtualizacaoValidacao;
	@Autowired
	private OcorrenciaLeituraRespostaConsultaMapper ocorrenciaLeituraRespostaConsultaMapper;
	@Autowired
	private OcorrenciaLeituraCadastroMapper ocorrenciaLeituraCadastroMapper;
	@Autowired
	private OcorrenciaLeituraRespostaGravadoMapper ocorrenciaLeituraRespostaGravadoMapper;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public OcorrenciaLeituraRespostaConsultaDTO buscarOcorrenciaPorCodigoOcorrencia(Short cdOcorrencia) {

		this.ocorrenciaLeituraConsultaValidacao.validarConsulta(cdOcorrencia);
		OcorrenciaLeitura ocorrenciaLeitura = this.ocorrenciaLeituraRepository.findByCdOcorrencia(cdOcorrencia);
		return this.ocorrenciaLeituraRespostaConsultaMapper.toDto(ocorrenciaLeitura);
	}

	@Override
	public List<OcorrenciaLeituraRespostaDropDownDTO> buscarOcorrenciasPorTipoGravidade(String tipoGravidadeOcorrencia) {

		return this.ocorrenciaLeituraRepository.buscarListaOcorrenciaPorFiltroTipoOcorrencia(tipoGravidadeOcorrencia);
	}

	@Override
	public String buscarDescricaoPorCodigoOcorrencia(Short cdOcorrencia) {

		OcorrenciaLeitura ocorrenciaLeitura = this.ocorrenciaLeituraRepository.findByCdOcorrencia(cdOcorrencia);
		if (ocorrenciaLeitura != null) {

			return ocorrenciaLeitura.getDcOcorrencia();
		} else {

			return null;
		}
	}

	@Override
	public OcorrenciaLeituraDropDownProjectionDTO buscarDropDownOcorrenciaLeituraPorCodigo(Short cdOcorrencia) {

		return ocorrenciaLeituraRepository.buscarDropDownOcorrenciaLeituraPorCodigo(cdOcorrencia);
	}

	@Override
	public GenericoWrapperDTO<List<OcorrenciaLeituraRespostaGridDTO>> buscarOcorrenciaLeituraPorFiltro(OcorrenciaLeituraFilterDTO filter, Pageable pageable) {

		Short id = filter.getId();
		String descricao = filter.getDescricao();
		String idTipoServicoOcorrenciaLeitura = filter.getIdTipoServicoOcorrencia();
		String leituraVirtual = filter.getLeituraVirtual();
		List<OcorrenciaLeituraRespostaGridDTO> ocorrenciaLeituraRespostaGridDTO = this.ocorrenciaLeituraRepository.buscarListaOcorrenciaPorFiltro(id, descricao,
				idTipoServicoOcorrenciaLeitura, leituraVirtual);
		return Paginacao.paginarCampoUnico(new OcorrenciaLeituraGridComparator(), pageable, ocorrenciaLeituraRespostaGridDTO);
	}

	@Override
	public OcorrenciaLeituraGravadoRespostaIdentidadeDTO executarFluxoCadastrar(OcorrenciaLeituraCadastrarDTO dto, String token) {

		this.ocorrenciaLeituraCadastroValidacao.gerenciarValidacaoParaCadastrar(dto.getCodigo());
		OcorrenciaLeitura ocorrenciaLeitura = this.ocorrenciaLeituraCadastroMapper.toEntity(dto);
		OcorrenciaLeitura ocorrenciaLeituraSalvo = this.cadastrarOcorrenciaValida(ocorrenciaLeitura, this.jwtTokenProvider.buscarIdUsuario(token));
		return this.ocorrenciaLeituraRespostaGravadoMapper.toDto(ocorrenciaLeituraSalvo);
	}

	@Override
	public OcorrenciaLeituraGravadoRespostaIdentidadeDTO executarFluxoAtualizar(OcorrenciaLeituraAtualizarDTO dto, String token) {

		Short codigo = dto.getCodigo();
		String descricao = dto.getDescricao();
		String idTipoOcorrencia = dto.getIdTipoOcorrencia();
		Boolean leituraVirtual = dto.getLeituraVirtual();
		this.ocorrenciaLeituraAtualizacaoValidacao.gerenciarValidacaoDadosParaAtualizar(codigo);

		OcorrenciaLeitura ocorrenciaLeitura = this.ocorrenciaLeituraRepository.findByCdOcorrencia(codigo);
		String ocorrenciaAntesAtualizar = ConvertObjectToJsonCesan.execute(ocorrenciaLeitura);

		OcorrenciaLeitura ocorrenciaLeituraAtualizado = this.atualizarOcorrenciaValida(ocorrenciaLeitura, ocorrenciaAntesAtualizar, descricao, idTipoOcorrencia,
				leituraVirtual, this.jwtTokenProvider.buscarIdUsuario(token));

		return this.ocorrenciaLeituraRespostaGravadoMapper.toDto(ocorrenciaLeituraAtualizado);
	}

	@Override
	public void executarFluxoDeletar(Short cdOcorencia, String token) {

		this.ocorrenciaLeituraRemocaoValidacao.gerenciarValidacaoDadosParaDeletar(cdOcorencia);
		OcorrenciaLeitura ocorrenciaLeitura = this.ocorrenciaLeituraRepository.findByCdOcorrencia(cdOcorencia);
		String ocorrenciaLeituraAntesAtualizar = ConvertObjectToJsonCesan.execute(ocorrenciaLeitura);
		this.removerOcorrenciaValida(ocorrenciaLeitura, ocorrenciaLeituraAntesAtualizar, this.jwtTokenProvider.buscarIdUsuario(token));
	}

	private OcorrenciaLeitura cadastrarOcorrenciaValida(OcorrenciaLeitura ocorrenciaLeitura, Long idUsuario) {

		OcorrenciaLeitura ocorrenciaLeituraSalvo = this.ocorrenciaLeituraRepository.save(ocorrenciaLeitura);
		this.gerarAuditoria(ocorrenciaLeituraSalvo, "", ConvertObjectToJsonCesan.execute(ocorrenciaLeituraSalvo), idUsuario);
		return ocorrenciaLeituraSalvo;
	}

	private OcorrenciaLeitura atualizarOcorrenciaValida(OcorrenciaLeitura ocorrenciaLeitura, String ocorrenciaAntesAtualizar, String descricao,
			String idTipoLeitura, Boolean leituraVirtual, Long idUsuario) {

		ocorrenciaLeitura.setDcOcorrencia(descricao);
		ocorrenciaLeitura.setTipoOcorrencia(new TipoServicoOcorrenciaLeitura(idTipoLeitura));
		ocorrenciaLeitura.setLeituraVirtual((leituraVirtual ? "S" : "N"));

		this.gerarAuditoria(ocorrenciaLeitura, ocorrenciaAntesAtualizar, ConvertObjectToJsonCesan.execute(ocorrenciaLeitura), idUsuario);
		return ocorrenciaLeitura;
	}

	private void removerOcorrenciaValida(OcorrenciaLeitura ocorrenciaLeitura, String ocorrenciaAntesAtualizar, Long idUsuario) {

		ocorrenciaLeitura.setDataHoraExclusao(LocalDateTime.now());
		this.gerarAuditoria(ocorrenciaLeitura, ocorrenciaAntesAtualizar, ConvertObjectToJsonCesan.execute(ocorrenciaLeitura), idUsuario);
	}

	private void gerarAuditoria(OcorrenciaLeitura ocorrenciaLeitura, String ocorrenciaAntesAtualizar, String ocorrenciaDepoisAtualizar, Long idUsuario) {

		this.auditoriaService.gerarAuditoria(ocorrenciaLeitura.getCdOcorrencia().longValue(), ocorrenciaAntesAtualizar, ocorrenciaDepoisAtualizar, 32L,
				"Ocorrência de Leitura", idUsuario);
	}
}

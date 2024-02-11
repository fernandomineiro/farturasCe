package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper.IncentivoSituacaoLigacaoAguaMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper.IncentivoSituacaoLigacaoEsgotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.IncentivoClienteParametroDetalheGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaCadastroIdentidadeDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaAtualizarDetalheDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaCadastroDetalheDTO;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.mapper.IncentivoClienteParametroDetalheRespostaConsultaMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.mapper.IncentivoClienteDetalheAtualizarMapper;
import moduloFaturamento.dto.incentivoCliente.mapper.IncentivoClienteDetalheCadastroMapper;
import moduloFaturamento.dto.situacaoLigacaoAgua.mapper.SituacaoLigacaoAguaMapper;
import moduloFaturamento.dto.situacaoLigacaoEsgoto.mapper.SituacaoLigacaoEsgotoMapper;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IncentivoCliente;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import moduloFaturamento.model.IncentivoClienteHistoricoDetalhe;

import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import moduloFaturamento.repository.IncentivoClienteDetalheRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoDetalheRepository;
import moduloFaturamento.repository.IncentivoClienteRepository;
import moduloFaturamento.repository.common.SituacaoLigacaoAguaRepository;
import moduloFaturamento.repository.common.SituacaoLigacaoEsgotoRepository;
import moduloFaturamento.service.IncentivoClienteParametroDetalheGrupoConsumoService;
import moduloFaturamento.service.IncentivoClienteParametroDetalheLigacaoAguaService;
import moduloFaturamento.service.IncentivoClienteParametroDetalheLigacaoEsgotoService;
import moduloFaturamento.service.IncentivoClienteParametroDetalheService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroConsultaValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheConsultaValidacao;

@Service
@Transactional
public class IncentivoClienteParametroDetalheServiceImpl implements IncentivoClienteParametroDetalheService {

    @Autowired
    private IncentivoClienteDetalheRepository incentivoClienteDetalheRepository;
    @Autowired
    private IncentivoClienteHistoricoDetalheRepository incentivoClienteHistoricoDetalheRepository;
    @Autowired
    private SituacaoLigacaoAguaRepository situacaoLigacaoAguaRepository;
    @Autowired
    private SituacaoLigacaoEsgotoRepository situacaoLigacaoEsgotoRepository;
    @Autowired
    private IncentivoClienteParametroDetalheConsultaValidacao incentivoClienteParametroDetalheConsultaValidacao;
    @Autowired
    private IncentivoClienteParametroConsultaValidacao incentivoClienteParametroConsultaValidacao;
    @Autowired
    private IncentivoClienteParametroDetalheGrupoConsumoService incentivoClienteParametroDetalheGrupoConsumoService;
    @Autowired
    private IncentivoClienteParametroDetalheLigacaoAguaService incentivoClienteParametroDetalheLigacaoAguaService;
    @Autowired
    private IncentivoClienteParametroDetalheLigacaoEsgotoService incentivoClienteParametroDetalheLigacaoEsgotoService;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private IncentivoClienteParametroDetalheRespostaConsultaMapper incentivoClienteParametroDetalheRespostaConsultaMapper;
    @Autowired
    private IncentivoClienteDetalheCadastroMapper incentivoClienteDetalheCadastroMapper;
    @Autowired
    private IncentivoClienteDetalheAtualizarMapper incentivoClienteDetalheAtualizarMapper;
    @Autowired
    private SituacaoLigacaoAguaMapper situacaoLigacaoAguaMapper;
    @Autowired
    private SituacaoLigacaoEsgotoMapper situacaoLigacaoEsgotoMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IncentivoClienteRepository incentivoClienteRepository;
    @Override 
    public GenericoWrapperDTO<List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO>> listarGrid(Integer idParametroIncentivoCliente, Pageable pageable) {
      
    	
    	
    	System.out.print("------->");
    	System.out.print(idParametroIncentivoCliente);
    	
    	
    	this.incentivoClienteParametroConsultaValidacao.validar(idParametroIncentivoCliente);

        List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO> dto = this.incentivoClienteDetalheRepository
                .buscarIncentivoClienteDetalhePorIdIncentivo(idParametroIncentivoCliente);

        for (IncentivoClienteParametroDetalheRespostaGridProjectionDTO detalhe : dto) {
            List<SituacaoLigacaoAgua> listaSituacaoAgua = this.situacaoLigacaoAguaRepository
                    .buscarSituacaoAguaPorIdIncentivoCliente(detalhe.getId());
            List<SituacaoLigacaoEsgoto> listaSituacaoEsgoto = this.situacaoLigacaoEsgotoRepository
                    .buscarSituacaoEsgotoPorIdIncentivoCliente(detalhe.getId());
            List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listaGrupoConsumo = this.incentivoClienteParametroDetalheGrupoConsumoService
                    .buscarGrupoConsumoDoParametroDeIncentivoCliente(detalhe.getId());

            detalhe.setListaSituacaoAgua(this.situacaoLigacaoAguaMapper.toDto(listaSituacaoAgua));
            detalhe.setListaSituacaoEsgoto(this.situacaoLigacaoEsgotoMapper.toDto(listaSituacaoEsgoto));
            detalhe.setListaGrupoConsumo(listaGrupoConsumo);
        }

        return Paginacao.paginarCampoUnico(new IncentivoClienteParametroDetalheGridComparator(), pageable, dto);
    }

    @Override
    public IncentivoClienteParametroDetalheRespostaConsultaDTO buscarDetalheParametroIncentivoPorId(
            Integer idDetalheParametroIncentivoCliente) {
        this.incentivoClienteParametroDetalheConsultaValidacao.validar(idDetalheParametroIncentivoCliente);

        IncentivoClienteDetalhe incentivoClienteDetalhe = this.incentivoClienteDetalheRepository
                .buscarIncentivoExistente(idDetalheParametroIncentivoCliente);

        List<SituacaoLigacaoAgua> listaSituacaoAgua = this.situacaoLigacaoAguaRepository
                .buscarSituacaoAguaPorIdIncentivoCliente(incentivoClienteDetalhe.getId());
        List<SituacaoLigacaoEsgoto> listaSituacaoEsgoto = this.situacaoLigacaoEsgotoRepository
                .buscarSituacaoEsgotoPorIdIncentivoCliente(incentivoClienteDetalhe.getId());

        IncentivoClienteParametroDetalheRespostaConsultaDTO dto = this.incentivoClienteParametroDetalheRespostaConsultaMapper
                .toDto(incentivoClienteDetalhe);
        dto.setListaSituacaoAgua(this.situacaoLigacaoAguaMapper.toDto(listaSituacaoAgua));
        dto.setListaSituacaoEsgoto(this.situacaoLigacaoEsgotoMapper.toDto(listaSituacaoEsgoto));

        return dto;
    }

    @Override
    public List<IncentivoClienteParametroRespostaCadastroDetalheDTO> executarFluxoCadastrarDetalheParametroIncentivo(
            List<IncentivoClienteParametroDetalheCadastroDTO> dto,
            IncentivoCliente incentivoClienteDTO, String token) {

        List<IncentivoClienteParametroRespostaCadastroDetalheDTO> dtoRespostaLista = new ArrayList<>();

        for (IncentivoClienteParametroDetalheCadastroDTO detalheDTO : dto) {

            IncentivoClienteDetalhe detalhe = this.incentivoClienteDetalheCadastroMapper.toEntity(detalheDTO);

            IncentivoClienteDetalhe salvo = this.cadastrarIncentivoParametroDeDescontoValido(detalhe,
                    incentivoClienteDTO.getId(), detalheDTO.getSituacaoLigacaoAgua(),
                    detalheDTO.getSituacaoLigacaoEsgoto(), this.jwtTokenProvider.buscarIdUsuario(token));

            List<IncentivoClienteParametroRespostaGrupoConsumoCadastroDTO> salvoGrupoConsumo = this.incentivoClienteParametroDetalheGrupoConsumoService
                    .executarFluxoCadastrarGrupoConsumoDoDetalheDoParametroIncentivo(detalheDTO.getListaGrupoConsumo(),
                            salvo, token);

            IncentivoClienteParametroRespostaCadastroDetalheDTO dtoRespostaDetalhe = new IncentivoClienteParametroRespostaCadastroDetalheDTO(
                    salvo.getId(), salvoGrupoConsumo);

            dtoRespostaLista.add(dtoRespostaDetalhe);
        }

        return dtoRespostaLista;
    }

    @Override
    public List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> executarFluxoEditarDetalheParametroIncentivo(
            List<IncentivoClienteParametroDetalheAtualizarDTO> dto,
            IncentivoCliente incentivoClienteDTO, String token) {
        List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> dtoRespostaLista = new ArrayList<>();
        Long idUsuario = this.jwtTokenProvider.buscarIdUsuario(token);
        List<IncentivoClienteDetalhe> todosDetalhesCadastradosParaOIncentivo = this.incentivoClienteDetalheRepository
                .findByIncentivoCliente_IdAndDataHoraExclusaoIsNull(incentivoClienteDTO.getId());

        for (IncentivoClienteDetalhe todos : todosDetalhesCadastradosParaOIncentivo) {
            boolean registroEstaNoBancoDeDadosMasNaoEstaNaListaRecebida = true;
            for (IncentivoClienteParametroDetalheAtualizarDTO detalheDTO : dto) {
                if (todos.getId().equals(detalheDTO.getId())) {
                    registroEstaNoBancoDeDadosMasNaoEstaNaListaRecebida = false;
                    break;
                }
            }

            if (registroEstaNoBancoDeDadosMasNaoEstaNaListaRecebida) {
                IncentivoClienteDetalhe entidadeQueDeveSerRemovida = this.incentivoClienteDetalheRepository
                        .buscarIncentivoExistente(todos.getId());
                String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(entidadeQueDeveSerRemovida);
                this.removerIncentivoParametroDeDescontoValido(entidadeQueDeveSerRemovida, dadosAntesAtualizar,
                        idUsuario);
            }
        }

        for (IncentivoClienteParametroDetalheAtualizarDTO detalheDTO : dto) {

            IncentivoClienteDetalhe detalhe = this.incentivoClienteDetalheAtualizarMapper.toEntity(detalheDTO);

            if (detalheDTO.getId() == null) {
                this.cadastrarIncentivoParametroDeDescontoValido(detalhe, incentivoClienteDTO.getId(),
                        detalheDTO.getSituacaoLigacaoAgua(), detalheDTO.getSituacaoLigacaoEsgoto(),
                        idUsuario);
            } else {
                detalhe = this.incentivoClienteDetalheRepository.buscarIncentivoExistente(detalhe.getId());
                this.atualizarIncentivoParametroDeDescontoValido(detalhe, detalheDTO,
                        idUsuario);
            }

            List<IncentivoClienteParametroRespostaGrupoConsumoAtualizarDTO> salvoGrupoConsumo = this.incentivoClienteParametroDetalheGrupoConsumoService
                    .executarFluxoEditarGrupoConsumoDoDetalheDoParametroIncentivo(detalheDTO.getListaGrupoConsumo(),
                            detalhe, token);

            IncentivoClienteParametroRespostaAtualizarDetalheDTO dtoRespostaDetalhe = new IncentivoClienteParametroRespostaAtualizarDetalheDTO(
                    detalhe.getId(), salvoGrupoConsumo);

            dtoRespostaLista.add(dtoRespostaDetalhe);
        }

        return dtoRespostaLista;
    }

    private IncentivoClienteDetalhe cadastrarIncentivoParametroDeDescontoValido(
            IncentivoClienteDetalhe incentivoClienteDetalhe, Integer codigoParametroIncentivoCliente,
            List<Integer> situacaoLigacaoAgua, List<Integer> situacaoLigacaoEsgoto, Long idUsuario) {
        IncentivoCliente salvo = this.incentivoClienteRepository.findById(codigoParametroIncentivoCliente).get();
        incentivoClienteDetalhe.setIncentivoCliente(salvo);
        this.incentivoClienteDetalheRepository.save(incentivoClienteDetalhe);

        this.auditoriaService.gerarAuditoriaCadastramento(incentivoClienteDetalhe.getId().longValue(),
                ConvertObjectToJsonCesan.execute(incentivoClienteDetalhe), 73L, "Parâmetro de Negociação", idUsuario);

        IncentivoClienteHistoricoDetalhe historicoDetalhe = this.salvarHistoricoDetalhe(incentivoClienteDetalhe);

        this.incentivoClienteParametroDetalheLigacaoAguaService.executarFluxoCadastrarLigacao(incentivoClienteDetalhe,
                situacaoLigacaoAgua, idUsuario, historicoDetalhe);
        this.incentivoClienteParametroDetalheLigacaoEsgotoService.executarFluxoCadastrarLigacao(incentivoClienteDetalhe,
                situacaoLigacaoEsgoto, idUsuario, historicoDetalhe);

        return incentivoClienteDetalhe;
    }

    private void atualizarIncentivoParametroDeDescontoValido(IncentivoClienteDetalhe incentivoClienteDetalhe,
            IncentivoClienteParametroDetalheAtualizarDTO detalheAtualizacaoDTO, Long idUsuario) {
        IncentivoClienteDetalhe detalhe = this.incentivoClienteDetalheAtualizarMapper.toEntity(detalheAtualizacaoDTO);
        detalhe.setId(incentivoClienteDetalhe.getId());
        detalhe.setIncentivoCliente(incentivoClienteDetalhe.getIncentivoCliente());
        detalhe.setDataHoraExclusao(null);
        String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(incentivoClienteDetalhe);

        this.auditoriaService.gerarAuditoriaAlteracao(incentivoClienteDetalhe.getId().longValue(), dadosAntesAtualizar,
                ConvertObjectToJsonCesan.execute(incentivoClienteDetalhe), 73L, "Parâmetro de Negociação", idUsuario);

        IncentivoClienteHistoricoDetalhe historicoDetalhe = this.salvarHistoricoDetalhe(incentivoClienteDetalhe);

        this.incentivoClienteParametroDetalheLigacaoAguaService
                .executarFluxoRemoverTodasLigacoesDeUmParametroIncentivo(incentivoClienteDetalhe);
        this.incentivoClienteParametroDetalheLigacaoAguaService.executarFluxoCadastrarLigacao(detalhe,
                detalheAtualizacaoDTO.getSituacaoLigacaoAgua(), idUsuario, historicoDetalhe);

        this.incentivoClienteParametroDetalheLigacaoEsgotoService
                .executarFluxoRemoverTodasLigacoesDeUmParametroIncentivo(incentivoClienteDetalhe);
        this.incentivoClienteParametroDetalheLigacaoEsgotoService.executarFluxoCadastrarLigacao(detalhe,
                detalheAtualizacaoDTO.getSituacaoLigacaoEsgoto(), idUsuario, historicoDetalhe);
        this.incentivoClienteDetalheRepository.saveAndFlush(detalhe);
    }

  
    private void removerIncentivoParametroDeDescontoValido(IncentivoClienteDetalhe incentivoClienteDetalhe,
            String dadosAntesAtualizar, Long idUsuario) {
        incentivoClienteDetalhe.setDataHoraExclusao(LocalDateTime.now());
        this.auditoriaService.gerarAuditoriaAlteracao(incentivoClienteDetalhe.getId().longValue(), dadosAntesAtualizar,
                ConvertObjectToJsonCesan.execute(incentivoClienteDetalhe), 73L, "Parâmetro de Negociação", idUsuario);
    }

    private IncentivoClienteHistoricoDetalhe salvarHistoricoDetalhe(IncentivoClienteDetalhe incentivoClienteDetalhe) {
        IncentivoClienteHistoricoDetalhe historicoDetalhe = new IncentivoClienteHistoricoDetalhe(
                incentivoClienteDetalhe);
        this.incentivoClienteHistoricoDetalheRepository.save(historicoDetalhe);

        return historicoDetalhe;
    }
}

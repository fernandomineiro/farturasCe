package moduloFaturamento.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.IncentivoClienteParametroGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteRespostaAnexoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper.IncentivoClienteParametroRespostaConsultaMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper.IncentivoClienteParametroRespostaSituacaoAguaMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper.IncentivoClienteParametroRespostaSituacaoEsgotoMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.mapper.TipoIncentivoClienteMapper;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaAtualizarDetalheDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroRespostaCadastroDetalheDTO;
import moduloFaturamento.dto.incentivoCliente.mapper.IncentivoClienteCadastroMapper;
import moduloFaturamento.dto.incentivoCliente.mapper.IncentivoClienteDetalheCadastroMapper;
import moduloFaturamento.dto.incentivoCliente.mapper.IncentivoClienteDetalheGrupoConsumoCadastroMapper;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.IncentivoCliente;
import moduloFaturamento.model.IncentivoClienteHistorico;
import moduloFaturamento.model.TipoIncentivoCliente;
import moduloFaturamento.regras.incentivoCliente.IncentivoClienteAtualizarRegra;
import moduloFaturamento.regras.incentivoCliente.IncentivoClienteCadastroRegra;
import moduloFaturamento.repository.IncentivoClienteHistoricoRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoAguaRepository;
import moduloFaturamento.repository.IncentivoClienteHistoricoSituacaoEsgotoRepository;
import moduloFaturamento.repository.IncentivoClienteRepository;
import moduloFaturamento.repository.TipoIncentivoClienteRepository;
import moduloFaturamento.repository.common.UsuarioRepository;
import moduloFaturamento.repository.common.UsuarioSGAUSRepository;
import moduloFaturamento.service.IncentivoClienteParametroAnexoService;
import moduloFaturamento.service.IncentivoClienteParametroDetalheGrupoConsumoService;
import moduloFaturamento.service.IncentivoClienteParametroDetalheService;
import moduloFaturamento.service.IncentivoClienteParametroService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroAtualizarValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroCadastrarValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroConsultaValidacao;
import moduloFaturamento.validacoes.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRemocaoValidacao;

@Service
@Transactional
public class IncentivoClienteParametroServiceImpl implements IncentivoClienteParametroService {

        @Autowired
        private IncentivoClienteRepository incentivoClienteRepository;
        @Autowired
        IncentivoClienteHistoricoRepository incentivoClienteHistoricoRepository;
        @Autowired
        private UsuarioSGAUSRepository usuarioSGAUSRepository;
        @Autowired
        private IncentivoClienteParametroConsultaValidacao incentivoClienteParametroConsultaValidacao;
        @Autowired
        private IncentivoClienteParametroCadastrarValidacao incentivoClienteParametroCadastrarValidacao;
        @Autowired
        private IncentivoClienteParametroAtualizarValidacao incentivoClienteParametroAtualizarValidacao;
        @Autowired
        private IncentivoClienteParametroRemocaoValidacao incentivoClienteParametroRemocaoValidacao;
        @Autowired
        private IncentivoClienteParametroDetalheService incentivoClienteParametroDetalheService;
        @Autowired
        private IncentivoClienteParametroDetalheGrupoConsumoService incentivoClienteParametroDetalheGrupoConsumoService;
        @Autowired
        private IncentivoClienteParametroAnexoService incentivoClienteParametroAnexoService;
        @Autowired
        private AuditoriaService auditoriaService;
        @Autowired
        private IncentivoClienteCadastroRegra incentivoClienteCadastroRegra;
        @Autowired
        private IncentivoClienteAtualizarRegra incentivoClienteAtualizarRegra;
        @Autowired
        private IncentivoClienteCadastroMapper incentivoClienteCadastroMapper;
        @Autowired
        private IncentivoClienteDetalheCadastroMapper incentivoClienteDetalheCadastroMapper;
        @Autowired
        private IncentivoClienteParametroRespostaConsultaMapper incentivoClienteParametroRespostaConsultaMapper;
        @Autowired
        private IncentivoClienteDetalheGrupoConsumoCadastroMapper incentivoClienteDetalheGrupoConsumoCadastroMapper;
        @Autowired
        private IncentivoClienteParametroRespostaSituacaoAguaMapper incentivoClienteParametroRespostaSituacaoAguaMapper;
        @Autowired
        private IncentivoClienteParametroRespostaSituacaoEsgotoMapper incentivoClienteParametroRespostaSituacaoEsgotoMapper;
        @Autowired
        private JwtTokenProvider jwtTokenProvider;
        @Autowired
        private IncentivoClienteHistoricoSituacaoAguaRepository incenticoClienteHistoricoSituacaoAguaRepository;
        @Autowired
        private IncentivoClienteHistoricoSituacaoEsgotoRepository incenticoClienteHistoricoSituacaoEsgotoRepository;
        @Autowired
        private TipoIncentivoClienteRepository tipoIncentivoClienteRepository;
        @Autowired
        private TipoIncentivoClienteMapper tipoIncentivoClienteMapper;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Override
        public GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listarGrid(
                        Pageable pageable) {
                List<IncentivoClienteParametroRespostaGridProjectionDTO> dto = this.incentivoClienteRepository
                                .buscarListaIncentivoClienteParametro();

                return Paginacao.paginarCampoUnico(new IncentivoClienteParametroGridComparator(), pageable, dto);
        }

        @Override
        public IncentivoClienteParametroRespostaConsultaDTO buscarParametroIncentivoPorId(
                        Integer idParametroIncentivoCliente) {
                this.incentivoClienteParametroConsultaValidacao.validar(idParametroIncentivoCliente);

                IncentivoCliente incentivoCliente = this.incentivoClienteRepository
                                .buscarIncentivoExistentePorId(idParametroIncentivoCliente);

                return this.incentivoClienteParametroRespostaConsultaMapper.toDto(incentivoCliente);
        }

        @Override
        @Transactional(value = "chainedTransactionManager")
        public IncentivoClienteRespostaEstruturaCadastroDTO executarFluxoCadastrarIncentivoDetalhesEGrupoConsumoAnexo(
                        IncentivoClienteCadastroDTO dto, String token) {
                IncentivoCliente salvo = this.executarFluxoCadastrarParametroIncentivo(dto, token);

                ParametroIncentivoClienteRespostaAnexoDTO anexoRespostaAnexoDTO = this.incentivoClienteParametroAnexoService
                                .executarFluxoSalvarAnexo(dto.getAnexo(), salvo, token);

                List<IncentivoClienteParametroRespostaCadastroDetalheDTO> detalhesComGcSalvo = this.incentivoClienteParametroDetalheService
                                .executarFluxoCadastrarDetalheParametroIncentivo(dto.getListaDeDetalhes(), salvo,
                                                token);

                return new IncentivoClienteRespostaEstruturaCadastroDTO(salvo.getId(), anexoRespostaAnexoDTO.getId(),
                                detalhesComGcSalvo);
        }

        @Override
        @Transactional(value = "chainedTransactionManager")
        public IncentivoClienteRespostaEstruturaAtualizarDTO executarFluxoEditarIncentivoDetalhesEGrupoConsumoAnexo(
                        IncentivoClienteAtualizarDTO dto, String token) {
                IncentivoCliente atualizado = this.executarFluxoEditarParametroIncentivo(dto, token);

                ParametroIncentivoClienteRespostaAnexoDTO anexoRespostaAnexoDTO = this.incentivoClienteParametroAnexoService
                                .executarFluxoSalvarAnexo(dto.getAnexo(), atualizado, token);

                List<IncentivoClienteParametroRespostaAtualizarDetalheDTO> detalhesComGcSalvo = this.incentivoClienteParametroDetalheService
                                .executarFluxoEditarDetalheParametroIncentivo(dto.getListaDeDetalhes(), atualizado,
                                                token);

                return new IncentivoClienteRespostaEstruturaAtualizarDTO(atualizado.getId(),
                                anexoRespostaAnexoDTO.getId(),
                                detalhesComGcSalvo);
        }

        @Override
        public void executarFluxoRemoverIncentivo(Integer id, String token) {
                this.incentivoClienteParametroRemocaoValidacao.validar(id);

                IncentivoCliente incentivoCliente = this.incentivoClienteRepository.buscarIncentivoExistentePorId(id);

                this.removerIncentivoValido(incentivoCliente, this.jwtTokenProvider.buscarIdUsuario(token),
                                ConvertObjectToJsonCesan.execute(incentivoCliente));
        }

        private IncentivoCliente executarFluxoCadastrarParametroIncentivo(IncentivoClienteCadastroDTO dto,
                        String token) {
                this.incentivoClienteParametroCadastrarValidacao.validar(dto.getTipoIncentivo());

                IncentivoCliente incentivoCliente = this.incentivoClienteCadastroMapper.toEntity(dto);
                TipoIncentivoCliente tipoIncentivoCliente = tipoIncentivoClienteRepository
                                .findById(dto.getTipoIncentivo()).get();
                incentivoCliente.setTipoIncentivoCliente(tipoIncentivoCliente);
                IncentivoCliente salvo = this.cadastrarIncentivoValido(incentivoCliente, token);

                return salvo;
        }

        private IncentivoCliente cadastrarIncentivoValido(IncentivoCliente incentivoCliente, String token) {
                this.incentivoClienteCadastroRegra.validar(incentivoCliente);
                Long idUsuario = this.jwtTokenProvider.buscarIdUsuario(token);

                incentivoCliente.setUsuarioSgaus("jean");
                incentivoCliente.setUsuario(usuarioRepository.findById(idUsuario).get());

                this.incentivoClienteRepository.saveAndFlush(incentivoCliente);

                this.auditoriaService.gerarAuditoriaCadastramento(incentivoCliente.getId().longValue(),
                                ConvertObjectToJsonCesan.execute(incentivoCliente), 73L, "Parâmetro de Negociação",
                                idUsuario);

                this.salvarHistoricoIncenticoCliente(incentivoCliente);

                return incentivoCliente;
        }

        private IncentivoCliente executarFluxoEditarParametroIncentivo(IncentivoClienteAtualizarDTO dto, String token) {
                this.incentivoClienteParametroAtualizarValidacao.validar(dto.getId());

                IncentivoCliente incentivoCliente = this.incentivoClienteRepository
                                .buscarIncentivoExistentePorId(dto.getId());

                String dadosAntesAtualizar = ConvertObjectToJsonCesan.execute(incentivoCliente);

                IncentivoCliente dadoAtualizado = this.atualizarIncentivoValido(incentivoCliente, dto.getDescricao(),
                                dto.getDataInicioVigencia(), dto.getDataFimVigencia(),
                                this.jwtTokenProvider.buscarIdUsuario(token), dadosAntesAtualizar);

                return dadoAtualizado;
        }
        private boolean houveAlteracaoDataVigencia(IncentivoCliente incentivoCliente, LocalDateTime dataInicioVigencia, LocalDateTime dataFimVigencia) {

                return !(incentivoCliente.getDataInicioVigencia().equals(dataInicioVigencia) && incentivoCliente.getDataFimVigencia().equals(dataFimVigencia));
        }
        private IncentivoCliente atualizarIncentivoValido(IncentivoCliente incentivoCliente, String descricao,
                        LocalDateTime dataInicioVigencia, LocalDateTime dataFimVigencia, Long idUsuario,
                        String dadosAntesAtualizar) {
  
                if (this.houveAlteracaoDataVigencia(incentivoCliente, dataInicioVigencia, dataFimVigencia)) {
                        this.incentivoClienteAtualizarRegra.validar(incentivoCliente);
                }

                incentivoCliente.setDescricao(descricao);
                incentivoCliente.setDataInicioVigencia(dataInicioVigencia);
                incentivoCliente.setDataFimVigencia(dataFimVigencia);
                if (incentivoCliente.getTipoIncentivoCliente().getId() == null) {
                        incentivoCliente.setTipoIncentivoCliente(null);
                }

                this.auditoriaService.gerarAuditoriaAlteracao(incentivoCliente.getId().longValue(), dadosAntesAtualizar,
                                ConvertObjectToJsonCesan.execute(incentivoCliente), 73L, "Parâmetro de Negociação",
                                idUsuario);

                this.salvarHistoricoIncenticoCliente(incentivoCliente);

                return incentivoCliente;
        }

        private void removerIncentivoValido(IncentivoCliente incentivoCliente, Long idUsuario,
                        String dadosAntesAtualizar) {
                incentivoCliente.setDataHoraExclusao(LocalDateTime.now());

                this.auditoriaService.gerarAuditoriaAlteracao(incentivoCliente.getId().longValue(), dadosAntesAtualizar,
                                ConvertObjectToJsonCesan.execute(incentivoCliente), 73L, "Parâmetro de Negociação",
                                idUsuario);

        }

        private void salvarHistoricoIncenticoCliente(IncentivoCliente incentivoCliente) {
                IncentivoClienteHistorico incentivoClienteHistorico = new IncentivoClienteHistorico(incentivoCliente);
                this.incentivoClienteHistoricoRepository.save(incentivoClienteHistorico);
        }

}

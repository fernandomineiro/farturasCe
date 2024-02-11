package moduloFaturamento.service.impl;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.FeriadoGridComparator;
import moduloFaturamento.dto.*;
import moduloFaturamento.dto.feriado.*;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.mapper.FeriadoCadastroMapper;
import moduloFaturamento.mapper.FeriadoRespostaCadastroMapper;
import moduloFaturamento.mapper.FeriadoRespostaConsultaMapper;
import moduloFaturamento.model.Feriado;
import moduloFaturamento.model.IdFeriado;
import moduloFaturamento.model.TipoFeriado;
import moduloFaturamento.model.TipoUnidadeFederativa;
import moduloFaturamento.repository.FeriadoRepository;
import moduloFaturamento.service.FeriadoService;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.feriado.FeriadoAtualizacaoValidacao;
import moduloFaturamento.validacoes.feriado.FeriadoCadastroValidacao;
import moduloFaturamento.validacoes.feriado.FeriadoConsultaValidacao;
import moduloFaturamento.validacoes.feriado.FeriadoRemocaoValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeriadoServiceImpl implements FeriadoService {
    @Autowired
    private FeriadoRepository feriadoRepository;
    @Autowired
    private FeriadoRespostaConsultaMapper feriadoRespostaConsultaMapper;
    @Autowired
    private FeriadoConsultaValidacao feriadoConsultaValidacao;
    @Autowired
    private FeriadoCadastroValidacao feriadoCadastroValidacao;
    @Autowired
    private FeriadoAtualizacaoValidacao feriadoAtualizacaoValidacao;
    @Autowired
    private FeriadoRemocaoValidacao feriadoRemocaoValidacao;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private FeriadoCadastroMapper feriadoCadastroMapper;
    @Autowired
    private FeriadoRespostaCadastroMapper feriadoRespostaCadastroMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Feriado retornarEntidadeFeriadoExistente(Integer id) {
        return this.feriadoRepository.findById(id);
    }

    @Override
    public FeriadoRespostaConsultaDTO buscarFeriadoPorId(Integer id) {
        this.feriadoConsultaValidacao.validarConsulta(id);
        Feriado feriadoValido = this.retornarEntidadeFeriadoExistente(id);
        return this.feriadoRespostaConsultaMapper.toDto(feriadoValido);
    }

    @Override
    public GenericoWrapperDTO<List<FeriadoRespostaGridDTO>> buscarFeriadosPorFiltro(FeriadoFilterDTO filter, Pageable pageable) {
        Integer dataFeriadoInicio = ConverterData.localDateEmIntegerDataFormatoDB(filter.getDataFeriadoInicio());
        Integer dataFeriadoFim = ConverterData.localDateEmIntegerDataFormatoDB(filter.getDataFeriadoFim());
        Short tipoFeriado = filter.getIdTipoFeriado();
        String nomeFeriado = filter.getNomeFeriado();
        Integer unidadeFederativa = filter.getIdUnidadeFederativa();
        Short cdLocalidade = filter.getIdLocalidade();

        List<FeriadoRespostaGridDTO> feriadoRespostaGridDTO = this.feriadoRepository.buscarListaFeriadosPorFiltro(dataFeriadoInicio, dataFeriadoFim, tipoFeriado, nomeFeriado, unidadeFederativa, cdLocalidade);
        return Paginacao.paginarCampoUnico(new FeriadoGridComparator(), pageable, feriadoRespostaGridDTO);
    }

    @Override
    public FeriadoRespostaCadastrarDTO executarFluxoSalvar(FeriadoCadastrarDTO dto, String token) {
        Integer dataFeriado = ConverterData.localDateEmIntegerDataFormatoDB(dto.getDataFeriado());
        this.feriadoCadastroValidacao.gerenciarValidacaoParaCadastrar(dto.getIdLocalidade(), dto.getIdTipoFeriado(), dto.getIdUnidadeFederativa(), dataFeriado);
        Feriado feriado = this.feriadoCadastroMapper.toEntity(dto);
        Feriado feriadoCadastrado = this.cadastrarFeriadoValido(feriado, this.jwtTokenProvider.buscarLogin(token), this.jwtTokenProvider.buscarIdUsuario(token));
        return this.feriadoRespostaCadastroMapper.toDto(feriadoCadastrado);
    }

    @Override
    public FeriadoRespostaAtualizarDTO executarFluxoAtualizar(FeriadoAtualizarDTO dto, String token) {
        this.feriadoAtualizacaoValidacao.gerenciarValidacaoDadosParaAtualizar(dto.getId(), dto.getIdLocalidade(), dto.getIdTipoFeriado(), dto.getIdUnidadeFederativa());
        Feriado feriado = this.retornarEntidadeFeriadoExistente(dto.getId());
        String feriadoAntesAtualizar = ConvertObjectToJsonCesan.execute(feriado);
        this.atualizarFeriadoValido(feriado, feriadoAntesAtualizar, dto.getIdLocalidade(), dto.getNomeFeriado(), dto.getIdTipoFeriado(), dto.getIdUnidadeFederativa(), this.jwtTokenProvider.buscarIdUsuario(token));
        return new FeriadoRespostaAtualizarDTO(feriado.getId());
    }

    @Override
    public void executarFluxoDeletar(Integer id, String token) {
        this.feriadoRemocaoValidacao.gerenciarValidacaoDadosParaDeletar(id);
        Feriado feriado = this.retornarEntidadeFeriadoExistente(id);
        String feriadoAntesAtualizar = ConvertObjectToJsonCesan.execute(feriado);
        this.removerFeriadoValido(feriado, feriadoAntesAtualizar, this.jwtTokenProvider.buscarIdUsuario(token));
    }

    private Feriado cadastrarFeriadoValido(Feriado feriado, String loginUsuario, Long idUsuario) {
        feriado.setIdFeriado(new IdFeriado(feriado.getIdFeriado().getCdCidade(), ConverterData.localDateEmIntegerDataFormatoDB(feriado.getDataFeriado())));
        feriado.setLoginUsuario(loginUsuario);
        feriado.setDatahoraCadastro(LocalDateTime.now());
        feriado.setUnidadeFederativa(Optional.ofNullable(feriado.getUnidadeFederativa()).map(TipoUnidadeFederativa::getId).orElse(null) == null ? null : feriado.getUnidadeFederativa());
        Feriado feriadoSalvo = this.feriadoRepository.saveAndFlush(feriado);
        feriadoSalvo.setId(this.feriadoRepository.buscarIdFeriadoPorCidadeEDtFeriado(feriadoSalvo.getIdFeriado().getDtFeriado(), feriadoSalvo.getIdFeriado().getCdCidade()));

        this.gerarAuditoria(feriadoSalvo, "", ConvertObjectToJsonCesan.execute(feriadoSalvo), idUsuario);
        return feriadoSalvo;
    }

    private void atualizarFeriadoValido(Feriado feriado, String feriadoAntesAtualizar, Short novoCdCidade, String novoNomeFeriado, Short novoIdTipoFeriado, Integer novoIdUnidadeFederativa, Long idUsuario) {
        feriado.setDcFeriado(novoNomeFeriado);
        feriado.setDataFeriado(ConverterData.integerEmLocalDateFormatoDB(feriado.getIdFeriado().getDtFeriado()));
        feriado.setTpFeriado(new TipoFeriado(novoIdTipoFeriado));
        feriado.setUnidadeFederativa(novoIdUnidadeFederativa != null ? new TipoUnidadeFederativa(novoIdUnidadeFederativa) : null);
        feriado.setIdFeriado(new IdFeriado(novoCdCidade, feriado.getIdFeriado().getDtFeriado()));

        this.gerarAuditoria(feriado, feriadoAntesAtualizar, ConvertObjectToJsonCesan.execute(feriado), idUsuario);
    }

    private void removerFeriadoValido(Feriado feriado, String feriadoAntesAtualizar, Long idUsuario) {
        feriado.setDataHoraExclusao(LocalDateTime.now());
        this.gerarAuditoria(feriado, feriadoAntesAtualizar, ConvertObjectToJsonCesan.execute(feriado), idUsuario);
    }

    private void gerarAuditoria(Feriado feriado, String feriadoAntesAtualizar, String feriadoDepoisAtualizar, Long idUsuario) {
        this.auditoriaService.gerarAuditoria(feriado.getId().longValue(), feriadoAntesAtualizar, feriadoDepoisAtualizar, 61L, "Feriado", idUsuario);
    }
}

package moduloFaturamento.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.autenticacao.JwtTokenProvider;
import moduloFaturamento.comparator.CronogramaFaturamentoGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaAtualizarDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCadastroMultiploDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCadastroUnicoDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaDataTarifaDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaFaseDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaFilterDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaCalculoDataTarifaDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaCalculoDataVencimentoDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaCalculoDatasDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaConsultaDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaRespostaPopupCadastroUnicoDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaSalvarRespostaDTO;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaCadastroMultiploMapper;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaCadastroUnicoMapper;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaFaseMapper;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaRespostaConsultaProjectionMapper;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaSalvarRespostaMapper;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.infra.service.AuditoriaService;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.model.IdCronogramaFatura;
import moduloFaturamento.model.TipoFaseCronograma;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoAtualizarRegra;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoCadastroRegra;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoCalculaSugestaoDatasRegra;
import moduloFaturamento.regras.cronogramaFaturamento.CronogramaFaturamentoRemocaoFisicaRegra;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaseCronogramaRepository;
import moduloFaturamento.service.CronogramaFaturamentoService;
import moduloFaturamento.util.Constants;
import moduloFaturamento.util.ConvertObjectToJsonCesan;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoAtualizacaoValidacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoConsultaValidacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoMultiploCadastroValidacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoRemocaoFisicaValidacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoUnicoCadastroValidacao;

@Service
@Transactional
public class CronogramaFaturamentoServiceImpl implements CronogramaFaturamentoService {
    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private CronogramaFaturamentoConsultaValidacao cronogramaFaturamentoConsultaValidacao;
    @Autowired
    private CronogramaFaturamentoUnicoCadastroValidacao cronogramaFaturamentoUnicoCadastroValidacao;
    @Autowired
    private CronogramaFaturamentoMultiploCadastroValidacao cronogramaFaturamentoMultiploCadastroValidacao;
    @Autowired
    private CronogramaFaturamentoAtualizacaoValidacao cronogramaFaturamentoAtualizacaoValidacao;
    @Autowired
    private CronogramaFaturamentoRemocaoFisicaValidacao cronogramaFaturamentoRemocaoFisicaValidacao;
    @Autowired
    private CronogramaFaturaRespostaConsultaProjectionMapper cronogramaFaturaRespostaConsultaProjectionMapper;
    @Autowired
    private CronogramaFaturaSalvarRespostaMapper cronogramaFaturaSalvarRespostaMapper;
    @Autowired
    private CronogramaFaturaCadastroUnicoMapper cronogramaFaturaCadastroUnicoMapper;
    @Autowired
    private CronogramaFaturaCadastroMultiploMapper cronogramaFaturaCadastroMultiploMapper;
    @Autowired
    private CronogramaFaturamentoCalculaSugestaoDatasRegra cronogramaFaturamentoCalculaSugestaoDatasRegra;
    @Autowired
    private CronogramaFaturamentoCadastroRegra cronogramaFaturamentoCadastroRegra;
    @Autowired
    private CronogramaFaturamentoAtualizarRegra cronogramaFaturamentoAtualizarRegra;
    @Autowired
    private CronogramaFaturamentoRemocaoFisicaRegra cronogramaFaturamentoRemocaoFisicaRegra;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private FaseCronogramaRepository faseCronogramaRepository;
    @Autowired
    private CronogramaFaturaFaseMapper cronogramaFaturaFaseMapper;

    @Override
    public List<CronogramaFaturaFaseDTO> buscarTodasFasesCronograma(){
        List<TipoFaseCronograma> fasesCronograma = this.faseCronogramaRepository.findAll();
        return this.cronogramaFaturaFaseMapper.toDto(fasesCronograma);
    }

    @Override
    public List<CronogramaFaturaFaseDTO> buscarFasesCronograma(CronogramaFaturamentoFaseEnum ... fases){
    	
    	List<Short> collect = List.of(fases).stream().map(CronogramaFaturamentoFaseEnum::getValor).collect(Collectors.toList());
    	
        List<TipoFaseCronograma> fasesCronograma = this.faseCronogramaRepository.findAllById(collect);
        
        return this.cronogramaFaturaFaseMapper.toDto(fasesCronograma);
    }
        
    @Override
    public GenericoWrapperDTO<List<CronogramaFaturaRespostaGridProjectionDTO>> buscarCronogramaPorFiltro(CronogramaFaturaFilterDTO filter, Pageable pageable) {
        Integer dataReferencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(filter.getDataReferencia());
        Short cdCidade = filter.getCdCidade();
        Short ciclo = filter.getCiclo();
        List<CronogramaFaturaRespostaGridProjectionDTO> dto = this.cronogramaFaturaRepository.buscarListaCronogramaFaturasPorFiltro(dataReferencia, cdCidade, ciclo);
        return Paginacao.paginarCampoUnico(new CronogramaFaturamentoGridComparator(), pageable, dto);
    }

    @Override
    public CronogramaFaturaRespostaConsultaDTO buscarCronogramaPorId(Long id) {
        this.cronogramaFaturamentoConsultaValidacao.validarConsulta(id);
        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarCronogramaExistentePorId(id);
        return this.cronogramaFaturaRespostaConsultaProjectionMapper.toDto(cronogramaFatura);
    }

    @Override
    public CronogramaFaturaRespostaCalculoDatasDTO retornarDatasCalculadasOperacaoAdicionaSubtraiBaseadoDataLeituraCronograma(LocalDate dataLeitura) {
        LocalDate dataArquivoLeituraPrevista = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoArquivoLeituraPrevista(dataLeitura);
        LocalDate dataFaturamentoPrevista = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoFaturamentoPrevista(dataLeitura);
        LocalDate dataConsolidacaoPrevista = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoConsolidacaoPrevista(dataLeitura);
        LocalDate dataEmissaoFaturaPrevista = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoEmissaoFaturaPrevista(dataLeitura);
        LocalDate dataArquivoCortePrevista = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoArquivoCortePrevista(dataLeitura);

        CronogramaFaturaRespostaCalculoDatasDTO dto = new CronogramaFaturaRespostaCalculoDatasDTO();
        dto.setDataArquivoLeituraPrevista(dataArquivoLeituraPrevista);
        dto.setDataFaturamentoPrevista(dataFaturamentoPrevista);
        dto.setDataConsolidaPrevista(dataConsolidacaoPrevista);
        dto.setDataEmissaoPrevista(dataEmissaoFaturaPrevista);
        dto.setDataCortePrevista(dataArquivoCortePrevista);
        return dto;
    }

    @Override
    public CronogramaFaturaRespostaCalculoDataVencimentoDTO retonarDataVencimentoCalculadaCronograma(Short cdCidade, Short ciclo, LocalDate dataReferencia) {
        LocalDate dataVencimento = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoVencimento(cdCidade, ciclo, dataReferencia);
        CronogramaFaturaRespostaCalculoDataVencimentoDTO dto = new CronogramaFaturaRespostaCalculoDataVencimentoDTO();
        dto.setDataVencimento(dataVencimento);
        return dto;
    }

    @Override
    public CronogramaFaturaRespostaCalculoDataTarifaDTO retornarDataTarifaCalculadaCronograma(CronogramaFaturaDataTarifaDTO dto) {
        LocalDate dataAprovacaoTarifa = this.cronogramaFaturamentoCalculaSugestaoDatasRegra.obterDataSugestaoAprovacaoTarifa(dto.getCdCidade(), dto.getCiclo(), dto.getDataReferencia(), dto.getDataLeitura());
        CronogramaFaturaRespostaCalculoDataTarifaDTO dtoRetorno = new CronogramaFaturaRespostaCalculoDataTarifaDTO();
        dtoRetorno.setDataAprovacaoTarifa(dataAprovacaoTarifa);
        return dtoRetorno;
    }

    @Override
    public CronogramaFaturaRespostaPopupCadastroUnicoDTO retornarCondicaoAbrirPopupConfirmarCadastroUnico(Short cdCidade, Short ciclo, LocalDate dataReferencia) {
        CronogramaFatura cronogramaFatura = this.buscarCronogramaReferenciaMesAnterior(cdCidade, ciclo, dataReferencia);

        if (cronogramaFatura == null) {
            return new CronogramaFaturaRespostaPopupCadastroUnicoDTO(true);
        } else {
            return new CronogramaFaturaRespostaPopupCadastroUnicoDTO(false);
        }
    }

    @Override
    public CronogramaFaturaSalvarRespostaDTO executarFluxoCadastrar(CronogramaFaturaCadastroUnicoDTO dto, String token) {

        this.cronogramaFaturamentoUnicoCadastroValidacao.gerenciarValidacaoParaCadastrar(dto.getCdCidade(), dto.getCiclo(), ConverterData.localDateEmIntegerReferenciaFormatoDB(dto.getDataReferencia()));

        CronogramaFatura cronogramaFatura = this.cronogramaFaturaCadastroUnicoMapper.toEntity(dto);
        CronogramaFatura cronogramaCadastrado = this.cadastrarCronogramaValido(cronogramaFatura, this.jwtTokenProvider.buscarLogin(token), this.jwtTokenProvider.buscarIdUsuario(token));
        return this.cronogramaFaturaSalvarRespostaMapper.toDto(cronogramaCadastrado);
    }

    @Override
    public List<CronogramaFaturaSalvarRespostaDTO> executarFluxoCadastrar(CronogramaFaturaCadastroMultiploDTO dto, String token) {
        List<CronogramaFatura> cronogramasCriados = new ArrayList<>();
        LocalDate dataReferenciaSubtraidoMenosUm = dto.getDataReferencia().minusMonths(1);

        List<Short> cidadesParaGerarCronograma = this.cronogramaFaturamentoCadastroRegra.obterListaCidadesDoCronogramaBaseadoCicloEMesReferencia(dto.getCiclo(), dataReferenciaSubtraidoMenosUm);

        for (Short cidade : cidadesParaGerarCronograma) {
            this.cronogramaFaturamentoUnicoCadastroValidacao.gerenciarValidacaoParaCadastrar(cidade, dto.getCiclo(), ConverterData.localDateEmIntegerReferenciaFormatoDB(dto.getDataReferencia()));
            CronogramaFatura cronogramaFatura = this.cronogramaFaturaCadastroMultiploMapper.toEntity(dto);

            IdCronogramaFatura idCronogramaFatura = new IdCronogramaFatura(cidade, cronogramaFatura.getIdCronogramaFatura().getCiclo(), cronogramaFatura.getIdCronogramaFatura().getRefCronograma());
            cronogramaFatura.setIdCronogramaFatura(idCronogramaFatura);

            cronogramasCriados.add(this.cadastrarCronogramaValido(cronogramaFatura, this.jwtTokenProvider.buscarLogin(token), this.jwtTokenProvider.buscarIdUsuario(token)));
        }
        return this.cronogramaFaturaSalvarRespostaMapper.toDto(cronogramasCriados);
    }

    @Override
    public CronogramaFaturaSalvarRespostaDTO executarFluxoEditar(CronogramaFaturaAtualizarDTO dto, String token) {
        Long idCronograma = dto.getId();
        Short cdCidade = dto.getCdCidade();
        Short ciclo = dto.getCiclo();
        Integer dataReferencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(dto.getDataReferencia());
        Integer aprovacaoTarifa = ConverterData.localDateEmIntegerDataFormatoDB(dto.getAprovacaoTarifa());
        Integer vencimentoFatura = ConverterData.localDateEmIntegerDataFormatoDB(dto.getVencimentoFatura());
        Integer geraArquivoPrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getGeraArquivoPrevista());
        Integer leituraPrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getLeituraPrevista());
        Integer faturamentoPrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getFaturamentoPrevista());
        Integer consolidaPrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getConsolidaPrevista());
        Integer emissaoPrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getEmissaoPrevista());
        Integer cortePrevista = ConverterData.localDateEmIntegerDataFormatoDB(dto.getCortePrevista());
        boolean avancarFase = dto.getAvancarFase();
        boolean retornarFase = dto.getRetornarFase();

        this.cronogramaFaturamentoAtualizacaoValidacao.gerenciarValidacaoParaAtualizar(cdCidade, ciclo, dataReferencia, idCronograma, avancarFase, retornarFase);

        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarCronogramaExistentePorId(idCronograma);
        String feriadoAntesAtualizar = ConvertObjectToJsonCesan.execute(cronogramaFatura);

        this.atualizarCronogramaValido(idCronograma, cdCidade, ciclo, dataReferencia, aprovacaoTarifa, vencimentoFatura, geraArquivoPrevista, leituraPrevista, faturamentoPrevista, consolidaPrevista,
                emissaoPrevista, cortePrevista, avancarFase, retornarFase, cronogramaFatura, feriadoAntesAtualizar, this.jwtTokenProvider.buscarIdUsuario(token));

        return new CronogramaFaturaSalvarRespostaDTO(cronogramaFatura.getId());
    }

    @Override
    public void executarFluxoDeletar(Long idCronograma, String token){
        this.cronogramaFaturamentoRemocaoFisicaValidacao.gerenciarValidacaoDadosParaDeletar(idCronograma);

        CronogramaFatura cronogramaFatura = this.cronogramaFaturaRepository.buscarCronogramaExistentePorId(idCronograma);

        this.removerCronogramaValido(cronogramaFatura, this.jwtTokenProvider.buscarIdUsuario(token));
    }

    private void removerCronogramaValido(CronogramaFatura cronogramaFatura, Long idUsuario) {
        Short fase = cronogramaFatura.getFase().getId();
        Short cdCidade = cronogramaFatura.getIdCronogramaFatura().getCdCidade();
        Short ciclo = cronogramaFatura.getIdCronogramaFatura().getCiclo();
        Integer dataReferencia = cronogramaFatura.getIdCronogramaFatura().getRefCronograma();

        this.cronogramaFaturamentoRemocaoFisicaRegra.gerenciarRegrasCronogramaExistenteParaExcluir(fase, cdCidade, ciclo, dataReferencia);

        String feriadoAntesAtualizar = ConvertObjectToJsonCesan.execute(cronogramaFatura);
        this.cronogramaFaturaRepository.delete(cronogramaFatura);

        this.gerarAuditoria(cronogramaFatura, feriadoAntesAtualizar, Constants.EMPTY_STRING, idUsuario);
    }


    private void atualizarCronogramaValido(Long idCronograma, Short cdCidade, Short ciclo, Integer dataReferencia, Integer aprovacaoTarifa, Integer vencimentoFatura,
                                           Integer geraArquivoPrevista, Integer leituraPrevista, Integer faturamentoPrevista, Integer consolidaPrevista,
                                           Integer emissaoPrevista, Integer cortePrevista, Boolean avancarFase, Boolean retornarFase, CronogramaFatura cronogramaFatura,
                                           String feriadoAntesAtualizar, Long idUsuario) {

        cronogramaFatura.setDataTarifa(aprovacaoTarifa);
        cronogramaFatura.setDataVencimento(vencimentoFatura);
        cronogramaFatura.setDataGeraArquivoPrevista(geraArquivoPrevista);
        cronogramaFatura.setDataLeituraPrevista(leituraPrevista);
        cronogramaFatura.setDataFaturamentoPrevista(faturamentoPrevista);
        cronogramaFatura.setDataConsolidaPrevista(consolidaPrevista);
        cronogramaFatura.setDataEmissaoPrevista(emissaoPrevista);
        cronogramaFatura.setDataCortePrevista(cortePrevista);
        cronogramaFatura.setFase(new TipoFaseCronograma(this.cronogramaFaturamentoAtualizarRegra.retornarFaseAtualizadaPelaAcaoAvancarRetornarFase(cronogramaFatura.getFase().getId(), retornarFase, avancarFase)));

        this.cronogramaFaturamentoAtualizarRegra.gerenciarRegrasCronogramaExistenteParaEditar(cronogramaFatura.getFase().getId());

        this.gerarAuditoria(cronogramaFatura, feriadoAntesAtualizar, ConvertObjectToJsonCesan.execute(cronogramaFatura), idUsuario);
    }


    private CronogramaFatura cadastrarCronogramaValido(CronogramaFatura cronogramaFatura, String loginUsuario, Long idUsuario) {
        Short cdCidade = cronogramaFatura.getIdCronogramaFatura().getCdCidade();
        Short ciclo = cronogramaFatura.getIdCronogramaFatura().getCiclo();
        Integer dataReferencia = cronogramaFatura.getIdCronogramaFatura().getRefCronograma();
        cronogramaFatura.setFase(new TipoFaseCronograma(this.cronogramaFaturamentoCadastroRegra.obterFaseCadastroCronograma()));

        CronogramaFatura cronogramaSalvo = this.cronogramaFaturaRepository.saveAndFlush(cronogramaFatura);
        cronogramaSalvo.setId(this.cronogramaFaturaRepository.buscarIdCronogramaPorCdCidadeCicloDataReferencia(cdCidade, ciclo, dataReferencia));

        this.gerarAuditoria(cronogramaSalvo, Constants.EMPTY_STRING, ConvertObjectToJsonCesan.execute(cronogramaSalvo), idUsuario);

        return cronogramaSalvo;
    }

    private CronogramaFatura buscarCronogramaReferenciaMesAnterior(Short cdCidade, Short ciclo, LocalDate dataReferencia) {
        LocalDate dataReferenciaAnterior = dataReferencia.minusMonths(1);
        IdCronogramaFatura idCronogramaFatura = new IdCronogramaFatura(cdCidade, ciclo, ConverterData.localDateEmIntegerReferenciaFormatoDB(dataReferenciaAnterior));
        return this.cronogramaFaturaRepository.findByIdCronogramaFatura(idCronogramaFatura);
    }

    private void gerarAuditoria(CronogramaFatura cronogramaFatura, String feriadoAntesAtualizar, String feriadoDepoisAtualizar, Long idUsuario) {
        this.auditoriaService.gerarAuditoria(cronogramaFatura.getId(), feriadoAntesAtualizar, feriadoDepoisAtualizar, 64L, "Cronograma de Faturamento", idUsuario);
    }

    @Override
    public List<CronogramaFatura> buscarDataFaturaPorMatricula(Integer matriculaImovel, Integer ref) {
        List<CronogramaFatura> cronogramaFatura = this.cronogramaFaturaRepository.bucarPorMatricula(matriculaImovel, ref);
        return cronogramaFatura;
    }
}

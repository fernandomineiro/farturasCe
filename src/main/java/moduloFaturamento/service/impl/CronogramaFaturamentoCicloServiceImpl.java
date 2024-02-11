package moduloFaturamento.service.impl;

import moduloFaturamento.comparator.CronogramaFaturamentoCicloFaturamentoGridComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoFilterDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloImovelRespostaProjectionDTO;
import moduloFaturamento.dto.parametroLeituraMensalFaturamento.ParametroLeituraMensalFaturamentoDTO;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.FaturaRepository;
import moduloFaturamento.service.CronogramaFaturamentoCicloService;
import moduloFaturamento.service.ParametroLeituraMensalFaturamentoService;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;
import moduloFaturamento.validacoes.cronogramaFaturamento.CronogramaFaturamentoConsultaValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CronogramaFaturamentoCicloServiceImpl implements CronogramaFaturamentoCicloService {

    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private FaturaRepository faturaRepository;
    @Autowired
    private CronogramaFaturamentoConsultaValidacao cronogramaFaturamentoConsultaValidacao;
    @Autowired
    private ParametroLeituraMensalFaturamentoService parametroLeituraMensalFaturamentoService;

    @Override
    public CronogramaFaturaCicloFaturamentoParametroDTO buscarParametroProcessamentoMensal(){
        ParametroLeituraMensalFaturamentoDTO parametroLeituraMensalFaturamentoDTO = this.parametroLeituraMensalFaturamentoService.buscarParametro();

        return new CronogramaFaturaCicloFaturamentoParametroDTO(parametroLeituraMensalFaturamentoDTO.getId(), parametroLeituraMensalFaturamentoDTO.getTipoGeracao(), parametroLeituraMensalFaturamentoDTO.getHorario());
    }

    @Override
    public GenericoWrapperDTO<List<CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO>> buscarCronogramaPorFiltro(CronogramaFaturaCicloFaturamentoFilterDTO filter, Pageable pageable) {
        Integer dataReferencia = ConverterData.localDateEmIntegerReferenciaFormatoDB(filter.getDataReferencia());
        Short cdCidade = filter.getCdCidade();
        Short ciclo = filter.getCiclo();

        List<CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO> dto = this.cronogramaFaturaRepository.buscarListaCronogramaFaturasParaCicloFaturamentoPorFiltro(dataReferencia, cdCidade, ciclo);

        return Paginacao.paginarCampoUnico(new CronogramaFaturamentoCicloFaturamentoGridComparator(), pageable, dto);
    }

    @Override
    public CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO buscarCronogramaPorId(Long id) {
        this.cronogramaFaturamentoConsultaValidacao.validarConsulta(id);

        CronogramaFatura cronogramaFatura = cronogramaFaturaRepository.buscarCronogramaExistentePorId(id);
        Short ciclo = cronogramaFatura.getIdCronogramaFatura().getCiclo();
        Integer refCronograma = cronogramaFatura.getIdCronogramaFatura().getRefCronograma();
        Short cdCidade = cronogramaFatura.getIdCronogramaFatura().getCdCidade();

        CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO dto = this.cronogramaFaturaRepository.buscarDetalheCicloFaturamentoDeUmCronogramaExistentePorId(id);

        Integer faturasFase0 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.GERAR_ESPELHO.getValor(), cdCidade);
        Integer faturasFase1 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.INCLUIR_LEITURA.getValor(), cdCidade);
        Integer faturasFase2 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.FATURAR.getValor(), cdCidade);
        Integer faturasFase3 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.CONSOLIDAR.getValor(), cdCidade);
        Integer faturasFase4 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.EMITIR_FATURA.getValor(), cdCidade);
        Integer faturasFase5 = this.faturaRepository.contarQuantosFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo, refCronograma, CronogramaFaturamentoFaseEnum.ENCERRADO.getValor(), cdCidade);

        dto.setFase0QuantidadeFaturas(faturasFase0);
        dto.setFase1QuantidadeFaturas(faturasFase1);
        dto.setFase2QuantidadeFaturas(faturasFase2);
        dto.setFase3QuantidadeFaturas(faturasFase3);
        dto.setFase4QuantidadeFaturas(faturasFase4);
        dto.setFase5QuantidadeFaturas(faturasFase5);

        return dto;
    }

    @Override
    public List<CronogramaFaturaCicloImovelRespostaProjectionDTO> buscarMatriculasDasFaturasDeUmaFaseDoCronograma(Long idCronograma, List<Short> fase) {
        this.cronogramaFaturamentoConsultaValidacao.validarConsulta(idCronograma);

        CronogramaFatura cronogramaFatura = cronogramaFaturaRepository.buscarCronogramaExistentePorId(idCronograma);

        Short ciclo = cronogramaFatura.getIdCronogramaFatura().getCiclo();
        Integer refCronograma = cronogramaFatura.getIdCronogramaFatura().getRefCronograma();
        Short cdCidade = cronogramaFatura.getIdCronogramaFatura().getCdCidade();

        if(fase == null){
            fase = new ArrayList<>();
            for(short idFase = 0; idFase < 6; idFase++){
                fase.add(idFase);
            }
        }
        return this.faturaRepository.buscarImovelDasFaturasCadastradasParaCronogramaEmDeterminadaFase(ciclo,refCronograma, fase, cdCidade);
    }




}

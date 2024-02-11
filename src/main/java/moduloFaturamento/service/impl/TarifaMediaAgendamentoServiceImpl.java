package moduloFaturamento.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaCalcularPorGrupoServicoProjectionDTO;
import moduloFaturamento.model.ReferenciaTarifaMediaAdiado;
import moduloFaturamento.model.TarifaMedia;
import moduloFaturamento.regras.leitura.LeituraRegraPesquisaLeitura;
import moduloFaturamento.repository.CronogramaFaturaRepository;
import moduloFaturamento.repository.ReferenciaTarifaMediaAdiadoRepository;
import moduloFaturamento.repository.TarifaMediaRepository;
import moduloFaturamento.service.TarifaMediaAgendamentoService;
import moduloFaturamento.util.ConverterData;

@Service
@Transactional
public class TarifaMediaAgendamentoServiceImpl implements TarifaMediaAgendamentoService{

    @Autowired
    private CronogramaFaturaRepository cronogramaFaturaRepository;
    @Autowired
    private TarifaMediaRepository repository;
    @Autowired
    private ReferenciaTarifaMediaAdiadoRepository referenciaTarifaMediaAdiadoRepository;
    @Autowired
    private LeituraRegraPesquisaLeitura leituraRegraPesquisaLeitura;

    @Override
    public void executarRotinaTarifaMediaAgendamento() {

        Integer refParaCalculoTarifaMedia = buscarReferenciaAtualParaCalcularTarifaMedia();
        boolean cronogramaDeFaturaEstaoTodosNaFase5OuMais = cronogramaFaturaRepository.verificarSeCronogramaFaturaEstaComTodosFase5(refParaCalculoTarifaMedia);
  
        if (cronogramaDeFaturaEstaoTodosNaFase5OuMais) {

            calcularMedias(refParaCalculoTarifaMedia);
        } else {

            ReferenciaTarifaMediaAdiado referenciaTarifaMediaAdiado = new ReferenciaTarifaMediaAdiado();
            LocalDate referenciaEmLocalDate = ConverterData.integerEmLocalDateReferenciaFormatoDB(refParaCalculoTarifaMedia);    
            referenciaTarifaMediaAdiado.setRefTarifaMediaAdiado(referenciaEmLocalDate);
            referenciaTarifaMediaAdiadoRepository.save(referenciaTarifaMediaAdiado);
        }
    }

    @Override
    public void verificarSeExisteReferenciaAExecutar() {

        List<ReferenciaTarifaMediaAdiado> listaReferenciasAdiadas = referenciaTarifaMediaAdiadoRepository.findAll();
        
        if (!listaReferenciasAdiadas.isEmpty()) {

            verificarSeRefenciaPodeExecutarRotina(listaReferenciasAdiadas);
        }
    }

    private void verificarSeRefenciaPodeExecutarRotina(List<ReferenciaTarifaMediaAdiado> listaReferenciasAdiadas) {

        for (ReferenciaTarifaMediaAdiado referenciaTarifaMediaAdiado : listaReferenciasAdiadas) {

            Integer refTarifaMediaAdiado = ConverterData.localDateEmIntegerReferenciaFormatoDB(referenciaTarifaMediaAdiado.getRefTarifaMediaAdiado());
            boolean cronogramaDeFaturaEstaoTodosNaFase5OuMais = cronogramaFaturaRepository.verificarSeCronogramaFaturaEstaComTodosFase5(refTarifaMediaAdiado);

            if (cronogramaDeFaturaEstaoTodosNaFase5OuMais) {
                calcularMedias(refTarifaMediaAdiado);
            }
        }
    }

    private void calcularMedias(Integer refParaCalculoTarifaMedia) {

        calcularMediaAgua(refParaCalculoTarifaMedia);
        calcularMediaEsgoto(refParaCalculoTarifaMedia);
        calcularMediaDisponibilidade(refParaCalculoTarifaMedia);
        calcularTarifaMedia(refParaCalculoTarifaMedia);

        verificarSeRotinaSeTrataDeUmaReferenciaAdiado(refParaCalculoTarifaMedia);
    }

    private void calcularMediaAgua(Integer refParaCalculoTarifaMedia) {

        List<Short> listaServicoAgua = repository.listaServicosAgua();

        List<TarifaMediaCalcularPorGrupoServicoProjectionDTO> listaTarifaAgua = repository.buscarPorRegionalEGrupoConsumoPorGrupoServico(refParaCalculoTarifaMedia, listaServicoAgua);

        for (TarifaMediaCalcularPorGrupoServicoProjectionDTO tarifaMediaDTO : listaTarifaAgua) {

            Optional<TarifaMedia> buscaTarifaMedia = repository.findByIdRegionalAndIdGrupoDeConsumoAndRefTarifaMedia(
                tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia);

            if (buscaTarifaMedia.isPresent()) {

                buscaTarifaMedia.get().setValorMedioAgua(tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN));
                repository.save(buscaTarifaMedia.get());
            } else {

                TarifaMedia tarifaMedia = new TarifaMedia(tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia,
                        BigDecimal.ZERO, tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN), BigDecimal.ZERO, BigDecimal.ZERO);
                repository.save(tarifaMedia);
            }
        }
    }

    private void calcularMediaDisponibilidade(Integer refParaCalculoTarifaMedia) {

        List<Short> listaServicoDisponibilidadeEsgoto = repository.listaServicosDisponibilidadeEsgoto();

        List<TarifaMediaCalcularPorGrupoServicoProjectionDTO> listaTarifaDisponibilidade = repository.buscarPorRegionalEGrupoConsumoPorGrupoServico(refParaCalculoTarifaMedia,
            listaServicoDisponibilidadeEsgoto);

        for (TarifaMediaCalcularPorGrupoServicoProjectionDTO tarifaMediaDTO : listaTarifaDisponibilidade) {

            Optional<TarifaMedia> buscaTarifaMedia = repository.findByIdRegionalAndIdGrupoDeConsumoAndRefTarifaMedia(
                    tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia);

            if (buscaTarifaMedia.isPresent()) {

                buscaTarifaMedia.get().setValorMedioDisponibilidade(tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN));
                repository.save(buscaTarifaMedia.get());
            } else {

                TarifaMedia tarifaMedia = new TarifaMedia(tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN));

                repository.save(tarifaMedia);
            }
        }
    }

    private void calcularMediaEsgoto(Integer refParaCalculoTarifaMedia) {

        List<Short> listaServicoEsgoto = repository.listaServicosEsgoto();

        List<TarifaMediaCalcularPorGrupoServicoProjectionDTO> listaTarifaEsgoto = repository.buscarPorRegionalEGrupoConsumoPorGrupoServico(
            refParaCalculoTarifaMedia, listaServicoEsgoto);

        for (TarifaMediaCalcularPorGrupoServicoProjectionDTO tarifaMediaDTO : listaTarifaEsgoto) {

            Optional<TarifaMedia> buscaTarifaMedia = repository.findByIdRegionalAndIdGrupoDeConsumoAndRefTarifaMedia(
                    tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia);

            if (buscaTarifaMedia.isPresent()) {

                buscaTarifaMedia.get().setValorMedioEsgoto(tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN));
                repository.save(buscaTarifaMedia.get());
            } else {

                TarifaMedia tarifaMedia = new TarifaMedia(tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia,
                        BigDecimal.ZERO, BigDecimal.ZERO, tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN), BigDecimal.ZERO);

                repository.save(tarifaMedia);
            }
        }
    }

    private void calcularTarifaMedia(Integer refParaCalculoTarifaMedia) {

        List<Short> listaServicosTarifaMedia = repository.listaServicosTarifaMedia();

        List<TarifaMediaCalcularPorGrupoServicoProjectionDTO> listaTarifaMediaGeral = repository.buscarPorRegionalEGrupoConsumoPorGrupoServico(
            refParaCalculoTarifaMedia, listaServicosTarifaMedia);

        for (TarifaMediaCalcularPorGrupoServicoProjectionDTO tarifaMediaDTO : listaTarifaMediaGeral) {

            Optional<TarifaMedia> buscaTarifaMedia = repository.findByIdRegionalAndIdGrupoDeConsumoAndRefTarifaMedia(
                    tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia);

            if (buscaTarifaMedia.isPresent()) {

                buscaTarifaMedia.get().setValorTarifaMedia(tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN));
                repository.save(buscaTarifaMedia.get());
            } else {

                TarifaMedia tarifaMedia = new TarifaMedia(tarifaMediaDTO.getRegional(), tarifaMediaDTO.getGrupoDeConsumo(), refParaCalculoTarifaMedia,
                        tarifaMediaDTO.getMedia().setScale(2, RoundingMode.HALF_EVEN), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

                repository.save(tarifaMedia);
            }
        }
    }

    private void verificarSeRotinaSeTrataDeUmaReferenciaAdiado(Integer refParaCalculoTarifaMedia) {

        LocalDate localdateDaReferencia = ConverterData.integerEmLocalDateReferenciaFormatoDB(refParaCalculoTarifaMedia);

        Optional<ReferenciaTarifaMediaAdiado> referenciaAdiado = referenciaTarifaMediaAdiadoRepository.findByRefTarifaMediaAdiado(localdateDaReferencia);

        if (referenciaAdiado.isPresent()) {
            referenciaTarifaMediaAdiadoRepository.deleteById(referenciaAdiado.get().getId());
        }
    }   
    
    private Integer buscarReferenciaAtualParaCalcularTarifaMedia() {
       
        Integer refAtualEmInteger = ConverterData.localDateEmIntegerReferenciaFormatoDB(LocalDate.now());
        return leituraRegraPesquisaLeitura.buscarReferenciaAnterior(refAtualEmInteger);
    }
}

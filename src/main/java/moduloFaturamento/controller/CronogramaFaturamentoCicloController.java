package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoFilterDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO;
import moduloFaturamento.dto.cronogramaFaturaCicloFaturamento.projection.CronogramaFaturaCicloImovelRespostaProjectionDTO;
import moduloFaturamento.service.CronogramaFaturamentoCicloService;
import moduloFaturamento.service.ParametroLeituraMensalFaturamentoService;
import moduloFaturamento.service.common.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/cicloCronogramaFaturamento")
@CrossOrigin
public class CronogramaFaturamentoCicloController {

    @Autowired
    private CronogramaFaturamentoCicloService cronogramaFaturamentoCicloService;
    @Autowired
    private ParametroLeituraMensalFaturamentoService parametroLeituraMensalFaturamentoService;
    @Autowired
    private LocalidadeService localidadeService;

    @GetMapping
    public GenericoWrapperDTO<List<CronogramaFaturaCicloFaturamentoRespostaGridProjectionDTO>> listarGrid(@Valid CronogramaFaturaCicloFaturamentoFilterDTO filter, Pageable pageable) {
        return this.cronogramaFaturamentoCicloService.buscarCronogramaPorFiltro(filter, pageable);
    }

    @GetMapping("/listarLocalidade")
    public List<LocalidadeDTO> listarLocalidade() {
        return this.localidadeService.buscarTodasLocalidades();
    }

    @GetMapping("/{id}")
    public CronogramaFaturaCicloFaturamentoDetalheRespostaProjectionDTO buscarDetalhePorIdCronograma(@PathVariable(value = "id") Long id) {
        return this.cronogramaFaturamentoCicloService.buscarCronogramaPorId(id);
    }

    @GetMapping("/matriculasDasFaturasDeUmaFase/idCronograma/{id}")
    public List<CronogramaFaturaCicloImovelRespostaProjectionDTO> buscarDetalhePorIdCronograma(@PathVariable(value = "id") Long id, @RequestParam(value = "fase", required = false) List<Short> fase) {
        return this.cronogramaFaturamentoCicloService.buscarMatriculasDasFaturasDeUmaFaseDoCronograma(id, fase);
    }

    @GetMapping("/parametro")
    public CronogramaFaturaCicloFaturamentoParametroDTO buscarParametroProcessamentoMensal() {
        return this.cronogramaFaturamentoCicloService.buscarParametroProcessamentoMensal();
    }

    @PutMapping("/parametro")
    public CronogramaFaturaCicloFaturamentoParametroRespostaSalvarDTO atualizar(@RequestBody @Valid CronogramaFaturaCicloFaturamentoParametroAtualizacaoDTO dto, @RequestHeader("Authorization") String token) {
        return this.parametroLeituraMensalFaturamentoService.executarFluxoAtualizarPorCronogramaFaturaCicloFaturamento(dto, token);
    }

}

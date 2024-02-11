package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.cronogramaFatura.*;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.service.CronogramaFaturamentoService;
import moduloFaturamento.service.common.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/cronogramaFaturamento")
@CrossOrigin
public class CronogramaFaturamentoController {

    @Autowired
    private LocalidadeService localidadeService;
    @Autowired
    private CronogramaFaturamentoService cronogramaFaturamentoService;

    @GetMapping("/listarFases")
    public List<CronogramaFaturaFaseDTO> listarFases() {
        return this.cronogramaFaturamentoService.buscarTodasFasesCronograma();
    }

    @GetMapping("/listarLocalidade")
    public List<LocalidadeDTO> listarLocalidade() {
        return this.localidadeService.buscarTodasLocalidades();
    }

    @GetMapping
    public GenericoWrapperDTO<List<CronogramaFaturaRespostaGridProjectionDTO>> listarGrid(@Valid CronogramaFaturaFilterDTO filter, Pageable pageable) {
        return this.cronogramaFaturamentoService.buscarCronogramaPorFiltro(filter, pageable);
    }

    @GetMapping("/{id}")
    public CronogramaFaturaRespostaConsultaDTO buscarCronogramaPorid(@PathVariable(value = "id") Long id) {
        return this.cronogramaFaturamentoService.buscarCronogramaPorId(id);
    }

    @GetMapping("/buscarSugestaoDatas")
    public CronogramaFaturaRespostaCalculoDatasDTO buscarSugestaoDatasPorDataLeitura(
            @RequestParam(value = "dataLeitura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLeituraPrevista) {
        return this.cronogramaFaturamentoService.retornarDatasCalculadasOperacaoAdicionaSubtraiBaseadoDataLeituraCronograma(dataLeituraPrevista);
    }

    @GetMapping("/buscarSugestaoDataVencimento")
    public CronogramaFaturaRespostaCalculoDataVencimentoDTO buscarSugestaoDataVencimentoPorCidadeCicloEReferencia(@RequestParam(value = "cdCidade", required = false) Short cdCidade, @
                                    RequestParam("ciclo") Short ciclo, @RequestParam("dataReferencia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataReferencia) {
        return this.cronogramaFaturamentoService.retonarDataVencimentoCalculadaCronograma(cdCidade, ciclo, dataReferencia);
    }

    @GetMapping("/buscarSugestaoDataAprovacaoTarifa")
    public CronogramaFaturaRespostaCalculoDataTarifaDTO buscarSugestaoDataTaficaPorCidadeCicloEReferencia(@Valid CronogramaFaturaDataTarifaDTO dto) {
        return this.cronogramaFaturamentoService.retornarDataTarifaCalculadaCronograma(dto);
    }

    @GetMapping("/verificarSePopupConfirmacaoCadastroUnicoDeveSerAberto")
    public CronogramaFaturaRespostaPopupCadastroUnicoDTO verificarSePopupConfirmacaoCadastroUnicoDeveSerAberto(@RequestParam("cdCidade") Short cdCidade,  @RequestParam("ciclo") Short ciclo,
                                                                                                               @RequestParam("dataReferencia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataReferencia) {
        return this.cronogramaFaturamentoService.retornarCondicaoAbrirPopupConfirmarCadastroUnico(cdCidade, ciclo, dataReferencia);
    }

    @PostMapping("/unico")
    @ResponseStatus(HttpStatus.CREATED)
    public CronogramaFaturaSalvarRespostaDTO cadastrarUnicoCronograma(@RequestBody @Valid CronogramaFaturaCadastroUnicoDTO dto, @RequestHeader("Authorization") String token) {
        return this.cronogramaFaturamentoService.executarFluxoCadastrar(dto, token);
    }

    @PostMapping("/multiplo")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CronogramaFaturaSalvarRespostaDTO> cadastrarMultiploCronograma(@RequestBody @Valid CronogramaFaturaCadastroMultiploDTO dto, @RequestHeader("Authorization") String token) {
        return this.cronogramaFaturamentoService.executarFluxoCadastrar(dto, token);
    }

    @PutMapping
    public CronogramaFaturaSalvarRespostaDTO atualizarCronograma(@RequestBody @Valid CronogramaFaturaAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.cronogramaFaturamentoService.executarFluxoEditar(dto, token);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable(value = "id") Long id, @RequestHeader("Authorization") String token) {
        this.cronogramaFaturamentoService.executarFluxoDeletar(id, token);
    }

    @GetMapping("/datas/{matriculaImovel}/referencia/{ref}")
    public List<CronogramaFatura> buscarDataPorMatricula(@PathVariable(value = "matriculaImovel") Integer matriculaImovel, @PathVariable(value = "ref") Integer ref) {
        return this.cronogramaFaturamentoService.buscarDataFaturaPorMatricula(matriculaImovel, ref);
    }

}

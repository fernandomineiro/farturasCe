package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.imovel.projection.ImovelMatriculaComDvProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.*;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelCabecalhoDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelMicroRespostaGridProjectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.LeituraConsumoImovelRespostaGridProjectionDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaDropDownDTO;
import moduloFaturamento.dto.tipoConsumoFaturado.TipoConsumoFaturadoDTO;
import moduloFaturamento.service.LeituraConsumoImovelService;
import moduloFaturamento.service.LeituraService;
import moduloFaturamento.service.TipoConsumoFaturadoService;
import moduloFaturamento.service.common.OcorrenciaLeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/leituraConsumoImovel")
@CrossOrigin
public class LeituraConsumoImovelController {

    @Autowired
    private LeituraConsumoImovelService leituraConsumoImovelService;
    @Autowired
    private TipoConsumoFaturadoService tipoConsumoFaturadoService;
    @Autowired
    private OcorrenciaLeituraService ocorrenciaLeituraService;
    @Autowired
    private LeituraService leituraService;

    @GetMapping("/listarOcorrenciaLeitura")
    public List<OcorrenciaLeituraRespostaDropDownDTO> listarOcorrenciaLeitura(@RequestParam(value = "tipoGravidade", required = false) String tipoGravidade) {
        return this.ocorrenciaLeituraService.buscarOcorrenciasPorTipoGravidade(tipoGravidade);
    }

    @GetMapping("/listarMotivoEdicaoLeitura")
    public List<LeituraConsumoImovelTipoMotivoEdicaoDTO> listarMotivoEdicaoLeitura() {
        return this.leituraConsumoImovelService.buscarListaMotivoEdicaoLeitura();
    }

    @GetMapping("/listarMatriculasMicroDeUmaMatriculaMacro/matricula/{matricula}")
    public List<ImovelMatriculaComDvProjectionDTO> listarMatriculasMicroDeUmaMatriculaMacro(@PathVariable("matricula") Integer matricula) {
        return this.leituraConsumoImovelService.buscarListaMatriculasMicroDeUmaMatriculaMacro(matricula);
    }

    @GetMapping("/listarTipoConsumoFaturado")
    public List<TipoConsumoFaturadoDTO> listarTipoConsumoFaturado(@RequestParam(value = "refFatura", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura) {
        return this.tipoConsumoFaturadoService.buscarListaTipoConsumoFaturadoParaLeituraConsumoImovel(refFatura);
    }


    @GetMapping("/buscarCabecalhoDadosImovel/matricula/{matricula}/dv/{dv}")
    public LeituraConsumoImovelCabecalhoDTO buscarCabecalhoDadosDoImovel(@PathVariable("matricula") Integer matricula, @PathVariable("dv") Short dv) {
        return this.leituraConsumoImovelService.buscarCabecalhoDadosDoImovel(matricula, dv);
    }

    @GetMapping("/{id}")
    public LeituraConsumoImovelRespostaConsultaDTO buscarLeituraPorId(@PathVariable("id") Long id) {
        return this.leituraConsumoImovelService.buscarLeituraConsumoImovelPorId(id);
    }

    @GetMapping("/buscarLeiturasSomenteComMatriculaMicro/matriculaMacro/{matricula}/referencia/{refFatura}")
    public GenericoWrapperDTO<List<LeituraConsumoImovelMicroRespostaGridProjectionDTO>> listarGridLeiturasMatriculaMicroDeUmaMatriculaMacro(@PathVariable("matricula") Integer matricula,
                                                                                                                                            @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura,
                                                                                                                                            Pageable pageable) {
        return this.leituraConsumoImovelService.buscarLeiturasSomenteComMatriculaMicroPorFiltro(matricula, refFatura, pageable);
    }

    @GetMapping
    public GenericoWrapperDTO<List<LeituraConsumoImovelRespostaGridProjectionDTO>> listarGridPrincipal(@RequestParam("matricula") Integer matricula,
                                                                                                           @RequestParam(value = "refFatura", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura,
                                                                                                           Pageable pageable) {
        return this.leituraConsumoImovelService.buscarLeiturasPorFiltro(matricula, refFatura, pageable);
    }


    @GetMapping("/buscarValorMaximoEMinimoFaixaConsumoParaAnalise/valorMediaDiariaLeitura/{valor}")
    public LeituraConsumoImovelFaixaConsumoAnaliseDTO buscarValorMaximoEMinimoFaixaConsumoParaAnalise(@PathVariable("valor") BigDecimal mediaDiariaDaLeitura) {
        return this.leituraConsumoImovelService.buscarValorMaximoEMinimoFaixaConsumoParaAnalise(mediaDiariaDaLeitura);
    }

    @GetMapping("/buscarValorConsumoMedido")
    public LeituraConsumoImovelRespostaConsumoMedidoDTO buscarValorConsumoMedido(@Valid LeituraConsumoImovelConsumoMedidoDTO dto) {
        return this.leituraConsumoImovelService.buscarValorConsumoMedido(dto);
    }

    @GetMapping("/buscarValorConsumoMedidoMacro/matricula/{matricula}/referencia/{refFatura}")
    public Integer buscarValorConsumoMedidoMacro(@PathVariable("matricula") Integer matricula, @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura) {
        return this.leituraConsumoImovelService.buscarConsumoMedidoMatriculaMacro(matricula, refFatura);
    }

    @GetMapping("/buscarValorConsumoFaturadoEsgoto/matricula/{matricula}/referencia/{refFatura}/consumoFaturadoEsgoto/{consumoFaturadoEsgoto}")
    public BigDecimal buscarValorConsumoFaturadoEsgoto(@PathVariable("matricula") Integer matricula, @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura,
                                                       @PathVariable("consumoFaturadoEsgoto") BigDecimal consumoFaturadoEsgoto) {
        return this.leituraConsumoImovelService.buscarConsumoFaturadoEsgotoParaMatriculaPrincipal(matricula, refFatura, consumoFaturadoEsgoto);
    }

    @GetMapping("/buscarTipoConsumoFaturado/matricula/{matricula}/referencia/{refFatura}/tipoConsumoFaturado/{idTipoConsumoFaturado}")
    public Short buscarTipoConsumoFaturado(@PathVariable("matricula") Integer matricula, @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura,
                                                        @PathVariable("idTipoConsumoFaturado") Short idTipoConsumoFaturado,
                                                        @Valid LeituraConsumoImovelTipoConsumoFaturadoFilterDTO dto) {
        return this.leituraConsumoImovelService.buscarNovoTipoConsumoFaturado(matricula, refFatura, idTipoConsumoFaturado, dto);
    }

    @GetMapping("/buscarConsumoFaturado/matricula/{matricula}/referencia/{refFatura}/tipoConsumoFaturado/{idTipoConsumoFaturado}")
    public BigDecimal buscarConsumoFaturado(@PathVariable("matricula") Integer matricula, @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura,
                                                        @PathVariable("idTipoConsumoFaturado") Short idTipoConsumoFaturado,
                                                        @Valid LeituraConsumoImovelConsumoFaturadoFilterDTO dto) {
        return this.leituraConsumoImovelService.buscarValorConsumoFaturado(matricula, refFatura, idTipoConsumoFaturado, dto);
    }

    @GetMapping("/buscarDiasDeVenda/matricula/{matricula}/dataLeitura/{dataLeitura}/referencia/{refFatura}")
    public Short buscarDiasDeVenda(@PathVariable("matricula") Integer matricula, @PathVariable("dataLeitura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLeitura,
                                                @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura) {
        return this.leituraConsumoImovelService.buscarValorDiasDeVenda(matricula, dataLeitura, refFatura);
    }

    @GetMapping("/buscarDiasDeConsumo/matricula/{matricula}/dataLeitura/{dataLeitura}/referencia/{refFatura}")
    public Short buscarDiasDeConsumo(@PathVariable("matricula") Integer matricula, @PathVariable("dataLeitura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataLeitura,
                                                @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura) {
        return this.leituraConsumoImovelService.buscarValorDiasDeConsumo(matricula, dataLeitura, refFatura);
    }

    @GetMapping("/buscarValorMediaDiaria/matricula/{matricula}/referencia/{refFatura}")
    public BigDecimal buscarValorMediaDiaria(@PathVariable("matricula") Integer matricula, @PathVariable("refFatura") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate refFatura) {
        return this.leituraConsumoImovelService.buscarMediaDiaria(matricula, refFatura);
    }

    @PutMapping
    public LeituraConsumoImovelGravadoRespostaIdentidadeDTO editar(@RequestBody @Valid LeituraConsumoImovelAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.leituraConsumoImovelService.executarFluxoEditarLeitura(dto, token);
    }
}

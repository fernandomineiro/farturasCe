package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cobrancaFatura.*;
import moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO;
import moduloFaturamento.service.CobrancaFaturaService;
import moduloFaturamento.service.common.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/cobrancaFatura")
@CrossOrigin
public class CobrancaFaturaController {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private CobrancaFaturaService cobrancaFaturaService;

    @GetMapping("/listarServicosEmCadastro")
    public List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownCadastro(@RequestParam("matriculaImovel") Integer matricula, @RequestParam("dv") Short dv) {
        return this.servicoService.buscarServicosParaDropDownCadastroCobrancaEmFatura(matricula, dv);
    }

    @GetMapping("/listarServicosEmEdicao")
    public List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownEdicao(@RequestParam("matriculaImovel") Integer matricula, @RequestParam("dv") Short dv) {
        return this.servicoService.buscarServicosParaDropDownEdicaoCobrancaEmFatura(matricula, dv);
    }

    @GetMapping("/buscarValoresCadastradosNoServicosNaoTarifados")
    public List<BigDecimal> buscarValoresCadastradosNoServicosNaoTarifados(@RequestParam("cdServico") Short cdServico, @RequestParam("matriculaImovel") Integer matricula, @RequestParam("dv") Short dv) {
        return this.cobrancaFaturaService.buscarValoresCadastradosNoServicosNaoTarifados(cdServico, matricula, dv);
    }

    @GetMapping
    public GenericoWrapperDTO<List<CobrancaFaturaRespostaGridProjectionDTO>> buscarListaCobrancaFatura(@Valid CobrancaFaturaFilterDTO filter, Pageable pageable) {
        return this.cobrancaFaturaService.buscarCobrancaPorFiltro(filter, pageable);
    }

    @GetMapping("/{id}")
    public CobrancaFaturaRespostaConsultaDTO buscarCobrancaPorId(@PathVariable("id") Long id) {
        return this.cobrancaFaturaService.buscarCobrancaPorId(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public CobrancaFaturaRespostaSalvarDTO cadastrar(@RequestBody @Valid CobrancaFaturaCadastrarDTO dto, @RequestHeader("Authorization") String token) {
        return this.cobrancaFaturaService.executarFluxoCadastrarCobranca(dto, token);
    }

    @PutMapping
    public CobrancaFaturaRespostaSalvarDTO atualizar(@RequestBody @Valid CobrancaFaturaAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.cobrancaFaturaService.executarFluxoEditarCobranca(dto, token);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable(value = "id") Long id, @RequestHeader("Authorization") String token) {
        this.cobrancaFaturaService.executarFluxoRemoverCobranca(id, token);
    }

}

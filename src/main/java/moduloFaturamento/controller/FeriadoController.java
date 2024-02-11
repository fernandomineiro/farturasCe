package moduloFaturamento.controller;

import moduloFaturamento.dto.*;
import moduloFaturamento.dto.feriado.*;
import moduloFaturamento.service.FeriadoService;
import moduloFaturamento.service.TipoFeriadoService;
import moduloFaturamento.service.UnidadeFederativaService;
import moduloFaturamento.service.common.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/feriado")
public class FeriadoController {

    @Autowired
    private UnidadeFederativaService unidadeFederativaService;
    @Autowired
    private LocalidadeService municipioService;
    @Autowired
    private TipoFeriadoService tipoFeriadoService;
    @Autowired
    private FeriadoService feriadoService;

    @CrossOrigin
    @GetMapping("/listarUnidadeFederativa")
    public List<UnidadeFederativaDTO> listaUnidadeFederativa(){
        return this.unidadeFederativaService.listarUnidadeFederativa();
    }

    @CrossOrigin
    @GetMapping("/listarMunicipio")
    public List<LocalidadeDTO> listarMunicipio() {
        return this.municipioService.buscarTodasLocalidades();
    }

    @CrossOrigin
    @GetMapping("/listarTipoFeriado")
    public List<TipoFeriadoDTO> listarTipoFeriado() {
        return this.tipoFeriadoService.buscarTodosTipoFeriado();
    }

    @CrossOrigin
    @GetMapping("/buscarFeriado/{idFeriado}")
    public FeriadoRespostaConsultaDTO buscarFeriadoPorId(@PathVariable(value = "idFeriado") Integer id) {
        return this.feriadoService.buscarFeriadoPorId(id);
    }

    @CrossOrigin
    @GetMapping("/listarFeriado")
    public GenericoWrapperDTO<List<FeriadoRespostaGridDTO>> buscarListaFeriado(@Valid FeriadoFilterDTO filter, Pageable pageable) {
        return this.feriadoService.buscarFeriadosPorFiltro(filter, pageable);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FeriadoRespostaCadastrarDTO cadastrar(@RequestBody @Valid FeriadoCadastrarDTO dto, @RequestHeader("Authorization") String token) {
        return this.feriadoService.executarFluxoSalvar(dto, token);
    }

    @CrossOrigin
    @PutMapping
    public FeriadoRespostaAtualizarDTO atualizar(@RequestBody @Valid FeriadoAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.feriadoService.executarFluxoAtualizar(dto,token);
    }

    @CrossOrigin
    @DeleteMapping("/{idFeriado}")
    public void remover(@PathVariable(value = "idFeriado") Integer id, @RequestHeader("Authorization") String token) {
        this.feriadoService.executarFluxoDeletar(id, token);
    }
}
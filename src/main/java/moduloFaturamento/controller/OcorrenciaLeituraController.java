package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.*;
import moduloFaturamento.service.common.OcorrenciaLeituraService;
import moduloFaturamento.service.common.TipoServicoOcorrenciaLeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/ocorrenciaLeitura")
public class OcorrenciaLeituraController {

    @Autowired
    private TipoServicoOcorrenciaLeituraService tipoServicoOcorrenciaLeituraService;
    @Autowired
    private OcorrenciaLeituraService ocorrenciaLeituraService;

    @CrossOrigin
    @GetMapping("/{cdOcorrencia}")
    public OcorrenciaLeituraRespostaConsultaDTO buscarOcorrenciaPorCodigoOcorrencia(@PathVariable(value = "cdOcorrencia") Short cdOcorrencia) {
        return this.ocorrenciaLeituraService.buscarOcorrenciaPorCodigoOcorrencia(cdOcorrencia);
    }

    @CrossOrigin
    @DeleteMapping("/{cdOcorrencia}")
    public void remover(@PathVariable(value = "cdOcorrencia") Short cdOcorrencia, @RequestHeader("Authorization") String token) {
        this.ocorrenciaLeituraService.executarFluxoDeletar(cdOcorrencia, token);
    }

    @CrossOrigin
    @GetMapping("/listarTipoServicoOcorrenciaLeitura")
    public List<TipoServicoOcorrenciaLeituraDTO> listarTipoServicoOcorrenciaLeitura() {
        return this.tipoServicoOcorrenciaLeituraService.buscarTodosTipoOcorrenciaLeitura();
    }

    @CrossOrigin
    @GetMapping
    public GenericoWrapperDTO<List<OcorrenciaLeituraRespostaGridDTO>> listarGrid(@Valid OcorrenciaLeituraFilterDTO filter, Pageable pageable) {
        return this.ocorrenciaLeituraService.buscarOcorrenciaLeituraPorFiltro(filter, pageable);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OcorrenciaLeituraGravadoRespostaIdentidadeDTO cadastrar(@RequestBody @Valid OcorrenciaLeituraCadastrarDTO dto, @RequestHeader("Authorization") String token) {
        return this.ocorrenciaLeituraService.executarFluxoCadastrar(dto, token);
    }

    @CrossOrigin
    @PutMapping
    public OcorrenciaLeituraGravadoRespostaIdentidadeDTO atualizar(@RequestBody @Valid OcorrenciaLeituraAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.ocorrenciaLeituraService.executarFluxoAtualizar(dto, token);
    }

}

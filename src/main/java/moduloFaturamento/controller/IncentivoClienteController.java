package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaAtualizarDTO;
import moduloFaturamento.dto.incentivoCliente.IncentivoClienteRespostaEstruturaCadastroDTO;
import moduloFaturamento.dto.incentivoCliente.anexo.ParametroIncentivoClienteAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteGrupoConsumo.projection.IncentivoClienteGrupoConsumoRespostaProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.IncentivoClienteParametroTipoDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.IncentivoClienteParametroDetalheRespostaConsultaDTO;
import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;
import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;
import moduloFaturamento.service.*;
import moduloFaturamento.service.common.SituacaoLigacaoAguaService;
import moduloFaturamento.service.common.SituacaoLigacaoEsgotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backend-faturamento/incentivoCliente")
@CrossOrigin
public class IncentivoClienteController {

    @Autowired
    private IncentivoClienteParametroService incentivoClienteParametroService;
    @Autowired
    private IncentivoClienteParametroDetalheService incentivoClienteParametroDetalheService;
    @Autowired
    private IncentivoClienteParametroDetalheGrupoConsumoService incentivoClienteParametroDetalheGrupoConsumoService;
    @Autowired
    private SituacaoLigacaoAguaService situacaoLigacaoAguaService;
    @Autowired
    private SituacaoLigacaoEsgotoService situacaoLigacaoEsgotoService;
    @Autowired
    private IncentivoClienteParametroAnexoService incentivoClienteParametroAnexoService;
    @Autowired
    private TipoIncentivoClienteService tipoIncentivoClienteService;

    @GetMapping("/tipo")
    public List<IncentivoClienteParametroTipoDTO> listarTipoIncentivo() {
        return this.tipoIncentivoClienteService.listarTodosTipoIncentivoCliente();
    }

    @GetMapping("/situacaoLigacaoAgua")
    public List<SituacaoLigacaoAguaDTO> listarSituacaoAguaLigacao() {
        return this.situacaoLigacaoAguaService.buscarTodasAsLigacoes();
    }

    @GetMapping("/situacaoLigacaoEsgoto")
    public List<SituacaoLigacaoEsgotoDTO> listarSituacaoEsgotoLigacao() {
        return this.situacaoLigacaoEsgotoService.buscarTodasAsLigacoes();
    }

    @GetMapping
    public GenericoWrapperDTO<List<IncentivoClienteParametroRespostaGridProjectionDTO>> listarGridParametro(Pageable pageable) {
        return this.incentivoClienteParametroService.listarGrid(pageable);
    }

    @GetMapping("/{idParametroIncentivo}")
    public IncentivoClienteParametroRespostaConsultaDTO buscarParametroPorId(@PathVariable("idParametroIncentivo") Integer id) {
        return this.incentivoClienteParametroService.buscarParametroIncentivoPorId(id);
    }

    @GetMapping("/detalhe/lista/{idParametroIncentivo}")
    public GenericoWrapperDTO<List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO>> listarGridDetalhe(@PathVariable("idParametroIncentivo") Integer id, Pageable pageable) {
        return this.incentivoClienteParametroDetalheService.listarGrid(id, pageable);
    }

    @GetMapping("/detalhe/{idDetalheParametroIncentivo}")
    public IncentivoClienteParametroDetalheRespostaConsultaDTO buscarDetalhePorId(@PathVariable("idDetalheParametroIncentivo") Integer id) {
        return this.incentivoClienteParametroDetalheService.buscarDetalheParametroIncentivoPorId(id);
    }

    @GetMapping("/detalhe/grupoConsumo")
    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarTodosGrupoConsumo(){
        return this.incentivoClienteParametroDetalheGrupoConsumoService.listarTodosGrupoConsumo();
    }

    @GetMapping("/detalhe/grupoConsumo/cadastradoNoIncentivo/{idDetalheParametroIncentivo}")
    public List<IncentivoClienteGrupoConsumoRespostaProjectionDTO> listarGrupoConsumoCadastroNoDetalheIncentivo(@PathVariable("idDetalheParametroIncentivo") Integer id){
        return this.incentivoClienteParametroDetalheGrupoConsumoService.listarGrupoConsumoAssociadoAoIncentivo(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public IncentivoClienteRespostaEstruturaCadastroDTO cadastrar(@RequestBody @Valid IncentivoClienteCadastroDTO dto, @RequestHeader("Authorization") String token) {
        return this.incentivoClienteParametroService.executarFluxoCadastrarIncentivoDetalhesEGrupoConsumoAnexo(dto, token);
    }

    @PutMapping
    public IncentivoClienteRespostaEstruturaAtualizarDTO atualizar(@RequestBody @Valid IncentivoClienteAtualizarDTO dto, @RequestHeader("Authorization") String token) {
        return this.incentivoClienteParametroService.executarFluxoEditarIncentivoDetalhesEGrupoConsumoAnexo(dto, token);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        this.incentivoClienteParametroService.executarFluxoRemoverIncentivo(id, token);
    }

    @GetMapping("/anexo/{idIncentivoCliente}")
    public ParametroIncentivoClienteAnexoRespostaWrapperDTO listarAnexoPorIdIncentivo(@PathVariable("idIncentivoCliente") Integer idIncentivo, Pageable pageable) {
        return this.incentivoClienteParametroAnexoService.buscarAnexoIncentivoCliente(idIncentivo, pageable);
    }

    @GetMapping("/anexo/download/{id}")
    public GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(@PathVariable(value = "id") Long id) {
        return this.incentivoClienteParametroAnexoService.retornarArquivoParaDownload(id);
    }


}

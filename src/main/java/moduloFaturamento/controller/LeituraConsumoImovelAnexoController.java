package moduloFaturamento.controller;

import moduloFaturamento.dto.GenericoDownloadArquivoAnexoDTO;
import moduloFaturamento.dto.leituraAnexo.LeituraAnexoRespostaWrapperDTO;
import moduloFaturamento.dto.leituraConsumoImovel.LeituraConsumoImovelSalvarAnexoDTO;
import moduloFaturamento.service.LeituraAnexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/backend-faturamento/anexoLeituraConsumoImovel")
@CrossOrigin
public class LeituraConsumoImovelAnexoController {

    @Autowired
    private LeituraAnexoService leituraAnexoService;

    @GetMapping("/{idLeitura}")
    public LeituraAnexoRespostaWrapperDTO listarAnexosPorIdLeitura(@PathVariable("idLeitura") Long idLeitura, Pageable pageable) {
        return this.leituraAnexoService.buscarLeiturasPorIdLeitura(idLeitura, pageable);
    }

    @PostMapping
    public void salvarAnexo (@RequestBody @Valid LeituraConsumoImovelSalvarAnexoDTO dto, @RequestHeader("Authorization") String token) {
        this.leituraAnexoService.executarFluxoSalvarAnexoConsumoImovel(dto, token);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable(value = "id") Long id, @RequestHeader("Authorization") String token) {
        this.leituraAnexoService.executarFluxoRemoverAnexo(id, token);
    }

    @GetMapping("/retornarArquivoParaDownload/{id}")
    public GenericoDownloadArquivoAnexoDTO retornarArquivoParaDownload(@PathVariable(value = "id") Long id) {
        return this.leituraAnexoService.retornarArquivoParaDownload(id);
    }


}

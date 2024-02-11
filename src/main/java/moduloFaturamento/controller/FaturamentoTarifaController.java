package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.tarifa.ParamametrosPesquisaTarifaFilterDTO;
import moduloFaturamento.dto.tarifa.PesquisaTarifaRespostaDTO;
import moduloFaturamento.dto.tarifa.TarifaDeletarDTO;
import moduloFaturamento.service.TarifaService;

@RestController
@RequestMapping("/backend-faturamento/faturamentoTarifas")
@CrossOrigin
public class FaturamentoTarifaController {

    @Autowired
    private TarifaService service;

    @GetMapping
    public List<TarifaLocalidadeRespostaDTO> buscarNomeDasRegioes(){
        return service.buscarListaDasRegioes();
    }

    @GetMapping("/tarifa")
    public GenericoWrapperDTO<List<PesquisaTarifaRespostaDTO>> pesquisarFaturas(ParamametrosPesquisaTarifaFilterDTO tarifaFilterDTO, Pageable pageable){
        return service.pesquisarTarifas(tarifaFilterDTO, pageable);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/uploadplanilhaNova")
	public String cadastrarNovasTarifas(@RequestParam("planilhaNova") MultipartFile file, @RequestHeader("Authorization") String token) {
		return service.cadastrarNovasTarifas(file, token);
	}
    
	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/downloadModeloXLS")
	public ResponseEntity<Resource> downloadModeloXLS(@RequestHeader("Authorization") String token) {
		Resource resource = service.downloadModeloXLS(token);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/excel")).header(HttpHeaders.CONTENT_DISPOSITION, "modelo-amostras-minimas-exigidas.xlsx").body(resource);
	}

    @DeleteMapping
    public ResponseEntity<Void> deletarTarifaPorData(@Valid @RequestBody TarifaDeletarDTO tarifaDeletarDTO, @RequestHeader("Authorization") String token){
        service.deletarTarifaPorData(tarifaDeletarDTO, token);
        return ResponseEntity.noContent().build();
    }
    
    
}

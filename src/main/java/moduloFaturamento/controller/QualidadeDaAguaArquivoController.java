package moduloFaturamento.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasArquivosProjectionRespostaDTO;
import moduloFaturamento.dto.amostrasRealizadas.projection.PesquisaAmostrasRealizadasProjectionRespostaDTO;
import moduloFaturamento.service.QualidadeDaAguaArquivosAmostrasService;

@RestController
@RequestMapping("/backend-faturamento/qualidadeDaAguaArquivo")
public class QualidadeDaAguaArquivoController {

	@Autowired
	private QualidadeDaAguaArquivosAmostrasService qualidadeDaAguaArquivosAmostrasService;

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/pesquisaAmostrasArquivos")
	public GenericoWrapperDTO<List<PesquisaAmostrasArquivosProjectionRespostaDTO>> pesquisaAmostrasArquivos(Pageable pageable,
			@RequestHeader("Authorization") String token) {

		return qualidadeDaAguaArquivosAmostrasService.pesquisaAmostrasArquivos(pageable, token);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/pesquisaAmostrasRealizadas")
	public GenericoWrapperDTO<List<PesquisaAmostrasRealizadasProjectionRespostaDTO>> pesquisaAmostrasRealizadas(Integer refAmostras, Pageable pageable,
			@RequestHeader("Authorization") String token) {

		return qualidadeDaAguaArquivosAmostrasService.pesquisaAmostrasRealizadas(refAmostras, pageable, token);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/downloadModeloAmostrasExigidas")
	public ResponseEntity<Resource> downloadModeloExigidasXlsx(@RequestHeader("Authorization") String token) {

		Resource resource = qualidadeDaAguaArquivosAmostrasService.arquivoModeloAmostrasMinimasExigidas(token);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "modelo-amostras-minimas-exigidas.xlsx").body(resource);

	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/downloadModeloAmostrasRealizadas")
	public ResponseEntity<Resource> downloadModeloRealizadasXlsx(@RequestHeader("Authorization") String token) {

		Resource resource = qualidadeDaAguaArquivosAmostrasService.arquivoModeloAmostrasMinimasRealizadas(token);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "modelo-amostras-minimas-realizadas.xlsx").body(resource);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/downloadAmostrasExigidas")
	public ResponseEntity<Resource> downloadAmostrasExigidas(Integer dtInicio, @RequestHeader("Authorization") String token) {

		Resource resource = qualidadeDaAguaArquivosAmostrasService.downloadAmostrasExigidas(dtInicio, token);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "amostras-minimas-exigidas-" + dtInicio + ".xlsx").body(resource);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/downloadAmostrasRealizadas")
	public ResponseEntity<Resource> downloadAmostrasRealizadas(Integer refAmostras, @RequestHeader("Authorization") String token) {

		Resource resource = qualidadeDaAguaArquivosAmostrasService.downloadAmostrasRealizadas(refAmostras, token);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "amostras-minimas-realizadas-" + refAmostras + ".xlsx").body(resource);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@CrossOrigin
	@PostMapping("/uploadAmostrasExigidas")
	public void uploadAmostrasExigidas(@RequestParam("amostrasExigidas") MultipartFile file, @RequestHeader("Authorization") String token) {

		qualidadeDaAguaArquivosAmostrasService.uploadAmostrasExigidas(file, token);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@CrossOrigin
	@PostMapping("/uploadAmostrasRealizadas")
	public void uploadAmostrasRealizadas(@RequestParam("amostrasRealizadas") MultipartFile file, @RequestHeader("Authorization") String token) {

		qualidadeDaAguaArquivosAmostrasService.uploadAmostrasRealizadas(file, token);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@CrossOrigin
	@DeleteMapping("/removerAmostrasExigidas")
	public void removerAmostrasExigidas(Integer dtInicio, @RequestHeader("Authorization") String token) {

		qualidadeDaAguaArquivosAmostrasService.removerAmostrasExigidas(dtInicio, token);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@CrossOrigin
	@DeleteMapping("/removerAmostrasRealizadas")
	public void removerAmostrasRealizadas(Integer refAmostras, MultipartFile file, @RequestHeader("Authorization") String token) {

		qualidadeDaAguaArquivosAmostrasService.removerAmostrasRealizadas(refAmostras, token);
	}

}
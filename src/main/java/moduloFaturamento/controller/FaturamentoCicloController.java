package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoDeletarDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoListaDTO;
import moduloFaturamento.dto.diasCiclos.FaturamentoDiasVencimentoNovoDTO;
import moduloFaturamento.service.FaturamentoCicloService;

@RestController
@RequestMapping("/backend-faturamento/faturamentoCiclo")
@CrossOrigin
public class FaturamentoCicloController {

    @Autowired
	private FaturamentoCicloService faturamentoCicloService;

	@GetMapping
	public List<Integer> buscarCiclos() {
		return faturamentoCicloService.buscarListaCiclos();
	}

    @GetMapping("/selecionarCiclos")
	public List<FaturamentoDiasVencimentoListaDTO> buscarDiasDosCiclos(@RequestParam List<Short> ciclos) {
		return faturamentoCicloService.buscarDiasDosCiclos(ciclos);
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public FaturamentoDiasVencimentoListaDTO criarDiaNoCiclo(@Valid @RequestBody FaturamentoDiasVencimentoNovoDTO ciclo, @RequestHeader("Authorization") String token) {
		return faturamentoCicloService.novoDiaPorCiclo(ciclo, token);
	}

	@DeleteMapping
	public ResponseEntity<Void> deletarDiaNoCiclo(@Valid @RequestBody FaturamentoDiasVencimentoDeletarDTO ciclo, @RequestHeader("Authorization") String token) {
		faturamentoCicloService.deletarDiaDoCiclo(ciclo, token);
		return ResponseEntity.noContent().build();
	}
    
}

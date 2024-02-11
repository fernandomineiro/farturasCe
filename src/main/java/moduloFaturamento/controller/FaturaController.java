package moduloFaturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.MensagemTipoGenericoDTO;
import moduloFaturamento.service.FaturaService;

@RestController
@RequestMapping("/backend-faturamento/faturamentoImovel")
public class FaturaController {

	@Autowired
	private FaturaService faturaService;
	
	@CrossOrigin
	@GetMapping("/imovel/{matricula}")
	public MensagemTipoGenericoDTO<Boolean> validarExclusaoImovel(@PathVariable(value = "matricula") Integer matricula) {
		return faturaService.validarExclusaoImovel(matricula);
	}

}

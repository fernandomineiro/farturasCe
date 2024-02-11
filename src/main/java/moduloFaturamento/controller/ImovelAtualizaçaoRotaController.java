package moduloFaturamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.service.ImovelAtualizacaoRotaService;

@RestController
@RequestMapping("/backend-faturamento/atualizacaoRotaController")
@CrossOrigin
public class ImovelAtualizaçaoRotaController {

	@Autowired
	private ImovelAtualizacaoRotaService imovelAtualizacaoRotaService;
	
	@GetMapping("/atualizarRota")
	public void executarAtualizacaoRota(Integer cdCidade, Integer ciclo) {
		
		imovelAtualizacaoRotaService.executarAtualizacaoRota(cdCidade, ciclo);
	}
}
//   http://localhost:8088/backend-faturamento/atualizacaoRotaController/atualizarRota?cdCidade=4000&ciclo=1
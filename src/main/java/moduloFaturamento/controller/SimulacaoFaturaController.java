package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaFilterDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaFilterDTO;
import moduloFaturamento.service.SimulacaoFaturaService;
import moduloFaturamento.service.common.GrupoDeConsumoService;
import moduloFaturamento.service.common.LocalidadeService;

@RestController
@RequestMapping("/backend-faturamento/simulacaoFatura")
public class SimulacaoFaturaController {

	@Autowired
	private LocalidadeService localidadeService;
	
	@Autowired
	private GrupoDeConsumoService grupoDeConsumoService;

	@Autowired
	private SimulacaoFaturaService simulacaoFaturaService;

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/buscarDropDownLocalidade")
	public List<LocalidadeDTO> buscarDropDownLocalidade(@RequestHeader("Authorization") String token) {

		return localidadeService.buscarLocalidadesQueTemFaturamento();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/buscarDropDownGrupoDeConsumo")
	public List<GrupoConsumoDropDownProjectionDTO> buscarDropDownGrupoDeConsumo(@RequestHeader("Authorization") String token) {

		return grupoDeConsumoService.buscarListaDropDownGrupoDeConsumo();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/buscarDropDownOcorrenciaLeitura")
	public List<OcorrenciaLeituraDropDownProjectionDTO> buscarDropDownOcorrenciaLeitura(@RequestHeader("Authorization") String token) {

		return simulacaoFaturaService.buscarDropDownOcorrenciaLeitura();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/buscarFaturaPorMatriculaReferencia")
	public FaturaDetalhadaRespostaDTO buscarFaturaPorMatriculaReferencia(SimulacaoFaturaMatriculaReferenciaFilterDTO dto,
			@RequestHeader("Authorization") String token) {

		return simulacaoFaturaService.buscarFaturaPorMatriculaReferencia(dto, token);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@CrossOrigin
	@GetMapping("/buscarFaturaSimulada")
	public FaturaDetalhadaRespostaDTO buscarFaturaSimulada(@Valid SimulacaoFaturaFilterDTO dto, @RequestHeader("Authorization") String token) {
		
		return simulacaoFaturaService.buscarFaturaSimulada(dto, token);
	}

}

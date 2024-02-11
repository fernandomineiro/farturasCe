package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaFaseDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoAlterarFilterDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoAlterarResumoRespostaDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoVerificarFilterDTO;
import moduloFaturamento.dto.vencimentoFaturasPorCicloContingencia.FaturasVencimentoVerificarResumoRespostaDTO;
import moduloFaturamento.service.VencimentoFaturasPorCicloContingenciaService;
import moduloFaturamento.service.common.LocalidadeService;

@RestController
@RequestMapping("/backend-faturamento/vencimentoFaturasPorCicloContingencia")
public class VencimentoFaturasPorCicloContingenciaController {

	@Autowired
	private LocalidadeService localidadeService;

	@Autowired
	private VencimentoFaturasPorCicloContingenciaService vencimentoFaturasPorCicloContingenciaService;

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/listarLocalidades")
	public List<LocalidadeDTO> listarLocalidades(@RequestHeader("Authorization") String token) {

		return localidadeService.buscarTodasLocalidades();
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarDropDownCiclo")
	public List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(@NotNull Short cdCidade, @NotNull Integer referencia,
			@RequestHeader("Authorization") String token) {

		return vencimentoFaturasPorCicloContingenciaService.buscarDropDownCiclo(cdCidade, referencia);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarDropDownFasesCronograma")
	public List<CronogramaFaturaFaseDTO> buscarDropDownFasesCronograma() {

		return vencimentoFaturasPorCicloContingenciaService.buscarFasesEmitirFaturaEEncerrado();
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/verificarVencimentosParaAlterarEmLote")
	public FaturasVencimentoVerificarResumoRespostaDTO verificarVencimentosParaAlterarEmLote(@Valid FaturasVencimentoVerificarFilterDTO dto) {

		return vencimentoFaturasPorCicloContingenciaService.verificarVencimentosParaAlterarEmLote(dto);
	}

	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/alterarVencimentosEmLote")
	public FaturasVencimentoAlterarResumoRespostaDTO alterarVencimentosEmLote(@Valid @RequestBody FaturasVencimentoAlterarFilterDTO dto, @RequestHeader("Authorization") String token) {

		return vencimentoFaturasPorCicloContingenciaService.executarFluxoAlterarVencimentosEmLote(dto, token);
	}
}

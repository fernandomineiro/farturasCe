package moduloFaturamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.dto.tarifaMedia.TarifaMediaPesquisaDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaProjectionDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaRegionalProjectionDTO;
import moduloFaturamento.service.TarifaMediaService;
import moduloFaturamento.service.common.GrupoDeConsumoService;

@RequestMapping("/backend-faturamento/faturaMedia")
@RestController
@CrossOrigin
public class TarifaMediaController {

    @Autowired
    private TarifaMediaService service;
    @Autowired
	private GrupoDeConsumoService grupoDeConsumoService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public GenericoWrapperDTO<List<TarifaMediaProjectionDTO> >buscarListaTarifaMedia(@RequestParam(value = "idGrupoConsumo", required = false) Short idGrupoConsumo,
                                                                                      TarifaMediaPesquisaDTO pesquisaDTO, Pageable pageable) {
        return service.buscarListaTarifaMedia(pesquisaDTO, pageable);
    }

    @ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarDropDownGrupoDeConsumo")
	public List<GrupoConsumoDropDownProjectionDTO> buscarDropDownGrupoDeConsumo() {

		return grupoDeConsumoService.buscarListaDropDownGrupoDeConsumo();
	}

    @ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/buscarDropDownRegionais")
	public List<TarifaMediaRegionalProjectionDTO> buscarDropDwonRegionais() {

		return service.buscarDropDwonRegionais();
	}

    
}

package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.bairroCiclo.LocalidadeNomeBairroCicloFilterDTO;
import moduloFaturamento.dto.bairroCiclo.CicloBairroRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.TarifaLocalidadeRespostaDTO;
import moduloFaturamento.dto.bairroCiclo.projection.LocalidadeIdNomeProjectionDTO;
import moduloFaturamento.service.FaturamentoBairroCicloService;

@RestController
@RequestMapping("/backend-faturamento/faturamentoBairroCiclo")
@CrossOrigin
public class FaturamentoBairroCicloController {

    @Autowired
    private FaturamentoBairroCicloService service;

    @GetMapping
    public List<TarifaLocalidadeRespostaDTO> buscarNomeDasRegioes(){
        return service.buscarListaDasRegioes();
    }

    @GetMapping("/ciclos")
    public List<Short> pesquisaCiclo( @RequestParam Short cdCidade) {
        return service.buscarCiclosDaLocalidade(cdCidade);
    }

    @GetMapping("/bairros")
    public List<LocalidadeIdNomeProjectionDTO> pesquisaBairro( @RequestParam Short cdCidade) {
        return service.buscarBairrosDaLocalidade(cdCidade);
    }

    @GetMapping("/localidade")
    public GenericoWrapperDTO<List<CicloBairroRespostaDTO>> pesquisaLocalidadeECiclo(@Valid LocalidadeNomeBairroCicloFilterDTO localidadeFilterDTO, Pageable pageable) {
        return service.pesquisarBairrosPorCiclo(localidadeFilterDTO, pageable);
    }

}

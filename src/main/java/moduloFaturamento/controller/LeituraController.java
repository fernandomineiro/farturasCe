package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.leitura.LeituraAgrupadorListAtualizarDTO;
import moduloFaturamento.dto.leitura.LeituraMostraDataDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarDTO;
import moduloFaturamento.dto.leitura.LeituraPesquisarListaDTO;
import moduloFaturamento.dto.leitura.projection.LeituraCicloProjectionDTO;
import moduloFaturamento.dto.leitura.projection.LeituraOcorrenciasProjectionDTO;
import moduloFaturamento.dto.leitura.projection.PesquisarNomeEIdLocalidadeProjectionDTO;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.service.LeituraService;

@RestController
@CrossOrigin
@RequestMapping("/backend-faturamento/faturamentoLeitura")
public class LeituraController {

    @Autowired
    private LeituraService service;

    @GetMapping
    public ResponseEntity<List<PesquisarNomeEIdLocalidadeProjectionDTO>> buscarNomeDaLocalidade() {
        return  ResponseEntity.ok().body(service.buscarNomeDaLocalidade());
    }

    @GetMapping("/ciclo")
    public ResponseEntity<List<LeituraCicloProjectionDTO>> buscarListaCiclo() {
        return  ResponseEntity.ok().body(service.buscarNumerosCiclos());
    }

    @GetMapping("/ocorrencias")
    public ResponseEntity<List<LeituraOcorrenciasProjectionDTO>> buscarOcorrencias() {
        return  ResponseEntity.ok().body(service.buscarOcorrecnias());
    }

    @GetMapping("/dataLeitura")
    public ResponseEntity<LeituraMostraDataDTO> buscarDaleituraReferenteAPesquisa(LeituraPesquisarListaDTO listaDTO) {
        return  ResponseEntity.ok().body(service.buscarDataDaleitura(listaDTO));
    }

    @GetMapping("/leituraMatricula/{matricula}")
    public ResponseEntity<List<Leitura>> buscarleituraMatricula(@PathVariable("matricula") Integer matricula) {
        return  ResponseEntity.ok().body(service.buscarLeituraExistentePorMatricula(matricula));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/leitura")
    public GenericoWrapperDTO<List<LeituraPesquisarDTO>> buscarLeituras(LeituraPesquisarListaDTO listaDTO, Pageable pageable) {
        return service.listarLeituraLocalidadeCicloReferencia(listaDTO, pageable);
    }

    @PutMapping
    public ResponseEntity<Void> atualilizarLista(@RequestBody @Valid LeituraAgrupadorListAtualizarDTO listaDeLeituraDTO, @RequestHeader("Authorization") String token) {
        service.atualizarLeituras(listaDeLeituraDTO, token);
        return ResponseEntity.noContent().build();
    }

}


package moduloFaturamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.credito.CreditoAtualizarDTO;
import moduloFaturamento.dto.credito.CreditoCadastrarDTO;
import moduloFaturamento.dto.credito.CreditoCampoLancadoPesquisaDTO;
import moduloFaturamento.dto.credito.CreditoEncerrarDTO;
import moduloFaturamento.dto.credito.CreditoLancadoDTO;
import moduloFaturamento.dto.credito.CreditoParaEditarRespostaDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaCreditoLancadoFilterDTO;
import moduloFaturamento.dto.credito.CreditoPesquisaDTO;
import moduloFaturamento.dto.credito.projection.CreditoServicosValidosprojectionDTO;
import moduloFaturamento.dto.leituraConsumoImovel.projection.EnderecoBasicoDoImovelProjectionDTO;
import moduloFaturamento.service.CreditoService;

@RestController
@RequestMapping("/backend-faturamento/credito")
@CrossOrigin
public class CreditoController {

    @Autowired
    private CreditoService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public GenericoWrapperDTO<List<CreditoPesquisaDTO>> pesquisarCreditodaMatricula (@RequestParam Integer matricula, @RequestParam Short dv, Pageable pageable) {
        return service.pesquisaCreditoPorMatricula(matricula, dv, pageable);
    }
    
    @GetMapping(value = "/campoCredito" )
    public ResponseEntity<CreditoCampoLancadoPesquisaDTO> buscarCampoCredito (CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO){
        return ResponseEntity.ok().body(service.pesquisarCampoCredito(creditoLancadoDTO));
    }

    
    @GetMapping(value = "/enderecoCompleto" )
    public ResponseEntity<EnderecoBasicoDoImovelProjectionDTO> pesquisarEnderecoCompleto (@RequestParam Integer matricula){
        return ResponseEntity.ok().body(service.pesquisarEnderecoCompleto(matricula));
    }

    @GetMapping(value = "/servicos" )
    public ResponseEntity<List<CreditoServicosValidosprojectionDTO>> pesquisarServicosQuePodemSerSelcionadoNoCredito(){
        return ResponseEntity.ok().body(service.pesquisarServicosQuePodemSerSelcionadoNoCredito());
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/campoLancamento" )
    public GenericoWrapperDTO<List<CreditoLancadoDTO>> pesquisarAsOcorrenciasCreditodoCredito (@Valid CreditoPesquisaCreditoLancadoFilterDTO creditoLancadoDTO, Pageable pageable){
        return service.pesquisarCreditoOcorrencias(creditoLancadoDTO, pageable);
    }

    @GetMapping(value = "/editarCredito/{id}")
    public ResponseEntity<CreditoParaEditarRespostaDTO> buscaCampoParaEditarCredito(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarDadosEditarCredito(id));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping
    public void editarCredito (@RequestBody @Valid CreditoAtualizarDTO atualizarDTO, @RequestHeader("Authorization") String token){
        service.atualizarCredito(atualizarDTO, token);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void cadastrarCredito (@Valid @RequestBody CreditoCadastrarDTO cadastroDTO,  @RequestHeader("Authorization") String token){
        service.cadastrarCredito(cadastroDTO, token);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping
    public void encerrarCredito(@Valid CreditoEncerrarDTO creditoEncerrarDTO, @RequestHeader("Authorization") String token){
        service.encerrarCredito(creditoEncerrarDTO, token);
    }
    
}

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
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaCampoLancamentoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaLocalidadeDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaParamServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaPesquisaFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaTotalServicoDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaValidarSSFilterDTO;
import moduloFaturamento.dto.faturaAvulsa.FaturaAvulsaVerificarClienteDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaBuscarMatriculaImovelProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaEnderecoClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaFiltroBaixaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaLancamentosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaServicosValidosProjectionDTO;
import moduloFaturamento.service.FaturaAvulsaService;

@RestController
@CrossOrigin
@RequestMapping("/backend-faturamento/faturaAvulsa")
public class FaturaAvulsaController {

    @Autowired
    private FaturaAvulsaService service;

    @GetMapping
    public ResponseEntity<GenericoWrapperDTO<List<FaturaAvulsaPesquisaDTO>>> buscarFaturaAvulsas(@Valid FaturaAvulsaPesquisaFilterDTO faturaAvulsaPesquisaFilterDTO, Pageable pageable) {
        return  ResponseEntity.ok().body(service.buscarFaturaAvulsas(faturaAvulsaPesquisaFilterDTO, pageable));
    }

    @GetMapping(value = "/tipoBaixa")
    public ResponseEntity<List<FaturaAvulsaFiltroBaixaProjectionDTO>> buscarTipoBaixa(){
        return ResponseEntity.ok().body(service.buscarTipoBaixa());
    }

    @GetMapping(value = "/enderecoCliente")
    public ResponseEntity<FaturaAvulsaEnderecoClienteProjectionDTO> buscarEnderedoCliente(@RequestParam Integer cdCliente){
        return ResponseEntity.ok().body(service.buscarEnderedoCliente(cdCliente));
    }

    @GetMapping(value = "/exibirTelaFaturaAvulsa/{id}")
    public ResponseEntity<FaturaAvulsaCampoLancamentoDTO> buscarCampoParaEditarCredito(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarDadosFaturaAvulsa(id));
    }

    @GetMapping(value = "/exibirTelaLancamentoFaturaAvulsa/{id}")
    public ResponseEntity<List<FaturaAvulsaLancamentosProjectionDTO>> buscarLancamentoFaturaAvulsa(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.buscarLancamentoFaturaAvulsa(id));
    }

    @GetMapping(value = "/dropdownLocalidade")
    public ResponseEntity<List<FaturaAvulsaLocalidadeDTO>> listaLocalidade (){
        return ResponseEntity.ok().body(service.listaLocalidade());
    }

    @GetMapping(value = "/matriculaFaturaAvulsa")
    public ResponseEntity<FaturaAvulsaBuscarMatriculaImovelProjectionDTO> buscarMatriculaImovel(@RequestParam Integer matriculaImovel, 
                                                                                                @RequestParam Short dvMatriculaImovel){
        return ResponseEntity.ok().body(service.buscarMatriculaImovel(matriculaImovel, dvMatriculaImovel));
    }

    @GetMapping(value = "/verificarCdClienteParaCadastrar")
    public ResponseEntity<FaturaAvulsaVerificarClienteDTO> retornarDadosCliente(@RequestParam Integer cdCliente, 
                                                                                    @RequestParam Short dvCliente ) {
        return ResponseEntity.ok().body(service.retornarImpostoDeUmClienteCliente(cdCliente, dvCliente));
    }

    @GetMapping(value = "/buscarServicoValidos")
    public ResponseEntity<List<FaturaAvulsaServicosValidosProjectionDTO>> buscarServicosValidosParaCadastrarFaturaAvulsa(Short cdCidade){
        return ResponseEntity.ok().body(service.buscarServicosValidosParaCadastrarFaturaAvulsa(cdCidade));
    }

    @GetMapping(value = "/validarSS")
    public ResponseEntity<Boolean> validarUmaSS(FaturaAvulsaValidarSSFilterDTO faturaAvulsaValidarSSDTO){
        return ResponseEntity.ok().body(service.validarUmaSS(faturaAvulsaValidarSSDTO));
    }

    @PutMapping(value = "/verificarServicoESomaDosValores")
    public ResponseEntity<FaturaAvulsaTotalServicoDTO> verificarServicoSelecioandoETotalLancamento (@RequestBody FaturaAvulsaParamServicoDTO listaServico){
        return ResponseEntity.ok().body(service.verificarServicoSelecioandoETotalLancamento(listaServico));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void cadastrarFaturaAvulsa(@Valid @RequestBody FaturaAvulsaCadastrarDTO cadastroFaturaAvulsaDTO, @RequestHeader("Authorization") String token){
        service.cadastrarUmFaturaAvusa(cadastroFaturaAvulsaDTO, token);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> encerrarUmaFaturaAvulsa(@PathVariable Long id, @RequestHeader("Authorization") String token){
        service.encerrarUmaFaturaAvulsa(id, token);
        return ResponseEntity.noContent().build();
    }
     
}

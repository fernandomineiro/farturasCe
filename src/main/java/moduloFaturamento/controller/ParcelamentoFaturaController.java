package moduloFaturamento.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaListaDasSituacoesDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoDaFaturaPorDeliberacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaCadastroDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaContasEmAbertoDoClienteDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaEnvioDaSimulacaoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaListaEmailDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoPagamentoDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFaturaSimulacaoRespostaDTO;
import moduloFaturamento.dto.parcelamentoFatura.ParcelamentoFauraSelecioadoDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaCabecalhoProjectionDTO;
import moduloFaturamento.dto.parcelamentoFatura.projection.ParcelamentoFaturaPesquisaResponseProjectionDTO;
import moduloFaturamento.service.ParcelamentoFaturaService;


@CrossOrigin
@RequestMapping("/backend-faturamento/parcelamentoFatura")
@RestController
public class ParcelamentoFaturaController {

    @Autowired
    private ParcelamentoFaturaService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public GenericoWrapperDTO<List<ParcelamentoFaturaPesquisaResponseProjectionDTO>> buscarMatriculasDoCliente(Integer cdCliente, Short dvCliente, String cpfOuCnpj, 
        Pageable pageable){
        return service.buscarFaturasParaCliente(cdCliente, dvCliente, cpfOuCnpj, pageable);
    }

    @GetMapping("/buscarNome")
    public ResponseEntity<String> buscarNome(Integer cdCliente, String cpfOuCnpj){
        return ResponseEntity.ok().body(service.buscarNome(cdCliente, cpfOuCnpj));
    }

    @GetMapping("/buscarFaturaDoCliente")
    public ResponseEntity<ParcelamentoFaturaCabecalhoProjectionDTO> buscarCabecalhoDaFaturaEmAberto(String cdCliente, Integer matriculaImovel){
        return ResponseEntity.ok().body(service.buscarCabecalhoDaFaturaEmAberto(cdCliente, matriculaImovel));
    }

    @GetMapping("/listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovel")
    public ResponseEntity<ParcelamentoFaturaContasEmAbertoDoClienteDTO> listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovel(String cdCliente, Integer matriculaImovel){
        return ResponseEntity.ok().body(service.listaDeFaturaEmAbertoDoClienteDaFaturaEMatriculaImovel(cdCliente, matriculaImovel));
    }

    @GetMapping("/quadroRefrenciasFaturaSelecioanda")
    public ResponseEntity<ParcelamentoFaturaContasEmAbertoDoClienteDTO> quadroRefrenciasFaturaSelecioanda(ParcelamentoFauraSelecioadoDTO parcelamentoFaturaSimulacaoDTO){
        return ResponseEntity.ok().body(service.quadroRefrenciasFaturaSelecioanda(parcelamentoFaturaSimulacaoDTO));
    }

    @GetMapping("/simularNegociacaoDividaContabil")
    public ResponseEntity<ParcelamentoFaturaSimulacaoRespostaDTO> simularNegociacaoDivida(ParcelamentoFaturaSelecionarFaturaDTO parcelamentoFaturaSimulacaoDTO){
        return ResponseEntity.ok().body(service.simularNegociacaoDivida(parcelamentoFaturaSimulacaoDTO));
    }    

    @GetMapping("/simularNegociacaoDividaParcelamentoFatura")
    public ResponseEntity<ParcelamentoFaturaSimulacaoPagamentoDTO> simularParcelamentoFatura(ParcelamentoFaturaSelecionarFaturaParaParcelamentoDTO parcelamentoFaturaSimulacaoDTO){
        return ResponseEntity.ok().body(service.simularParcelamentoFatura(parcelamentoFaturaSimulacaoDTO));
    }

    @GetMapping("/dataUtil")
    @ResponseStatus(code = HttpStatus.OK)
    public LocalDate buscarProximoDiaUtil(Integer matriculaImovel){
        return service.buscarProximoDiaUtil(matriculaImovel);
    } 

    @GetMapping("/buscarEmailDoCliente")
    public ResponseEntity<ParcelamentoFaturaListaEmailDTO> buscarEmailDoCliente(Integer cdCliente){
        return ResponseEntity.ok().body(service.buscarEmailDoCliente(cdCliente));
    } 

    @GetMapping("/enviarEmailComPropostaDoParcelamento")
    public ResponseEntity<Void> enviarEmailComPropostaDoParcelamento(ParcelamentoFaturaEnvioDaSimulacaoDTO envioDaSimulacaoDTO, 
        @RequestHeader("Authorization") String token ){

        return ResponseEntity.ok().body(service.enviarEmailComPropostaDoParcelamento(envioDaSimulacaoDTO, token));
    } 

    @GetMapping("/buscarSituacaoDoParcelamento")
    public ResponseEntity<ParcelamentoDaFaturaListaDasSituacoesDTO> buscarSituacaoDoParcelamento(Integer matriculaImovel,  Integer cdCliente){
        return ResponseEntity.ok().body(service.buscarSituacaoDoParcelamento(matriculaImovel, cdCliente));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void cadastrarParcelamento(@RequestBody ParcelamentoFaturaCadastroDTO cadastroDTO, @RequestHeader("Authorization") String token){
        service.cadastrarParcelamento(cadastroDTO, token);
    }

    
    @GetMapping("/informacaoDeliberacao")
    public ResponseEntity<ParcelamentoDaFaturaPorDeliberacaoDTO> informacaoDeliberacao(Integer id, Integer cdCliente, Pageable pageable){
    	 return ResponseEntity.ok().body(service.informacaoDeliberacao(id, cdCliente, pageable));
    }


}
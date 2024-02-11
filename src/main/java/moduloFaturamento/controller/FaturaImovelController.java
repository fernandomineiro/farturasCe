package moduloFaturamento.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cadastramentoFatura.CadastramentoListaFaturaFilterDTO;
import moduloFaturamento.dto.cadastramentoFatura.ClienteDropDownDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridRespostaDTO;
import moduloFaturamento.dto.cadastramentoFatura.InformacoesImovelDTO;
import moduloFaturamento.dto.cadastramentoFatura.SituacaoBaixaDropDownDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaAvulsaImovelCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaImovelAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.ParametroDropdownServicosFaturaAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.DadosImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaAvulsaCriadaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaPorTipoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaProjectionOutDTO;
import moduloFaturamento.dto.faturaImovel.BuscaValorServicoFilterDTO;
import moduloFaturamento.dto.faturaImovel.FaturaImovelDetalhadaDTO;
import moduloFaturamento.dto.faturaImovel.projection.FaturaImovelLancamentoProjectionDTO;
import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.dto.grupoDeConsumo.TipoFaturamentoDropDownProjectionDTO;
import moduloFaturamento.service.CadastramentoFaturaService;
import moduloFaturamento.service.FaturaAvulsaImovelService;
import moduloFaturamento.service.FaturaImovelService;
import moduloFaturamento.service.common.GrupoDeConsumoService;

@RestController
@CrossOrigin
@RequestMapping("/backend-faturamento/faturaImovel")
public class FaturaImovelController {

	@Autowired
	private FaturaAvulsaImovelService faturaAvulsaImovelService;

	@Autowired
	private GrupoDeConsumoService grupoDeConsumoService;

	@Autowired
	private FaturaImovelService faturaImovelService;

	@Autowired
	private CadastramentoFaturaService cadastramentoFaturaService;

	@GetMapping("/buscarFaturaImovelDetalhada")
	public FaturaImovelDetalhadaDTO buscarFaturaImovelDetalhada(Integer idFatura,
			@RequestHeader("Authorization") String token) {

		return faturaImovelService.buscarFaturaImovelDetalhada(idFatura, token);
	}

	@GetMapping("/buscarFaturaImovelDetalhadaLancamentos")
	public GenericoWrapperDTO<List<FaturaImovelLancamentoProjectionDTO>> buscarFaturaImovelDetalhadaLancamentos(
			Integer idFatura,
			@RequestHeader("Authorization") String token, Pageable pageable) {

		return faturaImovelService.buscarFaturaImovelDetalhadaLancamentos(idFatura, token, pageable);
	}

	@GetMapping("/buscarGrupoDeConsumoDropDown")
	public List<GrupoConsumoDropDownProjectionDTO> buscarGrupoDeConsumoDropDown(
			@RequestHeader("Authorization") String token) {

		return grupoDeConsumoService.buscarListaDropDownGrupoDeConsumo();
	}

	@GetMapping("/buscarTipoFaturamentoDropDown")
	public List<TipoFaturamentoDropDownProjectionDTO> buscarTipoFaturamentoDropDown(
			@RequestHeader("Authorization") String token) {

		return faturaAvulsaImovelService.buscarTipoFaturamentoDropDown();
	}

	@GetMapping("/buscarDadosImovelClienteCriacaoFaturaAvulsa")
	public DadosImovelClienteProjectionDTO buscarDadosImovelClienteCriacaoFaturaAvulsa(Integer matricula) {

		return faturaAvulsaImovelService.buscarDadosImovelClienteCriacaoFaturaAvulsa(matricula);
	}

	@GetMapping("/buscarServicosDropDown")
	public List<ServicoFaturaProjectionOutDTO> buscarServicosDropDown(Integer matricula, Integer referencia) {

		return faturaAvulsaImovelService.buscarServicosDropDown(matricula, referencia);
	}

	@GetMapping("/buscarClientesDropDown")
	public List<FaturaImovelClienteProjectionDTO> buscarClientesDropDown(Integer matriculaSemDv) {

		return faturaAvulsaImovelService.buscarClientesDropDown(matriculaSemDv);
	}
	@GetMapping("/buscarTodosServicosPorTipoDropDown")
	public List<ServicoFaturaPorTipoProjectionDTO> buscarServicosDropDown(ParametroDropdownServicosFaturaAvulsaDTO filter) {

		return faturaAvulsaImovelService.buscarServicosDropDown(filter);
	}

	@GetMapping("/buscarValorServico")
	public BigDecimal buscarValorServico(BuscaValorServicoFilterDTO filter) {

		return faturaAvulsaImovelService.buscarValorServico(filter);
	}
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/gravarFaturaImovelAvulsa")
	public void gravarFaturaImovelAvulsa(@RequestBody @Valid FaturaAvulsaImovelCadastrarDTO dto,
			@RequestHeader("Authorization") String token) {

		 faturaAvulsaImovelService.cadastrarFaturaAvulsaImovel(dto, token);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/faturas/situacoesBaixaDropDown")
	public List<SituacaoBaixaDropDownDTO> buscarSituacaoBaixaDropDown(@RequestHeader("Authorization") String token) {

		return cadastramentoFaturaService.listarSituacoesBaixaDropDown();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/faturas/clientesDropDown")
	public List<ClienteDropDownDTO> buscarLocalidadeDropDown(Integer matricula,
			@RequestHeader("Authorization") String token) {

		return cadastramentoFaturaService.listarClientesDropDown(matricula);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/faturas/informacoesImovel")
	public InformacoesImovelDTO buscarInformacoesImovel(Integer matricula,
			@RequestHeader("Authorization") String token) {

		return cadastramentoFaturaService.buscarInformacoesImovel(matricula);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/faturas/listar")
	public GenericoWrapperDTO<List<FaturaGridRespostaDTO>> buscarListaFaturas(CadastramentoListaFaturaFilterDTO filter,
			Pageable pageable,
			@RequestHeader("Authorization") String token) {

		return cadastramentoFaturaService.listarFaturasGrid(filter, pageable);
	}
}

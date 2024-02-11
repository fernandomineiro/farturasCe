package moduloFaturamento.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import moduloFaturamento.dto.faturaAvulsaImovel.FaturaAvulsaImovelCadastrarDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.FaturaImovelAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.ParametroDropdownServicosFaturaAvulsaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.DadosImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaAvulsaCriadaDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.FaturaImovelClienteProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaPorTipoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsaImovel.projection.ServicoFaturaProjectionOutDTO;
import moduloFaturamento.dto.faturaImovel.BuscaValorServicoFilterDTO;
import moduloFaturamento.dto.grupoDeConsumo.TipoFaturamentoDropDownProjectionDTO;

public interface FaturaAvulsaImovelService {

	List<TipoFaturamentoDropDownProjectionDTO> buscarTipoFaturamentoDropDown();

	List<ServicoFaturaProjectionOutDTO> buscarServicosDropDown(Integer matricula, Integer referencia);

	DadosImovelClienteProjectionDTO buscarDadosImovelClienteCriacaoFaturaAvulsa(Integer matricula);

	DadosImovelClienteProjectionDTO buscarDadosImovelClienteEdicaoFaturaAvulsa(Integer matricula, LocalDate referencia);

	
	BigDecimal buscarValorServico(Short cdServico, Integer volume, Integer matricula, LocalDate referencia);

	BigDecimal buscarValorServico(BuscaValorServicoFilterDTO filter);

	void cadastrarFaturaAvulsaImovel(FaturaAvulsaImovelCadastrarDTO dto, String token);

	List<ServicoFaturaPorTipoProjectionDTO> buscarServicosDropDown(ParametroDropdownServicosFaturaAvulsaDTO filter);

    List<FaturaImovelClienteProjectionDTO> buscarClientesDropDown(Integer matriculaSemDv);

}

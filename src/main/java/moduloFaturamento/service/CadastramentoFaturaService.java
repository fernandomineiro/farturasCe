package moduloFaturamento.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cadastramentoFatura.CadastramentoListaFaturaFilterDTO;
import moduloFaturamento.dto.cadastramentoFatura.ClienteDropDownDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridRespostaDTO;
import moduloFaturamento.dto.cadastramentoFatura.InformacoesImovelDTO;
import moduloFaturamento.dto.cadastramentoFatura.SituacaoBaixaDropDownDTO;

public interface CadastramentoFaturaService {

	List<SituacaoBaixaDropDownDTO> listarSituacoesBaixaDropDown();

	List<ClienteDropDownDTO> listarClientesDropDown(Integer matricula);

	InformacoesImovelDTO buscarInformacoesImovel(Integer matricula);

	GenericoWrapperDTO<List<FaturaGridRespostaDTO>> listarFaturasGrid(CadastramentoListaFaturaFilterDTO filter, Pageable pageable);

}

package moduloFaturamento.regras.calculoFaturaDetalhada;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalheDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaFaixaDTO;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;

@Service
public class CalculoFaturaDetalhadaProcessamentoParaDtoMapeador {
	
	public FaturaDetalhadaRespostaDTO mapearFaturaDetalheProcessamentoParaDTO(FaturaDetalheProcessamento detalhe) {

		FaturaDetalhadaRespostaDTO detalhadaRespostaDTO = new FaturaDetalhadaRespostaDTO();

		FaturaDetalheDTO detalheDTO = new FaturaDetalheDTO();

		detalheDTO.setTotalMetrosUsadosFaixa(detalhe.getConsumo());

		detalheDTO.setTotalAgua(detalhe.getTotalAgua());
		detalheDTO.setTotalDisponibilidade(detalhe.getTotalDisponibilidade());

		detalheDTO.setTotalEsgoto(BigDecimal.ZERO);

		detalheDTO.setTotalEsgoto("Coletado".equals(detalhe.getEsgoto()) ? detalhe.getTotalEsgotoAtivo() : detalheDTO.getTotalEsgoto());
		detalheDTO.setTotalEsgoto("Tratado".equals(detalhe.getEsgoto()) ? detalhe.getTotalEsgotoTratado() : detalheDTO.getTotalEsgoto());

		detalheDTO.setTotalTotalFatura(detalhe.getTotalFatura());

		List<FaturaFaixaDTO> faixasDTO = mapearFaturaFaixasDTO(detalhe);
		detalheDTO.setFaixas(faixasDTO);

		detalhadaRespostaDTO.setFaturaFaixasDetalheDTO(detalheDTO);

		return detalhadaRespostaDTO;
	}
	
	public List<FaturaFaixaDTO> mapearFaturaFaixasDTO(FaturaDetalheProcessamento detalhe) {

		return detalhe.getFaixas().stream().map(faixaProcessamento -> {

			FaturaFaixaDTO dto = new FaturaFaixaDTO();

			dto.setFaixa(faixaProcessamento.getFaixa());

			dto.setMetrosPorFaixa(faixaProcessamento.getMetrosPorFaixaCorrente());
			dto.setMetrosUsadosFaixa(faixaProcessamento.getMetrosUsadosFaixaCorrente());

			dto.setAguaParcelaFixa(faixaProcessamento.getAguaParcelaFixa());
			dto.setAguaParcelaVariavel(faixaProcessamento.getAguaParcelaVariavel());
			dto.setAguaTotal(faixaProcessamento.getAguaTotal());

			if ("Coletado".equals(detalhe.getEsgoto())) {

				dto.setEsgotoParcelaFixa(faixaProcessamento.getEsgotoAtivoParcelaFixa());
				dto.setEsgotoParcelaVariavel(faixaProcessamento.getEsgotoAtivoParcelaVariavel());
				dto.setEsgotoTotal(faixaProcessamento.getEsgotoAtivoTotal());
			}

			if ("Tratado".equals(detalhe.getEsgoto())) {

				dto.setEsgotoParcelaFixa(faixaProcessamento.getEsgotoTratadoParcelaFixa());
				dto.setEsgotoParcelaVariavel(faixaProcessamento.getEsgotoTratadoParcelaVariavel());
				dto.setEsgotoTotal(faixaProcessamento.getEsgotoTratadoTotal());
			}

			if ("Disponibilidade".equals(detalhe.getEsgoto())) {

				dto.setDisponibilidadeParcelaFixa(faixaProcessamento.getDisponibilidadeParcelaFixa());
				dto.setDisponibilidadeParcelaVariavel(faixaProcessamento.getDisponibilidadeParcelaVariavel());
				dto.setDisponibilidadeTotal(faixaProcessamento.getDisponibilidadeTotal());
			}
			return dto;
		}).collect(Collectors.toList());
	}
}

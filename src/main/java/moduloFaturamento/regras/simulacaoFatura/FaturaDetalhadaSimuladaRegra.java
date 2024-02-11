package moduloFaturamento.regras.simulacaoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaFaixaDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaLeituraDadosDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaFilterDTO;
import moduloFaturamento.model.Leitura;
import moduloFaturamento.model.common.Imovel;
import moduloFaturamento.regras.calculoFaturaDetalhada.CalculoFaturaDetalhadaProcessamentoParaDtoMapeador;
import moduloFaturamento.regras.calculoFaturaDetalhada.CalculoFaturaDetalhadaRegra;
import moduloFaturamento.regras.simulacaoFatura.processamento.FaturaDetalheProcessamento;
import moduloFaturamento.repository.common.ImovelRepository;
import moduloFaturamento.repository.common.LocalidadeRepository;
import moduloFaturamento.service.common.GrupoDeConsumoService;
import moduloFaturamento.service.common.LocalidadeService;
import moduloFaturamento.service.common.OcorrenciaLeituraService;
import moduloFaturamento.util.ConverterData;

@Service
public class FaturaDetalhadaSimuladaRegra extends CalculoFaturaDetalhadaRegra {

	// ---------------------------------------------------------------- Repositories

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private ImovelRepository imovelRepository;

	@Autowired
	private OcorrenciaLeituraService ocorrenciaLeituraService;

	// ---------------------------------------------------------------- Services

	@Autowired
	private LocalidadeService localidadeService;

	@Autowired
	private GrupoDeConsumoService grupoDeConsumoService;

	// ---------------------------------------------------------------- Mapeador

	@Autowired
	private CalculoFaturaDetalhadaProcessamentoParaDtoMapeador paraDtoMapeador;

	public FaturaDetalheProcessamento construirDetalheProcessamentoPorDto(SimulacaoFaturaFilterDTO dto) {

		if (dto.getCdOcorrencia() != null) {

			Imovel imovel = imovelRepository.findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(dto.getMatricula(), dto.getDv()).get();

			Leitura leitura = buscarLeitura(imovel.getMatriculaImovel(), dto.getReferencia());

			Short diasVenda = leitura.getDiasVenda();

			BigDecimal consumo = BigDecimal.ZERO;

			if (dto.getCdOcorrencia() != null && dto.getCdOcorrencia() != 0) {
			
				consumo = super.buscarMediaConsumoMensal(imovel.getMatriculaImovel(), dto.getReferencia());

				dto.setLeituraConsumo(consumo);
				dto.setLeituraAnterior(BigDecimal.valueOf(leitura.getLeitura()));
				dto.setLeituraAtual(dto.getLeituraAnterior().add(consumo));
				
			} else {

				consumo = super.determinarConsumoPorLeitura(leitura, dto.getMatricula(), dto.getReferencia());
			}

			Integer matricula = imovel.getMatriculaImovel();
			LocalDate referencia = dto.getReferencia();

			FaturaSituacaoAguaEsgotoProjection situacaoAguaEsgoto = super.buscarSituacaoAguaEsgotoPelaFatura(matricula, referencia);

			Boolean agua = super.determinarSituacaoAgua(situacaoAguaEsgoto);
			String esgoto = super.determinarSituacaoEsgoto(situacaoAguaEsgoto);

			return super.construirDetalheProcessamento(diasVenda, consumo, agua, esgoto);
		} else {

			Short diasVenda = dto.getDataLeituraDiasVenda();
			BigDecimal consumo = dto.getLeituraConsumo();

			Boolean agua = dto.getAgua();
			String esgoto = dto.getEsgoto();

			return super.construirDetalheProcessamento(diasVenda, consumo, agua, esgoto);
		}

	}

	public void construirFaixasProcessamentoPorDto(SimulacaoFaturaFilterDTO dto, FaturaDetalheProcessamento detalhe) {

		Short cdTarifa = localidadeRepository.buscarCodigoTarifaPorCodigoCidade(dto.getCdCidade());

		Integer dataReferencia = ConverterData.localDateEmIntegerDataFormatoDB(dto.getReferencia());
		Short grupoDeConsumo = dto.getGrupoDeConsumo();

		super.construirFaixasProcessamento(dataReferencia, grupoDeConsumo, cdTarifa, detalhe);
	}

	public FaturaLeituraDadosDTO construirFaturaLeituraDadosDTO(SimulacaoFaturaFilterDTO dto) {

		FaturaLeituraDadosDTO leituraDadosDTO = new FaturaLeituraDadosDTO();

		leituraDadosDTO.setMatricula(dto.getMatricula());
		leituraDadosDTO.setDv(dto.getDv());

		leituraDadosDTO.setAgua(dto.getAgua());
		leituraDadosDTO.setEsgoto(dto.getEsgoto());

		leituraDadosDTO.setReferencia(dto.getReferencia());

		leituraDadosDTO.setDataLeituraAtual(dto.getDataLeituraAtual());
		leituraDadosDTO.setDataLeituraAnterior(dto.getDataLeituraAnterior());
		leituraDadosDTO.setDataLeituraDiasVenda(dto.getDataLeituraDiasVenda());

		leituraDadosDTO.setLeituraAtual(dto.getLeituraAtual());
		leituraDadosDTO.setLeituraAnterior(dto.getLeituraAnterior());
		leituraDadosDTO.setLeituraConsumo(dto.getLeituraConsumo());

		leituraDadosDTO.setLocalidadeDTO(localidadeService.buscarLocalidadePorCdCidade(dto.getCdCidade()));
		leituraDadosDTO.setGrupoConsumoDTO(grupoDeConsumoService.buscarGruposDeConsumoPorCodigo(dto.getGrupoDeConsumo()));

		if (dto.getCdOcorrencia() != null) {

			leituraDadosDTO.setOcorrenciaLeituraDTO(ocorrenciaLeituraService.buscarDropDownOcorrenciaLeituraPorCodigo(dto.getCdOcorrencia()));
		}

		return leituraDadosDTO;
	}

	@Override
	public void calcularFaturaDetalheProcessamento(FaturaDetalheProcessamento detalhe) {

		super.calcularFaturaDetalheProcessamento(detalhe);
	}

	@Override
	public void calcularFaturaFaixasProcessamento(FaturaDetalheProcessamento detalhe) {

		super.calcularFaturaFaixasProcessamento(detalhe);
	}

	public FaturaDetalhadaRespostaDTO mapearFaturaDetalheProcessamentoParaDTO(SimulacaoFaturaFilterDTO dto, FaturaDetalheProcessamento detalhe) {

		FaturaDetalhadaRespostaDTO detalhadaRespostaDTO = paraDtoMapeador.mapearFaturaDetalheProcessamentoParaDTO(detalhe);

		FaturaLeituraDadosDTO leituraDadosDTO = construirFaturaLeituraDadosDTO(dto);
		detalhadaRespostaDTO.setFaturaLeituraDadosDTO(leituraDadosDTO);

		return detalhadaRespostaDTO;
	}

	public List<FaturaFaixaDTO> mapearFaturaFaixasDTO(FaturaDetalheProcessamento detalhe) {

		return paraDtoMapeador.mapearFaturaFaixasDTO(detalhe);
	}

}

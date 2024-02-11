package moduloFaturamento.regras.simulacaoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.dto.simulacaoFatura.FaturaDetalhadaRespostaDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaFaixaDTO;
import moduloFaturamento.dto.simulacaoFatura.FaturaLeituraDadosDTO;
import moduloFaturamento.dto.simulacaoFatura.SimulacaoFaturaMatriculaReferenciaFilterDTO;
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
public class FaturaDetalhadaMatriculaReferenciaRegra extends CalculoFaturaDetalhadaRegra {

	// ---------------------------------------------------------------- Service

	@Autowired
	private LocalidadeService localidadeService;

	@Autowired
	private GrupoDeConsumoService grupoDeConsumoService;

	@Autowired
	private OcorrenciaLeituraService ocorrenciaLeituraService;

	// ---------------------------------------------------------------- Repository

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private ImovelRepository imovelRepository;

	// ---------------------------------------------------------------- Mapeador

	@Autowired
	private CalculoFaturaDetalhadaProcessamentoParaDtoMapeador paraDtoMapeador;

	public FaturaDetalheProcessamento construirDetalheProcessamentoPorDtoMatricula(SimulacaoFaturaMatriculaReferenciaFilterDTO dto) {

		Imovel imovel = imovelRepository.findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(dto.getMatricula(), dto.getDv()).get();

		Leitura leitura = buscarLeitura(imovel.getMatriculaImovel(), dto.getReferencia());

		Short diasVenda = leitura.getDiasVenda();
		
		BigDecimal consumo = super.determinarConsumoPorLeitura(leitura, dto.getMatricula(), dto.getReferencia());

		Integer matricula = imovel.getMatriculaImovel();
		LocalDate referencia = dto.getReferencia();

		FaturaSituacaoAguaEsgotoProjection situacaoAguaEsgoto = super.buscarSituacaoAguaEsgotoPelaFatura(matricula, referencia);

		Boolean agua = super.determinarSituacaoAgua(situacaoAguaEsgoto);
		String esgoto = super.determinarSituacaoEsgoto(situacaoAguaEsgoto);

		return super.construirDetalheProcessamento(diasVenda, consumo, agua, esgoto);
	}

	public void construirFaixasProcessamentoPorDtoSimulacao(SimulacaoFaturaMatriculaReferenciaFilterDTO dto, FaturaDetalheProcessamento detalhe) {

		Imovel imovel = imovelRepository.findByMatriculaImovelAndDvAndDataHoraExclusaoIsNull(dto.getMatricula(), dto.getDv()).get();

		Short cdTarifa = localidadeRepository.buscarCodigoTarifaPorCodigoCidade(imovel.getCdCidade());

		Short grupoDeConsumo = imovel.getGrupoDeConsumo();

		Integer dataReferencia = ConverterData.localDateEmIntegerDataFormatoDB(dto.getReferencia());

		super.construirFaixasProcessamento(dataReferencia, grupoDeConsumo, cdTarifa, detalhe);
	}

	@Override
	public void calcularFaturaDetalheProcessamento(FaturaDetalheProcessamento detalhe) {

		super.calcularFaturaDetalheProcessamento(detalhe);
	}

	@Override
	public void calcularFaturaFaixasProcessamento(FaturaDetalheProcessamento detalhe) {

		super.calcularFaturaFaixasProcessamento(detalhe);
	}

	public FaturaLeituraDadosDTO construirFaturaLeituraDadosDTO(SimulacaoFaturaMatriculaReferenciaFilterDTO dto) {

		LocalDate referenciaAtual = dto.getReferencia();
		LocalDate referenciaAnterior = referenciaAtual.minusMonths(1);

		FaturaLeituraDadosDTO leituraDadosDTO = new FaturaLeituraDadosDTO();

		Optional<Imovel> imovelOP = imovelRepository.findById(dto.getMatricula());

		Imovel imovel = imovelOP.get();

		leituraDadosDTO.setMatricula(dto.getMatricula());
		leituraDadosDTO.setDv(dto.getDv());
		leituraDadosDTO.setReferencia(referenciaAtual);

		Leitura leituraAtual = buscarLeitura(dto.getMatricula(), referenciaAtual);
		Leitura leituraAnterior = buscarLeitura(dto.getMatricula(), referenciaAnterior);

		FaturaSituacaoAguaEsgotoProjection situacaoAguaEsgoto = super.buscarSituacaoAguaEsgotoPelaFatura(leituraDadosDTO.getMatricula(), referenciaAtual);

		leituraDadosDTO.setAgua(determinarSituacaoAgua(situacaoAguaEsgoto));
		leituraDadosDTO.setEsgoto(determinarSituacaoEsgoto(situacaoAguaEsgoto));

		LocalDate dataAtual = ConverterData.integerEmLocalDateDataFormatoDB(leituraAtual.getDataDeleitura());
		leituraDadosDTO.setDataLeituraAtual(dataAtual);
		leituraDadosDTO.setLeituraAtual(BigDecimal.valueOf(leituraAtual.getLeitura()));

		LocalDate dataAnterior = ConverterData.integerEmLocalDateDataFormatoDB(leituraAnterior.getDataDeleitura());
		leituraDadosDTO.setDataLeituraAnterior(dataAnterior);
		leituraDadosDTO.setLeituraAnterior(BigDecimal.valueOf(leituraAnterior.getLeitura()));

		leituraDadosDTO.setDataLeituraDiasVenda(leituraAtual.getDiasVenda());

		leituraDadosDTO.setLeituraConsumo(BigDecimal.valueOf(leituraAtual.getMedido()));

		leituraDadosDTO.setLocalidadeDTO(localidadeService.buscarLocalidadePorMatriculaImovel(dto.getMatricula()));
		leituraDadosDTO.setGrupoConsumoDTO(grupoDeConsumoService.buscarGruposDeConsumoPorCodigo(imovel.getGrupoDeConsumo()));

		if (leituraAtual.getOcorrencia() != null) {

			leituraDadosDTO.setOcorrenciaLeituraDTO(ocorrenciaLeituraService.buscarDropDownOcorrenciaLeituraPorCodigo(leituraAtual.getOcorrencia()));
		}

		return leituraDadosDTO;
	}

	public FaturaDetalhadaRespostaDTO mapearFaturaDetalheProcessamentoParaDTO(SimulacaoFaturaMatriculaReferenciaFilterDTO dto,
			FaturaDetalheProcessamento detalhe) {

		FaturaDetalhadaRespostaDTO detalhadaRespostaDTO = paraDtoMapeador.mapearFaturaDetalheProcessamentoParaDTO(detalhe);

		FaturaLeituraDadosDTO leituraDadosDTO = construirFaturaLeituraDadosDTO(dto);
		detalhadaRespostaDTO.setFaturaLeituraDadosDTO(leituraDadosDTO);

		return detalhadaRespostaDTO;
	}

	public List<FaturaFaixaDTO> mapearFaturaFaixasDTO(FaturaDetalheProcessamento detalhe) {

		return paraDtoMapeador.mapearFaturaFaixasDTO(detalhe);
	}
}

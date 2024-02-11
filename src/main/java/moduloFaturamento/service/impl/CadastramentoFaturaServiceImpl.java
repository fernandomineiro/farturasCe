package moduloFaturamento.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import moduloFaturamento.comparator.FaturaGridRespostaComparator;
import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cadastramentoFatura.CadastramentoListaFaturaFilterDTO;
import moduloFaturamento.dto.cadastramentoFatura.ClienteDropDownDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridProjectionDTO;
import moduloFaturamento.dto.cadastramentoFatura.FaturaGridRespostaDTO;
import moduloFaturamento.dto.cadastramentoFatura.InformacoesImovelDTO;
import moduloFaturamento.dto.cadastramentoFatura.SituacaoBaixaDropDownDTO;
import moduloFaturamento.mapper.CadastramentoFaturaListaGridProjectionDtoMapper;
import moduloFaturamento.repository.CadastramentoFaturaRepository;
import moduloFaturamento.service.CadastramentoFaturaService;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.Paginacao;

@Service
public class CadastramentoFaturaServiceImpl implements CadastramentoFaturaService {

	// ----------------------------------------------------------------------- Repository

	@Autowired
	private CadastramentoFaturaRepository cadastramentoFaturaRepository;

	// ----------------------------------------------------------------------- Mapper

	@Autowired
	private CadastramentoFaturaListaGridProjectionDtoMapper faturaListaGridProjectionDtoMapper;

	@Override
	public List<SituacaoBaixaDropDownDTO> listarSituacoesBaixaDropDown() {

		return cadastramentoFaturaRepository.buscarListaSituacaoDropDown();
	}

	@Override
	public List<ClienteDropDownDTO> listarClientesDropDown(Integer matricula) {

		matricula = removerDigitoMatricula(matricula);

		return cadastramentoFaturaRepository.buscarListaClientesDropDown(matricula);
	}

	@Override
	public InformacoesImovelDTO buscarInformacoesImovel(Integer matricula) {

		matricula = removerDigitoMatricula(matricula);

		return cadastramentoFaturaRepository.buscarInformacoesImovel(matricula);

	}

	@Override
	public GenericoWrapperDTO<List<FaturaGridRespostaDTO>> listarFaturasGrid(@Valid CadastramentoListaFaturaFilterDTO filter, Pageable pageable) {

		filter.setMatricula(removerDigitoMatricula(filter.getMatricula()));

		Integer matricula = filter.getMatricula();

		LocalDate refFaturamento = filter.getRefFaturamento();
		Integer referencia = ConverterData.localDateEmIntegerReferenciaFormatoDBNullPossivel(refFaturamento);

		Integer codigoClienteTitular = filter.getCodigoClienteTitular();
		Short codigoSituacaoBaixa = filter.getCodigoSituacaoBaixa();

		List<FaturaGridProjectionDTO> listarFaturasGrid = cadastramentoFaturaRepository.listarFaturasGrid(matricula, referencia, codigoClienteTitular,
				codigoSituacaoBaixa);

		List<FaturaGridRespostaDTO> dtoList = faturaListaGridProjectionDtoMapper.toDto(listarFaturasGrid);

		GenericoWrapperDTO<List<FaturaGridRespostaDTO>> wrapperDTO = Paginacao.paginarCampoUnico(new FaturaGridRespostaComparator(), pageable, dtoList);

		return wrapperDTO;

	}

	private Integer removerDigitoMatricula(Integer matricula) {

		return matricula / 10;
	}

}

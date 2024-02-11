package moduloFaturamento.service.impl.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.mapper.LocalidadeMapper;
import moduloFaturamento.model.common.Localidade;
import moduloFaturamento.repository.common.LocalidadeRepository;
import moduloFaturamento.service.common.LocalidadeService;

@Service
public class LocalidadeServiceImpl implements LocalidadeService {

	@Autowired
	private LocalidadeRepository localidadeRepository;

	@Autowired
	private LocalidadeMapper localidadeMapper;

	@Override
	public LocalidadeDTO buscarLocalidadePorCdCidade(Short cdCidade) {

		Optional<Localidade> localidadeOP = this.localidadeRepository.buscarLocalidadePorCodigoCidade(cdCidade);
		
		return localidadeMapper.toDto(localidadeOP.orElseThrow());
	}

	@Override
	public LocalidadeDTO buscarLocalidadePorMatriculaImovel(Integer matricula) {

		Optional<Localidade> localidadeOP = this.localidadeRepository.buscarLocalidadePorMatriculaImovel(matricula);
		
		return localidadeMapper.toDto(localidadeOP.orElseThrow());
	}
	
	@Override
	public List<LocalidadeDTO> buscarTodasLocalidades() {

		List<Localidade> localidadeList = this.localidadeRepository.findByDataHoraExclusaoIsNullOrderByDcCidade();
		return this.localidadeMapper.toDto(localidadeList);
	}

	@Override
	public List<LocalidadeDTO> buscarLocalidadesQueTemFaturamento() {

		List<Localidade> localidadeList = this.localidadeRepository.buscarLocalidadesQueTemFaturamento();
		return localidadeMapper.toDto(localidadeList);
	}
}

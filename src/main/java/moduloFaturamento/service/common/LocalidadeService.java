package moduloFaturamento.service.common;

import java.util.List;

import moduloFaturamento.dto.LocalidadeDTO;

public interface LocalidadeService {
	
	LocalidadeDTO buscarLocalidadePorCdCidade(Short cdCidade);
	
	LocalidadeDTO buscarLocalidadePorMatriculaImovel(Integer matricula);
	
	List<LocalidadeDTO> buscarTodasLocalidades();
	
	List<LocalidadeDTO> buscarLocalidadesQueTemFaturamento();

}


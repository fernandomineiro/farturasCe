package moduloFaturamento.service.impl.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.repository.GrupoDeConsumoRepository;
import moduloFaturamento.service.common.GrupoDeConsumoService;

@Service
public class GrupoDeConsumoServiceImpl implements GrupoDeConsumoService {

	@Autowired
	private GrupoDeConsumoRepository grupoDeConsumoRepository;

	@Override
	public List<GrupoConsumoDropDownProjectionDTO> buscarListaDropDownGrupoDeConsumo() {

		return grupoDeConsumoRepository.buscarGruposDeConsumoParaPesquisa();
	}

	@Override
	public GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorCodigo(Short grupoDeConsumo) {
	
		return grupoDeConsumoRepository.buscarGruposDeConsumoPorCodigo(grupoDeConsumo);
	}
	
	@Override
	public GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorMatriculaImovel(Integer matricula) {

		return grupoDeConsumoRepository.buscarGruposDeConsumoPorMatriculaImovel(matricula);
	}

}

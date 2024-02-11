package moduloFaturamento.service.common;

import java.util.List;

import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;

public interface GrupoDeConsumoService {
	
	List<GrupoConsumoDropDownProjectionDTO> buscarListaDropDownGrupoDeConsumo();

	GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorMatriculaImovel(Integer matricula);

	GrupoConsumoDropDownProjectionDTO buscarGruposDeConsumoPorCodigo(Short grupoDeConsumo);
}

package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.dto.grupoDeConsumo.TipoFaturamentoDropDownProjectionDTO;
import moduloFaturamento.model.TipoFaturamento;

public interface TipoFaturamentoRepository extends JpaRepository<TipoFaturamento, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM CAD_TP_CONSUMO_FATURADO WHERE id > 0")
	List<TipoFaturamentoDropDownProjectionDTO> buscarTipoFaturamentoDropDown();
}

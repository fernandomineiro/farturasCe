package moduloFaturamento.regras.cronogramaFaturamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.cronogramaFatura.mapper.CronogramaFaturaCicloProjectionMapper;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaCicloProjectionDTO;
import moduloFaturamento.validacoes.common.CommonUtilValidacoes;

@Service
public class CronogramaFaturamentoCicloRegra extends CronogramaFaturamentoRegra {

	@Autowired
	private CommonUtilValidacoes commonUtilValidacoes;

	@Autowired
	private CronogramaFaturaCicloProjectionMapper cronogramaFaturaCicloProjectionMapper;

	public List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(Short cdCidade, Integer referencia) {
		
		commonUtilValidacoes.gerarExcessaoReferenciaInvalidaFormatoDB(referencia);

		List<CronogramaFaturaCicloProjectionDTO> projectionList = super.buscarDropDownCicloProjection(cdCidade, referencia);

		return cronogramaFaturaCicloProjectionMapper.toDto(projectionList);

	}
}

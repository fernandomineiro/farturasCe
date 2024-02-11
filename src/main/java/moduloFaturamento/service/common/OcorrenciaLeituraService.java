package moduloFaturamento.service.common;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.*;
import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OcorrenciaLeituraService {

	OcorrenciaLeituraRespostaConsultaDTO buscarOcorrenciaPorCodigoOcorrencia(Short cdOcorrencia);

	OcorrenciaLeituraDropDownProjectionDTO buscarDropDownOcorrenciaLeituraPorCodigo(Short cdOcorrencia);

	List<OcorrenciaLeituraRespostaDropDownDTO> buscarOcorrenciasPorTipoGravidade(String tipoGravidadeOcorrencia);

	String buscarDescricaoPorCodigoOcorrencia(Short cdOcorrencia);

	GenericoWrapperDTO<List<OcorrenciaLeituraRespostaGridDTO>> buscarOcorrenciaLeituraPorFiltro(OcorrenciaLeituraFilterDTO filter, Pageable pageable);

	OcorrenciaLeituraGravadoRespostaIdentidadeDTO executarFluxoCadastrar(OcorrenciaLeituraCadastrarDTO dto, String token);

	OcorrenciaLeituraGravadoRespostaIdentidadeDTO executarFluxoAtualizar(OcorrenciaLeituraAtualizarDTO dto, String token);

	void executarFluxoDeletar(Short cdOcorencia, String token);
}

package moduloFaturamento.service;

import moduloFaturamento.dto.GenericoWrapperDTO;
import moduloFaturamento.dto.cronogramaFatura.*;
import moduloFaturamento.dto.cronogramaFatura.projection.CronogramaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.model.CronogramaFatura;
import moduloFaturamento.regras.cronogramaFaturamento.spec.CronogramaFaturamentoFaseEnum;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CronogramaFaturamentoService {

	List<CronogramaFatura> buscarDataFaturaPorMatricula(Integer matriculaImovel, Integer ref);

	List<CronogramaFaturaFaseDTO> buscarTodasFasesCronograma();

	List<CronogramaFaturaFaseDTO> buscarFasesCronograma(CronogramaFaturamentoFaseEnum ... fases);

	GenericoWrapperDTO<List<CronogramaFaturaRespostaGridProjectionDTO>> buscarCronogramaPorFiltro(CronogramaFaturaFilterDTO filter, Pageable pageable);

	CronogramaFaturaRespostaConsultaDTO buscarCronogramaPorId(Long id);

	CronogramaFaturaRespostaCalculoDatasDTO retornarDatasCalculadasOperacaoAdicionaSubtraiBaseadoDataLeituraCronograma(LocalDate dataLeitura);

	CronogramaFaturaRespostaCalculoDataVencimentoDTO retonarDataVencimentoCalculadaCronograma(Short cdCidade, Short ciclo, LocalDate dataReferencia);

	CronogramaFaturaRespostaCalculoDataTarifaDTO retornarDataTarifaCalculadaCronograma(CronogramaFaturaDataTarifaDTO dto);

	CronogramaFaturaRespostaPopupCadastroUnicoDTO retornarCondicaoAbrirPopupConfirmarCadastroUnico(Short cdCidade, Short ciclo, LocalDate dataReferencia);

	CronogramaFaturaSalvarRespostaDTO executarFluxoCadastrar(CronogramaFaturaCadastroUnicoDTO dto, String token);

	List<CronogramaFaturaSalvarRespostaDTO> executarFluxoCadastrar(CronogramaFaturaCadastroMultiploDTO dto, String token);

	CronogramaFaturaSalvarRespostaDTO executarFluxoEditar(CronogramaFaturaAtualizarDTO dto, String token);

	void executarFluxoDeletar(Long idCronograma, String token);
}

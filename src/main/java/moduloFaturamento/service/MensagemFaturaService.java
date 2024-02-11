package moduloFaturamento.service;

import java.util.List;

import moduloFaturamento.dto.cronogramaFatura.CronogramaFaturaCicloDropDownDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaAlteracaoDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaCadastroDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaGravadaRespostaDTO;
import moduloFaturamento.dto.mensgemFatura.MensagemFaturaRemocaoDTO;

public interface MensagemFaturaService {

	List<CronogramaFaturaCicloDropDownDTO> buscarDropDownCiclo(Short cdCidade, Integer referencia);

	MensagemFaturaGravadaRespostaDTO buscarMensagemFatura(Short cdCidade, Short ciclo, Integer referencia, String token);
	
	MensagemFaturaGravadaRespostaDTO executarFluxoCadastrar(MensagemFaturaCadastroDTO dto, String token);

	MensagemFaturaGravadaRespostaDTO executarFluxoAtualizar(MensagemFaturaAlteracaoDTO dto, String token);

	void executarFluxoRemover(MensagemFaturaRemocaoDTO dto, String token);

}

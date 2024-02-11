package moduloFaturamento.service;

import moduloFaturamento.dto.MensagemTipoGenericoDTO;
import moduloFaturamento.dto.fatura.projection.FaturaSituacaoAguaEsgotoProjection;
import moduloFaturamento.model.Fatura;

public interface FaturaService {

	MensagemTipoGenericoDTO<Boolean> validarExclusaoImovel(Integer matricula);
	
	Fatura retornarEntidadeUltimoFaturamentoDoImovel(Integer matricula, Integer refFatura);
	
	FaturaSituacaoAguaEsgotoProjection buscarFaturaSituacaoAguaEsgotoProjectionPorMatriculaReferencia(Integer matricula, Integer refFatura); 
}

package moduloFaturamento.infra.service;

import java.util.List;

import moduloFaturamento.rabbitmq.produtores.mensagens.AuditoriaMsgProdutor;

public interface AuditoriaService {

	public void gerarAuditoriaCadastramentoSomenteChave(Long chave, Long idEntidade, String rotina, Long idUsuario);

	public void gerarAuditoriaCadastramento(Long chave, String dadosDepois, Long idEntidade, String rotina, Long idUsuario);

	public void gerarAuditoriaRemocao(Long chave, String dadosAntes, Long idEntidade, String rotina, Long idUsuario);

	public void gerarAuditoriaAlteracao(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario);

	public void gerarAuditoria(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario);

	public void gerarAuditoriaAssociacaoDesassociacao(Long chave, String dadosAntes, String dadosDepois, Long idEntidade, String rotina, Long idUsuario);

	void gerarAuditoriaCadastramentoLote(List<AuditoriaMsgProdutor> auditoriaMsgList);

	void gerarAuditoriaAlteracaoLote(List<AuditoriaMsgProdutor> auditoriaMsgList);

	public String forcarRegistroCamposEmAlteracao(String jsonAntes, String... campos);

}

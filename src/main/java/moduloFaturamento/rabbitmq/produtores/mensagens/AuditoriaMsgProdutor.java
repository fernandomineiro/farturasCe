package moduloFaturamento.rabbitmq.produtores.mensagens;

import moduloFaturamento.util.Constants;

public class AuditoriaMsgProdutor {

	private String dadosAntes;
	private String dadosDepois;
	private Long chave;
	private Long idEntidade;
	private String rotina;
	private Long idUsuario;

	public AuditoriaMsgProdutor(String dadosAntes, String dadosDepois, Long chave, Long idEntidade, String rotina, Long idUsuario) {

		super();
		this.dadosAntes = dadosAntes;
		this.dadosDepois = dadosDepois;
		this.chave = chave;
		this.idEntidade = idEntidade;
		this.rotina = rotina;
		this.idUsuario = idUsuario;
	}

	public String getDadosAntes() {

		return dadosAntes;
	}

	public void setDadosAntes(String dadosAntes) {

		this.dadosAntes = dadosAntes;
	}

	public String getDadosDepois() {

		return dadosDepois;
	}

	public void setDadosDepois(String dadosDepois) {

		this.dadosDepois = dadosDepois;
	}

	public Long getChave() {

		return chave;
	}

	public void setChave(Long chave) {

		this.chave = chave;
	}

	public Long getIdEntidade() {

		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {

		this.idEntidade = idEntidade;
	}

	public String getRotina() {

		return rotina;
	}

	public void setRotina(String rotina) {

		this.rotina = rotina;
	}

	public Long getIdUsuario() {

		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {

		this.idUsuario = idUsuario;
	}

	public static AuditoriaMsgProdutor fabricarCriacao(Long chave, String jsonFaturaDepois, Long idEntidade, String rotina, Long idUsuario) {

		return new AuditoriaMsgProdutor(Constants.EMPTY_STRING, jsonFaturaDepois, chave, idEntidade, rotina, idUsuario);
	}

	public static AuditoriaMsgProdutor fabricarAlteracao(Long chave, String jsonFaturaAntes, String jsonFaturaDepois, Long idEntidade, String rotina,
			Long idUsuario) {

		return new AuditoriaMsgProdutor(jsonFaturaAntes, jsonFaturaDepois, chave, idEntidade, rotina, idUsuario);
	}

	public static AuditoriaMsgProdutor fabricarExclusao(Long chave, String jsonFaturaAntes, Long idEntidade, String rotina, Long idUsuario) {

		return new AuditoriaMsgProdutor(jsonFaturaAntes, Constants.EMPTY_STRING, chave, idEntidade, rotina, idUsuario);
	}

}

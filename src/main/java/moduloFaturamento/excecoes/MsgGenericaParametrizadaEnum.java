package moduloFaturamento.excecoes;

public enum MsgGenericaParametrizadaEnum {

	TAMANHO_CAMPO_INVALIDO("Dado com quantidade de digitos inválido"),
	DADO_COM_INFORMACAO_INVALIDA("Dado com valor especificado inválido"),
	DADO_OBRIGATORIO_NAO_INFORMADO("Não foi informado todos parametros obrigatorios"),
	DIAS_VENCIMENTO_CICLODIA_JA_EXISTE("Dia já existe no CICLO!"),
	DIAS_VENCIMENTO_CICLODIA_NAO_EXISTE("Dia não existe no CICLO!"),
	SERVICO_INDISPONIVEL("serviço indiponível"),
	
	CAPTCHA_INVALIDO("Captcha Inválido");

	private String message;

	MsgGenericaParametrizadaEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getClientCode() {
		return this.toString().toLowerCase();
	}
}



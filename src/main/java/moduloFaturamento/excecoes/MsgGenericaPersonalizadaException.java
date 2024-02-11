package moduloFaturamento.excecoes;

import org.springframework.http.HttpStatus;

public class MsgGenericaPersonalizadaException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	private final String clienteCodeAgencia;
	private final String mensagemParaAgencia;

	public MsgGenericaPersonalizadaException(String mensagem){
		super(mensagem);
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.clienteCodeAgencia =  MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getClientCode();
		this.mensagemParaAgencia = MsgGenericaParametrizadaEnum.SERVICO_INDISPONIVEL.getMessage();
	}

	public MsgGenericaPersonalizadaException(String mensagem, HttpStatus httpStatus){
		super(mensagem);
		this.httpStatus = httpStatus;
		this.clienteCodeAgencia =  null;
		this.mensagemParaAgencia =  null;
	}

	public MsgGenericaPersonalizadaException(String mensagem, HttpStatus httpStatus, String clienteCodeAgencia, String mensagemParaAgencia){
		super(mensagemParaAgencia);
		this.httpStatus = httpStatus;
		this.clienteCodeAgencia =  clienteCodeAgencia.toLowerCase();
		this.mensagemParaAgencia = mensagemParaAgencia;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getClienteCodeAgencia() {
		return clienteCodeAgencia;
	}

	public String getMensagemParaAgencia() {
		return mensagemParaAgencia;
	}
}

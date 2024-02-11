package moduloFaturamento.excecoes;


import java.time.LocalDateTime;

public class MsgGenericaParametrizadaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6664152713289138491L;
	
	private String clientCode;
	private String message;
	
	private Integer statusCode;

	private	String  path;

	private LocalDateTime timestamp = LocalDateTime.now();

	public MsgGenericaParametrizadaException(MsgGenericaParametrizadaEnum mensagemEnum) {
		this.clientCode = mensagemEnum.getClientCode();
		this.message = mensagemEnum.getMessage();
	}

	public String getClientCode() {
		return clientCode;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public MsgGenericaParametrizadaException setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public String getPath() {
		return path;
	}

	public MsgGenericaParametrizadaException setPath(String path) {
		this.path = path;
		return this;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

}


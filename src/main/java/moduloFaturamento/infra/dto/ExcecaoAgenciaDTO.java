package moduloFaturamento.infra.dto;

import java.time.LocalDateTime;

public class ExcecaoAgenciaDTO {

	//errors:[]
	
	private String clientCode;
	private String message;
	
	private Integer statusCode;
	private	String  path;

	private LocalDateTime timestamp = LocalDateTime.now();
	
	

	public ExcecaoAgenciaDTO(String clientCode, String message, Integer statusCode, String path) {
		this.clientCode = clientCode;
		this.message = message;
		this.statusCode = statusCode;
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
}

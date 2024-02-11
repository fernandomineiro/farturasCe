package moduloFaturamento.autenticacao;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtTokenAgenciaProvider {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	public Long buscarIdUsuarioAgencia(String token) {

		token = unpack(token);

		try {
			return mapper.readTree(token).findValue("idUsuarioAgencia").asLong();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Long buscarIdUsuarioSiscom(String token) {

		token = unpack(token);

		try {
			return mapper.readTree(token).findValue("idUsuarioSiscom").asLong();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Integer buscarIdClienteSiscom(String token) {

		token = unpack(token);

		try {
			return mapper.readTree(token).findValue("idClienteSiscom").asInt();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private String unpack(String token) {

		String parts[] = token.split("\\.");
		byte[] decodedBytes = Base64.getDecoder().decode(parts[1]);
		return new String(decodedBytes);
	}

}

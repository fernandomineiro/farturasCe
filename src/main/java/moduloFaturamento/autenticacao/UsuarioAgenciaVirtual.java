package moduloFaturamento.autenticacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAgenciaVirtual {

	@Value("${agencia.login}")
    private String login;
	
	@Value("${agencia.id}")
	private Long idUsuario;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}

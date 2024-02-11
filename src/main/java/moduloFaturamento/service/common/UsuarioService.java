package moduloFaturamento.service.common;

import java.util.List;

import moduloFaturamento.dto.UsuarioDTO;

public interface UsuarioService {

	public List<UsuarioDTO> buscarTodos();
	
	public UsuarioDTO buscarLogin(String login);
	
}

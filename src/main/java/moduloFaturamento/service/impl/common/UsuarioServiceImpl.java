package moduloFaturamento.service.impl.common;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moduloFaturamento.dto.UsuarioDTO;
import moduloFaturamento.excecoes.MsgGenericaPersonalizadaException;
import moduloFaturamento.mapper.definition.EntityMapper;
import moduloFaturamento.model.common.Usuario;
import moduloFaturamento.repository.common.UsuarioRepository;
import moduloFaturamento.service.common.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioMapperTemp usuarioMapper;

	@Override
	public List<UsuarioDTO> buscarTodos() {
		return usuarioMapper.toDto(usuarioRepository.findByStatusRegOrderByLogin("A"));
	}

	@Override
	public UsuarioDTO buscarLogin(String login) {
		Optional<Usuario> usuario = usuarioRepository.findByLoginAndStatusRegOrderByLogin(login, "A");
		if (usuario.isEmpty())
			throw new MsgGenericaPersonalizadaException("Login não existe");
		else
			return usuarioMapper.toDto(usuario.get());
	}
	
	@Mapper(componentModel = "spring")
	static interface UsuarioMapperTemp extends EntityMapper<UsuarioDTO, Usuario> {
		
	}
}




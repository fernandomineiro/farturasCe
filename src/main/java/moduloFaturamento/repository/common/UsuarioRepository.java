package moduloFaturamento.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.common.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

	List<Usuario> findByStatusRegOrderByLogin(String status);
	
	Optional<Usuario> findByLoginAndStatusRegOrderByLogin(String login,String status);
	
}

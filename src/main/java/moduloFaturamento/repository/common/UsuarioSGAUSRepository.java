package moduloFaturamento.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.common.UsuarioSGAUS;

@Repository
public interface UsuarioSGAUSRepository extends JpaRepository<UsuarioSGAUS, String> {

    UsuarioSGAUS findByIdUsuario(String idUsuario);
}

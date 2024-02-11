package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.ArrecadacaoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrecadacaoImovelRepository extends JpaRepository<ArrecadacaoImovel, Integer> {

	ArrecadacaoImovel findByMatriculaImovel(Integer matriculaImovel);

}

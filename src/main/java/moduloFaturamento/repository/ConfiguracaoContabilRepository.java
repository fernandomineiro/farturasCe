package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.ConfiguracaoContabil;

@Repository
public interface ConfiguracaoContabilRepository extends JpaRepository<ConfiguracaoContabil, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM CTTCF where CD_SERVICO = :cdServico AND CD_EVENTO = :cdEvento	")
	List<ConfiguracaoContabil> buscarPorCdServicoECdEvento(Short cdServico, Short cdEvento);

	@Query(nativeQuery = true, value = " SELECT * FROM CTTCF where  CD_EVENTO = 10 AND CD_SERVICO = :cdServico")
	List<ConfiguracaoContabil> findByCdServicoAndCdEventoAndDcServico(@Param("cdServico") Short cdServico );
}

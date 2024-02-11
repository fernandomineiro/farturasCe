package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.TipoServicoOcorrenciaLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicoOcorrenciaLeituraRepository extends JpaRepository<TipoServicoOcorrenciaLeitura, String> {

}

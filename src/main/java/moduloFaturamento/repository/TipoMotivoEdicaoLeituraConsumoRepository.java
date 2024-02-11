package moduloFaturamento.repository;

import moduloFaturamento.model.TipoMotivoEdicaoLeituraConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMotivoEdicaoLeituraConsumoRepository extends JpaRepository<TipoMotivoEdicaoLeituraConsumo, Short> {
}

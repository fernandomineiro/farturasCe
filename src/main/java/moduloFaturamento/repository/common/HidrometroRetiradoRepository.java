package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.HidrometroRetirado;
import moduloFaturamento.model.common.IdHidrometroRetirado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HidrometroRetiradoRepository extends JpaRepository<HidrometroRetirado, IdHidrometroRetirado> {

    @Query(value = "SELECT TOP 1 * FROM HITRE WHERE MATRICULA_IMOVEL = :matriculaImovel ORDER BY DT_RETIRADA DESC", nativeQuery = true)
    HidrometroRetirado buscarUltimoHidrometroRetiradoPelaMatriculaImovel(Integer matriculaImovel);

    List<HidrometroRetirado> findByIdHidrometroRetirado_MatriculaImovel(Integer matriculaImovel);

    @Query(value = "SELECT TOP 1 * FROM HITRE WHERE MATRICULA_IMOVEL = :matriculaImovel " +
            "AND (DT_INSTALACAO < :periodoLimiteMinimo OR DT_INSTALACAO > :periodoLimiteMinimo ) " +
            "AND (DT_RETIRADA < :periodoLimiteMaximo OR DT_RETIRADA > :periodoLimiteMaximo )", nativeQuery = true)
    HidrometroRetirado buscarSeHouveInstalacaoHidrometroDentroDeUmPeriodoDeTempo(Integer matriculaImovel, Integer periodoLimiteMinimo, Integer periodoLimiteMaximo);


    @Query(value = "SELECT TOP 1 * FROM HITRE WHERE MATRICULA_IMOVEL = :matriculaImovel AND :dataLeitura BETWEEN DT_INSTALACAO AND DT_RETIRADA", nativeQuery = true)
    HidrometroRetirado buscarSeHouveInstalacaoHidrometroDentroDeUmPeriodoDeTempo(Integer matriculaImovel, Integer dataLeitura);
}

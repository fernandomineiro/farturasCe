package moduloFaturamento.repository;

import moduloFaturamento.model.common.HidrometroInstalado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HidrometroInstaladoRepository extends JpaRepository<HidrometroInstalado, Integer> {

    @Query(value = "SELECT TOP 1 * FROM HITIN WHERE MATRICULA_IMOVEL = :matriculaImovel ORDER BY ID DESC", nativeQuery = true)
    HidrometroInstalado buscarUltimoHidrometroInstaladoPelaMatriculaImovel(Integer matriculaImovel);

    @Query(value = "SELECT TOP 1 * FROM HITIN WHERE MATRICULA_IMOVEL = :matriculaImovel AND DT_INSTALACAO < :dataLeituraAnterior ORDER BY ID DESC", nativeQuery = true)
    HidrometroInstalado buscarSeHidrometroInstaladoOcorreuDepoisDaDataInformada(Integer matriculaImovel, Integer dataLeituraAnterior);



    @Query(value = "SELECT TOP 1 * FROM HITIN WHERE " +
            "        MATRICULA_IMOVEL = :matriculaImovel " +
            "        AND DT_INSTALACAO < :dataReferencia " +
            "        AND (SELECT TOP 1 COUNT(*) FROM HITRE WHERE CD_HIDROMETRO = HITIN.CD_HIDROMETRO AND DT_RETIRADA IS NULL) < 1", nativeQuery = true)
    HidrometroInstalado buscarHidrometroInstalado(Integer matriculaImovel, Integer dataReferencia);


    /*@Query(value = "SELECT TOP 1 * FROM HITIN WHERE " +
            "        MATRICULA_IMOVEL = :matriculaImovel " +
            "        AND (SELECT TOP 1 COUNT(*) FROM HITRE WHERE CD_HIDROMETRO = HITIN.CD_HIDROMETRO AND DT_RETIRADA IS NULL) < 1 ORDER BY ID DESC", nativeQuery = true)
    HidrometroInstalado buscarHidrometroInstalado(Integer matriculaImovel, Integer dataReferencia);*/


}

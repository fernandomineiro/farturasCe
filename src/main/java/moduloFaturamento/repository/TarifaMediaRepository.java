package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaCalcularPorGrupoServicoProjectionDTO;
import moduloFaturamento.dto.tarifaMedia.projection.TarifaMediaProjectionDTO;
import moduloFaturamento.model.TarifaMedia;

@Repository
public interface TarifaMediaRepository extends JpaRepository<TarifaMedia, Long>{

    @Query(nativeQuery = true, value = "SELECT GRUPO_CONSUMO AS idGrupoConsumo,"
                                        + " VALOR_TARIFA AS valorMedio,"
                                        + " MEDIA_AGUA AS mediaAgua,"
                                        + " MEDIA_ESGOTO As mediaEsgoto,"
                                        + " MEDIA_DISPONIBILIDADE AS mediaDisponibilidadeEsgoto"
                                    + " FROM HITTA"
                                    + " WHERE CD_REGIONAL = :cdReggional AND REF_TARIFA_MEDIA = :dataEmInteger"
                                    + " AND (:idGrupoConsumo is null or GRUPO_CONSUMO = :idGrupoConsumo)")
    List<TarifaMediaProjectionDTO> buscarPesquisaTarifaMedia(Short cdReggional, Integer dataEmInteger, Short idGrupoConsumo);

    @Query(nativeQuery = true, value = "SELECT DISTINCT CD_CIDADE FROM FTTSV WHERE CD_CIDADE <> 0")
    List<Short> buscarCidadesConfiguracaoAguaEEsgoto();

    Optional<TarifaMedia> findByIdRegionalAndIdGrupoDeConsumoAndRefTarifaMedia(Short regional, Short grupoDeConsumo, Integer refParaCalculoTarifaMedia);

    @Query(nativeQuery = true, value = "SELECT cidade.CD_REGIONAL AS regional,"
                    + " fatura.GRUPO_CONSUMO AS grupoDeConsumo,"
                    + " COUNT(DISTINCT fatura.MATRICULA_IMOVEL) matriculas,"
                    + " SUM(lancamento.VR_SERVICO) AS somaValores,"
                    + " SUM(lancamento.VR_SERVICO) / COUNT(DISTINCT fatura.MATRICULA_IMOVEL) AS media"
                + " FROM FTTFA fatura"
                + " JOIN FTTLA lancamento ON fatura.ID = lancamento.ID_FATURA"
                + " JOIN CDACI cidade ON fatura.CD_CIDADE = cidade.CD_CIDADE"
                + " WHERE fatura.REF_FATURA = :refParaCalculoTarifaMedia"
                + " AND lancamento.D_C = 'D'"
                + " AND lancamento.CD_SERVICO in :listaServicos"
                + " GROUP BY"
                    + " cidade.CD_REGIONAL,"
                    + " fatura.GRUPO_CONSUMO")
    List<TarifaMediaCalcularPorGrupoServicoProjectionDTO> buscarPorRegionalEGrupoConsumoPorGrupoServico(Integer refParaCalculoTarifaMedia, List<Short> listaServicos);

    Optional<List<TarifaMedia>> findByRefTarifaMedia(Integer refParaCalculoTarifaMedia);

    @Query(nativeQuery = true, value = "SELECT DISTINCT conf.CD_SRV_AGUA FROM FTTSV conf")
    List<Short> listaServicosAgua();

    @Query(nativeQuery = true, value = "SELECT DISTINCT conf.CD_SRV_DISP_ESG FROM FTTSV conf")
    List<Short> listaServicosDisponibilidadeEsgoto();

    @Query(nativeQuery = true, value = "SELECT CD_SRV_ESGT_N_TRAT AS CD_SERVICO FROM FTTSV"
                + " UNION"
                + " SELECT CD_SRV_ESGT_TRAT AS CD_SERVICO FROM FTTSV")
    List<Short> listaServicosEsgoto();

    @Query(nativeQuery = true, value = "SELECT CD_SRV_AGUA AS CD_SERVICO FROM FTTSV"
                + " UNION"
                + " SELECT CD_SRV_DISP_ESG AS CD_SERVICO FROM FTTSV"
                + " UNION" 
                + " SELECT CD_SRV_ESGT_N_TRAT AS CD_SERVICO FROM FTTSV"
                + " UNION"
                + " SELECT CD_SRV_ESGT_TRAT AS CD_SERVICO FROM FTTSV")
List<Short> listaServicosTarifaMedia();
}

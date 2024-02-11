package moduloFaturamento.repository;

import moduloFaturamento.dto.feriado.FeriadoRespostaGridDTO;
import moduloFaturamento.model.Feriado;
import moduloFaturamento.model.IdFeriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, IdFeriado> {
    Optional<Feriado> findByIdAndDataHoraExclusaoIsNull(Integer id);

    Feriado findById(Integer id);

    Optional<Feriado> findByIdFeriado_DtFeriadoAndIdFeriado_CdCidadeAndUnidadeFederativa_IdAndDataHoraExclusaoIsNull(Integer dtFeriado, Short cdCidade, Integer unidadeFederativa);

    @Query(nativeQuery = true, value = "SELECT ID FROM FTAFE WHERE CD_CIDADE = :cdCidade AND DT_FERIADO = :dtFeriado")
    Integer buscarIdFeriadoPorCidadeEDtFeriado(Integer dtFeriado, Short cdCidade);


    @Query(value = "SELECT new moduloFaturamento.dto.feriado.FeriadoRespostaGridDTO(feriado.id,feriado.idFeriado.dtFeriado, feriado.tpFeriado.descricao, feriado.dcFeriado, localidade.dcCidade, tipoUnidadeFederativa.sigla) " +
            "FROM Feriado feriado " +
            "LEFT JOIN Localidade localidade ON feriado.idFeriado.cdCidade = localidade.cdCidade " +
            "LEFT JOIN TipoUnidadeFederativa tipoUnidadeFederativa ON tipoUnidadeFederativa.id = feriado.unidadeFederativa.id " +
            "WHERE " +
            "feriado.idFeriado.dtFeriado BETWEEN :dtFeriadoInicio AND :dtFeriadoFim " +
            "AND (:tipoFeriado is null or feriado.tpFeriado.id = :tipoFeriado) " +
            "AND (:nomeFeriado is null or feriado.dcFeriado like :nomeFeriado) " +
            "AND (:unidadeFederativa is null or feriado.unidadeFederativa.id = :unidadeFederativa) " +
            "AND (:cdLocalidade is null or feriado.idFeriado.cdCidade = :cdLocalidade) " +
            "AND feriado.dataHoraExclusao IS NULL")
    List<FeriadoRespostaGridDTO> buscarListaFeriadosPorFiltro(@Param("dtFeriadoInicio") Integer dtFeriadoInicio, @Param("dtFeriadoFim") Integer dtFeriadoFim,
                                                              @Param("tipoFeriado") Short tipoFeriado, @Param("nomeFeriado") String nomeFeriado,
                                                              @Param("unidadeFederativa") Integer unidadeFederativa, @Param("cdLocalidade") Short cdLocalidade);


    @Query(nativeQuery = true,
            value="SELECT CASE WHEN COUNT(ID) > 0 THEN 'true' ELSE 'false' END FROM FTAFE WHERE DT_FERIADO = :dataFeriado AND (CD_CIDADE = 4000 OR CD_CIDADE = 0) AND DATA_HORA_EXCLUSAO IS NULL")
    Boolean retornarSeDataInformadaEUmFeriadoVitoriaOuNacional(Integer dataFeriado);

    @Query(nativeQuery = true,
        value="SELECT CASE WHEN COUNT(ID) > 0 THEN 'true' ELSE 'false' END FROM FTAFE WHERE DT_FERIADO = :dataFeriado AND (:cdCidade = 4000 OR CD_CIDADE = 0) AND DATA_HORA_EXCLUSAO IS NULL")
    Boolean retornarSeDataInfomadaEhUmFeriadoMunicipalDaCidadeSelecionada(Integer dataFeriado, Integer cdCidade);
}


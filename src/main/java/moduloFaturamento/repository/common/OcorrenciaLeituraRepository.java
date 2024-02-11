package moduloFaturamento.repository.common;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaDropDownDTO;
import moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaGridDTO;
import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;
import moduloFaturamento.model.common.OcorrenciaLeitura;

@Repository
public interface OcorrenciaLeituraRepository extends JpaRepository<OcorrenciaLeitura, Short> {

    Optional<OcorrenciaLeitura> findByCdOcorrenciaAndDataHoraExclusaoIsNull(Short cdOcorrencia);

    OcorrenciaLeitura findByCdOcorrencia(Short cdOcorrencia);

    @Query(value = "SELECT " +
            "new moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaGridDTO(ocorrencia.cdOcorrencia, ocorrencia.dcOcorrencia, tipoOcorrencia.descricao, ocorrencia.leituraVirtual) " +
            "FROM OcorrenciaLeitura ocorrencia " +
            "INNER JOIN TipoServicoOcorrenciaLeitura tipoOcorrencia " +
            "ON tipoOcorrencia.id = ocorrencia.tipoOcorrencia.id " +
            "WHERE (:cdOcorrencia is null or ocorrencia.cdOcorrencia = :cdOcorrencia) " +
            "AND (:descricao is null or ocorrencia.dcOcorrencia like :descricao) " +
            "AND (:idTipoOcorrencia is null or ocorrencia.tipoOcorrencia.id = :idTipoOcorrencia) " +
            "AND (:leituraVirtual is null or ocorrencia.leituraVirtual = :leituraVirtual) " +
            "AND ocorrencia.dataHoraExclusao IS NULL")
    List<OcorrenciaLeituraRespostaGridDTO> buscarListaOcorrenciaPorFiltro(@Param("cdOcorrencia") Short cdOcorrencia,
                                                                          @Param("descricao") String descricao,
                                                                          @Param("idTipoOcorrencia") String idTipoOcorrencia,
                                                                          @Param("leituraVirtual") String leituraVirtual);

    @Query(nativeQuery = true, value="SELECT CD_OCORRENCIA AS cdOcorrencia, DC_OCORRENCIA AS dcOcorrencia FROM FTTOL WHERE CD_OCORRENCIA = :cdOcorrencia")
    OcorrenciaLeituraDropDownProjectionDTO buscarDropDownOcorrenciaLeituraPorCodigo(Short cdOcorrencia);
    
    @Query(nativeQuery = true, value="SELECT CD_OCORRENCIA AS cdOcorrencia, DC_OCORRENCIA AS dcOcorrencia FROM FTTOL WHERE DATA_HORA_EXCLUSAO IS NULL")
    List<OcorrenciaLeituraDropDownProjectionDTO> buscarListaDropDownOcorrenciaLeitura();
    
    @Query(value = "SELECT new moduloFaturamento.dto.ocorrenciaLeitura.OcorrenciaLeituraRespostaDropDownDTO(ocorrencia.cdOcorrencia, ocorrencia.dcOcorrencia) " +
            "FROM OcorrenciaLeitura ocorrencia " +
            "WHERE (:idTipoOcorrencia is null or ocorrencia.tipoOcorrencia.id = :idTipoOcorrencia) " +
            "AND ocorrencia.dataHoraExclusao IS NULL")
    List<OcorrenciaLeituraRespostaDropDownDTO> buscarListaOcorrenciaPorFiltroTipoOcorrencia(@Param("idTipoOcorrencia") String idTipoOcorrencia);

}

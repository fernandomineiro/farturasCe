package moduloFaturamento.repository;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO;
import moduloFaturamento.model.IncentivoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncentivoClienteRepository extends JpaRepository<IncentivoCliente, Integer> {

    Optional<IncentivoCliente> findByIdAndDataHoraExclusaoIsNull(Integer id);

    @Query(value = "SELECT incentivo FROM IncentivoCliente incentivo WHERE incentivo.id = :id")
    IncentivoCliente buscarIncentivoExistentePorId(Integer id);

    @Query(value = "SELECT new moduloFaturamento.dto.incentivoCliente.incentivoClienteParametro.projection.IncentivoClienteParametroRespostaGridProjectionDTO(incentivo.id, incentivo.descricao, " +
            " incentivo.dataInicioVigencia, incentivo.dataFimVigencia, tipoIncentivo.descricao) " +
            "FROM IncentivoCliente incentivo " +
            "LEFT JOIN TipoIncentivoCliente tipoIncentivo ON incentivo.tipoIncentivoCliente.id = tipoIncentivo.id " +
            "WHERE incentivo.dataHoraExclusao IS NULL ")
    List<IncentivoClienteParametroRespostaGridProjectionDTO> buscarListaIncentivoClienteParametro();


    @Query(value = "SELECT incentivo FROM IncentivoCliente incentivo " +
            "WHERE incentivo.tipoIncentivoCliente.id = :tipoIncentivo AND incentivo.dataHoraExclusao IS NULL " +
            "AND (:inicio BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia OR :fim BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia)" )
    Optional<List<IncentivoCliente>> buscarIncentivoPeloSeuTipoEmAbertoCadastro(LocalDateTime inicio, LocalDateTime fim, Integer tipoIncentivo);

    @Query(value = "SELECT incentivo FROM IncentivoCliente incentivo " +
            "WHERE incentivo.tipoIncentivoCliente.id IS NULL AND incentivo.dataHoraExclusao IS NULL " +
            "AND (:inicio BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia OR :fim BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia)" )
    Optional<List<IncentivoCliente>> buscarIncentivoPeloSeuTipoEmAbertoCadastro(LocalDateTime inicio, LocalDateTime fim);


    @Query(value = "SELECT incentivo FROM IncentivoCliente incentivo " +
            "WHERE incentivo.tipoIncentivoCliente.id = :tipoIncentivo AND incentivo.dataHoraExclusao IS NULL " +
            "AND (:inicio BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia OR :fim BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia) " +
            "AND incentivo.id <> :idIncentivoParametro" )
    Optional<List<IncentivoCliente>> buscarIncentivoPeloSeuTipoEmAbertoAtualizar(LocalDateTime inicio, LocalDateTime fim, Integer tipoIncentivo, Integer idIncentivoParametro);

    @Query(value = "SELECT incentivo FROM IncentivoCliente incentivo " +
            "WHERE incentivo.tipoIncentivoCliente.id IS NULL AND incentivo.dataHoraExclusao IS NULL " +
            "AND (:inicio BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia OR :fim BETWEEN incentivo.dataInicioVigencia AND incentivo.dataFimVigencia) " +
            "AND incentivo.id <> :idIncentivoParametro" )
    Optional<List<IncentivoCliente>> buscarIncentivoPeloSeuTipoEmAbertoAtualizar(LocalDateTime inicio, LocalDateTime fim, Integer idIncentivoParametro);

    @Query(nativeQuery = true, value = "SELECT TOP 1 CD_PARAMETRO_INCENTIVO FROM FAT_INCENTIVO_CLIENTE "
        + " WHERE ID_TIPO_INCENTIVO = :tipoDoIncentivo"
	+ " AND DATA_HORA_EXCLUSAO IS NULL"
	+ " AND DT_INI_VIGENCIA <= :diaEHoraDeHoje AND DT_FIM_VIGENCIA >= :diaEHoraDeHoje")
    Optional<Short> buscarIncentivoPorTipoIncentivoJuridico(Short tipoDoIncentivo, LocalDateTime diaEHoraDeHoje);

    @Query(nativeQuery = true, value = "SELECT TOP 1 CD_PARAMETRO_INCENTIVO FROM FAT_INCENTIVO_CLIENTE" 
        + " WHERE DATA_HORA_EXCLUSAO IS NULL"
        + " AND ID_TIPO_INCENTIVO <> 3"
        + " AND DT_INI_VIGENCIA <= :diaEHoraDeHoje AND DT_FIM_VIGENCIA >= :diaEHoraDeHoje"
        + " ORDER BY CD_PARAMETRO_INCENTIVO DESC")
    Optional<Short> buscarIncentivoPorTipoIncentivoGeralEEspecial(LocalDateTime diaEHoraDeHoje);

    @Query(nativeQuery = true, value = "SELECT top 1 CD_PARAMETRO_INCENTIVO FROM FAT_INCENTIVO_CLIENTE"
        + " WHERE ID_TIPO_INCENTIVO = :tipoDoIncentivoJuridico"
        + " AND DATA_HORA_EXCLUSAO IS NOT NULL"
        + " AND DATA_HORA_EXCLUSAO <= :diaEHoraDeHoje"
        + " ORDER BY DATA_HORA_EXCLUSAO DESC")
    Optional<Short> buscarUltimoIncentivoInativoPorTipoIncentivo(Short tipoDoIncentivoJuridico, LocalDateTime diaEHoraDeHoje);

}

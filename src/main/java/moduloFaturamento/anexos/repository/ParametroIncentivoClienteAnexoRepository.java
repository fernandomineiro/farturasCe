package moduloFaturamento.anexos.repository;

import moduloFaturamento.anexos.model.ParametroIncentivoClienteAnexo;
import moduloFaturamento.dto.incentivoCliente.anexo.projection.ParametroIncentivoAnexoRespostaGridProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParametroIncentivoClienteAnexoRepository extends JpaRepository<ParametroIncentivoClienteAnexo, Long> {

    Optional<ParametroIncentivoClienteAnexo> findById(Long id);

    @Query(value = "SELECT anexo FROM ParametroIncentivoClienteAnexo anexo WHERE anexo.id = :id")
    ParametroIncentivoClienteAnexo buscarAnexoExistentePorId(@Param("id") Long id);

    List<ParametroIncentivoClienteAnexo> findByIdParametroIncentivoCliente(Integer idParametroIncentivo);

    @Query(value = "SELECT new moduloFaturamento.dto.incentivoCliente.anexo.projection.ParametroIncentivoAnexoRespostaGridProjectionDTO(a.id, a.idParametroIncentivoCliente," +
            "a.dataHoraInclusao,a.descricao,a.usuario,a.nomeArquivo,a.tamanhoArquivo, a.arquivo) " +
            "FROM ParametroIncentivoClienteAnexo a WHERE a.idParametroIncentivoCliente = :id")
    List<ParametroIncentivoAnexoRespostaGridProjectionDTO> buscarAnexos(@Param("id") Integer id);

    long countByIdParametroIncentivoCliente(Integer idLeitura);
    
}


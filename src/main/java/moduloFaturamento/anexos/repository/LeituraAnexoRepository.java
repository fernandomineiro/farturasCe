package moduloFaturamento.anexos.repository;

import java.util.List;
import java.util.Optional;

import moduloFaturamento.dto.leituraAnexo.projection.LeituraAnexoRespostaGridProjectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.anexos.model.LeituraAnexo;

@Repository
public interface LeituraAnexoRepository extends JpaRepository<LeituraAnexo, Long> {

    Optional<LeituraAnexo> findById(Long id);

    @Query(value = "SELECT anexo FROM LeituraAnexo anexo WHERE anexo.id = :id")
    LeituraAnexo buscarAnexoLeituraExistentePorId(@Param("id") Long id);

    List<LeituraAnexo> findByLeituraId(Long idLeitura);

    @Query(value = "SELECT new moduloFaturamento.dto.leituraAnexo.projection.LeituraAnexoRespostaGridProjectionDTO(a.id, a.leituraId," +
            "a.dataHoraInclusao,a.descricao,a.usuario,a.nomeArquivo,a.tamanhoArquivo) " +
            "FROM LeituraAnexo a WHERE a.leituraId = :idLeitura")
    List<LeituraAnexoRespostaGridProjectionDTO> buscarAnexosDeUmaLeitura(@Param("idLeitura") Long idLeitura);

    long countByLeituraId(Long idLeitura);
    
}


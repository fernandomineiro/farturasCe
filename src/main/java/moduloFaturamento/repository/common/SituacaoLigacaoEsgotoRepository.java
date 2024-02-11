package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoLigacaoEsgotoRepository extends JpaRepository<SituacaoLigacaoEsgoto, Integer> {

    @Query(value = "SELECT situacaoEsgoto FROM SituacaoLigacaoEsgoto situacaoEsgoto " +
            "INNER JOIN IncentivoClienteSituacaoEsgoto incentivoSituacaoEsgoto ON incentivoSituacaoEsgoto.situacaoLigacaoEsgoto.id =  situacaoEsgoto.id " +
            "WHERE incentivoSituacaoEsgoto.incentivoClienteDetalhe.id = :idIncentivoClienteDetalhe")
    List<SituacaoLigacaoEsgoto> buscarSituacaoEsgotoPorIdIncentivoCliente(Integer idIncentivoClienteDetalhe);

    @Query(value = "SELECT situacaoEsgoto.id FROM SituacaoLigacaoEsgoto situacaoEsgoto " +
            "INNER JOIN IncentivoClienteSituacaoEsgoto incentivoSituacaoEsgoto ON incentivoSituacaoEsgoto.situacaoLigacaoEsgoto.id =  situacaoEsgoto.id " +
            "WHERE incentivoSituacaoEsgoto.incentivoClienteDetalhe.id = :idIncentivoClienteDetalhe")
    List<Integer> buscarSomenteCodigoSituacaoEsgotoPorIdIncentivoCliente(Integer idIncentivoClienteDetalhe);

}

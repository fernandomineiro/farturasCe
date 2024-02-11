package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.SituacaoLigacaoAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoLigacaoAguaRepository extends JpaRepository<SituacaoLigacaoAgua, Integer> {


    @Query(value = "SELECT situacaoAgua FROM SituacaoLigacaoAgua situacaoAgua " +
            "INNER JOIN IncentivoClienteSituacaoAgua  incentivoSituacaoAgua ON  incentivoSituacaoAgua.situacaoLigacaoAgua.id =  situacaoAgua.id " +
            "WHERE incentivoSituacaoAgua.incentivoClienteDetalhe.id = :idIncenticoClienteDetalhe")
    List<SituacaoLigacaoAgua> buscarSituacaoAguaPorIdIncentivoCliente(Integer idIncenticoClienteDetalhe);

    @Query(value = "SELECT situacaoAgua.id FROM SituacaoLigacaoAgua situacaoAgua " +
            "INNER JOIN IncentivoClienteSituacaoAgua  incentivoSituacaoAgua ON  incentivoSituacaoAgua.situacaoLigacaoAgua.id =  situacaoAgua.id " +
            "WHERE incentivoSituacaoAgua.incentivoClienteDetalhe.id = :idIncenticoClienteDetalhe")
    List<Integer> buscarSomenteCodigoSituacaoAguaPorIdIncentivoCliente(Integer idIncenticoClienteDetalhe);
}

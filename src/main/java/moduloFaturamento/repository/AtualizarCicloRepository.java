package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.AtualizarCiclo;

@Repository
public interface AtualizarCicloRepository extends JpaRepository<AtualizarCiclo, Integer>{

    @Query(nativeQuery = true, value = "SELECT MATRICULA_IMOVEL FROM CDTCN WHERE SITUACAO = 0")
    List<Integer> buscarMatriculasComSituacaoZero();

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(*) > 2 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END"
                                + " FROM FTTCR WHERE REF_CRONOGRAMA IN :referencias AND CD_CIDADE = :cidade AND CICLO = :ciclo AND FASE IN (0,5) ")
    boolean verificarSeMatriculaPodeTrocarCiclo(Short cidade, Short ciclo, List<Integer> referencias);

    List<AtualizarCiclo> findByMatriculaImovelAndSituacao(Integer matriculaImovel, short situacao);

    @Query(nativeQuery = true, value = "SELECT CICLO_NOVO FROM CDTCN WHERE SITUACAO = 0 AND MATRICULA_IMOVEL = :matriculaImovel")
    Short buscarCicloNaTabelaAtualizarCiclo(Integer matriculaImovel);

    @Query(nativeQuery = true, value = "SELECT * FROM CDTCN WHERE SITUACAO = 0 AND MATRICULA_IMOVEL = :matriculaImovel")
    AtualizarCiclo buscarPorMatriculaESituacaoZero(Integer matriculaImovel);

    AtualizarCiclo findByMatriculaImovelAndDataInclusao(int i, int j);
    
}

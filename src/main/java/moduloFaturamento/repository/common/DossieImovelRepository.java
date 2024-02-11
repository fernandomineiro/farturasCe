package moduloFaturamento.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.model.common.DossieImovel;
import moduloFaturamento.model.common.IdDossieImovel;

@Repository
public interface DossieImovelRepository extends JpaRepository<DossieImovel, IdDossieImovel> {

	@Query(value = "SELECT isnull(max(a.SEQ_ANOTACAO),0)+1 FROM ATTFL a WHERE a.MATRICULA_IMOVEL = :matriculaImovel"
			+ " AND a.DT_ANOTACAO = replace(convert(varchar,getdate(),102),'.','')", nativeQuery = true)
	Short buscarSequenciaPorDataEMatricula(Integer matriculaImovel);
    
}

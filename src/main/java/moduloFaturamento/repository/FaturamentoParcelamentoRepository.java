package moduloFaturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.model.FaturamentoParcelamento;
import moduloFaturamento.model.common.Imovel;

public interface FaturamentoParcelamentoRepository extends JpaRepository<FaturamentoParcelamento,Integer>{

 	List<FaturamentoParcelamento> findByImovel(Imovel imovel);

     @Query(nativeQuery = true, value = "SELECT top 1 * FROM FTTPC WHERE MATRICULA_IMOVEL = :matriculaImovel"
      + " AND CD_CLIENTE = :cdCliente AND DT_ENCERRAMENTO is NULL ORDER by DT_PARCELAMENTO DESC, HORA_PARCELAMENTO DESC ")
    FaturamentoParcelamento buscarParcelamentoDaFaturaAindaNaoPaga(Integer matriculaImovel, Integer cdCliente);
	
}

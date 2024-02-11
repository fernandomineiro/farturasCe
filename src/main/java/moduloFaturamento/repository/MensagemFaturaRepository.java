package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import moduloFaturamento.model.IdMensagemFatura;
import moduloFaturamento.model.MensagemFatura;

public interface MensagemFaturaRepository extends JpaRepository<MensagemFatura, IdMensagemFatura> {

	@Query(nativeQuery = true, value = "SELECT DISTINCT CICLO FROM FTAME WHERE CD_CIDADE = :cdCidade")
	List<Integer> ciclos(Short cdCidade);

	@Query(nativeQuery = true, value = "SELECT DISTINCT CICLO FROM FTAME WHERE CD_CIDADE = :cdCidade AND REF_FATURA = :refFatura")
	List<Integer> ciclos(Short cdCidade, Integer refFatura);

	@Query(nativeQuery = true, value = "SELECT cronograma.FASE FROM FTAME mensagem JOIN FTTCR cronograma ON mensagem.CD_CIDADE = cronograma.CD_CIDADE "
			+ " AND mensagem.CICLO = cronograma.CICLO AND mensagem.REF_FATURA = cronograma.REF_CRONOGRAMA WHERE "
			+ " mensagem.CD_CIDADE = :cdCidade AND mensagem.CICLO = :ciclo AND mensagem.REF_FATURA = :referencia")
	Optional<Short> buscarFaseCronogramaDaMensagem(Short cdCidade, Short ciclo, Integer referencia);

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM FTTCR WHERE " + " REF_CRONOGRAMA = :referencia AND FASE <> 0")
	Integer buscarCronogramasFaseNaoZeroParaReferencia(Integer referencia);

}

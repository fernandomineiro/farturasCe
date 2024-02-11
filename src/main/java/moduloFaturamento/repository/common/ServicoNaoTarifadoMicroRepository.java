package moduloFaturamento.repository.common;

import moduloFaturamento.model.common.ServicoNaoTarifadoMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServicoNaoTarifadoMicroRepository extends JpaRepository<ServicoNaoTarifadoMicro, Integer> {


    @Query(value = "SELECT DISTINCT servicoNaoTarifario.VALOR " +
            "FROM SRV_SERVICO_NAO_TARIFARIO_MICRO servicoNaoTarifarioMicro " +
            "INNER JOIN SRV_SERVICO_NAO_TARIFARIO servicoNaoTarifario " +
            "ON servicoNaoTarifarioMicro.CD_SRV_NT = servicoNaoTarifario.ID " +
            "INNER JOIN  SRV_SERVICO_NAO_TARIFARIO_LOCALIDADE servicoNaoTarifarioLocalidade " +
            "ON  servicoNaoTarifario.ID = servicoNaoTarifarioLocalidade.CD_SRV_NT "
            +
            "WHERE servicoNaoTarifarioMicro.CD_SERVICO = :cdServico AND servicoNaoTarifarioLocalidade.CD_LOCALIDADE = :cdLocalidade " +
            "AND servicoNaoTarifario.DATA_HORA_EXCLUSAO IS NULL", nativeQuery = true)
    List<BigDecimal> buscarValorServicosNaoTarifadoPorCodigoServicoELocalidade(Short cdServico, Short cdLocalidade);
}

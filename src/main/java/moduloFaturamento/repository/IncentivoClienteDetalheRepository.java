package moduloFaturamento.repository;

import moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO;
import moduloFaturamento.model.IncentivoClienteDetalhe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncentivoClienteDetalheRepository extends JpaRepository<IncentivoClienteDetalhe, Integer> {

    Optional<IncentivoClienteDetalhe> findByIdAndDataHoraExclusaoIsNull(Integer integer);

    List<IncentivoClienteDetalhe> findByIncentivoCliente_IdAndDataHoraExclusaoIsNull(Integer integer);

    @Query(value = "SELECT detalhe FROM IncentivoClienteDetalhe detalhe WHERE detalhe.id = :id")
    IncentivoClienteDetalhe buscarIncentivoExistente(Integer id);

    @Query(value = "SELECT new moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe.projection.IncentivoClienteParametroDetalheRespostaGridProjectionDTO(" +
            "detalhe.id, detalhe.descricao, detalhe.entradaInicio, detalhe.entradaFim, detalhe.descontoMultas, detalhe.descontoJuros, detalhe.descontoValorPrincipal, detalhe.numeroMaximoParcelas, " +
            "detalhe.numeroMinimoDebitos, detalhe.valorMinimoDebitos, detalhe.valorMinimoParcela, detalhe.numeroMinimoDiasDesconto, detalhe.numeroMaximoParcelasAgencia, detalhe.valorMaximoDebitoAgencia, " +
            "detalhe.indicativoCorrecaoMonetaria, detalhe.valorCorrecaoMonetaria, detalhe.valorMaximoDebitos) " +
            "FROM IncentivoClienteDetalhe detalhe WHERE detalhe.incentivoCliente.id = :idParametroIncentivo AND detalhe.dataHoraExclusao IS NULL")
    List<IncentivoClienteParametroDetalheRespostaGridProjectionDTO> buscarIncentivoClienteDetalhePorIdIncentivo(@Param("idParametroIncentivo") Integer idParametroIncentivo);

    @Query(nativeQuery = true, value = "SELECT *" 
            + " FROM FAT_INCENTIVO_CLIENTE_DETALHE"
            + " WHERE CD_PARAMETRO_INCENTIVO = :idParametroIncentivo" 
            + " AND DATA_HORA_EXCLUSAO IS NULL "
            + " AND (ENTRADA_FIM  = 0 OR ENTRADA_INI <= :porcentagemEntrada AND ENTRADA_FIM >= :porcentagemEntrada)"
            + " AND DATA_HORA_EXCLUSAO IS NULL"
            + " AND (NUM_MAX_PARCELAS = 0 OR NUM_MAX_PARCELAS >= :numeroParcelas)"
            + " AND (NUM_MIN_DEBITOS = 0 OR NUM_MIN_DEBITOS <= :quantidadeFaturaEmAberto)"
            + " AND (VR_MIN_DEBITOS = 0 OR VR_MIN_DEBITOS <= :valorDaDivida)"
            + " AND (VR_MAX_DEBITOS = 0 OR VR_MAX_DEBITOS >= :valorDaDivida)"
            + " AND (NUM_MIN_DIAS_DESC = 0 OR NUM_MIN_DIAS_DESC <= :numeroDiasDaUltimaFatura)")
    Optional<IncentivoClienteDetalhe> buscarIncentivoClienteDetalhePorIdIncentivoEntidade(Short idParametroIncentivo, BigDecimal porcentagemEntrada, Short numeroParcelas,
        Integer quantidadeFaturaEmAberto, BigDecimal valorDaDivida, short numeroDiasDaUltimaFatura);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN EXISTS ("
        + " SELECT *"
        + " FROM FAT_INCENTIVO_CLIENTE_SITUACAO_ESGOTO esgoto"
        + " INNER JOIN FAT_INCENTIVO_CLIENTE_SITUACAO_AGUA agua ON agua.ID_INCENTIVO_CLIENTE_DETALHE = esgoto.ID_INCENTIVO_CLIENTE_DETALHE" 
        + " INNER JOIN FAT_INCENTIVO_GRUPO_CONSUMO grupo ON grupo.CD_PARAMETRO_DETALHE = agua.ID_INCENTIVO_CLIENTE_DETALHE "
        + " WHERE esgoto.ID_SITUACAO_ESGOTO = :situacaoEsgotoDoImovel"
        + " AND agua.ID_SITUACAO_AGUA = :situacaoAguaDoImovel"
        + " AND grupo.ID_GRUPO_CONSUMO = :grupoDeConsumoDoImovel"
        + " AND grupo.CD_PARAMETRO_DETALHE = :incentivoClienteDetalhe"
    + " )"
    + " THEN CAST(1 AS BIT)"
    + " ELSE CAST(0 AS BIT) END")
    boolean verificarSeIdIncentivoDetalhadoClienteEstaDeAcordoComRegraDeNegocio(Short situacaoEsgotoDoImovel, Short situacaoAguaDoImovel, Short grupoDeConsumoDoImovel, 
        IncentivoClienteDetalhe incentivoClienteDetalhe);

    

}

package moduloFaturamento.repository;

import moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO;
import moduloFaturamento.model.CobrancaServicoFatura;
import moduloFaturamento.model.IdCobrancaServicoFatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CobrancaServicoFaturaRepository extends JpaRepository<CobrancaServicoFatura, IdCobrancaServicoFatura> {


    Optional<CobrancaServicoFatura> findByIdAndDataHoraExclusaoIsNull(Long id);

    CobrancaServicoFatura findById(Long id);

    @Query(value = "SELECT MAX(CD_COBRANCA) FROM CBACB WHERE MATRICULA_IMOVEL = :matriculaImovel AND DATA_HORA_EXCLUSAO IS NULL", nativeQuery = true)
    Short buscarMaiorCdCobrancaPorMatriculaImovel(Integer matriculaImovel);

    @Query(value = "SELECT ID FROM CBACB WHERE MATRICULA_IMOVEL = :matriculaImovel AND CD_COBRANCA = :cdCobranca", nativeQuery = true)
    Long buscarIdCobrancaPorMatriculaECdCobranca(Integer matriculaImovel, Short cdCobranca);

    @Query(value = "" +
            "SELECT new moduloFaturamento.dto.cobrancaFatura.projection.CobrancaFaturaRespostaGridProjectionDTO(" +
                "cobranca.id, cobranca.idCobrancaServicoFatura.cdCobranca, servico.cdServico, servico.dcServico, cobranca.valorServico, cobranca.dataInclusao, cobranca.loginUsuario, " +
                "ss.idSolicitacaoServico.codAtendimento, ss.idSolicitacaoServico.refAtendimento, ss.idSolicitacaoServico.seqSS, " +
                "item.idItemAtendimento.codAtendimento, item.idItemAtendimento.refAtendimento, item.idItemAtendimento.seqAtendimento, cobranca.refFatura) " +
            "FROM CobrancaServicoFatura cobranca " +
            "LEFT JOIN SolicitacaoServico ss " +
            "ON (ss.idSolicitacaoServico.codAtendimento = cobranca.solicitacaoServico.idSolicitacaoServico.codAtendimento " +
            "       AND ss.idSolicitacaoServico.refAtendimento = cobranca.solicitacaoServico.idSolicitacaoServico.refAtendimento " +
            "       AND ss.idSolicitacaoServico.seqSS = cobranca.solicitacaoServico.idSolicitacaoServico.seqSS" +
            "   ) " +
            "LEFT JOIN ItemAtendimento item " +
            "ON (item.idItemAtendimento.codAtendimento = cobranca.itemAtendimento.idItemAtendimento.codAtendimento " +
            "    AND item.idItemAtendimento.refAtendimento = cobranca.itemAtendimento.idItemAtendimento.refAtendimento " +
            "    AND item.idItemAtendimento.seqAtendimento = cobranca.itemAtendimento.idItemAtendimento.seqAtendimento" +
            "   ) " +
            "INNER JOIN Servico servico ON servico.cdServico = cobranca.servico.cdServico " +
            "WHERE " +
            "cobranca.idCobrancaServicoFatura.matriculaImovel = :matriculaImovel " +
            "AND cobranca.dv = :dv " +
            "AND (:codigoServico is null or cobranca.servico.cdServico = :codigoServico) " +
            "AND (:refFaturamento is null or cobranca.refFatura = :refFaturamento) " +
            "AND cobranca.dataHoraExclusao IS NULL")
    List<CobrancaFaturaRespostaGridProjectionDTO> buscarListaFeriadosPorFiltro(@Param(value = "matriculaImovel") Integer matriculaImovel, @Param(value = "dv") Short dv, @Param(value = "codigoServico") Short cdServico,  @Param(value = "refFaturamento") Integer refFaturamento);
}

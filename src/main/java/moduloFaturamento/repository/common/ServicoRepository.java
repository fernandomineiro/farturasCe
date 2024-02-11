package moduloFaturamento.repository.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.credito.projection.CreditoServicosValidosprojectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaServicosValidosProjectionDTO;
import moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO;
import moduloFaturamento.model.common.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Short> {

        @Query(value = "SELECT new moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO(servico.cdServico, servico.dcServico) "
                        +
                        "FROM Servico servico " +
                        "      INNER JOIN ConfiguracaoContabil configuracaoContabil " +
                        "      ON configuracaoContabil.servico.cdServico = servico.cdServico " +
                        "      INNER JOIN CentroCusto centroDeCusto " +
                        "      ON centroDeCusto.localidade.cdCidade = :cdCidade and centroDeCusto.nrContaContabilSap = configuracaoContabil.nrContaCtbSap "
                        +
                        "      where servico.permitoLancamentoEmFaturaDebito = 'S' " +
                        "      AND configuracaoContabil.cdEvento = 10 " +
                        "      AND servico.dataHoraExclusao IS NULL " +
                        "      AND servico.cdServico <> 1421 AND servico.cdServico <> 1405 " +
                        "      ORDER BY servico.cdServico ASC")
        List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownCadastroCobrancaEmFatura(
                        @Param("cdCidade") Short cdCidade);

        @Query(value = "SELECT new moduloFaturamento.dto.servico.projection.ServicoDropDownDescricaoPrefixadoComCodigoDTO(servico.cdServico, servico.dcServico) "
                        +
                        "FROM Servico servico " +
                        "      INNER JOIN ConfiguracaoContabil configuracaoContabil " +
                        "      ON configuracaoContabil.servico.cdServico = servico.cdServico " +
                        "      INNER JOIN CentroCusto centroDeCusto " +
                        "      ON centroDeCusto.localidade.cdCidade = :cdCidade and centroDeCusto.nrContaContabilSap = configuracaoContabil.nrContaCtbSap "
                        +
                        "      where servico.permitoLancamentoEmFaturaDebito = 'S' " +
                        "      AND configuracaoContabil.cdEvento = 10 " +
                        "      AND servico.dataHoraExclusao IS NULL " +
                        "      ORDER BY servico.cdServico ASC ")
        List<ServicoDropDownDescricaoPrefixadoComCodigoDTO> buscarServicosParaDropDownEdicaoCobrancaEmFatura(
                        @Param("cdCidade") Short cdCidade);

        @Query(nativeQuery = true, value = "SELECT CD_SERVICO AS cdServico, DC_SERVICO as descricao"
                        + " FROM SRASE where PERM_FAT_CREDITO_PRX = 'S'")
        List<CreditoServicosValidosprojectionDTO> pesquisarListaServicoComPermissaoLancamentoProxFatura();

        @Query(nativeQuery = true, value = "SELECT CD_SERVICO AS cdServico, DC_SERVICO as dcServico"
                        + " FROM SRASE where PERM_FAT_DEB_AVULSA_CLIENTE = 'S'")
        List<FaturaAvulsaServicosValidosProjectionDTO> buscarServicosComLancamentoADEbitoEmFatura_S();

        @Query(nativeQuery = true, value = "SELECT DC_SERVICO from SRASE WHERE CD_SERVICO = :cdServico")
        String buscarNomeServico(Short cdServico);

        @Query(nativeQuery = true, value = "SELECT NR_CONTA_CTB_SAP FROM CTTCF WHERE CD_SERVICO = :cdServico AND INFORMAR_CCUSTO = 'S' AND CD_EVENTO = 10")
        Long buscarNumeroContaContabilNoEvento10(Short cdServico);

        @Query(nativeQuery = true, value = "SELECT NR_CONTA_CONTABIL_SAP FROM SAP_CENTRO_CUSTOS WHERE CD_CIDADE = :cdCidade AND NR_CONTA_CONTABIL_SAP = :numeroContaContabilSap")
        Optional<Long> buscarSeServicoPodeSerUtilizadoPelaCidade(Short cdCidade, Long numeroContaContabilSap);

        @Query(nativeQuery = true, value = "SELECT CD_CCUSTO_SAP FROM SAP_CENTRO_CUSTOS WHERE CD_CIDADE = :cdCidade AND NR_CONTA_CONTABIL_SAP = :numeroContaContabilSap")
        Optional<String> buscarCdCustoSapPorNumeroContaContabil(@Param("cdCidade") Short cdCidade,
                        @Param("numeroContaContabilSap") Integer numeroContaContabilSap);

        @Query(nativeQuery = true, value = "SELECT NR_CONTA_CONTABIL_SAP FROM SAP_CENTRO_CUSTOS WHERE CD_CIDADE = :cdCidade AND NR_CONTA_CONTABIL_SAP = :numeroContaContabilSap")
        Optional<Long> buscarSeServicoPodeSerUtilizadoPelaCidade(Short cdCidade, Integer numeroContaContabilSap);

        @Query(nativeQuery = true, value = "SELECT DISTINCT CD_SRV_DISP_ESG FROM FTTSV WHERE CD_SRV_DISP_ESG <> 0")
        List<Short> listaDosServicosDeEsgoto();

        @Query(nativeQuery = true, value = "SELECT *FROM SRASE WHERE CD_SERVICO IN (1994, 1996, 1997)")
        List<Servico> buscarServicosParaClienteMarcadoComoReterImpostos();
}

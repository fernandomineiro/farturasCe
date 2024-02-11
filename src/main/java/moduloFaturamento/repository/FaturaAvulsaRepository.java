package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaExibirDadosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaFiltroBaixaProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaLancamentosProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaPesquisaLoteArrecadacaoProjectionDTO;
import moduloFaturamento.dto.faturaAvulsa.projection.FaturaAvulsaPesquisaProjectionDTO;
import moduloFaturamento.model.FaturaAvulsa;
import moduloFaturamento.model.IdFaturaAvulsa;

@Repository
public interface FaturaAvulsaRepository extends JpaRepository<FaturaAvulsa, IdFaturaAvulsa>{

    @Query(nativeQuery = true, value = "SELECT fatAvulsa.CD_CLIENTE AS cdCliente,"
                            + " fatAvulsa.REF_FATURA AS refFatura,"
                            + " fatAvulsa.ORIGEM_FATURA AS origemFatura,"
                            + " fatAvulsa.MATRICULA_IMOVEL AS matriculaImovel,"
                            + " fatAvulsa.VR_FATURA AS valorFatura,"
                            + " fatAvulsa.DT_VENCIMENTO AS dataVencimento,"
                            + " baixa.DESCRICAO AS nomeBaixa,"
                            + " fatAvulsa.REF_BAIXA AS referenciaBaixa,"
                            + " fatAvulsa.DIA_BAIXA AS diaDaBaixa,"
                            + " fatAvulsa.REF_ATENDIMENTO AS refAtendimento,"
                            + " fatAvulsa.CD_ATENDIMENTO AS cdAtendimento,"
                            + " fatAvulsa.SEQ_SS AS seqSS,"
                            + " fatAvulsa.ID AS id"
                        + " FROM FTTEV fatAvulsa"
                        + " INNER JOIN CAD_TP_BAIXA baixa ON baixa.ID = fatAvulsa.TP_BAIXA"  
                        + " WHERE fatAvulsa.CD_CLIENTE = :cdCliente"
                        + " AND (:matriculaImovel is null or fatAvulsa.MATRICULA_IMOVEL = :matriculaImovel)"
                        + " AND (:refFatura is null or fatAvulsa.REF_FATURA = :refFatura)"
                        + " AND (:idBaixa is null or fatAvulsa.TP_BAIXA = :idBaixa)"
                            + " ORDER BY fatAvulsa.REF_FATURA DESC")
    List<FaturaAvulsaPesquisaProjectionDTO> buscarFaturas(Integer cdCliente, @Param("matriculaImovel") Integer matriculaImovel,
            @Param("refFatura")  Integer refFatura, @Param("idBaixa") Short idBaixa);

    @Query(nativeQuery = true, value = "SELECT cliente.CD_CLIENTE AS cdCliente,"
                        + " cliente.DV AS dvCliente,"
                        + " cliente.NOME AS nomeCliente,"
                        + " faturaAvulsa.MATRICULA_IMOVEL AS matriculaImovel,"
                        + " localidade.DC_CIDADE AS nomeLocalidade,"
                        + " faturaAvulsa.REF_FATURA AS refFatura,"
                        + " faturaAvulsa.VR_FATURA AS valorFatura,"
                        + " faturaAvulsa.ORIGEM_FATURA AS origemFatura,"
                        + " faturaAvulsa.DT_VENCIMENTO as dataVencimento,"
                        + " CASE WHEN faturaAvulsa.TP_BAIXA = 1 THEN 'pago' ELSE 'não pago' END AS tipoBaixa,"
                        + " (faturaAvulsa.REF_BAIXA + CAST(faturaAvulsa.DIA_BAIXA AS VARCHAR(2))) AS dataBaixaCompleto,"
                        + " faturaAvulsa.REF_ATENDIMENTO AS refAtendimento,"
                        + " faturaAvulsa.CD_ATENDIMENTO AS codAtendimento,"
                        + " faturaAvulsa.SEQ_SS AS seqSS,"
                        + " faturaAvulsa.MENSAGEM_01 AS mensagem1,"
                        + " faturaAvulsa.MENSAGEM_02 AS mensagem2"
                        + " FROM FTTEV faturaAvulsa"
                        + " INNER JOIN CDACL cliente ON cliente.CD_CLIENTE  = faturaAvulsa.CD_CLIENTE"
                        + " INNER JOIN CDACI localidade ON localidade.CD_CIDADE = faturaAvulsa.CD_CIDADE"
                        + " WHERE faturaAvulsa.ID = :id")
    FaturaAvulsaExibirDadosProjectionDTO buscarDadosFaturaAvulsa(Long id);

    @Query(nativeQuery = true, value = "SELECT servico.CD_SERVICO AS cdServico,"
                        + " servico.DC_SERVICO AS descricaoServico,"
                        + " lancFA.VR_SERVICO AS valorServico"
                        + " FROM FTTEL lancFA"
                        + " INNER JOIN FTTEV fatAvulsa ON lancFA.CD_CLIENTE = fatAvulsa.CD_CLIENTE AND lancFA.REF_FATURA = fatAvulsa.REF_FATURA AND lancFA.ORIGEM_FATURA = fatAvulsa.ORIGEM_FATURA"
                        + " INNER JOIN SRASE servico ON servico.CD_SERVICO = lancFA.CD_SERVICO"
                        + " WHERE fatAvulsa.ID  = :id")
    List<FaturaAvulsaLancamentosProjectionDTO> buscarLancamentoFatutaAvulsa(Long id);

    @Query(nativeQuery = true, value = "SELECT COUNT(ORIGEM_FATURA)+10 FROM FTTEV WHERE CD_CLIENTE = :cdCliente AND REF_FATURA = :dataRefEmInteiro")
    Short buscarUmaNovaOuSequenciaDaOrigemFatura(Integer cdCliente, Integer dataRefEmInteiro);

    @Query(nativeQuery = true, value = "SELECT tab3.CODG_AGENTE_ARRECADADOR AS agenteArrecadador," 
                        + "tab3.CODG_FILIAL AS agenciaArredadora," 
                        + "tab2.DATA_MOVIMENTO AS dataProcedimentoDeArrecadacao," 
                        + "tab2.CODG_ARQUIVO_RECEBIMENTO AS numeroLote"
                        + " FROM   EBL_RA_ITENS_ARQUIVOS_RECEBIMENTOS tab1"
                        + " INNER JOIN EBL_RA_ARQUIVOS_RECEBIMENTOS tab2 ON tab1.CODG_ARQUIVO_RECEBIMENTO = tab2.CODG_ARQUIVO_RECEBIMENTO"
                        + " INNER JOIN EBL_CG_FILIAIS tab3 ON tab3.CODG_FILIAL = tab2.CODG_FILIAL"
                        + " WHERE  tab1.DESC_INSTALACAO_SEM_DIGITO = :cdCliente"
                        + " AND    tab1.NUME_TIPO_FATURA = :origemFatura"
                        + " AND    tab1.NUME_ANO = :ano AND tab1.NUME_MES = :mes ")
    FaturaAvulsaPesquisaLoteArrecadacaoProjectionDTO buscarPesquisaLosteArrecadacao(Integer cdCliente, Short origemFatura, Integer ano, Short mes);

    @Query(nativeQuery = true, value = "SELECT ID AS idBaixa, DESCRICAO AS descricaoBaixa FROM CAD_TP_BAIXA")
    List<FaturaAvulsaFiltroBaixaProjectionDTO> buscarTipoBaixa();

    Optional<FaturaAvulsa> findById(Long id);


    
}

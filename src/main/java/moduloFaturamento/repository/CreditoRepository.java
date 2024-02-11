package moduloFaturamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import moduloFaturamento.dto.credito.projection.CreditoCampoEditarProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoCampoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoLancadoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoParaPesqBancoProjectionDTO;
import moduloFaturamento.dto.credito.projection.CreditoValoresParaLancamentoContabilProjectionDTO;
import moduloFaturamento.model.Credito;
import moduloFaturamento.model.IdCredito;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, IdCredito> {

    @Query(nativeQuery = true, value = "SELECT credito.CD_CREDITO AS cdCredito,"
                                            + " credito.CD_SERVICO AS cdServico,"
                                            + " servico.DC_SERVICO AS nomeServico," 
                                            + " credito.VR_CREDITO AS valorCredito,"
                                            + " credito.DT_PROCE_ARREC AS dataInicio,"
                                            + " credito.VR_SALDO AS saldo,"
                                            + " credito.ID_USUARIO AS usuario,"
                                            + " credito.REF_ENCERRAMENTO AS encerramento,"

                                            + " credito.REF_ATENDIMENTO AS refAtendimento,"
                                            + " credito.CD_ATENDIMENTO AS cdAtendimento,"
                                            + " credito.SEQ_SS AS seqSS,"

                                            + " credito.ID AS id,"
                                            + " credito.CD_PARCELAMENTO AS cdParcelamento,"
                                            + " credito.NUMERO_PARCELA AS numeroParcela,"
                                            + " credito.ORIGEM_FATURA AS origemFatura,"
                                            + " credito.REF_FATURA AS refFatura"
                                        
                                        + " FROM CBACR credito"
                                        + " JOIN SRASE servico ON credito.CD_SERVICO = servico.CD_SERVICO"
                                        + " WHERE credito.MATRICULA_IMOVEL = :matricula")
    List<CreditoParaPesqBancoProjectionDTO> pesquisarMatriculaNoCredito(Integer matricula);

    @Query(nativeQuery = true, value = "SELECT top 1 credito.MATRICULA_IMOVEL"
                                        + " FROM CBACR credito"
                                        + " WHERE credito.MATRICULA_IMOVEL = :matricula"
                                        + " AND credito.CD_CREDITO = :credito")
    Integer verificarSeExisteCredito(Integer matricula, short credito);

    @Query(nativeQuery = true, value = "SELECT credito.CD_SERVICO AS cdServico,"
                                        + " credito.MATRICULA_IMOVEL AS matricula," 
                                        + " credito.DV AS dv,"
                                        + " credito.VR_CREDITO AS valorCredito, credito.VR_SALDO AS saldo,"
                                            + " servico.DC_SERVICO AS descServico," 
                                            + " credito.CD_CREDITO AS cdCredito,"
                                            + " credito.REF_ENCERRAMENTO AS encerramento,"

                                            + " credito.REF_ATENDIMENTO AS refAtendimento,"                                            
                                            + " credito.CD_ATENDIMENTO AS cdAtendimento,"                                            
                                            + " credito.SEQ_SS AS seqSS,"

                                            + " credito.CD_PARCELAMENTO AS cdParcelamento,"
                                            + " credito.NUMERO_PARCELA AS numeroParcela,"
                                            + " credito.ORIGEM_FATURA AS origemFatura,"
                                            + " credito.REF_FATURA AS refFatura"

                                        + " FROM CBACR credito"
                                        + " JOIN SRASE servico ON credito.CD_SERVICO = servico.CD_SERVICO"
                                        + " WHERE credito.MATRICULA_IMOVEL = :matricula"
                                        + " AND credito.CD_CREDITO = :credito")
    CreditoCampoProjectionDTO pesquisarCreditoCampo(Integer matricula, short credito);

    @Query(nativeQuery = true, value = "SELECT creditoLancado.REF_CREDITO AS data, creditoLancado.VR_CREDITO AS valorLancado"
                                        + " FROM CBACR credito"
                                        + " JOIN CBTCF creditoLancado ON credito.MATRICULA_IMOVEL = creditoLancado.MATRICULA_IMOVEL AND credito.CD_CREDITO = creditoLancado.CD_CREDITO"
                                        + " WHERE creditoLancado.MATRICULA_IMOVEL = :matricula"
                                        + " AND creditoLancado.CD_CREDITO = :credito")
    List<CreditoLancadoProjectionDTO> pesquisarCreditoLancado(Integer matricula, short credito);

    @Query(nativeQuery = true, value = "SELECT COUNT(CD_CREDITO)+1 FROM CBACR"
                                        + " WHERE MATRICULA_IMOVEL = :matricual")
    short buscarProximaSequenciaCredito(Integer matricual);

    @Query(nativeQuery = true, value = "SELECT credito.MATRICULA_IMOVEL AS matricula,"
                                        + " credito.DV AS dv,"
                                        + " credito.CD_CREDITO AS cdCredito,"
                                        + " credito.CD_SERVICO AS cdServico,"
                                        + " servico.DC_SERVICO AS descricaoServico,"
                                        + " credito.VR_CREDITO AS valorCredito,"                                        
                                        + " credito.VR_SALDO AS valorSaldo,"
                                        + " credito.REF_ENCERRAMENTO AS encerramento,"
                                        + " credito.REF_ATENDIMENTO AS refAtendimento,"
                                        + " credito.CD_ATENDIMENTO AS codAtendimento,"
                                        + " credito.SEQ_SS AS seqSS,"
                                        + " credito.ID_USUARIO AS usuario,"

                                        + " credito.ID AS id,"
                                        + " credito.CD_PARCELAMENTO AS cdParcelamento,"
                                        + " credito.NUMERO_PARCELA AS numeroParcela,"
                                        + " credito.ORIGEM_FATURA AS origemFatura,"
                                        + " credito.REF_FATURA AS referenciaFatura,"

                                        + " credito.DC_ANOTACAO AS justificativa"
                                        + " FROM CBACR credito"
                                        + " JOIN SRASE servico ON credito.CD_SERVICO = servico.CD_SERVICO"
                                        + " WHERE credito.ID = :id")
    CreditoCampoEditarProjectionDTO pesquisarCreditoParaTelaEditar(Long id);

    @Query(nativeQuery = true, value = "SELECT top 1 creditoLancado.REF_CREDITO" 
                                        + " FROM CBTCF creditoLancado"
                                        + " WHERE creditoLancado.MATRICULA_IMOVEL = :matricula AND creditoLancado.CD_CREDITO = :sequenciaCredito"
                                        + " ORDER BY creditoLancado.REF_CREDITO DESC")
    Integer pesquisaSeExisteLancamentoNoUltimoCredito(Integer matricula, short sequenciaCredito);

    @Query(nativeQuery = true, value = "SELECT NR_CONTA_CTB_SAP AS nrConta, D_C_CTB_SAP AS ctbSap"
                                        + " FROM CTTCF where CD_SERVICO = :servico AND CD_EVENTO = :evento AND D_C_SERVICO = :debitoOuCredito")
    List<CreditoValoresParaLancamentoContabilProjectionDTO> buscarNumeroContaEContabilSapParaLancamentoContabil(Short servico, Short evento, String debitoOuCredito);

    @Query(nativeQuery = true, value = "SELECT cc.CD_CCUSTO_SAP"
                                        + " FROM CTTCF confContabil"
                                        + " JOIN SAP_CENTRO_CUSTOS cc ON cc.NR_CONTA_CONTABIL_SAP = confContabil.NR_CONTA_CTB_SAP AND confContabil.INFORMAR_CCUSTO = 'S'"
                                        + " WHERE cc.CD_CIDADE = :cdCidade"
                                        + " AND confContabil.NR_CONTA_CTB_SAP = :nrConta"
                                        + " AND confContabil.CD_SERVICO = :servico"
                                        + " AND confContabil.CD_EVENTO = :evento")
    String verificarSeTabelaCTTCFPossuei_S_ParaFornecerCentroCusto(Integer cdCidade, Short servico, Short evento, Integer nrConta);

    Optional<Credito> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT ID FROM CBACR WHERE MATRICULA_IMOVEL = :matricula AND CD_CREDITO = :credito")
    Long buscarIDCredito(Integer matricula, short credito);

    Optional<List<Credito>> findByIdCreditoMatriculaAndCdClienteAndRefEncerramento(Integer matriculaImovel, Integer cdCliente, Integer refEncerramento);

    @Query(nativeQuery = true, value = "SELECT ID FROM CBACR WHERE MATRICULA_IMOVEL = :matriculaImovel AND CD_CLIENTE = :cdCliente AND REF_ENCERRAMENTO = :refEncerramento")
    List<Long> buscarListaDeIdDaMattriculaEClienteComSaldo(Integer matriculaImovel, Integer cdCliente, Integer refEncerramento);
    
}

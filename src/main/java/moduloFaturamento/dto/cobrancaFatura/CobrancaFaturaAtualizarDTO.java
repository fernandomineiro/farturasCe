package moduloFaturamento.dto.cobrancaFatura;

import moduloFaturamento.regras.cobrancaFatura.spec.CobrancaFaturaTipoNumeroSolicitacaoEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CobrancaFaturaAtualizarDTO {
    @NotNull
    private Long id;
    @NotNull
    private Short codigoServico;
    @NotNull
    private BigDecimal valorServico;
    @NotNull @PastOrPresent
    private LocalDate dataDeExecucao;
    @NotBlank
    @Size(max = 400)
    private String justificativa;
    private LocalDate referenciaFaturar;
    private Integer refSolicitacao;
    private Integer codSolicitacao;
    private Short seqSolicitacao;
    private CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(Short codigoServico) {
        this.codigoServico = codigoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public LocalDate getDataDeExecucao() {
        return dataDeExecucao;
    }

    public void setDataDeExecucao(LocalDate dataDeExecucao) {
        this.dataDeExecucao = dataDeExecucao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDate getReferenciaFaturar() {
        return referenciaFaturar;
    }

    public void setReferenciaFaturar(LocalDate referenciaFaturar) {
        this.referenciaFaturar = referenciaFaturar;
    }

    public Integer getRefSolicitacao() {
        return refSolicitacao;
    }

    public void setRefSolicitacao(Integer refSolicitacao) {
        this.refSolicitacao = refSolicitacao;
    }

    public Integer getCodSolicitacao() {
        return codSolicitacao;
    }

    public void setCodSolicitacao(Integer codSolicitacao) {
        this.codSolicitacao = codSolicitacao;
    }

    public Short getSeqSolicitacao() {
        return seqSolicitacao;
    }

    public void setSeqSolicitacao(Short seqSolicitacao) {
        this.seqSolicitacao = seqSolicitacao;
    }

    public CobrancaFaturaTipoNumeroSolicitacaoEnum getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(CobrancaFaturaTipoNumeroSolicitacaoEnum tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }
}

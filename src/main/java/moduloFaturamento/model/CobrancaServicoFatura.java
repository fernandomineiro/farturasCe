package moduloFaturamento.model;

import moduloFaturamento.model.common.ItemAtendimento;
import moduloFaturamento.model.common.Servico;
import moduloFaturamento.model.common.SolicitacaoServico;
import moduloFaturamento.util.ConverterData;
import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CBACB")
public class CobrancaServicoFatura {

    @EmbeddedId
    private IdCobrancaServicoFatura idCobrancaServicoFatura;
    @Column(name = "AGENCIA_ARRECADADORA") @JsonCesanNotSerializable
    private Short agenciaArrecadadora;
    @Column(name = "AGENTE_ARRECADADOR") @JsonCesanNotSerializable
    private Short agenteArrecadador;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SERVICO", referencedColumnName = "CD_SERVICO")
    private Servico servico;
    @Column(name = "CD_ATENDIMENTO") @JsonCesanNotSerializable
    private Integer cdAtendimento;
    @Column(name = "DT_EXECUCAO")
    private Integer dataExecucao;
    @Column(name = "DT_INCLUSAO")
    private Integer dataInclusao;
    @Column(name = "DT_PROCE_ARREC") @JsonCesanNotSerializable
    private Integer dataProcessamentoArrecadacao;
    @Column(name = "DV")
    private Short dv;
    @Column(name = "ID_USUARIO")  @Size(max = 30)
    private String loginUsuario;
    @Column(name = "MAINT")  @Size(max = 1) @JsonCesanNotSerializable
    private String maint;
    @Column(name = "MAQ_REG") @JsonCesanNotSerializable
    private Short maqReg;
    @Column(name = "NU_LOTE_ARREC") @JsonCesanNotSerializable
    private Integer numeroLoteArrecado;
    @Column(name = "NUM_PARCELA") @JsonCesanNotSerializable
    private Short numParcela;
    @Column(name = "QTD_PARCELA") @JsonCesanNotSerializable
    private Short quantidadeParcela;
    @Column(name = "REF_ATENDIMENTO") @JsonCesanNotSerializable
    private Integer refAtendimento;
    @Column(name = "REF_FATURA")
    private Integer refFatura;
    @Column(name = "REF_FATURAR")
    private Integer refFaturar;
    @Column(name = "SEQ_SS") @JsonCesanNotSerializable
    private Short seqSS;
    @Column(name = "TIPO_AGENTE") @JsonCesanNotSerializable
    private Short tipoAgente;
    @Column(name = "VR_SERVICO")  @Digits(integer=10, fraction=2)
    private BigDecimal valorServico;
    @Column(name = "VR_TOTAL")  @Digits(integer=10, fraction=2)
    private BigDecimal valorTotal;
    @Column(name = "REF_COBRANCA")
    private Integer refCobranca;
    @Column(name = "ORIGEM_COBRANCA") @JsonCesanNotSerializable
    private Integer origemCobranca;
    @Column(name = "CS_BAIXA_ENVIO")  @Size(max = 1) @JsonCesanNotSerializable
    private String csBaixaEnvio;
    @Column(name = "VR_SUBSIDIO")  @Digits(integer=10, fraction=2) @JsonCesanNotSerializable
    private BigDecimal valorSubsidio;
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name = "SS_CD_ATENDIMENTO", referencedColumnName="CD_ATENDIMENTO"),
            @JoinColumn(name = "SS_REF_ATENDIMENTO", referencedColumnName="REF_ATENDIMENTO"),
            @JoinColumn(name = "SS_SEQ", referencedColumnName="SEQ_SS")
    })
    private SolicitacaoServico solicitacaoServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name = "ITEM_CD_ATENDIMENTO", referencedColumnName="CD_ATENDIMENTO"),
            @JoinColumn(name = "ITEM_REF_ATENDIMENTO", referencedColumnName="REF_ATENDIMENTO"),
            @JoinColumn(name = "ITEM_SEQ", referencedColumnName="SEQ_ATEND")
    })
    private ItemAtendimento itemAtendimento;
    @Column(name = "DATA_HORA_EXClUSAO")
    private LocalDateTime dataHoraExclusao;

    public CobrancaServicoFatura() {
        this.agenciaArrecadadora = 0;
        this.agenteArrecadador = 0;
        this.dataInclusao = ConverterData.localDateEmIntegerDataFormatoDB(LocalDate.now());
        this.dataProcessamentoArrecadacao = 0;
        this.maint = "A";
        this.maqReg = 0;
        this.numeroLoteArrecado = 0;
        this.numParcela = 0;
        this.quantidadeParcela = 0;
        this.origemCobranca = 0;
        this.csBaixaEnvio = "";
        this.valorSubsidio  = BigDecimal.ZERO;
        this.cdAtendimento = 0;
        this.refAtendimento = 0;
        this.seqSS = 0;
        this.refCobranca = 0;
        this.refFatura = 0;
        this.tipoAgente = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    public IdCobrancaServicoFatura getIdCobrancaServicoFatura() {
        return idCobrancaServicoFatura;
    }

    public void setIdCobrancaServicoFatura(IdCobrancaServicoFatura idCobrancaServicoFatura) {
        this.idCobrancaServicoFatura = idCobrancaServicoFatura;
    }

    public Short getAgenciaArrecadadora() {
        return agenciaArrecadadora;
    }

    public void setAgenciaArrecadadora(Short agenciaArrecadadora) {
        this.agenciaArrecadadora = agenciaArrecadadora;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Integer getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Integer getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Integer dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public Integer getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Integer dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Integer getDataProcessamentoArrecadacao() {
        return dataProcessamentoArrecadacao;
    }

    public void setDataProcessamentoArrecadacao(Integer dataProcessamentoArrecadacao) {
        this.dataProcessamentoArrecadacao = dataProcessamentoArrecadacao;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getMaint() {
        return maint;
    }

    public void setMaint(String maint) {
        this.maint = maint;
    }

    public Short getMaqReg() {
        return maqReg;
    }

    public void setMaqReg(Short maqReg) {
        this.maqReg = maqReg;
    }

    public Integer getNumeroLoteArrecado() {
        return numeroLoteArrecado;
    }

    public void setNumeroLoteArrecado(Integer numeroLoteArrecado) {
        this.numeroLoteArrecado = numeroLoteArrecado;
    }

    public Short getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Short numParcela) {
        this.numParcela = numParcela;
    }

    public Short getQuantidadeParcela() {
        return quantidadeParcela;
    }

    public void setQuantidadeParcela(Short quantidadeParcela) {
        this.quantidadeParcela = quantidadeParcela;
    }

    public Integer getRefAtendimento() {
        return refAtendimento;
    }

    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    public Integer getRefFaturar() {
        return refFaturar;
    }

    public void setRefFaturar(Integer refFaturar) {
        this.refFaturar = refFaturar;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }

    public Short getTipoAgente() {
        return tipoAgente;
    }

    public void setTipoAgente(Short tipoAgente) {
        this.tipoAgente = tipoAgente;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getRefCobranca() {
        return refCobranca;
    }

    public void setRefCobranca(Integer refCobranca) {
        this.refCobranca = refCobranca;
    }

    public Integer getOrigemCobranca() {
        return origemCobranca;
    }

    public void setOrigemCobranca(Integer origemCobranca) {
        this.origemCobranca = origemCobranca;
    }

    public String getCsBaixaEnvio() {
        return csBaixaEnvio;
    }

    public void setCsBaixaEnvio(String csBaixaEnvio) {
        this.csBaixaEnvio = csBaixaEnvio;
    }

    public BigDecimal getValorSubsidio() {
        return valorSubsidio;
    }

    public void setValorSubsidio(BigDecimal valorSubsidio) {
        this.valorSubsidio = valorSubsidio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SolicitacaoServico getSolicitacaoServico() {
        return solicitacaoServico;
    }

    public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
        this.solicitacaoServico = solicitacaoServico;
    }

    public ItemAtendimento getItemAtendimento() {
        return itemAtendimento;
    }

    public void setItemAtendimento(ItemAtendimento itemAtendimento) {
        this.itemAtendimento = itemAtendimento;
    }

    public LocalDateTime getDataHoraExclusao() {
        return dataHoraExclusao;
    }

    public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
        this.dataHoraExclusao = dataHoraExclusao;
    }

    public Short getAgenteArrecadador() {
        return agenteArrecadador;
    }

    public void setAgenteArrecadador(Short agenteArrecadador) {
        this.agenteArrecadador = agenteArrecadador;
    }
}

package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "CBACR")
public class Credito {

    @EmbeddedId
    private IdCredito idCredito;

    @Column(name = "DV")
    private Short dv;

    @Column(name = "CD_SERVICO")
    private Short cdServico;

    @Column(name = "DC_ANOTACAO", length = 330)
    private String dcAnotacao;
    
    @Column(name = "DT_PROCE_ARREC")
    private Integer data;

    @Column(name = "CD_CLIENTE")
    private Integer cdCliente;

    @Column(name = "ID_USUARIO")
    private String usuario;

    @Column(name = "MAINT", length = 1)
    private Character maint;

    @Column(name = "REF_ENCERRAMENTO")
    private Integer refEncerramento;

    @Column(name = "VR_CREDITO")
    private BigDecimal valorCredito;

    @Column(name = "VR_SALDO")
    private BigDecimal valorSaldo;

    @Column(name = "REF_ATENDIMENTO")
    private Integer refAtendimento;

    @Column(name = "CD_ATENDIMENTO")
    private Integer cdAtendimento;

    @Column(name = "SEQ_SS")
    private Short seqSS;

    @Column(name = "ID", insertable = false, updatable = false)
    private Long id;

    @Column(name = "AGENCIA_ARRECADADORA")
    private Short agenciaArrecadadora;

    @Column(name = "AGENTE_ARRECADADOR")
    private Short agenteArrecadador;

    @Column(name = "MAQ_REG")
    private Short maqReg;

    @Column(name = "NU_LOTE_ARREC")
    private Integer numeroLoteArrecadador;

    @Column(name = "TIPO_AGENTE")
    private Short tipoAgente;

    @Column(name = "CD_COBRANCA")
    private Short cdCobranca;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name = "CD_PARCELAMENTO", referencedColumnName="CD_PARCELAMENTO", nullable = true),
            @JoinColumn(name = "NUMERO_PARCELA", referencedColumnName="NUMERO_PARCELA", nullable = true)
    })
    private FaturamentoParcelamentoParcela faturamentoParcelamentoParcela;

    @Column(name = "ORIGEM_FATURA")
    private Short origemFatura;

    @Column(name = "REF_FATURA")
    private Integer refFatura;

    public IdCredito getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(IdCredito idCredito) {
        this.idCredito = idCredito;
    }

    public Short getCdServico() {
        return cdServico;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Character getMaint() {
        return maint;
    }

    public void setMaint(Character maint) {
        this.maint = maint;
    }

    public Integer getRefEncerramento() {
        return refEncerramento;
    }

    public void setRefEncerramento(Integer refEncerramento) {
        this.refEncerramento = refEncerramento;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(BigDecimal valorCredito) {
        this.valorCredito = valorCredito;
    }

    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(BigDecimal valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public Integer getRefAtendimento() {
        return refAtendimento;
    }

    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }

    public Integer getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
    }

    public String getDcAnotacao() {
        return dcAnotacao;
    }

    public void setDcAnotacao(String dcAnotacao) {
        this.dcAnotacao = dcAnotacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }
    public FaturamentoParcelamentoParcela getFaturamentoParcelamentoParcela() {
        return faturamentoParcelamentoParcela;
    }

    public void setFaturamentoParcelamentoParcela(FaturamentoParcelamentoParcela faturamentoParcelamentoParcela) {
        this.faturamentoParcelamentoParcela = faturamentoParcelamentoParcela;
    }

    public Short getAgenciaArrecadadora() {
        return agenciaArrecadadora;
    }

    public void setAgenciaArrecadadora(Short agenciaArrecadadora) {
        this.agenciaArrecadadora = agenciaArrecadadora;
    }

    public Short getAgenteArrecadador() {
        return agenteArrecadador;
    }

    public void setAgenteArrecadador(Short agenteArrecadador) {
        this.agenteArrecadador = agenteArrecadador;
    }

    public Short getMaqReg() {
        return maqReg;
    }

    public void setMaqReg(Short maqReg) {
        this.maqReg = maqReg;
    }

    public Integer getNumeroLoteArrecadador() {
        return numeroLoteArrecadador;
    }

    public void setNumeroLoteArrecadador(Integer numeroLoteArrecadador) {
        this.numeroLoteArrecadador = numeroLoteArrecadador;
    }

    public Short getTipoAgente() {
        return tipoAgente;
    }

    public void setTipoAgente(Short tipoAgente) {
        this.tipoAgente = tipoAgente;
    }

    public Short getCdCobranca() {
        return cdCobranca;
    }

    public void setCdCobranca(Short cdCobranca) {
        this.cdCobranca = cdCobranca;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCredito == null) ? 0 : idCredito.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Credito other = (Credito) obj;
        if (idCredito == null) {
            if (other.idCredito != null)
                return false;
        } else if (!idCredito.equals(other.idCredito))
            return false;
        return true;
    }
}

package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import moduloFaturamento.util.ConvertObjectToJsonCesan;

@Embeddable
public class IdLancamentoFaturaAvulsa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CD_CLIENTE")
    private Integer cdCliente;

    @Column(name = "REF_FATURA")
    private Integer refFatura;

    @Column(name = "ORIGEM_FATURA")
    private Short origemFatura;

    @Column(name = "CD_SERVICO")
    private Short cdServico;

    public IdLancamentoFaturaAvulsa() {
    }

    public IdLancamentoFaturaAvulsa(Integer cdCliente, Integer refFatura, Short origemFatura, Short cdServico) {
        this.cdCliente = cdCliente;
        this.refFatura = refFatura;
        this.origemFatura = origemFatura;
        this.cdServico = cdServico;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    public Short getCdServico() {
        return cdServico;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdCliente == null) ? 0 : cdCliente.hashCode());
        result = prime * result + ((cdServico == null) ? 0 : cdServico.hashCode());
        result = prime * result + ((origemFatura == null) ? 0 : origemFatura.hashCode());
        result = prime * result + ((refFatura == null) ? 0 : refFatura.hashCode());
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
        IdLancamentoFaturaAvulsa other = (IdLancamentoFaturaAvulsa) obj;
        if (cdCliente == null) {
            if (other.cdCliente != null)
                return false;
        } else if (!cdCliente.equals(other.cdCliente))
            return false;
        if (cdServico == null) {
            if (other.cdServico != null)
                return false;
        } else if (!cdServico.equals(other.cdServico))
            return false;
        if (origemFatura == null) {
            if (other.origemFatura != null)
                return false;
        } else if (!origemFatura.equals(other.origemFatura))
            return false;
        if (refFatura == null) {
            if (other.refFatura != null)
                return false;
        } else if (!refFatura.equals(other.refFatura))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ConvertObjectToJsonCesan.execute(this);
    }

}

package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdFaturamentoParcelamentoParcela implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CD_PARCELAMENTO", nullable = true)
    private Integer cdParcelamento;

    @Column(name = "NUMERO_PARCELA", nullable = true)
    private Integer numeroParcela;

    public IdFaturamentoParcelamentoParcela() {
    }

    public IdFaturamentoParcelamentoParcela(Integer cdParcelamento, Integer numeroParcela) {
        this.cdParcelamento = cdParcelamento;
        this.numeroParcela = numeroParcela;
    }

    public Integer getCdParcelamento() {
        return cdParcelamento;
    }

    public void setCdParcelamento(Integer cdParcelamento) {
        this.cdParcelamento = cdParcelamento;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdParcelamento == null) ? 0 : cdParcelamento.hashCode());
        result = prime * result + ((numeroParcela == null) ? 0 : numeroParcela.hashCode());
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
        IdFaturamentoParcelamentoParcela other = (IdFaturamentoParcelamentoParcela) obj;
        if (cdParcelamento == null) {
            if (other.cdParcelamento != null)
                return false;
        } else if (!cdParcelamento.equals(other.cdParcelamento))
            return false;
        if (numeroParcela == null) {
            if (other.numeroParcela != null)
                return false;
        } else if (!numeroParcela.equals(other.numeroParcela))
            return false;
        return true;
    }

}

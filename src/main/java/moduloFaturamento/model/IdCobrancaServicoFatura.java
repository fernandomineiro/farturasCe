package moduloFaturamento.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdCobrancaServicoFatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "CD_COBRANCA")
    private Short cdCobranca;
    @Column(name = "MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    public IdCobrancaServicoFatura() {
    }

    public IdCobrancaServicoFatura(Short cdCobranca, Integer matriculaImovel) {
        this.cdCobranca = cdCobranca;
        this.matriculaImovel = matriculaImovel;
    }

    public Short getCdCobranca() {
        return cdCobranca;
    }

    public void setCdCobranca(Short cdCobranca) {
        this.cdCobranca = cdCobranca;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdCobranca == null) ? 0 : cdCobranca.hashCode());
        result = prime * result + ((matriculaImovel == null) ? 0 : matriculaImovel.hashCode());
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
        IdCobrancaServicoFatura other = (IdCobrancaServicoFatura) obj;
        if (cdCobranca == null) {

            if (other.cdCobranca != null)
                return false;
        } else if (!cdCobranca.equals(other.cdCobranca))
            return false;
        if (matriculaImovel == null) {
            return other.matriculaImovel == null;
        } else return matriculaImovel.equals(other.matriculaImovel);
    }

    @Override
    public String toString() {
        return "[" +
                "cdCobranca=" + cdCobranca +
                ", matriculaImovel=" + matriculaImovel +
                ']';
    }
}

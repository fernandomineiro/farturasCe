package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import moduloFaturamento.util.ConvertObjectToJsonCesan;

@Embeddable
public class IdLeitura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;

    @Column(name = "REF_FATURA")
	private Integer refFatura;

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Integer getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matriculaImovel == null) ? 0 : matriculaImovel.hashCode());
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
        IdLeitura other = (IdLeitura) obj;
        if (matriculaImovel == null) {
            if (other.matriculaImovel != null)
                return false;
        } else if (!matriculaImovel.equals(other.matriculaImovel))
            return false;
        if (refFatura == null) {
            if (other.refFatura != null)
                return false;
        } else if (!refFatura.equals(other.refFatura))
            return false;
        return true;
    }

    public IdLeitura(Integer matriculaImovel, Integer refFatura) {
        this.matriculaImovel = matriculaImovel;
        this.refFatura = refFatura;
    }

    public IdLeitura() {
    }

    @Override
    public String toString() {
        return ConvertObjectToJsonCesan.execute(this);
    }

    
    
}

package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import moduloFaturamento.util.ConvertObjectToJsonCesan;

@Embeddable
public class IdCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "CD_CREDITO")
    private short credito;

    @Column(name = "MATRICULA_IMOVEL")
    private Integer matricula;

    public IdCredito() {
    }

    public IdCredito(Integer matricula, short credito) {
        this.credito = credito;
        this.matricula = matricula;
    }

    public short getCredito() {
        return credito;
    }

    public void setCredito(short credito) {
        this.credito = credito;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + credito;
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
        IdCredito other = (IdCredito) obj;
        if (credito != other.credito)
            return false;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ConvertObjectToJsonCesan.execute(this);
    }
    
    
}

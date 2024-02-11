package moduloFaturamento.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdBairro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name="CD_BAIRRO")
    private Short cdBairro;
    @Column(name="CD_CIDADE")
    private Short cdLocalidade;


    public IdBairro() {

    }

    public IdBairro(Short cdBairro, Short cdLocalidade) {
        this.cdBairro = cdBairro;
        this.cdLocalidade = cdLocalidade;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdBairro == null) ? 0 : cdBairro.hashCode());
        result = prime * result + ((cdLocalidade == null) ? 0 : cdLocalidade.hashCode());
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
        IdBairro other = (IdBairro) obj;
        if (cdBairro == null) {
            if (other.cdBairro != null)
                return false;
        } else if (!cdBairro.equals(other.cdBairro))
            return false;
        if (cdLocalidade == null) {
            return other.cdLocalidade == null;
        } else return cdLocalidade.equals(other.cdLocalidade);
    }
    public Short getCdBairro() {
        return cdBairro;
    }
    public void setCdBairro(Short cdBairro) {
        this.cdBairro = cdBairro;
    }
    public Short getCdLocalidade() {
        return cdLocalidade;
    }
    public void setCdLocalidade(Short cdLocalidade) {
        this.cdLocalidade = cdLocalidade;
    }


}

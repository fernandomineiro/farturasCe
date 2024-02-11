package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdDetalheDaFaturaParcelada implements Serializable{

    @Column(name = "CD_PARCELAMENTO")
    private Integer cdParcelamento;

    @Column(name = "MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    @Column(name = "REF_FATURA")
    private Integer referenciaFatura;

    @Column(name = "ORIGEM_FATURA")
    private Short origemFatura;

    public IdDetalheDaFaturaParcelada() {
    }

    public IdDetalheDaFaturaParcelada(Integer cdParcelamento, Integer matriculaImovel, Integer referenciaFatura,
            Short origemFatura) {
        this.cdParcelamento = cdParcelamento;
        this.matriculaImovel = matriculaImovel;
        this.referenciaFatura = referenciaFatura;
        this.origemFatura = origemFatura;
    }

    public Integer getCdParcelamento() {
        return cdParcelamento;
    }

    public void setCdParcelamento(Integer cdParcelamento) {
        this.cdParcelamento = cdParcelamento;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Integer getReferenciaFatura() {
        return referenciaFatura;
    }

    public void setReferenciaFatura(Integer referenciaFatura) {
        this.referenciaFatura = referenciaFatura;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdParcelamento == null) ? 0 : cdParcelamento.hashCode());
        result = prime * result + ((matriculaImovel == null) ? 0 : matriculaImovel.hashCode());
        result = prime * result + ((origemFatura == null) ? 0 : origemFatura.hashCode());
        result = prime * result + ((referenciaFatura == null) ? 0 : referenciaFatura.hashCode());
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
        IdDetalheDaFaturaParcelada other = (IdDetalheDaFaturaParcelada) obj;
        if (cdParcelamento == null) {
            if (other.cdParcelamento != null)
                return false;
        } else if (!cdParcelamento.equals(other.cdParcelamento))
            return false;
        if (matriculaImovel == null) {
            if (other.matriculaImovel != null)
                return false;
        } else if (!matriculaImovel.equals(other.matriculaImovel))
            return false;
        if (origemFatura == null) {
            if (other.origemFatura != null)
                return false;
        } else if (!origemFatura.equals(other.origemFatura))
            return false;
        if (referenciaFatura == null) {
            if (other.referenciaFatura != null)
                return false;
        } else if (!referenciaFatura.equals(other.referenciaFatura))
            return false;
        return true;
    }

    

}

package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTFP")
public class DetalheDaFaturaParcelada {

    @EmbeddedId
    private IdDetalheDaFaturaParcelada idDetalheDaFaturaParcelada;

    @Column(name = "MULTA_FATURA")
    private BigDecimal multaFatura;

    @Column(name = "JUROS_FATURA")
    private BigDecimal jurosFatura;

    @Column(name = "DESCONTO_VR_FATURA")
    private BigDecimal descontoFatura;

    @Column(name = "DESCONTO_JUROS")
    private BigDecimal descontoJuros;

    @Column(name = "DESCONTO_MULTA")
    private BigDecimal descontoMulta;

    public DetalheDaFaturaParcelada() {
    }

    public DetalheDaFaturaParcelada(IdDetalheDaFaturaParcelada idDetalheDaFaturaParcelada, BigDecimal multaFatura,
            BigDecimal jurosFatura, BigDecimal descontoFatura, BigDecimal descontoJuros, BigDecimal descontoMulta) {
        this.idDetalheDaFaturaParcelada = idDetalheDaFaturaParcelada;
        this.multaFatura = multaFatura;
        this.jurosFatura = jurosFatura;
        this.descontoFatura = descontoFatura;
        this.descontoJuros = descontoJuros;
        this.descontoMulta = descontoMulta;
    }

    public IdDetalheDaFaturaParcelada getIdDetalheDaFaturaParcelada() {
        return idDetalheDaFaturaParcelada;
    }

    public void setIdDetalheDaFaturaParcelada(IdDetalheDaFaturaParcelada idDetalheDaFaturaParcelada) {
        this.idDetalheDaFaturaParcelada = idDetalheDaFaturaParcelada;
    }

    public BigDecimal getMultaFatura() {
        return multaFatura;
    }

    public void setMultaFatura(BigDecimal multaFatura) {
        this.multaFatura = multaFatura;
    }

    public BigDecimal getJurosFatura() {
        return jurosFatura;
    }

    public void setJurosFatura(BigDecimal jurosFatura) {
        this.jurosFatura = jurosFatura;
    }

    public BigDecimal getDescontoFatura() {
        return descontoFatura;
    }

    public void setDescontoFatura(BigDecimal descontoFatura) {
        this.descontoFatura = descontoFatura;
    }

    public BigDecimal getDescontoJuros() {
        return descontoJuros;
    }

    public void setDescontoJuros(BigDecimal descontoJuros) {
        this.descontoJuros = descontoJuros;
    }

    public BigDecimal getDescontoMulta() {
        return descontoMulta;
    }

    public void setDescontoMulta(BigDecimal descontoMulta) {
        this.descontoMulta = descontoMulta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDetalheDaFaturaParcelada == null) ? 0 : idDetalheDaFaturaParcelada.hashCode());
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
        DetalheDaFaturaParcelada other = (DetalheDaFaturaParcelada) obj;
        if (idDetalheDaFaturaParcelada == null) {
            if (other.idDetalheDaFaturaParcelada != null)
                return false;
        } else if (!idDetalheDaFaturaParcelada.equals(other.idDetalheDaFaturaParcelada))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DetalheDaFaturaParcelada [descontoFatura=" + descontoFatura + ", descontoJuros=" + descontoJuros
                + ", descontoMulta=" + descontoMulta + ", idDetalheDaFaturaParcelada=" + idDetalheDaFaturaParcelada
                + ", jurosFatura=" + jurosFatura + ", multaFatura=" + multaFatura + "]";
    }    
}

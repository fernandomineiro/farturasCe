package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_IMPOSTO")
public class ServicoImposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "CD_SERVICO")
    private Integer cdServico;

    @Column(name = "PORCENTAGEM")
    private BigDecimal porcentagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCdServico() {
        return cdServico;
    }

    public void setCdServico(Integer cdServico) {
        this.cdServico = cdServico;
    }

    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ServicoImposto other = (ServicoImposto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}

package moduloFaturamento.model;

import moduloFaturamento.model.common.Localidade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SAP_CENTRO_CUSTOS")
public class CentroCusto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SAP_CENTRO_CUSTOS")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_CIDADE", referencedColumnName = "CD_CIDADE")
    private Localidade localidade;

    @Column(name = "NR_CONTA_CONTABIL_SAP")
    private BigDecimal nrContaContabilSap;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public BigDecimal getNrContaContabilSap() {
        return nrContaContabilSap;
    }

    public void setNrContaContabilSap(BigDecimal nrContaContabilSap) {
        this.nrContaContabilSap = nrContaContabilSap;
    }
}

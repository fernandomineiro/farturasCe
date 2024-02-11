package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HITTA")
public class TarifaMedia {

    @Column(name = "ROWID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CD_REGIONAL")
    private Short idRegional;

    @Column(name = "GRUPO_CONSUMO")
    private Short idGrupoDeConsumo;

    @Column(name = "REF_TARIFA_MEDIA")
    private Integer refTarifaMedia;

    @Column(name = "VALOR_TARIFA")
    private BigDecimal valorTarifaMedia;

    @Column(name = "MEDIA_AGUA")
    private BigDecimal valorMedioAgua;

    @Column(name = "MEDIA_ESGOTO")
    private BigDecimal valorMedioEsgoto;

    @Column(name = "MEDIA_DISPONIBILIDADE")
    private BigDecimal valorMedioDisponibilidade;

    public TarifaMedia() {
    }

    public TarifaMedia(Short idRegional, Short idGrupoDeConsumo, Integer refTarifaMedia, BigDecimal valorTarifaMedia,
            BigDecimal valorMedioAgua, BigDecimal valorMedioEsgoto, BigDecimal valorMedioDisponibilidade) {
        this.idRegional = idRegional;
        this.idGrupoDeConsumo = idGrupoDeConsumo;
        this.refTarifaMedia = refTarifaMedia;
        this.valorTarifaMedia = valorTarifaMedia;
        this.valorMedioAgua = valorMedioAgua;
        this.valorMedioEsgoto = valorMedioEsgoto;
        this.valorMedioDisponibilidade = valorMedioDisponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getIdRegional() {
        return idRegional;
    }

    public void setIdRegional(Short idRegional) {
        this.idRegional = idRegional;
    }

    public Short getIdGrupoDeConsumo() {
        return idGrupoDeConsumo;
    }

    public void setIdGrupoDeConsumo(Short idGrupoDeConsumo) {
        this.idGrupoDeConsumo = idGrupoDeConsumo;
    }

    public Integer getRefTarifaMedia() {
        return refTarifaMedia;
    }

    public void setRefTarifaMedia(Integer refTarifaMedia) {
        this.refTarifaMedia = refTarifaMedia;
    }

    public BigDecimal getValorTarifaMedia() {
        return valorTarifaMedia;
    }

    public void setValorTarifaMedia(BigDecimal valorTarifaMedia) {
        this.valorTarifaMedia = valorTarifaMedia;
    }

    public BigDecimal getValorMedioAgua() {
        return valorMedioAgua;
    }

    public void setValorMedioAgua(BigDecimal valorMedioAgua) {
        this.valorMedioAgua = valorMedioAgua;
    }

    public BigDecimal getValorMedioEsgoto() {
        return valorMedioEsgoto;
    }

    public void setValorMedioEsgoto(BigDecimal valorMedioEsgoto) {
        this.valorMedioEsgoto = valorMedioEsgoto;
    }

    public BigDecimal getValorMedioDisponibilidade() {
        return valorMedioDisponibilidade;
    }

    public void setValorMedioDisponibilidade(BigDecimal valorMedioDisponibilidade) {
        this.valorMedioDisponibilidade = valorMedioDisponibilidade;
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
        TarifaMedia other = (TarifaMedia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}

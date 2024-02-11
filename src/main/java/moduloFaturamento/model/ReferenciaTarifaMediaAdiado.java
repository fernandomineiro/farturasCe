package moduloFaturamento.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FAT_REF_TARIFA_MEDIA_ADIADO")
public class ReferenciaTarifaMediaAdiado {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REF_TARIFA_MEDIA_ADIADO")
    private LocalDate refTarifaMediaAdiado;

    public ReferenciaTarifaMediaAdiado() {
    }

    public ReferenciaTarifaMediaAdiado(Integer id, LocalDate refTarifaMediaAdiado) {
        this.id = id;
        this.refTarifaMediaAdiado = refTarifaMediaAdiado;
    }

    public LocalDate getRefTarifaMediaAdiado() {
        return refTarifaMediaAdiado;
    }

    public void setRefTarifaMediaAdiado(LocalDate refTarifaMediaAdiado) {
        this.refTarifaMediaAdiado = refTarifaMediaAdiado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}

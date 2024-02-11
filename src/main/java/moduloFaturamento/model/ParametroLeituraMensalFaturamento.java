package moduloFaturamento.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="FAT_PARAMETROS")
public class ParametroLeituraMensalFaturamento {

    @Id
    @Column(name="ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TP_GERACAO", referencedColumnName = "ID", insertable = false)
    private TipoGeracaoParametros tipoGeracaoParametros;

    @Column(name = "HORARIO", insertable = false)
    private LocalDateTime horario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoGeracaoParametros getTipoGeracaoParametros() {
        return tipoGeracaoParametros;
    }

    public void setTipoGeracaoParametros(TipoGeracaoParametros tipoGeracaoParametros) {
        this.tipoGeracaoParametros = tipoGeracaoParametros;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}

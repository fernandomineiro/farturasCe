package moduloFaturamento.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="FAT_TP_UNIDADE_FEDERATIVA")
public class TipoUnidadeFederativa {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="DESCRICAO")
    @Size(max = 100)
    private String descricao;
    @Column(name="SIGLA")
    private String sigla;

    public TipoUnidadeFederativa() {
    }

    public TipoUnidadeFederativa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return (this.id != null ? this.id.toString(): null);
    }
}

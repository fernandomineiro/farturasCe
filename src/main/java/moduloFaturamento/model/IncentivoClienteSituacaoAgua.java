package moduloFaturamento.model;

import moduloFaturamento.model.common.SituacaoLigacaoAgua;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FAT_INCENTIVO_CLIENTE_SITUACAO_AGUA")
public class IncentivoClienteSituacaoAgua implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INCENTIVO_CLIENTE_DETALHE", referencedColumnName = "CD_PARAMETRO_DETALHE")
    private IncentivoClienteDetalhe incentivoClienteDetalhe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SITUACAO_AGUA", referencedColumnName = "ID_SIT_LIGACAO_AGUA")
    private SituacaoLigacaoAgua situacaoLigacaoAgua;

    public IncentivoClienteSituacaoAgua() {
    }

    public IncentivoClienteSituacaoAgua(IncentivoClienteDetalhe incentivoClienteDetalhe, SituacaoLigacaoAgua situacaoLigacaoAgua) {
        this.setIncentivoClienteDetalhe(incentivoClienteDetalhe);
        this.situacaoLigacaoAgua = situacaoLigacaoAgua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SituacaoLigacaoAgua getSituacaoLigacaoAgua() {
        return situacaoLigacaoAgua;
    }

    public void setSituacaoLigacaoAgua(SituacaoLigacaoAgua situacaoLigacaoAgua) {
        this.situacaoLigacaoAgua = situacaoLigacaoAgua;
    }

    public IncentivoClienteDetalhe getIncentivoClienteDetalhe() {
        return incentivoClienteDetalhe;
    }

    public void setIncentivoClienteDetalhe(IncentivoClienteDetalhe incentivoClienteDetalhe) {
        this.incentivoClienteDetalhe = incentivoClienteDetalhe;
    }
}

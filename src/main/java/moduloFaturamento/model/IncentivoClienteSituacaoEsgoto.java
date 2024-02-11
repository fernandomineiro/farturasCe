package moduloFaturamento.model;

import moduloFaturamento.model.common.SituacaoLigacaoEsgoto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FAT_INCENTIVO_CLIENTE_SITUACAO_ESGOTO")
public class IncentivoClienteSituacaoEsgoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INCENTIVO_CLIENTE_DETALHE", referencedColumnName = "CD_PARAMETRO_DETALHE")
    private IncentivoClienteDetalhe incentivoClienteDetalhe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SITUACAO_ESGOTO", referencedColumnName = "ID_SIT_LIGACAO_ESGOTO")
    private SituacaoLigacaoEsgoto situacaoLigacaoEsgoto;

    public IncentivoClienteSituacaoEsgoto() {
    }

    public IncentivoClienteSituacaoEsgoto(IncentivoClienteDetalhe incentivoClienteDetalhe, SituacaoLigacaoEsgoto situacaoLigacaoEsgoto) {
        this.setIncentivoClienteDetalhe(incentivoClienteDetalhe);
        this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public SituacaoLigacaoEsgoto getSituacaoLigacaoEsgoto() {
        return situacaoLigacaoEsgoto;
    }

    public void setSituacaoLigacaoEsgoto(SituacaoLigacaoEsgoto situacaoLigacaoEsgoto) {
        this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
    }

    public IncentivoClienteDetalhe getIncentivoClienteDetalhe() {
        return incentivoClienteDetalhe;
    }

    public void setIncentivoClienteDetalhe(IncentivoClienteDetalhe incentivoClienteDetalhe) {
        this.incentivoClienteDetalhe = incentivoClienteDetalhe;
    }
}

package moduloFaturamento.model.common;

import moduloFaturamento.util.customAnnotation.JsonCesanNotSerializable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "CAD_CLASSIFICACAO_IMOBILIARIA")
public class ClassificacaoImobiliaria implements Serializable {

    @Id
    @Column(name = "ID_CLASSIFICACAO_IMOBILIARIA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClassificacaoImobiliaria;

    @Column(name = "ID_GRUPO_CONSUMO")
    private Integer idGrupoConsumo;
    @Column(name = "CS_CATEGORIA")
    private Short csCategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NR_MATRICULA_IMOVEL", referencedColumnName = "MATRICULA_IMOVEL")
    private Imovel imovel;

    @Column(name = "ECONOMIAS")
    private Short economias;

    @Column(name = "DATA_HORA_FIM")
    @JsonCesanNotSerializable
    private LocalDateTime dataHoraFim;

    public Integer getIdClassificacaoImobiliaria() {
        return idClassificacaoImobiliaria;
    }

    public void setIdClassificacaoImobiliaria(Integer idClassificacaoImobiliaria) {
        this.idClassificacaoImobiliaria = idClassificacaoImobiliaria;
    }

    public Integer getIdGrupoConsumo() {
        return idGrupoConsumo;
    }

    public void setIdGrupoConsumo(Integer idGrupoConsumo) {
        this.idGrupoConsumo = idGrupoConsumo;
    }

    public Short getEconomias() {
        return economias;
    }

    public void setEconomias(Short economias) {
        this.economias = economias;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Short getCsCategoria() {
        return csCategoria;
    }

    public void setCsCategoria(Short csCategoria) {
        this.csCategoria = csCategoria;
    }
}


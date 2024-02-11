package moduloFaturamento.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAT_HISTORICO_INCENTIVO_CLIENTE_SITUACAO_ESGOTO")
public class IncentivoClienteHistoricoSituacaoEsgoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_INCENTIVO_CLIENTE_DETALHE")
    private Integer idIncentivoClienteDetalhe;

    @Column(name = "ID_SITUACAO_ESGOTO")
    private Integer idSituacaoEsgoto;
    @Column(name = "DATA_REGISTRO_HISTORICO")
    private LocalDateTime dataHoraRegistroHistorico;

    public IncentivoClienteHistoricoSituacaoEsgoto() {
    }

    public IncentivoClienteHistoricoSituacaoEsgoto(IncentivoClienteHistoricoDetalhe incentivoClienteDetalheHistorico, Integer idSituacaoEsgoto) {
        this.idIncentivoClienteDetalhe = incentivoClienteDetalheHistorico.getIdParametroDetalhe();
        this.idSituacaoEsgoto = idSituacaoEsgoto;
        this.dataHoraRegistroHistorico = LocalDateTime.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdIncentivoClienteDetalhe() {
        return idIncentivoClienteDetalhe;
    }

    public void setIdIncentivoClienteDetalhe(Integer idIncentivoCliente) {
        this.idIncentivoClienteDetalhe = idIncentivoCliente;
    }

    public Integer getIdSituacaoEsgoto() {
        return idSituacaoEsgoto;
    }

    public void setIdSituacaoEsgoto(Integer idSituacaoAgua) {
        this.idSituacaoEsgoto = idSituacaoAgua;
    }
}

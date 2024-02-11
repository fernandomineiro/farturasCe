package moduloFaturamento.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAT_HISTORICO_INCENTIVO_CLIENTE_SITUACAO_AGUA")
public class IncentivoClienteHistoricoSituacaoAgua implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_INCENTIVO_CLIENTE_DETALHE")
    private Integer idIncentivoClienteDetalhe;

    @Column(name = "ID_SITUACAO_AGUA")
    private Integer idSituacaoAgua;
    @Column(name = "DATA_REGISTRO_HISTORICO")
    private LocalDateTime dataHoraRegistroHistorico;

    public IncentivoClienteHistoricoSituacaoAgua() {
    }



    public IncentivoClienteHistoricoSituacaoAgua(IncentivoClienteHistoricoDetalhe incentivoClienteDetalheHistorico, Integer idSituacaoAgua) {
        this.idIncentivoClienteDetalhe = incentivoClienteDetalheHistorico.getIdParametroDetalhe();
        this.idSituacaoAgua = idSituacaoAgua;
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

    public Integer getIdSituacaoAgua() {
        return idSituacaoAgua;
    }

    public void setIdSituacaoAgua(Integer idSituacaoAgua) {
        this.idSituacaoAgua = idSituacaoAgua;
    }
}

package moduloFaturamento.model.common;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="SRASE")
public class Servico implements Serializable {

    @Id
    @Column(name="CD_SERVICO")
    private Short cdServico;

    @Column(name = "DC_SERVICO")
    @Size(max = 30)
    private String dcServico;

    @ManyToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.DETACH)
    @JoinColumn(name = "CD_AGRUPAMENTO", nullable = false, referencedColumnName = "CD_SERVICO")
    @NotFound(action = NotFoundAction.IGNORE)
    @Transient
    private Servico servicoMacro;

    @Column(name="CD_AGRUPAMENTO")
    private Short cdAgrupamento;

    @Column(name="PERM_FAT_DEBITO")
    private String permitoLancamentoEmFaturaDebito;

    @Column(name="DATA_HORA_EXCLUSAO")
    private LocalDateTime dataHoraExclusao;

    public Servico() {
    }

    public Servico(Short cdServico) {
        this.cdServico = cdServico;
    }

    public Short getCdServico() {
        return cdServico;
    }

    public void setCdServico(Short cdServico) {
        this.cdServico = cdServico;
    }

    public Servico getServicoMacro() {
        return servicoMacro;
    }

    public void setServicoMacro(Servico servicoMacro) {
        this.servicoMacro = servicoMacro;
    }

    public Short getCdAgrupamento() {
        return cdAgrupamento;
    }

    public void setCdAgrupamento(Short cdAgrupamento) {
        this.cdAgrupamento = cdAgrupamento;
    }

    public String getPermitoLancamentoEmFaturaDebito() {
        return permitoLancamentoEmFaturaDebito;
    }

    public void setPermitoLancamentoEmFaturaDebito(String permitoLancamentoEmFaturaDebito) {
        this.permitoLancamentoEmFaturaDebito = permitoLancamentoEmFaturaDebito;
    }

    public String getDcServico() {
        return dcServico;
    }

    public void setDcServico(String dcServico) {
        this.dcServico = dcServico;
    }

    public LocalDateTime getDataHoraExclusao() {
        return dataHoraExclusao;
    }

    public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
        this.dataHoraExclusao = dataHoraExclusao;
    }

    @Override
    public String toString() {
        return this.cdServico.toString();
    }
}

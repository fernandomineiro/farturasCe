package moduloFaturamento.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CDTCN")
public class AtualizarCiclo {

    @Column(name = "ROWID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "MATRICULA_IMOVEL")
    private Integer matriculaImovel;

    @Column(name = "CICLO_ANTIGO")
    private Short cicloAntigo;

    @Column(name = "CICLO_NOVO")
    private Short cicloNovo;

    @Column(name = "DT_INCLUSAO")
    private Integer dataInclusao;

    @Column(name = "HR_INCLUSAO")
    private Short horaInclusao;

    @Column(name = "MI_INCLUSAO")
    private Short minInclusao;

    @Column(name = "DT_EXECUCAO")
    private Integer dataExecucao;

    @Column(name = "HR_EXECUCAO")
    private Short horaExecucao;

    @Column(name = "MI_EXECUCAO")
    private Short minExecucao;

    @Column(name = "SITUACAO")
    private Short situacao;

    @Column(name = "DT_HR_INCLUSAO")
    private LocalDateTime dataHoraInclusao;

    @Column(name = "DT_HR_EXECUCAO")
    private LocalDateTime dataHoraExecucao;

    @Column(name = "DC_SITUACAO")
    private String descricaoSituacao;

    public AtualizarCiclo() {
    }

    public AtualizarCiclo(Integer matriculaImovel, Short cicloAntigo, Short cicloNovo, Integer dataInclusao,
            Short horaInclusao, Short minInclusao, Integer dataExecucao, Short horaExecucao, Short minExecucao,
            Short situacao) {
        this.matriculaImovel = matriculaImovel;
        this.cicloAntigo = cicloAntigo;
        this.cicloNovo = cicloNovo;
        this.dataInclusao = dataInclusao;
        this.horaInclusao = horaInclusao;
        this.minInclusao = minInclusao;
        this.dataExecucao = dataExecucao;
        this.horaExecucao = horaExecucao;
        this.minExecucao = minExecucao;
        this.situacao = situacao;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Short getCicloAntigo() {
        return cicloAntigo;
    }

    public void setCicloAntigo(Short cicloAntigo) {
        this.cicloAntigo = cicloAntigo;
    }

    public Short getCicloNovo() {
        return cicloNovo;
    }

    public void setCicloNovo(Short cicloNovo) {
        this.cicloNovo = cicloNovo;
    }

    public Integer getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Integer dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Short getHoraInclusao() {
        return horaInclusao;
    }

    public void setHoraInclusao(Short horaInclusao) {
        this.horaInclusao = horaInclusao;
    }

    public Short getMinInclusao() {
        return minInclusao;
    }

    public void setMinInclusao(Short minInclusao) {
        this.minInclusao = minInclusao;
    }

    public Integer getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(Integer dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public Short getHoraExecucao() {
        return horaExecucao;
    }

    public void setHoraExecucao(Short horaExecucao) {
        this.horaExecucao = horaExecucao;
    }

    public Short getMinExecucao() {
        return minExecucao;
    }

    public void setMinExecucao(Short minExecucao) {
        this.minExecucao = minExecucao;
    }

    public Short getSituacao() {
        return situacao;
    }

    public void setSituacao(Short situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public LocalDateTime getDataHoraExecucao() {
        return dataHoraExecucao;
    }

    public void setDataHoraExecucao(LocalDateTime dataHoraExecucao) {
        this.dataHoraExecucao = dataHoraExecucao;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
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
        AtualizarCiclo other = (AtualizarCiclo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}

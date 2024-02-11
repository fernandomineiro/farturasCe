package moduloFaturamento.dto.faturaAvulsa;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FaturaAvulsaPesquisaFilterDTO {

    @NotNull
    private Integer cdCliente;
    @NotNull
    private Short dvCliente;
    private Integer matriculaImovel;
    private Short dvMatriculaImovel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate refFatura;

    private Short idBaixa;


    public Integer getCdCliente() {
        return cdCliente;
    }
    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }
    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }
    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }
    public LocalDate getRefFatura() {
        return refFatura;
    }
    public void setRefFatura(LocalDate refFatura) {
        this.refFatura = refFatura;
    }
    public Short getIdBaixa() {
        return idBaixa;
    }
    public void setIdBaixa(Short idBaixa) {
        this.idBaixa = idBaixa;
    }
    public Short getDvCliente() {
        return dvCliente;
    }
    public void setDvCliente(Short dvCliente) {
        this.dvCliente = dvCliente;
    }
    public Short getDvMatriculaImovel() {
        return dvMatriculaImovel;
    }
    public void setDvMatriculaImovel(Short dvMatriculaImovel) {
        this.dvMatriculaImovel = dvMatriculaImovel;
    }

}

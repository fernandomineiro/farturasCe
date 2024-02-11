package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FaturaAvulsaCadastrarDTO {

    @NotNull
    private Integer cdCliente;
    @NotNull
    private Short dvCliente;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate refFatura;
    @NotNull
    private Short origemFatura;
    @NotEmpty
    private String mensagem01;
    @NotEmpty
    private String mensagem02;
    
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVenciemtno;

    private Integer matriculaImovel;
    @NotNull
    private Short localidade;
    @NotNull
    private BigDecimal valorTotal;

    private Integer refAtendimento;
    private Integer cdAtendimento;
    private Short seqSS;

    @NotNull
    @Valid
    private List<FaturaAvulsaListaLancamentoDTO> listaLancamentoFaturaAvulsa;

    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Short getDvCliente() {
        return dvCliente;
    }

    public void setDvCliente(Short dvCliente) {
        this.dvCliente = dvCliente;
    }

    public LocalDate getRefFatura() {
        return refFatura;
    }

    public void setRefFatura(LocalDate refFatura) {
        this.refFatura = refFatura;
    }

    public Short getOrigemFatura() {
        return origemFatura;
    }

    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }

    public String getMensagem01() {
        return mensagem01;
    }

    public void setMensagem01(String mensagem01) {
        this.mensagem01 = mensagem01;
    }

    public String getMensagem02() {
        return mensagem02;
    }

    public void setMensagem02(String mensagem02) {
        this.mensagem02 = mensagem02;
    }

    public LocalDate getDataVenciemtno() {
        return dataVenciemtno;
    }

    public void setDataVenciemtno(LocalDate dataVenciemtno) {
        this.dataVenciemtno = dataVenciemtno;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Short getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Short localidade) {
        this.localidade = localidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<FaturaAvulsaListaLancamentoDTO> getListaLancamentoFaturaAvulsa() {
        return listaLancamentoFaturaAvulsa;
    }

    public void setListaLancamentoFaturaAvulsa(List<FaturaAvulsaListaLancamentoDTO> listaLancamentoFaturaAvulsa) {
        this.listaLancamentoFaturaAvulsa = listaLancamentoFaturaAvulsa;
    }

    public Integer getRefAtendimento() {
        return refAtendimento;
    }

    public void setRefAtendimento(Integer refAtendimento) {
        this.refAtendimento = refAtendimento;
    }

    public Integer getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Integer cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }



    
}

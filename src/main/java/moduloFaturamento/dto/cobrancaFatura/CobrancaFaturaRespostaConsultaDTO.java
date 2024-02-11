package moduloFaturamento.dto.cobrancaFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CobrancaFaturaRespostaConsultaDTO {

    private Long id;
    private Integer matriculaImovel;
    private Short dv;
    private Short codigoServico;
    private BigDecimal valorServico;
    private LocalDate dtExecucao;
    private LocalDate referenciaFaturar;
    private LocalDate referenciaFaturada;
    private Integer ssCdAtendimento;
    private Integer ssRefAtendimento;
    private Short seqSS;
    private Integer itemCdAtendimento;
    private Integer itemRefAtendimento;
    private Short itemSeq;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMatriculaImovel() {
        return matriculaImovel;
    }

    public void setMatriculaImovel(Integer matriculaImovel) {
        this.matriculaImovel = matriculaImovel;
    }

    public Short getDv() {
        return dv;
    }

    public void setDv(Short dv) {
        this.dv = dv;
    }

    public Short getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(Short codigoServico) {
        this.codigoServico = codigoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public LocalDate getDtExecucao() {
        return dtExecucao;
    }

    public void setDtExecucao(LocalDate dtExecucao) {
        this.dtExecucao = dtExecucao;
    }

    public LocalDate getReferenciaFaturar() {
        return referenciaFaturar;
    }

    public void setReferenciaFaturar(LocalDate referenciaFaturar) {
        this.referenciaFaturar = referenciaFaturar;
    }

    public Integer getSsCdAtendimento() {
        return ssCdAtendimento;
    }

    public void setSsCdAtendimento(Integer ssCdAtendimento) {
        this.ssCdAtendimento = ssCdAtendimento;
    }

    public Integer getSsRefAtendimento() {
        return ssRefAtendimento;
    }

    public void setSsRefAtendimento(Integer ssRefAtendimento) {
        this.ssRefAtendimento = ssRefAtendimento;
    }

    public Short getSeqSS() {
        return seqSS;
    }

    public void setSeqSS(Short seqSS) {
        this.seqSS = seqSS;
    }

    public Integer getItemCdAtendimento() {
        return itemCdAtendimento;
    }

    public void setItemCdAtendimento(Integer itemCdAtendimento) {
        this.itemCdAtendimento = itemCdAtendimento;
    }

    public Integer getItemRefAtendimento() {
        return itemRefAtendimento;
    }

    public void setItemRefAtendimento(Integer itemRefAtendimento) {
        this.itemRefAtendimento = itemRefAtendimento;
    }

    public Short getItemSeq() {
        return itemSeq;
    }

    public void setItemSeq(Short itemSeq) {
        this.itemSeq = itemSeq;
    }

    public LocalDate getReferenciaFaturada() {
        return referenciaFaturada;
    }

    public void setReferenciaFaturada(LocalDate referenciaFaturada) {
        this.referenciaFaturada = referenciaFaturada;
    }
}

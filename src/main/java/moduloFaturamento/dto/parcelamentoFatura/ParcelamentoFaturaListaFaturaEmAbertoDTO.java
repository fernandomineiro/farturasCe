package moduloFaturamento.dto.parcelamentoFatura;

import java.math.BigDecimal;

public class ParcelamentoFaturaListaFaturaEmAbertoDTO {

    private Integer refFatura;
    private Short origemFatura;
    private String grupoDeConsumo;

    private BigDecimal valorFatura;
    private BigDecimal valorJuros;
    private BigDecimal jurosMora;

    private BigDecimal descontoFatura;
    private BigDecimal descontoJuros;
    private BigDecimal descontoJurosMora;
    
    public Integer getRefFatura() {
        return refFatura;
    }
    public void setRefFatura(Integer refFatura) {
        this.refFatura = refFatura;
    }
    public Short getOrigemFatura() {
        return origemFatura;
    }
    public void setOrigemFatura(Short origemFatura) {
        this.origemFatura = origemFatura;
    }
    public String getGrupoDeConsumo() {
        return grupoDeConsumo;
    }
    public void setGrupoDeConsumo(String grupoDeConsumo) {
        this.grupoDeConsumo = grupoDeConsumo;
    }
    public BigDecimal getValorFatura() {
        return valorFatura;
    }
    public void setValorFatura(BigDecimal valorFatura) {
        this.valorFatura = valorFatura;
    }
    public BigDecimal getValorJuros() {
        return valorJuros;
    }
    public void setValorJuros(BigDecimal valorJuros) {
        this.valorJuros = valorJuros;
    }
    public BigDecimal getJurosMora() {
        return jurosMora;
    }
    public void setJurosMora(BigDecimal jurosMora) {
        this.jurosMora = jurosMora;
    }
    public BigDecimal getDescontoFatura() {
        return descontoFatura;
    }
    public void setDescontoFatura(BigDecimal descontoFatura) {
        this.descontoFatura = descontoFatura;
    }
    public BigDecimal getDescontoJuros() {
        return descontoJuros;
    }
    public void setDescontoJuros(BigDecimal descontoJuros) {
        this.descontoJuros = descontoJuros;
    }
    public BigDecimal getDescontoJurosMora() {
        return descontoJurosMora;
    }
    public void setDescontoJurosMora(BigDecimal descontoJurosMora) {
        this.descontoJurosMora = descontoJurosMora;
    }
   
    
}

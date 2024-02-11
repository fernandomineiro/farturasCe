package moduloFaturamento.dto.tarifa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PesquisaTarifaRespostaDTO {

    private Short idTarifa;
    private LocalDate dataTarifa;
    private Short grupo;
    private Integer limite;

    private BigDecimal valorAgua;
    private BigDecimal valorAguaParcelaFixa;

    private BigDecimal valorEsgotoTratado;
    private BigDecimal valorEsgotoTratadoParcelaFixa;

    private BigDecimal valorEsgotoNaoTratado;
    private BigDecimal valorEsgotoNaoTratadoParcelaFixa;

    private BigDecimal valorDisponibilidadeEsgoto;    
    private BigDecimal valorDisponibilidadeEsgotoParcelaFixa;

    
    public PesquisaTarifaRespostaDTO() {
    }

    public PesquisaTarifaRespostaDTO(Short idTarifa, LocalDate dataTarifa, Short grupo, Integer limite,
            BigDecimal valorAgua, BigDecimal valorAguaParcelaFixa, BigDecimal valorEsgotoTratado,
            BigDecimal valorEsgotoTratadoParcelaFixa, BigDecimal valorEsgotoNaoTratado,
            BigDecimal valorEsgotoNaoTratadoParcelaFixa, BigDecimal valorDisponibilidadeEsgoto,
            BigDecimal valorDisponibilidadeEsgotoParcelaFixa) {
        this.idTarifa = idTarifa;
        this.dataTarifa = dataTarifa;
        this.grupo = grupo;
        this.limite = limite;
        this.valorAgua = valorAgua;
        this.valorAguaParcelaFixa = valorAguaParcelaFixa;
        this.valorEsgotoTratado = valorEsgotoTratado;
        this.valorEsgotoTratadoParcelaFixa = valorEsgotoTratadoParcelaFixa;
        this.valorEsgotoNaoTratado = valorEsgotoNaoTratado;
        this.valorEsgotoNaoTratadoParcelaFixa = valorEsgotoNaoTratadoParcelaFixa;
        this.valorDisponibilidadeEsgoto = valorDisponibilidadeEsgoto;
        this.valorDisponibilidadeEsgotoParcelaFixa = valorDisponibilidadeEsgotoParcelaFixa;
    }

    public Short getIdTarifa() {
        return idTarifa;
    }
    public void setIdTarifa(Short idTarifa) {
        this.idTarifa = idTarifa;
    }
    public LocalDate getDataTarifa() {
        return dataTarifa;
    }
    public void setDataTarifa(LocalDate dataTarifa) {
        this.dataTarifa = dataTarifa;
    }
    public Short getGrupo() {
        return grupo;
    }
    public void setGrupo(Short grupo) {
        this.grupo = grupo;
    }
    public Integer getLimite() {
        return limite;
    }
    public void setLimite(Integer limite) {
        this.limite = limite;
    }
    public BigDecimal getValorAgua() {
        return valorAgua;
    }
    public void setValorAgua(BigDecimal valorAgua) {
        this.valorAgua = valorAgua;
    }
    public BigDecimal getValorAguaParcelaFixa() {
        return valorAguaParcelaFixa;
    }
    public void setValorAguaParcelaFixa(BigDecimal valorAguaParcelaFixa) {
        this.valorAguaParcelaFixa = valorAguaParcelaFixa;
    }
    public BigDecimal getValorEsgotoTratado() {
        return valorEsgotoTratado;
    }
    public void setValorEsgotoTratado(BigDecimal valorEsgotoTratado) {
        this.valorEsgotoTratado = valorEsgotoTratado;
    }
    public BigDecimal getValorEsgotoTratadoParcelaFixa() {
        return valorEsgotoTratadoParcelaFixa;
    }
    public void setValorEsgotoTratadoParcelaFixa(BigDecimal valorEsgotoTratadoParcelaFixa) {
        this.valorEsgotoTratadoParcelaFixa = valorEsgotoTratadoParcelaFixa;
    }
    public BigDecimal getValorEsgotoNaoTratado() {
        return valorEsgotoNaoTratado;
    }
    public void setValorEsgotoNaoTratado(BigDecimal valorEsgotoNaoTratado) {
        this.valorEsgotoNaoTratado = valorEsgotoNaoTratado;
    }
    public BigDecimal getValorEsgotoNaoTratadoParcelaFixa() {
        return valorEsgotoNaoTratadoParcelaFixa;
    }
    public void setValorEsgotoNaoTratadoParcelaFixa(BigDecimal valorEsgotoNaoTratadoParcelaFixa) {
        this.valorEsgotoNaoTratadoParcelaFixa = valorEsgotoNaoTratadoParcelaFixa;
    }
    public BigDecimal getValorDisponibilidadeEsgoto() {
        return valorDisponibilidadeEsgoto;
    }
    public void setValorDisponibilidadeEsgoto(BigDecimal valorDisponibilidadeEsgoto) {
        this.valorDisponibilidadeEsgoto = valorDisponibilidadeEsgoto;
    }
    public BigDecimal getValorDisponibilidadeEsgotoParcelaFixa() {
        return valorDisponibilidadeEsgotoParcelaFixa;
    }
    public void setValorDisponibilidadeEsgotoParcelaFixa(BigDecimal valorDisponibilidadeEsgotoParcelaFixa) {
        this.valorDisponibilidadeEsgotoParcelaFixa = valorDisponibilidadeEsgotoParcelaFixa;
    }
    
  

}

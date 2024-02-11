package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe;

import moduloFaturamento.dto.situacaoLigacaoAgua.SituacaoLigacaoAguaDTO;
import moduloFaturamento.dto.situacaoLigacaoEsgoto.SituacaoLigacaoEsgotoDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class IncentivoClienteParametroDetalheRespostaConsultaDTO {

    private Integer id;
    private String descricao;
    private LocalDateTime dataRegistro;
    private Integer entradaInicio;
    private Integer entradaFim;
    private Integer descontoMultas;
    private Integer descontoJuros;
    private Integer descontoValorPrincipal;
    private Short numeroMaximoParcelas;
    private Short numeroMinimoDebitos;
    private BigDecimal valorMaximoDebitos;
    private BigDecimal valorMinimoDebitos;
    private BigDecimal valorMinimoParcela;
    private Short numeroMinimoDiasDesconto;
    private Short numeroMaximoParcelasAgencia;
    private BigDecimal valorMaximoDebitoAgencia;
    private Boolean possuiCorrecaoMonetaria;
    private BigDecimal valorCorrecaoMonetaria;
    private List<SituacaoLigacaoAguaDTO> listaSituacaoAgua;
    private List<SituacaoLigacaoEsgotoDTO> listaSituacaoEsgoto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Integer getEntradaInicio() {
        return entradaInicio;
    }

    public void setEntradaInicio(Integer entradaInicio) {
        this.entradaInicio = entradaInicio;
    }

    public Integer getEntradaFim() {
        return entradaFim;
    }

    public void setEntradaFim(Integer entradaFim) {
        this.entradaFim = entradaFim;
    }

    public Integer getDescontoMultas() {
        return descontoMultas;
    }

    public void setDescontoMultas(Integer descontoMultas) {
        this.descontoMultas = descontoMultas;
    }

    public Integer getDescontoJuros() {
        return descontoJuros;
    }

    public void setDescontoJuros(Integer descontoJuros) {
        this.descontoJuros = descontoJuros;
    }

    public Integer getDescontoValorPrincipal() {
        return descontoValorPrincipal;
    }

    public void setDescontoValorPrincipal(Integer descontoValorPrincipal) {
        this.descontoValorPrincipal = descontoValorPrincipal;
    }

    public Short getNumeroMaximoParcelas() {
        return numeroMaximoParcelas;
    }

    public void setNumeroMaximoParcelas(Short numeroMaximoParcelas) {
        this.numeroMaximoParcelas = numeroMaximoParcelas;
    }

    public Short getNumeroMinimoDebitos() {
        return numeroMinimoDebitos;
    }

    public void setNumeroMinimoDebitos(Short numeroMinimoDebitos) {
        this.numeroMinimoDebitos = numeroMinimoDebitos;
    }

    public BigDecimal getValorMinimoDebitos() {
        return valorMinimoDebitos;
    }

    public void setValorMinimoDebitos(BigDecimal valorMinimoDebitos) {
        this.valorMinimoDebitos = valorMinimoDebitos;
    }

    public BigDecimal getValorMinimoParcela() {
        return valorMinimoParcela;
    }

    public void setValorMinimoParcela(BigDecimal valorMinimoParcela) {
        this.valorMinimoParcela = valorMinimoParcela;
    }

    public Short getNumeroMinimoDiasDesconto() {
        return numeroMinimoDiasDesconto;
    }

    public void setNumeroMinimoDiasDesconto(Short numeroMinimoDiasDesconto) {
        this.numeroMinimoDiasDesconto = numeroMinimoDiasDesconto;
    }

    public Short getNumeroMaximoParcelasAgencia() {
        return numeroMaximoParcelasAgencia;
    }

    public void setNumeroMaximoParcelasAgencia(Short numeroMaximoParcelasAgencia) {
        this.numeroMaximoParcelasAgencia = numeroMaximoParcelasAgencia;
    }

    public BigDecimal getValorMaximoDebitoAgencia() {
        return valorMaximoDebitoAgencia;
    }

    public void setValorMaximoDebitoAgencia(BigDecimal valorMaximoDebitoAgencia) {
        this.valorMaximoDebitoAgencia = valorMaximoDebitoAgencia;
    }

    public Boolean getPossuiCorrecaoMonetaria() {
        return possuiCorrecaoMonetaria;
    }

    public void setPossuiCorrecaoMonetaria(Boolean possuiCorrecaoMonetaria) {
        this.possuiCorrecaoMonetaria = possuiCorrecaoMonetaria;
    }

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
    }

    public List<SituacaoLigacaoAguaDTO> getListaSituacaoAgua() {
        return listaSituacaoAgua;
    }

    public void setListaSituacaoAgua(List<SituacaoLigacaoAguaDTO> listaSituacaoAgua) {
        this.listaSituacaoAgua = listaSituacaoAgua;
    }

    public List<SituacaoLigacaoEsgotoDTO> getListaSituacaoEsgoto() {
        return listaSituacaoEsgoto;
    }

    public void setListaSituacaoEsgoto(List<SituacaoLigacaoEsgotoDTO> listaSituacaoEsgoto) {
        this.listaSituacaoEsgoto = listaSituacaoEsgoto;
    }

    public BigDecimal getValorMaximoDebitos() {
        return valorMaximoDebitos;
    }

    public void setValorMaximoDebitos(BigDecimal valorMaximoDebitos) {
        this.valorMaximoDebitos = valorMaximoDebitos;
    }
}

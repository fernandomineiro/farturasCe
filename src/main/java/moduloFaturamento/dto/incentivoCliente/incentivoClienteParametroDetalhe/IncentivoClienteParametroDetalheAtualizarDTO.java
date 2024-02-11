package moduloFaturamento.dto.incentivoCliente.incentivoClienteParametroDetalhe;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class IncentivoClienteParametroDetalheAtualizarDTO {

    @NotBlank @Size(max = 50)
    private String descricao;
    @NotNull
    private BigDecimal entradaInicio;
    @NotNull
    private BigDecimal entradaFim;
    @NotNull
    private Integer descontoMultas;
    @NotNull
    private Integer descontoJuros;
    @NotNull
    private Integer descontoValorPrincipal;
    @NotNull
    private Short numeroMaximoParcelas;
    @NotNull
    private BigDecimal valorMinimoParcela;
    @NotNull
    private Short numeroMinimoDebitos;
    @NotNull
    private BigDecimal valorMinimoDebitos;
    @NotNull
    private BigDecimal ValorMaximoDebitos;
    @NotNull
    private Short numeroMinimoDiasDesconto;
    @NotNull
    private Boolean correcaoMonetaria;
    @NotNull(message="Favor informar o valor padrão '0' quando a correção monetária não for informado")
    private BigDecimal valorCorrecaoMonetaria;
    @NotNull
    private Short numeroMaximoParcelasAgencia;
    @NotNull
    private BigDecimal valorMaximoDebitoAgencia;
    @NotEmpty
    private List<@Valid Integer> situacaoLigacaoAgua;
    @NotEmpty
    private List<@Valid Integer> situacaoLigacaoEsgoto;
    @NotEmpty(message="Favor informar grupo de consumo do parâmetro")
    private List<@Valid Integer> listaGrupoConsumo;
    private Integer id;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getEntradaInicio() {
        return entradaInicio;
    }

    public void setEntradaInicio(BigDecimal entradaInicio) {
        this.entradaInicio = entradaInicio;
    }

    public BigDecimal getEntradaFim() {
        return entradaFim;
    }

    public void setEntradaFim(BigDecimal entradaFim) {
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

    public BigDecimal getValorMinimoParcela() {
        return valorMinimoParcela;
    }

    public void setValorMinimoParcela(BigDecimal valorMinimoParcela) {
        this.valorMinimoParcela = valorMinimoParcela;
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

    public Short getNumeroMinimoDiasDesconto() {
        return numeroMinimoDiasDesconto;
    }

    public void setNumeroMinimoDiasDesconto(Short numeroMinimoDiasDesconto) {
        this.numeroMinimoDiasDesconto = numeroMinimoDiasDesconto;
    }

    public Boolean getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }

    public void setCorrecaoMonetaria(Boolean correcaoMonetaria) {
        this.correcaoMonetaria = correcaoMonetaria;
    }

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
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

    public List<Integer> getSituacaoLigacaoAgua() {
        return situacaoLigacaoAgua;
    }

    public void setSituacaoLigacaoAgua(List<Integer> situacaoLigacaoAgua) {
        this.situacaoLigacaoAgua = situacaoLigacaoAgua;
    }

    public List<Integer> getSituacaoLigacaoEsgoto() {
        return situacaoLigacaoEsgoto;
    }

    public void setSituacaoLigacaoEsgoto(List<Integer> situacaoLigacaoEsgoto) {
        this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorMaximoDebitos() {
        return ValorMaximoDebitos;
    }

    public void setValorMaximoDebitos(BigDecimal valorMaximoDebitos) {
        ValorMaximoDebitos = valorMaximoDebitos;
    }

    public List<Integer> getListaGrupoConsumo() {
        return listaGrupoConsumo;
    }

    public void setListaGrupoConsumo(List<Integer> listaGrupoConsumo) {
        this.listaGrupoConsumo = listaGrupoConsumo;
    }
}

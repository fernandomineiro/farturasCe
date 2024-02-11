package moduloFaturamento.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FAT_INCENTIVO_CLIENTE_DETALHE")
public class IncentivoClienteDetalhe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PARAMETRO_DETALHE")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PARAMETRO_INCENTIVO", referencedColumnName = "CD_PARAMETRO_INCENTIVO")
    private IncentivoCliente incentivoCliente;
    @Column(name = "DESCRICAO")
    @Size(max = 50)
    private String descricao;
    @Column(name = "ENTRADA_INI", scale = 2, precision = 5)
    private BigDecimal entradaInicio;
    @Column(name = "ENTRADA_FIM", scale = 2, precision = 5)
    private BigDecimal entradaFim;
    @Column(name = "DESCONTO_MULTAS")
    private Integer descontoMultas;
    @Column(name = "DESCONTO_JUROS")
    private Integer descontoJuros;
    @Column(name = "DESCONTO_VALOR_PRINCIPAL")
    private Integer descontoValorPrincipal;
    @Column(name = "DT_REGISTRO")
    private LocalDateTime dataRegistro;
    @Column(name = "DATA_HORA_EXCLUSAO")
    private LocalDateTime dataHoraExclusao;
    @Column(name = "NUM_MAX_PARCELAS")
    private Short numeroMaximoParcelas;
    @Column(name = "NUM_MIN_DEBITOS")
    private Short numeroMinimoDebitos;
    @Column(name = "VR_MIN_DEBITOS", scale = 2, precision = 18)
    private BigDecimal valorMinimoDebitos;
    @Column(name = "VR_MAX_DEBITOS",  scale = 2, precision = 18)
    private BigDecimal valorMaximoDebitos;
    @Column(name = "VR_MIN_PARCELA", scale = 2, precision = 10)
    private BigDecimal valorMinimoParcela;
    @Column(name = "NUM_MIN_DIAS_DESC")
    private Short numeroMinimoDiasDesconto;
    @Column(name = "NUM_MAX_PARCELAS_AGENCIA")
    private Short numeroMaximoParcelasAgencia;
    @Column(name = "VR_MAX_DEBITO_AGENCIA", scale = 2, precision = 18)
    private BigDecimal valorMaximoDebitoAgencia;
    @Column(name = "INDICATIVO_CORRECAO_MONETARIA")
    private Short indicativoCorrecaoMonetaria;
    @Column(name = "VR_CORRECAO_MONETARIA", scale = 2, precision = 10)
    private BigDecimal valorCorrecaoMonetaria;

    public IncentivoClienteDetalhe() {
        this.setDataRegistro(LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IncentivoCliente getIncentivoCliente() {
        return incentivoCliente;
    }

    public void setIncentivoCliente(IncentivoCliente incentivoCliente) {
        this.incentivoCliente = incentivoCliente;
    }

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

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDateTime getDataHoraExclusao() {
        return dataHoraExclusao;
    }

    public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
        this.dataHoraExclusao = dataHoraExclusao;
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

    public Short getIndicativoCorrecaoMonetaria() {
        return indicativoCorrecaoMonetaria;
    }

    public void setIndicativoCorrecaoMonetaria(Short indicativoCorrecaoMonetaria) {
        this.indicativoCorrecaoMonetaria = indicativoCorrecaoMonetaria;
    }

    public BigDecimal getValorCorrecaoMonetaria() {
        return valorCorrecaoMonetaria;
    }

    public void setValorCorrecaoMonetaria(BigDecimal valorCorrecaoMonetaria) {
        this.valorCorrecaoMonetaria = valorCorrecaoMonetaria;
    }

    public BigDecimal getValorMaximoDebitos() {
        return valorMaximoDebitos;
    }

    public void setValorMaximoDebitos(BigDecimal valorMaximoDebitos) {
        this.valorMaximoDebitos = valorMaximoDebitos;
    }
}

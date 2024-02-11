package moduloFaturamento.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "FAT_HISTORICO_INCENTIVO_CLIENTE_DETALHE")
public class IncentivoClienteHistoricoDetalhe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CD_PARAMETRO_DETALHE")
    private Integer idParametroDetalhe;
    @Column(name = "CD_PARAMETRO_INCENTIVO")
    private Integer idIncentivoCliente;
    @Column(name = "DESCRICAO") @Size(max = 50)
    private String descricao;

   
    @Column(name = "ENTRADA_INI")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal entradaInicio;

   
   
    @Column(name = "ENTRADA_FIM")
    @Digits(integer = 5, fraction = 2)
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

    @Column(name = "DATA_REGISTRO_HISTORICO")
    private LocalDateTime dataHoraRegistroHistorico;

    @Column(name = "NUM_MAX_PARCELAS")
    private Short numeroMaximoParcelas;

    @Column(name = "NUM_MIN_DEBITOS")
    private Short numeroMinimoDebitos;

    @Column(name = "VR_MIN_DEBITOS") 
    @Digits(integer=16, fraction=2)
    private BigDecimal valorMinimoDebitos;

    @Column(name = "VR_MAX_DEBITOS") 
    @Digits(integer=16, fraction=2)
    private BigDecimal valorMaximoDebitos;

    @Column(name = "VR_MIN_PARCELA") 
    @Digits(integer=16, fraction=2)
    private BigDecimal valorMinimoParcela;

    @Column(name = "NUM_MIN_DIAS_DESC")
    private Short numeroMinimoDiasDesconto;

    @Column(name = "NUM_MAX_PARCELAS_AGENCIA")
    private Short numeroMaximoParcelasAgencia;

    @Column(name = "VR_MAX_DEBITO_AGENCIA") 
    @Digits(integer=16, fraction=2)
    private BigDecimal valorMaximoDebitoAgencia;

    @Column(name = "INDICATIVO_CORRECAO_MONETARIA")
    private Short indicativoCorrecaoMonetaria;

    @Column(name = "VR_CORRECAO_MONETARIA") 
    @Digits(integer=16, fraction=2)
    private BigDecimal valorCorrecaoMonetaria;

    public IncentivoClienteHistoricoDetalhe() {
        this.setDataRegistro(LocalDateTime.now());
    }


    public IncentivoClienteHistoricoDetalhe(IncentivoClienteDetalhe entidade) {
        this.setIdParametroDetalhe(entidade.getId());
        this.setIdIncentivoCliente(entidade.getIncentivoCliente().getId());
        this.setDescricao(entidade.getDescricao());
        this.setEntradaInicio(entidade.getEntradaInicio());
        this.setEntradaFim(entidade.getEntradaFim());
        this.setDescontoMultas(entidade.getDescontoMultas());
        this.setDescontoJuros(entidade.getDescontoJuros());
        this.setDescontoValorPrincipal(entidade.getDescontoValorPrincipal());
        this.setDataRegistro(entidade.getDataRegistro());
        this.setDataHoraExclusao(entidade.getDataHoraExclusao());
        this.setDataHoraRegistroHistorico(LocalDateTime.now());
        this.setNumeroMaximoParcelas(entidade.getNumeroMaximoParcelas());
        this.setNumeroMaximoParcelasAgencia(entidade.getNumeroMaximoParcelasAgencia());
        this.setValorMaximoDebitoAgencia(entidade.getValorMaximoDebitoAgencia());
        this.setNumeroMinimoDebitos(entidade.getNumeroMinimoDebitos());
        this.setValorMinimoDebitos(entidade.getValorMinimoDebitos());
        this.setValorMaximoDebitos(entidade.getValorMaximoDebitos());
        this.setValorMinimoParcela(entidade.getValorMinimoParcela());
        this.setNumeroMinimoDiasDesconto(entidade.getNumeroMinimoDiasDesconto());
        this.setIndicativoCorrecaoMonetaria(entidade.getIndicativoCorrecaoMonetaria());
        this.setValorCorrecaoMonetaria(entidade.getValorCorrecaoMonetaria());
    }

    public IncentivoClienteHistoricoDetalhe(Integer id) {
        this.setId(id.longValue());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdParametroDetalhe() {
        return idParametroDetalhe;
    }

    public void setIdParametroDetalhe(Integer idParametroDetalhe) {
        this.idParametroDetalhe = idParametroDetalhe;
    }

    public Integer getIdIncentivoCliente() {
        return idIncentivoCliente;
    }

    public void setIdIncentivoCliente(Integer idIncentivoCliente) {
        this.idIncentivoCliente = idIncentivoCliente;
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

    public LocalDateTime getDataHoraRegistroHistorico() {
        return dataHoraRegistroHistorico;
    }

    public void setDataHoraRegistroHistorico(LocalDateTime dataHoraRegistroHistorico) {
        this.dataHoraRegistroHistorico = dataHoraRegistroHistorico;
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

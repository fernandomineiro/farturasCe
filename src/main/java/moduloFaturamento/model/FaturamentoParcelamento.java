package moduloFaturamento.model;

import moduloFaturamento.model.common.Imovel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FTTPC")
public class FaturamentoParcelamento {

	@Id
	@Column(name = "CD_PARCELAMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoParcelamento;

	@Column(name = "CD_CLIENTE")
	private Integer cdCliente;

	@ManyToOne
	@JoinColumn(name = "MATRICULA_IMOVEL")
	private Imovel imovel;

	@Column(name = "ID_USUARIO")
	private String loginUsuario;

	@Column(name = "DT_PARCELAMENTO")
	private Integer dataParcelamento;

	@Column(name = "HORA_PARCELAMENTO")
	private Integer horaParcelamento;

	@Column(name = "INDICE_REAJUSTE")
	private BigDecimal indiceReajuste;

	@Column(name="STATUS_PARCELAMENTO")
	private Short statusParcelamento;

	@Column(name = "DT_ENCERRAMENTO")
	private Integer dataEncerramento;

	@Column(name = "HORA_ENCERRAMENTO")
	private Integer horaEncerramento;

	@Column(name = "QTDE_PARCELAS")
	private Short quantidadeParcelas;

	@Column(name = "ORIGEM_FATURA_PARCELAMENTO")
	private Short origemFaturaParcelamento;

	@Column(name = "VR_ENTRADA")
	private BigDecimal valorEntrada;

	@Column(name = "VR_DESCONTO_NEGOCIACAO")
	private BigDecimal valorDescontoNegociacao;

	@Column(name = "DIA_VENCIMENTO")
	private Short diaVencimento;

	@Column(name = "CS_CORTAR")
	private String csCortar;

	@Column(name = "CS_ENVIAR_COB")
	private String csEmviarCobranca;

	@Column(name = "VR_AGUA")
	private BigDecimal valorAgua;

	@Column(name = "VR_ESGOTO")
	private BigDecimal valorEsgoto;

	@Column(name = "VR_OUTROS_SERVICOS")
	private BigDecimal valorOutrosServicos;

	@Column(name = "VR_JUROS")
	private BigDecimal valorJuros;

	@Column(name = "VR_MULTA")
	private BigDecimal valorMulta;

	@Column(name = "DESC_AUTOMATICOS_PRINCIPAL")
	private BigDecimal descontroAutomaticosDoPrincipal;
	
	@Column(name = "DESC_AUTOMATICOS_FINANCEIROS")
	private BigDecimal descontosAutomaticosDoFinanceiros;

	@Column(name = "DESC_CREDITO_CLIENTE")
	private BigDecimal descontrosCreditoDoCliente;

	@Column(name = "VR_CORRECAO_MONETARIA")
	private BigDecimal valorCorrecaoMonetraria;

	public Integer getCodigoParcelamento() {
		return codigoParcelamento;
	}
	public void setCodigoParcelamento(Integer codigoParcelamento) {
		this.codigoParcelamento = codigoParcelamento;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	public Short getStatusParcelamento() {
		return statusParcelamento;
	}
	public void setStatusParcelamento(Short statusParcelamento) {
		this.statusParcelamento = statusParcelamento;
	}
	public Integer getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	public String getLoginUsuario() {
		return loginUsuario;
	}
	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	public Integer getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(Integer dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public Integer getHoraParcelamento() {
		return horaParcelamento;
	}
	public void setHoraParcelamento(Integer horaParcelamento) {
		this.horaParcelamento = horaParcelamento;
	}
	public BigDecimal getIndiceReajuste() {
		return indiceReajuste;
	}
	public void setIndiceReajuste(BigDecimal indiceReajuste) {
		this.indiceReajuste = indiceReajuste;
	}
	public Integer getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(Integer dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public Integer getHoraEncerramento() {
		return horaEncerramento;
	}
	public void setHoraEncerramento(Integer horaEncerramento) {
		this.horaEncerramento = horaEncerramento;
	}
	public Short getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Short quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public Short getOrigemFaturaParcelamento() {
		return origemFaturaParcelamento;
	}
	public void setOrigemFaturaParcelamento(Short origemFaturaParcelamento) {
		this.origemFaturaParcelamento = origemFaturaParcelamento;
	}
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public BigDecimal getValorDescontoNegociacao() {
		return valorDescontoNegociacao;
	}
	public void setValorDescontoNegociacao(BigDecimal valorDescontoNegociacao) {
		this.valorDescontoNegociacao = valorDescontoNegociacao;
	}
	public Short getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getCsCortar() {
		return csCortar;
	}
	public void setCsCortar(String csCortar) {
		this.csCortar = csCortar;
	}
	public String getCsEmviarCobranca() {
		return csEmviarCobranca;
	}
	public void setCsEmviarCobranca(String csEmviarCobranca) {
		this.csEmviarCobranca = csEmviarCobranca;
	}
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}
	public BigDecimal getValorOutrosServicos() {
		return valorOutrosServicos;
	}
	public void setValorOutrosServicos(BigDecimal valorOutrosServicos) {
		this.valorOutrosServicos = valorOutrosServicos;
	}
	public BigDecimal getValorJuros() {
		return valorJuros;
	}
	public void setValorJuros(BigDecimal valorJuros) {
		this.valorJuros = valorJuros;
	}
	public BigDecimal getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	public BigDecimal getDescontroAutomaticosDoPrincipal() {
		return descontroAutomaticosDoPrincipal;
	}
	public void setDescontroAutomaticosDoPrincipal(BigDecimal descontroAutomaticosDoPrincipal) {
		this.descontroAutomaticosDoPrincipal = descontroAutomaticosDoPrincipal;
	}
	public BigDecimal getDescontosAutomaticosDoFinanceiros() {
		return descontosAutomaticosDoFinanceiros;
	}
	public void setDescontosAutomaticosDoFinanceiros(BigDecimal descontosAutomaticosDoFinanceiros) {
		this.descontosAutomaticosDoFinanceiros = descontosAutomaticosDoFinanceiros;
	}
	public BigDecimal getDescontrosCreditoDoCliente() {
		return descontrosCreditoDoCliente;
	}
	public void setDescontrosCreditoDoCliente(BigDecimal descontrosCreditoDoCliente) {
		this.descontrosCreditoDoCliente = descontrosCreditoDoCliente;
	}
	public BigDecimal getValorCorrecaoMonetraria() {
		return valorCorrecaoMonetraria;
	}
	public void setValorCorrecaoMonetraria(BigDecimal valorCorrecaoMonetraria) {
		this.valorCorrecaoMonetraria = valorCorrecaoMonetraria;
	}

		
}

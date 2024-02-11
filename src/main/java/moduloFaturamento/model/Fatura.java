package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTFA")

public class Fatura {

	@EmbeddedId
	private IdFatura idFatura;

	

	@Column(name = "TP_BAIXA", length = 1, precision = 3, nullable = false)
	private Short tpBaixa;

	@Column(name = "CD_CIDADE", length = 2, precision = 5, nullable = false)
	private Short cdCidade;

	@Column(name = "CICLO", length = 1, precision = 3, nullable = false)
	private Short ciclo;

	@Column(name = "DT_VENCIMENTO", length = 4, precision = 10, nullable = false)
	private int dtVencimento;

	@Column(name = "DV", length = 1, precision = 3, nullable = false)
	private Short dv;

	@Column(name = "ID", insertable = false, updatable = false)
	private Long id;
	
	@Column(name = "VR_FATURA", length = 9, precision = 10, scale = 2, nullable = false)
	private BigDecimal valorFatura;

	@Column(name = "FASE", length = 1, precision = 3, nullable = false)
	private Short fase;

	@Column(name = "CD_CLIENTE", length = 4, precision = 4, nullable = false)
	private int cdCliente;

	@Column(name = "DIA_BAIXA", length = 1, precision = 3, nullable = false)
	private short diaBaixa;

	@Column(name = "REF_BAIXA", length = 4, precision = 10, nullable = false)
	private int referenciaBaixa;

	@Column(name = "REF_FATURA", length = 4, precision = 10, nullable = false, insertable = false, updatable = false)
	private int refFatura;

	@Column(name = "REF_BXA_CONTABIL", length = 4, precision = 10, nullable = false)
	private int referenciaBaixaContabil;

	
	@Column(name = "TP_CONSUMO_FATURADO", length = 1, precision = 3, nullable = false)
	private short tipoConsumoFaturado;

	@Column(name = "TP_ENTREGA_FATURA", length = 1, precision = 3, nullable = false)
	private short tipoEntregaFatura;

	@Column(name = "TRATAMENTO_ESGOTO", length = 1, nullable = false)
	private String tratamentoEsgoto;

	@Column(name = "REF_BXA_PERDAS", length = 4, precision = 10, nullable = false)
	private int referenciaBaixaPerdas;

	@Column(name = "REF_CONTABIL", length = 4, precision = 10, nullable = false)
	private int referenciaContabil;

	@Column(name = "REF_ATENDIMENTO", length = 4, precision = 10, nullable = false)
	private int referenciaAtendimento;

	@Column(name = "CD_ATENDIMENTO", length = 4, precision = 10, nullable = false)
	private int codigoAtendimento;

	@Column(name = "SEQ_ATEND", length = 1, precision = 3, nullable = false)
	private Short sequenciaAtendimento;

	@Column(name = "CD_CLIENTE_PRINCIPAL", length = 4, precision = 10, nullable = false)
	private int codigoClientePrincipal;

	@Column(name = "CD_TARIFA", length = 2, precision = 5, nullable = false)
	private int codigoTarifa;

	@Column(name = "DESC_ESGOTO", length = 1, precision = 3, nullable = false)
	private int descontoEsgoto;

	@Column(name = "DEVEDOR_DUVIDOSO", length = 1, nullable = false)
	private String devedorDuvidoso;

	@Column(name = "DIAS_CONSUMO", length = 2, precision = 5, nullable = false)
	private int diasConsumo;

	@Column(name = "DIAS_VENDA", length = 2, precision = 5, nullable = false)
	private int diasVenda;

	@Column(name = "DOC_BAIXA", length = 1, precision = 3, nullable = false)
	private Short documentoBaixa;

	@Column(name = "DT_TARIFA", length = 4, precision = 10, nullable = false)
	private int dataTarifa;

	@Column(name = "FATURA_EMITIDA", length = 1, nullable = false)
	private String faturaEmitida;

	@Column(name = "FORMA_ENTREGA", length = 1, precision = 3, nullable = false)
	private Short formaEntrega;

	@Column(name = "GRUPO_CONSUMO", length = 1, precision = 3, nullable = false)
	private Short grupoConsumo;

	@Column(name = "ID_USUARIO", length = 15, nullable = false)
	private String usuario;

	@Column(name = "MAINT", length = 1, nullable = false)
	private String maint;

	@Column(name = "MATRICULA_IMOVEL", length = 4, precision = 10, nullable = false, insertable = false, updatable = false)
	private int matriculaImovel;

	@Column(name = "ORIGEM_FATURA", length = 1, precision = 3, nullable = false, insertable = false, updatable = false)
	private Short origemFatura;

	@Column(name = "PERC_TARIFA", length = 2, precision = 5, nullable = false)
	private int percentualTarifa;

	@Column(name = "UN_CONSUMO", length = 2, precision = 5, nullable = false)
	private int unidadeConsumo;

	@Column(name = "VOL_AGUA", length = 5, precision = 9, scale = 1, nullable = false)
	private BigDecimal volumeAgua;

	@Column(name = "VOL_ESGOTO", length = 5, precision = 9, scale = 1, nullable = false)
	private BigDecimal volumeEsgoto;

	@Column(name = "ID_LOTE", length = 4, precision = 10, nullable = false)
	private int idLote;

	@Column(name = "CS_BAIXA_ENVIO", length = 1, nullable = false)
	private String csBaixaEnvio;

	@Column(name = "CS_NOTIFICACAO", length = 1, nullable = false)
	private String csNotificacao;

	@Column(name = "TP_PDD", length = 1, precision = 3, scale = 0, nullable = false)
	private Short tipoPdd;

	@Column(name = "MOT_REFATURAMENTO", length = 1, precision = 3, nullable = false)
	private Short motivoRefaturamento;

	@Column(name = "VOL_DISP_ESG", length = 5, precision = 9, scale = 1, nullable = false)
	private BigDecimal volumeDispEsg;

	@Column(name = "UN_CONSUMO_CAD", length = 2, precision = 5, scale = 0, nullable = false)
	private int unidadeConsumoCad;

	@Column(name = "CRIT_FATU_DECI_JUDI", length = 1, precision = 3, nullable = false)
	private short criterioFatDeciJudi;

	@Column(name = "VERSAO_FATURA", length = 1, precision = 3, nullable = false)
	private Short versaoFatura;

	@Column(name = "TP_MEDICAO_ESG", length = 1, precision = 3, nullable = false)
	private Short tipoMedicaoEsg;

	public IdFatura getIdFatura() {
		return idFatura;
	}

	public void setIdFatura(IdFatura idFatura) {
		this.idFatura = idFatura;
	}

	public Short getTpBaixa() {
		return tpBaixa;
	}

	public void setTpBaixa(Short tpBaixa) {
		this.tpBaixa = tpBaixa;
	}

	public int getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Short getCiclo() {
		return ciclo;
	}

	public void setCiclo(Short ciclo) {
		this.ciclo = ciclo;
	}

	public int getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(int dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Short getDv() {
		return dv;
	}

	public void setDv(Short dv) {
		this.dv = dv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	public Short getFase() {
		return fase;
	}

	public void setFase(Short fase) {
		this.fase = fase;
	}

	public int getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(int cdCliente) {
		this.cdCliente = cdCliente;
	}

	public short getDiaBaixa() {
		return diaBaixa;
	}

	public void setDiaBaixa(short diaBaixa) {
		this.diaBaixa = diaBaixa;
	}

	public int getReferenciaBaixa() {
		return referenciaBaixa;
	}

	public void setReferenciaBaixa(int referenciaBaixa) {
		this.referenciaBaixa = referenciaBaixa;
	}

	public int getRefFatura() {
		return refFatura;
	}

	public void setRefFatura(int refFatura) {
		this.refFatura = refFatura;
	}

	public int getReferenciaBaixaContabil() {
		return referenciaBaixaContabil;
	}

	public void setReferenciaBaixaContabil(int referenciaBaixaContabil) {
		this.referenciaBaixaContabil = referenciaBaixaContabil;
	}

	public short getTipoConsumoFaturado() {
		return tipoConsumoFaturado;
	}

	public void setTipoConsumoFaturado(short tipoConsumoFaturado) {
		this.tipoConsumoFaturado = tipoConsumoFaturado;
	}

	public short getTipoEntregaFatura() {
		return tipoEntregaFatura;
	}

	public void setTipoEntregaFatura(short tipoEntregaFatura) {
		this.tipoEntregaFatura = tipoEntregaFatura;
	}

	public String getTratamentoEsgoto() {
		return tratamentoEsgoto;
	}

	public void setTratamentoEsgoto(String tratamentoEsgoto) {
		this.tratamentoEsgoto = tratamentoEsgoto;
	}

	public int getReferenciaBaixaPerdas() {
		return referenciaBaixaPerdas;
	}

	public void setReferenciaBaixaPerdas(int referenciaBaixaPerdas) {
		this.referenciaBaixaPerdas = referenciaBaixaPerdas;
	}

	public int getReferenciaContabil() {
		return referenciaContabil;
	}

	public void setReferenciaContabil(int referenciaContabil) {
		this.referenciaContabil = referenciaContabil;
	}

	public int getReferenciaAtendimento() {
		return referenciaAtendimento;
	}

	public void setReferenciaAtendimento(int referenciaAtendimento) {
		this.referenciaAtendimento = referenciaAtendimento;
	}

	public int getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(int codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	public Short getSequenciaAtendimento() {
		return sequenciaAtendimento;
	}

	public void setSequenciaAtendimento(Short sequenciaAtendimento) {
		this.sequenciaAtendimento = sequenciaAtendimento;
	}

	public int getCodigoClientePrincipal() {
		return codigoClientePrincipal;
	}

	public void setCodigoClientePrincipal(int codigoClientePrincipal) {
		this.codigoClientePrincipal = codigoClientePrincipal;
	}

	public int getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(int codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public int getDescontoEsgoto() {
		return descontoEsgoto;
	}

	public void setDescontoEsgoto(int descontoEsgoto) {
		this.descontoEsgoto = descontoEsgoto;
	}

	public String getDevedorDuvidoso() {
		return devedorDuvidoso;
	}

	public void setDevedorDuvidoso(String devedorDuvidoso) {
		this.devedorDuvidoso = devedorDuvidoso;
	}

	public int getDiasConsumo() {
		return diasConsumo;
	}

	public void setDiasConsumo(int diasConsumo) {
		this.diasConsumo = diasConsumo;
	}

	public int getDiasVenda() {
		return diasVenda;
	}

	public void setDiasVenda(int diasVenda) {
		this.diasVenda = diasVenda;
	}

	public Short getDocumentoBaixa() {
		return documentoBaixa;
	}

	public void setDocumentoBaixa(Short documentoBaixa) {
		this.documentoBaixa = documentoBaixa;
	}

	public int getDataTarifa() {
		return dataTarifa;
	}

	public void setDataTarifa(int dataTarifa) {
		this.dataTarifa = dataTarifa;
	}

	public String getFaturaEmitida() {
		return faturaEmitida;
	}

	public void setFaturaEmitida(String faturaEmitida) {
		this.faturaEmitida = faturaEmitida;
	}

	public Short getFormaEntrega() {
		return formaEntrega;
	}

	public void setFormaEntrega(Short formaEntrega) {
		this.formaEntrega = formaEntrega;
	}

	public Short getGrupoConsumo() {
		return grupoConsumo;
	}

	public void setGrupoConsumo(Short grupoConsumo) {
		this.grupoConsumo = grupoConsumo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public int getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(int matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public Short getOrigemFatura() {
		return origemFatura;
	}

	public void setOrigemFatura(Short origemFatura) {
		this.origemFatura = origemFatura;
	}

	public int getPercentualTarifa() {
		return percentualTarifa;
	}

	public void setPercentualTarifa(int percentualTarifa) {
		this.percentualTarifa = percentualTarifa;
	}

	public int getUnidadeConsumo() {
		return unidadeConsumo;
	}

	public void setUnidadeConsumo(int unidadeConsumo) {
		this.unidadeConsumo = unidadeConsumo;
	}

	public BigDecimal getVolumeAgua() {
		return volumeAgua;
	}

	public void setVolumeAgua(BigDecimal volumeAgua) {
		this.volumeAgua = volumeAgua;
	}

	public BigDecimal getVolumeEsgoto() {
		return volumeEsgoto;
	}

	public void setVolumeEsgoto(BigDecimal volumeEsgoto) {
		this.volumeEsgoto = volumeEsgoto;
	}

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public String getCsBaixaEnvio() {
		return csBaixaEnvio;
	}

	public void setCsBaixaEnvio(String csBaixaEnvio) {
		this.csBaixaEnvio = csBaixaEnvio;
	}

	public String getCsNotificacao() {
		return csNotificacao;
	}

	public void setCsNotificacao(String csNotificacao) {
		this.csNotificacao = csNotificacao;
	}

	public Short getTipoPdd() {
		return tipoPdd;
	}

	public void setTipoPdd(Short tipoPdd) {
		this.tipoPdd = tipoPdd;
	}

	public Short getMotivoRefaturamento() {
		return motivoRefaturamento;
	}

	public void setMotivoRefaturamento(Short motivoRefaturamento) {
		this.motivoRefaturamento = motivoRefaturamento;
	}

	public BigDecimal getVolumeDispEsg() {
		return volumeDispEsg;
	}

	public void setVolumeDispEsg(BigDecimal volumeDispEsg) {
		this.volumeDispEsg = volumeDispEsg;
	}

	public int getUnidadeConsumoCad() {
		return unidadeConsumoCad;
	}

	public void setUnidadeConsumoCad(int unidadeConsumoCad) {
		this.unidadeConsumoCad = unidadeConsumoCad;
	}

	public short getCriterioFatDeciJudi() {
		return criterioFatDeciJudi;
	}

	public void setCriterioFatDeciJudi(short criterioFatDeciJudi) {
		this.criterioFatDeciJudi = criterioFatDeciJudi;
	}

	public Short getVersaoFatura() {
		return versaoFatura;
	}

	public void setVersaoFatura(Short versaoFatura) {
		this.versaoFatura = versaoFatura;
	}

	public Short getTipoMedicaoEsg() {
		return tipoMedicaoEsg;
	}

	public void setTipoMedicaoEsg(Short tipoMedicaoEsg) {
		this.tipoMedicaoEsg = tipoMedicaoEsg;
	}

}



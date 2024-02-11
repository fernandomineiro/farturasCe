package moduloFaturamento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTEV")
public class FaturaAvulsa {

    @EmbeddedId
	private IdFaturaAvulsa idFaturaAvulsa;
    
	@Column(name = "REF_ATENDIMENTO")
	private Integer refAtendimento;
	@Column(name = "CD_ATENDIMENTO")
	private Integer cdAtendimento;
	@Column(name = "SEQ_SS")
	private Short seqSS;

	@Column(name = "CD_CIDADE")
	private Short cdCidade;

	@Column(name = "DIA_BAIXA")
	private Short diaBaixa;

	@Column(name = "DOC_BAIXA")
	private Short docBaixa;

	@Column(name = "DT_VENCIMENTO")
	private Integer dataVencimento;

	@Column(name = "DV")
	private Short dvCliente;

	@Column(name = "GRUPO_CONSUMO")
	private Short grupoConsumo;

	@Column(name = "MAINT")
	private Character maint = 'A';

	@Column(name = "MENSAGEM_01")
	private String mensagem01;

	@Column(name = "MENSAGEM_02")
	private String mensagem02;

	@Column(name = "REF_BAIXA")
	private String refBaixa;

	@Column(name = "REF_BXA_CONTABIL")
	private Integer refBaixaContabil;

	@Column(name = "REF_CONTABIL")
	private Integer refContabil;

	@Column(name = "TP_BAIXA")
	private Short tipoBaixa;

	@Column(name = "VR_FATURA")
	private BigDecimal valorFatura;

	@Column(name = "ID", insertable = false, updatable = false)
	private Long id;

	@Column(name = "MATRICULA_IMOVEL")
	private Integer matriculaImovel;

	public IdFaturaAvulsa getIdFaturaAvulsa() {
		return idFaturaAvulsa;
	}

	public void setIdFaturaAvulsa(IdFaturaAvulsa idFaturaAvulsa) {
		this.idFaturaAvulsa = idFaturaAvulsa;
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

	public Short getCdCidade() {
		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {
		this.cdCidade = cdCidade;
	}

	public Short getDiaBaixa() {
		return diaBaixa;
	}

	public void setDiaBaixa(Short diaBaixa) {
		this.diaBaixa = diaBaixa;
	}

	public Short getDocBaixa() {
		return docBaixa;
	}

	public void setDocBaixa(Short docBaixa) {
		this.docBaixa = docBaixa;
	}

	public Integer getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Integer dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Short getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(Short dvCliente) {
		this.dvCliente = dvCliente;
	}

	public Short getGrupoConsumo() {
		return grupoConsumo;
	}

	public void setGrupoConsumo(Short grupoConsumo) {
		this.grupoConsumo = grupoConsumo;
	}

	public Character getMaint() {
		return maint;
	}

	public void setMaint(Character maint) {
		this.maint = maint;
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

	public String getRefBaixa() {
		return refBaixa;
	}

	public void setRefBaixa(String refBaixa) {
		this.refBaixa = refBaixa;
	}

	public Integer getRefBaixaContabil() {
		return refBaixaContabil;
	}

	public void setRefBaixaContabil(Integer refBaixaContabil) {
		this.refBaixaContabil = refBaixaContabil;
	}

	public Integer getRefContabil() {
		return refContabil;
	}

	public void setRefContabil(Integer refContabil) {
		this.refContabil = refContabil;
	}

	public Short getTipoBaixa() {
		return tipoBaixa;
	}

	public void setTipoBaixa(Short tipoBaixa) {
		this.tipoBaixa = tipoBaixa;
	}

	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

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

    
}

package moduloFaturamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import moduloFaturamento.model.common.Servico;

@Entity
@Table(name = "CTTCF")

public class ConfiguracaoContabil implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_CONFIG")
	private Integer cdConfig;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_SERVICO", referencedColumnName = "CD_SERVICO")
	private Servico servico;

	@Column(name = "D_C_SERVICO", length = 1)
	private String dCServico;

	@Column(name = "DT_INI_VALIDADE")
	private Integer dtIniValidade;

	@Column(name = "DT_FIM_VALIDADE")
	private Integer dtFimValidade;

	@Column(name = "INFORMAR_CCUSTO", length = 1)
	private String informarCCusto;

	@Column(name = "D_C_CTB_SAP", length = 1)
	private String dCCtbSap;

	@Column(name = "MAINT", length = 1)
	private String maint;

	@Column(name = "CD_EVENTO", length = 2, precision = 5)
	private Short cdEvento;

	@Column(name = "NR_CONTA_CTB_SAP", length = 4, precision = 10, scale = 0)
	private Integer nrContaCtbSap;

	public Integer getCdConfig() {
		return cdConfig;
	}

	public void setCdConfig(Integer cdConfig) {
		this.cdConfig = cdConfig;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getdCServico() {
		return dCServico;
	}

	public void setdCServico(String dCServico) {
		this.dCServico = dCServico;
	}

	public Integer getDtIniValidade() {
		return dtIniValidade;
	}

	public void setDtIniValidade(Integer dtIniValidade) {
		this.dtIniValidade = dtIniValidade;
	}

	public Integer getDtFimValidade() {
		return dtFimValidade;
	}

	public void setDtFimValidade(Integer dtFimValidade) {
		this.dtFimValidade = dtFimValidade;
	}

	public String getInformarCCusto() {
		return informarCCusto;
	}

	public void setInformarCCusto(String informarCCusto) {
		this.informarCCusto = informarCCusto;
	}

	public String getdCCtbSap() {
		return dCCtbSap;
	}

	public void setdCCtbSap(String dCCtbSap) {
		this.dCCtbSap = dCCtbSap;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public Short getCdEvento() {
		return cdEvento;
	}

	public void setCdEvento(Short cdEvento) {
		this.cdEvento = cdEvento;
	}

	public Integer getNrContaCtbSap() {
		return nrContaCtbSap;
	}

	public void setNrContaCtbSap(Integer nrContaCtbSap) {
		this.nrContaCtbSap = nrContaCtbSap;
	}

}

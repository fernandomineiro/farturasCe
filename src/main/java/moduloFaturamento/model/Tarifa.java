package moduloFaturamento.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FTTTR")
public class Tarifa implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IdTarifa id;

	@Column(name = "VR_AGUA")
	private BigDecimal valorAgua;
	@Column(name = "VR_AGUA_PFIXA")
	private BigDecimal valorAguaParcelaFixa;

	@Column(name = "VR_ESG_TRATADO")
	private BigDecimal valorEsgotoTratado;
	@Column(name = "VR_ESG_TRAT_PFIXA")
	private BigDecimal valorEsgotoTratadoParcelaFixa;

	@Column(name = "VR_ESG_N_TRATADO")
	private BigDecimal valorEsgotoNaoTratado;
	@Column(name = "VR_ESG_N_TRAT_PFIXA")
	private BigDecimal valorEsgotoNaoTratadoParcelaFixa;

	@Column(name = "VR_DISP_ESG")
	private BigDecimal valorDisponibilidadeEsgoto;
	@Column(name = "VR_DISP_ESG_PFIXA")
	private BigDecimal valorDisponibilidadeEsgotoParcelaFixa;

	@Column(name = "ID", insertable = false, updatable = false)
	private Long idAuditoria;

	@Column(name = "MAINT")
	private String maint = "A";

	public Tarifa() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IdTarifa getId() {
		return id;
	}

	public void setId(IdTarifa id) {
		this.id = id;
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

	public Long getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(Long idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	
}

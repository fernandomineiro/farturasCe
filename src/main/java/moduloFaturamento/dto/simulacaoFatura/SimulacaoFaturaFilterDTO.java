package moduloFaturamento.dto.simulacaoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SimulacaoFaturaFilterDTO {
	
	private Integer matricula;
	
	private Short dv;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate referencia;

	@NotNull
	private Short cdCidade;

	@NotNull
	private Short grupoDeConsumo;

	@NotNull
	private Boolean agua;
	
	private String esgoto;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataLeituraAnterior;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataLeituraAtual;
	@NotNull
	private Short dataLeituraDiasVenda;

	private Short cdOcorrencia;

	private BigDecimal leituraAnterior;
	private BigDecimal leituraAtual;
	private BigDecimal leituraConsumo;

	public Integer getMatricula() {

		return matricula;
	}

	public void setMatricula(Integer matricula) {

		this.matricula = matricula;
	}

	public Short getDv() {

		return dv;
	}

	public void setDv(Short dv) {

		this.dv = dv;
	}

	public LocalDate getReferencia() {

		return referencia;
	}

	public void setReferencia(LocalDate referencia) {

		this.referencia = referencia;
	}

	public Short getCdCidade() {

		return cdCidade;
	}

	public void setCdCidade(Short cdCidade) {

		this.cdCidade = cdCidade;
	}

	public Short getGrupoDeConsumo() {

		return grupoDeConsumo;
	}

	public void setGrupoDeConsumo(Short grupoDeConsumo) {

		this.grupoDeConsumo = grupoDeConsumo;
	}

	public Boolean getAgua() {

		return agua;
	}

	public void setAgua(Boolean agua) {

		this.agua = agua;
	}

	public String getEsgoto() {

		return esgoto;
	}

	public void setEsgoto(String esgoto) {

		this.esgoto = esgoto;
	}

	public LocalDate getDataLeituraAnterior() {

		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(LocalDate dataLeituraAnterior) {

		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public LocalDate getDataLeituraAtual() {

		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(LocalDate dataLeituraAtual) {

		this.dataLeituraAtual = dataLeituraAtual;
	}

	public Short getDataLeituraDiasVenda() {

		return dataLeituraDiasVenda;
	}

	public void setDataLeituraDiasVenda(Short dataLeituraDiasVenda) {

		this.dataLeituraDiasVenda = dataLeituraDiasVenda;
	}

	public Short getCdOcorrencia() {

		return cdOcorrencia;
	}

	public void setCdOcorrencia(Short cdOcorrencia) {

		this.cdOcorrencia = cdOcorrencia;
	}

	public BigDecimal getLeituraAnterior() {

		return leituraAnterior;
	}

	public void setLeituraAnterior(BigDecimal leituraAnterior) {

		this.leituraAnterior = leituraAnterior;
	}

	public BigDecimal getLeituraAtual() {

		return leituraAtual;
	}

	public void setLeituraAtual(BigDecimal leituraAtual) {

		this.leituraAtual = leituraAtual;
	}

	public BigDecimal getLeituraConsumo() {

		return leituraConsumo;
	}

	public void setLeituraConsumo(BigDecimal leituraConsumo) {

		this.leituraConsumo = leituraConsumo;
	}
}

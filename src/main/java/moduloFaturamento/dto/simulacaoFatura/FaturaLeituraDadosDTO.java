package moduloFaturamento.dto.simulacaoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

import moduloFaturamento.dto.LocalidadeDTO;
import moduloFaturamento.dto.grupoDeConsumo.GrupoConsumoDropDownProjectionDTO;
import moduloFaturamento.dto.ocorrenciaLeiturao.OcorrenciaLeituraDropDownProjectionDTO;

public class FaturaLeituraDadosDTO {

	private Integer matricula;

	private Short dv;

	private LocalDate referencia;

	private Boolean agua;
	private String esgoto;

	private LocalDate dataLeituraAtual;
	private LocalDate dataLeituraAnterior;
	private Short dataLeituraDiasVenda;

	private BigDecimal leituraAtual;
	private BigDecimal leituraAnterior;
	private BigDecimal leituraConsumo;

	private LocalidadeDTO localidadeDTO;

	private GrupoConsumoDropDownProjectionDTO grupoConsumoDTO;

	private OcorrenciaLeituraDropDownProjectionDTO ocorrenciaLeituraDTO;

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

	public LocalDate getDataLeituraAtual() {

		return dataLeituraAtual;
	}

	public void setDataLeituraAtual(LocalDate dataLeituraAtual) {

		this.dataLeituraAtual = dataLeituraAtual;
	}

	public LocalDate getDataLeituraAnterior() {

		return dataLeituraAnterior;
	}

	public void setDataLeituraAnterior(LocalDate dataLeituraAnterior) {

		this.dataLeituraAnterior = dataLeituraAnterior;
	}

	public Short getDataLeituraDiasVenda() {

		return dataLeituraDiasVenda;
	}

	public void setDataLeituraDiasVenda(Short dataLeituraDiasVenda) {

		this.dataLeituraDiasVenda = dataLeituraDiasVenda;
	}

	public BigDecimal getLeituraAtual() {

		return leituraAtual;
	}

	public void setLeituraAtual(BigDecimal leituraAtual) {

		this.leituraAtual = leituraAtual;
	}

	public BigDecimal getLeituraAnterior() {

		return leituraAnterior;
	}

	public void setLeituraAnterior(BigDecimal leituraAnterior) {

		this.leituraAnterior = leituraAnterior;
	}

	public BigDecimal getLeituraConsumo() {

		return leituraConsumo;
	}

	public void setLeituraConsumo(BigDecimal leituraConsumo) {

		this.leituraConsumo = leituraConsumo;
	}

	public LocalidadeDTO getLocalidadeDTO() {

		return localidadeDTO;
	}

	public void setLocalidadeDTO(LocalidadeDTO localidadeDTO) {

		this.localidadeDTO = localidadeDTO;
	}

	public GrupoConsumoDropDownProjectionDTO getGrupoConsumoDTO() {

		return grupoConsumoDTO;
	}

	public void setGrupoConsumoDTO(GrupoConsumoDropDownProjectionDTO grupoConsumoDTO) {

		this.grupoConsumoDTO = grupoConsumoDTO;
	}

	public OcorrenciaLeituraDropDownProjectionDTO getOcorrenciaLeituraDTO() {

		return ocorrenciaLeituraDTO;
	}

	public void setOcorrenciaLeituraDTO(OcorrenciaLeituraDropDownProjectionDTO ocorrenciaLeituraDTO) {

		this.ocorrenciaLeituraDTO = ocorrenciaLeituraDTO;
	}

}

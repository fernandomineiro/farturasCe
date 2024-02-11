package moduloFaturamento.model.common;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="FTTOL")
public class OcorrenciaLeitura {

	@Id
	@Column(name="CD_OCORRENCIA")
	private Short cdOcorrencia;
	@Column(name="DC_OCORRENCIA") @Size(max = 30)
	private String dcOcorrencia;
	@Column(name="LEITURA_VIRTUAL") @Size(max = 1)
	private String leituraVirtual;
	@Column(name="MAINT") @Size(max = 1)
	private String maint;
	@Column(name="DATA_HORA_EXCLUSAO")
	private LocalDateTime dataHoraExclusao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tpOcorrencia", referencedColumnName = "ID")
	private TipoServicoOcorrenciaLeitura tipoOcorrencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTpAcaoParaOsh")
	private TipoAcaoOSH acaoOSH;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTpAcaoParaPesquisaIrregularidade")
	private TipoAcaoPesquisaIrregularidade acaoPesquisaIrregularidade;


	public OcorrenciaLeitura() {
		this.maint = "A";
	}

	public Short getCdOcorrencia() {
		return cdOcorrencia;
	}

	public void setCdOcorrencia(Short cdOcorrencia) {
		this.cdOcorrencia = cdOcorrencia;
	}

	public String getDcOcorrencia() {
		return dcOcorrencia;
	}

	public void setDcOcorrencia(String dcOcorrencia) {
		dcOcorrencia = Objects.requireNonNullElse(dcOcorrencia, "");
		this.dcOcorrencia = dcOcorrencia.toUpperCase();
	}

	public String getLeituraVirtual() {
		return leituraVirtual;
	}

	public void setLeituraVirtual(String leituraVirtual) {
		this.leituraVirtual = leituraVirtual;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public TipoServicoOcorrenciaLeitura getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoServicoOcorrenciaLeitura tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public TipoAcaoOSH getAcaoOSH() {
		return acaoOSH;
	}

	public void setAcaoOSH(TipoAcaoOSH acaoOSH) {
		this.acaoOSH = acaoOSH;
	}

	public TipoAcaoPesquisaIrregularidade getAcaoPesquisaIrregularidade() {
		return acaoPesquisaIrregularidade;
	}

	public void setAcaoPesquisaIrregularidade(TipoAcaoPesquisaIrregularidade acaoPesquisaIrregularidade) {
		this.acaoPesquisaIrregularidade = acaoPesquisaIrregularidade;
	}

	public LocalDateTime getDataHoraExclusao() {
		return dataHoraExclusao;
	}

	public void setDataHoraExclusao(LocalDateTime dataHoraExclusao) {
		this.dataHoraExclusao = dataHoraExclusao;
	}
}

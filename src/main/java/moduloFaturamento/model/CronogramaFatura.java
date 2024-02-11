package moduloFaturamento.model;

import javax.persistence.*;

@Entity
@Table(name = "FTTCR")
public class CronogramaFatura {

	@EmbeddedId
	private IdCronogramaFatura idCronogramaFatura;

	@Column(name = "ID",insertable = false,updatable = false)
	private Long id;

	@Column(name = "DT_CONSOLIDA_P")
	private Integer dataConsolidaPrevista;
	
	@Column(name = "DT_CONSOLIDA_R")
	private Integer dataConsolidaRealizada;
	
	@Column(name = "DT_CORTE_P")
	private Integer dataCortePrevista;
	
	@Column(name = "DT_CORTE_R")
	private Integer dataCorteRealizada;
	
	@Column(name = "DT_EMISSAO_P")
	private Integer dataEmissaoPrevista;
	
	@Column(name = "DT_EMISSAO_R")
	private Integer dataEmissaoRealizada;
	
	@Column(name = "DT_FATURAMENTO_P")
	private Integer dataFaturamentoPrevista;
	
	@Column(name = "DT_FATURAMENTO_R")
	private Integer dataFaturamentoRealizada;
	
	@Column(name = "DT_GERA_ARQUIVO_P")
	private Integer dataGeraArquivoPrevista;
	
	@Column(name = "DT_GERA_ARQUIVO_R")
	private Integer dataGeraArquivoRealizada;
	
	@Column(name = "DT_LEITURA_P")
	private Integer dataLeituraPrevista;
	
	@Column(name = "DT_LEITURA_R")
	private Integer dataLeituraRealizada;
	
	@Column(name = "DT_TARIFA")
	private Integer dataTarifa;
	
	@Column(name = "DT_VENCIMENTO")
	private Integer dataVencimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FASE", referencedColumnName = "ID")
	private TipoFaseCronograma fase;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS_PROCESSAMENTO", referencedColumnName = "ID")
	private TipoStatusProcessamento tipoStatusProcessamento;
	
	@Column(name = "MAINT")
	private String maint;


	public CronogramaFatura() {
		this.dataConsolidaRealizada = 0;
		this.dataCorteRealizada = 0;
		this.dataEmissaoRealizada = 0;
		this.dataFaturamentoRealizada = 0;
		this.dataGeraArquivoRealizada = 0;
		this.dataLeituraRealizada = 0;
		this.maint = "A";
	}

	public IdCronogramaFatura getIdCronogramaFatura() {
		return idCronogramaFatura;
	}

	public void setIdCronogramaFatura(IdCronogramaFatura idCronogramaFatura) {
		this.idCronogramaFatura = idCronogramaFatura;
	}



	public Integer getDataConsolidaPrevista() {
		return dataConsolidaPrevista;
	}

	public void setDataConsolidaPrevista(Integer dataConsolidaPrevista) {
		this.dataConsolidaPrevista = dataConsolidaPrevista;
	}

	public Integer getDataConsolidaRealizada() {
		return dataConsolidaRealizada;
	}

	public void setDataConsolidaRealizada(Integer dataConsolidaRealizada) {
		this.dataConsolidaRealizada = dataConsolidaRealizada;
	}

	public Integer getDataCortePrevista() {
		return dataCortePrevista;
	}

	public void setDataCortePrevista(Integer dataCortePrevista) {
		this.dataCortePrevista = dataCortePrevista;
	}

	public Integer getDataCorteRealizada() {
		return dataCorteRealizada;
	}

	public void setDataCorteRealizada(Integer dataCorteRealizada) {
		this.dataCorteRealizada = dataCorteRealizada;
	}

	public Integer getDataEmissaoPrevista() {
		return dataEmissaoPrevista;
	}

	public void setDataEmissaoPrevista(Integer dataEmissaoPrevista) {
		this.dataEmissaoPrevista = dataEmissaoPrevista;
	}

	public Integer getDataEmissaoRealizada() {
		return dataEmissaoRealizada;
	}

	public void setDataEmissaoRealizada(Integer dataEmissaoRealizada) {
		this.dataEmissaoRealizada = dataEmissaoRealizada;
	}

	public Integer getDataFaturamentoPrevista() {
		return dataFaturamentoPrevista;
	}

	public void setDataFaturamentoPrevista(Integer dataFaturamentoPrevista) {
		this.dataFaturamentoPrevista = dataFaturamentoPrevista;
	}

	public Integer getDataFaturamentoRealizada() {
		return dataFaturamentoRealizada;
	}

	public void setDataFaturamentoRealizada(Integer dataFaturamentoRealizada) {
		this.dataFaturamentoRealizada = dataFaturamentoRealizada;
	}

	public Integer getDataGeraArquivoPrevista() {
		return dataGeraArquivoPrevista;
	}

	public void setDataGeraArquivoPrevista(Integer dataGeraArquivoPrevista) {
		this.dataGeraArquivoPrevista = dataGeraArquivoPrevista;
	}

	public Integer getDataGeraArquivoRealizada() {
		return dataGeraArquivoRealizada;
	}

	public void setDataGeraArquivoRealizada(Integer dataGeraArquivoRealizada) {
		this.dataGeraArquivoRealizada = dataGeraArquivoRealizada;
	}

	public Integer getDataLeituraPrevista() {
		return dataLeituraPrevista;
	}

	public void setDataLeituraPrevista(Integer dataLeituraPrevista) {
		this.dataLeituraPrevista = dataLeituraPrevista;
	}

	public Integer getDataLeituraRealizada() {
		return dataLeituraRealizada;
	}

	public void setDataLeituraRealizada(Integer dataLeituraRealizada) {
		this.dataLeituraRealizada = dataLeituraRealizada;
	}

	public Integer getDataTarifa() {
		return dataTarifa;
	}

	public void setDataTarifa(Integer dataTarifa) {
		this.dataTarifa = dataTarifa;
	}

	public Integer getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Integer dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getMaint() {
		return maint;
	}

	public void setMaint(String maint) {
		this.maint = maint;
	}

	public TipoFaseCronograma getFase() {
		return fase;
	}

	public void setFase(TipoFaseCronograma fase) {
		this.fase = fase;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoStatusProcessamento getTipoStatusProcessamento() {
		return tipoStatusProcessamento;
	}

	public void setTipoStatusProcessamento(TipoStatusProcessamento tipoStatusProcessamento) {
		this.tipoStatusProcessamento = tipoStatusProcessamento;
	}
}

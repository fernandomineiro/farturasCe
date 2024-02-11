package moduloFaturamento.dto.faturaAvulsa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FaturaAvulsaPesquisaDTO {

    private Integer cdCliente;

    private LocalDate refFatura;
	private Short origemFatura;

	private Integer matriculaImovel;
	private Short dvMatriculaImovel;

	private BigDecimal valorFatura;

	private LocalDate dataVencimento;

	private String nomeBaixa;

	private LocalDate dataBaixaCompleto;

	private Integer agenteArrecadador;
	private Integer agenciaArrecadadora;
	private LocalDateTime dataProcessamento;
	private Integer numeroLote;

	private Integer refAtendimento;
	private Integer cdAtendimento;
	private Short seqSS;

	private Long id;

	public Integer getCdCliente() {
		return cdCliente;
	}
	public void setCdCliente(Integer cdCliente) {
		this.cdCliente = cdCliente;
	}
	public LocalDate getRefFatura() {
		return refFatura;
	}
	public void setRefFatura(LocalDate refFatura) {
		this.refFatura = refFatura;
	}
	public Short getOrigemFatura() {
		return origemFatura;
	}
	public void setOrigemFatura(Short origemFatura) {
		this.origemFatura = origemFatura;
	}
	public BigDecimal getValorFatura() {
		return valorFatura;
	}
	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getNomeBaixa() {
		return nomeBaixa;
	}
	public void setNomeBaixa(String nomeBaixa) {
		this.nomeBaixa = nomeBaixa;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getAgenteArrecadador() {
		return agenteArrecadador;
	}
	public void setAgenteArrecadador(Integer agenteArrecadador) {
		this.agenteArrecadador = agenteArrecadador;
	}
	public Integer getAgenciaArrecadadora() {
		return agenciaArrecadadora;
	}
	public void setAgenciaArrecadadora(Integer agenciaArrecadadora) {
		this.agenciaArrecadadora = agenciaArrecadadora;
	}
	public LocalDateTime getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(LocalDateTime dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public Integer getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}
	public LocalDate getDataBaixaCompleto() {
		return dataBaixaCompleto;
	}
	public void setDataBaixaCompleto(LocalDate dataBaixaCompleto) {
		this.dataBaixaCompleto = dataBaixaCompleto;
	}
	public Integer getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(Integer matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public Short getDvMatriculaImovel() {
		return dvMatriculaImovel;
	}
	public void setDvMatriculaImovel(Short dvMatriculaImovel) {
		this.dvMatriculaImovel = dvMatriculaImovel;
	}
	
}

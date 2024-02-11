package moduloFaturamento.dto.cadastramentoFatura;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FaturaGridRespostaDTO {

	private Integer id;

	private LocalDate refFatura;

	private Integer origemFatura;

	private Integer versaoFatura;

	private BigDecimal valorFatura;

	private LocalDate dataVencimento;

	private LocalDate dataPagamento;

	private String situacaoBaixa;

	private Integer codigoCliente;

	private String nomeCliente;

	private Integer refBaixa;

	private Integer diaBaixa;

	private Integer dvCliente;

	public Integer getDvCliente(){
		return dvCliente;
	}

	public void setDvCliente(Integer dvCliente) {
		this.dvCliente = dvCliente;
	}


	public Integer getId() {

		return id;
	}

	public void setRefBaixa(Integer refBaixa){
		this.refBaixa = refBaixa;
	}

	public Integer getRefBaixa() {
		return this.refBaixa;
	}

	public void setDiaBaixa(Integer diaBaixa){
		this.diaBaixa = diaBaixa;
	}

	public Integer getDiaBaixa() {
		return this.diaBaixa;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public LocalDate getRefFatura() {

		return refFatura;
	}

	public void setRefFatura(LocalDate refFatura) {

		this.refFatura = refFatura;
	}

	public Integer getOrigemFatura() {

		return origemFatura;
	}

	public void setOrigemFatura(Integer origemFatura) {

		this.origemFatura = origemFatura;
	}

	public Integer getVersaoFatura() {

		return versaoFatura;
	}

	public void setVersaoFatura(Integer versaoFatura) {

		this.versaoFatura = versaoFatura;
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

	public LocalDate getDataPagamento() {

		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {

		this.dataPagamento = dataPagamento;
	}

	public String getSituacaoBaixa() {

		return situacaoBaixa;
	}

	public void setSituacaoBaixa(String situacaoBaixa) {

		this.situacaoBaixa = situacaoBaixa;
	}

	public Integer getCodigoCliente() {

		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {

		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {

		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {

		this.nomeCliente = nomeCliente;
	}

}
